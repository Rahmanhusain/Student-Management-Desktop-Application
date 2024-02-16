package com.management.schoolmanagement.modelcontroller;

import com.management.schoolmanagement.ConnectionStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class addChemistry implements Initializable {
    ConnectionStatus con = new ConnectionStatus();
    @FXML
    TextField Id,Name,Father,sname,tfee,pfee,dfee;
    @FXML
    ChoiceBox<String> Class,plan,batch,status;
    @FXML
    Button closebtn;
    @FXML
    Button addbtn;
    @FXML
    Label messege;
    String[] clas = new String[]{"9th", "10th", "11th","12th","JEE-MAIN","NEET"};
    String[] statusopt = new String[]{"Current", "Ex"};
    String[] planopt = new String[]{"I", "II", "III"};
    String[] batchopt = new String[]{"A1", "A2", "A3","B1","B2","B3"};

    public addChemistry() {
    }

    @FXML
    void add_student() {
        try {

            if (this.con.InsertStudent(Long.parseLong(Id.getText()),Name.getText().toUpperCase(),Class.getValue(),Father.getText().toUpperCase(),sname.getText(),
                    Long.parseLong(tfee.getText()),Long.parseLong(pfee.getText()),Long.parseLong(dfee.getText()),plan.getValue(),
                    batch.getValue(),status.getValue(),"Chemistry")) {
                Stage stage = (Stage) this.addbtn.getScene().getWindow();
                stage.close();
            }else{
                messege.setText("Enter proper data or check ID should be unique!");
            }

        } catch (Exception var16) {
            messege.setText("Enter proper data or check ID should be unique!");
            System.out.println("enter proper data");
        }


    }

    @FXML
    void close() {
        Stage stage = (Stage)this.closebtn.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    Id.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        tfee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    tfee.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        pfee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    pfee.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        dfee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    dfee.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        this.Class.getItems().addAll(this.clas);
        this.Class.setValue("9th");
        this.plan.getItems().addAll(this.planopt);
        this.plan.setValue("I");
        this.batch.getItems().addAll(this.batchopt);
        this.batch.setValue("A1");
        this.status.getItems().addAll(this.statusopt);
        this.status.setValue("Current");

    }
}
