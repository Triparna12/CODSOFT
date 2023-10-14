import java.util.Scanner;

public class ATM_INTERFACE {
    private BankAccount account;
    private String userId;
    private String password;

    public ATM_INTERFACE(BankAccount account, String userId, String password) {
        this.account = account;
        this.userId = userId;
        this.password = password;
    }

    public void displayMenu() {
        System.out.println();
        System.out.println(" ====================");
        System.out.println("  Welcome to the ATM ");
        System.out.println(" ====================");
        System.out.println();
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Exit");
        System.out.println();
    }

    public void authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your User ID: ");
        String inputUserId = scanner.nextLine();
        System.out.print("Enter your Password: ");
        String inputPassword = scanner.nextLine();

        if (inputUserId.equals(userId) && inputPassword.equals(password)) {
            System.out.println("Authentication successful. You can now access your account.");
            showMenu();
        } else {
            System.out.println("Wrong user name and password. Access denied.");
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 4) {
                break;
            }
            if(choice == 1){
                double now_balance = account.getBalance();
                System.out.println("Your Balance is : ₹ " + now_balance);
            }

            else if (choice == 2 || choice == 3) {
                System.out.println("Enter the amount: ₹ ");
                double amount = scanner.nextDouble();
                performOperation(choice, amount);
            }
            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void performOperation(int choice, double amount) {
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                withdraw(amount);
                break;
            case 3:
                deposit(amount);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void checkBalance() {
        double balance = account.getBalance();
        System.out.println("Your account balance is: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount > 0 && account.getBalance() >= amount) {
            account.withdraw(amount);
            System.out.println("Withdrawal successful. Your new balance is: $" + account.getBalance());
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            account.deposit(amount);
            System.out.println("Deposit successful. Your new balance is: $" + account.getBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public static class BankAccount {
        private double balance;

        public BankAccount(double initialBalance) {
            this.balance = initialBalance;
        }

        public double getBalance() {
            return balance;
        }

        public void withdraw(double amount) {
            if (amount > 0 && balance >= amount) {
                balance -= amount;
            }
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00);
        ATM_INTERFACE atm = new ATM_INTERFACE(account, "TRIPARNA", "TRIPARNA@2002");
        atm.authenticateUser();
    }
}
