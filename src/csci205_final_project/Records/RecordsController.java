/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 25, 2017
* Time: 11:19:03 PM
*
* Project: csci205_final_project
* Package: Records
* File: RecordsController
* Description:
*
* ****************************************
 */
package csci205_final_project.Records;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hs031
 */
public class RecordsController implements Initializable {

    @FXML
    private Label first;
    @FXML
    private Label second;
    @FXML
    private Label third;
    @FXML
    private Button btnReturn;
    @FXML
    private ImageView challenger;
    @FXML
    private ImageView diamond;
    @FXML
    private ImageView gold;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image img = new Image(this.getClass().getClassLoader().getResource(
                "image/1.png").toString());
        challenger.setImage(img);

        img = new Image(this.getClass().getClassLoader().getResource(
                "image/2.png").toString());
        diamond.setImage(img);

        img = new Image(this.getClass().getClassLoader().getResource(
                "image/3.png").toString());
        gold.setImage(img);

        ArrayList<Label> records = new ArrayList<Label>();
        records.add(first);
        records.add(second);
        records.add(third);
        String content;

        File recordsFile = null;
        BufferedReader br = null;
        try {
            recordsFile = Paths.get(
                    this.getClass().getClassLoader().getResource(
                            "record/Records.txt").toURI()).toFile();
            br = new BufferedReader(new FileReader(recordsFile));
        } catch (FileNotFoundException ex) {
        } catch (URISyntaxException ex) {
            Logger.getLogger(RecordsController.class.getName()).log(Level.SEVERE,
                                                                    null, ex);
        }

        int i = 0;
        try {
            while ((content = br.readLine()) != null) {
                records.get(i).setText(content);
                i++;
            }
        } catch (IOException ex) {
        }
    }

    @FXML
    private void returnToMenu(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) btnReturn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource(
                "/csci205_final_project/Menu/finalProjectMenu.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/csci205_final_project/Menu/menu.css");
        stage.setScene(scene);
        stage.show();
    }

}
