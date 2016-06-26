package com.api.lang;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by kjnam on 2016. 6. 17..
 */
public class GenericTest {

    @Before
    public void setUp() {

    }

    @Test(expected = ClassCastException.class)
    public void castTest() {
        CastingDTO dto1 = new CastingDTO();
        dto1.setObject(new String());

        CastingDTO dto2 = new CastingDTO();
        dto2.setObject(new StringBuffer());

        CastingDTO dto3 = new CastingDTO();
        dto3.setObject(new StringBuilder());

        String get1 = (String)dto1.getObject();
        String get2 = (String)dto2.getObject();
    }

    @Test
    public void castTestWithGeneric() {
        CastingDTOwithGeneric<String> dto1 = new CastingDTOwithGeneric<>();
        dto1.setObject(new String());

        CastingDTOwithGeneric<StringBuffer> dto2 = new CastingDTOwithGeneric<>();
        dto2.setObject(new StringBuffer());

        CastingDTOwithGeneric<StringBuilder> dto3 = new CastingDTOwithGeneric<>();
        dto3.setObject(new StringBuilder());

        // 형 변환 명시 생략
        String get1 = dto1.getObject();
        StringBuffer get2 = dto2.getObject();
        StringBuilder get3 = dto3.getObject();
    }



    static class CastingDTO implements Serializable {
        private Object object;

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }

    static class CastingDTOwithGeneric<T> implements Serializable {
        private T object;

        public T getObject() {
            return object;
        }

        public void setObject(T object) {
            this.object = object;
        }
    }


}
