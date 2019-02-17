package rocks.zipcode.atm.bank;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Modality;
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

public class AlertBox {
    public static void display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close Window");
        closeButton.setOnAction(e-> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos. CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        window.showAndWait();
    }
}
