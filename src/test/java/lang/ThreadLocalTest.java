package lang;

import org.assertj.core.api.Assertions;
import org.junit.Test;


/**
 * @see <a href = "http://javacan.tistory.com/entry/ThreadLocalUsage">ThreadLocal 사용법과 활용</a>
 */
public class ThreadLocalTest {

    /**
     * 같은 스레드에서 스레드로컬 영역의 값을 참조 가능하다.
     */
    @Test
    public void 같은_스레드에서_스레드로컬_영역의_값을_참조_가능하다() {
        int number = 1;
        Context.threadLocal.set(number);
        Assertions.assertThat(Context.threadLocal.get()).isEqualTo(number);

        Context.threadLocal.remove();
        Assertions.assertThat(Context.threadLocal.get()).isNull();
    }

    @Test
    public void 각_스레드마다_스레드로컬을_가진다() {
        int number = 1;
        Context.threadLocal.set(number);

        new Thread(() -> Context.threadLocal.set(100));

        Assertions.assertThat(Context.threadLocal.get()).isEqualTo(number);
    }
}


/**
 * The type Context.
 */
class Context {
    /**
     * The constant threadLocal.
     */
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal();
}