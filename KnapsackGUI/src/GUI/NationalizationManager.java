package GUI;

import GUI.Controls.BetterScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class NationalizationManager {

    private static Locale locale=new Locale("en");
    private static ResourceBundle LR=ResourceBundle.getBundle("res/LR",locale);
    public static void setLocal(String localizationCode)
    {
        locale=new Locale(localizationCode);
        LR=ResourceBundle.getBundle("res/LR",locale);
        try {

            Parent newRoot=FXMLLoader.load(App.url,getResourceBundle());
            App.scene.setRoot(newRoot);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Locale getLocale()
    {
        return locale;
    }

    public  static ResourceBundle getResourceBundle()
    {
        return LR;
    }

}
