package logichandle;

import entity.Account;
import entity.Bank;
import entity.Customer;
import entity.SavingAccount;
import utilities.FileUtil;
import utilities.ScannerUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavingAccountLogic {
    private CustomerLogic customerLogic;
    private BankLogic bankLogic;
    private List<SavingAccount> savingAccounts;
    public static final String SAVING_ACCOUNT_DATA_FILE = "saving-accounts.dat";

    public SavingAccountLogic(CustomerLogic customerLogic, BankLogic bankLogic, List<SavingAccount> savingAccounts) {
        this.customerLogic = customerLogic;
        this.bankLogic = bankLogic;
        this.savingAccounts = new ArrayList<>(savingAccounts);
    }

    public List<SavingAccount> getSavingAccounts() {
        return this.savingAccounts;
    }

    public void inputSavingAccountList() {
        System.out.print("Nhập số lượng sổ tiết kiệm: ");
        int numberOfSavingAccount = ScannerUtility.inputValidInteger();
        for (int i = 0; i < numberOfSavingAccount; i++) {
            System.out.println("Thông tin sổ tiết kiệm thứ " + (i + 1));
            Customer customer = inputCustomerInfo();
            SavingAccount savingAccount = null;
            Map<Integer, Integer> limitAccountForEachBank = null;
            List<Account> accounts;

            if (checkExistCustomer(customer)) {
                savingAccount = getSavingAccountInList(customer);
                if (savingAccount != null) {
                    limitAccountForEachBank = savingAccount.getLimitAccountForEachBank();
                }
            } else {
                limitAccountForEachBank = new HashMap<>();
            }

            accounts = inputAccountListInfo(limitAccountForEachBank);

            if (checkExistCustomer(customer)) {
                try {
                    if (savingAccount != null) {
                        savingAccount.getAccounts().addAll(accounts);
                        savingAccount.setLimitAccountForEachBank(limitAccountForEachBank);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                savingAccount = new SavingAccount(customer, accounts, limitAccountForEachBank);
                storeSavingAccount(savingAccount);
            }
        }
    }

    public void displaySavingAccountList() {
        if (this.savingAccounts.isEmpty()) {
            System.out.println("-------------------------");
            System.out.println("Danh sách số tiết kiệm rỗng");
            System.out.println("-------------------------");
            return;
        }

        System.out.println("Thông tin sổ tiết kiệm của các khách hàng");

        for (SavingAccount savingAccount : this.savingAccounts) {
            savingAccount.displayInfo();
            System.out.println("-----------------------------------------------------");
        }
    }

    public void displaySavingAccountList(List<SavingAccount> savingAccounts) {
        for (SavingAccount savingAccount : savingAccounts) {
            savingAccount.displayInfo();
            System.out.println("-----------------------------------------------------");
        }
    }

    private boolean checkExistCustomer(Customer customer) {
        for (SavingAccount savingAccount : this.savingAccounts) {
            if (savingAccount.getCustomer().getId() == customer.getId()) {
                return true;
            }
        }

        return false;
    }

    private SavingAccount getSavingAccountInList(Customer customer) {
        for (SavingAccount savingAccount : this.savingAccounts) {
            if (savingAccount.getCustomer().getId() == customer.getId()) {
                return savingAccount;
            }
        }

        return null;
    }

    private void storeSavingAccount(SavingAccount savingAccount) {
        this.savingAccounts.add(savingAccount);
        FileUtil.getInstance().writeDataToFile(this.savingAccounts, SAVING_ACCOUNT_DATA_FILE);
    }

    private List<Account> inputAccountListInfo(Map<Integer, Integer> limitAccountForEachBank) {
        System.out.println("Nhập số lượng tài khoản:");
        int numberOfAccount = ScannerUtility.inputValidInteger();
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < numberOfAccount; i++) {
            System.out.println("Tài khoản thứ " + (i + 1));
            Bank bank;
            int bankId;
            do {
                System.out.print("Nhập mã ngân hàng: ");
                bankId = ScannerUtility.inputValidInteger();
                bank = this.bankLogic.searchBankById(bankId);
                if (bank != null) {
                    break;
                }
                System.out.println("Không tồn tại ngân hàng mang mã " + bankId + ", vui lòng nhập lại.");
            } while (true);

            if (limitAccountForEachBank.get(bank.getId()) == null) {
                limitAccountForEachBank.put(bank.getId(), 1);
            } else {
                int numberOfExistAccount = limitAccountForEachBank.get(bank.getId());
                if (numberOfExistAccount > 4) {
                    System.out.println("Ngân hàng " + bank.getBankName() + " đã mở 5 tài khoản.");
                    break;
                } else {
                    limitAccountForEachBank.put(bank.getId(), ++numberOfExistAccount);
                }
            }

            System.out.print("Nhập số tiền cần gửi: ");
            double cash = ScannerUtility.inputValidDouble();

            Account account = new Account(bank, cash);
            accounts.add(account);
        }

        return accounts;
    }

    private Customer inputCustomerInfo() {
        System.out.println("Nhập mã khách hàng: ");
        Customer customer;
        int customerId;
        do {
            customerId = ScannerUtility.inputValidInteger();
            customer = this.customerLogic.searchCustomerById(customerId);
            if (customer != null) {
                break;
            }
            System.out.println("Không tồn tại khách hàng mang mã " + customerId + ", vui lòng nhập lại.");
        } while (true);
        return customer;
    }

    public void displayTotalBalanceForAllWorkers() {
        System.out.printf("%-16s | %-24s | %-16s |\n", "Mã khách hàng", "Tên khách hàng", "Tổng số tiền gửi");
        for (SavingAccount savingAccount : this.savingAccounts) {
            savingAccount.displayTotalBalance();
        }
    }
}
