#!/bin/bash
#Utilerias para generar y gestionar la imagen de la aplicacion: retail-store.
#supuesto: El registry esta levantado, la imagen se creo con exito

set -e

IMG_NAME="localhost:5000/retail-store:1.0.0"
CONTAINER_NAME="retail-store"

PATH_LOGS_FILE=scripts/logs/utilities_activity.log

LOGFILE=scripts/logs/utilities_activity1.log
BUILD_LOG=scripts/logs/utilities_activity2.log

error_handler() {
    local lineno=$1
    local funcname=${FUNCNAME[1]}
    echo "Error en la línea $lineno dentro de la función $funcname"
}

trap 'error_handler $LINENO' ERR

check_error() {
    if [[ "$?" -ne 0 ]]; then
        echo "Hubo un Error. Ver $LOGFILE para mayor detalle"
    fi
}

#Verifica el estado de un contenedor
state_container() {
    CONTAINER_TO_VERIFY="$1"
    echo "Verificando la salud del contenedor: " "\t[$CONTAINER_TO_VERIFY]"
    salida=$(
        CONTAINER_NAME="registry"
        docker ps -a --filter "name=$CONTAINER_NAME" | awk 'NR>1 {print $1, $NF}'
    )

    if [[ "$salida" = "" ]]; then
        echo "la cadena no esta vacia, continuando"
        echo "segundo mensaje"
    else echo "sino se cumple haz esto"; fi
    # docker container inspect ${CONTAINER} | jq '.[].State'
}

#Construye la imagen del proyecto:  Agrega un tag para nombrar y poder identificar la url del servidor docker registry.
create() {
    docker build -t ${IMG_NAME} . 2> >(tee -a $LOGFILE >&2) | tee -a $BUILD_LOG
    check_error "Error en la construccion de la imagen Docker"
}

#Agrega la imagen del proeycto al servidor de docker registry
addToRegistry() {
    echo "[$(date +"%Y-%m-%d %H:%M:%S")] Funcion actual: add_to_registry"
    local CONTAINER_NAME="registry"

    is_container_exist() {
        echo "[$(date +"%Y-%m-%d %H:%M:%S")] Funcion anidada (actual): is_container_exist  \t[OK]"
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
        echo "[$(date +"%Y-%m-%d %H:%M:%S")] Funcion anidada (actual)] is_container_running \t[OK]"
        state_container=$(docker container inspect registry | jq '.[].State')
        if [[ $(echo $state_container | jq '.Running') == "false" ]]; then
            echo "No esta corriendo el contenedor"
            docker start ${CONTAINER_NAME}
        fi

        echo $state_container | jq .
    }

    check_health_container() {
        echo "[$(date +"%Y-%m-%d %H:%M:%S")] Funcion anidada (actual)] check_health_container \t[OK]"
        URL="localhost:5000/v2/_catalog"
        http_code=$(curl -o /dev/null -s -w "%{http_code}" "$URL" | tr -d "\r")
        if [ "$http_code" -eq 200 ]; then
            echo "[$(date +"%Y-%m-%d %H:%M:%S")] Servicio docker registry esta levantado y escuchando peticiones por la URL: '$URL'" | tee ./scripts/logs/utilities.log
        fi
    }

    #Dependencias
    is_container_exist && is_container_running && check_health_container && docker push ${IMG_NAME}
}

running_container() {
    docker run --name retail-store -p 8080:8080 ${IMG_NAME}
}

run() {
    create && addToRegistry
    # running_container
}

# run
URL="localhost:8080/api/products"
curl -s -u admin:password  $URL | jq ".content.[0]"
