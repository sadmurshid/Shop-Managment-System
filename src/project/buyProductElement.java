package project;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class buyProductElement {

    private Label no;
    private TextField productName, companyName, priceTextField, quantityTextField, unitPriceTextField;
    private Button addButton;
    private  String warning;
    private buyProductController controller;
    private double price, quantity, unitPrice;

    protected buyProductElement(Label no, TextField pName, TextField cName, TextField price, TextField quantity,
                                TextField uPrice, Button aButton, buyProductController bpc){

        this.no=no;
        productName=pName;
        companyName=cName;
        this.priceTextField=price;
        this.quantityTextField=quantity;
        unitPriceTextField=uPrice;
        addButton=aButton;
        controller=bpc;

        unitPrice=Double.parseDouble(uPrice.getText());
        this.price=Double.parseDouble(uPrice.getText());
        this.quantity=Double.parseDouble(quantity.getText());
        unitPrice=this.price/this.quantity;
        priceTextField.setOnKeyTyped(event -> { priceOnAction(); });
        quantityTextField.setOnKeyTyped(event -> {quantityOnAction();});
        unitPriceTextField.setOnKeyTyped(event -> { unitPriceOnAction();});

    }

    private void priceOnAction(){
        String str=priceTextField.getText();
        if(!str.equals("")) {
            price = Double.parseDouble(str);
        }else{
            price=0.0;
        }
        unitPrice = price / quantity;
        unitPriceTextField.setText("" + unitPrice);
        controller.setTotalDiscountGrandTotal();
    }

    private void quantityOnAction(){
        String str=quantityTextField.getText();

        if(!str.equals(""))
            quantity=Double.parseDouble(str);
        else
            quantity=0.0;
        unitPrice=price/quantity;
        unitPriceTextField.setText(""+unitPrice);
        controller.setTotalDiscountGrandTotal();
    }

    private void unitPriceOnAction(){
        String str=unitPriceTextField.getText();
        if(!str.equals(""))
            unitPrice=Double.parseDouble(str);
        else
            unitPrice=0.0;
        price=quantity*unitPrice;
        priceTextField.setText(""+price);
        controller.setTotalDiscountGrandTotal();
    }

    protected buyProductElement(String str){
        warning=str;
    }

    protected Label getNo() {
        return no;
    }

    protected void setNo(Label no) {
        this.no = no;
    }

    protected TextField getProductName() {
        return productName;
    }

    protected void setProductName(TextField productName) {
        this.productName = productName;
    }

    protected TextField getCompanyName() {
        return companyName;
    }

    protected void setCompanyName(TextField companyName) {
        this.companyName = companyName;
    }

    protected TextField getPriceTextField() {
        return priceTextField;
    }

    protected void setPriceTextField(TextField priceTextField) {
        this.priceTextField = priceTextField;
    }

    protected TextField getQuantityTextField() {
        return quantityTextField;
    }

    protected void setQuantityTextField(TextField quantity) {
        this.quantityTextField = quantity;
    }

    protected TextField getUnitPriceTextField() {
        return unitPriceTextField;
    }

    protected void setUnitPriceTextField(TextField unitPriceTextField) {
        this.unitPriceTextField = unitPriceTextField;
    }

    protected Button getAddButton() {
        return addButton;
    }

    protected void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    protected int getNoValue(){
        return Integer.parseInt(no.getText());
    }

    protected String getProductNameValue(){
        return productName.getText();
    }

    protected String getCompanyNameValue(){
        return companyName.getText();
    }

    protected double getPriceTextFieldValue(){
        return price;
    }

    protected double getQuantityTextFieldValue(){
        return quantity;
    }

    protected double getUnitPriceTextFieldValue(){
        return unitPrice;
    }


}
