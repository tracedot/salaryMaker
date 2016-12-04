package SalarySheet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        CommonData commonData = new CommonData();


        File file = new File("salarymakerfile/db/salarysheet.mv.db").getAbsoluteFile();
        if (!file.exists()){
            System.out.println(file.toString());
            commonData.SetupDB();

        }

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("")+"/windowStyle.css");

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        //primaryStage.setMaximized(true);

        primaryStage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}
