package TicTacToe;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import TicTacToe.JsFileFilter;
public class JavaScriptManager {

    static ArrayList<Script> scripts=new ArrayList<>();
    static ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    static File scriptsFolder;
    public static void updateScripts() throws FileNotFoundException, ScriptException {

        if(scriptsFolder==null)
        {
            scriptsFolder=new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()+"/scripts");
            if(!createScriptFolder())
            {
                System.out.println("There is 'scripts' file and it is not a directory");
                while(!createScriptFolder());
            }
        }
            scripts.clear();
            getAllJavaScript();
            System.out.println(scripts.size());

        }

    private static boolean createScriptFolder()
    {
        if(!scriptsFolder.exists()) {

            boolean succes = scriptsFolder.mkdir();
            if(scriptsFolder.exists())
            {
                System.out.println("Scripts folder created. Give me new AI scripts!!");
                return true;
            }
            System.out.println(succes);
        }
        else if(scriptsFolder.isDirectory()){
            return true;
        }


        return false;
    }
    private static void getAllJavaScript() throws FileNotFoundException, ScriptException {
        FilenameFilter js = new JsFileFilter();
        ArrayList<File> jsFiles=new ArrayList<>();
        for (File script:scriptsFolder.listFiles(js)
             ) {
            scripts.add(new Script(script,"js"));
        }
    }
    public static void printAllScripts()
    {
        for (Script fileName:scripts
             ) {
            System.out.println(fileName.name);
        }
    }
    public static void invokeScript(Script script) throws FileNotFoundException, ScriptException {
        engine.eval(new FileReader(script.path.toString()));
        Invocable invocable = (Invocable) engine;
        try {
            char[][] testArray = new char[4][4];
            testArray[0][0]='a';
            invocable.invokeFunction("moveTick",testArray);
        } catch (NoSuchMethodException e) {
            System.out.println("This script doesn't have moveTick function!");
        }
    }
}
