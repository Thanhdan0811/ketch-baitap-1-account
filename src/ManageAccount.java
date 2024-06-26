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
                    " || Hạn mức rút: " +
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
                    " || % lãi suất: " +
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
        name = InputHelper.inputString("Nhập tên chủ tài khoản: ", sc);
        while (true) {
            initialBalance = InputHelper.inputDouble("Số tiền gửi hiện tại: ", sc);
            if(initialBalance < 0) {
                System.out.println("Số tiền nhập chưa hợp lệ");
                continue;
            }
            break;
        }
        while (true) {
            hanMucRut = InputHelper.inputDouble("Hạn mức rút: ", sc);
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
        name = InputHelper.inputString("Nhập tên chủ tài khoản: ", sc);
        while (true) {
            initialBalance = InputHelper.inputDouble("Số tiền gửi tiết kiệm: ", sc);
            if(initialBalance < 0) {
                System.out.println("Số tiền nhập chưa hợp lệ");
                continue;
            }
            break;
        }
        while (true) {
            interestRate = InputHelper.inputFloat("Tỷ lệ lãi suất: ", sc);
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

    private CurrentAccount getAccountFromListCurrent(int iDBank) {
        for(CurrentAccount acc: this.listCurrentAccounts) {
            if(acc.getIdBank() == iDBank) return acc;
        }
        return null;
    }
    private SavingsAccount getAccountFromListSavings(int iDBank) {
        for(SavingsAccount acc: this.listSavingAccounts) {
            if(acc.getIdBank() == iDBank) return acc;
        }
        return null;
    }

    public void printDetailCurrentAccount(Scanner sc) {
        if(this.listCurrentAccounts.isEmpty()) {
            System.out.println("Hiện chưa có account nào.");
            return;
        }
        int iDBank;
        CurrentAccount acc;
        while (true) {
            iDBank = InputHelper.inputInt("Nhập mã tài khoản hiện tại (0 để thoát): ", sc);
            if(iDBank == 0) return;
            acc = getAccountFromListCurrent(iDBank);
            if(acc == null) continue;
            break;
        }

        acc.printCurrentAccount();

        int luaChon;
        while (true) {
            printLuaChonDetail("Current");
            luaChon = sc.nextInt();
            if(luaChon == 0) return;
            switch (luaChon) {
                case 1:
                    napTienVaoAccount(acc, null, sc);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Lựa chọn chưa phù hợp");
            }

        }
    }

    private void napTienVaoAccount(CurrentAccount curAcc, SavingsAccount svAcc,Scanner sc) {
        double amount;
        while (true) {
            amount = InputHelper.inputDouble("Số tiền nạp vào: ", sc);
            if(amount < 0) {
                System.out.println("Số tiền nhập chưa hợp lệ.");
                continue;
            }
            break;
        }
        if(curAcc != null) {
            curAcc.setAccountBalance(curAcc.getBalanceAccount() + amount);
            return;
        }
        if(svAcc != null) {
            svAcc.setAccountBalance(svAcc.getBalanceAccount() + amount);
            return;
        }
    }

    public void printDetailSavingAccount(Scanner sc) {
        if(this.listSavingAccounts.isEmpty()) {
            System.out.println("Hiện chưa có account nào.");
            return;
        }
        int iDBank;
        SavingsAccount acc;
        while (true) {
            iDBank = InputHelper.inputInt("Nhập mã tài khoản hiện tại (0 để thoát): ", sc);
            if(iDBank == 0) return;
            acc = getAccountFromListSavings(iDBank);
            if(acc == null) continue;
            break;
        }

        acc.printSavingsAccount();

        int luaChon;
        while (true) {
            printLuaChonDetail("Savings");
            luaChon = sc.nextInt();
            if(luaChon == 0) return;
            switch (luaChon) {
                case 1:
                    napTienVaoAccount(null, acc, sc);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Lựa chọn chưa phù hợp");
            }

        }

    }

    private void printLuaChonDetail(String typeAccount) {
        System.out.println();
        System.out.println("Bạn cần gì ?");
        System.out.println("1.Nạp tiền vào tk.");
        System.out.println("2.Rút tiền từ tk.");
        System.out.println("3.Xem số dư tk.");
        if(typeAccount == "Savings") {
            System.out.println("4.Xem số dư sau lãi suất.");
        }
        System.out.println("0.Để thoát.");

    }

}
