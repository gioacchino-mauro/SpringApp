import java.text.SimpleDateFormat

def releaseVersion = ""
def sha1 = ""

pipeline {
	agent any
	 stages {	
		stage('prepare') {
			steps {
				script {
					echo "Releasing branch master..."                
				}
			}
		}

         
		/*stage('Source checkout') {
            steps {
                script{
                     def scmUrl = scm.getUserRemoteConfigs()[0].getUrl()
                     def scmCredentialsId = scm.getUserRemoteConfigs()[0].getCredentialsId()

                     echo "Checking out ${BUILD_BRANCH}@${scmUrl} credentials id ${scmCredentialsId}"
                     echo "user remote configs: ${scm.getUserRemoteConfigs()}"

               def scmVars =  checkout(
                        [$class                           : 'GitSCM', branches: [[name: "${BUILD_BRANCH}"]],
                         doGenerateSubmoduleConfigurations: false,
                         extensions                       : [],
                         submoduleCfg             testidentityservice        : [],
                         userRemoteConfigs                : [[credentialsId: "${GIT_CREDENTIAL_ID}", url: "${APP_GIT_URL}"]]]
                )
                echo "scmVars.GIT_COMMIT"
                echo "${scmVars.GIT_COMMIT}"
                echo "env.GIT_COMMIT"
                echo "${env.GIT_COMMIT}"
                sha1 = scmVars.GIT_COMMIT
                echo "sha1"
                echo "${sha1}"
                }
            }
        }*/
		stage('Checkout repository') {
			/* Cloning the Repository to our Workspace */
            steps {
			    checkout scm
            }
		}

		stage('Read Version') {
			steps{
				script{
					version = readFile file: "VERSION"
					version = version.trim()
					echo "Version: ${version}"
					releaseVersion = version.replace("-SNAPSHOT", "");
					echo "releaseVersion: ${releaseVersion}"
					echo "before replace version: ${version}"					
				    writeFile file: "VERSION", text: releaseVersion
                    version = readFile file: "VERSION" 
					echo "after replace VERSION: ${version}"
						
				}	
			}				
		}
		
		stage('Docker Login') {
			steps {
				script {
					try{
						withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]){
							def updateResult = bat(script: "docker login ${DOCKER_REGISTRY} -u $USERNAME -p $PASSWORD ", returnStdout: true)
							echo updateResult;
						}						
					}catch(e){
						echo "Error in Login:"+e.toString()
					}
				}
			}
		}

		stage('Building image') {
		  steps{
			script {
					  bat "docker build -f ${DOCKER_FILE} -t ${DOCKER_REGISTRY}/${IMAGE_NAME}:${releaseVersion} ."        }
			}
		}
		
		stage('Docker Push') {
			steps {
				script {
					try{
						def updateResult = bat (script: "docker push ${DOCKER_REGISTRY}/${IMAGE_NAME}:${releaseVersion}", returnStdout: true)
						echo updateResult;
					}catch(e){
						echo "Error in Pull:"+e.toString()
					}
				}
			}
		}
    }
}