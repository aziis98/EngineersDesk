package com.aziis98.edesk.util;

public class Time {

    public static long milliTime() {
        return System.nanoTime() / 1000000;
    }

    public static void sleep(long millis) {
        try
        {
            Thread.sleep( millis );
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void runLater(long millis, Runnable runnable) {
        new Thread( () -> {
            sleep( millis );
            runnable.run();
        }).start();
    }

}
