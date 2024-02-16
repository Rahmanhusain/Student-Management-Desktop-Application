//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.management.schoolmanagement;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    Button stu;
    @FXML
    Button teach;
    @FXML
    Button lib;
    @FXML
    Button dash;
    @FXML
    Button change;
    @FXML
    Button comp;
    @FXML
    Button sci;
    @FXML
    AnchorPane menu;
    @FXML
    AnchorPane anchorpane;
    @FXML
    ImageView menubar;
    @FXML
    ImageView back;
    @FXML
    Label status;
    @FXML
    Label user;
    @FXML
    Label Techers;
    @FXML
    Label student;
    ConnectionStatus c = new ConnectionStatus();

    public HelloController() {
    }

    @FXML
    protected void Student() throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Physics");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Student.fxml"));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("styles/slogo.png")).openStream()));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.show();
    }

    @FXML
    protected void Teachers() throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Chemistry");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Chemistry.fxml"));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("styles/slogo.png")).openStream()));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.show();
    }

    @FXML
    void Science() throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Mathematics");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Maths.fxml"));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("styles/slogo.png")).openStream()));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.show();
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.menu.setTranslateX(121.0);
        this.back.setVisible(false);
        if (this.c.isconnected()) {
            this.status.setText("Database Status : connected");
        } else {
            this.status.setText("Database Status : disconnected");
        }

        try {
            this.user.setText(String.valueOf(this.c.usercount()));
        } catch (SQLException var4) {
            throw new RuntimeException(var4);
        }
    }

    @FXML
    void Dropmenu() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(this.menu);
        slide.setToX(0.0);
        slide.play();
        this.menu.setTranslateX(121.0);
        slide.setOnFinished((actionEvent) -> {
            this.menubar.setVisible(false);
            this.back.setVisible(true);
        });
    }

    @FXML
    void hidedDropmenu() {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.3));
        slide.setNode(this.menu);
        slide.setToX(121.0);
        slide.play();
        this.menu.setTranslateX(0.0);
        slide.setOnFinished((actionEvent) -> {
            this.menubar.setVisible(true);
            this.back.setVisible(false);
        });
    }

    @FXML
    void adduser() throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Add User");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("modelfxml/add_user.fxml"));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("styles/slogo.png")).openStream()));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.setMaximized(false);
        newWindow.setResizable(false);
        newWindow.show();
    }

    @FXML
    void editpass() throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Change Password/Delete acount");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("modelfxml/EditUser.fxml"));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("styles/slogo.png")).openStream()));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.setMaximized(false);
        newWindow.setResizable(false);
        newWindow.show();
    }
}
