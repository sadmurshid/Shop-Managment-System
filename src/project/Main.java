package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Runtime.getRuntime().exec("E:\\Work\\Shop Managment System\\src\\database\\start.bat", null, new File("E:\\Work\\Shop Managment System\\src\\database\\"));
        Thread.sleep(5000);
        database d = new database("jdbc:mysql://localhost:3311/","root","root");
//        d.createDB("result_processing_db");





        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        primaryStage.setTitle("LOGIN");

        Scene scene=new Scene(root, 590, 455);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);

        loginPageController lp=new loginPageController();
//        lp.changeLabel("User Name or Password was incorrect");

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
