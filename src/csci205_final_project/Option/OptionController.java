/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 9, 2017
* Time: 9:31:13 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Option
* File: OptionController
* Description:
*
* ****************************************
 */
package csci205_final_project.Option;

import csci205_final_project.Game.FinalProjectGameSceneController;
import csci205_final_project.Model.Level;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class for option menu
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
 */
public class OptionController implements Initializable {

    ObservableList<String> themeList = FXCollections.observableArrayList(
            "pokeman", "professor", "ragecomics");
    ObservableList<String> levelList = FXCollections.observableArrayList(
            "Easy", "Medium", "Hard");

    @FXML
    private Slider volumeSlider;
    @FXML
    private Button returnBtn;
    @FXML
    private ComboBox themeBox;
    @FXML
    private ComboBox levelBox;
    @FXML
    private Button startGame;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        themeBox.setValue("pokeman");
        themeBox.setItems(themeList);

        levelBox.setValue("Easy");
        levelBox.setItems(levelList);
    }

    @FXML
    private void resume(ActionEvent event) throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) returnBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "/csci205_final_project/Menu/finalProjectMenu.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void start(ActionEvent event) throws IOException {
        String levelString;
        String themeString;
        themeString = (String) themeBox.getValue();
        levelString = (String) levelBox.getValue();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/csci205_final_project/Game/finalProjectGameScene.fxml"));

        Parent root;
        BorderPane option = (BorderPane) loader.load();

        FinalProjectGameSceneController gameController = loader.<FinalProjectGameSceneController>getController();

        Stage stage;
        stage = (Stage) returnBtn.getScene().getWindow();
        // default game values
        if (themeString == null) {
            themeString = "pokeman";

        }

        if (levelString == null) {
            levelString = Level.EASY.toString();
        }
        gameController.initData(themeString, levelString);
        gameController.createModel();
        Scene scene = new Scene(option);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

}
