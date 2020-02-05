import java.util.Observable;

class Licznik extends Observable implements Runnable {
    private int liczydlo = 0;
    private boolean czyLiczyc = false;

    @Override
    public void run() {
        Thread watek1 = Thread.currentThread();
        while (true) {
            if (czyLiczyc) {
                liczydlo++;
                setChanged();
                notifyObservers(liczydlo);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    void setCzyLiczyc() {
        czyLiczyc = true;
    }

    void setLiczydlo() {
        liczydlo = 0;
    }

}