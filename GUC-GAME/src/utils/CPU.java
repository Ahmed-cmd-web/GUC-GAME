package utils;

import java.awt.Point;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import engine.Game;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import views.el3ab;

public class CPU {
    private ArrayList<Hero> heros;
    private el3ab GameInstance;
    private int currentHero=0;


    public CPU(el3ab GameInstance) {
        this.GameInstance = GameInstance;
        this.heros = Game.heroes;
    }



    private boolean isAbleToDoMovement(int x, int y) {
        var hero = heros.get(currentHero);
        var p = hero.getLocation();
        if (hero.getActionsAvailable() < 1)
            return false;
        if (!(x <= 14 && y <= 14 && x >= 0 && y >= 0))
            return false;
        if (Game.map[x][p.y] instanceof CharacterCell && ((CharacterCell) Game.map[x][p.y]).getCharacter() != null && x>0)
            return false;
        if (Game.map[p.x][y] instanceof CharacterCell && ((CharacterCell) Game.map[p.x][y]).getCharacter() != null && y>0)
            return false;
        if (Math.abs(x - p.x) >= 1 && Math.abs(y - p.y) >= 1 && hero.getActionsAvailable() <= 1)
            return false;
        // This condition is useless
        if ((Math.abs(x - p.x) >= 2 || Math.abs(y - p.y) >= 2) && hero.getActionsAvailable() <= 1)
            return false;
        return true;
    }


    private void wait(EventHandler<ActionEvent> event,int duration) {
        PauseTransition pause = new PauseTransition(Duration.seconds(duration));
        pause.setOnFinished(event);
        pause.play();
    }


    private void moveToPoint(int x, int y) {
        var hero = heros.get(currentHero);
        var p = hero.getLocation();
        var dx = x - p.x;
        var dy = y - p.y;
        switch (dx) {
            case 1:
                GameInstance.right.fire();
                break;
            case 2:
                GameInstance.right.fire();
                GameInstance.right.fire();
                break;
            case -1:
                GameInstance.left.fire();
                break;
            case -2:
                GameInstance.left.fire();
                GameInstance.left.fire();
                break;
            default:
                break;
        }
            switch (dy) {
                case 1:
                    GameInstance.up.fire();
                    break;
                case 2:
                    GameInstance.up.fire();
                    GameInstance.up.fire();
                    break;
                case -1:
                    GameInstance.down.fire();
                    break;
                case -2:
                    GameInstance.down.fire();
                    GameInstance.down.fire();
                    break;
                default:
                    break;
            }
    }


    private void useAdjacentCells(Method e) {
        var hero = heros.get(currentHero);
        var p = hero.getLocation();
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if ((i + p.x) <= 14 && (j + p.y) <= 14 && (i + p.x) >= 0 && (j + p.y) >= 0) {
                    int cx = p.x + i;
                    int cy = p.y + j;
                    try {
                        e.invoke(this, cx, cy);
                    } catch (Exception ex) {
                        // TODO: handle exception
                        System.out.println(ex.getMessage());
                    }
                }
    }


    private void pickVaccineIfAvailable(int x,int y) {
            var cell = Game.map[x][y];
                if (cell instanceof CollectibleCell
                        && ((CollectibleCell) cell).getCollectible() instanceof Vaccine) {
                    if (isAbleToDoMovement(x, y)) {
                        moveToPoint(x, y);
                        return;
                    }
                }
    }


    private void cureZombieIfPossible(int x, int y) {
        var hero = heros.get(currentHero);
        if (hero.getVaccineInventory().size() <= 0)
            return;
        var cell = Game.map[x][y];
        if (cell instanceof CharacterCell
                && ((CharacterCell) cell).getCharacter() instanceof Zombie) {
            hero.setTarget(((CharacterCell) cell).getCharacter());
            // wait(e -> GameInstance.cure.fire(), 2);
            GameInstance.cure.fire();
        }
    }



    private void moveRandomly() {
        try {
            var possibleMoves = new ArrayList<Point>();
            var hero = heros.get(currentHero);
            if (hero.getActionsAvailable() < 1)
                return ;
            var p = hero.getLocation();
            for (int i = -1; i < 2; i++){
                    possibleMoves.add(new Point(p.x + i, 0));
                    possibleMoves.add(new Point(0, p.y + i));
                }

            Random random = new Random();
            int randInt=random.nextInt(possibleMoves.size());
            while (!isAbleToDoMovement(possibleMoves.get(randInt).x, possibleMoves.get(randInt).y) && possibleMoves.size()>0) {
                possibleMoves.remove(randInt);
                randInt = random.nextInt(possibleMoves.size());
            }
            moveToPoint(possibleMoves.get(randInt).x, possibleMoves.get(randInt).y);
            System.out.println("moved");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

    public void algo() {

                    try {
                this.useAdjacentCells(
                        getClass().getDeclaredMethod("pickVaccineIfAvailable",
                                new Class[] { int.class, int.class }));
                this.useAdjacentCells(
                        getClass().getDeclaredMethod("cureZombieIfPossible",
                                new Class[] { int.class, int.class }));
                moveRandomly();
                System.out.println(GameInstance.currentHero.getActionsAvailable());
                System.out.println(GameInstance.currentHero.getLocation());
            } catch (Exception err) {
                // TODO: handle exception
                System.out.println(err);
            }


    }

    public void play() {
        var hero = heros.get(currentHero);

        var forThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            algo();
                        }
                    });
                }
                try {
                    Thread.sleep(2000);

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }

        });
        forThread.start();

        // for (int i = 0; i < 5; i++) {

        //         Platform.runLater(new Runnable() {
        //             @Override
        //             public void run() {
        //                 algo();
        //             }
        //         });

        //     try {
        //     Thread.sleep(2000);
        //     } catch (Exception e) {
        //         // TODO: handle exception
        //     }
        // }













        // while (GameInstance.currentHero.getActionsAvailable() > 0) {
        //     try {
        //         this.useAdjacentCells(
        //                 this.getClass().getDeclaredMethod("pickVaccineIfAvailable",
        //                         new Class[] { int.class, int.class }));
        //         this.useAdjacentCells(
        //                 this.getClass().getDeclaredMethod("cureZombieIfPossible",
        //                         new Class[] { int.class, int.class }));
        //         moveRandomly();
        //         System.out.println(GameInstance.currentHero.getActionsAvailable());
        //         System.out.println(GameInstance.currentHero.getLocation());
        //     } catch (Exception err) {
        //         // TODO: handle exception
        //         System.out.println(err);
        //     }
        //     try {
        //         Thread.sleep(1000);
        //     } catch (Exception e) {
        //         // TODO: handle exception
        //     }
        // }
    }




}
