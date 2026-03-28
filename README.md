# 💼 Backend Technical Challenge - Investment Funds API

This project is a backend API developed as part of a technical challenge. It allows users to manage investment funds, including subscriptions, cancellations, and transaction history.

---

# 🧠 Business Context

BTG Pactual requires a platform where users can manage their investment funds without contacting an advisor.

---

# 🚀 Technologies Used

* Java 17
* Spring Boot
* Spring Data MongoDB
* Maven
* JUnit & Mockito (Unit Testing)
* Docker

---

# 📌 Features

* Subscribe to an investment fund
* Cancel fund subscription
* View transaction history
* Validate minimum fund amount
* Balance management (initial balance: COP $500,000)
* Simulated notifications (Email/SMS)
* JWT Authentication

---

# 🧠 Business Rules

* Each user starts with a balance of **COP $500,000**
* Each fund has a **minimum investment amount**
* If the user does not have enough balance:

```
No tiene saldo disponible para vincularse al fondo <Nombre del fondo>
```

* When a subscription is canceled, the amount is returned to the user
* Each transaction has a unique identifier

---

# 🔐 Authentication (JWT)

The API is secured using JWT.

## 📌 Flow:

1. User logs in
2. Receives a token
3. Uses the token in requests:

```
Authorization: Bearer <token>
```

---

# 📦 API Endpoints

## 🔑 Authentication

```
POST /api/users/login
```

---

## 👤 Users

```
POST /api/users
```

---

## 📌 Funds

```
GET /api/funds
POST /api/funds
```

---

## 📌 Subscriptions

### Subscribe

```
POST /api/funds/subscribe
```

Body:

```
{
  "userId": "user1",
  "fundId": "1",
  "amount": 100000
}
```

---

### Cancel Subscription

```
POST /api/funds/cancel/{subscriptionId}
```

---

### Get User Subscriptions

```
GET /api/funds/subscriptions/{userId}
```

---

## 📊 Transactions

```
GET /api/transactions?userId={userId}
```

---

# 🐳 Running with Docker (Recommended)

This project uses Docker to run MongoDB locally, avoiding external dependencies.

## 📋 Prerequisites

* Docker Desktop installed and running

---

## 🚀 Steps

1. Clone the repository:

```
git clone https://github.com/Carlostorres8791/challenge-ceiba-funds-api.git
cd challenge-ceiba-funds-api
```

---

2. Start MongoDB:

```
docker compose up -d
```

---

3. Run the backend:

```
mvn spring-boot:run
```

---

4. API available at:

```
http://localhost:9090
```

---

## 🧪 Verify MongoDB

```
docker ps
```

You should see:

```
mongo-funds
```

---

# 📦 Default Data

Initial funds are automatically loaded using a DataLoader when the application starts.

---

# 📮 API Testing with Postman

A Postman collection is included to facilitate testing of all endpoints.

---

## 📦 Import Collection

1. Open Postman
2. Click **Import**
3. Select the collection JSON file

---

## ⚙️ Environment Variables

| Variable       | Description           |
| -------------- | --------------------- |
| baseUrl        | http://localhost:9090 |
| token          | JWT token             |
| userId         | User ID               |
| fundId         | Fund ID               |
| subscriptionId | Subscription ID       |

---

## 🔑 Testing Flow

1. Create User → `POST /api/users`
2. Login → `POST /api/users/login`
3. Copy token
4. Get Funds → `GET /api/funds`
5. Subscribe → `POST /api/funds/subscribe`
6. Cancel → `POST /api/funds/cancel/{subscriptionId}`
7. View subscriptions → `GET /api/funds/subscriptions/{userId}`

---

# 🧪 Unit Testing

Unit tests are implemented using:

* JUnit
* Mockito

They cover:

* Successful subscription
* Insufficient balance
* Minimum amount validation
* Entity not found scenarios

---

# ⚙️ Design Decisions

* MongoDB chosen for flexibility (NoSQL)
* Clean architecture: Controller → Service → Repository
* DTOs used to decouple layers
* Exception handling for business rules
* Docker used to simplify environment setup

---

# ☁️ Deployment (AWS)

The application can be deployed using AWS services such as:

* EC2
* ECS (Docker containers)

Due to time constraints, full CloudFormation templates are not included, but the application is ready for container-based deployment.

---


# 👨‍💻 Author

Carlos Torres
Backend Developer
