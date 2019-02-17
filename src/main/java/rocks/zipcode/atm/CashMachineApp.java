package rocks.zipcode.atm;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

import java.awt.*;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private TextField depositAndWithdrawTextField = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setPrefSize(1000, 1000);

        TextArea areaInfo = new TextArea();
        Button btnDeposit = new Button("Deposit");
        btnDeposit.setDisable(true);

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setDisable(true);

        Button btnAccounts = new Button("Accounts");
        btnAccounts.setDisable(true);

//        Button btnLogIn = new Button("Log in");
        Button btnHelp = new Button("Help");

        Label depositAndWithdrawLabel = new Label("Enter Deposit or Withdraw Amount:");
        depositAndWithdrawTextField.setDisable(true);


        Label accountIDLabel = new Label("Account ID:");
        TextField accountIDTextField = new TextField (cashMachine.accountIdToString());
//        accountIDTextField.setDisable(true);

        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField (cashMachine.nameToString());
        nameTextField.setDisable(true);

        Label emailLabel = new Label("Email");
        TextField emailTextField = new TextField (cashMachine.emailToString());
        emailTextField.setDisable(true);

        Label balanceLabel = new Label("Balance:");
        TextField balanceTextField = new TextField (cashMachine.balanceToString());
        balanceTextField.setDisable(true);

        field.setText("Enter Account ID.");

//        btnLogIn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
////                System.out.println("Enter ID");
//                areaInfo.setText("Enter ID");
//            }
//        });

        btnHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                areaInfo.setText("Loading...");
            }
        });


        Button btnSubmit = new Button("Login");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(accountIDTextField.getText());
            cashMachine.login(id);

            depositAndWithdrawTextField.setDisable(false);
//            accountIDTextField.setDisable(false);
            nameTextField.setDisable(false);
            emailTextField.setDisable(false);
            balanceTextField.setDisable(false);

            accountIDTextField.setText(cashMachine.accountIdToString());
            nameTextField.setText(cashMachine.nameToString());
            emailTextField.setText(cashMachine.emailToString());
            balanceTextField.setText(cashMachine.balanceToString());

            btnDeposit.setDisable(false);
            btnWithdraw.setDisable(false);
            btnAccounts.setDisable(false);

//            areaInfo.setText(cashMachine.toString());
        });


//        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            double amount = Double.parseDouble(depositAndWithdrawTextField.getText());
            cashMachine.deposit(amount);
            balanceTextField.setText(cashMachine.balanceToString());
//            areaInfo.setText(cashMachine.toString());
        });

//        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            double amount = Double.parseDouble(depositAndWithdrawTextField.getText());

//            if(amount > cashMachine.getAccountBalance()) {
//                String overDraftAmount = (amount - cashMachine.getAccountBalance()) + "";
//                areaInfo.setText("You have over drafted your account $" + overDraftAmount);
//            }
            cashMachine.withdraw(amount);
            balanceTextField.setText(cashMachine.balanceToString());
//            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            System.exit(-1);

//            areaInfo.setText(cashMachine.toString());
        });

//        Button btnAccounts = new Button("Accounts");
        btnAccounts.setOnAction(e -> {
            cashMachine.exit();

            depositAndWithdrawTextField.setDisable(true);
//            accountIDTextField.setDisable(true);
            nameTextField.setDisable(true);
            emailTextField.setDisable(true);
            balanceTextField.setDisable(true);

            btnDeposit.setDisable(true);
            btnWithdraw.setDisable(true);

            accountIDTextField.setText(cashMachine.accountIdToString());
            nameTextField.setText(cashMachine.nameToString());
            emailTextField.setText(cashMachine.emailToString());
            balanceTextField.setText(cashMachine.balanceToString());

            areaInfo.setText(cashMachine.toString());
        });

//        if(!(btnSubmit.isArmed())) {
//            btnDeposit.setDisable(false);
//            btnWithdraw.setDisable(false);
//
//        }

        FlowPane flowpane = new FlowPane();

//        flowpane.getChildren().add(btnLogIn);
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnAccounts);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnHelp);
        flowpane.getChildren().add(btnExit);

        // areaInfo

        vbox.getChildren().addAll(flowpane, depositAndWithdrawLabel, depositAndWithdrawTextField, accountIDLabel, accountIDTextField, nameLabel, nameTextField,
                emailLabel, emailTextField, balanceLabel, balanceTextField, areaInfo);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Welcome to ZipBanking");
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
