package id.ilkom;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SistemGudangFormController implements Initializable {

    @FXML
    private TextField tfGudangID;

    @FXML
    private TextField tfNama;

    @FXML
    private TextField tfAlamat;

    @FXML
    private ComboBox<String> cbNik;

    @FXML
    private DatePicker dpBirthdate;

    @FXML
    private TextField tfNoMember;

    @FXML
    private TextField tfAkBarang;

    @FXML
    private Button btnAddSistemGudang;

    @FXML
    private Button btnReload;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblSaveStatus;

    @FXML
    private TableView<?> tblSisGudang;

    @FXML
    private TableColumn<?, ?> colGudangID;

    @FXML
    private TableColumn<?, ?> colNama;

    @FXML
    private TableColumn<?, ?> colAlamat;

    @FXML
    private TableColumn<?, ?> colNik;

    @FXML
    private TableColumn<?, ?> colBirthdate;

    @FXML
    private TableColumn<?, ?> colNoAkunn;

    @FXML
    private TableView<?> tblAkun;

    @FXML
    private TableColumn<?, ?> colNoMember;

    @FXML
    private TableColumn<?, ?> colBarang;

    @FXML
    private TextField tfNewGudangID;

    @FXML
    private TextField tfNewNoMember;

    @FXML
    private TextField tfNewAkBarang;

    @FXML
    private Button btnAddAkun;

    @FXML
    private Label lblDBStatus;
    
    private SistemGudangDataModel sgdm;

    @FXML
    void handleAddAkunButton(ActionEvent event) {

    }

    @FXML
    void handleAddGudangButton(ActionEvent event) {
        LocalDate ld = dpBirthdate.getValue();
        String birthdate = String.format("%d-%02d-%02d", ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
        IndividualHolder gudang = new IndividualHolder(Integer.parseInt(tfGudangID.getText()), tfNama.getText(), tfAlamat.getText(),
                cbNik.getSelectionModel().getSelectedItem(),birthdate,new Akun (Integer.parseInt(tfNoMember.getText(),tfAkBarang.getText())));

    }

    @FXML
    void handleClearButton(ActionEvent event) {

    }

    @FXML
    void handleReloadButton(ActionEvent event) {

    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> nik = FXCollections.observableArrayList("KTP", "SIM");
        cbNik.setItems(nik);
        try {
            SistemGudangDataModel sgdm = new SistemGudangDataModel ("MYSQL");
            lblDBStatus.setText(sgdm.conn==null?"Not Connected" : "Connected");
            tfGudangID.setText(""+sgdm.nextSistemGudangID());
            tfNoMember.setText(tfGudangID.getText()+"01");
            dpBirthdate.setValue(LocalDate.of(LocalDate.now().getYear()-17, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));
                    
        } catch (SQLException ex) {
            Logger.getLogger(SistemGudangFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  

}
