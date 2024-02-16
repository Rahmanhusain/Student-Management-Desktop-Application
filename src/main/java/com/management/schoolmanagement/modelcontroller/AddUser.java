//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.management.schoolmanagement.modelcontroller;

import com.management.schoolmanagement.ConnectionStatus;
import com.management.schoolmanagement.model.usermodel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddUser implements Initializable {
    ConnectionStatus c = new ConnectionStatus();

    @FXML
    private Button addbtn;
    @FXML
    private Button closebtn;
    @FXML
    private TextField password;
    @FXML
    private TextField userid;
    @FXML
    Label status;

    public AddUser() throws SQLException {
    }

    int search() throws SQLException {
        ArrayList<usermodel> user = this.c.Retrieveuser();
        if (!user.isEmpty()) {
            for(int i = 0; i < user.size(); ++i) {
                if (this.userid.getText().equals(((usermodel)user.get(i)).getUserid())) {
                    return 0;
                }
            }
        }

        return -1;
    }

    @FXML
    void add() throws SQLException {
        if (this.search() == -1) {
            this.c.Insertuser(this.password.getText(), this.userid.getText());
            Stage stage = (Stage)this.addbtn.getScene().getWindow();
            stage.close();
        } else {
            this.status.setText("⚠ User ID Already Taken!");
        }

    }

    @FXML
    void close() {
        Stage stage = (Stage)this.closebtn.getScene().getWindow();
        stage.close();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
