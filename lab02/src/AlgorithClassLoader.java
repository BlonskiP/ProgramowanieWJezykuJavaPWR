import KnapsackProblem.KnapsackSolvingAlgorithms.InstanceProblem;
import KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackSolvingResult;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;


public class AlgorithClassLoader{
    public Method solve;
    private Object solverObject;

    public AlgorithClassLoader(String classBinName, InstanceProblem problem)
    {
        try{
            String path = new File(AlgorithClassLoader.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath();
            path=path+"\\classes";
            File classFile=new File(path);
            URL[] urls = new URL[]{classFile.toURI().toURL()};
            ClassLoader classLoader=new URLClassLoader(urls);
            Class<?> loadedMyClass = classLoader.loadClass(classBinName);
            Constructor[] allConstructors = loadedMyClass.getDeclaredConstructors();
            solverObject = allConstructors[0].newInstance(problem);
            solve=loadedMyClass.getMethod("Solve");
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
    public KnapsackSolvingResult Solve() throws InvocationTargetException, IllegalAccessException {
        return (KnapsackSolvingResult) solve.invoke(solverObject);
    }


}
