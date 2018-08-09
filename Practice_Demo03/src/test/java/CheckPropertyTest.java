import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by dx on 2018/8/9.
 *
 * @author dx
 */
public class CheckPropertyTest {
    @Test
    public void tovalue() throws Exception {
     CheckProperty.tovalue(Class.forName("SonClass"),"name");
    }

}