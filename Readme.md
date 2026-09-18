# Banking & Fraud Transaction Simulator

A robust, object-oriented console application developed in Java to model core banking operations, concurrent transaction handling, and a rules-based fraud detection system. 

**Developed By:** Saurish Modgil (Reg: 25BAI10891)

## 📌 Features

- **Account Management:** Create user accounts with initial deposits and unique IDs.
- **Financial Transactions:** Execute deposits and fund transfers between accounts securely.
- **Ledger & Statements:** Maintain an immutable ledger of all transactions (UUID, timestamp, status). View account-specific statements.
- **Fraud Detection:** Automatically flags transactions exceeding defined thresholds (e.g., $10,000) as suspicious without halting the banking process.
- **Concurrency & Thread Safety:** Utilizes Java `synchronized` monitor locks on account objects to prevent race conditions and "double-spending" during concurrent transfers.
- **Multi-threaded Demo:** Includes an interactive demo spawning parallel threads to attempt simultaneous withdrawals, proving the efficacy of the thread locks.

## 🛠️ Technology Stack

- **Language:** Java (JDK 8+)
- **Core Concepts:** Object-Oriented Programming (OOP), Multithreading, Concurrency, Enums, Java Collections Framework (ArrayList).

## 📂 File Structure

The project is structured under the `BankingSystem` package:
- `BankingSystem.java`: The presentation layer containing the CLI loop and main menu.
- `Account.java`: POJO representing user accounts containing synchronized mutation methods.
- `Transaction.java`: POJO representing the immutable record of a financial movement.
- `Services.java`: Contains the business logic (`BankingService`) and rules engine (`FraudDetectionService`), as well as the custom `TransactionThread` class for concurrent simulation.

## 🚀 How to Run

1. Ensure you have the Java Development Kit (JDK) installed on your system.
2. Clone or download the repository files.
3. Open a terminal and navigate to the root directory containing the `BankingSystem` folder.
4. Compile the java files:
   ```bash
   javac BankingSystem/*.java
   ```
5. Run the main application:
   ```bash
   java BankingSystem.BankingSystem
   ```
6. Follow the on-screen menu instructions to create accounts, perform transfers, and run the Concurrent Transaction Demo (Option 7).

## 🔒 Concurrency Note
To simulate real-world processing latency, a `Thread.sleep(100)` is included in the `Account.withdraw()` method. Because the method is marked as `synchronized`, you will observe that even if two threads attempt to drain an account simultaneously, the system correctly processes them sequentially, failing the second thread if funds are insufficient.