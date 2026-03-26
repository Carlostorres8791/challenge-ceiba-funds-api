# challenge-ceiba-funds-api
Backend technical challenge - Investment funds management API

# 💼 Backend Technical Challenge - Investment Funds API

This project is a backend API developed as part of a technical challenge. It allows users to manage investment funds, including subscriptions, cancellations, and transaction history.

---

## 🚀 Technologies Used

* Java 17
* Spring Boot
* Spring Data MongoDB
* Maven
* JUnit (Unit Testing)

---

## 📌 Features

* Subscribe to an investment fund
* Cancel fund subscription
* View transaction history
* Validate minimum fund amount
* Balance management (initial balance: COP $500,000)
* Simulated notifications (Email/SMS)

---

## 🧠 Business Rules

* Each user starts with a balance of **COP $500,000**

* Each fund has a **minimum investment amount**

* If the user does not have enough balance:

  ```
  No tiene saldo disponible para vincularse al fondo <Nombre del fondo>
  ```

* When a subscription is canceled, the amount is returned to the user

* Each transaction has a unique identifier

---

## 📦 API Endpoints

### 📌 Get all funds

```
GET /funds
```

### 📌 Subscribe to a fund

```
POST /funds/{fundId}/subscribe?userId={userId}
```

### 📌 Cancel subscription

```
POST /funds/{fundId}/cancel?userId={userId}
```

### 📌 Get transaction history

```
GET /transactions?userId={userId}
```

---

## 🧪 Running the Project

1. Clone the repository:

```
git clone https://github.com/TU-USUARIO/TU-REPO.git
```

2. Navigate to the project:

```
cd TU-REPO
```

3. Run the application:

```
mvn spring-boot:run
```

---

## ⚙️ Design Decisions

* MongoDB was chosen as a NoSQL database for flexibility and scalability
* Clean architecture with separation of concerns (Controller, Service, Repository)
* DTOs used to avoid exposing internal models
* Exception handling implemented for business rules

---

## 🔐 Security

Basic validation is implemented.
Authentication and authorization can be extended using JWT or Spring Security.

---

## ☁️ Deployment (AWS)

The application can be deployed using AWS CloudFormation.
Due to time constraints, full infrastructure setup is described but not fully implemented.

---

## 👨‍💻 Author

Carlos Torres
Backend Developer
