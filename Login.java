package SalarySheet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by famed on 5/6/16.
 */
public class Login implements Initializable{

    @FXML
    public TextField textFielduser;
    public PasswordField passwordField;
    public Label staus;



    public void login(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        CommonData commonData = new CommonData();
        if(commonData.loginFirst(textFielduser.getText(),passwordField.getText())){

            ((Node)actionEvent.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("salaryMain.fxml"));
            primaryStage.setTitle("SalaryMaker by Jobyer Ahmed");
            primaryStage.setScene(new Scene(root,800,600));
            primaryStage.setMaximized(true);
            primaryStage.show();
            //primaryStage.setResizable(false);
        }
        else {
            staus.setText(commonData.loginFailed);
        }


    }

    public void ExitButton(ActionEvent actionEvent){
        ((Node)actionEvent.getSource()).getScene().getWindow().hide();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
