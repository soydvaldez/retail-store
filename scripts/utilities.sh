#!/bin/bash
#Utilerias para generar y gestionar la imagen de la aplicacion: retail-store. dependencias: El registry esta levantado, la imagen se creo con exito

SCRIPT=$0
echo "\nInicializando script: $SCRIPT"

IMG_NAME="localhost:5000/retail-store:1.0.0"
CONTAINER_NAME="retail-store"

PATH_LOGS_FILE=scripts/logs/utilities_activity.log

LOGFILE=./scripts/logs/utilities_activity1.log
BUILD_LOG=./scripts/logs/utilities_activity2.log

echo "Variables setteadas dentro del contexto de script: IMG_NAME:$IMG_NAME \tCONTAINER_NAME: $CONTAINER_NAME"
echo "LOGFILE:$LOGFILE \tBUILD_LOG:$BUILD_LOG"

error_handler() {
    local lineno=$1
    local funcname=${FUNCNAME[1]}
    echo "Error en la línea $lineno dentro de la función $funcname"
}

# trap 'error_handler $LINENO' ERR

check_error() {
    if [[ "$?" -ne 0 ]]; then
        echo "Hubo un Error. Ver $LOGFILE para mayor detalle"
    fi
}

timeToSleep=1
goToSleep() {
    sleep $timeToSleep
}

currentTime=""
getCurrentTime() {
    currentTime="[$(date +"%Y-%m-%d %H:%M:%S")]"
}

# Imprime por terminal y captura los logs de script
logs() {
    getCurrentTime
    if [[ $2 -eq 1 ]]; then
        echo "\n$currentTime $1" | tee -a "./scripts/temporal.log"
    else
        echo "$currentTime $1" | tee -a "./scripts/temporal.log"
    fi
}

#Construye la imagen del proyecto:  Agrega un tag para nombrar y poder identificar la url destino para guardarla en el servidor: docker registry.
createImage() {
    goToSleep
    logs "Step1: Create Docker Image \t[start]" "1"

    docker build -t ${IMG_NAME} . 2> >(tee -a $LOGFILE >&2) | tee -a $BUILD_LOG
    check_error "Error en la construccion de la imagen Docker"

    logs "Step1: Create Docker Image \t[done]"
}

#Agrega la imagen del proyecto al servidor de docker registry
addImageToRegistry() {
    goToSleep
    logs "Step2: Add Image To Docker Registry" "1" "\t[start]"
    logs "Docker Registry Running at URL: localhost:5000 \t[running]"

    echo "[$(date +"%Y-%m-%d %H:%M:%S")] Currently Running Function: add_to_registry"
    local CONTAINER_NAME="registry"

    is_container_exist() {
        echo "[$(date +"%Y-%m-%d %H:%M:%S")] Currently Running Function: is_container_exist  \t[OK]"
        isContainerExists=$(docker ps -a --format "{{.Names}}" | awk "/${CONTAINER_NAME}/")
        if [ "${isContainerExists}" = "" ]; then
            echo "No existe el contenedor: $(echo $CONTAINER_NAME | tr 'a-z' 'A-Z')" | tee "$PATH_LOGS_FILE"
            # echo "[$(date +"%Y-%m-%d %H:%M:%S")] El servicio registry esta detenido." >>$LOGFILE
            docker run -it \
                --name "${CONTAINER_NAME}" \
                --restart always \
                -v "$HOME/docker/volumes/registry:/var/lib/registry" \
                -p 5000:5000 \
                -d registry:2
        fi
    }

    # Aqui tengo una dependencia:
    is_container_running() {
        echo "[$(date +"%Y-%m-%d %H:%M:%S")] Currently Running Function: is_container_running \t[OK]"
        state_container=$(docker container inspect registry | jq '.[].State')
        if [[ $(echo $state_container | jq '.Running') == "false" ]]; then
            echo "No esta corriendo el contenedor"
            docker start ${CONTAINER_NAME}
        fi

        echo $state_container | jq .
    }

    check_health_container() {
        echo "[$(date +"%Y-%m-%d %H:%M:%S")] Currently Running Function ] check_health_container \t[OK]"
        URL="localhost:5000/v2/_catalog"
        http_code=$(curl -o /dev/null -s -w "%{http_code}" "$URL" | tr -d "\r")
        if [ "$http_code" -eq 200 ]; then
            echo "[$(date +"%Y-%m-%d %H:%M:%S")] Servicio docker registry esta levantado y escuchando peticiones por la URL: '$URL'" | tee ./scripts/logs/utilities.log
        fi
    }

    #Dependencias:
    is_container_exist && is_container_running && check_health_container && docker push ${IMG_NAME}
    logs "Step2: Add Image To Docker Registry \t[done]"
}

deployAndStartContainer() {
    goToSleep
    logs "Step3: Deploy And Start Container \t[start]" "1"
    docker run --name retail-store -p 8080:8080 ${IMG_NAME}
    logs "Step3: Deploy And Start Container \t[done]"
}

run() {
    createImage
    addImageToRegistry
    deployAndStartContainer
    goToSleep
    logs "Finalizo la ejecuccion del script: $SCRIPT" "1"
}

run

# URL="localhost:8080/api/products"
# curl -s -u admin:password  $URL | jq ".content.[0]"

# Levantar el servidor consumir algunas rutas

# Mejorar: Debo de agregar un trace para registrar en que step fallo y poder volver a ejecutar desde ese punto en especifico
