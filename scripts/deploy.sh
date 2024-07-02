#!/bin/zsh
#Utileria para desplegar ensamblar un archivo JAR en un servidor remoto:

# Ruta del proyecto
PROJECT_PATH=${PROJECT_PATH:-$1}
if [ -z "$PROJECT_PATH" ]; then
  echo "Error: Ingresa el [PROJECT_PATH] para continar. \nFinalizado con estatus 1"
  exit 1
fi

# Nombre del archivo jar
JAR_NAME=${JAR_NAME:-$2}
if [ -z "$JAR_NAME" ]; then
  echo "Error: Ingresa el [JAR_NAME] para continuar \nFinalizado con estatus 1"
  exit 1
fi

# Directorio de construcción (ajusta esto según tu configuración de Gradle)
BUILD_DIR="$PROJECT_PATH/build/libs"

#Carga de variables en el contexto del script
echo "Variables Cargadas en el Contexto del Script:"
echo "PROJECT_PATH=$PROJECT_PATH"
echo "JAR_NAME=$JAR_NAME"
echo "BUILD_DIR=$BUILD_DIR"

# Usuario y servidor remoto
REMOTE_USER="pi"
REMOTE_HOST="192.168.1.174"
REMOTE_DIR="~/deploy/"

# Construir el archivo .jar usando Gradle
cd $PROJECT_PATH

#valida si estan instaladas en el sistema las herramientas (dependencias)
tools() {
  echo "[$(date +"%Y-%m-%d %H:%M:%S")] Step1: Verificando las herramientas para emsamblar del archivo JAR"
  gradle --version
  if [ $? -eq 0 ]; then
    echo "Step1: Finalizo exitosamente"
  else
    echo "Step1: Fallo la ejecucion: Comando Gradle no encontrado: Instala la version 8.0 o superior para correr este script"
    exit 1
  fi
}

build() {
  echo "[$(date +"%Y-%m-%d %H:%M:%S")] Step2: Ensamblando el archivo JAR del proyecto: Realizando pruebas unitarias y de integracion"
  ./gradlew clean build && JAR_FILE=$(ls build/libs/*.jar)
  if [ $? -eq 0 ]; then
    echo "Step2: Finalizo con éxito: Se ensamblo correctamente el archivo JAR del proyecto"
    echo "JAR_FILE Ensamblado: \t $JAR_FILE"
  else
    echo "Step2: Se genero un error: No se pudo emsamblar el archivo JAR. Causa: "
    exit 1
  fi
}

copyToServer() {
  JAR_FILE=$(ls build/libs/*.jar)
  echo "[$(date +"%Y-%m-%d %H:%M:%S")] Step3: Copiando el archivo JAR: \t[${JAR_FILE}] al servidor: [pi@piserver:~/deploy]"
  scp ${JAR_FILE} piserver:~/deploy/
  if [[ $? -eq 0 ]]; then
    echo "Step3: Finalizo exitosamente Archivo JAR copiado al servidor finalizo con exito"
  else
    echo "Step3: fallo la ejecuccion: Archivo JAR no pudo copiarse al servidor. Causado por: "
  fi
}

#JAR Utilities: Depende de que exista en el servidor remoto un JAR
start_server() {
  JAR_FILE=$(basename build/libs/*.jar)
  echo "[$(date +"%Y-%m-%d %H:%M:%S")] Step4: Levantando la aplicacion en el servidor remoto.."
  echo "cd deploy/; bin/start.sh start_embed_server $JAR_FILE" | ssh piserver "cat|sh"
  if [ $? -eq 0 ]; then
    echo "Step 4: Termino de ejecutarse el: resultado de la ejecucion: $?"
  fi
  # echo "cd deploy/; bin/start.sh $JAR_FILE" | ssh piserver "cat|sh" > scripts/output.txt
}

# Verifica la salud del servidor desplegado
check_health_server() {
  echo "Step5: Verificando salud del servidor tomcat levantado en el servidor"
  HTTP_CODE=$(curl -s "https://miportafoliodvaldez.com/api/categories" -u user:password -o /dev/null -w "%{http_code}" | tr -d '\r')

  if [ $HTTP_CODE -ge 500 ]; then
    echo "[Servidor esta detenido] \tError el servidor no se pudo levantar: consulta los logs para mayor detalle"
    # echo "respuesta de servidor: $response"
  else
    echo "[Servidor esta corriendo] \tEl servidor esta en linea y preparado para escuchar peticiones entrantes en la red"
  fi
}

# check_health_server

run() {
  tools && 
  build &&
  copyToServer &&
  start_server &&
  # check_health_server
  #Ejecuta en segundo plano y agrega un PID
  # start_server &
  # PID=$!
  # wait $PID
  # echo "Este sale de la ejecuccion: $PID"
}

run
