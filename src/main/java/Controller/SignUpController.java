package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button btn_signup;
    @FXML
    private Hyperlink btn_log_in;
    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField tf_password;
    @FXML
    private PasswordField tf_confirmpass;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty() && !tf_confirmpass.getText().trim().isEmpty()){
                    LoginController.SignUpUser(event,tf_username.getText(), tf_password.getText(),tf_confirmpass.getText());
                }else{
                    System.out.println("Please fill in all information ");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information");
                    alert.show();
                }
            }
        });

        btn_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginController.changeScence1(event, "views/hello-view.fxml", "Log In", null);
            }
        });
    }
}
