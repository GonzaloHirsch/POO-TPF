package game.frontend;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animations {

    private static TranslateTransition swapTransition1;
    private static TranslateTransition swapTransition2;


    public static void SwapElements(Node node1, Node node2, float duration){
        if(swapTransition1 == null || swapTransition1.getStatus() != Animation.Status.RUNNING) {
            swapAnimation(node1,node2,duration);
        } else {
            swapTransition1.setOnFinished(event -> swapAnimation(node1, node2, duration));
        }
    }

    private static void swapAnimation(Node node1, Node node2, float duration){
        swapTransition1 = new TranslateTransition(Duration.millis(duration), node1);
        swapTransition1.setToX(node1.sceneToLocal(node2.localToScene(node1.getTranslateX(), node1.getTranslateY())).getX());
        swapTransition1.setToY(node1.sceneToLocal(node2.localToScene(node1.getTranslateX(), node1.getTranslateY())).getY());
        swapTransition1.play();

        swapTransition2 = new TranslateTransition(Duration.millis(duration), node2);
        swapTransition2.setToX(node2.sceneToLocal(node1.localToScene(node2.getTranslateX(), node2.getTranslateY())).getX());
        swapTransition2.setToY(node2.sceneToLocal(node1.localToScene(node2.getTranslateX(), node2.getTranslateY())).getY());
        swapTransition2.play();
    }

    public static void DropElement(Node node, int cellSize, float duration){
        TranslateTransition translateImage1 = new TranslateTransition(Duration.millis(duration), node);
        translateImage1.setToY(cellSize);
        translateImage1.play();
    }


}
