package project;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class inventoryTableView {
    private SimpleIntegerProperty no;
    private SimpleStringProperty productName;
    private SimpleStringProperty companyName;
    private SimpleDoubleProperty stock;
    private SimpleDoubleProperty sellPrice;
    private SimpleStringProperty srName;
    private SimpleDoubleProperty minRequire;
    private SimpleDoubleProperty buyPrice;

    public inventoryTableView(int no, String productName, String companyName, double stock, double sellPrice, String srName,
                              double minRequire, double buyPrice){
        this.no=new SimpleIntegerProperty(no);
        this.productName=new SimpleStringProperty(productName);
        this.companyName=new SimpleStringProperty(companyName);
        this.stock=new SimpleDoubleProperty(stock);
        this.sellPrice=new SimpleDoubleProperty(sellPrice);
        this.srName=new SimpleStringProperty(srName);
        this.minRequire=new SimpleDoubleProperty(minRequire);
        this.buyPrice=new SimpleDoubleProperty(buyPrice);


    }

    public inventoryTableView(int no, String productName, String companyName, double stock, double sellPrice, String srName,
                              double minRequire){
        this.no=new SimpleIntegerProperty(no);
        this.productName=new SimpleStringProperty(productName);
        this.companyName=new SimpleStringProperty(companyName);
        this.stock=new SimpleDoubleProperty(stock);
        this.sellPrice=new SimpleDoubleProperty(sellPrice);
        this.srName=new SimpleStringProperty(srName);
        this.minRequire=new SimpleDoubleProperty(minRequire);

    }

    public String getcompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setcompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public int getno() {
        return no.get();
    }

    public SimpleIntegerProperty noProperty() {
        return no;
    }

    public void setno(int no) {
        this.no.set(no);
    }

    public String getproductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setproductName(String productName) {
        this.productName.set(productName);
    }


    public double getstock() {
        return stock.get();
    }

    public SimpleDoubleProperty stockProperty() {
        return stock;
    }

    public void setstock(int stock) {
        this.stock.set(stock);
    }

    public double getsellPrice() {
        return sellPrice.get();
    }

    public SimpleDoubleProperty sellPriceProperty() {
        return sellPrice;
    }

    public void setsellPrice(double sellPrice) {
        this.sellPrice.set(sellPrice);
    }

    public String getsrName() {
        return srName.get();
    }

    public SimpleStringProperty srNameProperty() {
        return srName;
    }

    public void setsrName(String srName) {
        this.srName.set(srName);
    }

    public double getminRequire() {
        return minRequire.get();
    }

    public SimpleDoubleProperty minRequireProperty() {
        return minRequire;
    }

    public void setminRequire(int minRequire) {
        this.minRequire.set(minRequire);
    }

    public double getbuyPrice() {
        return buyPrice.get();
    }

    public SimpleDoubleProperty buyPriceProperty() {
        return buyPrice;
    }

    public void setbuyPrice(double buyPrice) {
        this.buyPrice.set(buyPrice);
    }
}
