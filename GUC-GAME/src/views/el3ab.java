package views;
import javafx.scene.text.Font;

import java.io.InputStream;
import java.util.Map;

import engine.Game;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import utils.AppPopup;
import utils.CPU;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.layout.VBox;

public class el3ab extends Application {
	public Hero currentHero = Game.currentHero;
    private Map<KeyCode,Direction> keyMappings = Map.of(
                        KeyCode.A, Direction.DOWN,
                        KeyCode.W, Direction.RIGHT,
                        KeyCode.D, Direction.UP,
                        KeyCode.S, Direction.LEFT
    );
    public Button attack;
    public Button cure;
    public Button special;
    public Button up;
    public Button down;
    public Button right;
    public Button left;
    public Button endTurn;
    private CPU cpu = new CPU(this);
    public static boolean useCPU = false;

	@Override
    public void start(Stage stage) {
        Game.startGame(currentHero);
        String css = this.getClass().getResource("application.css").toExternalForm();
        Pane root = new Pane();
        Scene screen = new Scene(root);
        BackgroundImage myBI = new BackgroundImage(
                new Image(getClass().getResourceAsStream("../assets/startScreen.jpg")),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        screen.getStylesheets().add(css);

        GridPane board = new GridPane();
        board.setTranslateX(340);
        board.setStyle("-fx-border-color:BLACK;" + "-fx-border-width: 3 3 3 3;");

        VBox herosLeftPanel = new VBox();
        herosLeftPanel.setStyle("-fx-border-color:BLACK;" + "-fx-border-width: 3 3 3 3;");
        herosLeftPanel.setPrefSize(341, 897);

        VBox actions = new VBox(8);
        actions.setAlignment(Pos.BASELINE_CENTER);
        actions.setTranslateX(1240);
        actions.setPrefSize(345, 897);
        actions.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        root.getChildren().add(actions);

        attack = new Button("Attack!");
        attack.setAlignment(Pos.CENTER);
        attack.setFont(Font.font("Old English Text MT", 40));
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
            try {
                attack.getStyleClass().removeAll("sc3");
            attack.getStyleClass().add("sc4");
                currentHero.attack();
                this.updateCellsVisibility(board);
                revalidateHerosPanel(herosLeftPanel, root);
                isFinished(stage);
                 PauseTransition pause = new PauseTransition(Duration.seconds(0.500));
            pause.setOnFinished(j -> {
                    attack.getStyleClass().remove("sc4");
            attack.getStyleClass().add("sc3");
                });
                pause.play();
            } catch (Exception e) {
                new AppPopup(e.getMessage(), stage).open();
            }
        });

        cure = new Button("Cure");
        cure.setAlignment(Pos.CENTER);
        cure.setFont(Font.font("Old English Text MT", 40));
        cure.setPrefWidth(270);
        cure.getStyleClass().add("sc3");
        cure.setOnMouseEntered(event -> {
            cure.getStyleClass().removeAll("sc3");
            cure.getStyleClass().add("sc4");
        });
        cure.setOnMouseExited(event -> {
            cure.getStyleClass().remove("sc4");
            cure.getStyleClass().add("sc3");
        });
        cure.setOnAction(event -> {
            try {
                currentHero.cure();
                this.updateCellsVisibility(board);
                revalidateHerosPanel(herosLeftPanel, root);
                isFinished(stage);
            } catch (Exception e) {
                new AppPopup(e.getMessage(), stage).open();
            }
        });
        actions.getChildren().add(cure);

