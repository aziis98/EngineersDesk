package com.aziis98.edesk.math;

public class Vec3 {

    private float x, y , z;

    public Vec3() {
        this( 0, 0, 0 );
    }

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Vec3 vec3 = (Vec3) o;

        return Float.compare( vec3.x, x ) == 0 && Float.compare( vec3.y, y ) == 0 && Float.compare( vec3.z, z ) == 0;

    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits( x ) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits( y ) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits( z ) : 0);
        return result;
    }
}
