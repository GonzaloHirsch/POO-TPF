package game.frontend.animations;

import javafx.animation.Animation.Status;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class SwapTransition{
        private TranslateTransition translateTransition1;
        private TranslateTransition translateTransition2;

        public SwapTransition(Node node1, Node node2, Duration duration){
            translateTransition1 = new TranslateTransition(duration, node1);
            translateTransition2 = new TranslateTransition(duration, node2);

            translateTransition1.setToX(node1.sceneToLocal(node2.localToScene(node1.getTranslateX(), node1.getTranslateY())).getX());
            translateTransition1.setToY(node1.sceneToLocal(node2.localToScene(node1.getTranslateX(), node1.getTranslateY())).getY());

            translateTransition2.setToX(node2.sceneToLocal(node1.localToScene(node2.getTranslateX(), node2.getTranslateY())).getX());
            translateTransition2.setToY(node2.sceneToLocal(node1.localToScene(node2.getTranslateX(), node2.getTranslateY())).getY());
        }

        public void setOnFinished(EventHandler<ActionEvent> value){
            translateTransition1.setOnFinished(value);
        }

        public void play(){
            translateTransition1.play();
            translateTransition2.play();
        }

        public Status getStatus(){
            return translateTransition1.getStatus();
        }
}
