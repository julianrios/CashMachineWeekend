package rocks.zipcode.atm.bank;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;

    // will the balance change?
    private final Double balance;

    AccountData(int id, String name, String email, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");

        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                "Balance: " + formatter.format(balance);
    }
}
