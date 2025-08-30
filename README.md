⚡ Energy Trading Console Application (Java + SQL Server)

## 📌 Project Overview
This is a **console-based Java application** that connects to a **SQL Server database** to manage **Energy Trade records**.  
The app demonstrates how to perform **CRUD operations** (Create, Read, Update, Delete) using **JDBC**.

---

## ⚙️ Features Implemented
- **Add a Trade** → Insert a new trade record (Buy/Sell).
- **View All Trades** → Display all trades.
- **Update Trade** → Update price and volume of a trade.
- **Delete Trade** → Delete trade using Trade ID.
- **Search Trades** → Search by Counterparty or Commodity.
- **Exit** → Close the application.

---

## 🛠️ Tech Stack
- **Language**: Java (JDK 11+)
- **Database**: Microsoft SQL Server
- **JDBC Driver**: Microsoft JDBC Driver for SQL Server
- **Build/Run**: Command line (`javac` / `java`) or IDE (Eclipse / IntelliJ)

---

## 🚀 How to Run

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/<your-username>/EnergyTradingApp.git
cd EnergyTradingApp
```

### 2️⃣ Configure Database Connection
Inside '''EnergyTradingApp.java''', update the following:

👉 **For SQL Authentication**
```

static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=EnergyTradingDB;encrypt=true;trustServerCertificate=true;";
static final String USER = "sa";
static final String PASS = "your_password";
```
👉 **For Windows Authentication**

```
static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=EnergyTradingDB;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
static final String USER = "";
static final String PASS = "";
```
⚠️ If using **Windows Authentication**, copy
```sqljdbc_auth.dll``` from:

```sqljdbc_12.6\enu\auth\x64\```
to:
```C:\Windows\System32\```
### 3️⃣ Compile

```javac -cp "C:\path\to\mssql-jdbc-12.6.4.jre11.jar" EnergyTradingApp.java```
### 4️⃣ Run

```java -cp ".;C:\path\to\mssql-jdbc-12.6.4.jre11.jar" EnergyTradingApp```
📸 Sample Output
```
===== Energy Trading Menu =====
1. Add a Trade
2. View All Trades
3. Update Trade
4. Delete Trade
5. Search Trades by Counterparty
6. Search Trades by Commodity
7. Exit
Enter your choice:
```
### ✅ Deliverables
```EnergyTradingApp.java``` → Java Source Code

```EnergyTradingDB.sql``` → SQL script to create database and tables

```README.md``` → Project documentation

```Screenshots``` → Console outputs of test runs

### 📂 Folder Structure
```
EnergyTradingApp/
│── src/
│   └── EnergyTradingApp.java
│── sql/
│   └── EnergyTradingDB.sql
│── README.md
```
### ✨ Author
Shreyas Deshpande
