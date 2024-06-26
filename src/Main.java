import java.util.Scanner;

public class Main {
    // sau java 9 có thêm default method và static method.
    // Serialization
    public static void main(String[] args) {
        ManageAccount manageAccount = new ManageAccount();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printLuaChon();
            int luaChon = sc.nextInt();
            if(luaChon == 0) break;
            switch (luaChon) {
                case 1:
                    manageAccount.printListCurrentAccounts();
                    break;
                case 2:
                    manageAccount.printListSavingsAccounts();
                    break;
                case 3:
                    manageAccount.printDetailCurrentAccount(sc);
                    break;
                case 4:
                    manageAccount.printDetailSavingAccount(sc);
                    break;
                case 5:
                    manageAccount.addCurrentAccout(sc);
                    break;
                case 6:
                    manageAccount.addSavingsAccout(sc);
                    break;
                default:
                    System.out.println("Lựa chọn của bạn chưa phù hơp");
            }

        }
        sc.close();
    }

    static void printLuaChon() {
        System.out.println();
        System.out.println("Hệ thống ngân hàng");
        System.out.println("1. Xem danh sách tài khoản hiện tại.");
        System.out.println("2. Xem danh sách tài khoản tiết kiệm.");
        System.out.println("3. Xem chi tiết tài khoản hiện tại");
        System.out.println("4. Xem chi tiết tài khoản tiết kiệm");
        System.out.println("5. Thêm tài khoản hiện tại");
        System.out.println("6. Thêm tài khoản tiết kiệm");
        System.out.println("0. Để thoát");
    }
}