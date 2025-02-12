pipeline{
    agent any
    parameters{
    string(name:'BRANCH_NAME',defaultValue:'master')
    string(name:'BUILD_NUM')
    string(name:'SERVER_IP')
}
stages{
    stage("downloading the artifact"){
        steps{
            println"here the artifact is downloaded"
            sh "aws s3 ls"
            sh "aws s3 ls s3://publicbucke"
            sh "aws s3 ls s3://publicbucke/${BRANCH_NAME}/${BUILD_NUM}"
            sh "aws s3 cp s3://publicbucke/${BRANCH_NAME}/${BUILD_NUM}/hello-${BUILD_NUM}.war ."
        }
    }
    stage("copying the artifact"){
        steps{
            println"artifcats copied" 
            sh "ssh -i /tmp/linuxcommands.pem ec2-user@${SERVER_IP} \"systemctl status tomcat\""
            sh "scp -i /tmp/linuxcommands.pem hello-${BUILD_NUM}.war ec2-user@${SERVER_IP}:/var/lib/tomcat/webapps/"
        }
    }
}
}