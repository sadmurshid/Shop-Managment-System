package project;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class newProductGridPaneController {

    @FXML GridPane newProductGridPane=new GridPane();
    @FXML TextField productNameTextField=new TextField(), quantityTextField=new TextField(), sellPriceTextField=new TextField(),
            buyPriceTextField=new TextField(), maxDiscountTextField=new TextField(), minimumRequireTextField=new TextField(),
            srNameTextField=new TextField(), srPhoneNumberTextfield=new TextField(), categoryTextField=new TextField(),
            productDescriptionTextField=new TextField();
    @FXML Label warningLabel=new Label(), productNameWarningLabel=new Label(""), companyNameWarningLabel=new Label(""),
                quantityWarningLabel=new Label("");
    @FXML Button saveButton=new Button();
    @FXML ComboBox companyNameComboBox=new ComboBox();

    newProductGridPaneController(){
        warningLabel.setText("");
        productNameWarningLabel.setText("");
        companyNameWarningLabel.setText("");
        quantityWarningLabel.setText("");
    }



}
