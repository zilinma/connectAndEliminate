/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 16, 2017
* Time: 3:34:02 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Model.GameSaveMenu
* File: GameSaveMenuController
* Description:
*
* ****************************************
 */
package csci205_final_project.GameLoadMenu;

import Util.SaveAndLoadModelUtil;
import csci205_final_project.Game.FinalProjectGameSceneController;
import csci205_final_project.Model.Level;
import csci205_final_project.Model.Model;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
 */
public class GameLoadMenuController implements Initializable {

    @FXML
    private Button resume;

    private Model save1;
    private Model save2;
    private Model save3;
    private Model theModel;
    @FXML
    private Button loadSlot1;
    @FXML
    private Button loadSlot2;
    @FXML
    private Button loadSlot3;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // read the three save files and see if they are there.
        // if they are, deserialize them and put their information on the buttons.

        try {
            save1 = SaveAndLoadModelUtil.deserializeModel(
                    this.getClass().getClassLoader().getResource(
                            "ser/save1.ser").openConnection().getInputStream());

            this.loadSlot1.setText(save1.toString());
        } catch (IOException ex) {
            this.loadSlot1.setText("Empty Save Slot");
        } catch (ClassNotFoundException ex) {

        }

        try {
            save2 = SaveAndLoadModelUtil.deserializeModel(
                    this.getClass().getClassLoader().getResource(
                            "ser/save2.ser").openConnection().getInputStream());

            this.loadSlot2.setText(save2.toString());
        } catch (IOException ex) {

            this.loadSlot2.setText("Empty Save Slot");

        } catch (ClassNotFoundException ex) {

        }
        try {
            save3 = SaveAndLoadModelUtil.deserializeModel(
                    this.getClass().getClassLoader().getResource(
                            "ser/save3.ser").openConnection().getInputStream());
            this.loadSlot3.setText(save3.toString());
        } catch (IOException ex) {

            this.loadSlot3.setText("Empty Save Slot");
        } catch (ClassNotFoundException ex) {

        }
    }

    @FXML
    private void resumeToGame(ActionEvent event) {
        Stage stage = (Stage) this.resume.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void loadSlot1(ActionEvent event) throws IOException, FileNotFoundException, ClassNotFoundException {

        if (save1 == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/csci205_final_project/Game/finalProjectGameScene.fxml"));

        Parent root;
        BorderPane game = (BorderPane) loader.load();

        FinalProjectGameSceneController gameController = loader.<FinalProjectGameSceneController>getController();

        Stage stage;
        stage = (Stage) loadSlot1.getScene().getWindow();
        gameController.initData(save1.getTheme(), save1.getLevel());
        gameController.setTheModel(save1);
        TilePane tilePane = gameController.getTilePane();
        Level level = gameController.getLevel();
        gameController.startGameBoardWithMode(level);
        Scene scene = new Scene(game);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void loadSlot2(ActionEvent event
    ) throws IOException, FileNotFoundException, ClassNotFoundException {
        if (save2 == null) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/csci205_final_project/Game/finalProjectGameScene.fxml"));

        Parent root;
        BorderPane game = (BorderPane) loader.load();

        FinalProjectGameSceneController gameController = loader.<FinalProjectGameSceneController>getController();

        Stage stage;
        stage = (Stage) loadSlot2.getScene().getWindow();
        gameController.initData(save2.getTheme(), save2.getLevel());
        gameController.setTheModel(save2);
        TilePane tilePane = gameController.getTilePane();
        Level level = gameController.getLevel();
        gameController.startGameBoardWithMode(level);
        Scene scene = new Scene(game);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void loadSlot3(ActionEvent event
    ) throws IOException, FileNotFoundException, ClassNotFoundException {
        if (save3 == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/csci205_final_project/Game/finalProjectGameScene.fxml"));

        Parent root;
        BorderPane game = (BorderPane) loader.load();

        FinalProjectGameSceneController gameController = loader.<FinalProjectGameSceneController>getController();

        Stage stage;
        stage = (Stage) loadSlot3.getScene().getWindow();
        gameController.initData(save3.getTheme(), save3.getLevel());
        gameController.setTheModel(save3);
        TilePane tilePane = gameController.getTilePane();
        Level level = gameController.getLevel();
        gameController.startGameBoardWithMode(level);
        Scene scene = new Scene(game);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

}
