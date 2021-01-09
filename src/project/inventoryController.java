package project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ListIterator;

public class inventoryController {

    @FXML
    Label productNameLabel, companyNameLabel, warningLabel;

    @FXML
    Button searchButton;

    @FXML
    RadioButton buyPriceRadioButton;

    @FXML
    protected TableView<inventoryTableView> inventoryTableView;

    @FXML
    private TableColumn<inventoryTableView, String> noColumn, productNameColumn, companyNameColumn, stockColumn,
            sellPriceColumn, srNameColumn, minRequireColumn, buyPriceColumn;

    @FXML
            private TextField productNameTextField, companyNameTextField;

    ObservableList<inventoryTableView> products;

    private int totalTableRow=0;

    private String [] [] product;

    database db;
    String username;

    private void setDefault(){

        productNameTextField=new TextField("Product Name");
        companyNameTextField=new TextField("Company Name");

        warningLabel.setText("");
        
        buyPriceRadioButton.setSelected(false);
        
        searchButton=new Button("SEARCH");

        setDefaultProductsTable();

    }
    
    private void setDefaultProductsTable(){
        try{

            
            products= FXCollections.observableArrayList();


            noColumn.setCellValueFactory(new PropertyValueFactory<>("no"));
            productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
            companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
            stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
            srNameColumn.setCellValueFactory(new PropertyValueFactory<>("srName"));
            minRequireColumn.setCellValueFactory(new PropertyValueFactory<>("minRequire"));
            buyPriceColumn.setCellValueFactory(new PropertyValueFactory<>("buyPrice"));

            inventoryTableView.setItems(products);

            setTotalTableRow();
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
    }

    private void setTotalTableRow(){
        try{
            int i = 0;
            for (ListIterator<inventoryTableView> iterator = products.listIterator(); iterator.hasNext();
                 iterator.next()) {

                i++;

            }
            totalTableRow=i;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void populateTableWOBuyPrice(){
        try{
            product=db.getFullInventoryWOBuyPrice(username);

            for(int i=0; i<product.length; i++){
                int no=Integer.parseInt(product[i][0]);
                double stock=Double.parseDouble(product[i][3]);
                double sellPrice=Double.parseDouble(product[i][4]);
                double minRequire=Double.parseDouble(product[i][6]);
                products.add(new inventoryTableView(no, product[i][1], product[i][2], stock, sellPrice, product[i][5], minRequire));



            }

            inventoryTableView.setItems(products);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
        try{
            setDefault();
            
            
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
            populateTableWOBuyPrice();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected database getDatabase(){
        return db;
    }


}
