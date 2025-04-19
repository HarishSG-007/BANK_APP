package entity;

public class User {
    private String username;
    private String password;
    private String contactNumber;
    private String role;
    private Double accountBalance;

    public User(String username, String password, String contactNumber, String role, Double accountBalance) {
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
        this.role = role;
        this.accountBalance = accountBalance;
    }

    public User(String username, String password) {
        this(username, password, "", "user", 0.0);
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getContactNumber() { return contactNumber; }
    public String getRole() { return role; }
    public Double getAccountBalance() { return accountBalance; }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", role='" + role + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
