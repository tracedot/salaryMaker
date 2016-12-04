package SalarySheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JRException;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.control.ButtonType.*;

/**
 * Created by famed on 6/7/16.
 */
public class UpSalaryController implements Initializable{
    ObservableList observableList = FXCollections.observableArrayList("January","February","March",
            "Appril","May","June","July",
            "August","September","October",
            "November","December");
    private Connection connection;
    @FXML
    private ListView<String> empl;
    @FXML
    private ListView<String> months;
    @FXML
    GridPane gridPane;

    CommonData commonData = new CommonData();
    public String empName = null;
    public int getID = 0;
    public String sQuery = null;
    LocalDate localDate = LocalDate.now();
    private int year = localDate.getYear();
    private String listCurrentItem = null;
    private String getMonth = null;
    ResultSet sqlResultSet;


    //TextFields IDs


    @FXML
    private TextField salaryBasicl;
    @FXML
    private TextField homel;
    @FXML
    private TextField treatementl;
    @FXML
    private TextField tiffinl;
    @FXML
    private TextField educationl;
    @FXML
    private TextField mohargol;
    @FXML
    private TextField washingl;
    @FXML
    private TextField genwelfareFundl;
    @FXML
    private TextField welfarefundl;
    @FXML
    private TextField genwelfarepaidl;
    @FXML
    private TextField genwelfarepaid1l;
    @FXML
    private TextField genwelfarepaid2l;
    @FXML
    private TextField homefaredeductl;
    @FXML
    private TextField homedeductadvinstalll;
    @FXML
    private TextField bikedeductl;
    @FXML
    private TextField salaryTotall;
    @FXML
    private TextField homeConInstalll;
    @FXML
    private TextField firstInstallmentl;
    @FXML
    private TextField secInstallmentl;
    @FXML
    private TextField thrdInstallmentl;
    @FXML
    private TextField totalDeductionl;
    @FXML
    private TextField totalDemandl;
    @FXML
    private Label notify;
    @FXML
    private Button cancel;
    @FXML
    private VBox vBox;

