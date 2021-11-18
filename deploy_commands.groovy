pipeline{
    agent any
    parameters{
    string(name:'BRANCH_NAME',defaultValue:'master')
    string(name:'BRANCH_NUM')
    string(name:'SERVER_IP')
}
stages{
    stage("downloading the artifact"){
        steps{
            println"here the artifact is downloaded"
            sh "aws s3 ls"
            sh "aws s3 ls s3://publicbucke"
            sh "aws s3 ls s3://publicbucke/${BRANCH_NAME}/${BUILD_NUM}"
            sh "aws s3 ls s3://publicbucke/${BRANCH_NAME}/${BUILD_NUM}/hello-${BUILD_NUM}.war"
        }
    }
    stage("copying the artifact"){
        steps{
            println"artifcats copied" 
        }
    }
}
}