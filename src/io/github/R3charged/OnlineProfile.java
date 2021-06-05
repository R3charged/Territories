package io.github.R3charged;

public class OnlineProfile {

    private long startTime; //start of time in a chunk

    private boolean isAfk;

    public OnlineProfile() {
        startTime = System.currentTimeMillis();
        isAfk = false;
    }

    public long updateTimer() {
        long prevStart = startTime;
        startTime = System.currentTimeMillis();
        return startTime - prevStart;
    }

}
