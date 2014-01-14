/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ignis.v;

/**
 *
 * @author henrypitcairn
 */
public class GenericFlooder {
    private int numThreads;
    private String target;
    private StatsTracker tracker;
    public GenericFlooder(int numThreads, String target) {
        this.numThreads = numThreads;
        this.target = target;
        tracker = new StatsTracker(target);
    }
}
