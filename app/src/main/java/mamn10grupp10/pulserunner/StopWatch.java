package mamn10grupp10.pulserunner;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class StopWatch {
    long start, now, diff, paused;
    boolean started;
    String formatted;
    SimpleDateFormat sdf;

    /* Creates a StopWatch, but do not start it
    * setProperties() makes sure the timeFormat is correct */
    public StopWatch(){
        started = false;
        setProperties();
    }

    /* Creates the timeFormat that the user will se one the display
     * and make sure the timeZone is correct */
    public void setProperties(){
        sdf = new SimpleDateFormat("HH:mm:ss.S");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /* Creates the startTime for the Stopwatch and set the flag 'started' to true,
     * which indicates that the StopWatch is ON */
    public void startStopWatch(){
        start = System.currentTimeMillis();
        started = true;
    }

    /* This function will indicate is the StopWatch has started or not
     * @returns true if ON and false if OFF */
    public boolean hasItStarted(){
        return started;
    }

    /* This function will first set a current time and then calculate the
     * elapsed time since the start, by calculation the difference between start and now.
     * Then creates a String is the correct format of the elapsed time
     * @return the elapsed time as a String object */
    public String getTimeElapsedAsString(){
        now = System.currentTimeMillis();
        diff = (now-start);
        formatted = sdf.format(diff);
        return formatted;
    }

    /* @returns the elapsed time in milliseconds */
    public long getTimeElapsedAsLong(){
        return diff;
    }

    /* Pauses the stopwatch */
    public void pause() {
        paused = System.currentTimeMillis();
    }

    /* Resumes the stopwatch*/
    public void resume() {
        start += System.currentTimeMillis() - paused;
    }
}