import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

class Okno implements Observer {
    private JPanel glowny;
    private JPanel wewnetrzny;
    private JPanel a1;
    private JPanel a2;
    private JPanel a3;
    private JPanel a4;
    private JPanel a5;
    private JButton gotoweButton;
    private JLabel raport;
    private JLabel timer;
    private JTextField tytul;
    private JTextField textX;
    private JTextField textY;
    private JButton kliknijButton;
    private JTextField textLegenda;
    private JTextField textPrzypisX;
    private JTextField textPrzypisY;
    private JComboBox comboKolor;
    private JCheckBox checkBoxPrzerywana;
    private JCheckBox checkBoxAproksymacji;
    private final Licznik LiczydloWatku;
    private int minuty = 0;
    private int godziny = 0;
    private Wczytaj toWczytaj;
    private ZnajdzPlik znajdzTenPlik;


    public Okno() {

        LiczydloWatku = new Licznik();
        LiczydloWatku.addObserver(this);
        Thread watek = new Thread(LiczydloWatku);
        watek.start();
        LiczydloWatku.setCzyLiczyc(); //ustawienie watku licznika


        gotoweButton.addActionListener(actionEvent -> glownePrzygotowanie()); //wyrazenie lambda do przycisku

        kliknijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                znajdzTenPlik = new ZnajdzPlik();
                gotoweButton.setEnabled(true);
            }
        });
    }

    public JPanel zwrocglowny() {
        return glowny;
    }

    @Override
    public void update(Observable a0, Object a1) {
        final Integer counter = (Integer) a1;
        SwingUtilities.invokeLater(() -> timer.setText(timeremZarzadca(counter)));
    }

    private String timeremZarzadca(int temp) {
        int sekundy = temp;

        if (sekundy == 60) {
            ++minuty;
            sekundy = 0;
            LiczydloWatku.setLiczydlo();
        }
        if (minuty == 60) {
            ++godziny;
            minuty = 0;
            sekundy = 0;
        }
        if (godziny == 60) {
            godziny = 0;
            minuty = 0;
            sekundy = 0;
        }

        return godziny + " : " + minuty + " : " + sekundy + "   ";
    }

    void glownePrzygotowanie() {

        if (znajdzTenPlik.zabezpiecz()) {
            try {
                toWczytaj = new Wczytaj(znajdzTenPlik.zwrocsciezke(), textPrzypisX.getText(), textPrzypisY.getText());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (!toWczytaj.zgodnosc()) {
                raport.setText("Podane wektory nie są tej samej długości");
            } else if (toWczytaj.zgodnosc()) {

                raport.setText("Powodzenie");
                Wykres moj = new Wykres(tytul.getText(), textLegenda.getText(), textX.getText(),
                        textY.getText(), toWczytaj.zwrocX(), toWczytaj.zwrocY(), comboKolor.getSelectedIndex(), checkBoxPrzerywana.isSelected(), checkBoxAproksymacji.isSelected());
                moj.pack();
                moj.setVisible(true);
                //moj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }


        } else {
            raport.setText("Nie wczytano poprawnie sciezki");
        }


    }

}
