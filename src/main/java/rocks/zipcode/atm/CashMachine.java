package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author ZipCodeWilmington
 */
public class CashMachine {

    private final Bank bank;
    private AccountData accountData = null;
    private NumberFormat formatter = new DecimalFormat("#0.00");

    public CashMachine(Bank bank) {
        this.bank = bank;
    }

    private Consumer<AccountData> update = data -> {
        accountData = data;
    };

    public void login(int id) {
        tryCall(
                () -> bank.getAccountById(id),
                update
        );
    }

    public void deposit(double amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.deposit(accountData, amount),
                    update
            );
        }
    }

    public void withdraw(double amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update
            );
        }
    }

    public double getAccountBalance() {
        return bank.getBalance(accountData);
    }

    public void exit() {
        if (accountData != null) {
            accountData = null;
        }
    }

    @Override
    public String toString() {
        return accountData != null ? accountData.toString() : "Enter Account number and click Login.\n" +
                "Accounts:\n1000\n2000\n3000\n4000\n";
    }

    public String accountIdToString() {
        return accountData != null ? accountData.getId() + "" : "ID Required";
    }

    public String nameToString() {
        return accountData != null ? accountData.getName() : "ID Required";
    }

    public String emailToString() {
        return accountData != null ? accountData.getEmail() : "ID Required";
    }

    public String balanceToString() {
        return accountData != null ? precisionFormatter(accountData.getBalance()) : "ID Required";
    }

    public String precisionFormatter(Double amount) {
        return formatter.format(amount);
    }

    private <T> void tryCall(Supplier<ActionResult<T> > action, Consumer<T> postAction) {
        try {
            ActionResult<T> result = action.get();
            if (result.isSuccess()) {
                T data = result.getData();
                postAction.accept(data);
            } else {
                String errorMessage = result.getErrorMessage();
                throw new RuntimeException(errorMessage);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
