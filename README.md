### How to do the BDD

1. Write a test scenario in Feature file: **src/test/resources/GildedRose.feature**
2. Modify the StepDefinitions file to match the Feature description in: **src/test/java/com/gildedrose/StepDefinitions.java**
3. Run: **./gradlew cucumber** from project dir

Note: Please check https://cucumber.io for syntax references.

Run Gradle
./gradlew clean test     
Run the local sonarqube
docker run -d --name sonarqube   -p 9000:9000   sonarqube:latest        
echo $env:SONAR_TOKEN

./gradlew sonar `
  "-Dsonar.projectKey=Java-Cucumber" `
"-Dsonar.projectName=Java-Cucumber" `
  "-Dsonar.host.url=http://localhost:9000" `
"-Dsonar.token=$env:SONAR_TOKEN"