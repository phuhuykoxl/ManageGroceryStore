package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BillController implements Initializable {


    @FXML
    private Button btn_prBill;
    @FXML
    private AnchorPane pane_bill;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_prBill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("views/detail_bill.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                    pane_bill.getChildren().add(0, node);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}