package SalarySheet;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import net.sf.jasperreports.engine.JRException;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.sql.*;

public class SalaryMainController implements Initializable {
    private Connection connection;
    private CommonData commonData = new CommonData();

    @FXML
    public ComboBox<String> comboBox;

    @FXML
    private TextField mydate;
    @FXML
    MenuBar menuBar;

    @FXML private BorderPane borderPane = new BorderPane();

    ObservableList<String> observableList = FXCollections.observableArrayList("AAA","BBB");

    public void newSalary(ActionEvent actionEvent) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("salaryEntry.fxml"));
        primaryStage.setTitle("Entry Monthly Salary");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner((Stage) menuBar.getScene().getWindow());
       // primaryStage.initStyle(StageStyle.UTILITY);
       // primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        //primaryStage.setResizable(false);

    }


    public void report(ActionEvent myReport) throws Exception{
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("report.fxml"));
        primaryStage.setTitle("Print monthly or yearly Report");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinWidth(800);
        primaryStage.setMaxHeight(700);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner((Stage) menuBar.getScene().getWindow());
        //primaryStage.initStyle(StageStyle.UTILITY);
       // primaryStage.setAlwaysOnTop(false);

        primaryStage.show();
        primaryStage.setResizable(false);


    }

    public void newemployee(ActionEvent myReport) throws Exception{


        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("newEmp.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("")+"/windowStyle.css");
        primaryStage.setTitle("Insert and update employee");
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner((Stage) menuBar.getScene().getWindow());
        primaryStage.initStyle(StageStyle.UTILITY);
        //primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        primaryStage.setResizable(false);


    }

    public void deleteData(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("deleteRow.fxml"));
        primaryStage.setTitle("Delete employee and Salary");
        primaryStage.setScene(new Scene(root));
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner((Stage) menuBar.getScene().getWindow());
        primaryStage.initStyle(StageStyle.UTILITY);
        //primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void closeWindow(){
        System.exit(0);
    }

    public void About(ActionEvent actionEvent) {



        Dialog dialog = new Dialog<>();
        dialog.setTitle("About This Application!");
        dialog.setHeaderText("Application Information!");
        dialog.setResizable(true);


        final ImageView imv = new ImageView();
        final Image image2 = new Image(Main.class.getResourceAsStream("logo.png"));
        imv.setImage(image2);


        TextFlow flow = new TextFlow(
                new Text("\n\nThis software personally developed by JOBYER AHMED(Founder of TraceDot IT Company) to help others for freely.\n" +
                        "The software will be improved gradually if i get feedback & suggestions.\n\n" +
                        "Note: All numbers input should be in English. But This will be converted to Bengali When you print something or generate any report.\n" +
                        "\n\n")
        );
        TextFlow flow1 = new TextFlow(
                new Text("\n\nCompany: TraceDot\n\nWebsite:"), new Hyperlink("http://tracedot.com")
        );
        TextFlow flow2 = new TextFlow(new Text("\nDeveloper: Jobyer Ahmed(Founder of TraceDot).\n\n" +
                "Email: Jobyer@tracedot.com,jobyera@gmail.com.\n\nTell: 01737577661\n\n")
        );

        flow1.setStyle("-fx-font-weight: bold");
        flow2.setStyle("-fx-font-weight: bold");
        VBox grid = new VBox();
        grid.getChildren().add(imv);
        grid.getChildren().add(flow);
        grid.getChildren().add(flow1);
        grid.getChildren().add(flow2);

        dialog.getDialogPane().setContent(grid);


        ButtonType buttonTypeOk = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);



        Optional result = dialog.showAndWait();




    }

    public void databaseSetup(ActionEvent actionEvent){

        try {
            commonData.SetupDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void users(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("users.fxml"));
        primaryStage.setTitle("Delete Employee or Salary");
        primaryStage.setScene(new Scene(root));
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner((Stage) menuBar.getScene().getWindow());
        primaryStage.initStyle(StageStyle.UTILITY);
        //primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public void UpSalary(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("upSalary.fxml"));
        primaryStage.setTitle("Modify Salary");
        primaryStage.setScene(new Scene(root));
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner((Stage) menuBar.getScene().getWindow());
        //primaryStage.initStyle(StageStyle.UTILITY);
        //primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
        primaryStage.setResizable(true);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        borderPane.setStyle("-fx-border-color: brown;-fx-background-color: skyblue");
        menuBar.setStyle("-fx-border-color: darkblue");


    }


}
