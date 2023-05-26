package views;
import javafx.scene.text.Font;

import java.io.InputStream;
import java.util.Arrays;

import engine.Game;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import utils.AppPopup;
//import model.characters.Hero;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Stop;

public class el3ab extends Application {
    private Hero currentHero = Game.currentHero;


	@Override
    public void start(Stage stage) {


        String css = this.getClass().getResource("application.css").toExternalForm();
        Pane root = new Pane();
        Scene screen = new Scene(root);
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("../assets/startScreen.jpg")),
        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
          BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        screen.getStylesheets().add(css);

        GridPane board = new GridPane();
        board.setTranslateX(340);
        board.setStyle("-fx-border-color:BLACK;" + "-fx-border-width: 3 3 3 3;");

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
        up.setOnMouseClicked(event -> {
            try {
                currentHero.move(Direction.RIGHT);
                this.updateCellsVisibility(board);
            } catch (Exception e) {
               new AppPopup(e.getMessage(), stage).open();
            }

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
        left.getStyleClass().add("sc3");
        left.setOnMouseClicked(event -> {
            try {
                currentHero.move(Direction.DOWN);
                updateCellsVisibility(board);
            } catch (Exception e) {
                new AppPopup(e.getMessage(), stage).open();

            }

        });

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
        right.setOnMouseClicked(event -> {
            try {
                currentHero.move(Direction.UP);
                this.updateCellsVisibility(board);
            } catch (Exception e) {
              new AppPopup(e.getMessage(), stage).open();

            }

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
        down.setOnMouseClicked(event -> {
            try {
                currentHero.move(Direction.LEFT);
                this.updateCellsVisibility(board);
            } catch (Exception e) {
               new AppPopup(e.getMessage(), stage).open();
            }

        });

        move.getChildren().add(down);

        ProgressBar pb = new ProgressBar(currentHero.getCurrentHp()/currentHero.getMaxHp());

	    pb.setStyle("-fx-accent: transparent; -fx-control-inner-background: transparent; -fx-background-color: transparent;");
        pb.setStyle("-fx-accent: RED ");


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
        endT.setOnMouseClicked(e -> {
            try {
                Game.endTurn();
                createMap(board);
                pb.setProgress(currentHero.getCurrentHp() / (currentHero.getMaxHp()*1.0));
            } catch (Exception err) {
                new AppPopup(err.getMessage(), stage).open();
            }
        });

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
	    // LinearGradient linear = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);





		pb.setPrefSize(200, 20);
		info.getChildren().add(pb);

        currentHero.setActionsAvailable(1000);

        Game.startGame(currentHero);
        createMap(board);
        screen.setOnKeyPressed(e -> {
            try {
                switch (e.getCode()) {
                    case A:
                        currentHero.move(Direction.DOWN);
                        break;
                    case W:
                        currentHero.move(Direction.RIGHT);
                        break;
                    case D:
                        currentHero.move(Direction.UP);
                        break;
                    case S:
                        currentHero.move(Direction.LEFT);
                        break;
                    default:
                        break;
                }
                updateCellsVisibility(board);
            } catch (Exception err) {
                new AppPopup(err.getMessage(), stage).open();
            }
        });


        root.getChildren().add(board);


        stage.setWidth(1600);
        stage.setHeight(896);
        stage.setResizable(false);
        stage.setTitle("Game Map");
        stage.setScene(screen);
        stage.show();

    }


    private void createMap(GridPane board) {
        for (int x = 0; x < 15; x++)
            for (int y = 0; y < 15; y++) {
                var cell = Game.map[x][y];
                var img=setCellImage(cell);
                board.add(img, x, 14 - y, 1, 1);
            }
    }


     private void updateCellsVisibility(GridPane board) {
        var p = currentHero.getLocation();
        for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
                if ((i + p.x) <= 14 && (j + p.y) <= 14 && (i + p.x) >= 0 && (j + p.y) >= 0) {
                    int cx = p.x + i;
                    int cy = p.y + j;
                    var cell = Game.map[cx][cy];
                    var img=setCellImage(cell);
                    board.getChildren().removeIf(e->GridPane.getColumnIndex(e)==cx && GridPane.getRowIndex(e)==14-cy);
                    board.add(img, cx, 14-cy, 1, 1);
				}
    }


    private ImageView setCellImage(Cell cell) {
        ImageView img = getScaledImage("../assets/visibleGrass.png", 60, 57);
        if (cell.isVisible()) {
            if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() instanceof Hero)
                img = getScaledImage("../assets/heroFaces/" + validateNaming(cell.getCellImage()) , 60, 57);
            else if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() instanceof Zombie)
                img = getScaledImage("../assets/" + cell.getCellImage(), 60, 57);
            else if (cell instanceof CollectibleCell)
                img = getScaledImage("../assets/" + cell.getCellImage(), 60, 57);
            }
            else
                img = getScaledImage("../assets/Darkgrass.png", 60, 57);
            img.setEffect(new DropShadow(2, Color.BLACK));
            return img;
    }

    private String validateNaming(String text) {
        if (text.contains(" "))
            return text.split(" ")[0] + ".png";
        return text;
    }

    private ImageView getScaledImage(String path, int width, int height) {
        InputStream input = getClass().getResourceAsStream(path);
        Image image = new Image(input, width-4, height, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setCache(true);
        return imageView;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
