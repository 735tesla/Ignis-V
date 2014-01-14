/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ignis.v;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author henrypitcairn
 */
public class StatsTracker {
    private int packetsSent;
    private int bytesSent;
    private Timer timer;
    private int secondsRunning;
    private String target;
    public StatsTracker(String target) {
        packetsSent = 0;
        bytesSent = 0;
        timer = new Timer();
        secondsRunning = 0;
        this.target = target;
    }
}
