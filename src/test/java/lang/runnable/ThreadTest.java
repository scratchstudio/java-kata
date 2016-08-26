package lang.runnable;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by kjnam on 2016. 6. 26..
 */
public class ThreadTest {

    @Before
    public void setUp() {

    }

    @Test
    public void threadTest() {
        RunnableSample [] runnable = new RunnableSample[5];
        ThreadSample [] thread = new ThreadSample[5];

        for (int i=0; i<5; i++) {
            runnable[i] = new RunnableSample();
            thread[i] = new ThreadSample();

            new Thread(runnable[i]).start();
            thread[i].start();
        }
    }

    class RunnableSample implements Runnable {
        @Override
        public void run() {
            System.out.println("This is RunnableSample's run() method.");
        }
    }

    class ThreadSample extends Thread {
        @Override
        public void run() {
            System.out.println("This is ThreadSample's run() method.");
        }
    }
}
