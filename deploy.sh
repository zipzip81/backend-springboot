#!/bin/bash

# 1. Create two projects
gcloud projects create frontend-reactjs --name="Frontend-ReactJS-1"
gcloud projects create backend-springboot --name="Backend-Springboot-1"

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
