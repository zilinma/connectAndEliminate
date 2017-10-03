/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: May 1, 2017
* Time: 9:38:02 PM
*
* Project: csci205_final_project
* Package: csci205_final_project.Tutorial
* File: TutorialController
* Description:
*
* ****************************************
 */
package csci205_final_project.Tutorial;

import Util.AudioUtil;
import Util.SaveAndLoadModelUtil;
import csci205_final_project.Model.Level;
import csci205_final_project.Model.Model;
import csci205_final_project.Model.Tile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hs031
 */
public class TutorialController implements Initializable {

    @FXML
    private Button btnPause;
    @FXML
    private Button btnShuffle;
    @FXML
    private Button btnHint;
    @FXML
    private Label labelScore;
    @FXML
    private Label labelLevel;
    @FXML
    private Label labelShuffle;
    @FXML
    private Label labelHint;
    @FXML
    private TilePane tilePane;
    @FXML
    private BorderPane parentPane;
    @FXML
    private Button btnExit;

    private Level level;
    private Model theModel;
    private Thread th;
    public Tile selectedTile;
    public Rectangle selectedRectangle;
    public int numOfSelections = 0;
    private String theme;
    private ArrayList<ArrayList<Rectangle>> data;
    private int levelNum = 0;
    private boolean gg;
    private ArrayList<ArrayList<Integer>> connectTiles = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AudioUtil.playMusic(this.getClass().getClassLoader().getResource(
                "sound/music.wav"));
        beginTimer();
        data = new ArrayList<>();
    }

    /**
     * a method used to begin a timer
     *
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void beginTimer() {
        // start timer
        gg = false;
        Task<Void> task;
        task = new Task<Void>() {
            @Override
            public Void call() {
                // 2 minutes
                for (int i = 0; i < 240; i++) {
                    try {
                        th.sleep(500);
                    } catch (InterruptedException e) {
                        gg = true;
                        break;
                    }
                    if (connectTiles != null) {
                        drawLine(connectTiles, 0);
                    }

                    updateProgress(i + 1, 240);
                }
                return null;
            }

        };

        th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    /**
     * Initialize the model
     *
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void createModel() {
        theModel = new Model(level, theme);
        startGameBoardWithMode(theModel.getLevel());
        labelScore.setText(String.format("%d", theModel.getScore()));
        labelHint.setText(String.format("%d", theModel.getHintChance()));
        labelShuffle.setText(String.format("%d", theModel.getShuffleChance()));
        labelLevel.setText(String.format("%d", levelNum));
    }

    /**
     * Load saved model
     *
     * @param model
     */
    public void loadModel(Model model) {
        startGameBoardWithMode(model.getLevel());
        labelScore.setText(String.format("%d", theModel.getScore()));
        labelHint.setText(String.format("%d", theModel.getHintChance()));
        labelShuffle.setText(String.format("%d", theModel.getShuffleChance()));
        labelLevel.setText(String.format("%d", levelNum));
    }

    /**
     * Update theme and level
     *
     * @param themeString
     * @param levelString
     */
    public void initData(String themeString, String levelString) {
        this.theme = themeString;
        if (levelString.equals("Easy")) {
            level = Level.EASY;
        }
        else if (levelString.equals("Medium")) {
            level = Level.MEDIUM;
        }
        else if (levelString.equals("Hard")) {
            level = Level.HARD;
        }

    }

    /**
     * Update theme and level
     *
     * @param themeString
     * @param level
     */
    public void initData(String themeString, Level level) {
        this.theme = themeString;
        this.level = level;
    }

    @FXML
    private void btnPause(ActionEvent event) throws IOException, InterruptedException {

    }

    @FXML
    private void btnShuffle(ActionEvent event) {
        if (!gg) {
            theModel.shuffle();
            Level level = theModel.getLevel();
            tilePane.getChildren().clear();
            startGameBoardWithMode(level);
            labelShuffle.setText(
                    String.format("%d", theModel.getShuffleChance()));
        }
    }

    @FXML
    private void btnHint(ActionEvent event) {
        if (!gg) {
            ArrayList<Tile> result = theModel.hint();
            if (result != null) {
                Tile a = result.get(0);
                Tile b = result.get(1);
                data.get(a.getPosY()).get(a.getPosX()).setOpacity(0.3);
                data.get(b.getPosY()).get(b.getPosX()).setOpacity(0.3);
            }
            else {
                this.btnShuffle(event);
            }
            labelHint.setText(String.format("%d", theModel.getHintChance()));
        }
    }

    public void runHint() {
        ArrayList<Tile> result = theModel.hint();
        if (result != null) {
            Tile a = result.get(0);
            Tile b = result.get(1);
            data.get(a.getPosY()).get(a.getPosX()).setOpacity(0.3);
            data.get(b.getPosY()).get(b.getPosX()).setOpacity(0.3);
        }
        else {
            theModel.shuffle();
            Level level = theModel.getLevel();
            tilePane.getChildren().clear();
            startGameBoardWithMode(level);
        }
    }

    /**
     * Initialize game with a level. Have to initialize game first.
     *
     * @param level
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void startGameBoardWithMode(Level level) {
        connectTiles = null;
        setupTilePane(level);
        data = new ArrayList();
        for (int i = 0; i < level.getHeight() + 2; i++) {
            ArrayList<Rectangle> row = new ArrayList();
            for (int j = 0; j < level.getWidth() + 2; j++) {
                Tile aTile = theModel.getData().get(i).get(j);
                Rectangle aRectangle = new Rectangle(50, 50);
                aRectangle.setOnMouseClicked((MouseEvent eventB) -> {
                    try {
                        selectRectangle(aRectangle, aTile);
                    } catch (IOException ex) {

                    } catch (ClassNotFoundException ex) {

                    }
                });
                if (aTile != null) {
                    Image img = new Image(aTile.getImgName());
                    aRectangle.setFill(new ImagePattern(img));
                }
                else {
                    aRectangle.setOpacity(0);
                    aRectangle.setFill(Color.BLUE);
                }
                tilePane.getChildren().add(aRectangle);
                row.add(aRectangle);
            }
            data.add(row);
        }
    }

    public void setupTilePane(Level level) {
        tilePane.setPrefColumns(level.getWidth() + 2);
        tilePane.setPrefRows(level.getHeight() + 2);
        tilePane.setPrefWidth(50 * (level.getWidth() + 2));
        tilePane.setPrefHeight(50 * (level.getHeight() + 2));
        tilePane.setMaxSize(50 * (level.getWidth() + 2),
                            50 * (level.getHeight() + 2));
    }

    /**
     * a method to select a rectangle and link that to the tile in the
     * background
     *
     * @param aRectangle
     * @param aTile
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public void selectRectangle(Rectangle aRectangle, Tile aTile) throws IOException, FileNotFoundException, ClassNotFoundException {
        if (aRectangle.getOpacity() == 0) {
            return;
        }

        if (numOfSelections % 2 == 0) {
            selectedTile = aTile;
            selectedRectangle = aRectangle;
            selectedRectangle.setOpacity(0.5); // set the opacity to show that this rectangle is selected.
            numOfSelections++;
            //playMusic("audioeff/blomark.wav");
        }
        else if (numOfSelections % 2 == 1) {

            selectedRectangle.setOpacity(1); // set the opacity back in case there is no path between the current one and the next one.

            connectTiles = theModel.findPath(selectedTile, aTile);
            if (connectTiles != null) {
                theModel.removeTile(selectedTile, aTile);
                // make the tiles invisible.
                selectedRectangle.setFill(Color.BLUE);
                aRectangle.setFill(Color.BLUE);
                drawLine(connectTiles, 0.2);
                ArrayList<Tile> result = theModel.hint();
                if (result != null) {
                    Tile a = result.get(0);
                    Tile b = result.get(1);
                    data.get(a.getPosY()).get(a.getPosX()).setOpacity(0.3);
                    data.get(b.getPosY()).get(b.getPosX()).setOpacity(0.3);
                    theModel.setHintChance(2);
                }
                else {
                    theModel.shuffle();
                    Level level = theModel.getLevel();
                    tilePane.getChildren().clear();
                    startGameBoardWithMode(level);
                    theModel.setShuffleChance(2);
                }
                if (theModel.getTotalSize() == 0) {
                    Model tutorial2 = SaveAndLoadModelUtil.deserializeModel(
                            this.getClass().getClassLoader().getResource(
                                    "tutorials/tutorial2.ser").openConnection().getInputStream());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(
                            "/csci205_final_project/Tutorial/finalProjectTutorial.fxml"));

                    Parent root;
                    BorderPane game = (BorderPane) loader.load();

                    TutorialController gameController = loader.<TutorialController>getController();

                    Stage stage;
                    stage = (Stage) btnHint.getScene().getWindow();
                    gameController.initData(tutorial2.getTheme(),
                                            tutorial2.getLevel());
                    gameController.setTheModel(tutorial2);
                    TilePane tilePane = gameController.getTilePane();
                    Level level = gameController.getLevel();
                    gameController.startGameBoardWithMode(level);
                    Scene scene = new Scene(game);
                    scene.getStylesheets().add(
                            "/csci205_final_project/Menu/menu.css");
                    stage.setScene(scene);
                    stage.show();
                    gameController.runHint();
                }
            }
            else { // if there is no path between them, change the next selected tile as selected.
                selectedRectangle.setOpacity(1);
                selectedRectangle = aRectangle;
                selectedTile = aTile;
                selectedRectangle.setOpacity(1);

                //playMusic("audioeff/blomark.wav");
            }

            numOfSelections++;
        }
    }

    /**
     * Show or hide the tiles on the path
     *
     * @param coordinates - turning points of the path stored in groups (x, y)
     * @param opacity - a double from 0 to 1
     * @author Iris Fu
     */
    private void drawLine(ArrayList<ArrayList<Integer>> coordinates,
                          double opacity) {
        for (int i = 0; i < coordinates.size() - 1; i++) {
            if (coordinates.get(i).get(1) < coordinates.get(i + 1).get(1)) {
                for (int j = coordinates.get(i).get(1); j <= coordinates.get(
                     i + 1).get(1); j++) {
                    data.get(j).get(coordinates.get(i).get(0)).setOpacity(
                            opacity);
                }
            }
            else if (coordinates.get(i).get(1) > coordinates.get(i + 1).get(1)) {
                for (int j = coordinates.get(i + 1).get(1); j <= coordinates.get(
                     i).get(1); j++) {
                    data.get(j).get(coordinates.get(i).get(0)).setOpacity(
                            opacity);
                }
            }
            else if (coordinates.get(i).get(0) < coordinates.get(
                    i + 1).get(0)) {
                for (int j = coordinates.get(i).get(0); j <= coordinates.get(
                     i + 1).get(0); j++) {
                    data.get(coordinates.get(i).get(1)).get(j).setOpacity(
                            opacity);
                }
            }
            else {
                for (int j = coordinates.get(i + 1).get(0); j <= coordinates.get(
                     i).get(0); j++) {
                    data.get(coordinates.get(i).get(1)).get(j).setOpacity(
                            opacity);
                }
            }
        }
    }

    @FXML
    private void btnExit(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnExit.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "/csci205_final_project/Menu/finalProjectMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

    public TilePane getTilePane() {
        return tilePane;
    }

    public void setTilePane(TilePane tilePane) {
        this.tilePane = tilePane;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Model getTheModel() {
        return theModel;
    }

    public void setTheModel(Model theModel) {
        this.theModel = theModel;
    }

    @FXML
    private void labelScore(MouseEvent event) {
    }

    @FXML
    private void labelLevel(MouseEvent event) {
    }

}
