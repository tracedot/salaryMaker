package SalarySheet;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by famed on 5/6/16.
 */
public class ConnectionError {

    public void alertError(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Connection Failed!");
        alert.setHeaderText("Database Connection Failed!");
        alert.setContentText("There is something wrong when connecting to Database!");

        alert.showAndWait();
    }

    public void dataExist(String empName, String month){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Already Exist!");
        alert.setHeaderText("Error: Data Already Exist "+ month);
        alert.setContentText("The data for "+ empName+" of " + month + " Already Exist!");

        alert.showAndWait();
    }
}
