package views;
import javafx.scene.text.Font;
import javafx.application.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
//import model.characters.Hero;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.application.Application;  
import javafx.scene.layout.VBox;   
import javafx.scene.paint.CycleMethod;  
import javafx.scene.paint.LinearGradient;  
import javafx.scene.paint.Stop;  
import javafx.scene.shape.Rectangle; 

public class el3ab extends Application {
	@Override
	public void start(Stage stage) {
		
		String css = this.getClass().getResource("application.css").toExternalForm();
		Pane root = new Pane();
		Scene screen=new Scene(root);
		screen.getStylesheets().add(css);
       
		
		
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
		// Image img = new Image("visibleGrass.jpg");
		// ImageView iv = new ImageView(img);
		// //special.setGraphic(iv);

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
		
		//Fighter f= new Fighter("5anzeer", 200, 40, 1000);
		//Game.startGame(f);
		
		
		GridPane board = new GridPane();
		board.setTranslateX(340);
		board.setStyle("-fx-border-color:BLACK;"+"-fx-border-width: 3 3 3 3;");
		Image img = new Image("visibleGrass.jpg",60,57,true,true);
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				Button cell = new Button();
				cell.getStyleClass().add("gridButton");
				cell.setPrefSize(60,57);
				cell.setMinSize(60, 57);
        		cell.setMaxSize(60, 57);
				cell.setStyle("-fx-border-color:BLACK;"+"-fx-border-width: 1 1 1 1;");
				board.add(cell, i, j);
				//if(Game.map[i][j].isVisible()){
					//cell.setStyle("-fx-background-color:green;");
					//var imgV =new ImageView(img);
					//imgV.setFitWidth(57);
					//imgV.setFitWidth(60);
					//imgV.setPreserveRatio(true);
					//cell.setGraphic(imgV);
					
				//}
			}
		}
		root.getChildren().add(board);
		
		
		VBox heros = new VBox();
		heros.setPrefSize(341,897);
		heros.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		root.getChildren().add(heros);
		
		HBox all =new HBox();
		heros.getChildren().add(all);
		
		VBox info =new VBox();
		info.setAlignment(Pos.BASELINE_LEFT);
		all.getChildren().add(info);
		
		
	    Stop[] stops = new Stop[] { new Stop(0, Color.GREEN), new Stop(1, Color.BLUE)};  
	    LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
		
	    ProgressBar pb = new ProgressBar(100);
	   
	    pb.setStyle("-fx-accent: transparent; -fx-control-inner-background: transparent; -fx-background-color: transparent;");
        //pb.lookup(".bar").setStyle("-fx-background-color: transparent;");
       // pb.lookup(".track").setStyle("-fx-background-color: transparent;");
        pb.setProgress(0.7); // Set the progress value (0.0 to 1.0)
        pb.setStyle("-fx-accent: RED ");
        
        
        
		pb.setPrefSize(200, 20);
		info.getChildren().add(pb);
		
		
		
		
		
		
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
