package com.management.schoolmanagement;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.management.schoolmanagement.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChemistryController implements Initializable {

    ObservableList<Student> list = FXCollections.observableArrayList();
    ConnectionStatus c = new ConnectionStatus();
    ArrayList<Student> List = new ArrayList<>();
    String[] stufilter = new String[]{"All Students", "Current", "Ex"};
    String[] clasf = new String[]{"all","9th", "10th", "11th","12th","JEE-MAIN","NEET"};
    String[] planf = new String[]{"all","I", "II", "III"};
    String[] batchf = new String[]{"all","A1", "A2", "A3","B1","B2","B3"};
    @FXML
    BorderPane bd;
    @FXML
    ChoiceBox<String> chbox,clas,plan,batch;
    @FXML
    private TableView<Student> Studenttblview;
    @FXML
    private TableColumn<Student,Long> sno;
    @FXML
    private TableColumn<Student, Long>Roll;
    @FXML
    private TableColumn<Student, Long> tfee;
    @FXML
    private TableColumn<Student, Long> pFee;
    @FXML
    private TableColumn<Student, Long> dfee;
    @FXML
    private TableColumn<Student, String> Name;
    @FXML
    private TableColumn<Student, String> Class;
    @FXML
    private TableColumn<Student, String> father;
    @FXML
    private TableColumn<Student, String> School;
    @FXML
    private TableColumn<Student, String> tplan;
    @FXML
    private TableColumn<Student, String> tbatch;
    @FXML
    private TableColumn<Student, String> status;
    @FXML
    private TextField searchbox;

    public ChemistryController() {
    }

    @FXML
    protected void addstudent() throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Add Chemistry");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("modelfxml/add_Chemistry.fxml"));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("styles/slogo.png")).openStream()));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.setMaximized(false);
        newWindow.setResizable(false);
        newWindow.show();
    }

    @FXML
    protected void Editstudent() throws IOException {
        Stage newWindow = new Stage();
        newWindow.setTitle("Edit Chemistry");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("modelfxml/edit_Chemistry.fxml"));
        newWindow.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResource("styles/slogo.png")).openStream()));
        newWindow.setScene(new Scene(loader.load()));
        newWindow.setMaximized(false);
        newWindow.setResizable(false);
        newWindow.show();
    }

    protected void currentStudent() {
        if (!this.List.isEmpty() && !this.list.isEmpty()) {
            this.List.clear();
            this.list.clear();
            this.Studenttblview.getItems().clear();
        }

        try {
            this.List = this.c.RetrieveStudent("Current","all","all","all","Chemistry");
        } catch (SQLException var2) {
            throw new RuntimeException(var2);
        }

        if (!this.List.isEmpty()) {

            for(int i = 0; i <= this.List.size() - 1; i++) {
                this.list.add(new Student(this.List.get(i).getSno()+i,this.List.get(i).getID(), this.List.get(i).getNAME(),
                        this.List.get(i).getCLASS(), this.List.get(i).getFATHER(), this.List.get(i).getSCHOOL(), this.List.get(i).getTOATALFESS(),
                        this.List.get(i).getFEEPAID(), this.List.get(i).getFEEDUE(), this.List.get(i).getFEEPLAN(), this.List.get(i).getBATCH(),
                        this.List.get(i).getStatus()));
            }
            this.sno.setCellValueFactory(new PropertyValueFactory<>("sno"));
            this.Roll.setCellValueFactory(new PropertyValueFactory<>("ID"));
            this.Name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            this.Class.setCellValueFactory(new PropertyValueFactory<>("CLASS"));
            this.father.setCellValueFactory(new PropertyValueFactory<>("FATHER"));
            this.School.setCellValueFactory(new PropertyValueFactory<>("SCHOOL"));
            this.tfee.setCellValueFactory(new PropertyValueFactory<>("TOATALFESS"));
            this.pFee.setCellValueFactory(new PropertyValueFactory<>("FEEPAID"));
            this.dfee.setCellValueFactory(new PropertyValueFactory<>("FEEDUE"));
            this.tplan.setCellValueFactory(new PropertyValueFactory<>("FEEPLAN"));
            this.tbatch.setCellValueFactory(new PropertyValueFactory<>("BATCH"));
            this.status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            this.Studenttblview.setItems(this.list);
        }

    }

    protected void Exstudent() {

        if (!this.List.isEmpty() && !this.list.isEmpty()) {
            this.List.clear();
            this.list.clear();
            this.Studenttblview.getItems().clear();
        }

        try {
            this.List = this.c.RetrieveStudent("Ex","all","all","all","Chemistry");
        } catch (SQLException var2) {
            throw new RuntimeException(var2);
        }

        if (!this.List.isEmpty()) {

            for(int i = 0; i <= this.List.size() - 1; i++) {
                this.list.add(new Student(this.List.get(i).getSno()+i,this.List.get(i).getID(), this.List.get(i).getNAME(),
                        this.List.get(i).getCLASS(), this.List.get(i).getFATHER(), this.List.get(i).getSCHOOL(), this.List.get(i).getTOATALFESS(),
                        this.List.get(i).getFEEPAID(), this.List.get(i).getFEEDUE(), this.List.get(i).getFEEPLAN(), this.List.get(i).getBATCH(),
                        this.List.get(i).getStatus()));
            }
            this.sno.setCellValueFactory(new PropertyValueFactory<>("sno"));
            this.Roll.setCellValueFactory(new PropertyValueFactory<>("ID"));
            this.Name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            this.Class.setCellValueFactory(new PropertyValueFactory<>("CLASS"));
            this.father.setCellValueFactory(new PropertyValueFactory<>("FATHER"));
            this.School.setCellValueFactory(new PropertyValueFactory<>("SCHOOL"));
            this.tfee.setCellValueFactory(new PropertyValueFactory<>("TOATALFESS"));
            this.pFee.setCellValueFactory(new PropertyValueFactory<>("FEEPAID"));
            this.dfee.setCellValueFactory(new PropertyValueFactory<>("FEEDUE"));
            this.tplan.setCellValueFactory(new PropertyValueFactory<>("FEEPLAN"));
            this.tbatch.setCellValueFactory(new PropertyValueFactory<>("BATCH"));
            this.status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            this.Studenttblview.setItems(this.list);
        }

    }

    @FXML
    protected void Allstudent() {
        if (!this.List.isEmpty() && !this.list.isEmpty()) {
            this.List.clear();
            this.list.clear();
            this.Studenttblview.getItems().clear();
        }

        try {
            this.List = this.c.RetrieveStudent("All Students","all","all","all","Chemistry");
        } catch (SQLException var2) {
            throw new RuntimeException(var2);
        }

        if (!this.List.isEmpty()) {

            for(int i = 0; i <= this.List.size() - 1;i++) {
                this.list.add(new Student(this.List.get(i).getSno()+i,this.List.get(i).getID(), this.List.get(i).getNAME(),
                        this.List.get(i).getCLASS(), this.List.get(i).getFATHER(), this.List.get(i).getSCHOOL(), this.List.get(i).getTOATALFESS(),
                        this.List.get(i).getFEEPAID(), this.List.get(i).getFEEDUE(), this.List.get(i).getFEEPLAN(), this.List.get(i).getBATCH(),
                        this.List.get(i).getStatus()));
            }
            this.sno.setCellValueFactory(new PropertyValueFactory<>("sno"));
            this.Roll.setCellValueFactory(new PropertyValueFactory<>("ID"));
            this.Name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            this.Class.setCellValueFactory(new PropertyValueFactory<>("CLASS"));
            this.father.setCellValueFactory(new PropertyValueFactory<>("FATHER"));
            this.School.setCellValueFactory(new PropertyValueFactory<>("SCHOOL"));
            this.tfee.setCellValueFactory(new PropertyValueFactory<>("TOATALFESS"));
            this.pFee.setCellValueFactory(new PropertyValueFactory<>("FEEPAID"));
            this.dfee.setCellValueFactory(new PropertyValueFactory<>("FEEDUE"));
            this.tplan.setCellValueFactory(new PropertyValueFactory<>("FEEPLAN"));
            this.tbatch.setCellValueFactory(new PropertyValueFactory<>("BATCH"));
            this.status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            this.Studenttblview.setItems(this.list);
        }

    }

    @FXML
    protected void SearchStudent() {

        if (!this.List.isEmpty() && !this.list.isEmpty()) {
            this.List.clear();
            this.list.clear();
            this.Studenttblview.getItems().clear();
        }

        try {
            this.List = this.c.RetrieveStudent("All Students","all","all","all","Chemistry");
        } catch (SQLException var3) {
            throw new RuntimeException(var3);
        }

        if (!this.List.isEmpty()) {

            for(int i = 0; i <= this.List.size() - 1; i++) {
                if(searchbox.getText().equals(String.valueOf(List.get(i).getID()))||searchbox.getText().toUpperCase().equals(List.get(i).getNAME())) {
                    this.list.add(new Student(this.List.get(i).getSno()+i, this.List.get(i).getID(), this.List.get(i).getNAME(),
                            this.List.get(i).getCLASS(), this.List.get(i).getFATHER(), this.List.get(i).getSCHOOL(), this.List.get(i).getTOATALFESS(),
                            this.List.get(i).getFEEPAID(), this.List.get(i).getFEEDUE(), this.List.get(i).getFEEPLAN(), this.List.get(i).getBATCH(),
                            this.List.get(i).getStatus()));
                }
            }
            this.sno.setCellValueFactory(new PropertyValueFactory<>("sno"));
            this.Roll.setCellValueFactory(new PropertyValueFactory<>("ID"));
            this.Name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            this.Class.setCellValueFactory(new PropertyValueFactory<>("CLASS"));
            this.father.setCellValueFactory(new PropertyValueFactory<>("FATHER"));
            this.School.setCellValueFactory(new PropertyValueFactory<>("SCHOOL"));
            this.tfee.setCellValueFactory(new PropertyValueFactory<>("TOATALFESS"));
            this.pFee.setCellValueFactory(new PropertyValueFactory<>("FEEPAID"));
            this.dfee.setCellValueFactory(new PropertyValueFactory<>("FEEDUE"));
            this.tplan.setCellValueFactory(new PropertyValueFactory<>("FEEPLAN"));
            this.tbatch.setCellValueFactory(new PropertyValueFactory<>("BATCH"));
            this.status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            this.Studenttblview.setItems(this.list);
        }

    }

    @FXML
    void setChbox() {
        if (this.chbox.getValue().equals("Current")) {
            this.currentStudent();
        } else if (this.chbox.getValue().equals("Ex")) {
            this.Exstudent();
        } else if (this.chbox.getValue().equals("All Students")) {
            this.Allstudent();
        }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Allstudent();
        this.chbox.getItems().addAll(this.stufilter);
        this.chbox.setValue("All Students");
        this.clas.getItems().addAll(this.clasf);
        this.clas.setValue("all");
        this.plan.getItems().addAll(this.planf);
        this.plan.setValue("all");
        this.batch.getItems().addAll(this.batchf);
        this.batch.setValue("all");
    }
    @FXML
    void filter() {

        if (!this.List.isEmpty() && !this.list.isEmpty()) {
            this.List.clear();
            this.list.clear();
            this.Studenttblview.getItems().clear();
        }

        try {
            if (chbox.getValue().equals("Current")){
                this.List = this.c.RetrieveStudent("Current",clas.getValue(),plan.getValue(),batch.getValue(),"Chemistry");
            }else if(chbox.getValue().equals("Ex")) {
                this.List = this.c.RetrieveStudent("Ex",clas.getValue(),plan.getValue(),batch.getValue(),"Chemistry");
            }else if(chbox.getValue().equals("All Students")) {
                this.List = this.c.RetrieveStudent("All Students",clas.getValue(),plan.getValue(),batch.getValue(),"Chemistry");
            }

        } catch (SQLException var2) {
            throw new RuntimeException(var2);
        }

        if (!this.List.isEmpty()) {

            for(int i = 0; i <= this.List.size() - 1; i++) {
                this.list.add(new Student(this.List.get(i).getSno()+i,this.List.get(i).getID(), this.List.get(i).getNAME(),
                        this.List.get(i).getCLASS(), this.List.get(i).getFATHER(), this.List.get(i).getSCHOOL(), this.List.get(i).getTOATALFESS(),
                        this.List.get(i).getFEEPAID(), this.List.get(i).getFEEDUE(), this.List.get(i).getFEEPLAN(), this.List.get(i).getBATCH(),
                        this.List.get(i).getStatus()));
            }
            this.sno.setCellValueFactory(new PropertyValueFactory<>("sno"));
            this.Roll.setCellValueFactory(new PropertyValueFactory<>("ID"));
            this.Name.setCellValueFactory(new PropertyValueFactory<>("NAME"));
            this.Class.setCellValueFactory(new PropertyValueFactory<>("CLASS"));
            this.father.setCellValueFactory(new PropertyValueFactory<>("FATHER"));
            this.School.setCellValueFactory(new PropertyValueFactory<>("SCHOOL"));
            this.tfee.setCellValueFactory(new PropertyValueFactory<>("TOATALFESS"));
            this.pFee.setCellValueFactory(new PropertyValueFactory<>("FEEPAID"));
            this.dfee.setCellValueFactory(new PropertyValueFactory<>("FEEDUE"));
            this.tplan.setCellValueFactory(new PropertyValueFactory<>("FEEPLAN"));
            this.tbatch.setCellValueFactory(new PropertyValueFactory<>("BATCH"));
            this.status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            this.Studenttblview.setItems(this.list);
        }
    }
    @FXML
    void print() throws FileNotFoundException {
        String fileAsString = null;
        File file = null;
        Window stage = bd.getScene().getWindow();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("save");
        chooser.setInitialFileName("test");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
        try {
            file = chooser.showSaveDialog(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file != null) {
            fileAsString = file.toString();

            System.out.println(fileAsString);

            PdfDocument pdfDoc
                    = new PdfDocument(new PdfWriter(fileAsString));
            // Step-2 Creating a Document object
            Document doc = new Document(pdfDoc);
            String timeStamp = new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            Text title=new Text("Chemistry Students Data.").setFontSize(12f);
            Text time=new Text("Date : "+timeStamp+"\n").setFontSize(10f);
            Paragraph p1=new Paragraph().add(title);
            Paragraph p2=new Paragraph().add(time);
            doc.add(p1);
            doc.add(p2);
            float[] width = {7,10,20,7,25,30,10,10,10,5,5,10};
            // Step-3 Creating a table
            Table table = new Table(width);

            // Step-4 Adding cells to the table
            table.addCell(new Cell().add("s.no").setFontSize(8F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("ID  ").setFontSize(10F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Name").setFontSize(10F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Class").setFontSize(10F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Father Name").setFontSize(8F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("School Name").setFontSize(10F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Total Fee").setFontSize(8F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Paid Fee").setFontSize(8F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Fee Due").setFontSize(8F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Fee Plan").setFontSize(8F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Batch").setFontSize(10F).setBackgroundColor(Color.YELLOW));
            table.addCell(new Cell().add("Status").setFontSize(10F).setBackgroundColor(Color.YELLOW));
            if (!List.isEmpty()) {
                for (int i = 0; i < List.size(); i++) {
                    table.addCell(new Cell().add(String.valueOf(List.get(i).getSno() + i)).setFontSize(8F));
                    table.addCell(new Cell().add(String.valueOf(List.get(i).getID())).setFontSize(8F));
                    table.addCell(new Cell().add(List.get(i).getNAME()).setFontSize(8F));
                    table.addCell(new Cell().add(List.get(i).getCLASS()).setFontSize(8F));
                    table.addCell(new Cell().add(List.get(i).getFATHER()).setFontSize(8F));
                    table.addCell(new Cell().add(List.get(i).getSCHOOL()).setFontSize(8F));
                    table.addCell(new Cell().add(String.valueOf(List.get(i).getTOATALFESS())).setFontSize(8F));
                    table.addCell(new Cell().add(String.valueOf(List.get(i).getFEEPAID())).setFontSize(8F));
                    table.addCell(new Cell().add(String.valueOf(List.get(i).getFEEDUE())).setFontSize(8F));
                    table.addCell(new Cell().add(List.get(i).getFEEPLAN()).setFontSize(8F));
                    table.addCell(new Cell().add(List.get(i).getBATCH()).setFontSize(8F));
                    table.addCell(new Cell().add(List.get(i).getStatus()).setFontSize(8F));
                }
            }

            // Step-6 Adding Table to document
            doc.add(table);

            // Step-7 Closing the document
            doc.close();
            System.out.println("Table created successfully..");
        }
    }
}
