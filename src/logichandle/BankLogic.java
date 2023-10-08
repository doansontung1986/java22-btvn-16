package logichandle;

import entity.Bank;
import utilities.FileUtil;
import utilities.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class BankLogic {
    private static BankLogic bankLogic;
    private List<Bank> bankList;
    public static final String BANK_DATA_FILE = "bank.dat";

    private BankLogic() {

    }

    private BankLogic(List<Bank> bankList) {
        super();
        this.bankList = new ArrayList<>(bankList);
    }

    public static BankLogic getInstance() {
        if (bankLogic == null) {
            bankLogic = new BankLogic();
        }
        return bankLogic;
    }

    public static BankLogic getInstance(List<Bank> bankList) {
        if (bankLogic == null) {
            bankLogic = new BankLogic(bankList);
        }
        return bankLogic;
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = new ArrayList<>(bankList);
    }

    public void inputBankList() {
        System.out.print("Nhập số ngân hàng mới: ");
        int numberOfBank = ScannerUtility.inputValidInteger();
        for (int i = 0; i < numberOfBank; i++) {
            System.out.println("Thông tin ngân hàng thứ " + (i + 1));
            Bank bank = new Bank();

            if (checkExistBank(bank)) {
                continue;
            }

            bank.inputInfo();
            saveBank(bank);
        }
    }

    public void displayBankList() {
        if (this.bankList.isEmpty()) {
            System.out.println("-------------------------");
            System.out.println("Danh sách ngân hàng rỗng");
            System.out.println("-------------------------");
            return;
        }

        System.out.printf("%-12s | %-36s | %-12s |\n", "Mã ngân hàng", "Tên ngân hàng", "Lãi suất");
        for (Bank bank : this.bankList) {
            bank.displayInfo();
        }
    }

    private void saveBank(Bank bank) {
        this.bankList.add(bank);
        FileUtil.getInstance().writeDataToFile(this.bankList, BANK_DATA_FILE);
    }

    private boolean checkExistBank(Bank newBank) {
        if (this.bankList.isEmpty()) {
            return false;
        }

        for (Bank bank : this.bankList) {
            if (bank.getId() == newBank.getId()) {
                return true;
            }
        }

        return false;
    }

    public Bank searchBankById(int bankId) {
        for (Bank bank : this.bankList) {
            if (bank.getId() == bankId) {
                return bank;
            }
        }
        return null;
    }

    public int getLastBankId() {
        return this.bankList.get(this.bankList.size() - 1).getId();
    }
}
