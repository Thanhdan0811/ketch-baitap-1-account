public interface Account {

    // phương thức gửi tiền
    void depositToAccount(double amount);
    // phương thức rút tiền
    void withdrawFromAccount(double amount);
    // tính lãi getInterestRate
    double calculateInterest();
    // get số dư
    double getBalanceAccount();

}
