package project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class InvoiceViewController {

    @FXML
    ImageView invoiceShopLogoImageView;

    @FXML
    Label invoiceShopNameLabel, invoiceOwnerNameLabel, invoiceShopAddressLabel, invoiceContactLabel;
    @FXML
    Label invoiceCustomerNameLabel, invoiceCustomerAddressLabel, invoiceCustomerContactLabel;
    @FXML
    Label invoiceInvoiceNumberLabel, invoiceDateLabel, invoiceTimeLabel, invoiceAddressLabel;
    @FXML
    Label invoiceCustomerContactInfoLabel, invoiceSearchLabel, invoiceSearchProductDetailsLabel, invoiceSubTotalLabel;
    @FXML
    Label invoiceDiscountLabel, invoiceGrandTotalLabel, invoiceCashPaidLabel, invoiceCashBackLabel, invoiceDueLabel;
    @FXML
    Label invoiceTotalAmountLabel, invoiceGrandTotalAmountLabel, invoiceBackAmountLabel, invoiceDueAmountLabel;

    @FXML
    TextField invoiceCustomerNameTextField, invoiceSearchQuantityTextField, invoiceDiscountTextField,
            invoiceCashPaidTextField;

    @FXML
    ComboBox invoiceSearchProductNameComboBox, invoiceSearchCompanyNameComboBox;

    @FXML
    Button invoiceSearchAddButton, invoiceCheckOutButton, invoiceCancelButton;

    @FXML
    public void initialize(){
        database db=new database();
        try{
            invoiceShopNameLabel.setText(db.getShopName());
            invoiceOwnerNameLabel.setText(db.getOwnerName());
            invoiceAddressLabel.setText(db.getAddress());
            invoiceContactLabel.setText(db.getContact());


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
