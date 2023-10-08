import logichandle.MenuManagement;

public class BankManagementSystem {

    public static void main(String[] args) {
        MenuManagement menuManagement = new MenuManagement();
        menuManagement.initializeData();
        menuManagement.run();
    }
}
