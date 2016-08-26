package lang.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by kjnam on 2016. 6. 26..
 */
public class CollectionsTest {

    HashMap hashMap;

    @Before
    public void setUp() {
        hashMap = new HashMap();
    }

    @Test
    public void testHashMap() {
        hashMap.put("A", "a");
        hashMap.put("B", "b");
        hashMap.put("C", "c");
        hashMap.put("D", "d");

        Collection<String> values = hashMap.values();

        for (String val : values) {
            System.out.println(val);
        }

    }

}
