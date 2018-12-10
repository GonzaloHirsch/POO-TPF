package game.frontend.animations;

import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;


public class ShakeTransition {
    private static final int SHAKE_COUNT = 6;
    SequentialTransition sequentialTransition;

    public ShakeTransition(Node node, int shakeAmount ,Duration duration){
        sequentialTransition = new SequentialTransition();
        for(int i = 0; i < SHAKE_COUNT; i++)
            sequentialTransition.getChildren().add(newTransition(node, (int)(Math.pow(-1,i) * shakeAmount), duration.divide(SHAKE_COUNT)));
    }

    private TranslateTransition newTransition(Node node, int to, Duration duration){
        TranslateTransition transition = new TranslateTransition(duration, node);
        transition.setToX(to);
        return transition;
    }

    public void setOnFinished(EventHandler<ActionEvent> value){
        sequentialTransition.setOnFinished(value);
    }

    public void play(){
        sequentialTransition.play();
    }

    public Status getStatus(){
        return sequentialTransition.getStatus();
    }
}
