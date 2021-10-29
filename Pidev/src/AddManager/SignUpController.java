package AddManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button button_signup;
    @FXML
    private Button button_log_in;

    @FXML
    private RadioButton rb_wittcode;
    @FXML
    private RadioButton rb_someone_else;

    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;

    @FXML
    private RadioButton rb_wittcode1;

    @FXML
    private RadioButton rb_wittcode2;

    @FXML
    private RadioButton rb_wittcode3;

    @FXML
    private RadioButton rb_wittcode4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_wittcode.setToggleGroup(toggleGroup);
        rb_wittcode1.setToggleGroup(toggleGroup);
        rb_wittcode2.setToggleGroup(toggleGroup);
        rb_wittcode3.setToggleGroup(toggleGroup);
        rb_wittcode4.setToggleGroup(toggleGroup);

        rb_someone_else.setToggleGroup(toggleGroup);

        rb_wittcode.setSelected(true);
        rb_wittcode1.setSelected(true);
        rb_wittcode2.setSelected(true);
        rb_wittcode3.setSelected(true);
        rb_wittcode4.setSelected(true);

        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();

                if (!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText(), toggleName);
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up!");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Log in!", null, null);
            }
        });

    }
}
