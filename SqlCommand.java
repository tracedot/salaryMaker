/**
 * Created by famed on 5/6/16.
 */
package SalarySheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.nio.channels.Pipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SqlCommand implements Initializable{
    Connection connection;


    public void readIntoTextFild(){

    }

    public void insertData(int a,int b,int c,int d,int e,int f,int g,int h) throws SQLException {


        try{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            String insertQuery = "INSERT INTO mainsalary(salary_basic,home_allowance,treatment_allowance," +
                    "tiffin_allowance,education_allowance,mohargo_allowance,washing_allowance,emp_id) VALUES(?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,a);
            preparedStatement.setInt(2,b);
            preparedStatement.setInt(3,c);
            preparedStatement.setInt(4,d);
            preparedStatement.setInt(5,e);
            preparedStatement.setInt(6,f);
            preparedStatement.setInt(7,g);
            preparedStatement.setInt(8,h);

            preparedStatement.executeUpdate();
        }

        catch (SQLException e1e){
            System.out.println(e1e);
        }


    }

    public void updateIntoTextFild(){

    }

    public void employeCombo(){

        //ArrayList<String> arrayList = new ArrayList();





    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
