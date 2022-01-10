package org.example.rbc.dto;

public abstract class UiElementDto {

    private boolean isClicked;
    private boolean isVisible;

    public UiElementDto() {
        this.isClicked = false;
        this.isVisible = true;
    }

    public abstract Object getValue();

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
