public class Main {

    public static void main(String[] args) {
        InstanceProblemGenerator gen=new InstanceProblemGenerator((long)313);

        gen.generateProblem().printItems();

    }
}
