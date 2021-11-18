pipeline{
    agent any
    stages{
        stage{
            steps("adding the code"){
                println"clone added"
            }
        }
    satge{
        steps("building code"){
            println"code builded"
        }
    }
} 
}