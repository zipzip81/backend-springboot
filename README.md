This is a regular Spring Boot application using Tomcat server.

## Build
```
mvn package
```

## Run
```
java -jar target/helloworld.jar
```

## Containerize with Jib
```
mvn compile com.google.cloud.tools:jib-maven-plugin:2.4.0:build -Dimage=gcr.io/PROJECT_ID/helloworld-springboot-tomcat-jib
```

## Docker Build 
```
mvn package
docker build -t gcr.io/PROJECT_ID/helloworld-springboot-tomcat-appcds .
```

Run without AppCDS:
```
docker run -ti --rm gcr.io/PROJECT_ID/helloworld-springboot-tomcat-appcds
```

## App Engine

```
gcloud app deploy target/helloworld.jar
```

## Cloud Run
Run with Jib
```
gcloud run deploy helloworld-springboot-tomcat-jib \
  --image=gcr.io/PROJECT_ID/helloworld-springboot-tomcat-jib \
   --region=us-central1 \
   --platform managed \
   --allow-unauthenticated
```

Run with Docker Image, without AppCDS
```
gcloud run deploy helloworld-springboot-tomcat-docker \
  --image=gcr.io/PROJECT_ID/helloworld-springboot-tomcat \
  --region=us-central1 \
  --platform managed \
  --allow-unauthenticated
```

Run with Docker Image, without AppCDS, with Tiered compilation
```
gcloud run deploy helloworld-springboot-tomcat-docker-t1 \
  --image=gcr.io/PROJECT_ID/helloworld-springboot-tomcat \
  -e JAVA_TOOL_OPTIONS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
  --region=us-central1 \
  --platform managed \
  --allow-unauthenticated
```

Bash
```
#!/bin/bash

# 1. Create two projects
gcloud projects create --name="Frontend-ReactJS"
gcloud projects create --name="Backend-Springboot"

# Set region to asia-southeast1
gcloud config set compute/region asia-southeast1

# 2. Clone the backend-springboot and frontend-reactjs repositories
git clone https://github.com/zipzip81/backend-springboot.git
git clone https://github.com/zipzip81/frontend-reactjs.git

# 3. Deploy the frontend-reactjs code to Firebase Hosting
cd frontend-reactjs
gcloud config set project frontend-reactjs
npm install
npm run build
firebase login --no-localhost
firebase init hosting
firebase deploy

# 4. Deploy the backend-springboot code to App Engine
cd ../backend-springboot
gcloud config set project backend-springboot
./mvnw clean package
gcloud app create
gcloud app deploy

echo "Script finished running."
```
