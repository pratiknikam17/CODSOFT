import java.util.Scanner;

// Class representing the user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize the balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrew: $" + amount);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
        }
    }

    // Method to check the balance
    public double checkBalance() {
        return balance;
    }
}

// ATM class to interact with the user
class ATM {
    private BankAccount account;
    
    // Constructor to initialize with a user's bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display the menu and process user inputs
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n==== ATM Menu ====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: $" + account.checkBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        } while (choice != 4);
        sc.close();
    }
}

// Main class
public class ATMInterface {
    public static void main(String[] args) {
        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(500.00); // Assume initial balance is $500

        // Create an ATM object linked to the user's bank account
        ATM atm = new ATM(account);

        // Display the ATM menu
        atm.displayMenu();
    }
}
