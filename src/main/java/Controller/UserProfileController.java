package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_fix;
    @FXML
    private Button btn_delete;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence2(actionEvent, "views/update_view.fxml", "Update", null);
            }
        });
        btn_fix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence2(actionEvent, "views/update_view.fxml", "Fix", null);
            }
        });
        btn_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScence2(actionEvent, "views/update_view.fxml", "Delete", null);
            }
        });
    }
}
