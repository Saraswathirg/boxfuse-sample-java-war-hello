pipeline{
    agent any
    stages{
        stage("adding the code"){
            steps{
                println"clone added"
            }
        }
    satge("building code"){
        steps{
            println"code builded"
        }
    }
} 
}