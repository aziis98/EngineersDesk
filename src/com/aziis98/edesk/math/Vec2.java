package com.aziis98.edesk.math;

public class Vec2 {

    private float x, y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(float x, float y) {
        return new Vec2( this.x + x, this.y + y );
    }

    public Vec2 add(Vec2 other) {
        return add( other.x, other.y );
    }

    public Vec2 sub(float x, float y) {
        return new Vec2( this.x - x, this.y - y );
    }

    public Vec2 sub(Vec2 other) {
        return sub( other.x, other.y );
    }

    public Vec2 scale(float x, float y) {
        return new Vec2( this.x * x, this.y * y );
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Vec2 vec2 = (Vec2) o;

        return Float.compare( vec2.x, x ) == 0 && Float.compare( vec2.y, y ) == 0;

    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits( x ) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits( y ) : 0);
        return result;
    }
}
