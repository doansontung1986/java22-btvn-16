package entity;

import utilities.ScannerUtility;
import utilities.ValidateUserInput;

import java.io.Serializable;

public class Bank implements Inputable, Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private static int AUTO_ID = 100;
    private int id;
    private String bankName;
    private double interestRate;

    public Bank() {
        this.id = AUTO_ID;
        AUTO_ID++;
    }

    public int getId() {
        return id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void displayInfo() {
        System.out.printf("%-12s | %-36s | %-12s |\n", this.id, this.bankName, this.interestRate);
    }

    @Override
    public void inputInfo() {
        System.out.println("Nhập tên ngân hàng: ");
        this.setBankName(inputValidBankName());

        System.out.println("Nhập lãi suất: ");
        this.setInterestRate(inputValidInterestRate());
    }

    private String inputValidBankName() {
        String bankName;
        do {
            bankName = ScannerUtility.inputStringInRange(6, 35);

            if (ValidateUserInput.checkValidName(bankName)) {
                break;
            }

            System.out.print("Tên ngân hàng phải có độ dài 6 - 35 ký tự. Nhập lại tên ngân hàng: ");
        } while (true);

        return bankName;
    }

    private double inputValidInterestRate() {
        return ScannerUtility.inputValidDouble();
    }
}
