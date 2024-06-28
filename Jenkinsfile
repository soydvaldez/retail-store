pipeline {
    agent any
    tools {
        gradle '8.6'
    }
    environment {
        DOCKER_IMAGE = 'localhost:5000/retail-store:1.0.0'
        CONTAINER_NAME = 'retail-store'
        REGISTRY = 'localhost:5000/'
        REGISTRY_CREDENTIALS = 'docker-credentials'
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Stage - Checkout: Cloning from Remote Repository...'
                git branch: 'main', credentialsId: '2', url: 'git@github.com:soydvaldez/retail-store.git'
                script {
                    sh 'git branch --show-current'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Stage - Test: Running Tests...'
                script {
                    sh "gradle wrapper && ./gradlew test"
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Stage - Build: Generate a JAR Package and Build a Docker Image'
                script {
                    sh './gradlew clean build -x test'
                    // Construir la imagen Docker
                    docker.build(DOCKER_IMAGE)
                }
            }
        }
        stage('Push') {
            steps {
                echo 'Stage - Push: Save Image to Docker Registry'
                script {
                    // Iniciar sesión en el registro Docker
                    docker.withRegistry("http://${env.REGISTRY}") {
                        // Etiquetar y hacer push a la imagen generada hacia el Docker al registro
                        def dockerImage = docker.image(DOCKER_IMAGE)
                        dockerImage.push()
                    }
                }
            }
        }

        // stage('Deploy') {
        //     steps {
        //             echo "Desplegando el contenedor"
        //             echo 'Ejecutando el deploy de la imagen'
        //         script {
        //             sh 'docker --version'
        //             sh 'docker stop ${CONTAINER_NAME}'
        //             sh 'docker rm ${CONTAINER_NAME}'
        //             sh 'docker run -d --name ${CONTAINER_NAME} -p 8080:8080 ${DOCKER_IMAGE}'
        //         }
        //     }
        // }
    }

    post {
        always{
            cleanWs()
            // mail to: 'tu-email@dominio.com',
            // subject: "Pipeline ${currentBuild.fullDisplayName} Finalizado",
            // body: "El pipeline ${currentBuild.fullDisplayName} ha finalizado con el estado: ${currentBuild.currentResult}"
        }
        success {
            echo 'Pipeline ejecutado con éxito!'
        }
        failure {
            echo 'Pipeline Fallo!'
        }
    }
}