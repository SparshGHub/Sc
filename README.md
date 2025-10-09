# Software Production Engineering  
## Mini Project 1  
### Scientific Calculator: CI/CD Pipeline using Docker, Jenkins, and Ansible  

---

## Overview

The **Scientific Calculator** is a **Java-based command-line application** designed to perform a variety of mathematical and scientific operations.  
This project demonstrates a **complete CI/CD (Continuous Integration and Continuous Deployment)** setup using **Jenkins**, **Docker**, and **Ansible**.

When new code is pushed to GitHub, Jenkins automatically:
1. Fetches the latest source code  
2. Builds and tests it using Maven  
3. Packages it into a Docker image  
4. Pushes the image to DockerHub  
5. Deploys it via Ansible automatically  

This ensures the latest version of the calculator is always tested, packaged, and ready to run.

---

## Tools Used

| Tool | Purpose |
|------|----------|
| **GitHub** | Version control and Jenkins trigger |
| **Jenkins** | Automates the build, test, and deployment process |
| **Maven** | Builds and manages Java project dependencies |
| **Docker** | Packages the calculator into an isolated environment |
| **Ansible** | Handles deployment automation |

---

## How to Run the Application

### Option 1: Run via Docker (Recommended)

If Docker is already installed on your system, simply run:

```bash
docker run -it --name scicalc sparshdockerman/scicalc:latest
```

This opens the **Scientific Calculator CLI** in your terminal.

---

### Option 2: Build and Run Locally (Without Docker)

If you prefer to run directly from source:

```bash
git clone https://github.com/SparshGHub/SciCalC.git
cd SciCalC/thescicalc
mvn clean package
java -jar target/thescicalc-1.0.jar
```

---

### Option 3: Run using `RUN.sh` Script

A convenience script called **`RUN.sh`** is provided for users who want an easy one-command execution.

#### To run:
```bash
chmod +x RUN.sh
./RUN.sh
```

This script will:
- Check if Docker is installed  
- Pull the latest image if needed  
- Stop any previously running instance  
- Run the Scientific Calculator interactively  

---

## How the CI/CD Workflow Operates

1. **Code Push (GitHub)**  
   Developers push updated code to the repository.  
   A webhook automatically triggers Jenkins.

2. **Build & Test (Maven)**  
   Jenkins compiles the project and runs all test cases.

3. **Package (JAR Creation)**  
   Maven produces a `.jar` file that contains the application.

4. **Dockerization**  
   Jenkins builds a Docker image from the latest code and tags it as `latest`.

5. **Publish & Deploy**  
   The image is pushed to DockerHub and deployed automatically using Ansible.

---

## RUN.sh Script

Below is the content of the **`RUN.sh`** file:

```bash
#!/bin/bash

APP_NAME="scicalc"
IMAGE_NAME="sparshdockerman/scicalc:latest"

echo "Starting Scientific Calculator CLI..."

# Check for Docker
if ! command -v docker &> /dev/null; then
  echo "Docker not found. Installing Docker..."
  sudo apt-get update -y
  sudo apt-get install -y ca-certificates curl gnupg lsb-release
  sudo mkdir -p /etc/apt/keyrings
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
  echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg]   https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
  sudo apt-get update -y
  sudo apt-get install -y docker-ce docker-ce-cli containerd.io
fi

# Pull the latest image
echo "Pulling latest image..."
docker pull $IMAGE_NAME

# Stop and remove any existing container
if [ "$(docker ps -q -f name=$APP_NAME)" ]; then
  echo "Stopping existing container..."
  docker stop $APP_NAME
fi
if [ "$(docker ps -aq -f name=$APP_NAME)" ]; then
  echo "Removing existing container..."
  docker rm $APP_NAME
fi

# Run interactively
docker run -it --name $APP_NAME --rm $IMAGE_NAME
```

---

## Project Structure

```
SciCalC/
│
├── thescicalc/
│   ├── src/                # Java source code
│   ├── target/             # Compiled output (JAR files)
│   ├── Dockerfile          # Docker build instructions
│   └── ansible/            # Deployment scripts
├── pom.xml                 # Maven configuration
├── RUN.sh                  # Helper script to run the calculator
├── Jenkinsfile             # Jenkins Pipeline
└── README.md               # Project documentation
```

---

## Summary

This project demonstrates how a simple CLI application can be transformed into a **production-grade pipeline** through:
- Automated builds and testing (Maven)
- Continuous integration and deployment (Jenkins)
- Containerization (Docker)
- Infrastructure automation (Ansible)

Another Test Trigger
Another Test Trigger
Test Trigger number 4353
