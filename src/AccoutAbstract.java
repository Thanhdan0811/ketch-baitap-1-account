abstract class AccoutAbstract {
    String name;
    int idBank;
    double initalDeposit;
    double accountBalance;

    double getInitalDeposit() {
        return this.initalDeposit;
    }

    void setAccountBalance(double newBalance) {
        if(newBalance < 0) {
            System.out.println("số tiền gửi sai");
            return;
        }
        this.accountBalance = newBalance;
    }

    int getIdBank() {
        return this.idBank;
    }

    String getName() {
        return this.name;
    }


}
