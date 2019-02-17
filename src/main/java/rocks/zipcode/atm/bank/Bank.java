package rocks.zipcode.atm.bank;

import javafx.scene.control.Alert;
import rocks.zipcode.atm.ActionResult;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", 500.00
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200.00
        )));

        accounts.put(3000, new PremiumAccount(new AccountData(3000, "Checking", "example3@gmail.com", 1000.00)));

        accounts.put(4000, new BasicAccount(new AccountData(4000, "Saving", "example4@gmail.com", 1.00)));
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000, 2000, 3000, or 4000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, double amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, double amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
//            account.overdrawn(amount);
//            Double overdraftAmount = account.getBalance() - amount;
//            NumberFormat formatter = new DecimalFormat("#0.00");
//            String overDraftMessage =  "Over drawn account $" + formatter.format(overdraftAmount) ;
            AlertBox.display("Overdraft Alert", "Withdraw failed: Insufficient Funds");
            return ActionResult.fail("Withdraw failed: Insufficient funds");
//            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }

    public double getBalance(AccountData accountData) {
        double balance = accountData.getBalance();
        return balance;
//         ActionResult.success(account.getBalance());
    }
}
