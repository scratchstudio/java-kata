package lang.annotation;

/**
 * @author Kj Nam
 * @since 2017-03-11
 */
@ClassPreamble(
        author = "Tester",
        date = "2017/03/11",
        reviewers = {"John", "Allen"}
)
public class CustomAnnotation {
    @Override
    public String toString() {
        return "Hello, World!";
    }
}
