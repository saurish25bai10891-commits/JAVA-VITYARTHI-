package BankingSystem;

import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankingService bankingService = new BankingService();

        // Seed Data
        bankingService.createAccount(101, "Alice", 5000);
        bankingService.createAccount(102, "Bob", 3000);

        while (true) {
            System.out.println("\n==========================================");
            System.out.println("   BANKING & FRAUD TRANSACTION SIMULATOR  ");
            System.out.println("==========================================");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Balance Inquiry");
            System.out.println("4. Transfer Money");
            System.out.println("5. Account Statement");
            System.out.println("6. View Fraud Report");
            System.out.println("7. Concurrent Transaction Demo");
            System.out.println("0. Exit");
            System.out.println("==========================================");
            System.out.print("Enter Choice: ");
            
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    double deposit = sc.nextDouble();
                    bankingService.createAccount(id, name, deposit);
                    break;
                case 2:
                    System.out.print("Enter Account ID: ");
                    int depId = sc.nextInt();
                    System.out.print("Enter Amount to Deposit: ");
                    double depAmt = sc.nextDouble();
                    bankingService.deposit(depId, depAmt);
                    break;
                case 3:
                    System.out.print("Enter Account ID: ");
                    int balId = sc.nextInt();
                    Account acc = bankingService.getAccount(balId);
                    if (acc != null) System.out.println(acc);
                    else System.out.println("Account not found.");
                    break;
                case 4:
                    System.out.print("Enter Source Account ID: ");
                    int srcId = sc.nextInt();
                    System.out.print("Enter Destination Account ID: ");
                    int destId = sc.nextInt();
                    System.out.print("Enter Amount to Transfer: ");
                    double transAmt = sc.nextDouble();
                    bankingService.transfer(srcId, destId, transAmt);
                    break;
                case 5:
                    System.out.print("Enter Account ID: ");
                    int stmtId = sc.nextInt();
                    bankingService.generateAccountStatement(stmtId);
                    break;
                case 6:
                    bankingService.generateFraudReport();
                    break;
                case 7:
                    System.out.println("\n===== CONCURRENT TRANSACTION DEMO =====");
                    System.out.println("Creating test account (ID: 999) with $1000 balance...");
                    bankingService.createAccount(999, "Shared Business", 1000);
                    bankingService.createAccount(888, "Vendor A", 0);
                    bankingService.createAccount(777, "Vendor B", 0);
                    
                    System.out.println("Thread 1 and Thread 2 will attempt to transfer $800 simultaneously.");
                    TransactionThread t1 = new TransactionThread(bankingService, 999, 888, 800, "Thread-1");
                    TransactionThread t2 = new TransactionThread(bankingService, 999, 777, 800, "Thread-2");
                    
                    t1.start();
                    t2.start();
                    
                    try {
                        t1.join();
                        t2.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    
                    System.out.println("\nDemo Completed. Final state of Source Account:");
                    System.out.println(bankingService.getAccount(999));
                    break;
                case 0:
                    System.out.println("Exiting Simulator...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}