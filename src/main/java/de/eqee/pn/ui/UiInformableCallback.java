package de.eqee.pn.ui;

public interface UiInformableCallback<T> extends UiCallback<T> {
    void inform(String text);
}
