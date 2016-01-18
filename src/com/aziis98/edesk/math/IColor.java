package com.aziis98.edesk.math;

public interface IColor<T extends IColor> {

    int toRGB();

    // ----

    T add(int amount);

    T sub(int amount);

    T scale(float scalar);

    T lerp(T other, float t);

    default T mix(T other) {
        return lerp( other, 0.5F );
    }

    // ----

    class ColorRGB implements IColor<ColorRGB> {

        private int value;

        public ColorRGB() {
            this( 0, 0, 0, 0 );
        }

        public ColorRGB(int argb) {
            this.value = argb;
        }

        public ColorRGB(int r, int g, int b) {
            this( 255, r, g, b );
        }

        public ColorRGB(int a, int r, int g, int b) {
            a = clamp( a );
            r = clamp( r );
            g = clamp( g );
            b = clamp( b );

            this.value = (a << 24) | (r << 16) | (g << 8) | b;
        }

        @Override
        public int toRGB() {
            return value;
        }

        public int getAlpha() {
            return (value >> 24) & 0xFF;
        }

        public int getRed() {
            return (value >> 16) & 0xFF;
        }

        public int getGreen() {
            return (value >> 8) & 0xFF;
        }

        public int getBlue() {
            return (value) & 0xFF;
        }

        public void setAlpha(int value) {
            this.value = (clamp( value ) << 24) | (this.value & 0x00FFFFFF);
        }

        public void setRed(int value) {
            this.value = (clamp( value ) << 16) | (this.value & 0xFF00FFFF);
        }

        public void setGreen(int value) {
            this.value = (clamp( value ) << 8) | (this.value & 0xFFFF00FF);
        }

        public void setBlue(int value) {
            this.value = clamp( value ) | (this.value & 0xFFFFFF00);
        }

        // ----


        @Override
        public ColorRGB add(int amount) {
            return new ColorRGB( getAlpha() + amount, getRed() + amount, getGreen() + amount, getBlue() + amount );
        }

        @Override
        public ColorRGB sub(int amount) {
            return new ColorRGB( getAlpha() - amount, getRed() - amount, getGreen() - amount, getBlue() - amount );
        }

        @Override
        public ColorRGB scale(float scalar) {
            return new ColorRGB( (int) (getAlpha() * scalar), (int) (getRed() * scalar), (int) (getGreen() * scalar), (int) (getBlue() * scalar) );
        }

        @Override
        public ColorRGB lerp(ColorRGB other, float t) {
            int da = other.getAlpha() - getAlpha();
            int dr = other.getRed() - getRed();
            int dg = other.getGreen() - getGreen();
            int db = other.getBlue() - getBlue();

            return new ColorRGB(
                    getAlpha() + (int) (da * t),
                    getRed() + (int) (dr * t),
                    getGreen() + (int) (dg * t),
                    getBlue() + (int) (db * t)
            );
        }


        // ----

        public static final ColorRGB RED     = new ColorRGB( 255, 0, 0 );
        public static final ColorRGB GREEN   = new ColorRGB( 0, 255, 0 );
        public static final ColorRGB BLUE    = new ColorRGB( 0, 0, 255 );
        public static final ColorRGB WHITE   = new ColorRGB( 255, 255, 255 );
        public static final ColorRGB GRAY    = new ColorRGB( 128, 128, 128 );
        public static final ColorRGB BLACK   = new ColorRGB( 0, 0, 0 );
        public static final ColorRGB YELLOW  = new ColorRGB( 255, 255, 0 );
        public static final ColorRGB MAGENTA = new ColorRGB( 255, 0, 255 );
        public static final ColorRGB ORANGE  = new ColorRGB( 255, 200, 0 );
        public static final ColorRGB CYAN    = new ColorRGB( 0, 255, 255 );
    }

    // ----

    static int clamp(int colorComp) {
        return (colorComp > 255 ? 255 : (colorComp < 0 ? 0 : colorComp));
    }

}
