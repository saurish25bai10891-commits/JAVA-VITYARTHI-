package BankingSystem;

public class Account {
    private int accountId;
    private String accountHolder;
    private double balance;

    public Account(int accountId, String accountHolder, double initialDeposit) {
        this.accountId = accountId;
        this.accountHolder = accountHolder;
        this.balance = initialDeposit;
    }

    public int getAccountId() { return accountId; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }

    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public synchronized boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            // Simulated processing delay to expose race conditions if unsynchronized
            try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + " | Holder: " + accountHolder + " | Balance: $" + balance;
    }
}