/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 9, 2017
* Time: 8:59:49 PM
*
* Project: csci205_final_project
* Package: csci205_final_project
* File: FinalProjectMenuController
* Description:
*
* ****************************************
 */
package csci205_final_project.Menu;

import Util.SaveAndLoadModelUtil;
import csci205_final_project.Model.Level;
import csci205_final_project.Model.Model;
import csci205_final_project.Tutorial.TutorialController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
 */
public class FinalProjectMenuController implements Initializable {

    @FXML
    private Button btnStart;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnTutorial;
    @FXML
    private Button btnRecords;
    @FXML
    private Button btnExit;
    @FXML
    private VBox background;
    @FXML
    private ImageView menuImage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Image woodenTexture = new Image(getClass().getResourceAsStream(
        //        "../images/wooden.jpg"));
        //btnStart.setGraphic(new ImageView(woodenTexture));
        Image img = new Image(this.getClass().getClassLoader().getResource(
                "image/d.png").toString());
        menuImage.setImage(img);
    }

    @FXML
    void startNewGame() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnStart.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "/csci205_final_project/Option/option.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loadGame() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnLoad.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "/csci205_final_project/GameLoadMenu/GameLoadMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openTutorial() throws IOException, FileNotFoundException, ClassNotFoundException {

        Model tutorial1 = SaveAndLoadModelUtil.deserializeModel(
                this.getClass().getClassLoader().getResource(
                        "tutorials/tutorial1.ser").openConnection().getInputStream());

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/csci205_final_project/Tutorial/finalProjectTutorial.fxml"));

        Parent root;
        BorderPane game = (BorderPane) loader.load();

        TutorialController gameController = loader.<TutorialController>getController();

        Stage stage;
        stage = (Stage) btnTutorial.getScene().getWindow();
        gameController.initData(tutorial1.getTheme(), tutorial1.getLevel());
        gameController.setTheModel(tutorial1);
        TilePane tilePane = gameController.getTilePane();
        Level level = gameController.getLevel();
        gameController.startGameBoardWithMode(level);
        Scene scene = new Scene(game);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
        gameController.runHint();
    }

    void openOptions() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnStart.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "/csci205_final_project/Option/option.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openRecords() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnRecords.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "/csci205_final_project/Records/Records.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Exit() {
        Platform.exit();
    }

    public Button getBtnStart() {
        return btnStart;
    }

    public Button getBtnLoad() {
        return btnLoad;
    }

    public Button getBtnTutorial() {
        return btnTutorial;
    }

    public Button getBtnRecords() {
        return btnRecords;
    }

    public Button getBtnExit() {
        return btnExit;
    }

    public VBox getBackground() {
        return background;
    }

}
