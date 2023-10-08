package entity;

import utilities.ScannerUtility;
import utilities.ValidateUserInput;

import java.io.Serializable;

public abstract class Person implements Inputable, Displayable, Serializable {
    private static final long serialVersionUID = -6500665823330706018L;
    protected String name;
    protected String address;
    protected String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public abstract void displayInfo();

    @Override
    public void inputInfo() {
        System.out.println("Nhập họ tên: ");
        this.setName(inputValidName());

        System.out.println("Nhập địa chỉ: ");
        this.setAddress(inputValidAddress());

        System.out.println("Nhập số điện thoại: ");
        this.setPhoneNumber(inputValidPhoneNumber());
    }

    private String inputValidName() {
        String name;
        do {
            name = ScannerUtility.inputStringInRange(6, 35);

            if (ValidateUserInput.checkValidName(name)) {
                break;
            }

            System.out.print("Tên nhân viên phải có độ dài 6 - 35 ký tự. Nhập lại tên nhân viên: ");
        } while (true);

        return name;
    }

    private String inputValidAddress() {
        String address;
        do {
            address = ScannerUtility.inputStringInRange(6, 35);

            if (ValidateUserInput.checkValidAddress(address)) {
                break;
            }

            System.out.print("Địa chỉ phải có độ dài 6 - 35 ký tự. Nhập lại địa chỉ: ");
        } while (true);

        return address;
    }

    private String inputValidPhoneNumber() {
        long phoneNumber;
        String phoneNumberStr;
        do {
            phoneNumber = ScannerUtility.inputValidLongNumber();
            phoneNumberStr = "0" + phoneNumber;

            if (ValidateUserInput.checkValidPhoneNumber(phoneNumberStr)) {
                break;
            }

            System.out.println("Số điện thoại phải có độ dài 10 hoặc 12 ký tự.");
            System.out.println("Bắt đầu bằng số 0 và có các số là 9, 8, 1, 7, 3, 5 phía sau. Ví dụ: 0912345678");
            System.out.println("Vui lòng nhập lại số điện thoại: ");
        } while (true);

        return phoneNumberStr;
    }
}
