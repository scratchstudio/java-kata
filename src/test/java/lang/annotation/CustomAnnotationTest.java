package lang.annotation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kj Nam
 * @since 2017-03-11
 */
public class CustomAnnotationTest {

    /*
        자바의 리플렉션 기술을 통해 특정 어노테이션이 포함 된 클래스의 메타데이터를 읽어올 수 있다.
     */
    @Test
    public void getMetadataTest() {
        //given
        Class clazz = new CustomAnnotation().getClass();
        Annotation annotation = clazz.getAnnotation(ClassPreamble.class);

        //when
        String result = annotation.toString();
        // @lang.annotation.ClassPreamble(currentRevision=1, lastModified=N/A, lastModifiedBy=N/A, author=Tester, date=2017/03/11, reviewers=[John, Allen])

        //then
        assertTrue(result.contains("currentRevision=1"));
        assertTrue(result.contains("lastModified=N/A"));
        assertTrue(result.contains("lastModifiedBy=N/A"));
        assertTrue(result.contains("author=Tester"));
        assertTrue(result.contains("date=2017/03/11"));
        assertTrue(result.contains("reviewers=[John, Allen]"));
    }
}

@ClassPreamble(
        author = "Tester",
        date = "2017/03/11",
        reviewers = {"John", "Allen"}
)
class CustomAnnotation {
    @Override
    public String toString() {
        return "Hello, World!";
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ClassPreamble {
    String author();

    String date();

    int currentRevision() default 1;

    String lastModified() default "N/A";

    String lastModifiedBy() default "N/A";

    String[] reviewers();
}
