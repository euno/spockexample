package spockexample;

/**
 * Created by euno7 on 2014/07/20.
 */
public class CapacityCounter {
    private int count = 0;

    public CapacityCounter() {
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void reduceCount(int count) {
        this.count -= count;
    }

    public int getCount() {
        return this.count;
    }
}
