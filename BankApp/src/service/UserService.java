package service;

import entity.User;
import repositry.UserRepositry;

public class UserService {
    private static UserRepositry userRepository = new UserRepositry();

    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    public boolean addNewCustomer(String username, String password, String contact) {
        return userRepository.addNewCustomer(username, password, contact);
    }

    public Double checkBankBalance(String userId) {
        return userRepository.checkBankBalance(userId);
    }

    public User getUser(String userId) {
        return userRepository.getUser(userId);
    }

    public boolean transferAmount(String userId, String payeeUserId, Double amount) {
        return userRepository.transferAmount(userId, payeeUserId, amount);
    }
}
