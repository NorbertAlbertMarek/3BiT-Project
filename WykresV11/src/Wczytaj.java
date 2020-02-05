import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class Wczytaj {

    private final ArrayList<Double> danex; //nie pozwol na kolejne referencje
    private final ArrayList<Double> daney;
    private boolean czySaZgodne;


    public Wczytaj(String sciezkata, String flaganazwx, String flaganazwy) throws FileNotFoundException {

        danex = new ArrayList<>();
        daney = new ArrayList<>();

        int flaga = 0;

        File plik = new File(sciezkata);
        Scanner wejscie = new Scanner(plik);
        wejscie.useLocale(Locale.ENGLISH);


        while (wejscie.hasNext()) {
            //jeśli jesteś x to flaga =0
            //jesli jestes y to flaga =1
            // nie mozna przypsiac do zmiennej

            if (wejscie.hasNextFloat()) {
                if (flaga == 0) {
                    danex.add(Double.parseDouble(wejscie.nextLine()));
                } else {

                    daney.add(Double.parseDouble(wejscie.nextLine()));

                }

            } else {

                String s = wejscie.nextLine();
                if (flaganazwx.equals(s)) {
                    flaga = 0;
                } else if (flaganazwy.equals(s)) {
                    flaga = 1;
                }

            }

        }

        czySaZgodne = danex.size() == daney.size();


    }

    public ArrayList<Double> zwrocX() {
        return danex;
    }

    public ArrayList<Double> zwrocY() {
        return daney;
    }

    public boolean zgodnosc() {
        return czySaZgodne;
    }

}
