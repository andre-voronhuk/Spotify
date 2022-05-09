package others;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ComponentFontFormatter{   

    List<Component> componentes;

    public ComponentFontFormatter(Container c) {
        componentes = getAllComponents(c);
    }
    
    public void format(Color primaryColor, ComponentesSwing cs) {
//        if (cs == ComponentesSwing.JLABEL) {
//            for (Component comp : componentes) {
//                if (comp instanceof JLabel) {
//                    JLabel j = (JLabel) comp;
//                    j.setForeground(primaryColor);
//                }
//            }
//        } else if (cs == ComponentesSwing.JTEXTFIELD) {
//            for (Component comp : componentes) {
//                if (comp instanceof JTextField) {
//                    JTextField j = (JTextField) comp;
//                    j.setBackground(primaryColor);
//                }
//            }
//        }

        switch (cs) {
            case JLABEL:
                for(Component comp : componentes) {
                    if(comp instanceof JLabel) {
                        JLabel j = (JLabel) comp;
                        j.setForeground(primaryColor);
                    }
                }
                
                break;
                
            case JTEXTFIELD:
                for (Component comp : componentes) {
                    if (comp instanceof JTextField) {
                        JTextField j = (JTextField) comp;
                        j.setForeground(primaryColor);
                    }
                }
                break;
                
            case JBUTTON:
                for (Component comp : componentes) {
                    if (comp instanceof JButton) {
                        JButton j = (JButton) comp;
                        j.setBackground(primaryColor);
                    }
                }
                
            default:
                throw new AssertionError();
        }
    }
    
    
    private List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        List<Component> compList = new ArrayList<Component>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container) {
                compList.addAll(getAllComponents((Container) comp));
            }
        }
        return compList;
    }

}
