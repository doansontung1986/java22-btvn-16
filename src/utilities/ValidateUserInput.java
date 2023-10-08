package utilities;

public class ValidateUserInput {
    public static boolean checkValidName(String name) {
        return name.matches("^[a-zA-Z0-9!@#$%^&*\\s]{6,35}$");
    }

    public static boolean checkValidAddress(String address) {
        return address.matches("^[a-zA-Z0-9!@#$%^&*,.\\s]{6,35}$");
    }

    public static boolean checkValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("(^0[9|8|1|7|3|5](\\d){8}$)|(^0[9|8|1|7|3|5](\\d){10}$)");
    }
}
