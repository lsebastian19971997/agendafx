package uia.com.agendafx.agendafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EventoEdicionDialogoController {

    @FXML
    private TextField tipoFieldEvento;
    @FXML private TextField nombreFieldEvento;
    @FXML private TextField fechaFieldEvento;
    @FXML private TextField fechaRecordatorioFieldEvento;


    private Stage dialogStage;
    private Evento evento;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the contacto to be edited in the dialog.
     *
     * @param evento
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
        tipoFieldEvento.setText(evento.getTipo());
        nombreFieldEvento.setText(evento.getNombre());
        fechaFieldEvento.setText(evento.getFecha());
        fechaRecordatorioFieldEvento.setText(evento.getFechaRecordatorio());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {

            this.evento.setTipo(tipoFieldEvento.getText());
            this.evento.setNombre(nombreFieldEvento.getText());
            this.evento.setFecha(fechaFieldEvento.getText());
            this.evento.setFechaRecordatorio(fechaRecordatorioFieldEvento.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (tipoFieldEvento.getText() == null || tipoFieldEvento.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (nombreFieldEvento.getText() == null || nombreFieldEvento.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (fechaFieldEvento.getText() == null || fechaFieldEvento.getText().length() == 0) {
            errorMessage += "No valid fecha!\n";
        }

        if (fechaRecordatorioFieldEvento.getText() == null || fechaRecordatorioFieldEvento.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }


}

