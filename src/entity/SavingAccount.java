package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavingAccount implements Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    private Customer customer;
    private List<Account> accounts;
    private Map<Integer, Integer> limitAccountForEachBank;

    public SavingAccount(Customer customer, List<Account> accounts, Map<Integer, Integer> limitAccountForEachBank) {
        this.customer = customer;
        this.accounts = new ArrayList<>(accounts);
        this.limitAccountForEachBank = new HashMap<>(limitAccountForEachBank);
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Map<Integer, Integer> getLimitAccountForEachBank() {
        return limitAccountForEachBank;
    }

    public void setLimitAccountForEachBank(Map<Integer, Integer> limitAccountForEachBank) {
        this.limitAccountForEachBank = new HashMap<>(limitAccountForEachBank);
    }

    @Override
    public void displayInfo() {
        System.out.println("Thông tin sổ tiết kiệm của khách hàng " + customer.getName());
        System.out.printf("%-16s | %-20s |\n", customer.getName(), customer.getCustomerType().type);
        System.out.printf("%-20s | %-16s |\n", "Tên ngân hàng", "Tiền gửi");
        for (Account account : this.accounts) {
            System.out.printf("%-20s | %-16s |\n", account.getBank().getBankName(), account.getBalance());
        }
    }

    public double calculateTotalBalance() {
        double totalBalance = 0;
        for (Account account : this.accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    public void displayTotalBalance() {
        System.out.printf("%-16s | %-24s | %-16s |\n", customer.getId(), customer.getName(), calculateTotalBalance());
    }
}
