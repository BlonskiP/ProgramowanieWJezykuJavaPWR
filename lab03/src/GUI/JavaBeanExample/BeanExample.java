package GUI.JavaBeanExample;

import javax.swing.*;
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
    private JButton addNoteBtn;
    private JButton removeSelectedNoteButton;
    private JButton readNoteBtn;
    private JTextArea readOnlyArea;
    private JLabel NoteTitle;
    private ArrayList<Note> noteList=new ArrayList<>();
    private int noteListMaxSize=10;

    public BeanExample()
    {
        title="default title";
        titleLabel.setText(title);
        changes.addPropertyChangeListener(evt -> {
            if(evt.getPropertyName()=="title")
            titleLabel.setText(title);
            JOptionPane.showMessageDialog(null, "TITLE LABEL ZOSTAJE ZMIENIONY!", "Property CHange: ", JOptionPane.INFORMATION_MESSAGE);
        });
        vetoes.addVetoableChangeListener(new VetoableChangeListener() {
            @Override
            public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
                if(evt.getPropertyName()=="listSize"){
                    if(noteListMaxSize >= (int)evt.getNewValue()) {
                        Note newNote = new Note(newNoteArea.getText(), newNoteTitle.getText());

                        noteList.add(newNote);
                        System.out.println(noteList.size());
                    }
                    else{
                        throw new PropertyVetoException("List is full",evt);
                    }
                }
            }
        });
        addNoteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(newNoteTitle!=null && newNoteArea!=null)
                {
                    AddNote();
                }
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
        this.noteList.remove(0); //0 temp
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

}
