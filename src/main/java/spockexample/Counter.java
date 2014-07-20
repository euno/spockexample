package spockexample;

/**
 * Created by yusuke on 2014/07/20.
 */
public class Counter {
    private int count = 0;

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
