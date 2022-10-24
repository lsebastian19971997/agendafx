package uia.com.agendafx.agendafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AgendaFXController implements Initializable {

    //Contacto
    @FXML private TableView<Contacto> table;
    @FXML private TableColumn<Contacto, String> tipo;
    @FXML private TableColumn<Contacto, String> nombre;
    @FXML private TableColumn<Contacto, String> fechaRecordatorio;
    @FXML private TableColumn<Contacto, String> fecha;

    @FXML private Label tipoLabel;
    @FXML private Label nombreLabel;
    @FXML private Label fechaLabel;
    @FXML private Label fechaRecordatorioLabel;

    //Evento
    @FXML private TableView<Evento> tableEvento;
    @FXML private TableColumn<Evento, String> tipoEvento;
    @FXML private TableColumn<Evento, String> nombreEvento;
    @FXML private TableColumn<Evento, String> fechaEvento;
    @FXML private TableColumn<Evento, String> fechaRecordatorioEvento;

    @FXML private Label tipoLabelEvento;
    @FXML private Label nombreLabelEvento;
    @FXML private Label fechaLabelEvento;
    @FXML private Label fechaRecordatorioLabelEvento;

    // Reference to the main application.
    private AgendaFXApplication mainApp;

    public ObservableList<Contacto> list = FXCollections.observableArrayList(
            new Contacto("1", "Nava", "1-11-2020", "1-10-2020"),
            new Contacto("2",  "Fahim", "1-12-2021", "1-10-2020"),
            new Contacto("3",  "Shariful", "3-10-2022", "1-10-2020"),
            new Contacto("4",  "Alfonso", "3-10-2022", "1-10-2020")
    );

    public ObservableList<Evento> listEvento = FXCollections.observableArrayList(
            new Evento("1", "Cumple", "1-11-2020", "1-10-2020"),
            new Evento("2",  "Fiesta", "1-12-2021", "1-10-2020"),
            new Evento("3",  "Reunion", "3-10-2022", "1-10-2020"),
            new Evento("4",  "Graduacion", "3-10-2022", "1-10-2020")
    );

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Contacto
        tipo.setCellValueFactory(new PropertyValueFactory<Contacto, String>("tipo"));
        nombre.setCellValueFactory(new PropertyValueFactory<Contacto, String>("nombre"));
        fechaRecordatorio.setCellValueFactory(new PropertyValueFactory<Contacto, String>("fechaRecordatorio"));
        fecha.setCellValueFactory(new PropertyValueFactory<Contacto, String>("fecha"));
        table.setItems(list);

        //Evento
        tipoEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("tipoEvento"));
        nombreEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("nombreEvento"));
        fechaRecordatorioEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("fechaRecordatorioEvento"));
        fechaEvento.setCellValueFactory(new PropertyValueFactory<Evento, String>("fechaEvento"));
        tableEvento.setItems(listEvento);

        // Clear person details.
        showContactoDetails(null);
        showEventoDetails(null);

        // Listen for selection changes and show the person details when changed.
        tableEvento.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showEventoDetails(newValue));
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactoDetails(newValue));

    }


    private void showContactoDetails(Contacto contacto) {
        if (contacto != null) {
            // Fill the labels with info from the contacto object.
            tipoLabel.setText(contacto.getTipo());
            nombreLabel.setText(contacto.getNombre());
            fechaRecordatorioLabel.setText(contacto.getFechaRecordatorio());
            fechaLabel.setText(contacto.getFecha());
        } else {
            // Contacto is null, remove all the text.
            tipoLabel.setText("");
            nombreLabel.setText("");
            fechaRecordatorioLabel.setText("");
            fechaLabel.setText("");
        }
    }

    private void showEventoDetails(Evento evento) {
        if (evento != null) {
            // Fill the labels with info from the contacto object.
            tipoLabelEvento.setText(evento.getTipo());
            nombreLabelEvento.setText(evento.getNombre());
            fechaRecordatorioLabelEvento.setText(evento.getFechaRecordatorio());
            fechaLabelEvento.setText(evento.getFecha());
        } else {
            // Contacto is null, remove all the text.
            tipoLabelEvento.setText("");
            nombreLabelEvento.setText("");
            fechaRecordatorioLabelEvento.setText("");
            fechaLabelEvento.setText("");
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(AgendaFXApplication mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        table.setItems(list);
        tableEvento.setItems(listEvento);
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewContacto() {
        Contacto tempContacto = new Contacto();
        boolean okClicked = mainApp.showContactoEdicionDialogo(tempContacto);
        if (okClicked) {
            list.add(tempContacto);
        }
    }
    @FXML
    private void handleNewEventoo() {
        Evento tempEvento = new Evento();
        boolean okClicked = mainApp.showEventoEdicionDialogo(tempEvento);
        if (okClicked) {
            listEvento.add(tempEvento);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditContacto() {
        Contacto selectedContacto = table.getSelectionModel().getSelectedItem();
        if (selectedContacto != null) {
            boolean okClicked = mainApp.showContactoEdicionDialogo(selectedContacto);
            if (okClicked) {
                showContactoDetails(selectedContacto);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Contacto Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


}