import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AlgorithClassLoader extends ClassLoader{
    public Method[] methods;
    public Constructor constructor;
    public Class cls;

    public AlgorithClassLoader(String classBinName)
    {
        try{
            ClassLoader classLoader=this.getClass().getClassLoader();
            Class<?> loadedMyClass = classLoader.loadClass(classBinName);
            Constructor<?> constructor= loadedMyClass.getConstructor();
            Object myClassObject = constructor.newInstance();
            Method[] methods=loadedMyClass.getMethods();

        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
