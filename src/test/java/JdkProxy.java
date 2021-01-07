import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static org.junit.Assert.assertEquals;

public class JdkProxy {

    @Test
    public void jdkProxy() {
        User user = new User("Вася");

        InvocationHandler handler = (proxy, method, args) -> {
            if (method.getName().equals("getName")) {
                return ((String) method.invoke(user, args)).toUpperCase();
            }
            return method.invoke(user, args);
        };

        IUser userProxy = (IUser) Proxy.newProxyInstance(user.getClass().getClassLoader(), User.class.getInterfaces(), handler);
        assertEquals("ВАСЯ", userProxy.getName());
    }

}
