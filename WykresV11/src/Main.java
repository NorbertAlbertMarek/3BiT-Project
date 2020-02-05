import javax.swing.*;

class Main {


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Main aplikacja = new Main();
            aplikacja.initGUI();

        });

    }


    private void initGUI() {
        JFrame frame = new JFrame("Okno");
        frame.setContentPane(new Okno().zwrocglowny());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}




