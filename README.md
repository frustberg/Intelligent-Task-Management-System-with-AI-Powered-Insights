# Intelligent Task Management System with AI-Powered Insights

## Overview
A backend microservices platform for managing tasks and projects with a smart AI add-on for predicting task priorities and deadlines. Includes analytics to provide insights on user productivity and task trends.

## Features
- Task and project management with CRUD operations.
- AI-powered prediction for task priority and expected completion.
- Analytics and reporting with asynchronous processing.
- Secure user authentication and role management.
- Scalable microservices architecture with centralized API Gateway.

## Tech Stack
- **Backend:** Spring Boot, Spring Cloud (Eureka, Gateway, Ribbon)
- **Database:** MongoDB
- **AI / ML:** Python (FastAPI, scikit-learn, pandas)
- **Messaging / Async:** RabbitMQ
- **Security:** Spring Security, JWT
- **Build / DevOps:** Gradle, Docker
- **Version Control:** Git

## Project Structure
```
ai-task-management-platform/
├── user-service/
├── task-service/
├── analytics-service/
├── ai-service/
│   └── app/
├── api-gateway/
├── docker-compose.yml
└── README.md
```

## Setup Instructions

### Prerequisites
- Java 17+
- Python 3.10+
- Gradle
- Docker & Docker Compose
- MongoDB
- RabbitMQ
- Git

### Steps
1. **Clone the repository**
```
git clone <repository_url>
cd ai-task-management-platform
```

2. **Build and run backend services using Gradle**
```
cd user-service
./gradlew build
./gradlew bootRun

cd ../task-service
./gradlew build
./gradlew bootRun

cd ../analytics-service
./gradlew build
./gradlew bootRun
```

3. **Run AI service**
```
cd ai-service/app
pip install -r requirements.txt
uvicorn main:app --reload
```

4. **Run API Gateway**
```
cd api-gateway
./gradlew build
./gradlew bootRun
```

5. **Start all services via Docker Compose** (optional)
```
docker-compose up --build
```

## Usage
- Register users via User Service API.
- Create, assign, and manage tasks via Task Service API.
- Predict task priority via AI Service API.
- Retrieve analytics reports via Analytics Service API.

## Contact
For any queries, contact: abhishekmahajan235@gmail.com

