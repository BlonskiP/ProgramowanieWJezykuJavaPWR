package TicTacToe;

import java.io.File;
import java.io.FilenameFilter;

public class CppFileFilter implements FilenameFilter {
    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param dir  the directory in which the file was found.
     * @param name the name of the file.
     * @return <code>true</code> if and only if the name should be
     * included in the file list; <code>false</code> otherwise.
     */
    @Override
    public boolean accept(File dir, String name) {
        if(name.endsWith(".dll"))return true;
        return false;
    }
}
