package SalarySheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;

/**
 * Created by famed on 5/15/16.
 */
public class ReportController implements Initializable {

    @FXML
    private TextField viewReport;
    LocalDate date = LocalDate.now();

    @FXML
    public TextField displayDate;

    @FXML
    private TextArea textArea;

    private Connection connection;
    private int currentYear = date.getYear();
    private String getMonth = date.getMonthValue()+"/"+date.getYear();
    private String getDate = date.getDayOfMonth()+"/"+date.getMonthValue()+"/"+date.getYear();
    private String listCurrentItem=null;


    @FXML
     ListView<String> listView = new ListView<>();
    public ObservableList<String> monthList = FXCollections.observableArrayList("January","February",
            "March","Appril","May","June",
        "July","August","September","October","November","December");


    public void ClickButton() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, JRException, SQLException {


        String date = viewReport.getText();



        //String reportSrcFile = "/home/famed/IdeaProjects/Salary Sheet/src/SalarySheet/salary.jrxml";




        Connection conn = SqlConnect.con();
        String comments = textArea.getText();

        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("Comments",comments);
        parameters.put("repDate", getMonth);
        if(displayDate.getText().isEmpty()){
            parameters.put("displayDate",getMonth);
        }

        else {
            parameters.put("displayDate",displayDate.getText());
        }

        System.out.println("The Date:");
        System.out.println(parameters.get("repDate"));
        try{
            JasperReport report = JasperCompileManager.compileReport(new File("").getAbsolutePath()+"/salarymakerfile/salary.jrxml");
            System.out.println(report.toString());
            JasperPrint print = JasperFillManager.fillReport(report,
                    parameters, conn);


            JasperViewer jv = new JasperViewer(print,false);

            jv.setTitle("Salary of "+getMonth.toString());
            jv.setVisible(true);
            jv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            //jv.viewReport(print,false);
        }
        catch (JRException jrexception){
            CommonData commonData = new CommonData();
            commonData.CustomException(jrexception);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewReport.setText(String.valueOf(currentYear));
        listView.setItems(monthList);
        viewReport.textProperty().addListener((observable, oldValue, newValue) -> {
            if (viewReport.getText().isEmpty()){
                listView.setDisable(true);
                viewReport.setStyle("-fx-border-color: red");
            }

            else {
                listView.setDisable(false);

            }
        });
        /*
        displayDate.textProperty().addListener((observable, oldValue, newValue) -> {
            if (displayDate.getText().isEmpty()){
                listView.setDisable(true);
                displayDate.setStyle("-fx-border-color: red");
            }
            else {
                listView.setDisable(false);

            }
        });
        */

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    listCurrentItem = listView.getSelectionModel().getSelectedItem();
                    loadMonths();


                    //viewReport.setText(getMonth);

                    try {
                        ClickButton();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (JRException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



    }

    public void loadMonths(){

        int January= 1;
        int February= 1;
        int March= 1;
        int Appril= 1;
        int May= 1;
        int June= 1;
        int July= 1;
        int August= 1;
        int September= 1;
        int October= 1;
        int November= 1;
        int December= 1;

        switch (listCurrentItem){
            case "January":

                getMonth = "/"+ 1+"/"+viewReport.getText();
                break;
            case "February":

                getMonth = "/"+  2+"/"+viewReport.getText();
                break;
            case "March":
                getMonth = "/"+  3+"/"+viewReport.getText();
                break;
            case "Appril":
                getMonth = "/"+  4+"/"+viewReport.getText();
                break;
            case "May":
                getMonth = "/"+  5+"/"+viewReport.getText();
                break;
            case "June":
                getMonth = "/"+  6+"/"+viewReport.getText();
                break;
            case "July":
                getMonth = "/"+  7+"/"+viewReport.getText();
                break;
            case "August":
                getMonth = "/"+  8+"/"+viewReport.getText();
                break;
            case "September":
                getMonth = "/"+  9+"/"+viewReport.getText();
                break;
            case "October":
                getMonth = "/"+ 10+"/"+viewReport.getText();
                break;
            case "November":
                getMonth = "/"+ 11+"/"+viewReport.getText();
                break;
            case "December":
                getMonth = "/"+ 12+"/"+viewReport.getText();
                break;
        }




    }
}
