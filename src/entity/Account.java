package entity;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private static int AUTO_ID = 10000;
    private int id;
    private Bank bank;
    private double balance;

    public Account(Bank bank, double balance) {
        this.id = AUTO_ID;
        this.bank = bank;
        this.balance = balance;
        AUTO_ID++;
    }

    public int getId() {
        return id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
