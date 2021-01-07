import org.junit.Test;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.Assert.assertEquals;

public class ByteBuddyTest {

    @Test
    public void byteBuddy() throws IllegalAccessException, InstantiationException {
        User user = new User("Вася");

        User userProxy = new ByteBuddy()
                .subclass(User.class)
                .method(named("getName"))
                .intercept(MethodDelegation.to(new MyInterceptor(user)))
                .make()
                .load(User.class.getClassLoader())
                .getLoaded()
                .newInstance();

        assertEquals("ВАСЯ", userProxy.getName());

    }
}
