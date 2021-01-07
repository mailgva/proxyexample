import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Cglib {

    @Test
    public void cglib() {
        User user = new User("Вася");

        MethodInterceptor handler = (obj, method ,  args,  proxy) -> {
            if(method.getName().equals("getName")){
                return ((String)proxy.invoke(user, args)).toUpperCase() ;
            }
            return proxy.invoke(user, args);
        };

        User userProxy = (User) Enhancer.create(User.class, handler);
        assertEquals("ВАСЯ", userProxy.getName());
    }
}
