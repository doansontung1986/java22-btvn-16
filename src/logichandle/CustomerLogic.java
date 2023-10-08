package logichandle;

import entity.Customer;
import utilities.FileUtil;
import utilities.ScannerUtility;

import java.util.ArrayList;
import java.util.List;

public class CustomerLogic {
    private List<Customer> customers;
    public static final String CUSTOMER_DATA_FILE = "customer.dat";

    public CustomerLogic(List<Customer> customers) {
        this.customers = new ArrayList<>(customers);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = new ArrayList<>(customers);
    }

    public void inputCustomerList() {
        System.out.print("Nhập số khách hàng mới: ");
        int numberOfCustomer = ScannerUtility.inputValidInteger();
        for (int i = 0; i < numberOfCustomer; i++) {
            System.out.println("Thông tin khách hàng thứ " + (i + 1));
            Customer customer = new Customer();

            if (checkExistCustomer(customer)) {
                continue;
            }

            customer.inputInfo();
            saveCustomer(customer);
        }
    }

    public void displayCustomerList() {
        if (this.customers.isEmpty()) {
            System.out.println("-------------------------");
            System.out.println("Danh sách khách hàng rỗng");
            System.out.println("-------------------------");
            return;
        }

        System.out.printf("%-16s | %-24s | %-40s | %-16s | %-20s |\n", "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Loại khách hàng");
        for (Customer customer : this.customers) {
            customer.displayInfo();
        }
    }

    private void saveCustomer(Customer customer) {
        this.customers.add(customer);
        FileUtil.getInstance().writeDataToFile(this.customers, CUSTOMER_DATA_FILE);
    }

    private boolean checkExistCustomer(Customer newCustomer) {
        if (this.customers.isEmpty()) {
            return false;
        }

        for (Customer customer : this.customers) {
            if (customer.getId() == newCustomer.getId()) {
                return true;
            }
        }

        return false;
    }

    public Customer searchCustomerById(int customerId) {
        for (Customer customer : this.customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }
}
