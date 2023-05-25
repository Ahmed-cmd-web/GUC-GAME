package views;
import javafx.scene.text.Font;
import engine.Game;
import javafx.application.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
//import model.characters.Hero;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class el3ab extends Application {
	@Override
	public void start(Stage stage) {
		
		String css = this.getClass().getResource("application.css").toExternalForm();
		Pane root = new Pane();
		Scene screen=new Scene(root);
		screen.getStylesheets().add(css);
       
		VBox heros = new VBox();
		//heros.setTranslateX(1240);
		heros.setPrefSize(340,897);
		heros.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		root.getChildren().add(heros);
		
		VBox actions= new VBox(8);
		actions.setAlignment(Pos.BASELINE_CENTER);
		actions.setTranslateX(1240);
		actions.setPrefSize(345,897);
		actions.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		root.getChildren().add(actions);
		
		Button attack =new Button("Attack!");
		attack.setAlignment(Pos.CENTER);
		attack.setFont(Font.font("Old English Text MT",40));
		attack.setPrefWidth(270);
		attack.getStyleClass().add("sc3");	
		attack.setOnMouseEntered(event -> {
            attack.getStyleClass().removeAll("sc3");
			attack.getStyleClass().add("sc4");
        });
        attack.setOnMouseExited(event -> {
            attack.getStyleClass().remove("sc4");
			attack.getStyleClass().add("sc3");
        });
       	actions.getChildren().add(attack);
		attack.setOnAction(event -> {
			
		});
		
		Button cure =new Button("Cure");
		cure.setAlignment(Pos.CENTER);
		cure.setFont(Font.font("Old English Text MT",40));
		cure.setPrefWidth(270);
		cure.getStyleClass().add("sc3");	
		cure.setOnMouseEntered(event -> {
            cure.getStyleClass().removeAll("sc3");
			cure.getStyleClass().add("sc4");
        });
        cure.setOnMouseExited(event -> {
            cure.getStyleClass().remove("sc4");;
			cure.getStyleClass().add("sc3");
        });
       	actions.getChildren().add(cure);
		   cure.setOnAction(event -> {
			
		});

		Button special =new Button("Use Special");
		special.setAlignment(Pos.CENTER);
		special.setFont(Font.font("Old English Text MT",40));
		special.setPrefWidth(270);
		special.getStyleClass().add("sc3");	
		special.setOnMouseEntered(event -> {
            special.getStyleClass().removeAll("sc3");
			special.getStyleClass().add("sc4");
        });
        special.setOnMouseExited(event -> {
            special.getStyleClass().remove("sc4");;
			special.getStyleClass().add("sc3");
        });
       	actions.getChildren().add(special);
		   special.setOnAction(event -> {
			
		});

		VBox move = new VBox();
		move.setTranslateY(-100);
		move.setAlignment(Pos.CENTER);
		actions.getChildren().add(move);

		Button up =new Button("<");
		up.setRotate(90);
		up.setAlignment(Pos.CENTER);
		up.setFont(Font.font(25));
		//up.setPrefWidth(270);
		up.getStyleClass().add("sc3");	
		up.setOnMouseEntered(event -> {
            up.getStyleClass().removeAll("sc3");
			up.getStyleClass().add("sc4");
        });
        up.setOnMouseExited(event -> {
            up.getStyleClass().remove("sc4");;
			up.getStyleClass().add("sc3");
        });
       	move.getChildren().add(up);
		   up.setOnAction(event -> {
			
		});

		HBox h = new HBox(51);
		h.setAlignment(Pos.CENTER);
		move.getChildren().add(h);

		Button left =new Button("<");
		left.setAlignment(Pos.CENTER);
		left.setFont(Font.font(25));
		//left.setPrefWidth(270);
		left.getStyleClass().add("sc3");	
		left.setOnMouseEntered(event -> {
            left.getStyleClass().removeAll("sc3");
			left.getStyleClass().add("sc4");
        });
        left.setOnMouseExited(event -> {
            left.getStyleClass().remove("sc4");;
			left.getStyleClass().add("sc3");
        });
       	h.getChildren().add(left);
		   left.setOnAction(event -> {
			
		});

		Button right =new Button(">");
		right.setAlignment(Pos.CENTER);
		right.setFont(Font.font(25));
		//left.setPrefWidth(270);
		right.getStyleClass().add("sc3");	
		right.setOnMouseEntered(event -> {
            right.getStyleClass().removeAll("sc3");
			right.getStyleClass().add("sc4");
        });
        right.setOnMouseExited(event -> {
            right.getStyleClass().remove("sc4");;
			right.getStyleClass().add("sc3");
        });
       	h.getChildren().add(right);
		   right.setOnAction(event -> {
			
		});

		Button down =new Button(">");
		down.setRotate(90);
		down.setTextAlignment(TextAlignment.JUSTIFY);
		down.setAlignment(Pos.CENTER);
		down.setFont(Font.font(25));
		down.getStyleClass().add("sc3");	
		down.setOnMouseEntered(event -> {
            down.getStyleClass().removeAll("sc3");
			down.getStyleClass().add("sc4");
        });
        down.setOnMouseExited(event -> {
            down.getStyleClass().remove("sc4");;
			down.getStyleClass().add("sc3");
        });
       	move.getChildren().add(down);
		   down.setOnAction(event -> {
			
		});
		
		Button endT =new Button("End Turn");
		endT.setTranslateY(270);
		endT.setFont(Font.font("Old English Text MT",40));
		endT.setPrefWidth(320);
		endT.getStyleClass().add("endT");	
		endT.setOnMouseEntered(event -> {
            endT.setStyle("-fx-background-color:Red");
        });
        endT.setOnMouseExited(event -> {
            endT.setStyle("-fx-background-color:Brown");
        });
       	actions.getChildren().add(endT);
		   endT.setOnAction(event -> {
			
		});
		
		
		
		GridPane board = new GridPane();
		board.setTranslateX(340);

		board.setStyle("-fx-border-color:BLACK;"+"-fx-border-width: 3 0 0 3;");
		
		for(int i=0;i<225;i++) {
			Button cell = new Button();
			cell.setPrefSize(60,57);
			cell.getStyleClass().add("gridButton");
			board.getChildren().add(cell);
		}
		board.setPrefColumns(15);
		board.setPrefRows(15);
		root.getChildren().add(board);
		
		// for(int i=0;i<15;i++){
		// 	for(int j=0;j<15;j++){
		// 		Button button = new Button();
		// 		if(Game.map[i][j].isVisible()){
		// 			Image img = new Image("visibleGrass.jpg");
		// 			ImageView visImg = new ImageView(img);
		// 			//button.setBackground(img);
		// 			button.setGraphic(visImg);
		// 			board.add(button, i, j);
		// 		}
		// 	}
		// }
		
		//Image selectImg = new Image("fog.jpg");
		//BackgroundImage selectS = new BackgroundImage(selectImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO , false, false, false, true));
        //board.setBackground(new Background(selectS));
        stage.setWidth(1600);
        stage.setHeight(896);
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