        special = new Button("Use Special");
        special.setAlignment(Pos.CENTER);
        special.setFont(Font.font("Old English Text MT", 40));
        special.setPrefWidth(270);
        special.getStyleClass().add("sc3");
        special.setOnMouseEntered(event -> {
            special.getStyleClass().removeAll("sc3");
            special.getStyleClass().add("sc4");
        });
        special.setOnMouseExited(event -> {
            special.getStyleClass().remove("sc4");
            special.getStyleClass().add("sc3");
        });
        actions.getChildren().add(special);
        special.setOnAction(e -> {
            try {
                currentHero.useSpecial();
                if (currentHero instanceof Explorer)
                    createMap(board);
                updateCellsVisibility(board);
                revalidateHerosPanel(herosLeftPanel, root);
                isFinished(stage);
            } catch (Exception err) {
                new AppPopup(err.getMessage(), stage).open();
            }
        });

        VBox move = new VBox();
        move.setTranslateY(-100);
        move.setAlignment(Pos.CENTER);
        actions.getChildren().add(move);

        up = new Button("<");
        up.setRotate(90);
        up.setAlignment(Pos.CENTER);
        up.setFont(Font.font(25));
        up.getStyleClass().add("sc3");
        up.setOnMouseEntered(event -> {
            up.getStyleClass().removeAll("sc3");
            up.getStyleClass().add("sc4");
        });
        up.setOnMouseExited(event -> {
            up.getStyleClass().remove("sc4");
            up.getStyleClass().add("sc3");
        });

        move.getChildren().add(up);

        HBox h = new HBox(51);
        h.setAlignment(Pos.CENTER);
        move.getChildren().add(h);

        left = new Button("<");
        left.setAlignment(Pos.CENTER);
        left.setFont(Font.font(25));
        left.getStyleClass().add("sc3");


        left.setOnMouseEntered(event -> {
            left.getStyleClass().removeAll("sc3");
            left.getStyleClass().add("sc4");
        });
        left.setOnMouseExited(event -> {
            left.getStyleClass().remove("sc4");
            left.getStyleClass().add("sc3");
        });
        h.getChildren().add(left);

        right = new Button(">");
        right.setAlignment(Pos.CENTER);
        right.setFont(Font.font(25));
        right.getStyleClass().add("sc3");
        right.setOnMouseEntered(event -> {
            right.getStyleClass().removeAll("sc3");
            right.getStyleClass().add("sc4");
        });
        right.setOnMouseExited(event -> {
            right.getStyleClass().remove("sc4");
            right.getStyleClass().add("sc3");
        });

        h.getChildren().add(right);

