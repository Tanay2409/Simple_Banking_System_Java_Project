import java.util.*;

public class AccountDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Account account[] = new Account[10];
        int numAccounts = 0;

        int choice;

        do {
            choice = menu(sc);
            System.out.println();

            if (choice == 1) {
                account[numAccounts++] = createAccount(sc);
            } else if (choice == 2) {
                doDeposit(account, numAccounts, sc);
            } else if (choice == 3) {
                doWithdraw(account, numAccounts, sc);
            } else if (choice == 4) {
                applyInterest(account, numAccounts, sc);
            } else {
                System.out.println("GoodBye!");
            }
            System.out.println();

        } while (choice != 5);

    }

    public static int accountMenu(Scanner sc) {
        System.out.println("Select Account Type");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");

        int choice;

        do {
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
        } while (choice < 1 || choice > 2);
        return choice;
    }

    public static int searchAccount(Account account[], int count, int accountNumber) {
        for (int i = 0; i < count; i++) {
            if (account[i].getAccountNumber() == accountNumber) {
                return i;
            }
        }
        return -1;
    }

    public static void doDeposit(Account account[], int count, Scanner sc) {
        System.out.print("\nEnter the Account Number: ");
        int accountNumber = sc.nextInt();

        int index = searchAccount(account, count, accountNumber);

        if (index >= 0) {
            System.out.print("Please enter deposit ammount: ");
            double amount = sc.nextDouble();

            account[index].deposit(amount);
        } else {
            System.out.println("No account exists with account number: " + accountNumber);
        }

    }

    public static void doWithdraw(Account account[], int count, Scanner sc) {
        System.out.print("\nEnter the Account Number: ");
        int accountNumber = sc.nextInt();

        int index = searchAccount(account, count, accountNumber);

        if (index >= 0) {
            System.out.print("Please enter withdraw ammount: ");
            double amount = sc.nextDouble();

            account[index].withdraw(amount);
        } else {
            System.out.println("No account exists with account number: " + accountNumber);
        }
    }

    public static void applyInterest(Account account[], int count, Scanner sc) {
        System.out.print("\nEnter the Account Number: ");
        int accountNumber = sc.nextInt();

        int index = searchAccount(account, count, accountNumber);

        if (index >= 0) {
            if (account[index] instanceof SavingsAccount) {
                ((SavingsAccount) account[index]).applyInterest();
            }
        } else {
            System.out.println("No account exists with account number: " + accountNumber);
        }
    }

    public static Account createAccount(Scanner sc) {

        Account account = null;
        int choice = accountMenu(sc);

        int accountNumber;
        System.out.print("Enter the Account Number: ");
        accountNumber = sc.nextInt();

        System.out.print("Enter the Account Number: ");

        if (choice == 1) {
            System.out.print("Enter the Transaction Fee: ");
            double fee = sc.nextInt();

            account = new CheckingAccount(accountNumber, fee);
        } else {
            System.out.print("Please enter interest rate: ");
            double ir = sc.nextInt();
            account = new SavingsAccount(accountNumber, ir);
        }
        return account;
    }

    public static int menu(Scanner sc) {
        System.out.println("Bank account menu");
        System.out.println("1. Create new account");
        System.out.println("2. Deposit Funds");
        System.out.println("3. Withdraw Funds");
        System.out.println("4. Apply Interest");
        System.out.println("5. Quit");

        int choice;

        do {
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
        } while (choice < 1 || choice > 5);
        return choice;
    }
}
