package project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class loginPageController {
    @FXML private Label errorMessageLabel;
    @FXML
    Button loginButton = new Button("Login");
    @FXML Button signupButton = new Button();
    @FXML
    private void initialize(){
//        errorMessageLabel.setText("error");
        loginButton.setOnAction(event ->{
            changeLabel("Username or Password was wrong");
            System.out.println("log in Button clicked");
        });
        signupButton.setOnAction(e->{
            System.out.println("signup Button clicked");
        });
    }








    @FXML
    private void changeLabel(String error)
    {
        try
        {
            errorMessageLabel.setText(error);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}
