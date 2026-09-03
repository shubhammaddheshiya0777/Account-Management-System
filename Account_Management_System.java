package whileloop;

import java.util.*;

public class Account_Management_System {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        AccountManager mgr = new AccountManager();

        mgr.createAccount("Alice", "Checking", 1000);
        mgr.createAccount("Bob", "Savings", 500);

        while (true) {
            printMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": createAccount(mgr); break;
                case "2": listAccounts(mgr); break;
                case "3": deposit(mgr); break;
                case "4": withdraw(mgr); break;
                case "5": transfer(mgr); break;
                case "6": search(mgr); break;
                case "7": closeAccount(mgr); break;
                case "8": System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option.");
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== Account Management System ===");
        System.out.println("1. Create account");
        System.out.println("2. List accounts");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transfer");
        System.out.println("6. Search by holder name");
        System.out.println("7. Close account");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createAccount(AccountManager mgr) {
        System.out.print("Holder name: ");
        String name = sc.nextLine().trim();
        System.out.print("Type (Checking/Savings): ");
        String type = sc.nextLine().trim();
        System.out.print("Initial deposit: ");
        double init = parseDouble(sc.nextLine().trim(), 0);
        Account a = mgr.createAccount(name, type, init);
        System.out.println("Created: " + a);
    }

    private static void listAccounts(AccountManager mgr) {
        System.out.println("Accounts:");
        for (Account a : mgr.listAccounts()) System.out.println("  " + a);
    }

    private static void deposit(AccountManager mgr) {
        try {
            System.out.print("Account ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            boolean ok = mgr.deposit(id, amt);
            System.out.println(ok ? "Deposit successful." : "Deposit failed (check ID/amount/status).");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void withdraw(AccountManager mgr) {
        try {
            System.out.print("Account ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            boolean ok = mgr.withdraw(id, amt);
            System.out.println(ok ? "Withdrawal successful." : "Withdrawal failed (insufficient funds or invalid).");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void transfer(AccountManager mgr) {
        try {
            System.out.print("From account ID: ");
            int from = Integer.parseInt(sc.nextLine().trim());
            System.out.print("To account ID: ");
            int to = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Amount: ");
            double amt = Double.parseDouble(sc.nextLine().trim());
            boolean ok = mgr.transfer(from, to, amt);
            System.out.println(ok ? "Transfer successful." : "Transfer failed (check IDs/funds/status).");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static void search(AccountManager mgr) {
        System.out.print("Search query: ");
        String q = sc.nextLine().trim();
        List<Account> res = mgr.searchByHolder(q);
        if (res.isEmpty()) System.out.println("No accounts found.");
        else for (Account a : res) System.out.println("  " + a);
    }

    private static void closeAccount(AccountManager mgr) {
        try {
            System.out.print("Account ID to close: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean ok = mgr.closeAccount(id);
            System.out.println(ok ? "Account closed." : "Account not found.");
        } catch (NumberFormatException ex) { System.out.println("Invalid number."); }
    }

    private static double parseDouble(String s, double def) {
        if (s.isEmpty()) return def;
        try { return Double.parseDouble(s); } catch (NumberFormatException ex) { return def; }
    }
}
