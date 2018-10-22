package Model.po;

public class User {
    String phoneNumber;
    double balance;

    public User(int id, String phoneNumber, double balance) {
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
}
