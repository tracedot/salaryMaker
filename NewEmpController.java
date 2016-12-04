package SalarySheet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.*;
/**
 * Created by famed on 5/21/16.
 */
public class NewEmpController implements Initializable{


    @FXML
    private ListView<String> empName;

    //Update
    @FXML
    private TextField Emp;
    @FXML
    private TextField Post;
    @FXML
    private TextField WorkPlace;
    @FXML
    private TextField office;
    @FXML
    private TextField HomeFare;
    @FXML
    private TextField GenFund;
    @FXML
    private TextField GpNo;
    @FXML
    Button cancelButton;

    @FXML
    private TabPane tabPane;

    @FXML
    private Label notify;
    @FXML
    private Label notify2;

    ObservableList<String> observableList = FXCollections.observableArrayList();
    List<String> list = new ArrayList<>();
    public String myQuery = null;
    public ResultSet sqlResultSet = null;
    public PreparedStatement prpStatement = null;
    private Connection connection;
    private String item;
    CommonData commonData = new CommonData();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CallMe();
        CException();
        empName.setStyle("-fx-background-color: beige");


        empName.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


                if(event.getClickCount()==1){

                    item = empName.getSelectionModel().getSelectedItem();
                    String myQuery="SELECT emp_name,emp_post,emp_work_place,officePlace,home_fare_percent,gen_fund_percent,gpno from employee WHERE emp_name="+"'"+item+"';";

                    try {

                        System.out.println("Executing Sql Command:");
                        connection = SqlConnect.con();
                        prpStatement = connection.prepareStatement(myQuery);
                        sqlResultSet = prpStatement.executeQuery();

                        while (sqlResultSet.next()){
                            list.add(sqlResultSet.getString("emp_name"));
                            Emp.setText(sqlResultSet.getString("emp_name"));
                            Post.setText(sqlResultSet.getString("emp_post"));
                            WorkPlace.setText(sqlResultSet.getString("emp_work_place"));
                            office.setText(sqlResultSet.getString("officePlace"));
                            HomeFare.setText(sqlResultSet.getString("home_fare_percent"));
                            GenFund.setText(sqlResultSet.getString("gen_fund_percent"));
                            GpNo.setText(sqlResultSet.getString("gpno"));
                        }



                    }
                    catch (SQLException e){

                        System.out.println(e);

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        if(connection!=null){

                            try {
                                commonData.CloseCon();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }


            }
        });


    }



    public void newemployee(ActionEvent event){
        if(Emp.getText().isEmpty() || Post.getText().isEmpty()|| office.getText().isEmpty()||
                WorkPlace.getText().isEmpty()||GenFund.getText().isEmpty()||HomeFare.getText().isEmpty()||GpNo.getText().isEmpty()){
            notify2.setText("Fill all required Fields!");
            notify2.setStyle("-fx-border-color: white; -fx-animated: true;-fx-background-color: darkred;-fx-text-fill: white");
            Emp.setStyle("-fx-border-color: red");
            Post.setStyle("-fx-border-color: red");
            office.setStyle("-fx-border-color: red");
            WorkPlace.setStyle("-fx-border-color: red");
            GenFund.setStyle("-fx-border-color: red");
            HomeFare.setStyle("-fx-border-color: red");
            GenFund.setStyle("-fx-border-color: red");
            GpNo.setStyle("-fx-border-color: red");
            return;
        }

        else {
            try{

                String insertQuery = null;
                connection = SqlConnect.con();
                myQuery = "select * from employee where emp_name like"+"'%"+Emp.getText()+"'";
                Statement statement = connection.createStatement();
                sqlResultSet = statement.executeQuery(myQuery);
                if(sqlResultSet.next()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Duplicate Data!");
                    alert.setContentText("The Employee Already Exist!");

                    alert.showAndWait();
                    return;
                }
                else {


                    insertQuery = "INSERT INTO employee(emp_name,emp_post,emp_work_place,officePlace,home_fare_percent,gen_fund_percent,gpno) VALUES(?,?,?,?,?,?,?)";
                    prpStatement = connection.prepareStatement(insertQuery);
                    prpStatement.setString(1,Emp.getText());
                    prpStatement.setString(2,Post.getText());
                    prpStatement.setString(3,WorkPlace.getText());
                    prpStatement.setString(4,office.getText());
                    prpStatement.setInt(5,Integer.parseInt(HomeFare.getText()));
                    prpStatement.setInt(6,Integer.parseInt(GenFund.getText()));
                    prpStatement.setInt(7,Integer.parseInt(GpNo.getText()));

                    prpStatement.executeUpdate();
                    notify2.setText("New employee Added Successfully!");
                    notify2.setStyle("-fx-border-color: black; -fx-animated: true;-fx-background-color: darkred;-fx-text-fill: white");
                }

            }

            catch (SQLException e1e){
                commonData.CustomException(e1e);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    commonData.CloseCon();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            empName.getItems().clear();
            CallMe();
        }

    }

    public void updateemployee(ActionEvent event) {
        try{

            connection = SqlConnect.con();
            String updateQuery = "UPDATE employee SET emp_name=?,emp_post=?,emp_work_place=?,officePlace=?,home_fare_percent=?,gen_fund_percent=?,gpno=? WHERE emp_name="+"'"+item+"'";
            prpStatement = connection.prepareStatement(updateQuery);


            prpStatement.setString(1,Emp.getText());
            prpStatement.setString(2,Post.getText());
            prpStatement.setString(3,WorkPlace.getText());
            prpStatement.setString(4,office.getText());
            prpStatement.setInt(5,Integer.parseInt(HomeFare.getText()));
            prpStatement.setInt(6,Integer.parseInt(GenFund.getText()));
            prpStatement.setInt(7,Integer.parseInt(GpNo.getText()));
            prpStatement.executeUpdate();


            notify2.setText("Updated Successfully!");
            notify2.setStyle("-fx-border-color: black; -fx-animated: true;-fx-background-color: darkred;-fx-text-fill: white");

        }

        catch (SQLException e1e){

            commonData.CustomException(e1e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            try {
                commonData.CloseCon();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        empName.getItems().clear();
        CallMe();

    }

    public void CallMe() {
        String sqlQuery = "SELECT emp_name,emp_id FROM employee";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = SqlConnect.con();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                observableList.add(resultSet.getString(1));


            }


        }
        catch (SQLException e){
            commonData.CustomException(e);
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                commonData.CloseCon();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



        empName.setItems(observableList);
    }

    public void CException(){
        HomeFare.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    HomeFare.setText(newValue.replaceAll("[^\\d]", ""));
                }

            }
        });
        GenFund.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    GenFund.setText(newValue.replaceAll("[^\\d]", ""));
                }

            }
        });
        GpNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    GpNo.setText(newValue.replaceAll("[^\\d]", ""));
                }

            }
        });
    }

    public void IfEmpty(){


    }

    public void Close(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}


