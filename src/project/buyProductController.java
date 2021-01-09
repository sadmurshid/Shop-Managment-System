package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.text.SimpleDateFormat;
import java.util.Date;

public class buyProductController {
    @FXML
    TextField companyNameTextField, seNameTextField, dateTextField, discountTextField;

    @FXML
    Button okButton, fullPaymentButton, partialPaymentButton;

    @FXML
    GridPane buyProductGridPane=new GridPane();

    @FXML
    Label totalLabel, grandTotalLabel;

    private database db;
    private String username;
    private Date date=new Date();
    private SimpleDateFormat format=new SimpleDateFormat("dd MMMM yyyy");
    private String strDate=format.format(date);
    private int totalRow=0;
    private String companyName=null;
    private double total, discount, grandTotal;

    ObservableList<buyProductElement> products;

    @FXML
    public void initialize(){
        try{
//            setDefault();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void setDatabase(database dt){
        try{
            db=dt;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void setUserName(String username){
        try{
            this.username=username;
            setDefault();
        }catch (Exception e){
            e.printStackTrace();

        }
    }

    protected database getDatabase(){
        return db;
    }

    private void setDefault(){

        companyNameTextField.setPromptText("COMPANY NAME");
        seNameTextField.setPromptText("SR NAME");
        dateTextField.setText(strDate);

        total=0.0;
        discount=0.0;
        grandTotal=0.0;

        totalLabel.setText(""+total);
        discountTextField.setPromptText(""+discount);
        grandTotalLabel.setText(""+grandTotal);

        products= FXCollections.observableArrayList();


        addInList();

    }

    private buyProductElement createNewProduct(){
        try{

            Label no=new Label(""+(++totalRow));
            TextField pName=new TextField("");
            pName.setPromptText("PRODUCT NAME");
            TextField cName;
            if(companyName==null){
                cName=new TextField();
                cName.setPromptText("COMPANY NAME");
            }else
                cName=new TextField(companyName);
            TextField price=new TextField("0.0");
            TextField quantity=new TextField("1.0");
            TextField uPrice=new TextField("0.0");
            Button add=new Button("ADD");

            add.setOnAction(event -> {
                addInList();
            });

            buyProductElement product=new buyProductElement(no, pName, cName, price, quantity, uPrice, add, this);


            return product;

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Couldn't create a node. An error occur.");
            totalRow--;
            return new buyProductElement("error");
        }
    }

    private boolean addInList(){
        try{
            buyProductElement node=createNewProduct();

            buyProductGridPane.add(node.getNo(), 0, totalRow);
            GridPane.setMargin(node.getNo(), new Insets(0,0,0,5));
            buyProductGridPane.add(node.getProductName(), 1,totalRow);
            GridPane.setMargin(node.getProductName(), new Insets(0,5,0,5));
            buyProductGridPane.add(node.getCompanyName(), 2, totalRow);
            GridPane.setMargin(node.getCompanyName(), new Insets(0,5,0,5));
            buyProductGridPane.add(node.getPriceTextField(), 3, totalRow);
            GridPane.setMargin(node.getPriceTextField(), new Insets(0,5,0,5));
            buyProductGridPane.add(node.getQuantityTextField(), 4, totalRow);
            GridPane.setMargin(node.getQuantityTextField(), new Insets(0,5,0,5));
            buyProductGridPane.add(node.getUnitPriceTextField(), 5, totalRow);
            GridPane.setMargin(node.getUnitPriceTextField(), new Insets(0,5,0,5));
            buyProductGridPane.add(node.getAddButton(), 6, totalRow);
            GridPane.setMargin(node.getAddButton(), new Insets(0,0,0,15));

            products.add(node);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    protected void setTotalDiscountGrandTotal(){
        try{
            int size=products.size();
            double tempTotal=0;


            if(size==totalRow){
                for(int i=0; i<size; i++){
                    tempTotal=tempTotal+products.get(i).getPriceTextFieldValue();
                }
                total=tempTotal;
                grandTotal=total-discount;
                totalLabel.setText(""+total);
                grandTotalLabel.setText(""+grandTotal);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
