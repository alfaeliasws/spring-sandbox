Compile package for jar = mvn compile
Run Default Properties= java -jar target/springconfig-0.0.1-SNAPSHOT.jar
Run Change Profile= java -jar target/springconfig-0.0.1-SNAPSHOT.jar --spring.profiles.active=production
External Properties cmd = java -jar target/springconfig-0.0.1-SNAPSHOT.jar --spring.config.location=external.properties