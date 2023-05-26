package utils;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;


public class AppPopup extends Popup {
    private Stage stage;
    public AppPopup(String message,Stage stage) {
        super();
        this.stage = stage;
        var label = new Label(message);
        label.setStyle(" -fx-background-color: white;-fx-border-color:BLACK;-fx-border-width: 3 3 3 3;");
        label.setMinWidth(80);
        label.setMinHeight(50);
        label.setPadding(new Insets(20, 20, 20, 20));
        label.setFont(Font.font("Old English Text MT", 40));
        this.setAnchorX(0);
        this.setAnchorY(stage.getHeight());
        this.getContent().add(label);
        this.setAutoHide(true);
    }

    public void open() {
        if (!this.isShowing())
            this.show(this.stage);
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> this.hide());
        pause.play();
    }

}
