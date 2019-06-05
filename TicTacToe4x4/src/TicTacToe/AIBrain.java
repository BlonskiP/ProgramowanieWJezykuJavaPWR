package TicTacToe;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AIBrain {
    public static ArrayList<Script> allAIScripts=new ArrayList<>();
    public static void updateScripts() throws FileNotFoundException, ScriptException {
        JavaScriptManager.updateScripts();
        allAIScripts.clear();
        allAIScripts.addAll(JavaScriptManager.scripts);
    }
    public static void invokeScript(int x) throws FileNotFoundException, ScriptException {
        Script scriptToExe = allAIScripts.get(x);
        switch(scriptToExe.type){
            case "js":{
                JavaScriptManager.invokeScript(allAIScripts.get(x));
                break;}
            case "c":{break;}
        }
    }
    public static void getMoveScript() throws FileNotFoundException, ScriptException {
        printAllScripts();
        System.out.println("Enter number of a script to invoke: ");
        Scanner sc = new Scanner(System.in);

        int scriptNumber=-1;
        int allScriptsCount = allAIScripts.size()-1;
        while(scriptNumber>allScriptsCount || scriptNumber<0){
        scriptNumber = sc.nextInt();
            if(scriptNumber>allScriptsCount || scriptNumber<0)
            {
                printAllScripts();
                System.out.println("Give script number from above list!");
            }
        }
        invokeScript(scriptNumber);

    }
    public static void printAllScripts() throws FileNotFoundException, ScriptException {
        updateScripts();
        int index=0;
        for (Script script:allAIScripts
             ) {
            System.out.println(index+". "+script.name);
            index++;
        }
    }
}
