package GUI.JavaBeanExample;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;


public class BeanExample extends  JComponent implements PropertyChangeListener, Serializable, VetoableChangeListener {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoes = new VetoableChangeSupport(this);
    private String title;// Simple property example
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextArea newNoteArea;
    private JTextField newNoteTitle;
    private JList list1;
    private DefaultListModel<Note> model=new DefaultListModel<>();
    private JButton addNoteBtn;
    private JButton removeSelectedNoteButton;
    private JButton readNoteBtn;
    private JTextArea readOnlyArea;
    private JLabel NoteTitle;
    private ArrayList<Note> noteList=new ArrayList<>();
    private int noteListMaxSize=10;
    public BeanExample()
    {
        readOnlyArea.setEditable(false);
        list1.setModel(model);
        title="default title";
        titleLabel.setText(title);
        changes.addPropertyChangeListener(evt -> {
            if(evt.getPropertyName()=="title")
            titleLabel.setText(title);
            JOptionPane.showMessageDialog(null, "TITLE LABEL ZOSTAJE ZMIENIONY!", "Property CHange: ", JOptionPane.INFORMATION_MESSAGE);
        });
        VetoableChangeListener vetoPlus = new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                if(evt.getPropertyName()=="listSize"){
                    if(noteListMaxSize >= (int)evt.getNewValue()) {
                        Note newNote = new Note(newNoteArea.getText(), newNoteTitle.getText());

                        noteList.add(newNote);
                        model.add(noteList.size()-1,newNote);
                        System.out.println(noteList.size());
                    }
                    else{
                        throw new PropertyVetoException("List size will be wrong",evt);
                    }
                }
            }
        };
        VetoableChangeListener vetoMinus = new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {

                if(evt.getPropertyName()=="listSizeMinus"){
                    if(((int)evt.getOldValue()>=1)) {


                        noteList.remove(list1.getSelectedIndex());
                        model.remove(list1.getSelectedIndex());
                     //   list1.remove(list1.getSelectedIndex());
                        System.out.println(noteList.size());
                      //  list1.repaint();
                    }
                    else{
                        throw new PropertyVetoException("List size will be wrong",evt);
                    }
                }
            }
        };
        vetoes.addVetoableChangeListener(vetoPlus);
        vetoes.addVetoableChangeListener(vetoMinus);
        addNoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newNoteTitle!=null && newNoteArea!=null)
                {
                    AddNote();
                }
            }
        });
        readNoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setReadOnlyArea();
                setNoteTitle();
            }
        });
        removeSelectedNoteButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!list1.isSelectionEmpty())
                RemoveNote(list1.getSelectedIndex());
            }
        });
    }



    public int getNoteListMaxSize(){
        return this.noteListMaxSize;
    }
    public void setNoteListMaxSize(int noteListMaxSize)
    {
        this.noteListMaxSize=noteListMaxSize;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        String oldTitle = new String(this.title);
        this.title=title;
        changes.firePropertyChange("title", oldTitle, new String(this.title));
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    /// PropertyChangeListeners
    public void addPropertyChangeListener (
            PropertyChangeListener p) {
        changes.addPropertyChangeListener (p);
    }

    public void removePropertyChangeListener (
            PropertyChangeListener p) {
        changes.removePropertyChangeListener (p);
    }

    public void AddNote()
    {

        Integer oldSize = new Integer(noteList.size());
        try {
            vetoes.fireVetoableChange("listSize",oldSize,new Integer(noteList.size()+1));
        } catch (PropertyVetoException e) {
            JOptionPane.showMessageDialog(null, "Wartość listy została przekroczona!", "Veto!!: ", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void RemoveNote(int index)
    {
        Integer oldSize = new Integer(noteList.size());
        try {
            vetoes.fireVetoableChange("listSizeMinus",oldSize,new Integer(noteList.size()-1));
        } catch (PropertyVetoException e) {
            JOptionPane.showMessageDialog(null, "Wartość listy jest 0 !", "Veto!!: ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method gets called when a constrained property is changed.
     *
     * @param evt a <code>PropertyChangeEvent</code> object describing the
     *            event source and the property that has changed.
     * @throws PropertyVetoException if the recipient wishes the property
     *                               change to be rolled back.
     */
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        System.out.println("VOTABLE CHANGE");
    }

    public void addVetoableChangeListener (VetoableChangeListener v) {
        vetoes.addVetoableChangeListener (v);
    }
    public void removeVetoableChangeListener (VetoableChangeListener v) {
        vetoes.removeVetoableChangeListener (v);
    }
    /**
     * Invoked when an action occurs.
     *
     * @param e
     */
    public void setReadOnlyArea() {
        if(!list1.isSelectionEmpty()){
            String text=noteList.get(list1.getSelectedIndex()).getNoteText();
        this.readOnlyArea.setText(text);
    }

    }

    public void setNoteTitle() {
        if(!list1.isSelectionEmpty()){
            String text=noteList.get(list1.getSelectedIndex()).getNoteTitle();
            this.NoteTitle.setText(text);
    }}
}
