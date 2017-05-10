package com.mykolabs.apple.util;

/**
 * Provides useful methods for managing time values.
 *
 * @author nikprix
 */
public class TimeManager {

    /**
     * Pauses entire execution Thread for provided amount of seconds.
     *
     * @param seconds
     */
    public static void waitInSeconds(int seconds) {
        int millisec = seconds * 1000;
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Returns current timestamp.
     *
     * @return String
     */
    public static String timestampNow() {
        return String.valueOf(System.currentTimeMillis() / 1000);

    }

}
