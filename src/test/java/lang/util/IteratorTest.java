package lang.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Kj Nam
 */
public class IteratorTest {

    private int[] ints;

    @Before
    public void setUp() {
        ints = new int[] {1, 2, 3, 4};
    }

    @Test
    public void arrayIterator() {
        Iterator<Integer> iterator = Arrays.stream(ints).iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();
    }

    @Test
    public void listIterator() {
        // List<Integer> intList = Arrays.asList(ints);
        // 위와 같이 변환할 수는 없음

        List<Integer> intList = new ArrayList<>(ints.length);
        for (int each : ints) {
            intList.add(Integer.valueOf(each));
        }

        Iterator<Integer> iterator = intList.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();
    }
}
