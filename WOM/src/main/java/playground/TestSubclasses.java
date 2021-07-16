package playground;

import static net.bytebuddy.matcher.ElementMatchers.*;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class TestSubclasses {

	public static class Delegator {

		public String method(String a, String b, int c) {
			System.out.println("Delegator Method: " + a + ", " + b + ", " + c);
			return "Delegator";
		}
	}

	public void run() throws InstantiationException, IllegalAccessException {

		Implementation implementation = MethodDelegation.to(new Delegator());
		Class<? extends Child> dynamicType = new ByteBuddy().subclass(Child.class)
															.method(ElementMatchers.named("toString"))
															.intercept(FixedValue.value("Hello World!"))
															.method(ElementMatchers	.named("method")
																					.and(takesArguments(String.class,
																							String.class, int.class)))
															.intercept(implementation)
															.make()
															.load(getClass().getClassLoader())
															.getLoaded();
		Child c = dynamicType.newInstance();
		System.out.println(c);
		System.out.println(c.method("foo", "bar", 3));
//				assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
	}

	public static void main(String... args) throws InstantiationException, IllegalAccessException {
		new TestSubclasses().run();

//		Child c = klass.newInstance();
//		if(c instanceof Child) {
//			System.out.println("Is a Child" + c);
//		}
//		c.method();
	}

}
