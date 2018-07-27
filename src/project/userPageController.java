package project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class userPageController {

    private Stage window, primaryStage;
    private String userName;
    private database db;

    @FXML private BorderPane borderPaneUserPage;
    @FXML private Button userPageInvoiceButton, userPageInventoryButton;
    @FXML private Label userPageUsernameLabel;

    public userPageController()
    {
        try{
            primaryStage= new Stage();
            userPageUsernameLabel=new Label();
            borderPaneUserPage=new BorderPane();
            userPageInvoiceButton=new Button("INVOICE");
            userPageInventoryButton=new Button("INVENTORY");




            System.out.println("user page controller constructor");


        } catch (Exception e){
            e.printStackTrace();
        }


    }


    protected boolean setUserName(String name)
    {
        try{
            userName=name;

            System.out.println(userName);
            userPageUsernameLabel.setText("USERNAME : "+userName);
//            System.out.println(db.userTableGetBackgroundColor(userName));
            String fx="-fx-background-color: "+db.userTableGetBackgroundColor(userName)+";";
//            System.out.println(fx);
            borderPaneUserPage.setStyle(fx);
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void initialize()
    {
        db=new database();
        window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(" USER ");

        userPageInvoiceButton.setText("INVOICE");
        userPageInventoryButton.setText("INVENTORY");


    }

    @FXML
    public void setUserPageInvoiceButton(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("invoiceView.fxml"));
            Parent root=loader.load();

            InvoiceViewController invoiceViewController = loader.getController();
            primaryStage.setTitle("INVOICE");
            Scene scene=new Scene(root, 1300, 700);
//            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
