package GUI;

import GUI.JavaBeanExample.BeanExample;

import com.sun.javaws.util.JfxHelper;
import ziarenka.DoubleArrayEditorPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class GUI {

    private JPanel MainPanel;
    public BeanExample beanExmpl;
    private JButton ChangeTitleBtn;
    private JTextField textField1;

    GUI()
    {
       ChangeTitleBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               beanExmpl.setTitle(textField1.getText());
           }
       });

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("APP");
        GUI gui=new GUI();
        frame.setContentPane(gui.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
