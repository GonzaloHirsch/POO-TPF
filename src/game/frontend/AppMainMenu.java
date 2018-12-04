package game.frontend;

import game.backend.CandyGame;
import game.backend.FruitCandyGame;
import game.backend.GameListener;
import game.backend.cell.Cell;
import game.backend.element.Element;
import game.backend.level.Level1;
import game.backend.level.Level2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AppMainMenu extends VBox {

    private Stage primaryStage;

    public AppMainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("Candy Game");

        StackPane bg = new StackPane();
        //bg.setPrefSize(500,500);

        //  Add the menu bar on top of the screen
        getChildren().add(new AppMenu());

        Label menuTitle = new Label("Candy Game");
        menuTitle.setAlignment(Pos.TOP_CENTER);
        menuTitle.setFont(new Font(50));
        menuTitle.setPrefSize(500,300);
        bg.getChildren().add(menuTitle);

        //  A button to go to each level
        Button level1Button = new Button("Level 1 - Normal");
        level1Button.setTextAlignment(TextAlignment.CENTER);

        level1Button.setOnAction(event -> {
            CandyGame game = new CandyGame(Level1.class);
            CandyFrame frame = new CandyFrame(game);
            Scene scene = new Scene(frame);
            AppMainMenu.this.primaryStage.setResizable(false);
            AppMainMenu.this.primaryStage.setScene(scene);
            AppMainMenu.this.primaryStage.show();
        });

        Button level2Button = new Button("Level 2 - Fruits");
        level2Button.setTextAlignment(TextAlignment.CENTER);
        level1Button.setOnAction(event -> {
            CandyGame game = new FruitCandyGame(Level2.class);
            CandyFrame frame = new CandyFrame(game);
            Scene scene = new Scene(frame);
            AppMainMenu.this.primaryStage.setResizable(false);
            AppMainMenu.this.primaryStage.setScene(scene);
            AppMainMenu.this.primaryStage.show();
        });

        Button level3Button = new Button("Level 3 - Jelly");
        level3Button.setTextAlignment(TextAlignment.CENTER);
        level1Button.setOnAction(event -> {
            /*
            CandyGame game = new CandyGame(Level1.class);
            CandyFrame frame = new CandyFrame(game);
            Scene scene = new Scene(frame);
            AppMainMenu.this.primaryStage.setResizable(false);
            AppMainMenu.this.primaryStage.setScene(scene);
            AppMainMenu.this.primaryStage.show();
            */
        });

        this.getChildren().addAll(level1Button, level2Button, level3Button);
        bg.getChildren().add(this);
        /*
        bg.getChildren().add(new Group(level1Button));
        bg.getChildren().add(new Group(level2Button));
        bg.getChildren().add(new Group(level3Button));

        this.getChildren().add(bg);
        */
    }
}
