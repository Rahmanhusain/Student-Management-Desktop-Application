package com.management.schoolmanagement.modelcontroller;

import com.management.schoolmanagement.ConnectionStatus;
import com.management.schoolmanagement.model.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class editMaths implements Initializable {
    ConnectionStatus con = new ConnectionStatus();
    @FXML
    TextField Id,Name,Father,sname,tfee,pfee,dfee,searchid;
    @FXML
    ChoiceBox<String> Class,plan,batch,status;
    @FXML
    Button closebtn;
    @FXML
    Button addbtn;
    @FXML
    Button searchbtn;
    @FXML
    Button delbtn;
    String[] clas = new String[]{"9th", "10th", "11th","12th","JEE-MAIN","NEET"};
    String[] statusopt = new String[]{"Current", "Ex"};
    String[] planopt = new String[]{"I", "II", "III"};
    String[] batchopt = new String[]{"A1", "A2", "A3","B1","B2","B3"};

    public editMaths() {
    }

    int search() throws SQLException {
        ArrayList<Student> editlist = this.con.RetrieveStudent("All Students","all","all","all","Maths");
        long id = Long.parseLong(this.searchid.getText());

        for(int i = 0; i < editlist.size(); ++i) {
            if (id == ((Student)editlist.get(i)).getID()) {
                return i;
            }
        }

        return -1;
    }

    @FXML
    void setSearchbtn() throws SQLException {
        ArrayList<Student> list = this.con.RetrieveStudent("All Students","all","all","all","Maths");
        int i = this.search();
        if (i != -1) {
            this.Id.setEditable(true);
            this.Name.setEditable(true);
            this.Father.setEditable(true);
            this.sname.setEditable(true);
            this.tfee.setEditable(true);
            this.pfee.setEditable(true);
            this.dfee.setEditable(true);
            this.Class.setDisable(false);
            this.plan.setDisable(false);
            this.batch.setDisable(false);
            this.status.setDisable(false);
            this.Id.setText(String.valueOf(list.get(i).getID()));
            this.Name.setText(list.get(i).getNAME().toUpperCase());
            this.Class.setValue(list.get(i).getCLASS());
            this.Father.setText(list.get(i).getFATHER());
            this.sname.setText(list.get(i).getSCHOOL());
            this.tfee.setText(String.valueOf(list.get(i).getTOATALFESS()));
            this.pfee.setText(String.valueOf(list.get(i).getFEEPAID()));
            this.dfee.setText(String.valueOf(list.get(i).getFEEDUE()));
            this.plan.setValue(list.get(i).getFEEPLAN());
            this.batch.setValue(list.get(i).getBATCH());
            this.status.setValue(list.get(i).getStatus());
        }

    }

    @FXML
    void setEdit() {
        this.con.EditStudent(Long.parseLong(Id.getText()),Name.getText().toUpperCase(),Class.getValue(),Father.getText().toUpperCase(),sname.getText(),
                Long.parseLong(tfee.getText()),Long.parseLong(pfee.getText()),Long.parseLong(dfee.getText()),plan.getValue(),
                batch.getValue(),status.getValue(),Long.parseLong(searchid.getText()),"Maths");
        Stage stage = (Stage)this.addbtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void setDelete() {
        String Sid = this.searchid.getText();
        long sid = Long.parseLong(Sid);
        this.con.Delstudent(sid,"Maths");
        Stage stage = (Stage)this.delbtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void close() {
        Stage stage = (Stage)this.closebtn.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    searchid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        this.Class.getItems().addAll(this.clas);
        this.Class.setValue("9th");
        this.status.getItems().addAll(this.statusopt);
        this.status.setValue("Current");
        this.plan.getItems().addAll(this.planopt);
        this.Class.setValue("I");
        this.batch.getItems().addAll(this.batchopt);
        this.batch.setValue("A1");
    }
}
