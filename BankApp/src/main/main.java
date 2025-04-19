package main;

import entity.User;
import service.UserService;
import java.util.Scanner;

public class main {
    private static Scanner sc = new Scanner(System.in);
    static main Main = new main();
    static UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter your name:");
            String s = sc.next();

            System.out.println("Enter your password:");
            String p = sc.next();

            User user = userService.login(s, p);

            if (user != null) {
                System.out.println("You are logged in successfully");
            } else {
                System.out.println("Login failed");
                continue;
            }

            if (user.getRole().equals("admin")) {
                initAdmin();
            } else if (user.getRole().equals("user")) {
                initCustomer(user);
            } else {
                System.out.println("Login failed");
            }
        }
    }

    private static void initAdmin() {
        boolean flag = true;
        while (flag) {
            System.out.println("You are an admin");
            System.out.println("1. Exit/Logout");
            System.out.println("2. Create a customer account");

            int selectedOption = sc.nextInt();

            switch (selectedOption) {
                case 1:
                    flag = false;
                    System.out.println("You have successfully logged out...");
                    break;
                case 2:
                    Main.addNewCustomer();
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private void addNewCustomer() {
        System.out.println("Enter username:");
        String username = sc.next();
        System.out.println("Enter password:");
        String password = sc.next();
        System.out.println("Enter contact number:");
        String contact = sc.next();

        boolean result = userService.addNewCustomer(username, password, contact);

        if (result) {
            System.out.println("Customer account is created");
        } else {
            System.out.println("Customer account creation failed...");
        }
    }

    private static void initCustomer(User user) {
        System.out.println("You are a customer");
        boolean flag = true;
        while (flag) {
            System.out.println("1. Exit/Logout");
            System.out.println("2. Check bank balance");
            System.out.println("3. Fund transfer");

            int selectedOption = sc.nextInt();

            switch (selectedOption) {
                case 1:
                    flag = false;
                    System.out.println("You have successfully logged out...");
                    break;
                case 2:
                    Double balance = checkBankBalance(user.getUsername());
                    if (balance != null) {
                        System.out.println("Your bank balance is " + balance);
                    } else {
                        System.out.println("Check your username");
                    }
                    break;
                case 3:
                    fundTransfer(user);
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private static void fundTransfer(User userDetails) {
        System.out.println("Enter payee account user id:");
        String payeeAccountId = sc.next();

        User payeeUser = getUser(payeeAccountId);
        if (payeeUser != null) {
            System.out.println("Enter amount to transfer:");
            Double amount = sc.nextDouble();

            Double userAccountBalance = checkBankBalance(userDetails.getUsername());

            if (userAccountBalance >= amount) {
                boolean result = userService.transferAmount(userDetails.getUsername(), payeeAccountId, amount);
                if (result) {
                    System.out.println("Amount transferred successfully...");
                } else {
                    System.out.println("Transfer failed. Try again.");
                }
            } else {
                System.out.println("Your balance is insufficient: " + userAccountBalance);
            }
        } else {
            System.out.println("Please enter a valid username");
        }
    }

    private static User getUser(String userId) {
        return userService.getUser(userId);
    }

    private static Double checkBankBalance(String userId) {
        return userService.checkBankBalance(userId);
    }
}
