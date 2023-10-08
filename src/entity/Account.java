package entity;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private Bank bank;
    private double balance;

    public Account(Bank bank, double balance) {
        this.bank = bank;
        this.balance = balance;
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
