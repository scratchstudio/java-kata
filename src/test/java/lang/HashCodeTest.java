package lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kj Nam
 */
public class HashCodeTest {

    @Test
    public void equals_와_hashcode_를_오버라이딩_하지_않으면_각_객체는_다른_해시코드를_가진다() {
        PersonA personA1 = new PersonA("Kyu");
        PersonA personA2 = new PersonA("Kyu");
        Assert.assertNotEquals(personA1.hashCode(), personA2.hashCode());

        PersonB personB1 = new PersonB("Kyu");
        PersonB personB2 = new PersonB("Kyu");
        Assert.assertEquals(personB1.hashCode(), personB2.hashCode());
    }

    @Test
    public void hashMapTest() {
        Map<Person, String> map = new HashMap<>();

        PersonA personA = new PersonA("Kyu");
        map.put(personA, "I'm Kyu");

        Assert.assertNull(map.get((new PersonA("Kyu"))));


        PersonB personB = new PersonB("Kyu");
        map.put(personB, "I'm Kyu");

        Assert.assertNotNull(map.get(new PersonB("Kyu")));
    }


    abstract class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }
    }

    class PersonA extends Person {
        public PersonA(String name) {
            super(name);
        }
    }

    class PersonB extends Person {
        public PersonB(String name) {
            super(name);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this)
                return true;

            if (!(obj instanceof PersonB))
                return false;

            PersonB personB = (PersonB) obj;
            return personB.name == name;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + name.hashCode();
            return result;
        }
    }
}