        down = new Button(">");
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
            down.getStyleClass().remove("sc4");
            down.getStyleClass().add("sc3");
        });
        move.getChildren().add(down);


        left.setOnAction(event -> {
            left.getStyleClass().removeAll("sc3");
            left.getStyleClass().add("sc4");
            moveRoutine(stage, herosLeftPanel, root, board, KeyCode.A);
            PauseTransition pause = new PauseTransition(Duration.seconds(0.500));
            pause.setOnFinished(j -> {
                    left.getStyleClass().remove("sc4");
            left.getStyleClass().add("sc3");
                });
                pause.play();
        });
        right.setOnAction(event -> {
            right.getStyleClass().removeAll("sc3");
            right.getStyleClass().add("sc4");
            moveRoutine(stage, herosLeftPanel, root, board, KeyCode.D);
             PauseTransition pause = new PauseTransition(Duration.seconds(0.500));
            pause.setOnFinished(j -> {
                    right.getStyleClass().remove("sc4");
            right.getStyleClass().add("sc3");
                });
                pause.play();
        });
        up.setOnAction(event -> {
            up.getStyleClass().removeAll("sc3");
            up.getStyleClass().add("sc4");
            moveRoutine(stage, herosLeftPanel, root, board, KeyCode.W);
             PauseTransition pause = new PauseTransition(Duration.seconds(0.500));
            pause.setOnFinished(j -> {
                    up.getStyleClass().remove("sc4");
            up.getStyleClass().add("sc3");
                });
                pause.play();
        });
        down.setOnAction(event -> {
            down.getStyleClass().removeAll("sc3");
            down.getStyleClass().add("sc4");
            moveRoutine(stage, herosLeftPanel, root, board, KeyCode.S);
             PauseTransition pause = new PauseTransition(Duration.seconds(0.500));
            pause.setOnFinished(j -> {
                    down.getStyleClass().remove("sc4");
            down.getStyleClass().add("sc3");
                });
                pause.play();
        });

        revalidateHerosPanel(herosLeftPanel, root);

        endTurn = new Button("End Turn");
        endTurn.setTranslateY(270);
        endTurn.setFont(Font.font("Old English Text MT", 40));
        endTurn.setPrefWidth(320);
        endTurn.getStyleClass().add("endT");
        endTurn.setOnMouseEntered(event -> endTurn.setStyle("-fx-background-color:Red"));
        endTurn.setOnMouseExited(event -> endTurn.setStyle("-fx-background-color:Brown"));
        actions.getChildren().add(endTurn);
        endTurn.setOnAction(e -> {
            try {
                endTurn.setStyle("-fx-background-color:Red");
                Game.endTurn();
                createMap(board);
                revalidateHerosPanel(herosLeftPanel, root);
                isFinished(stage);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.500));
                pause.setOnFinished(j -> endTurn.setStyle("-fx-background-color:Brown"));
                pause.play();
            } catch (Exception err) {
                new AppPopup(err.getMessage(), stage).open();
            }
        });

        createMap(board);
        screen.setOnMouseClicked(e ->revalidateHerosPanel(herosLeftPanel, root));
        screen.setOnKeyPressed(e ->moveRoutine(stage, herosLeftPanel, root, board, e.getCode()));

        root.getChildren().add(board);

        stage.setWidth(1600);
        stage.setHeight(896);
        stage.setResizable(false);
        stage.setTitle("Game Map");
        stage.setScene(screen);
        stage.show();
        if (useCPU) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> cpu.play());
            pause.play();
        }



    }

    private void moveRoutine(Stage stage, VBox herosLeftPanel, Pane root, GridPane board, KeyCode e) {
        try {

            currentHero.move(keyMappings.get(e));
            checkSteppedOnTrap(stage);
            this.updateCellsVisibility(board);
            revalidateHerosPanel(herosLeftPanel, root);
            isFinished(stage);

        } catch (Exception err) {
            new AppPopup(err.getMessage(), stage).open();
        }
    }

    private void checkSteppedOnTrap(Stage stage) {
        int currentHp = currentHero.getCurrentHp();
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(e -> {
            if (currentHero.getCurrentHp() < currentHp)
                    new AppPopup("you stepped on a trap!", stage).open();
        });
        pause.play();
    }

    private void isFinished(Stage stage) {
        if (Game.checkGameOver()) {
            StackPane endScreen = new StackPane();
            Scene endScene = new Scene(endScreen);
            endScene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            Image endImg = new Image(getClass().getResourceAsStream(Game.checkWin()?"../assets/finalScreen/victory.png":"../assets/finalScreen/defeat.png"));
            BackgroundImage endS = new BackgroundImage(endImg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, true));
            endScreen.setBackground(new Background(endS));
            stage.setScene(endScene);
        }
    }



    private void revalidateHerosPanel(VBox Panel,Pane parent) {
        parent.getChildren().remove(Panel);
        Panel.getChildren().clear();
        for (Hero hero : Game.heroes)
            Panel.getChildren().add(new HeroPanelDetails(hero,this.currentHero));
        parent.getChildren().add(Panel);
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

            img.setId(cell.toString());
            img.setStyle("-fx-cursor:hand");
            img.setEffect(new DropShadow(2, Color.BLACK));
            img.setOnMouseEntered(e -> ((ImageView) e.getSource()).setOpacity(0.75));
            img.setOnMouseExited(e -> ((ImageView) e.getSource()).setOpacity(1));
            img.setOnMouseClicked(e ->{
                var source = ((ImageView) e.getSource()).getId();
                ((ImageView) e.getSource()).setOpacity(0.15);
                Cell target=null;
                for (Cell[] cells : Game.map)
                    for (Cell mapCell : cells)
                        if (mapCell.toString().equals(source))
                            target = mapCell;
                if (target instanceof CharacterCell && (((CharacterCell) target).getCharacter() instanceof Hero
                        || ((CharacterCell) cell).getCharacter() instanceof Zombie)) {
                    if (currentHero instanceof Medic) {
                        if (e.getClickCount() >= 2)
                            if (((CharacterCell) target).getCharacter() instanceof Hero)
                                currentHero = ((Hero) ((CharacterCell) target).getCharacter());
                            else
                                currentHero.setTarget(((CharacterCell) target).getCharacter());
                    } else if (((CharacterCell) cell).getCharacter() instanceof Zombie)
                        currentHero.setTarget(((CharacterCell) target).getCharacter());
                    else if ((((CharacterCell) target).getCharacter() instanceof Hero))
                        currentHero = ((Hero) ((CharacterCell) target).getCharacter());
                }
            });
            return img;
    }

    private String validateNaming(String text) {
        if (text.contains(" "))
            return text.split(" ")[0] + ".png";
        return text+".png";
    }

    private ImageView getScaledImage(String path, int width, int height) {
        InputStream input = getClass().getResourceAsStream(path);
        Image image = new Image(input, width-4, height, true, true);
        ImageView imageView = new ImageView(image);
        imageView.setCache(true);
        return imageView;
    }


    public class HeroPanelDetails extends HBox {
        public HeroPanelDetails(Hero her,Hero currentHero) {
            super(3);
            this.setStyle("-fx-border-color:BLACK;" + "-fx-border-width: 3 0 3 3;");
            this.getChildren().add(getScaledImage("../assets/heroFaces/" + validateNaming(her.getName()), 135, 135));
            VBox info = new VBox();
            info.setAlignment(Pos.BASELINE_LEFT);
            this.getChildren().add(info);
            Text nameL = new Text("Name: " + her.getName());
            nameL.setStyle("-fx-fill: white;");
            info.getChildren().add(nameL);
            String ty = "";
            if (her instanceof Fighter)
                ty = "Fighter";
            else if (her instanceof Explorer)
                ty = "Explorer";
            else
                ty = "Medic";

            Text isActive = new Text("Current Hero: " + currentHero.getName().equals(her.getName()));
            isActive.setStyle("-fx-fill: white;");
            info.getChildren().add(isActive);
            Text typeL = new Text("Type: " + ty);
            typeL.setStyle("-fx-fill: white;");
            info.getChildren().add(typeL);
            Text targetL = new Text("Current Target: ");
            targetL.setStyle("-fx-fill: white;");
            if (her.getTarget() == null)
                targetL.setText("Current Target: Nobody");
            else
                targetL.setText("Current Target: "+her.getTarget().getName());
            info.getChildren().add(targetL);
            Text actionsL = new Text("Actions: " + her.getActionsAvailable());
            actionsL.setStyle("-fx-fill: white;");
            info.getChildren().add(actionsL);
            Text vaccineL = new Text("Vaccines: " + her.getVaccineInventory().size());
            vaccineL.setStyle("-fx-fill: white;");
            info.getChildren().add(vaccineL);
            Text supplyL = new Text("Supplies: " + her.getSupplyInventory().size());
            supplyL.setStyle("-fx-fill: white;");
            info.getChildren().add(supplyL);
            ProgressBar hp = new ProgressBar(her.getCurrentHp() / (her.getMaxHp()*1.0));
            hp.setTranslateY(5);
            hp.setStyle(
                    "-fx-accent: transparent; -fx-control-inner-background: transparent; -fx-background-color: transparent; -fx-accent: brown ");
            hp.setPrefSize(200, 20);
            info.getChildren().add(hp);
        }
    }
}
