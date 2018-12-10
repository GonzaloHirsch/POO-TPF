package game.frontend.animations;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animations {

    private static SwapTransition swapTransition;
    private static ShakeTransition shakeTransition;


    public static void SwapElements(Node node1, Node node2, int duration){
        if(swapTransition == null || swapTransition.getStatus() != Animation.Status.RUNNING) {  // This allows for only one SwapTransition to run at the same time.
            swapTransition = new SwapTransition(node1, node2, new Duration(duration));
            swapTransition.play();
        } else {
            swapTransition.setOnFinished(event -> {
                swapTransition = new SwapTransition(node1, node2, new Duration(duration));
                swapTransition.play();
            });
        }
    }

    public static void ShakeElement(Node node, int duration){
        if(shakeTransition == null || shakeTransition.getStatus() != Animation.Status.RUNNING) {  // This allows for only one SwapTransition to run at the same time.
            shakeTransition = new ShakeTransition(node,5, new Duration(duration));
            shakeTransition.play();
        } else {
            shakeTransition.setOnFinished(event -> {
                shakeTransition = new ShakeTransition(node,5, new Duration(duration));
                shakeTransition.play();
            });
        }
    }

    public static void DropElement(Node node, int cellSize, float duration){
        TranslateTransition translateImage1 = new TranslateTransition(Duration.millis(duration), node);
        translateImage1.setToY(cellSize);
        translateImage1.play();
        translateImage1.setOnFinished(event -> {
            node.setTranslateX(0);
            node.setTranslateY(0);
        });
    }


}
