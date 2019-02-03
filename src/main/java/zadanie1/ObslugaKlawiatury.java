package zadanie1;

import javax.swing.*;
import java.awt.event.KeyEvent;

class ObslugaKlawiatury {

    private static final float PRZESUNIECIE =  0.5f;
    private static final float OBROT = (float) Math.PI / 90;
    private static final float WEKTOR_PRZEKSZTALCENIA = 1 / 0.95f;

    private final Scena scena;
    private float przesuniecie;
    private float obrot;
    private boolean czyKlikniety;


    ObslugaKlawiatury(Scena scena) {
        this.scena = scena;
        this.przesuniecie = PRZESUNIECIE;
        this.obrot = OBROT;
        this.czyKlikniety = false;
    }

    private void wyswietlInstrukcje() {
        JFrame okno = new JFrame("Instrukcja sterowania");
        okno.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(okno, instrukcja());
    }


    private String instrukcja() {
        return "PRZESUNIECIE KAMERY: \n" +
                "W - przod \n" +
                "S - tyl\n" +
                "A - lewo\n" +
                "D - prawo\n" +
                "3 - gora\n" +
                "Z - dol \n\n" +
                "OBROT KAMERY: \n" +
                "STRZAŁKA W GÓRĘ - gora\n" +
                "STRZAŁKA W DÓŁ - dol\n" +
                "STRZAŁKA W LEWO - lewo\n" +
                "STRZAŁKA W PRAWO - prawo\n" +
                "PAGE DOWN - zgodnie z ruchem wskazowek zegara\n" +
                "PAGE UP - przeciwnie do ruchu wskazowek zegara\n\n" +
                "ZMIANA OGNISKOWEJ:\n" +
                "1 - zmniejsz\n" +
                "2 - zwiększ";
    }

    String pomoc() {
        return "Wcisnij H aby wlaczyc instrukcje sterowania";
    }

    public void klawiszWcisniety(KeyEvent klawisz){

        if (czyKlikniety) return;
        int klucz = klawisz.getKeyCode();
        switch (klucz) {
            case KeyEvent.VK_W:
                scena.przesunOZ(przesuniecie);
                break;

            case KeyEvent.VK_S:
                scena.przesunOZ(-przesuniecie);
                break;

            case KeyEvent.VK_A:
                scena.przesunOX(przesuniecie);
                break;

            case KeyEvent.VK_D:
                scena.przesunOX(-przesuniecie);
                break;

            case KeyEvent.VK_Z:
                scena.przesunOY(przesuniecie);
                break;

            case KeyEvent.VK_3:
                scena.przesunOY(-przesuniecie);
                break;

            case KeyEvent.VK_PAGE_UP:
                scena.obrocOZ(-obrot);
                break;

            case KeyEvent.VK_PAGE_DOWN:
                scena.obrocOZ(obrot);
                break;

            case KeyEvent.VK_DOWN:
                scena.obrocOX(obrot);
                break;

            case KeyEvent.VK_UP:
                scena.obrocOX(-obrot);
                break;

            case KeyEvent.VK_RIGHT:
                scena.obrocOY(obrot);
                break;

            case KeyEvent.VK_LEFT:
                scena.obrocOY(-obrot);
                break;

            case KeyEvent.VK_1:
                czyKlikniety = true;
                scena.zmienPerspektywe(1 / WEKTOR_PRZEKSZTALCENIA);
                break;

            case KeyEvent.VK_2:
                czyKlikniety = true;
                scena.zmienPerspektywe(WEKTOR_PRZEKSZTALCENIA);
                break;

            case KeyEvent.VK_H:
                czyKlikniety = true;
                wyswietlInstrukcje();
                break;
        }
    }

    public void klawiszZwolniony() {
        czyKlikniety = false;
    }
}
