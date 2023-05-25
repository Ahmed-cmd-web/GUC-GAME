package views;
import javafx.scene.text.Font;
import javafx.application.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
//import model.characters.Hero;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;

public class el3ab extends Application {
	@Override
	public void start(Stage stage) {
		
		String css = this.getClass().getResource("application.css").toExternalForm();
		Pane root = new Pane();
		Scene screen=new Scene(root);
		screen.getStylesheets().add(css);
       
		
		StackPane actions= new StackPane();
		actions.setPrefSize(150,1080);
		actions.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		root.getChildren().add(actions);
		
		/*StackPane stats= new StackPane();
		stats.setTranslateY(900);
		stats.setPrefSize(1920,180);
		stats.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		Image statImg = new Image("greyBack.jpg");
		BackgroundImage statS = new BackgroundImage(statImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO , false, false, false, true));
        stats.setBackground(new Background(statS));
		root.getChildren().add(stats);*/
		
		/*StackPane rigth= new StackPane();
		rigth.setPrefWidth(200);
		root.getChildren().add(rigth);
		
		StackPane left= new StackPane();
		left.setPrefWidth(200);
		root.getChildren().add(left);
		
		root.getChildren().add(left);*/
		
		
		
		
		TilePane board = new TilePane();
		board.setTranslateX(360);
		board.setStyle("-fx-border-color:BLACK;"+"-fx-border-width: 0 0 0 3;");
		
		for(int i=0;i<225;i++) {
			Pane cell = new Pane();
			cell.setPrefSize(80,72);
			cell.getStyleClass().add("gridButton");
			board.getChildren().add(cell);
		}
		board.setPrefColumns(15);
		board.setPrefRows(15);
		root.getChildren().add(board);
		
		
		//Image selectImg = new Image("fog.jpg");
		//BackgroundImage selectS = new BackgroundImage(selectImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO , false, false, false, true));
        //board.setBackground(new Background(selectS));
        stage.setWidth(1600);
        stage.setHeight(900);
        stage.setFullScreen(true);
        stage.setResizable(true);
        stage.setTitle("Game Map");
		stage.setScene(screen);
        stage.show();
        
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
