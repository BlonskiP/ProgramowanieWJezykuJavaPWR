import java.lang.reflect.InvocationTargetException;

public class ProblemSolverThread extends Thread {
    InstanceProblemGenerator generator;
    AlgorithClassLoader loader;
    // algorith name= random or brute
    public ProblemSolverThread(long seed, String algorithName)
    {
        generator=new InstanceProblemGenerator(seed);
        switch (algorithName){
            case "random":{break;}
            case "brute":{
                loader=new AlgorithClassLoader("KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackBForceAlgorithm",generator.generateProblem());
                break;}
            default: System.out.println("Wrong algorithName!!");
        }
    }
    public void run()
    {

        try {
            loader.Solve().PrintResult();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
