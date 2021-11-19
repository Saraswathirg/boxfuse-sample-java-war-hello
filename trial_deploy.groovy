pipeline{
    agent any
    parameters{
        string(name:'BRANCH_NAME',defaultValue:'master')
        string(name:'BUILD_NUMBER',defaultValue:'')
        string(name:'SERVER_IP',defaultValue:'')
    }
    stages{
        stage("download the artifacts"){
            steps{
                println "here the artifact downloaded"
                sh """
                aws s3 ls
                aws s3 ls s3://buildsteps
                aws s3 cp s3://buildsteps/${BRANCH_NAME}/${BUILD_NUMBER}/hello-${BUILD_NUMBER}.war ."""
            }
        }
        stage("here the code is copied"){
            steps{
                println "code copied"
                sh "scp -i /tmp/awsaws.pem hello-${BUILD_NUMBER}.war ec2-user@${SERVER_IP}:/var/lib/tomcat/webapps"
            }
        }
    }
}