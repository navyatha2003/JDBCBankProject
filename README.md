# 💳 Simple Net-Banking Console App (Java + JDBC + MySQL)

A simple *console-based Net-Banking application* built using *Java* and *JDBC, connected to a **MySQL* database.  
It simulates basic banking operations such as *Login, **Deposit, **Withdraw, **Transfer, and **Check Balance*.

---

## 📘 Features

- 🔐 *Login Authentication*
  - Users log in with account number and password.
  - Credentials validated directly from the database.

- 💰 *Deposit*
  - Add funds to the logged-in user's account.
  - Automatically records the transaction.

- 💸 *Withdraw*
  - Withdraw amount if balance is sufficient.
  - Updates balance and logs the operation.

- 🔁 *Transfer*
  - Send money to another account.
  - Ensures receiver exists and balance is sufficient.
  - Deducts from sender, adds to receiver, and records both accounts.

- 📊 *Check Balance*
  - Displays the user's current balance.

- 🚪 *Logout / Exit*
  - Option to safely exit the application.

---

## 🧩 Tech Stack

| Component     | Technology |
|----------------|-------------|
| Language       | Java (JDK 17 or above) |
| Database       | MySQL |
| Connection     | JDBC |
| IDE (optional) | IntelliJ IDEA / Eclipse / VS Code |
| Driver         | MySQL Connector/J |

---

## 🗄️ Database Schema

### Table: users
| Column       | Type         | Description                     |
|--------------|--------------|----------------------------------|
| id           | INT (PK, AUTO_INCREMENT) | Unique user ID |
| name         | VARCHAR(50)  | Full name |
| acc_number   | BIGINT (UNIQUE) | Account number |
| password     | VARCHAR(20)  | Login password |
| bank_balance | DECIMAL(10,2) | Current balance |

### Table: transactions
| Column           | Type         | Description                          |
|------------------|--------------|--------------------------------------|
| id               | INT (PK, AUTO_INCREMENT) | Transaction ID |
| type             | VARCHAR(20)  | Type (DEPOSIT / WITHDRAW / TRANSFER) |
| from_acc         | BIGINT       | Sender account number |
| to_acc           | BIGINT       | Receiver account number |
| amount           | DECIMAL(10,2) | Transaction amount |
| transaction_time | TIMESTAMP DEFAULT CURRENT_TIMESTAMP | Timestamp of transaction |

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/navyatha2003/JDBCBankProject.git
cd jdbcprojet1

2️⃣ Create Database in MySQL

CREATE DATABASE bank;
USE bank;

CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  acc_number BIGINT UNIQUE,
  password VARCHAR(20),
  bank_balance DECIMAL(10,2)
);

CREATE TABLE transactions (
  id INT PRIMARY KEY AUTO_INCREMENT,
  type VARCHAR(20),
  from_acc BIGINT,
  to_acc BIGINT,
  amount DECIMAL(10,2),
  transaction_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

3️⃣ Insert Sample Data

INSERT INTO users (name, acc_number, password, bank_balance)
VALUES ('Kavya M', 1001, 'kavya123', 5000.00),
       ('Ashoka K A', 1002, 'ashoka123', 3000.00);

4️⃣ Add JDBC Driver

Download MySQL Connector/J.

Add it to your project classpath or IDE dependencies.


5️⃣ Update Database Credentials

Inside BankApp.java, modify:

static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
static final String USER = "root";
static final String PASS = "yourpassword";

6️⃣ Compile and Run

javac BankApp.java
java BankApp


---

🧮 Example Output

Enter Account Number: 1001
Enter Password: kavya123

✅ Login Successful!

------ MAIN MENU ------
1. Deposit
2. Withdraw
3. Transfer
4. Check Balance
5. Exit
Enter your choice: 1
Enter amount to deposit: 1000
✅ Deposit successful!

💰 Current Balance: ₹6000.00


---

🛠️ Future Enhancements

Add admin panel to view all transactions.

Implement password hashing for better security.

Add user registration feature.
Create GUI version using JavaFX or Spring Boot.



---

👩‍💻 Author

Navyatha
🌐 github.com/navyatha2003


---

📄 License

This project is open-source and available under the MIT License.
