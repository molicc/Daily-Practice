import java.lang.reflect.Field;

/**
 * Created by dx on 2018/8/9.
 *
 * @author dx
 */
public class CheckProperty {
    public static void tovalue(Class currentClass, String property) {
        try {

            Field f = currentClass.getDeclaredField(property);
            f.setAccessible(true);
            Object o = f.get(currentClass.newInstance());
            System.out.println(currentClass.toString()+"'s "+property +" is "+o.toString());
            if (currentClass.getSuperclass() != null) {
                tovalue(currentClass.getSuperclass(), property);
            }
        } catch (NoSuchFieldException e) {
            if (currentClass.getSuperclass() != null) {
                tovalue(currentClass.getSuperclass(), property);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
