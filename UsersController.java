package SalarySheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by famed on 5/27/16.
 */
public class UsersController implements Initializable{
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Connection connection;
    CommonData commonData = new CommonData();
    ObservableList<String> observableList = FXCollections.observableArrayList();
    private String query = null;
    private int userID = 0;

    @FXML
    ListView<String> listView = new ListView<>();
    @FXML
    private PasswordField passUpdate;
    @FXML
    private PasswordField oldpass;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    Button button1;
    @FXML
    Button button2;

    public void UpdatePassword(ActionEvent actionEvent) throws SQLException {
        query = "UPDATE users SET password=? WHERE username=? ";
        String oldpw = "select * from users where username=? and password =?";
        try {
            connection = SqlConnect.con();

            preparedStatement = connection.prepareStatement(oldpw);
            preparedStatement.setString(1,listView.getSelectionModel().getSelectedItem());
            preparedStatement.setString(2,oldpass.getText());
            resultSet =preparedStatement.executeQuery();

            if(resultSet.next()){

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,passUpdate.getText());
                preparedStatement.setString(2,listView.getSelectionModel().getSelectedItem());
                int updated = preparedStatement.executeUpdate();
                if(updated != 0){
                    label1.setText("Password has been changed!");
                    label1.setStyle("-fx-border-color: green;-fx-font-weight: bold");
                    passUpdate.clear();
                    oldpass.clear();
                    return;
                }

                else {
                    label1.setText("Something wrong!");
                    label1.setStyle("-fx-border-color: green;-fx-font-weight: bold");
                    return;
                }
            }

            else {
                label1.setText("Old password did not match!");
                label1.setStyle("-fx-border-color: red");
                oldpass.clear();
                return;
            }


        }

        catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            connection.close();
        }

    }

    public void NewUser(ActionEvent actionEvent) throws SQLException {
        query = "INSERT INTO USERS(username,password) VALUES(?,?)";
        String query2 = "select * from users where username="+"'"+username.getText()+"'";
        ResultSet exist;
        try {
            connection = SqlConnect.con();
            Statement statement = connection.createStatement();
            exist=statement.executeQuery(query2);
            if(exist.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Error: The username Already Exist");
                alert.setContentText("Please choose different username!");
                alert.showAndWait();
                return;
            }
            else {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,username.getText());
                preparedStatement.setString(2,password.getText());
                int inserted = preparedStatement.executeUpdate();
                if(inserted !=0){
                    label2.setText("New Admin added!");
                    label2.setStyle("-fx-border-color: green;-fx-font-weight: bold");
                    password.clear();
                }
                else{
                    label2.setText("Something wrong!");
                    label2.setStyle("-fx-border-color: green;-fx-font-weight: bold");
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //listView.setItems();
        listView.getItems().clear();
        username.clear();
        CallMe();

    }
    public void DeleteUser(ActionEvent actionEvent){


        String myQuery="DELETE FROM users WHERE username=?";

        Alert  alert = new Alert(Alert.AlertType.CONFIRMATION);
        try {
            alert.setTitle("Sure?");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result=alert.showAndWait();
            if(result.get() == ButtonType.CLOSE.OK){
                connection=SqlConnect.con();
                preparedStatement = connection.prepareStatement(myQuery);
                preparedStatement.setString(1,listView.getSelectionModel().getSelectedItem());

                preparedStatement.executeUpdate();
                label1.setText("The User has been deleted !");
                label1.setStyle("-fx-border-color: white;-fx-background-color: red;-fx-text-fill: white");
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
            }
            else {
                return;
            }



        }
        catch (SQLException e){

            System.out.println(e);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //listView.getItems().clear();
        //CallMe();


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CallMe();
        passUpdate.textProperty().addListener((observable, oldValue, newValue) -> {
            if (passUpdate.getText().length() >= 4){
                button1.setDisable(false);


            }

            else if(passUpdate.getText().isEmpty()){
                button1.setDisable(true);
            }


        });

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (password.getText().length() >= 4){
                button2.setDisable(false);


            }

            else if(password.getText().isEmpty()){
                button2.setDisable(true);
            }
        });
    }

    public void CallMe(){
        button1.setDisable(true);
        button2.setDisable(true);
        query = "select username,userID from users";
        try {
            connection = SqlConnect.con();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                observableList.add(resultSet.getString("username"));
                userID = resultSet.getInt("userID");
            }


        }

        catch (SQLException e){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        listView.setItems(observableList);
    }
}
