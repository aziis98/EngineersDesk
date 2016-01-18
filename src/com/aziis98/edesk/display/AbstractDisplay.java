package com.aziis98.edesk.display;

public abstract class AbstractDisplay {

    protected long frameTime = 1000 / 120;

    public abstract void setTitle(String title);

    public abstract int getWidth();

    public abstract void setWidth(int width);

    public abstract int getHeight();

    public abstract void setHeight(int height);

    public void setSize(int width, int height) {
        setWidth( width );
        setHeight( height );
    }

    public abstract Drawer getDrawer();


    // ----

    public void setFPS(int fps) {
        this.frameTime = 1000 / fps;
    }

}
