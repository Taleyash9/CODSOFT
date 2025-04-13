import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        int balance = 0, withdraw, deposit, transferAmount;
        Scanner scanner = new Scanner(System.in);
        List<String> transactionHistory = new ArrayList<>();

        while (true) {
            System.out.println("ATM");
            System.out.println("Choose 1 for Withdraw");
            System.out.println("Choose 2 for Deposit");
            System.out.println("Choose 3 for Check Balance");
            System.out.println("Choose 4 for Transaction History");
            System.out.println("Choose 5 for Transfer");
            System.out.println("Choose 6 for Quit");
            System.out.print("Choose any operation you want to perform:");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter money to be withdrawn from a/c:");
                    withdraw = scanner.nextInt();
                    if (balance >= withdraw) {
                        balance -= withdraw;
                        transactionHistory.add("Withdrawal: " + withdraw);
                        System.out.println("Please collect your money");
                    } else {
                        System.out.println("Insufficient Balance");
                    }
                    System.out.println("");
                    break;

                case 2:
                    System.out.print("Enter money to be deposited:");
                    deposit = scanner.nextInt();
                    balance += deposit;
                    transactionHistory.add("Deposit: " + deposit);
                    System.out.println("Your money has been successfully deposited");
                    System.out.println("");
                    break;

                case 3:
                    System.out.println("Balance: " + balance);
                    System.out.println("");
                    break;

                case 4:
                    displayTransactionHistory(transactionHistory);
                    break;

                case 5:
                    System.out.print("Enter amount to transfer:");
                    transferAmount = scanner.nextInt();
                    if (balance >= transferAmount) {
                        System.out.print("Enter recipient's account number:");
                        int recipientAccountNumber = scanner.nextInt();
                        balance -= transferAmount;
                        transactionHistory.add("Transfer to account " + recipientAccountNumber + ": " + transferAmount);
                        System.out.println("Transfer successful!");
                    } else {
                        System.out.println("Insufficient Balance for transfer");
                    }
                    System.out.println("");
                    break;

                case 6:
                    System.out.println("Exiting the ATM. Goodbye!");
                    System.exit(0);
            }
        }
    }

    private static void displayTransactionHistory(List<String> transactionHistory) {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("");
    }
}
