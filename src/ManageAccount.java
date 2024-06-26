import java.util.ArrayList;
import java.util.Scanner;

public class ManageAccount {

    private String accountType;
    private final ArrayList<SavingsAccount> listSavingAccounts;
    private final ArrayList<CurrentAccount> listCurrentAccounts;

    ManageAccount() {
        listSavingAccounts = new ArrayList<>();
        listCurrentAccounts = new ArrayList<>();
    }

    public void printListCurrentAccounts() {
        System.out.println("Current Accounts");
        for(CurrentAccount acc : listCurrentAccounts) {
            System.out.println("tên tài khoản: " +
                    acc.getName() +
                    " || ID Bank: " +
                    acc.getIdBank() +
                    " || số dư hiện tại: " +
                    acc.getBalanceAccount() +
                    " || Hạn mức rút" +
                    acc.getOverDraftLimit());
        }
    }

    public void printListSavingsAccounts() {
        System.out.println("Savings Accounts");
        for(SavingsAccount acc : listSavingAccounts) {
            System.out.println("tên tài khoản: " +
                    acc.getName() +
                    " || ID Bank: " +
                    acc.getIdBank() +
                    " || số dư hiện tại: " +
                    acc.getBalanceAccount() +
                    " || % lãi suất" +
                    acc.getInterestRate());
        }
    }

    private int generateIDAccount(String typeAccount) {
        int iDBank;
        if(typeAccount == "Current") {
            if(this.listCurrentAccounts.isEmpty()) {
                return 1;
            }
            int size = this.listCurrentAccounts.size();
            return this.listCurrentAccounts.get(size - 1).getIdBank() + 1;
        }
        if(typeAccount == "Savings") {
            if(this.listSavingAccounts.isEmpty()) {
                return 1;
            }
            int size = this.listSavingAccounts.size();
            return this.listSavingAccounts.get(size - 1).getIdBank() + 1;
        }
        return -1;
    }

    public void addCurrentAccout(Scanner sc) {
        String name;
        int iDbank;
        double initialBalance;
        double hanMucRut;
        name = InputHelper.inputString("Nhập tên chủ tài khoản", sc);
        while (true) {
            initialBalance = InputHelper.inputDouble("Số tiền gửi hiện tại", sc);
            if(initialBalance < 0) {
                System.out.println("Số tiền nhập chưa hợp lệ");
                continue;
            }
            break;
        }
        while (true) {
            hanMucRut = InputHelper.inputDouble("Hạn mức rút", sc);
            if(hanMucRut < 0) {
                System.out.println("Số hạn mức nhập chưa hợp lệ");
                continue;
            }
            break;
        }
        iDbank = generateIDAccount("Current");

        CurrentAccount currentAccount = new CurrentAccount(name, iDbank,initialBalance, hanMucRut);

        this.listCurrentAccounts.add(currentAccount);
        this.printListCurrentAccounts();

    }

    public void addSavingsAccout(Scanner sc) {
        String name;
        int iDbank;
        double initialBalance;
        float interestRate;
        name = InputHelper.inputString("Nhập tên chủ tài khoản", sc);
        while (true) {
            initialBalance = InputHelper.inputDouble("Số tiền gửi tiết kiệm", sc);
            if(initialBalance < 0) {
                System.out.println("Số tiền nhập chưa hợp lệ");
                continue;
            }
            break;
        }
        while (true) {
            interestRate = InputHelper.inputFloat("Tỷ lệ lãi suất", sc);
            if(interestRate < 0) {
                System.out.println("Tỷ lệ lãi suất chưa hợp lệ");
                continue;
            }
            break;
        }
        iDbank = generateIDAccount("Savings");

        SavingsAccount savingsAccount = new SavingsAccount(name, iDbank,initialBalance, interestRate);

        this.listSavingAccounts.add(savingsAccount);
        this.printListSavingsAccounts();

    }

}
