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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditUser implements Initializable {
    ConnectionStatus c = new ConnectionStatus();
    ArrayList<usermodel> user;
    @FXML
    private TextField Email;
    @FXML
    private Button addbtn;
    @FXML
    private Button closebtn;
    @FXML
    private Button delbtn;
    @FXML
    private TextField pass;
    @FXML
    private TextField oldpassword;
    @FXML
    private TextField searchid;
    @FXML
    Label status;

    public EditUser() throws SQLException {
        this.user = this.c.Retrieveuser2();
    }

    int search() {
        if (!this.user.isEmpty()) {
            for(int i = 0; i < this.user.size(); ++i) {
                if (this.searchid.getText().equals(((usermodel)this.user.get(i)).getUserid()) && this.oldpassword.getText().equals(((usermodel)this.user.get(i)).getPass())) {
                    return 0;
                }
            }
        }

        return -1;
    }

    @FXML
    void setEdit() {
        if (this.search() != -1) {
            this.c.Edituser(this.pass.getText(),this.searchid.getText());
            Stage stage = (Stage)this.addbtn.getScene().getWindow();
            stage.close();
        } else {
            this.status.setText("⚠ User Id not Found or Wrong password!");
        }

    }

    @FXML
    void setDelete() throws SQLException {
        if (c.usercount()!=1) {


            if (this.search() != -1) {
                this.c.Deluser(this.searchid.getText());
                Stage stage = (Stage) this.delbtn.getScene().getWindow();
                stage.close();
            } else {
                this.status.setText("⚠ User Id not Found or wrong passwoed!");
            }
        }else {
            this.status.setText("⚠ You Cannot Delete Last User!");
        }

    }

    @FXML
    void close() {
        Stage stage = (Stage)this.closebtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
