package GUI.JavaBeanExample;

public class Note {
    String NoteText;
    String NoteTitle;
    public Note(String NoteText,String NoteTitle)
    {
        this.NoteText=NoteText;
        this.NoteTitle=NoteTitle;
    }

    public String getNoteText() {
        return NoteText;
    }

    public String getNoteTitle() {
        return NoteTitle;
    }

    public void setNoteText(String noteText) {
        NoteText = noteText;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }
}
