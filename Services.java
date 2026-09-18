package BankingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class FraudDetectionService {
    private static final double SUSPICIOUS_AMOUNT_THRESHOLD = 10000.0;

    public boolean isSuspicious(double amount) {
        // Simple rule: Flag transactions exceeding the threshold
        return amount > SUSPICIOUS_AMOUNT_THRESHOLD;
    }
}

class BankingService {
    private List<Account> accounts = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private FraudDetectionService fraudService = new FraudDetectionService();

    public void createAccount(int id, String name, double initialDeposit) {
        if (getAccount(id) != null) {
            System.out.println("Account ID already exists.");
            return;
        }
        accounts.add(new Account(id, name, initialDeposit));
        System.out.println("Account created successfully.");
        recordTransaction(id, 0, initialDeposit, TransactionType.DEPOSIT, TransactionStatus.SUCCESS);
    }

    public Account getAccount(int id) {
        for (Account acc : accounts) {
            if (acc.getAccountId() == id) return acc;
        }
        return null;
    }

    private String generateTxnId() {
        return "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private void recordTransaction(int source, int dest, double amount, TransactionType type, TransactionStatus status) {
        transactions.add(new Transaction(generateTxnId(), source, dest, amount, type, status));
    }

    public void deposit(int accountId, double amount) {
        Account acc = getAccount(accountId);
        if (acc != null) {
            acc.deposit(amount);
            System.out.println("Deposit successful. New Balance: $" + acc.getBalance());
            recordTransaction(accountId, 0, amount, TransactionType.DEPOSIT, TransactionStatus.SUCCESS);
        } else {
            System.out.println("Account not found.");
        }
    }

    public boolean transfer(int sourceId, int destId, double amount) {
        Account source = getAccount(sourceId);
        Account dest = getAccount(destId);

        if (source == null || dest == null) {
            System.out.println("Invalid source or destination account.");
            return false;
        }

        TransactionStatus status = TransactionStatus.SUCCESS;
        if (fraudService.isSuspicious(amount)) {
            status = TransactionStatus.FLAGGED;
            System.out.println("ALERT: Transaction flagged as suspicious due to high amount.");
        }

        // Object-level lock synchronization ensures safe concurrent transfers
        if (source.withdraw(amount)) {
            dest.deposit(amount);
            recordTransaction(sourceId, destId, amount, TransactionType.TRANSFER, status);
            System.out.println("Transfer completed. Status: " + status);
            return true;
        } else {
            recordTransaction(sourceId, destId, amount, TransactionType.TRANSFER, TransactionStatus.FAILED);
            System.out.println("Transfer failed: Insufficient funds.");
            return false;
        }
    }

    public void generateAccountStatement(int accountId) {
        System.out.println("\n--- STATEMENT FOR ACCOUNT: " + accountId + " ---");
        for (Transaction t : transactions) {
            if (t.toString().contains("Source: " + accountId) || t.toString().contains("Dest: " + accountId)) {
                System.out.println(t);
            }
        }
    }

    public void generateFraudReport() {
        System.out.println("\n========== FRAUD INCIDENT REPORT ==========");
        int count = 0;
        for (Transaction t : transactions) {
            if (t.getStatus() == TransactionStatus.FLAGGED) {
                System.out.println(t);
                count++;
            }
        }
        if (count == 0) System.out.println("No suspicious transactions detected.");
        System.out.println("===========================================");
    }
}

class TransactionThread extends Thread {
    private BankingService service;
    private int sourceId;
    private int destId;
    private double amount;
    private String threadName;

    public TransactionThread(BankingService service, int sourceId, int destId, double amount, String threadName) {
        this.service = service;
        this.sourceId = sourceId;
        this.destId = destId;
        this.amount = amount;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(threadName + " initiating transfer of $" + amount + "...");
        service.transfer(sourceId, destId, amount);
    }
}
