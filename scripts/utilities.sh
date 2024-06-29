#!/bin/bash
#Utilerias para generar y gestionar la imagen de la aplicacion: retail-store.

NAME_IMG="localhost:5000/retail-store:1.0.0"
CONTAINER_NAME="retail-store"

PATH_LOGS_FILE=scripts/logs/utilities.log

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

#Genera la imagen: agrega un tag para nombrar e identificar el destino del docker registry.
create() {
    docker build -t ${NAME_IMG} .
}

#Agrega la imagen al registry
add_to_registry() {
    REGISTRY_NAME="registry"

    container_is_exist() {
        is_exists_container=$(docker ps -a --format "{{.Names}}" | awk "/${REGISTRY_NAME}/")
        if [ "${is_exists_container}" != "" ]; then
            echo "Existe el contenedor: ${REGISTRY_NAME}" >> "$PATH_LOGS_FILE"
        fi
        
        # check_health_docker_registry=$(curl -s localhost:5000/v2/_catalog | jq -r '.repositories[]')
        # if [ "$check_health_docker_registry" = "" ]; then
            # echo "[$(date +"%Y-%m-%d %H:%M:%S")] El servicio registry esta detenido." >>scripts/logs/utilities.log
            # echo "[$(date +"%Y-%m-%d %H:%M:%S")] Levantando servicio" >>scripts/logs/utilities.log
            # echo "[$(date +"%Y-%m-%d %H:%M:%S")] Verificando si existe el contenedor" >>scripts/logs/utilities.log
            # echo "" >>scripts/logs/utilities.log
            # exit 127
        # fi
    }

    is_running() {
        state_container=$(docker container inspect registry | jq '.[].State')
        if [[ $(echo $state_container | jq '.Running') == "false" ]]; then
            echo "No esta corriendo el contenedor"
            docker start ${REGISTRY_NAME}
        fi
    }

    # Dependo del is_running' para registrar la imagen
    # is_running && docker push ${NAME_IMG}
    
    #Dependencias
    container_is_exist
}

NAME_CONTAINER=""

running_container() {
    is_running_container() {

    }
    docker run --name retail-store:1.0.0 -p 8080:8080 ${NAME_IMG}
}

run() {
    create &&
        add_to_registry
    # running_container
}

# state_container "registry"
add_to_registry

# create
echo "[message: salida estandar del script]"
exit 127
