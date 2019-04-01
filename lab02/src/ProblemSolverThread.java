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
                loader=new AlgorithClassLoader("classes.KnapsackBForceAlgorithm");
                break;}
            default: System.out.println("Wrong algorithName!!");
        }
    }
    public void run()
    {
        System.out.println("test complete");
    }
}
