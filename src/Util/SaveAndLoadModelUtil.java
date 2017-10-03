/* *****************************************
* CSCI205 - Software Engineering and Design
* Spring 2017
*
* Name: Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
* Date: Apr 16, 2017
* Time: 2:53:57 PM
*
* Project: csci205_final_project
* Package: Util
* File: SaveAndLoadModelUtil
* Description:
*
* ****************************************
 */
package Util;

import csci205_final_project.Model.Model;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * an utility class for saving and loading
 *
 * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
 */
public class SaveAndLoadModelUtil {

    /**
     * Deserializes a file. fileName is the name of the .ser file. Returns the
     * Model object that is deserialized.
     *
     * @param fileName
     * @return the deserialized Model
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     * @see
     * <a href="https://www.tutorialspoint.com/java/java_serialization.html">https://www.tutorialspoint.com/java/java_serialization.html</a>
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public static Model deserializeModel(InputStream fileIn) throws FileNotFoundException, IOException, ClassNotFoundException {
        Model myModel = null;
        try (final ObjectInputStream in = new ObjectInputStream(fileIn)) {
            myModel = (Model) in.readObject();
        }
        fileIn.close();
        return myModel;
    }

    /**
     * Serializes the ANN object to a file. myANN is the object being
     * serialized, and fileName is the .ser filename.
     *
     * @param myModel
     * @param dirName the directory of name.
     * @throws java.io.FileNotFoundException
     * @see
     * <a href="https://www.tutorialspoint.com/java/java_serialization.htm">https://www.tutorialspoint.com/java/java_serialization.htm</a>
     * @author Iris Fu, Haipu Sun, Junjie Jiang, Zilin Ma
     */
    public static void serializeModel(Model myModel, URL url) throws IOException, URISyntaxException {
        FileOutputStream fileOut = new FileOutputStream(
                Paths.get(url.toURI()).toFile());
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(myModel);
        out.close();
        fileOut.close();
    }
}
