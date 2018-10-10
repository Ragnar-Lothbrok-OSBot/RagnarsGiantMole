package utils;

public class Timer {

    private long timeout;

    public Timer() {
        timeout = System.currentTimeMillis();
    }

    public boolean isActive() {
        return timeout > System.currentTimeMillis();
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout + System.currentTimeMillis();
    }

    public long getRemainingTimeout() {
        long currentTime = System.currentTimeMillis();
        return timeout > currentTime ? timeout - currentTime : 0;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - timeout;
    }

    public String getElapsedToString() {
        return Format.msToString(getElapsedTime());
    }

    public long getPerHour(long value) {
        return value * 3600000 / getElapsedTime();
    }


}