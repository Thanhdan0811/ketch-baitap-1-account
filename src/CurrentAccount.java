public class CurrentAccount extends AccoutAbstract implements Account{
    private double overDraftLimit;
    static final String accoutType = "Current";

    CurrentAccount(String name, int idBank, double initalDeposit, double overDraftLimit) {
        this.name = name;
        this.idBank = idBank;
        this.initalDeposit = initalDeposit;
        this.overDraftLimit = overDraftLimit;
        this.accountBalance = this.initalDeposit;
    }

    @Override
    public void depositToAccount(double amount) {
        double newBalance = amount + this.getBalanceAccount();
        this.setAccountBalance(newBalance);
    }

    @Override
    public void withdrawFromAccount(double amount) {

        double newBalance = this.getBalanceAccount() - amount;
        this.setAccountBalance(newBalance);
    }

    @Override
    public double calculateInterest() {
        return this.getBalanceAccount();
    }

    @Override
    public double getBalanceAccount() {
        return this.accountBalance;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }
}
