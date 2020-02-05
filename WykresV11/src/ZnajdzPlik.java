import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

class ZnajdzPlik {
    private String sciezka = "";

    public ZnajdzPlik() {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); //tworzenie Chosera
        jfc.setDialogTitle("Wybierz plik tekstowy");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tylko tekst", "txt");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            sciezka = jfc.getSelectedFile().getPath();
        }
    }

    public String zwrocsciezke() {
        return sciezka;
    }

    public boolean zabezpiecz() {
        return !sciezka.equals("");
    }
}

/*
mkyong.com/swing/java-swing-jfilechooser-example
 */