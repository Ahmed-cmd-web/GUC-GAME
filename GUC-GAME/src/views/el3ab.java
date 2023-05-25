package views;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
        
		Pane root = new Pane();
		Scene screen=new Scene(root);
		root.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
		TilePane board = new TilePane();
		//board.setTranslateX(200);
		//board.setTranslateY(70);
		//Scene a7a = new Scene(board);
		//board.setPrefSize(1142, 619);
		board.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		for(int i=0;i<225;i++) {
			Button button = new Button();
			button.setPrefSize(45,45);
			board.getChildren().add(button);
		}
		board.setHgap(1);
		board.setVgap(1);
		board.setPrefColumns(15);
		board.setPrefRows(15);
		root.getChildren().add(board);
		
		
		
		
		
		
		/*VBox actions= new VBox();
		actions.setPrefWidth(120);
		actions.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		root.setRight(actions);
		
		HBox bottom = new HBox();
		bottom.setPrefHeight(100);
		//bottom.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		root.setBottom(bottom);
		
		HBox stats= new HBox();
		stats.setPrefSize(1142,100);
		stats.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		bottom.getChildren().add(stats);
		
		HBox exit = new HBox();
		exit.setPrefSize(120, 100);
		exit.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		bottom.getChildren().add(exit);*/
		
		
		
		
		
		
		
		
		
		
		
		
		
        
		
		
       // stage.setWidth(1000);
       // stage.setHeight(900);
        //stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setTitle("Game Map");
		stage.setScene(screen);
        stage.show();
        
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
