package entity;

import logichandle.CustomerLogic;
import statics.CustomerType;
import utilities.ScannerUtility;

public class Customer extends Person {
    private static int AUTO_ID;
    private int id;
    private CustomerType customerType;

    public Customer() {
        if (CustomerLogic.getInstance().getCustomers().size() > 0) {
            AUTO_ID = CustomerLogic.getInstance().getLastCustomerId() + 1;
        } else {
            AUTO_ID = 10000;
        }
        this.id = AUTO_ID;
        AUTO_ID++;
    }

    public int getId() {
        return id;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public void inputInfo() {
        super.inputInfo();
        System.out.println("Chọn 1 trong 3 loại khách hàng.");
        System.out.println("1. Cá nhân");
        System.out.println("2. Tập thể");
        System.out.println("3. Doanh nghiệp");
        int type;

        do {

            type = ScannerUtility.inputValidNumberInRange(1, 3);
            switch (type) {
                case 1 -> this.setCustomerType(CustomerType.PERSONAL);
                case 2 -> this.setCustomerType(CustomerType.GROUP);
                case 3 -> this.setCustomerType(CustomerType.CORPORATION);
                default -> System.out.println("Loại khách hàng đã chọn không hợp lệ. Vui lòng nhập lại.");
            }
        } while (type < 1 || type > 3);


    }

    @Override
    public void displayInfo() {
        System.out.printf("%-16s | %-24s | %-40s | %-16s | %-20s |\n", this.id, this.name, this.address, this.phoneNumber, this.customerType.type);
    }
}
