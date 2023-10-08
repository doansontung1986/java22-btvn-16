package logichandle;

import entity.Bank;
import entity.Customer;
import entity.Person;
import entity.SavingAccount;
import utilities.DataUtil;
import utilities.FileUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuManagement {
    private CustomerLogic customerLogic;
    private BankLogic bankLogic;
    private SavingAccountLogic savingAccountLogic;

    public void initializeData() {
        Object customerDataFromFile = FileUtil.getInstance().readDataFromFile(CustomerLogic.CUSTOMER_DATA_FILE);
        List<Customer> customers = DataUtil.isNullOrEmpty(customerDataFromFile) ? new ArrayList<>() : (List<Customer>) customerDataFromFile;
        this.customerLogic = CustomerLogic.getInstance(customers);

        Object bankDataFromFile = FileUtil.getInstance().readDataFromFile(BankLogic.BANK_DATA_FILE);
        List<Bank> bankList = DataUtil.isNullOrEmpty(bankDataFromFile) ? new ArrayList<>() : (List<Bank>) bankDataFromFile;
        this.bankLogic = BankLogic.getInstance(bankList);

        Object savingAccountDataFromFile = FileUtil.getInstance().readDataFromFile(SavingAccountLogic.SAVING_ACCOUNT_DATA_FILE);
        List<SavingAccount> savingAccounts = DataUtil.isNullOrEmpty(savingAccountDataFromFile) ? new ArrayList<>() : (List<SavingAccount>) savingAccountDataFromFile;
        savingAccountLogic = new SavingAccountLogic(this.customerLogic, this.bankLogic, savingAccounts);
    }

    public void run() {
        do {
            printMenu();
            int functionChoice = chooseFunction();
            switch (functionChoice) {
                case 1 -> customerLogic.inputCustomerList();
                case 2 -> customerLogic.displayCustomerList();
                case 3 -> bankLogic.inputBankList();
                case 4 -> bankLogic.displayBankList();
                case 5 -> savingAccountLogic.inputSavingAccountList();
                case 6 -> savingAccountLogic.displaySavingAccountList();
                case 7 -> {
                    List<SavingAccount> savingAccounts = new ArrayList<>(savingAccountLogic.getSavingAccounts());
                    savingAccounts.sort((o1, o2) -> o1.getCustomer().getName().compareToIgnoreCase(o2.getCustomer().getName()));
                    savingAccountLogic.displaySavingAccountList(savingAccounts);
                }
                case 8 -> {
                    List<SavingAccount> savingAccounts2 = new ArrayList<>(savingAccountLogic.getSavingAccounts());
                    savingAccounts2.sort((o1, o2) -> {
                        int i = -1;
                        int index = 0;

                        while (index < savingAccounts2.size()) {
                            i = (int) (o1.getAccounts().get(index).getBalance() - o2.getAccounts().get(index).getBalance());
                            index++;
                        }

                        return i;
                    });
                    savingAccountLogic.displaySavingAccountList(savingAccounts2);
                }
                case 9 -> savingAccountLogic.displayTotalBalanceForAllWorkers();
                case 10 -> System.exit(0);
            }
        } while (true);
    }

    private int chooseFunction() {
        System.out.println("Xin mời lựa chọn chức năng: ");
        int functionChoice;
        do {
            functionChoice = new Scanner(System.in).nextInt();
        } while (functionChoice <= 0 || functionChoice >= 11);
        return functionChoice;
    }

    private void printMenu() {
        System.out.println("------PHẦN MỀM QUẢN LÝ NGÂN HÀNG");
        System.out.println("1. Nhập khách hàng mới");
        System.out.println("2. In danh sách khách hàng");
        System.out.println("3. Nhập ngân hàng mới");
        System.out.println("4. In danh sách ngân hàng");
        System.out.println("5. Lập danh sách số tiết kiệm");
        System.out.println("6. In danh sách số tiết kiệm");
        System.out.println("7. Sắp xếp danh sách số tiết kiệm theo họ tên khách hàng");
        System.out.println("8. Sắp xếp danh sách số tiết kiệm theo số tiền gửi");
        System.out.println("9. Lập bảng kê tổng số tiền gửi cho mỗi khách hàng");
        System.out.println("10. Thoát");
    }
}
