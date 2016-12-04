package SalarySheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.sql.*;

/**
 * Created by famed on 5/25/16.
 */
public class CommonData {
    private Connection connection;
    ObservableList<String> observableList= FXCollections.observableArrayList();
    public int getID = 0;
    private String sqlQuery = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    public String loginFailed = null;
    public String info = null;

    public void employeeList(){
        sqlQuery = "SELECT emp_name,emp_id FROM employee";
        try {
            connection = SqlConnect.con();
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                observableList.add(resultSet.getString(1));
                //emp_id.add(resultSet.getInt(2));
                getID = resultSet.getInt("emp_id");

            }

        }
        catch (SQLException e){
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean SetupDB() throws ClassNotFoundException, SQLException {
        String dropEmp = "DROP TABLE IF EXISTS `employee`;";
        String dropSalary = "DROP TABLE IF EXISTS `mainsalary`;";
        String dropoffice = "DROP TABLE IF EXISTS `office`;";
        String dropusers = "DROP TABLE IF EXISTS `users`;";


        String salaryTable = "CREATE TABLE MAINSALARY\n" +
                "(\n" +
                "    ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    SALARY_BASIC INTEGER DEFAULT NULL,\n" +
                "    HOME_ALLOWANCE INTEGER DEFAULT NULL,\n" +
                "    TREATMENT_ALLOWANCE INTEGER DEFAULT NULL,\n" +
                "    TIFFIN_ALLOWANCE INTEGER DEFAULT NULL,\n" +
                "    EDUCATION_ALLOWANCE INTEGER DEFAULT NULL,\n" +
                "    MOHARGO_ALLOWANCE INTEGER DEFAULT NULL,\n" +
                "    WASHING_ALLOWANCE INTEGER DEFAULT NULL,\n" +
                "    SALARYTOTAL INTEGER DEFAULT NULL,\n" +
                "    GEN_WELFARE_FUND INTEGER DEFAULT NULL,\n" +
                "    WELFARE_FUND INTEGER DEFAULT NULL,\n" +
                "    GEN_WELFARE_FUND_PAID INTEGER DEFAULT NULL,\n" +
                "    HOME_FARE_DEDUCT INTEGER DEFAULT NULL,\n" +
                "    HOME_DEDUCT_ADV_INSTALLMENT INTEGER DEFAULT NULL,\n" +
                "    BIKE_DEDUCT INTEGER DEFAULT NULL,\n" +
                "    HOME_CONST_INSTALLMENT VARCHAR(2147483647),\n" +
                "    FIRST_INSTALLMENT VARCHAR(2147483647),\n" +
                "    SEC_INSTALLMENT VARCHAR(2147483647),\n" +
                "    THRD_INSTALLMENT VARCHAR(2147483647),\n" +
                "    TOTAL_DEDUCTION INTEGER DEFAULT NULL,\n" +
                "    TOTAL_DEMAND INTEGER DEFAULT NULL,\n" +
                "    SALARYDATE VARCHAR(2147483647) DEFAULT 'NULL',\n" +
                "    EMP_ID INTEGER DEFAULT NULL,\n" +
                "    GEN_WELFARE_FUND_PAID_ONE INTEGER,\n" +
                "    GEN_WELFARE_FUND_PAID_TWO INTEGER,\n" +
                "    \n" +
                "); ";
        String empTable = "CREATE TABLE EMPLOYEE\n" +
                "(\n" +
                "    EMP_ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    EMP_NAME VARCHAR(45) DEFAULT 'NULL',\n" +
                "    EMP_POST VARCHAR(45) DEFAULT 'NULL',\n" +
                "    EMP_WORK_PLACE VARCHAR(45) DEFAULT 'NULL',\n" +
                "    OFFICEPLACE VARCHAR(45) DEFAULT 'NULL',\n" +
                "    HOME_FARE_PERCENT VARCHAR(45) DEFAULT 'NULL',\n" +
                "    GEN_FUND_PERCENT VARCHAR(45) DEFAULT 'NULL',\n" +
                "    GPNO INTEGER DEFAULT NULL , \n" +
                ");";
        String officeTable = " CREATE TABLE OFFICE\n" +
                "(\n" +
                "    OFFICE_ID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    EMPLOYER VARCHAR(45) DEFAULT 'NULL',\n" +
                "    OFFICENAME VARCHAR(45) DEFAULT 'NULL',\n" +
                "    PLACE VARCHAR(45) DEFAULT 'NULL'\n" +
                ");";
        String userTable = " CREATE TABLE USERS\n" +
                "(\n" +
                "    USERID INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,\n" +
                "    USERNAME VARCHAR(45) DEFAULT 'NULL',\n" +
                "    PASSWORD VARCHAR(45) DEFAULT 'NULL'\n" +
                ");";
        String defaultUser="INSERT INTO users(username,password) VALUES(?,?) ";
        try{
            Statement statement=null;
            connection = SqlConnect.con();
            connection.setAutoCommit(false);
            statement=connection.createStatement();
            statement.addBatch(dropEmp);
            statement.addBatch(dropoffice);
            statement.addBatch(dropSalary);
            statement.addBatch(dropusers);
            statement.addBatch(empTable);
            statement.addBatch(salaryTable);
            statement.addBatch(officeTable);
            statement.addBatch(userTable);
            statement.executeBatch();

            preparedStatement = connection.prepareStatement(defaultUser);
            preparedStatement.setString(1,"admin");
            preparedStatement.setString(2,"admin");
            preparedStatement.executeUpdate();
            connection.commit();
        }
        catch (SQLException e){
            System.out.println(e);
        }

        finally {
            //connection.setAutoCommit(true);
            connection.close();
        }

        return false;
    }




    public boolean loginFirst(String user, String pass) throws SQLException {
        sqlQuery = "select * from users where username = ? and password = ?";
        try{
            connection = SqlConnect.con();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                loginFailed ="Success";
                return true;
            }
            else {
                loginFailed = "Username/Password is Wrong!";
                return false;
            }
        }
        catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }
        return false;
    }

    public boolean empExist(int empID,String currentMonth) throws SQLException {
        sqlQuery = "select * from mainsalary where emp_id = ? and salarydate LIKE ?";
        System.out.println(sqlQuery);
        try{
            connection = SqlConnect.con();
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,empID);
            preparedStatement.setString(2,"%"+currentMonth);
            resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement.toString());
            if (resultSet.next()){
                info ="Already Exist!";

                return false;

            }
            else {
                info = "OK";


            }

            while (resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        finally {
            preparedStatement.close();
            resultSet.close();
            connection.close();
        }
        return false;
    }

    public void CloseCon() throws SQLException {

        if(connection!=null){
            connection.close();

        }

    }

    public void CustomException(Exception exception){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errors");
        alert.setHeaderText("Error");
        alert.setContentText("Error Message");

        String exceptionText = exception.toString();

        Label label = new Label("Details:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
}
