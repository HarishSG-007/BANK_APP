package repositry;

import entity.User;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepositry {
    private static Set<User> users = new HashSet<>();

    static {
        users.add(new User("Harish", "Hari@2004", "8610522775", "admin", 200000.0));
        users.add(new User("aakash", "aak@2008", "8620522775", "admin", 1000.0));
        users.add(new User("gayathri", "gaya@1980", "9610522775", "admin", 2000.0));
        users.add(new User("Sundararajan", "sundar@1968", "96103422775", "admin", 2000.0));
        users.add(new User("akash", "aka@2004", "9619522575", "user", 2000000.0));
        users.add(new User("jaikrishnan", "jai@2004", "9310522775", "user", 200030.0));
        users.add(new User("jithu", "jithu@2004", "9610582755", "user", 2003354.0));
        users.add(new User("bala", "bala@2004", "9610322775", "user", 23243535.0));
    }

    public boolean transferAmount(String userId, String payeeUserId, Double amount) {
        boolean isDebit = debit(userId, amount);
        boolean isCredit = credit(payeeUserId, amount);
        return isDebit && isCredit;
    }

    private boolean debit(String userId, Double amount) {
        User user = getUser(userId);
        if (user == null) return false;

        Double accountBalance = user.getAccountBalance();
        users.remove(user);
        user.setAccountBalance(accountBalance - amount);
        return users.add(user);
    }

    private boolean credit(String userId, Double amount) {
        User user = getUser(userId);
        if (user == null) return false;

        Double accountBalance = user.getAccountBalance();
        users.remove(user);
        user.setAccountBalance(accountBalance + amount);
        return users.add(user);
    }

    public User getUser(String userId) {
        return users.stream()
                .filter(user -> user.getUsername().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public Double checkBankBalance(String userId) {
        User user = getUser(userId);
        return user != null ? user.getAccountBalance() : null;
    }

    public void printUsers() {
        users.forEach(System.out::println);
    }

    public User login(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean addNewCustomer(String username, String password, String contact) {
        User user = new User(username, password, contact, "user", 500.00);
        return users.add(user);
    }
}
