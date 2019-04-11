package GUI.JavaBeanExample;

import java.beans.*;
import java.lang.reflect.Method;

public class BeanExampleBeanInfo extends SimpleBeanInfo {
    public java.awt.Image getIcon(int iconKind) {
        if (iconKind == BeanInfo.ICON_MONO_16x16 || iconKind == BeanInfo.ICON_COLOR_16x16) {
            java.awt.Image img = loadImage("lab0316x16.png");
            return img;
        }
        if (iconKind == BeanInfo.ICON_MONO_32x32 || iconKind == BeanInfo.ICON_COLOR_32x32) {
            java.awt.Image img = loadImage("lab0332x32.png");
            return img;
        }
        return null;
    }
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor value =
                    new PropertyDescriptor("noteListMaxSize", BeanExample.class);
            PropertyDescriptor minimum =
                    new PropertyDescriptor("title", BeanExample.class);

            return new PropertyDescriptor [] { value, minimum };
        } catch (IntrospectionException e) {
            return null;
        }
    }
    public MethodDescriptor[] getMethodDescriptors() {
        // First find the "method" objects.
        Method setTitleMethod, getTitleMethod, changeDirectionMethod;
        Method propertyChangeMethod;
        Class args[] = { };
        Class actionEventArgs[] = { java.awt.event.ActionEvent.class };
        Class propertyChangeEventArgs[] = { PropertyChangeEvent.class };

        try {
            setTitleMethod = BeanExample.class.getMethod("setTitle", args);
            getTitleMethod = BeanExample.class.getMethod("getTitle", args);

        } catch (Exception ex) {
            // "should never happen"
            throw new Error("Missing method: " + ex);
        }

        // Utworzenie tablicy MethodDescriptor z widocznymi metodami obsługi zdarzeń
        MethodDescriptor result[] = {
                new MethodDescriptor(setTitleMethod),
                new MethodDescriptor(getTitleMethod),
        };
        return result;
    }
}