    public int homeFare=0;
    public int deduction = 0;
    public int genFund=0;
    public int salary = 0;
    public int homeFared = 0;
    public String employeePost = null;
    public int getEmp_id = 0;
    String item = null;
    private int plusdeduct = 0;
    PreparedStatement preparedStatement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notify.setStyle("-fx-font-size: 15pt;-fx-text-fill: blue");
        commonData.employeeList();
        ClearText();
        empl.setItems(commonData.observableList);
        months.setItems(observableList);
        textListener();
        months.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==1){
                    getMonth = months.getSelectionModel().getSelectedItem();
                    Months();
                    LoadData();

                }
            }
        });
        empl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==1){
                    empName = empl.getSelectionModel().getSelectedItem();
                    item = empl.getSelectionModel().getSelectedItem();



                    String myQuery="SELECT emp_id,home_fare_percent,gen_fund_percent,emp_post from employee WHERE emp_name="+"'"+item+"';";

                    //System.out.println(myQuery);
                    try {
                        PreparedStatement prpStatement;
                        System.out.println("Executing Sql Command:");
                        connection = SqlConnect.con();
                        prpStatement = connection.prepareStatement(myQuery);
                        sqlResultSet = prpStatement.executeQuery();


                        while (sqlResultSet.next()){


                            homeFare =sqlResultSet.getInt("home_fare_percent");
                            genFund =sqlResultSet.getInt("gen_fund_percent");
                            employeePost=sqlResultSet.getString("emp_post");

                            getEmp_id = sqlResultSet.getInt("emp_id");



                        }
                        System.out.println(getEmp_id);



                    }
                    catch (SQLException e){

                        System.out.println(e);

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

                }
            }
        });
        Shortcut();

    }

    public void CancelButton(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText("Are you sure?");
        Optional<ButtonType> response= alert.showAndWait();
        if(response.isPresent() && response.get() == ButtonType.OK){
            ((Node)actionEvent.getSource()).getScene().getWindow().hide();
        }
    }

    public void LoadData(){
        int value = 0;
        sQuery="select employee.emp_id,employee.emp_name,mainsalary.salary_basic,mainsalary.home_allowance,mainsalary.treatment_allowance,\n" +
                "\tmainsalary.tiffin_allowance,mainsalary.education_allowance,mainsalary.mohargo_allowance,mainsalary.washing_allowance,mainsalary.salaryTotal,\n" +
                "    mainsalary.gen_welfare_fund,mainsalary.welfare_fund,mainsalary.gen_welfare_fund_paid,mainsalary.gen_welfare_fund_paid_one,mainsalary.gen_welfare_fund_paid_two,mainsalary.home_fare_deduct,\n" +
                "    mainsalary.home_deduct_adv_installment,mainsalary.bike_deduct,mainsalary.salarydate,mainsalary.home_const_installment,mainsalary.first_installment, \n" +
                "mainsalary.sec_installment,mainsalary.thrd_installment,mainsalary.total_deduction,mainsalary.total_demand\n" +
                "\tfrom mainsalary inner JOIN employee ON mainsalary.emp_id=employee.emp_id " + " where employee.emp_name LIKE "+"'%"+empName+"'"+
                " and mainsalary.salarydate like " + "'%"+getMonth+"'"+";";
        //System.out.println(sQuery);
        try {

            connection = SqlConnect.con();
            Statement statement = connection.createStatement();
            sqlResultSet = statement.executeQuery(sQuery);
            ClearText();

            while (sqlResultSet.next()){

                salaryBasicl.setText(String.valueOf(sqlResultSet.getInt("salary_basic")));
                homel.setText(String.valueOf(sqlResultSet.getInt("home_allowance")));
                treatementl.setText(String.valueOf(sqlResultSet.getInt("treatment_allowance")));
                tiffinl.setText(String.valueOf(sqlResultSet.getInt("tiffin_allowance")));
                educationl.setText(String.valueOf(sqlResultSet.getInt("education_allowance")));
                mohargol.setText(String.valueOf(sqlResultSet.getInt("mohargo_allowance")));
                washingl.setText(String.valueOf(sqlResultSet.getInt("washing_allowance")));
                salaryTotall.setText(String.valueOf(sqlResultSet.getInt("salaryTotal")));

                //Deduction
                genwelfareFundl.setText(String.valueOf(sqlResultSet.getInt("gen_welfare_fund")));
                welfarefundl.setText(String.valueOf(sqlResultSet.getInt("welfare_fund")));
                genwelfarepaidl.setText(String.valueOf(sqlResultSet.getInt("gen_welfare_fund_paid")));
                genwelfarepaid1l.setText(String.valueOf(sqlResultSet.getInt("gen_welfare_fund_paid_one")));
                genwelfarepaid2l.setText(String.valueOf(sqlResultSet.getInt("gen_welfare_fund_paid_two")));
                homefaredeductl.setText(String.valueOf(sqlResultSet.getInt("home_fare_deduct")));
                homedeductadvinstalll.setText(String.valueOf(sqlResultSet.getInt("home_deduct_adv_installment")));
                bikedeductl.setText(String.valueOf(sqlResultSet.getInt("bike_deduct")));
                homeConInstalll.setText(sqlResultSet.getString("home_const_installment"));
                firstInstallmentl.setText(sqlResultSet.getString("first_installment"));
                secInstallmentl.setText(sqlResultSet.getString("sec_installment"));
                thrdInstallmentl.setText(sqlResultSet.getString("thrd_installment"));
                totalDeductionl.setText(String.valueOf(sqlResultSet.getInt("total_deduction")));
                totalDemandl.setText(String.valueOf(sqlResultSet.getInt("total_demand")));

                System.out.println("First Installment:"+String.valueOf(sqlResultSet.getInt("first_installment")));


            }





        }

        catch (SQLException e){

            System.out.println(e);

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

    }

    public void Update(ActionEvent actionEvent){

        sQuery="UPDATE mainsalary SET salary_basic=?,home_allowance=?,treatment_allowance=?,\n" +
                "\ttiffin_allowance=?,education_allowance=?,mohargo_allowance=?,washing_allowance=?,salaryTotal=?,\n" +
                " gen_welfare_fund=?,welfare_fund=?,gen_welfare_fund_paid=?,gen_welfare_fund_paid_one=?,gen_welfare_fund_paid_two=?,home_fare_deduct=?,\n" +
                " home_deduct_adv_installment=?,bike_deduct=?,home_const_installment=?,first_installment=?, \n" +
                "sec_installment=?,thrd_installment=?,total_deduction=?,total_demand=?\n" +
                "\twhere emp_id=? and salarydate like " + "'%"+getMonth+"'"+";";

        sQuery = "UPDATE mainsalary set salary_basic=?," +
                "home_allowance=?," +
                "treatment_allowance=?," +
                "tiffin_allowance=?," +
                "education_allowance=?," +
                "mohargo_allowance=?," +
                "washing_allowance=?," +
                "salaryTotal=?," + //8
                "gen_welfare_fund=?," +
                "welfare_fund=?," +
                "gen_welfare_fund_paid=?," +
                "gen_welfare_fund_paid_one=?," +
                "gen_welfare_fund_paid_two=?," +
                "home_fare_deduct=?," +
                "home_deduct_adv_installment=?," +
                "bike_deduct=?," +
                "home_const_installment=?," +
                "first_installment=?," +
                "sec_installment=?," +
                "thrd_installment=?," +
                "total_deduction=?," +
                "total_demand=?" +
                " where emp_id = ? and salarydate like  ?";


        try {
            connection= SqlConnect.con();
            preparedStatement=connection.prepareStatement(sQuery);
            preparedStatement.setInt(1,Integer.parseInt(salaryBasicl.getText()));
            preparedStatement.setInt(2,Integer.parseInt(homel.getText()));
            preparedStatement.setInt(3,Integer.parseInt(treatementl.getText()));
            preparedStatement.setInt(4,Integer.parseInt(tiffinl.getText()));
            preparedStatement.setInt(5,Integer.parseInt(educationl.getText()));
            preparedStatement.setInt(6,Integer.parseInt(mohargol.getText()));
            preparedStatement.setInt(7,Integer.parseInt(washingl.getText()));
            preparedStatement.setInt(8,Integer.parseInt(salaryTotall.getText()));


            preparedStatement.setInt(9,Integer.parseInt(genwelfareFundl.getText()));
            preparedStatement.setInt(10,Integer.parseInt(welfarefundl.getText()));
            preparedStatement.setInt(11,Integer.parseInt(genwelfarepaidl.getText()));
            preparedStatement.setInt(12,Integer.parseInt(genwelfarepaid2l.getText()));
            preparedStatement.setInt(13,Integer.parseInt(genwelfarepaid2l.getText()));
            preparedStatement.setInt(14,Integer.parseInt(homefaredeductl.getText()));
            preparedStatement.setInt(15,Integer.parseInt(homedeductadvinstalll.getText()));
            preparedStatement.setInt(16,Integer.parseInt(bikedeductl.getText()));


            preparedStatement.setString(17,homeConInstalll.getText());
            preparedStatement.setString(18,firstInstallmentl.getText());
            preparedStatement.setString(19,secInstallmentl.getText());
            preparedStatement.setString(20,thrdInstallmentl.getText());
            preparedStatement.setInt(21,Integer.parseInt(totalDeductionl.getText()));
            preparedStatement.setInt(22,Integer.parseInt(totalDemandl.getText()));
            preparedStatement.setInt(23,getEmp_id);
            preparedStatement.setString(24,"%"+getMonth);
            System.out.println(sQuery);

            int row = preparedStatement.executeUpdate();

            if (row!=0){

                notify.setText(empName + " of " + getMonth + " has been updated!");
                notify.setStyle("-fx-font-size: 15pt;-fx-border-color: white;-fx-text-fill: darkred");

            }
            else {
                notify.setText("Something Wrong!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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


    }

    public void Months(){
        switch (getMonth){
            case "January":
                getMonth = "/"+ 1+"/"+year;
                break;
            case "February":
                getMonth = "/"+  2+"/"+year;
                break;
            case "March":
                getMonth = "/"+  3+"/"+year;
                break;
            case "Appril":
                getMonth = "/"+  4+"/"+year;
                break;
            case "May":
                getMonth = "/"+  5+"/"+year;
                break;
            case "June":
                getMonth = "/"+  6+"/"+year;
                break;
            case "July":
                getMonth = "/"+  7+"/"+year;
                break;
            case "August":
                getMonth = "/"+  8+"/"+year;
                break;
            case "September":
                getMonth = "/"+  9+"/"+year;
                break;
            case "October":
                getMonth = "/"+ 10+"/"+year;
                break;
            case "November":
                getMonth = "/"+ 11+"/"+year;
                break;
            case "December":
                getMonth = "/"+ 12+"/"+year;
                break;

        }

        System.out.println("Date: "+getMonth + "Emp:"+empName);
    }

    public void ClearText(){


        salaryBasicl.setText("0");
        homel.setText("0");
        treatementl.setText("0");
        tiffinl.setText("0");
        educationl.setText("0");
        mohargol.setText("0");
        washingl.setText("0");
        salaryTotall.setText("0");
        genwelfareFundl.setText("0");
        welfarefundl.setText("0");
        genwelfarepaidl.setText("0");
        genwelfarepaid1l.setText("0");
        genwelfarepaid2l.setText("0");
        homefaredeductl.setText("0");
        homedeductadvinstalll.setText("0");
        bikedeductl.setText("0");
        homeConInstalll.setText("0");
        firstInstallmentl.setText("0");
        secInstallmentl.setText("0");
        secInstallmentl.setText("0");
        thrdInstallmentl.setText("0");
        totalDeductionl.setText("0");
        totalDemandl.setText("0");
    }

    public void CalculateFare(){
        homeFared = Integer.parseInt(salaryBasicl.getText())*homeFare/100;
        homel.setText(String.valueOf(homeFared));

    }

    public void CalculateDeduct(){
        deduction = Integer.parseInt(salaryBasicl.getText())*genFund/100;
        genwelfareFundl.setText(String.valueOf(deduction));
    }

    public void Calculate(){

        //Get the integer to calculate
        //The Salary
        int a = Integer.parseInt(salaryBasicl.getText());
        int b = Integer.parseInt(homel.getText());
        int c = Integer.parseInt(treatementl.getText());
        int d = Integer.parseInt(tiffinl.getText());
        int e = Integer.parseInt(educationl.getText());
        int f = Integer.parseInt(mohargol.getText());
        int g = Integer.parseInt(washingl.getText());
        int plus = a+b+c+d+e+f+g;
        salaryTotall.setText(Integer.toString(plus));
        // CalculateFare();


        //The Deduction


        int h = Integer.parseInt(String.valueOf(genwelfareFundl.getText()));
        int i = Integer.parseInt(String.valueOf(welfarefundl.getText()));
        int j = Integer.parseInt(String.valueOf(genwelfarepaidl.getText()));
        int k = Integer.parseInt(String.valueOf(homefaredeductl.getText()));
        int l = Integer.parseInt(String.valueOf(homedeductadvinstalll.getText()));
        int m = Integer.parseInt(String.valueOf(bikedeductl.getText()));
        int n = Integer.parseInt(String.valueOf(genwelfarepaid1l.getText()));
        int o = Integer.parseInt(String.valueOf(genwelfarepaid2l.getText()));
        plusdeduct = h+i+j+k+l+m+n+o;
        totalDeductionl.setText(Integer.toString(plusdeduct));
        totalDemandl.setText(String.valueOf(Integer.parseInt(String.valueOf(salaryTotall.getText()))-Integer.parseInt(String.valueOf(totalDeductionl.getText()))));


    }

    public void textListener(){
        salaryBasicl.textProperty().addListener((observable, oldValue, newValue) -> {

            Calculate();
            CalculateFare();
            CalculateDeduct();

        });
        homel.textProperty().addListener((observable, oldValue, newValue) -> {

            //CalculateFare();
            Calculate();

        });
        treatementl.textProperty().addListener((observable, oldValue, newValue) -> {

            Calculate();
        });
        tiffinl.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        educationl.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        mohargol.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        washingl.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });

        //Deduction

        genwelfareFundl.textProperty().addListener((observable, oldValue, newValue) -> {

            Calculate();
            //CalculateDeduct();
        });
        welfarefundl.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        genwelfarepaidl.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        genwelfarepaid1l.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        genwelfarepaid2l.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        homefaredeductl.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        homedeductadvinstalll.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        bikedeductl.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
    }

    public void Shortcut(){
        vBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(empl.getSelectionModel().getSelectedItem()!=null){
                    if(empl.getSelectionModel().getSelectedItem()!=null && months.getSelectionModel().getSelectedItem() !=null){
                        if(event.getCode() == KeyCode.S && event.isControlDown()){
                            Update(null);
                        }
                    }
                    else if(empl.getSelectionModel().getSelectedItem()==null){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Please select an Employee First!\nAlso Make sure you are inserting\ndata for all fields!");
                        alert.showAndWait();
                    }
                    else if(months.getSelectionModel().getSelectedItem()==null){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Without selecting a month how do you modify data?\nPlease select a month!");
                        alert.showAndWait();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("There is something wrong! Contact your administrator!");
                        alert.showAndWait();
                    }
                }
            }
        });
    }
}

