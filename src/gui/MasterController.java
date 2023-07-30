/*
  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Priya
 */
public class MasterController extends Application {
    
    public static Stage primaryStage;
    public static boolean IS_DARK_THEME = true;
    public MasterController()
    {
        primaryStage = null;
    }
    
    @Override
    public void start(Stage stage)
    {
        
        primaryStage = stage;
        
	//primaryStage.setWidth(700);
        //primaryStage.setHeight(500);
        /*AnchorPane root = new AnchorPane();
        Text text = new Text("Deepanshu Sahu");
        root.getChildren().add(text);
        text.setLayoutX(100);
        text.setLayoutY(100);
        Scene scene = new Scene(root);
        */
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("mainframe/mainframe_1.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setScene(scene);
            /*loading the welcome page*/
//        loadLoginPage();
///        MySQL.makeConnection("timetable", "root", "");
	try{
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
	primaryStage.setTitle("Mainframe");
	primaryStage.show();
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        primaryStage.setMaximized(true);
        
	//new Bounce(text).setDelay(Duration.ONE).play();
       
    }
    public void loadLoginPage()
    {
	//primaryStage.setScene(new LoginPage().loadLoginPage(primaryStage));
    }

 
    public static void main(String[] args) {
        launch(args);
    }
    
}
