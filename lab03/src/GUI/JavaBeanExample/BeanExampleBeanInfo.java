package GUI.JavaBeanExample;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class BeanExampleBeanInfo extends SimpleBeanInfo {
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
}
