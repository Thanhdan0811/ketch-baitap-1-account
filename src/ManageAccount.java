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

        System.out.println("Current Account");
        System.out.println("Initial Deposit: $" + currentAccount.getBalanceAccount());
        System.out.println("Overdraft Limit: $" + currentAccount.getOverDraftLimit());

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

        System.out.println("Savings Account:");
        System.out.println("Initial Deposit: $" + savingsAccount.getBalanceAccount());
        System.out.println("Interest rate: " + savingsAccount.getInterestRate() + "%");

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

    private void napTienVaoAccount(CurrentAccount curAcc, SavingsAccount svAcc,Scanner sc) {
        double amount;
        while (true) {
            amount = InputHelper.inputDouble("Số tiền nạp vào (0 để thoát): ", sc);
            if(amount == 0) return;
            if(amount < 0) {
                System.out.println("Số tiền nhập chưa hợp lệ.");
                continue;
            }
            break;
        }
        if(curAcc != null) {
            curAcc.depositToAccount(amount);
            System.out.println("Now deposit $" + amount + " to Current Account.");
            return;
        }
        // trường hợp của savings Acc
        svAcc.depositToAccount(amount);
        System.out.println("Now deposit $" + amount + " to Savings Account.");
        return;
    }

    private void rutTienTuAccount(CurrentAccount curAcc, SavingsAccount svAcc,Scanner sc) {
        double amount;
        while (true) {
            amount = InputHelper.inputDouble("Số tiền cần rút (0 để thoát): ", sc);
            if(amount == 0) return;
            boolean isValidateAmount = false;
            if(curAcc != null) {
                isValidateAmount = validateTienRutCurrentAccount(amount, curAcc);
            }
            if(svAcc != null) {
                isValidateAmount = validateTienRutSavingsAccount(amount, svAcc);
            }
            if(!isValidateAmount) continue;
            break;
        }
        if(curAcc != null) {
            curAcc.withdrawFromAccount(amount);
            System.out.println("Withdraw $" + amount + " from Savings Account.");
            return;
        }
        // trường hợp của savings Acc
        svAcc.withdrawFromAccount(amount);
        System.out.println("Withdraw $" + amount + " from Savings Account.");
        return;
    }

    private boolean validateTienRutCurrentAccount(double amount, CurrentAccount curAcc) {
        if(amount < 0) {
            System.out.println("Số tiền nhập chưa hợp lệ.");
            return false;
        }
        if(amount > curAcc.getOverDraftLimit()) {
            System.out.println("Số tiền rút vượt hạn mức (" + curAcc.getOverDraftLimit() + ").");
            return false;
        }
        if(amount > curAcc.getBalanceAccount()) {
            System.out.println("Tài khoản bạn không đủ tiền.");
            return false;
        }
        return true;
    }

    private boolean validateTienRutSavingsAccount(double amount, SavingsAccount svAcc) {
        if(amount < 0) {
            System.out.println("Số tiền nhập chưa hợp lệ.");
            return false;
        }
        if(amount > svAcc.getBalanceAccount()) {
            System.out.println("Tài khoản bạn không đủ tiền.");
            return false;
        }
        return true;
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
            printLuaChonDetail();
            luaChon = sc.nextInt();
            if(luaChon == 0) return;
            switch (luaChon) {
                case 1:
                    napTienVaoAccount(acc, null, sc);
                    break;
                case 2:
                    rutTienTuAccount(acc, null, sc);
                    break;
                case 3:
                    System.out.println("Current Account:");
                    System.out.println("Account Balance: " + acc.getBalanceAccount());
                    break;
                case 4:
                    System.out.println("After applying interest on Current A/C for 1 year");
                    System.out.println("Current A/C:");
                    System.out.println("Account Balance: " + String.format("%.2f", acc.calculateInterest()));
                    break;
                default:
                    System.out.println("Lựa chọn chưa phù hợp");
            }

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
            printLuaChonDetail();
            luaChon = sc.nextInt();
            if(luaChon == 0) return;
            switch (luaChon) {
                case 1:
                    napTienVaoAccount(null, acc, sc);
                    break;
                case 2:
                    rutTienTuAccount(null, acc, sc);
                    break;
                case 3:
                    System.out.println("Savings Account:");
                    System.out.println("Account Balance: " + acc.getBalanceAccount());
                    break;
                case 4:
                    System.out.println("After applying interest on Savings A/C for 1 year");
                    System.out.println("Savings A/C:");
                    System.out.println("Account Balance: " + String.format("%.2f", acc.calculateInterest()));
                    break;
                default:
                    System.out.println("Lựa chọn chưa phù hợp");
            }

        }

    }

    private void printLuaChonDetail() {
        System.out.println();
        System.out.println("Bạn cần gì ?");
        System.out.println("1.Nạp tiền vào tk.");
        System.out.println("2.Rút tiền từ tk.");
        System.out.println("3.Xem số dư tk.");
        System.out.println("4.Xem số dư sau lãi suất.");
        System.out.println("0.Để thoát.");

    }

}
