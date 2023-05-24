package views;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import jaco.mp3.a.s;
import javafx.application.*;
import javafx.stage.Stage;
import model.characters.Hero;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class el3ab extends Application{
    
    public static Stage stage = new Stage();

    @Override
    public void start(Stage stage) {
        StackPane root1 = new StackPane();
		Scene startScreen=new Scene(root1);
        



        stage.setWidth(1000);
        stage.setHeight(540);
        stage.setResizable(false);
        //stage.setFullScreen(true);
        stage.setTitle("Last OF Us");
		stage.setScene(startScreen);
        stage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
