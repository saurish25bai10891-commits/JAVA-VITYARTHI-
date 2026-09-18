# Project Problem Statement
**Project Title:** Banking & Fraud Transaction Simulator
**Submitted By:** Saurish Modgil
**Registration Number:** 25BAI10891
**Branch:** Computer Science {Artificial Intelligence & Machine Learning}

## 1. Background
In the digital era, banking systems handle thousands of transactions per second. Ensuring the integrity of these transactions is paramount. Two of the most critical challenges in modern financial software architecture are maintaining data consistency during concurrent operations and detecting malicious or anomalous activities in real-time.

## 2. Problem Description
Traditional, unsynchronized transaction systems are vulnerable to critical race conditions. For example, if multiple withdrawal requests are processed simultaneously against the same account, the system might approve both before updating the balance, leading to the "double-spending" problem and negative balances. 

Furthermore, processing transactions without automated oversight leaves the financial institution exposed to fraudulent activities, such as unusually large, unauthorized fund transfers that go undetected until manual audits are performed.

## 3. Proposed Solution
The "Banking & Fraud Transaction Simulator" is designed to address these issues by implementing a secure, multi-threaded backend simulation. 

The proposed system will:
1.  **Resolve Concurrency Issues:** Utilize Java's `synchronized` monitor locks on account objects to ensure that transactions (deposits and withdrawals) are mutually exclusive and processed atomically.
2.  **Automate Fraud Detection:** Introduce an integrated rules-engine (Fraud Detection Service) that actively monitors all incoming transactions and automatically flags suspicious activities (e.g., transfers exceeding $10,000) for administrative review without crashing or halting the legitimate banking queues.

## 4. Key Objectives
*   Build a robust Object-Oriented foundation using standard POJOs and Enum state tracking.
*   Simulate high-concurrency environments using programmatic multithreading to prove lock efficacy.
*   Maintain a detailed, immutable transaction ledger for accurate auditing and statement generation.