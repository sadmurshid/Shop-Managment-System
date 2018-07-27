package project;

import java.sql.*;
import java.sql.Timestamp;
import java.util.Date;

public class database {

    private static String databaseName="shop_management_database";
    private static String [] [] tableNameAndParameter=null;
    private static Connection conn = null;
    private static Statement statement;
    private static String sql;
    private static ResultSet resultSet, rs, rs1;

    public database(String address, String user, String password)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(address, user, password);

            System.out.println("conn built");
            databaseExist(databaseName);
            statement = conn.createStatement();
            sql = "use " + databaseName;
            statement.executeUpdate(sql);
            createTableNameAndParameter();
            for (int i = 0; i < tableNameAndParameter.length; i++) {
                if (!tableExist(databaseName, tableNameAndParameter[i][0]))
                    createTable(databaseName, tableNameAndParameter[i][0], tableNameAndParameter[i][1]);
            }
            if(!nameExist("SYSTEM_ADMIN"))
                addUser("SYSTEM_ADMIN", "admin", "01676542521", "51/1 Gohailkandi","sadmurshid@gmail.com","#ffffff", 1, 1,1,1, 1);


        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public database()
    {

    }

/*  Method Name: createTableNameAndParameter
    Parameter  : null
    Return     : void
    Purpose    : Initiate tableNameAndParameter String array which consists [tableName][parameters]
*/
    private static void createTableNameAndParameter()
    {
        databaseMandatoryTables dmt=new databaseMandatoryTables();
        String [] tablesName=dmt.tableNames(), tableDetails=dmt.tabelDetails();

        tableNameAndParameter=new String[tablesName.length][2];

        for(int i=0; i<tablesName.length; i++){
            tableNameAndParameter[i][0]=tablesName[i];
            tableNameAndParameter[i][1]=tableDetails[i];
        }



/*        tableNameAndParameter[0][0]="user";
        tableNameAndParameter[0][1]="user_ID int not null AUTO_INCREMENT,"+
                                     "name varchar(40),"+
                                     "password varchar(32),"+
                                     "phone_number varchar(11),"+
                                     "address varchar(100),"+
                                     "email varchar(50),"+
                                     "backgroundColor varchar(15),"+
                                     "inventory_access int not null,"+
                                     "batch_access int not null,"+
                                     "order_access int not null,"+
                                     "debits_access int not null,"+
                                     "PRIMARY KEY (user_ID)";
        tableNameAndParameter[1][0]="inventory";
        tableNameAndParameter[1][1]="product_ID int not null,"+
                                    "company_name varchar(20) not null,"+
                                    "product_name varchar(20) not null,"+
                                    "serial int not null,"+
                                    "quantity double not null,"+
                                    "sell_price double not null,"+
                                    "buy_price double not null,"+
                                    "max_discount double not null,"+
                                    "sr_name varchar(40),"+
                                    "sr_phone_number varchar(11),"+
                                    "starting_inventory double not null,"+
                                    "minimum_require double,"+
                                    "category varchar(20),"+
                                    "product_description varchar(50),"+
                                    "PRIMARY KEY (product_ID)";
        tableNameAndParameter[2][0]="batch";
        tableNameAndParameter[2][1]="product_ID int not null,"+
                                    "buy_price double not null,"+
                                    "quantity double not null,"+
                                    "buy_date date not null,"+
                                    "sr_name varchar(40),"+
                                    "PRIMARY KEY (product_ID, buy_date)";
        tableNameAndParameter[3][0]="orders";
        tableNameAndParameter[3][1]="order_ID int not null AUTO_INCREMENT,"+
                                    "product_ID int not null,"+
                                    "product_name varchar(20) not null,"+
                                    "quantity double not null,"+
                                    "sell_price double not null,"+
                                    "per_item_discount double,"+
                                    "total_discount double not null,"+
                                    "user_ID int not null,"+
                                    "customer_name varchar(40),"+
                                    "order_date date not null,"+
                                    "amount_received double not null,"+
                                    "amount_returned double not null,"+
                                    "PRIMARY KEY (order_ID)";
        tableNameAndParameter[4][0]="debits";
        tableNameAndParameter[4][1]="customer_ID int not null,"+
                                    "debits_ID int not null,"+
                                    "order_ID int not null,"+
                                    "date_given date,"+
                                    "date_return date,"+
                                    "PRIMARY KEY (customer_ID,debits_ID)";
        tableNameAndParameter[5][0]="basic";
        tableNameAndParameter[5][1]="shop_name varchar(40) not null,"+
                                    "owner_name varchar(40),"+
                                    "address varchar(80),"+
                                    "contact varchar(40),"+
                                    "picture_location varchar(150),"+
                                    "PRIMARY KEY (shop_name)";

                                    */




    }

/*  Method Name: databaseExist
    Parameter  : String databaseName
    Return     : void
    Purpose    : Check a database using the given name. If don't exist then call createDatabase to create one.
*/
    private static void databaseExist(String databaseName)
    {
        String getDatabaseName;
        ResultSet resultSet=null;
        boolean requiredDatabase=false;
        try {
            resultSet = conn.getMetaData().getCatalogs();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try{
            while(resultSet.next())
            {
                getDatabaseName=resultSet.getString(1);
                if(getDatabaseName.equals(databaseName))
                {
                    requiredDatabase=true;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        if(requiredDatabase==false){
            createDatabase(databaseName);
        }
    }

/*  Method Name: createDatabase
    Parameter  : String database
    Purpose    : Create a database using the given name
*/
    private static void createDatabase(String database)
    {
        String sql="create database "+database+";";
        System.out.println(sql);
        Statement stmt;
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully");

        }
        catch(Exception e)
        {
            System.out.println("Database creation failed\n");
            System.out.println(e);
            e.printStackTrace();
        }
    }

/*  Method Name: tableExist
    Parameter  : String databaseName, String tableName
    Purpose    : Check a table whether exist by the given name
    Return     : Boolean true if table exist false if don't exist
*/
    private static boolean tableExist(String databaseName, String tableName) throws Exception
    {
        boolean isTableExist=false;
        try{
            DatabaseMetaData metaData=conn.getMetaData();
            resultSet=metaData.getTables( databaseName, null,"%", null);
            String str;
            while(resultSet.next())
            {
                str=resultSet.getString("TABLE_NAME");
                if(str.equals(tableName)){
                    isTableExist=true;
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
//        System.out.println("Table Name : "+tableName+" Existence : "+isTableExist);
        return isTableExist;
    }

/*  Method Name: createTable
    Parameter  : String databaseName, String tableName, String nameParameter
                    for nameParameter the whole query inside "(" ")" must be provided
    Purpose    : Create a table by the given name
    Return     : Boolean true if table creation successful false if failed
*/
    private static boolean createTable(String databaseName, String tableName, String nameParameter)
    {
        boolean creationSuccessful=false;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="create table "+tableName+" ("+nameParameter+");";
            System.out.println(sql);
            statement.executeUpdate(sql);
            creationSuccessful=true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return creationSuccessful;
    }

    public boolean loginAllowed(String userName, String password)
    {
        boolean allowed=false;
        String tempUser=null, tempPassword=null;
//        System.out.println("user : "+userName+" password : "+password);
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select name, password from user order by user_ID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                tempUser=resultSet.getString("name");
                tempPassword=resultSet.getString("password");
                System.out.println(tempUser+" "+tempPassword);
                if(tempUser.equals(userName) && tempPassword.equals(password))
                    return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allowed;
    }

/*  Method Name: checkAccess
    Parameter  : String userName, String accessTable <only inventoryAccess, batchAccess, orderAccess, debitsAccess, adminAccess allowed>
    Purpose    : Check the specified user has the access of the desired table or level.
    Return     : Boolean true if access allowed, false if not allowed or any exception created
*/
    public boolean checkAccess(String userName, String accessTable){
        boolean boo=false;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="SELECT NAME, "+accessTable+" FROM USER WHERE NAME ='"+userName+"';";
            resultSet=statement.executeQuery(sql);
            resultSet.next();
            int access=resultSet.getInt(""+accessTable);
            if(access==1){
                return true;
            }else{
                System.out.println("Access for "+accessTable+" = "+access);
                boo=false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return boo;
    }

    private String userTableGetUserName(String userID)
    {
        String userName=null;
        String ID=null;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select name, userID from user order by userID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                userName=resultSet.getString("name");
                ID=resultSet.getString("user_ID");
                if(userID.equals(ID))
                {
                    return userName;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return userName;
    }

    private String userTableGetPassword(String userID)
    {
        String password=null;
        String ID=null;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select password, userID from user order by userID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                password=resultSet.getString("password");
                ID=resultSet.getString("user_ID");
                if(userID.equals(ID))
                {
                    return password;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return password;
    }

    public String userTableGetBackgroundColor(String userName)
    {
        String color=null;
        String tempName;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select backgroundColor, name from user order by name;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                color=resultSet.getString("backgroundColor");
                tempName=resultSet.getString("name");
                if(tempName.equals(userName))
                {
                    return color;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return color;
    }

    public boolean addUser(String name, String password, String phone_number, String address, String email
                                , String b_color, int inventory, int batch, int order, int debits, int admin){
//        boolean boo=false;
        try{
            sql="use "+databaseName;
//            System.out.println(sql);
            statement.executeUpdate(sql);
            sql="select max(user_ID) from user";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(user_ID)");
            if(!accessIntCorrect(inventory)||!accessIntCorrect(batch)||!accessIntCorrect(order)
                            ||!accessIntCorrect(debits)||!accessIntCorrect(admin)){
                return false;
            }
            sql="insert into user values ("+(max+1)+", '"+name+"', '"+password+"', '"+ phone_number+"', '"+address+"', '"
                    +email+"', '"+b_color+"', "+ inventory+", "+batch+", "+order+", "+debits+", "+admin+");";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private boolean accessIntCorrect(int integer){
        boolean boo=false;
        try{
            if(integer==0||integer==1)
                boo= true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  boo;
    }


/*  Method Name: nameExist
    Parameter  : String Name
    Purpose    : Check names from the user table whether exist by the given name
    Return     : Boolean true if name exist false if don't exist
*/
    public boolean nameExist(String name){
        boolean boo=false;
        try{
            String tempUser;
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select name from user order by user_ID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                tempUser=resultSet.getString("name");
//                System.out.println(tempUser+" "+tempPassword);
                if(tempUser.equals(name))
                    return true;
            }

        }catch(NullPointerException npe){
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return boo;
    }

/*
    Method Name: getShopName
    Parameter  : null
    Purpose    : Return Shop name from basic table
    Return     : String shopName
*/
    public String getShopName() {
        String shopName="";
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select shop_name from basic ;";
            resultSet=statement.executeQuery(sql);
            if(resultSet.next()) {
                shopName = resultSet.getString("shop_name");
            }
            return shopName;

        }catch (SQLException sqlE){
            sqlE.printStackTrace();
            return "Shop Name Wasn't Set.";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return shopName;
    }

    public String getOwnerName() {
        String ownerName="";
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select owner_name from basic ;";
            resultSet=statement.executeQuery(sql);
            if(resultSet.next()) {
                ownerName = resultSet.getString("owner_name");
            }
            return ownerName;

        }catch (SQLException sqlE){
            sqlE.printStackTrace();
            return "Owner Name Wasn't Set.";
        }catch (Exception e){
            e.printStackTrace();
        }
        return ownerName;
    }

    public String getAddress() {
        String address="";
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select address from basic ;";
            resultSet=statement.executeQuery(sql);
            if(resultSet.next()) {
                address = resultSet.getString("address");
            }
            return address;

        }catch (SQLException sqlE){
            sqlE.printStackTrace();
            return "Address Wasn't Set.";
        }catch (Exception e){
            e.printStackTrace();
        }
        return address;
    }

    public String getContact() {
        String contact="";
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select contact from basic ;";
            resultSet=statement.executeQuery(sql);
            if(resultSet.next()) {
                contact = resultSet.getString("contact");
            }
            return contact;

        }catch (SQLException sqlE){
            sqlE.printStackTrace();
            return "Contact Wasn't Set.";
        }catch (Exception e){
            e.printStackTrace();
        }
        return contact;
    }

    public boolean setBasicInformation(String shopName, String ownerName, String address, String contact){
        boolean boo=false;
        try{
            sql="use "+databaseName;
            System.out.println(sql);
            statement.executeUpdate(sql);

            sql="TRUNCATE TABLE  basic";
            statement.executeUpdate(sql);

            sql="insert into basic values ( '"+shopName+"', '"+ownerName+"', '"+ address+"', '"+contact+"' , '');";
            System.out.println(sql);
            statement.executeUpdate(sql);


            return boo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return boo;
    }

    public boolean addNewProduct(String productName,String companyName, double quantity, double sellPrice, double buyPrice,
                                 double maxDiscount, String srName, String srPhoneNumber, double startingInventory,
                                 double minimumRequire, String catagory, String productDescrrption, String userName){
        boolean boo=false;
        try{
            if(nameExist(userName) && checkAccess(userName, "inventory_access") && !uniqueProductAndCompanyName(productName, companyName) ){

                sql="use "+databaseName;
                statement.executeUpdate(sql);
                sql="SELECT MAX(product_ID) FROM inventory";
                rs = statement.executeQuery(sql);
                rs.next();
                int max=rs.getInt("MAX(product_ID)");
                sql="INSERT INTO inventory VALUES ( "+(max+1)+", '"+productName+"', '"+companyName+"', "+quantity+", "+sellPrice+
                        ", "+buyPrice+", "+maxDiscount+", '"+srName+"', '"+srPhoneNumber+"', "+startingInventory+", "+
                        minimumRequire+", '"+catagory+"', '"+productDescrrption+"');";
                System.out.println(sql);
                statement.executeUpdate(sql);
                if(startingInventory>0){
                    Date date = new Date();
                    Timestamp time=new Timestamp(date.getTime());
                    addNewBatch((max+1), productName, buyPrice, sellPrice, maxDiscount, startingInventory, time, srName,
                            0.0, 0.0, time, userName);
                }


            }else {
                if(nameExist(userName)){
                    System.out.println("User Name don't exist.");
                }else if(checkAccess(userName, "inventory_access")) {
                    System.out.println("Don't have inventory access.");
                }
                boo=false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return  boo;
    }

    public boolean addNewBatch(int productID, String productName, double buyPrice, double sellPrice, double maxDiscount,
                               double quantity, Timestamp buyDateTime, String srName, double cashPaid, double due,
                               Timestamp fullPaidDate, String userName){
        try{
            if(nameExist(userName) && checkAccess(userName, "batch_access")){
                sql="use "+databaseName;
                statement.executeUpdate(sql);
                sql="SELECT MAX(batch_ID) FROM batch";
                rs = statement.executeQuery(sql);
                rs.next();
                int max=rs.getInt("MAX(batch_ID)");
                sql="INSERT INTO inventory VALUES ( "+(max+1)+", "+productID+", '"+productName+"', "+buyPrice+", "+sellPrice+", "+
                        maxDiscount+", "+quantity+", "+buyDateTime+", "+srName+", "+cashPaid+", "+due+", "+fullPaidDate+", '"+userName+"');";
                System.out.println(sql);
                statement.executeUpdate(sql);
                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }


        return false;
    }

/*
    Method Name: productNameExist
    Parameter  : String productName
    Purpose    : check whether the inventory contain same product name or not
    Return     : boolean true [if contain] or false [if don't contain]
*/
    public boolean productNameExist(String productName){
        boolean boo=false;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="SELECT product_name FROM inventory WHERE product_name= '"+productName+"' ;";
            resultSet=statement.executeQuery(sql);
            if(resultSet==null){
                return false;
            }else{
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("An error occur.");
            return boo;
        }
    }

/*
        Method Name: companyNameExist
        Parameter  : String productName
        Purpose    : check whether the company table contain same company name or not
        Return     : boolean true [if contain] or false [if don't contain]
    */
    public boolean companyNameExist(String companyName){
        boolean boo=false;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="SELECT company_name FROM company WHERE company_name= '"+companyName+"' ;";
            resultSet=statement.executeQuery(sql);
            if(resultSet==null){
                return false;
            }else{
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("An error occur.");
            return boo;
        }
    }

    /*
    Method Name: uniqueProductAndCompanyName
    Parameter  : String productName, String companyName
    Purpose    : check whether the inventory contain same product name & company name or not
    Return     : boolean true [if contain] or false [if don't contain]
*/
    private boolean uniqueProductAndCompanyName(String productName, String companyName){
        boolean boo=false;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="SELECT product_name, company_name FROM inventory WHERE product_name= '"+productName+"' & company_name='"+companyName+"';";
            resultSet=statement.executeQuery(sql);
            if(resultSet==null){
                return false;
            }else{
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("An error occur.");
            return boo;
        }
    }
}
