package game.backend;

import game.backend.element.Element;

public interface FrontEndListener {

    void gridUpdated();

    void swapElements(int i1, int j1, int i2,int j2);

    void fallElements();
}
