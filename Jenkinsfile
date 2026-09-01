pipeline {
    agent { label 'linux && chrome' }

    options {
        timeout(time: 20, unit: 'MINUTES')
        disableConcurrentBuilds(abortPrevious: true)
        buildDiscarder(logRotator(daysToKeepStr: '7', artifactDaysToKeepStr: '7'))
        skipDefaultCheckout(true)
        timestamps()
    }

    environment {
        CUCUMBER_PUBLISH_ENABLED = 'true'
        CUCUMBER_PUBLISH_QUIET = 'false'
        HEADLESS = 'true'
    }

    stages {
        stage('Descargar el repositorio') {
            steps {
                checkout scm
            }
        }

        stage('Verificar el entorno') {
            environment {
                TEST_USER_EMAIL = credentials('TEST_USER_EMAIL')
                TEST_USER_PASSWORD = credentials('TEST_USER_PASSWORD')
            }

            steps {
                sh '''#!/usr/bin/env bash
                    set -euo pipefail
                    java -version
                    google-chrome --version

                    if [[ -z "$TEST_USER_EMAIL" || -z "$TEST_USER_PASSWORD" ]]; then
                        echo "Faltan las credenciales TEST_USER_EMAIL y/o TEST_USER_PASSWORD." >&2
                        exit 1
                    fi
                '''
            }
        }

        stage('Ejecutar la suite completa') {
            environment {
                TEST_USER_EMAIL = credentials('TEST_USER_EMAIL')
                TEST_USER_PASSWORD = credentials('TEST_USER_PASSWORD')
            }

            steps {
                sh '''#!/usr/bin/env bash
                    set -o pipefail
                    ./gradlew clean test --no-daemon 2>&1 | tee cucumber-run.log
                '''
            }
        }
    }

    post {
        always {
            script {
                if (fileExists('cucumber-run.log')) {
                    def reportUrls = readFile('cucumber-run.log').findAll(
                        /https:\/\/reports\.cucumber\.io\/reports\/[A-Za-z0-9-]+/
                    )

                    if (reportUrls) {
                        def reportUrl = reportUrls.last()
                        currentBuild.description = "Cucumber: ${reportUrl}"
                        writeFile file: 'cucumber-report-url.txt', text: "${reportUrl}\n"
                        echo "Reporte público de Cucumber (disponible durante 24 horas): ${reportUrl}"
                    } else {
                        echo 'Cucumber no devolvió una URL de reporte publicado. Revisá el log de la ejecución.'
                    }
                }
            }

            junit allowEmptyResults: true,
                testResults: 'build/test-results/cucumber/cucumber.xml'

            archiveArtifacts allowEmptyArchive: true,
                artifacts: 'build/reports/**, build/test-results/**, cucumber-run.log, cucumber-report-url.txt',
                fingerprint: true

            publishHTML target: [
                allowMissing: true,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/reports/cucumber',
                reportFiles: 'cucumber.html',
                reportName: 'Reporte Cucumber'
            ]
        }
    }
}
