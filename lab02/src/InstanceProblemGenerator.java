import KnapsackProblem.Item;
import KnapsackProblem.KnapsackSolvingAlgorithms.InstanceProblem;
import KnapsackProblem.KnapsackSolvingAlgorithms.KnapsackSolvingResult;
import java.util.ArrayList;

public class InstanceProblemGenerator {

    // max cap - 128
    // max itemWeight - 16
    // max items - 16
    public static InstanceProblem generateProblem(long seed)
    {
        ArrayList<Item> itemList=new ArrayList<>();
        long state=seed;

        return new InstanceProblem(itemList,capacity);

    }

    private static Result getNextNumber(long oldState, int a, int b, int m)
    {
        long state=(a*oldState + b )%m;
        boolean value=false;
        if(state%2 >0)value=true;
        Result newResult=new Result(state,value);
        return newResult;
    }


}
class Result{
    long state;
    boolean value;
    public Result(long givenState, boolean givevalue){this.state=state; this.value=value;}
}