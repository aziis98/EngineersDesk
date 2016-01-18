package com.aziis98.edesk.display;

import com.aziis98.edesk.math.IColor.*;

public abstract class Drawer {

    protected DrawingMode mode;
    private final AbstractDisplay display;

    public Drawer(AbstractDisplay display) {
        this.display = display;
    }

    public abstract void line(float x1, float y1, float x2, float y2);

    public abstract void rectangle(float x, float y, float width, float height);

    public abstract void ellpise(float x, float y, float width, float height);

    public abstract void arc(float x, float y, float width, float height, float angStart, float angEnd);

    public abstract void clear();

    // ----

    public abstract void translate(float dx, float dy);

    public abstract void rotate(float angle);

    public abstract void scale(float dx, float dy);

    public abstract void pushTransform();

    public abstract void popTransform();

    // TODO : vec2 unprojectPoint()

    // ----

    public abstract void applyFilter(float x, float y, float width, float height, FilterFunction filterFunction);

    // ----

    public abstract void lineWidth(int width);

    public abstract void color(ColorRGB color);

    public void color(int r, int g, int b, int a) {
        color( new ColorRGB( a, r, g, b ) );
    }

    // ----

    public void circle(float x, float y, float radius) {
        this.ellpise( x - radius, y - radius, radius * 2, radius * 2 );
    }

    // ----

    public void mode(DrawingMode mode) {
        this.mode = mode;
    }

    public DrawingMode getMode() {
        return mode;
    }

    public AbstractDisplay getDisplay() {
        return display;
    }

    public enum DrawingMode {Vertices, Line, Filling}

    @FunctionalInterface
    public interface FilterFunction {

        ColorRGB transform(int x, int y, ColorRGB color);

    }


}
