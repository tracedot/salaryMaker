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
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.exolab.castor.types.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by famed on 5/6/16.
 */
public class SalaryEntryController implements Initializable{
    private Connection connection;
    ConnectionError connectionError = new ConnectionError();

    ObservableList<String> observableList = FXCollections.observableArrayList();
    List<Integer> salaryDataSql = new ArrayList<>();


    public String myQuery = null;

    public ResultSet sqlResultSet = null;
    public PreparedStatement prpStatement = null;

    public String date = null;

    @FXML
    private TextField empPost;

    @FXML
    private TextField salaryBasic;
    @FXML
    private TextField home;
    @FXML
    private TextField treatement;
    @FXML
    private TextField tiffin;
    @FXML
    private TextField education;
    @FXML
    private TextField mohargo;
    @FXML
    private TextField washing;
    @FXML
    private TextField genwelfareFund;
    @FXML
    private TextField welfarefund;
    @FXML
    private TextField genwelfarepaid;
    @FXML
    public TextField genwelfarepaid1;
    @FXML
    public TextField genwelfarepaid2;
    @FXML
    private TextField homefarededuct;
    @FXML
    private TextField homedeductadvinstall;
    @FXML
    private TextField bikededuct;
    @FXML
    private TextField salaryTotal;
    @FXML
    private TextField homeConInstall;
    @FXML
    private TextField firstInstallment;
    @FXML
    private TextField secInstallment;
    @FXML
    private TextField thrdInstallment;
    @FXML
    private TextField totalDeduction;
    @FXML
    private TextField totalDemand;
    @FXML
    public TextField datepicker;


    @FXML
    private Label updateNotify;
    @FXML
    private Button updateButton;
    @FXML
    public Label employeeName;
    @FXML
    public ListView<String> listViewEmp;
    CommonData commonData  = new CommonData();

    @FXML
    private VBox vBox;
    @FXML private ComboBox comboBox;
    @FXML Button save;


    List<Integer> emp_id = new ArrayList<>();

    private int homeFare=0;
    private int deduction = 0;
    private int genFund=0;
    private int salary = 0;
    private int homeFared = 0;
    private String employeePost = null;
    public int getEmp_id = 0;
    private int plusdeduct = 0;


    LocalDate date1 = LocalDate.now();
    private String getDate = date1.getDayOfMonth()+"/"+date1.getMonthValue()+"/"+date1.getYear();
    private String item = null;
    private String lastMonth =(date1.getMonthValue()-1)+"/"+date1.getYear();
    private String lastMY =(date1.getMonthValue()-1)+"/"+(date1.getYear()-1);

    KeyCodeCombination key = new KeyCodeCombination(KeyCode.U, KeyCombination.CONTROL_DOWN);

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //vBox.setStyle("-fx-background-color: aliceblue");

        salaryTotal.setStyle("-fx-text-fill: darkred;-fx-border-color: purple ");
        totalDemand.setStyle("-fx-text-fill: darkred;-fx-border-color: purple");
        totalDeduction.setStyle("-fx-text-fill: darkred;-fx-border-color: purple");


