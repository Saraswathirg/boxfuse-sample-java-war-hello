pipeline{
agent any
stages{
    stage("cloning the code"){
        steps{
            println"the code is cloned"
            sh "ls -l"
            checkout([
                $class:'GitSCM',
                branches:[[name:*/master]],
                doGenerateSubmoduleconfigurations:false,
                extensions:[],
                submoduleCfg:[],
                userRemoteConfigs:[https://github.com/Saraswathirg/boxfuse-sample-java-war-hello.git]
            ]
            )
        }
    }
stage("building the code using maven clean package"){
    steps{
        println"here the code is converted to machine readable code"
    }
}
stage("uploading artifacts"){
    steps{
        println"here the artifact is moved to s3"
}
}
}
} 
