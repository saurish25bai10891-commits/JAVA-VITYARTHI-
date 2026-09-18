package BankingSystem;

import java.util.Date;

enum TransactionType { DEPOSIT, WITHDRAWAL, TRANSFER }
enum TransactionStatus { SUCCESS, FAILED, FLAGGED }

public class Transaction {
    private String transactionId;
    private int sourceAccountId;
    private int destinationAccountId;
    private double amount;
    private TransactionType type;
    private TransactionStatus status;
    private Date timestamp;

    public Transaction(String transactionId, int sourceAccountId, int destinationAccountId, double amount, TransactionType type, TransactionStatus status) {
        this.transactionId = transactionId;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.timestamp = new Date();
    }

    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "TXN: " + transactionId + " | [" + type + "] | Amount: $" + amount + 
               " | Source: " + (sourceAccountId == 0 ? "N/A" : sourceAccountId) + 
               " | Dest: " + (destinationAccountId == 0 ? "N/A" : destinationAccountId) + 
               " | Status: " + status + " | Time: " + timestamp;
    }
}