         String sqlQuery = "SELECT emp_name,emp_id FROM employee";
         ResultSet resultSet = null;
         PreparedStatement preparedStatement = null;
         try {
             connection = SqlConnect.con();
             preparedStatement = connection.prepareStatement(sqlQuery);
             resultSet = preparedStatement.executeQuery();

             while (resultSet.next()){
                 observableList.add(resultSet.getString(1));
                 emp_id.add(resultSet.getInt(2));

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



        
        listViewEmp.setItems(observableList);
        listViewEmp.setStyle("-fx-font-size: 14pt;-fx-border-color: orangered");



        if(salaryBasic.getText().isEmpty() || home.getText().isEmpty() || treatement.getText().isEmpty() ||
                tiffin.getText().isEmpty() || education.getText().isEmpty() || mohargo.getText().isEmpty() ||
                washing.getText().isEmpty()||genwelfareFund.getText().isEmpty()||welfarefund.getText().isEmpty()||genwelfarepaid.getText().isEmpty()||
                homefarededuct.getText().isEmpty()||homedeductadvinstall.getText().isEmpty()||bikededuct.getText().isEmpty()||
                homeConInstall.getText().isEmpty()||firstInstallment.getText().isEmpty()||secInstallment.getText().isEmpty()||
                thrdInstallment.getText().isEmpty()||totalDeduction.getText().isEmpty()||totalDemand.getText().isEmpty() ||
                genwelfarepaid1.getText().isEmpty()||genwelfarepaid2.getText().isEmpty()){


            defaultText();
            textListener();

        }


        else {
            textListener();

        }

        save.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                //System.out.println(event.getCode().getName());
                if(event.getCode() == KeyCode.ENTER){
                    try {
                        insertSalary();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }
        });


        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                defaultText();

            }
        });

        listViewEmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(event.getClickCount()==2){

                    item = listViewEmp.getSelectionModel().getSelectedItem();

                    if(item=="December"){
                       lastMonth = lastMY;
                        loadSqlData();
                    }
                    else {
                        //lastMonth=lastMonth;
                        defaultText();
                        loadSqlData();
                    }


                }

                if(event.getClickCount()==1){


                    item = listViewEmp.getSelectionModel().getSelectedItem();

                    String myQuery="SELECT emp_id,home_fare_percent,gen_fund_percent,emp_post from employee WHERE emp_name="+"'"+item+"';";

                    //System.out.println(myQuery);
                    try {
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
                        //System.out.println(getEmp_id);
                        empPost.setText(employeePost);


                    }
                    catch (SQLException e){

                        commonData.CustomException(e);

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

                    //System.out.println(item);
                }
                //System.out.println(lastMonth);

            }
        });

        vBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.S && event.isControlDown()){
                    try {
                        insertSalary();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //System.out.println(this.getClass().getCanonicalName());

    }



    private void defaultText() {

        datepicker.setText(getDate);

        salaryBasic.setText("0");
        home.setText("0");
        treatement.setText("0");
        tiffin.setText("0");
        education.setText("0");
        mohargo.setText("0");
        washing.setText("0");
        salaryTotal.setText("0");
        genwelfareFund.setText("0");
        welfarefund.setText("0");
        genwelfarepaid.setText("0");
        genwelfarepaid1.setText("0");
        genwelfarepaid2.setText("0");
        homefarededuct.setText("0");
        homedeductadvinstall.setText("0");
        bikededuct.setText("0");
        homeConInstall.setText("0");
        firstInstallment.setText("0");
        secInstallment.setText("0");
        secInstallment.setText("0");
        thrdInstallment.setText("0");
        totalDeduction.setText("0");
        totalDemand.setText("0");
    }



    public void loadSqlData() {

        item = listViewEmp.getSelectionModel().getSelectedItem();

        //date = datepicker.getText();
        PreparedStatement getPercent = null;


         myQuery="select employee.emp_id,employee.emp_name,mainsalary.salary_basic,mainsalary.home_allowance,mainsalary.treatment_allowance,\n" +
                "\tmainsalary.tiffin_allowance,mainsalary.education_allowance,mainsalary.mohargo_allowance,mainsalary.washing_allowance,mainsalary.salaryTotal,\n" +
                "    mainsalary.gen_welfare_fund,mainsalary.welfare_fund,mainsalary.gen_welfare_fund_paid,mainsalary.gen_welfare_fund_paid_one,mainsalary.gen_welfare_fund_paid_two,mainsalary.home_fare_deduct,\n" +
                "    mainsalary.home_deduct_adv_installment,mainsalary.bike_deduct,mainsalary.salarydate,mainsalary.home_const_installment,mainsalary.first_installment, \n" +
                "mainsalary.sec_installment,mainsalary.thrd_installment,mainsalary.total_deduction,mainsalary.total_demand\n" +
                "\tfrom mainsalary inner JOIN employee ON mainsalary.emp_id=employee.emp_id " + " where employee.emp_name LIKE "+"'%"+item+"'"+
                " and mainsalary.salarydate like " + "'%/"+lastMonth+"'"+";";

        System.out.println(myQuery);
        try {

            connection = SqlConnect.con();

            Statement statement = connection.createStatement();
            sqlResultSet = statement.executeQuery(myQuery);

            while (sqlResultSet.next()){


                salaryDataSql.add(sqlResultSet.getInt("salary_basic"));
                salaryDataSql.add(sqlResultSet.getInt("home_allowance"));
                salaryDataSql.add(sqlResultSet.getInt("treatment_allowance"));
                salaryDataSql.add(sqlResultSet.getInt("tiffin_allowance"));
                salaryDataSql.add(sqlResultSet.getInt("education_allowance"));
                salaryDataSql.add(sqlResultSet.getInt("mohargo_allowance"));
                salaryDataSql.add(sqlResultSet.getInt("washing_allowance"));
                salaryDataSql.add(sqlResultSet.getInt("salaryTotal"));
                salaryDataSql.add(sqlResultSet.getInt("gen_welfare_fund"));
                salaryDataSql.add(sqlResultSet.getInt("welfare_fund"));
                salaryDataSql.add(sqlResultSet.getInt("gen_welfare_fund_paid"));
                salaryDataSql.add(sqlResultSet.getInt("gen_welfare_fund_paid_one"));
                salaryDataSql.add(sqlResultSet.getInt("gen_welfare_fund_paid_two"));
                salaryDataSql.add(sqlResultSet.getInt("home_fare_deduct"));
                salaryDataSql.add(sqlResultSet.getInt("home_deduct_adv_installment"));
                salaryDataSql.add(sqlResultSet.getInt("bike_deduct"));
                salaryDataSql.add(sqlResultSet.getInt("emp_id"));


                 homeFared = Integer.parseInt(String.valueOf(sqlResultSet.getInt("salary_basic") *homeFare/100));
                System.out.println("Cal"+homeFared);
                salaryBasic.setText(String.valueOf(sqlResultSet.getInt("salary_basic")));
                home.setText(String.valueOf(homeFared));
                treatement.setText(String.valueOf(sqlResultSet.getInt("treatment_allowance")));
                tiffin.setText(String.valueOf(sqlResultSet.getInt("tiffin_allowance")));
                education.setText(String.valueOf(sqlResultSet.getInt("education_allowance")));
                mohargo.setText(String.valueOf(sqlResultSet.getInt("mohargo_allowance")));
                washing.setText(String.valueOf(sqlResultSet.getInt("washing_allowance")));
                salaryTotal.setText(String.valueOf(sqlResultSet.getInt("salaryTotal")));

                //Deduction
                genwelfareFund.setText(String.valueOf(sqlResultSet.getInt("gen_welfare_fund")));
                welfarefund.setText(String.valueOf(sqlResultSet.getInt("welfare_fund")));
                genwelfarepaid.setText(String.valueOf(sqlResultSet.getInt("gen_welfare_fund_paid")));
                genwelfarepaid1.setText(String.valueOf(sqlResultSet.getInt("gen_welfare_fund_paid_one")));
                genwelfarepaid2.setText(String.valueOf(sqlResultSet.getInt("gen_welfare_fund_paid_two")));
                homefarededuct.setText(String.valueOf(sqlResultSet.getInt("home_fare_deduct")));
                homedeductadvinstall.setText(String.valueOf(sqlResultSet.getInt("home_deduct_adv_installment")));
                bikededuct.setText(String.valueOf(sqlResultSet.getInt("bike_deduct")));
                homeConInstall.setText(sqlResultSet.getString("home_const_installment"));
                firstInstallment.setText(sqlResultSet.getString("first_installment"));
                secInstallment.setText(sqlResultSet.getString("sec_installment"));
                thrdInstallment.setText(sqlResultSet.getString("thrd_installment"));
                totalDeduction.setText(String.valueOf(sqlResultSet.getInt("total_deduction")));
                totalDemand.setText(String.valueOf(sqlResultSet.getInt("total_demand")));


                if(comboBox.getSelectionModel().getSelectedItem()=="Update"){
                    updateNotify.setText("Update: "+sqlResultSet.getString("salayDate"));

                }

            }

        }

        catch (SQLException e){

            commonData.CustomException(e);

        } catch (ClassNotFoundException e) {
            commonData.CustomException(e);
        }
        finally {
            try {
                commonData.CloseCon();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void CalculateFare(){
        homeFared = Integer.parseInt(salaryBasic.getText())*homeFare/100;
        home.setText(String.valueOf(homeFared));

    }

    public void CalculateDeduct(){
        deduction = Integer.parseInt(salaryBasic.getText())*genFund/100;
        genwelfareFund.setText(String.valueOf(deduction));
    }

    public void Calculate(){

        //Get the integer to calculate
        //The Salary
        int a = Integer.parseInt(salaryBasic.getText());
        int b = Integer.parseInt(home.getText());
        int c = Integer.parseInt(treatement.getText());
        int d = Integer.parseInt(tiffin.getText());
        int e = Integer.parseInt(education.getText());
        int f = Integer.parseInt(mohargo.getText());
        int g = Integer.parseInt(washing.getText());
        int plus = a+b+c+d+e+f+g;
        salaryTotal.setText(Integer.toString(plus));
       // CalculateFare();


        //The Deduction


        int h = Integer.parseInt(String.valueOf(genwelfareFund.getText()));
        int i = Integer.parseInt(welfarefund.getText());
        int j = Integer.parseInt(genwelfarepaid.getText());
        int k = Integer.parseInt(homefarededuct.getText());
        int l = Integer.parseInt(homedeductadvinstall.getText());
        int m = Integer.parseInt(bikededuct.getText());
        int n = Integer.parseInt(genwelfarepaid1.getText());
        int o = Integer.parseInt(genwelfarepaid2.getText());
        plusdeduct = h+i+j+k+l+m+n+o;
        totalDeduction.setText(Integer.toString(plusdeduct));
        totalDemand.setText(String.valueOf(Integer.parseInt(String.valueOf(salaryTotal.getText()))-Integer.parseInt(String.valueOf(totalDeduction.getText()))));


    }

    public void textListener(){
        salaryBasic.textProperty().addListener((observable, oldValue, newValue) -> {
            updateNotify.setText("You are inserting new Data!");
            updateNotify.setStyle("-fx-border-color: red");

            Calculate();
            CalculateFare();
            CalculateDeduct();

        });
        home.textProperty().addListener((observable, oldValue, newValue) -> {


            Calculate();

        });
        treatement.textProperty().addListener((observable, oldValue, newValue) -> {

            Calculate();
        });
        tiffin.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        education.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        mohargo.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        washing.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });

        //Deduction

        genwelfareFund.textProperty().addListener((observable, oldValue, newValue) -> {

            Calculate();
            //CalculateDeduct();
        });
        welfarefund.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        genwelfarepaid.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        genwelfarepaid1.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        genwelfarepaid2.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        homefarededuct.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        homedeductadvinstall.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
        bikededuct.textProperty().addListener((observable, oldValue, newValue) -> {
            Calculate();
        });
    }

    public void insertSalary() throws SQLException {



            int a = Integer.parseInt(salaryBasic.getText());
            int b = Integer.parseInt(home.getText());
            int c = Integer.parseInt(treatement.getText());
            int d = Integer.parseInt(tiffin.getText());
            int e = Integer.parseInt(education.getText());
            int f = Integer.parseInt(mohargo.getText());
            int g = Integer.parseInt(washing.getText());

            int h = Integer.parseInt(genwelfareFund.getText());
            int i = Integer.parseInt(welfarefund.getText());
            int j = Integer.parseInt(genwelfarepaid.getText());
            int k = Integer.parseInt(homefarededuct.getText());
            int l = Integer.parseInt(homedeductadvinstall.getText());
            int m = Integer.parseInt(bikededuct.getText());





            try{

                date = datepicker.getText();
                connection = SqlConnect.con();
                PreparedStatement id=null;
                CommonData commonData = new CommonData();
                ResultSet id1 =null;
                String selectID="select emp_id from employee where emp_name=" +"'"+item+"'";
                id = connection.prepareCall(selectID);

                id1 = id.executeQuery();

                while(id1.next())
                {
                    emp_id.add(id1.getInt("emp_id"));
                }

                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;



                String insertQuery = "INSERT INTO mainsalary(salary_basic,home_allowance,treatment_allowance,tiffin_allowance,\n" +
                        "\teducation_allowance,mohargo_allowance,washing_allowance,emp_id,gen_welfare_fund,welfare_fund,\n" +
                        "\tgen_welfare_fund_paid,home_fare_deduct,home_deduct_adv_installment,bike_deduct,salarydate,home_const_installment," +
                        "first_installment,sec_installment,thrd_installment,total_deduction,total_demand,salaryTotal,gen_welfare_fund_paid_one,gen_welfare_fund_paid_two) \n" +
                        "\tVALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(insertQuery);

                preparedStatement.setInt(1,a);
                preparedStatement.setInt(2,b);
                preparedStatement.setInt(3,c);
                preparedStatement.setInt(4,d);
                preparedStatement.setInt(5,e);
                preparedStatement.setInt(6,f);
                preparedStatement.setInt(7,g);
                preparedStatement.setInt(8,getEmp_id);

                preparedStatement.setInt(9,Integer.parseInt(genwelfareFund.getText()));
                preparedStatement.setInt(10,Integer.parseInt(welfarefund.getText()));
                preparedStatement.setInt(11,Integer.parseInt(genwelfarepaid.getText()));
                preparedStatement.setInt(12,Integer.parseInt(homefarededuct.getText()));
                preparedStatement.setInt(13,Integer.parseInt(homedeductadvinstall.getText()));
                preparedStatement.setInt(14,Integer.parseInt(bikededuct.getText()));
                preparedStatement.setString(15,date.toString());

                preparedStatement.setString(16,homeConInstall.getText());
                preparedStatement.setString(17,firstInstallment.getText());
                preparedStatement.setString(18,secInstallment.getText());
                preparedStatement.setString(19,thrdInstallment.getText());
                preparedStatement.setInt(20,Integer.parseInt(totalDeduction.getText()));
                preparedStatement.setInt(21,Integer.parseInt(totalDemand.getText()));
                preparedStatement.setInt(22,Integer.parseInt(salaryTotal.getText()));
                preparedStatement.setInt(23,Integer.parseInt(genwelfarepaid1.getText()));
                preparedStatement.setInt(24,Integer.parseInt(genwelfarepaid2.getText()));


                LocalDate localDate = LocalDate.now();
                String date ="/" + localDate.getMonthValue() + "/" + localDate.getYear();
                System.out.println(date);
                int inserted = 0;
                commonData.empExist(getEmp_id,date);
                if(commonData.info=="OK"){
                    inserted = preparedStatement.executeUpdate();

                    //System.out.println(commonData.loginFailed);
                    if(inserted != 0){
                        updateNotify.setText("Data Inserted Successfully!");
                        updateNotify.setStyle("-fx-border-color: green;-fx-font-weight: bold;-fx-font-size: 15pt");
                    }
                    else {
                        updateNotify.setText("Not Inserted!");
                    }
                }
                else {
                    connectionError.dataExist(item,date);
                }



            }

            catch (SQLException e1e){
                commonData.CustomException(e1e);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            finally {
                try {
                    commonData.CloseCon();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }



    }



}
