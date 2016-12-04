package SalarySheet;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by famed on 5/25/16.
 */
public class DeleteRow implements Initializable{
    @FXML
    private TextField date;

    @FXML
    ListView<String> listView = new ListView<>();
    CommonData commonData = new CommonData();

    public String empname = null;

    private Connection connection;

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @FXML
    Button deleteEmpButton;
    @FXML
    Button deleteSalaryButton;
    @FXML
    Label label;
    @FXML
    private TextField getDate;
    LocalDate date1 = LocalDate.now();
    String getMonth = date1.getMonthValue() + "/"+date1.getYear();
    void deleteSalary(){


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonData.employeeList();

        listView.setItems(commonData.observableList);
        listView.setStyle("-fx-font-size: 14pt;-fx-border-color: orangered");

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==1){
                    empname = listView.getSelectionModel().getSelectedItem();


                }
            }
        });


        deleteEmpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String query = "DELETE FROM employee WHERE emp_name=?";
                deleEmp(query);
            }
        });
        deleteSalaryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                deleSalary();
            }
        });

    }

    public void deleEmp(String query){


        String myQuery="DELETE FROM employee WHERE emp_name=?";


        try {

            connection=SqlConnect.con();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,empname);

            preparedStatement.executeUpdate();
            label.setText("The employee has been deleted !");
            label.setStyle("-fx-border-color: red;-fx-font-size: 100%");
            listView.getItems().remove(empname);


        }
        catch (SQLException e){

            commonData.CustomException(e);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void deleSalary(){


        String myQuery="DELETE FROM mainsalary WHERE emp_id=? and salarydate LIKE ? ";
        String getempID = "Select emp_id from employee where emp_name="+"'"+empname+"'";
        int id = 0;

        try {
            connection=SqlConnect.con();
            preparedStatement = connection.prepareStatement(getempID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                id = resultSet.getInt("emp_id");
            }
            System.out.println(id);
            //preparedStatement.close();

            preparedStatement = connection.prepareStatement(myQuery);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,"%"+getMonth);

            preparedStatement.executeUpdate();
            label.setText("Record deleted !");
            label.setStyle("-fx-border-color: white;-fx-font-size: 100%;-fx-background-color: darkred;-fx-text-fill: white");


        }
        catch (SQLException e){

            System.out.println(e);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
