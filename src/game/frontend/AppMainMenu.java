package game.frontend;

import game.backend.gametypes.CandyGame;
import game.backend.gametypes.FruitCandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import game.backend.gametypes.CageCandyGame;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Optional;

public class AppMainMenu extends VBox {

    //It shouldnt be public, but it doesnt work the app menu if its private, i have to think a better way
    public static Stage primaryStage;

    public AppMainMenu(Stage primaryStage) {

        //  Setting the background image
        this.setStyle("-fx-background-image: url(images/CGBackground.jpg);" +
                "-fx-background-size: 585px 585px;");

        AppMainMenu.primaryStage = primaryStage;

        //  Hbox to contain the exit and about buttons
        HBox topBox = new HBox();

        //  Spacing between nodes in the Vbox
        this.setSpacing(10);

        //  Vbox size
        this.setPrefSize(65 * 9, 65 * 9);
        this.setPadding(new Insets(5,5,5,5));

        //  So that all the children are center aligned
        this.setAlignment(Pos.CENTER);

        //  MENU LABEL
        Label menuTitle = new Label("Candy Game");
        menuTitle.setId("title-label");
        menuTitle.setAlignment(Pos.TOP_CENTER);
        menuTitle.setFont(new Font(75));
        menuTitle.setPrefSize(500,300);
        menuTitle.setPadding(new Insets(10,10,10,10));

        //  TIP LABEL
        Label tipLabel = new Label("Hover on the level button for more info!");
        tipLabel.setId("info-label");

        //  A button to go to each level
        Button level1Button = new Button("Level 1 - Normal");
        levelButtonFormatting(level1Button);
        level1Button.setOnAction(event -> {
            CandyGame game = new CandyGame(Level1.class);
            LevelLoader(game, AppMainMenu.primaryStage);
        });
        level1Button.setTooltip(new Tooltip(Level1.LevelInfo()));

        Button level2Button = new Button("Level 2 - Fruits");
        levelButtonFormatting(level2Button);
        level2Button.setOnAction(event -> {
            CandyGame game = new FruitCandyGame(Level2.class);
            LevelLoader(game, AppMainMenu.primaryStage);
        });
        level2Button.setTooltip(new Tooltip(Level2.LevelInfo()));

        Button level3Button = new Button("Level 3 - Cage");
        levelButtonFormatting(level3Button);
        level3Button.setOnAction(event -> {
            CandyGame game = new CageCandyGame(Level3.class);
            LevelLoader(game, AppMainMenu.primaryStage);
        });
        level3Button.setTooltip(new Tooltip(Level3.LevelInfo()));

        //  Button for information
        Button infoButton = new Button("About");
        optionButtonFormatting(infoButton);
        infoButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2018.\n" +
                    "Original Implementation: Laura Zabaleta (POO 2013).\n" +
                    "Actual Implementation: \n\tIgnacio Villanueva - 59000\n\tGonzalo Hirsch - 59089");
            alert.showAndWait();
        });

        //  Button to quit application
        Button exitButton = new Button("Exit");
        optionButtonFormatting(exitButton);
        exitButton.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Exit application");
            alert.setContentText("Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });

        //  Inset setting
        setIncets(level1Button);
        setIncets(level2Button);
        setIncets(level3Button);
        setIncets(infoButton);
        setIncets(exitButton);

        //  Space between the 2 buttons
        topBox.setSpacing(400);
        //  Adding option buttons
        topBox.getChildren().addAll(infoButton, exitButton);

        //  Adding all nodes
        this.getChildren().addAll(topBox, menuTitle, level1Button, level2Button, level3Button, tipLabel);
    }

    /**
     * Method to load a new level,
     * @param candyGame     level class to be loaded
     * @param primaryStage  primary stage to be able to load the level
     */
    private void LevelLoader(CandyGame candyGame, Stage primaryStage){
        CandyFrame frame = new CandyFrame(candyGame);
        Scene scene = new Scene(frame);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     *  Method to apply the same formatting to the level buttons
     * @param button    Button to be formatted
     */
    private void levelButtonFormatting(Button button){
        button.setId("level-button");
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefSize(150,50);
        button.setAlignment(Pos.CENTER);
    }

    /**
     *  Method to apply the same formatting to the option buttons
     * @param button    Button to be formatted
     */
    private void optionButtonFormatting(Button button){
        button.setId("option-button");
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefSize(90,25);
        button.setAlignment(Pos.CENTER);
    }

    /**
     *  Method to set the incets to the buttons
     * @param button    Button to be formatted
     */
    private void setIncets(Button button){
        button.setPadding(new Insets(10,10,10,10));
    }
}
