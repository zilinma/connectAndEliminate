package csci205_final_project.GameSaveMenu;

import Util.SaveAndLoadModelUtil;
import csci205_final_project.Model.Model;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zilin Ma
 */
public class GameSaveMenuController implements Initializable {

    @FXML
    private Button saveSlot1;
    @FXML
    private Button saveSlot2;
    @FXML
    private Button saveSlot3;
    @FXML
    private Button resume;

    private Model myModel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // read saves and change the text on the tiles.
        try {
            Model save1 = SaveAndLoadModelUtil.deserializeModel(
                    this.getClass().getClassLoader().getResource(
                            "ser/save1.ser").openConnection().getInputStream());

            this.saveSlot1.setText(save1.toString());
        } catch (IOException ex) {
            this.saveSlot1.setText("Empty Save Slot");
        } catch (ClassNotFoundException ex) {

        }

        try {
            Model save2 = SaveAndLoadModelUtil.deserializeModel(
                    this.getClass().getClassLoader().getResource(
                            "ser/save2.ser").openConnection().getInputStream());

            this.saveSlot2.setText(save2.toString());
        } catch (IOException ex) {

            this.saveSlot2.setText("Empty Save Slot");

        } catch (ClassNotFoundException ex) {

        }
        try {
            Model save3 = SaveAndLoadModelUtil.deserializeModel(
                    this.getClass().getClassLoader().getResource(
                            "ser/save3.ser").openConnection().getInputStream());
            this.saveSlot3.setText(save3.toString());
        } catch (IOException ex) {

            this.saveSlot3.setText("Empty Save Slot");
        } catch (ClassNotFoundException ex) {

        }
    }

    /**
     * initialize a model
     *
     * @param myModel
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void initModel(Model myModel) {
        this.myModel = myModel;
    }

    @FXML
    private void saveInSlot1(ActionEvent event) throws IOException, URISyntaxException {
        try {
            SaveAndLoadModelUtil.serializeModel(myModel,
                                                this.getClass().getClassLoader().getResource(
                                                        "ser/save1.ser"));
            this.saveSlot1.setText(myModel.toString());
        } catch (IOException ex) {
            this.saveSlot1.setText("Failed to save");

        }
    }

    @FXML
    private void saveInSlot2(ActionEvent event) throws URISyntaxException {
        try {
            SaveAndLoadModelUtil.serializeModel(myModel,
                                                this.getClass().getClassLoader().getResource(
                                                        "ser/save2.ser"));
            this.saveSlot2.setText(myModel.toString());
        } catch (IOException ex) {
            this.saveSlot2.setText("Failed to save");
        }
    }

    @FXML
    private void saveInSlot3(ActionEvent event) throws URISyntaxException {
        try {
            SaveAndLoadModelUtil.serializeModel(myModel,
                                                this.getClass().getClassLoader().getResource(
                                                        "ser/save3.ser"));
            this.saveSlot3.setText(myModel.toString());
        } catch (IOException ex) {
            this.saveSlot3.setText("Failed to save");

        }
    }

    @FXML
    private void resumeToGame(ActionEvent event) {
        Stage stage = (Stage) this.resume.getScene().getWindow();
        stage.close();
    }

}
