package views;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.InputStream;

import engine.Game;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import model.characters.Direction;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;
import model.world.CollectibleCell;
//import model.characters.Hero;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;

public class el3ab extends Application {
	@Override
    public void start(Stage stage) {

        String css = this.getClass().getResource("application.css").toExternalForm();
        Pane root = new Pane();
        Scene screen = new Scene(root);
        screen.getStylesheets().add(css);

        StackPane actions = new StackPane();
        actions.setPrefSize(150, 1080);
        actions.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        root.getChildren().add(actions);

        GridPane board = new GridPane();
        board.setTranslateX(200);

        // InputStream input = getClass().getResourceAsStream("../assets/heroFaces/Bill.png");
        // //set the size for image
        // Image image = new Image(input, 30, 30, true, true);
        // ImageView imageView = new ImageView(image);
        // var button = new Button();
        // button.setPrefSize(20, 20);
        // imageView.fitHeightProperty().bind(button.heightProperty());
        // imageView.fitWidthProperty().bind(button.widthProperty());
        // imageView.setPreserveRatio(true);
        // button.setGraphic(imageView);
        // board.add(button, 0, 0, 1, 1);
        var h = new Fighter("jksc", 10, 10, 10);
        h.setActionsAvailable(1000);
        Game.startGame(h);
        rerenderMap2(board);
        screen.setOnKeyPressed(e -> {
            try {
                switch (e.getCode()) {
                    case A:
                        h.move(Direction.DOWN);
                        break;
                    case W:
                        h.move(Direction.RIGHT);
                        break;
                    case D:
                        h.move(Direction.UP);
                        break;
                    case S:
                        h.move(Direction.LEFT);
                        break;
                    default:
                        break;
                }
                rerenderMap2(board);
            } catch (Exception err) {
                // TODO: handle exception
                System.out.println(err.getMessage());
            }
        });

        board.setPrefSize(900, 400);

        root.getChildren().add(board);

        stage.setWidth(1600);
        stage.setHeight(896);
        //stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setTitle("Game Map");
        stage.setScene(screen);
        stage.show();

    }


    private void rerenderMap(GridPane board) {
        for (int x = 0; x < 15; x++)
            for (int y = 0; y < 15; y++) {
                var cell = Game.map[x][y];
                var button = new Button();
                button.setPrefSize(60, 60);
                button.setVisible(cell.isVisible());
                try {

                    button.setGraphic(null);
                    if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() instanceof Hero) {
                        button.setGraphic(getScaledImage("../assets/heroFaces/" + cell.getCellImage(), 60, 60));
                    } else if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() instanceof Zombie)
                        button.setGraphic(getScaledImage("../assets/" + cell.getCellImage(), 30, 30));
                    else if (cell instanceof CollectibleCell)
                        button.setGraphic(getScaledImage("../assets/" + cell.getCellImage(), 30, 30));
                    board.add(button, x, 14 - y, 1, 1);
                } catch (Exception e) {

                    System.out.println(e.getMessage());
                }
            }
    }


       private void rerenderMap2(GridPane board) {
        for (int x = 0; x < 15; x++)
            for (int y = 0; y < 15; y++) {
                var cell = Game.map[x][y];
                var empty = new Button();
                empty.setPrefSize(40, 40);
                empty.setMinSize(40, 40);
                empty.setMaxSize(40, 40);
                ImageView img=null;
                try {

                    if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() instanceof Hero )
                        img=getScaledImage("../assets/heroFaces/"+cell.getCellImage(), 40, 40);
                    else if (cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() instanceof Zombie)
                        img=getScaledImage("../assets/"+cell.getCellImage(), 40, 40);
                    else if (cell instanceof CollectibleCell)
                        img = getScaledImage("../assets/" + cell.getCellImage(), 40, 40);
                    if (img != null)
                        img.setEffect(new DropShadow(2, Color.BLACK));
                    board.add(img==null || !cell.isVisible()?empty:img, x, 14 - y, 1, 1);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
    }

    private ImageView getScaledImage(String path, int width, int height) {
        InputStream input = getClass().getResourceAsStream(path);
        Image image = new Image(input, width, height, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setCache(true);
        return imageView;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
