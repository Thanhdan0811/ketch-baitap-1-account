public class SavingsAccount extends AccoutAbstract implements Account{
    private float interestRate;
    static final String accoutType = "Savings";

    SavingsAccount(String name,int idBank,double initalDeposit, float interestRate) {
        this.name = name;
        this.idBank = idBank;
        this.initalDeposit = initalDeposit;
        this.interestRate = interestRate;
        this.accountBalance = this.initalDeposit;
    }

    public float getInterestRate() {
        return this.interestRate;
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
        return (this.interestRate / 100) * this.accountBalance + this.accountBalance;
    }

    @Override
    public double getBalanceAccount() {
        return this.accountBalance;
    }

}
