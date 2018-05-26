package lang.reflect;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by kjnam on 2016. 10. 17..
 */
public class ReflectTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void 접근제어자가_public_인_필드를_가져온다() throws ClassNotFoundException {
		//given
		Class<?> clazz = Class.forName("lang.reflect.Reflect");
		// or
		//Class clazz = lang.reflect.Reflect.class;
		String[] expectedFields = {"field1", "field2", "field3"};

		//when
		Field[] fields = clazz.getFields(); // 접근제어자가 public인 필드만 가져온다

		//then
		int loopIndex = 0;
		for (Field field : fields) {
			assertThat(field.getType().getName(), is("int"));
			assertThat(field.getName(), is(expectedFields[loopIndex]));
			loopIndex++;
		}
	}

	@Test
	public void 접근제어자가_public_인_메소드를_가져온다() throws ClassNotFoundException {
		//given
		Class<?> clazz = Class.forName("lang.reflect.Reflect");
		String[] exptectedMethods = {"method3", "method2", "method1"};

		//when
		Method[] methods = clazz.getMethods();

		//then
		int loopIndex = 0;
		for (Method method: methods) {
			System.out.println(method.getName());
			assertThat(method.getName(), is(exptectedMethods[loopIndex]));
			loopIndex++;

			if (loopIndex == 3) break;
		}

		// Object로부터 상속받은 메소드까지 모두 읽어온다.
		//0 = {Method@818} "public void lang.reflect.Reflect.method1()"
		//1 = {Method@819} "public void lang.reflect.Reflect.method2()"
		//2 = {Method@820} "public void lang.reflect.Reflect.method3()"
		//3 = {Method@821} "public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException"
		//4 = {Method@822} "public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException"
		//5 = {Method@823} "public final void java.lang.Object.wait() throws java.lang.InterruptedException"
		//6 = {Method@824} "public boolean java.lang.Object.equals(java.lang.Object)"
		//7 = {Method@825} "public java.lang.String java.lang.Object.toString()"
		//8 = {Method@826} "public native int java.lang.Object.hashCode()"
		//9 = {Method@827} "public final native java.lang.Class java.lang.Object.getClass()"
		//10 = {Method@828} "public final native void java.lang.Object.notify()"
		//11 = {Method@829} "public final native void java.lang.Object.notifyAll()"
	}

	@Test
	public void 접근제어자가_private_인_메소드를_실행한다() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		//given
		Reflect refelct = new Reflect();
		Method method = refelct.getClass().getDeclaredMethod("privateMethod1", int.class); // 메소드명, 파리미터타입
		method.setAccessible(true);

		//when then
		assertThat(method.invoke(refelct, 1), is(2));
		assertThat(method.invoke(refelct, 3), is(6));
		assertThat(method.invoke(refelct, 10), is(20));
	}
}

class Reflect {
	public int field1;
	public int field2;
	public int field3;

	public void method1() {}
	public void method2() {}
	public void method3() {}

	private int privateMethod1(int number) {
		return number * 2;
	}

}
