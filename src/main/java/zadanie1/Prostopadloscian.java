package zadanie1;

import com.jogamp.opengl.GL2;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.*;

class Prostopadloscian {

    private Punkt[] punkty;
    private List<Sciana> sciany;
    private float wysokosc;
    private float szerokosc;
    private float dlugosc;
    private Punkt srodekCiezkosciProstopadloscianu;

    Prostopadloscian(@JsonProperty("x") float startX,
                     @JsonProperty("y") float startY,
                     @JsonProperty("z") float startZ,
                     @JsonProperty("wysokosc") float startWysokosc,
                     @JsonProperty("szerokosc") float startSzerokosc,
                     @JsonProperty("dlugosc") float startDlugosc) {
        this.punkty = new Punkt[]{
                /*A*/      new Punkt(startX, startY, startZ),
                /*B*/      new Punkt(startX + startSzerokosc, startY, startZ),
                /*C*/      new Punkt(startX + startSzerokosc, startY, startZ + startDlugosc),
                /*D*/      new Punkt(startX, startY, startZ + startDlugosc),
                /*A'*/     new Punkt(startX, startY + startWysokosc, startZ),
                /*B'*/     new Punkt(startX + startSzerokosc, startY + startWysokosc, startZ),
                /*C'*/     new Punkt(startX + startSzerokosc, startY + startWysokosc, startZ + startDlugosc),
                /*D'*/     new Punkt(startX, startY + startWysokosc, startZ + startDlugosc)
        };
        this.wysokosc = startWysokosc;
        this.dlugosc = startDlugosc;
        this.szerokosc = startSzerokosc;

        this.sciany = new ArrayList<>();
        this.sciany.add(new Sciana(1f, 0.5f, 0.5f,
                new Punkt[]{punkty[0], punkty[1], punkty[2], punkty[3]},
                new Punkt(startX + startSzerokosc / 2, startY, startZ + startDlugosc / 2)));
        this.sciany.add(new Sciana( 1f, 1f, 0.5f,
                new Punkt[]{punkty[4], punkty[5], punkty[6], punkty[7]},
                new Punkt(startX + startSzerokosc / 2, startY + startWysokosc, startZ + startDlugosc / 2)));
        this.sciany.add(new Sciana( 0.5f, 0.5f, 0.5f,
                new Punkt[]{punkty[0], punkty[1], punkty[5], punkty[4]},
                new Punkt(startX + startSzerokosc / 2, startY + startWysokosc / 2, startZ)));
        this.sciany.add(new Sciana( 0.5f, 1f, 0.5f,
                new Punkt[]{punkty[1], punkty[2], punkty[6], punkty[5]},
                new Punkt(startX + startSzerokosc, startY + startWysokosc / 2, startZ + startDlugosc / 2)));
        this.sciany.add(new Sciana( 0f, 0f, 1f,
                new Punkt[]{punkty[2], punkty[3], punkty[7], punkty[6]},
                new Punkt(startX + startSzerokosc / 2, startY + startWysokosc / 2, startZ + startDlugosc)));
        this.sciany.add(new Sciana( 0.5f, 1f, 1f,
                new Punkt[]{punkty[3], punkty[0], punkty[4], punkty[7]},
                new Punkt(startX, startY + startWysokosc / 2, startZ + startDlugosc / 2)));

        this.srodekCiezkosciProstopadloscianu = new Punkt(startX + startSzerokosc / 2, startY + startWysokosc / 2, startZ + startDlugosc / 2);
    }

    private void setWysokosc(float wysokosc) {
        this.wysokosc = wysokosc;
    }

    private void setSzerokosc(float szerokosc) {
        this.szerokosc = szerokosc;
    }

    private void setDlugosc(float dlugosc) {
        this.dlugosc = dlugosc;
    }

    private void aktualizujProstopadloscian(Punkt[] nowePunkty) {

        Punkt punktA = nowePunkty[0];
        this.setSzerokosc(nowePunkty[1].obliczOdlegloscOdPunktu(nowePunkty[0]));
        this.setWysokosc(nowePunkty[4].obliczOdlegloscOdPunktu(nowePunkty[0]));
        this.setDlugosc(nowePunkty[3].obliczOdlegloscOdPunktu(nowePunkty[0]));

        this.sciany.get(0).aktualizujSciane(1f, 0.5f, 0.5f,
                new Punkt[]{nowePunkty[0], nowePunkty[1], nowePunkty[2], nowePunkty[3]},
                new Punkt(punktA.x() + szerokosc / 2, punktA.y(), punktA.z() + dlugosc / 2));
        this.sciany.get(1).aktualizujSciane(1f, 1f, 0.5f,
                new Punkt[]{nowePunkty[4], nowePunkty[5], nowePunkty[6], nowePunkty[7]},
                new Punkt(punktA.x() + szerokosc / 2, punktA.y() + wysokosc, punktA.z() + dlugosc / 2));
        this.sciany.get(2).aktualizujSciane(0.5f, 0.5f, 0.5f,
                new Punkt[]{nowePunkty[0], nowePunkty[1], nowePunkty[5], nowePunkty[4]},
                new Punkt(punktA.x() + szerokosc / 2, punktA.y() + wysokosc / 2, punktA.z()));
        this.sciany.get(3).aktualizujSciane(0.5f, 1f, 0.5f,
                new Punkt[]{nowePunkty[1], nowePunkty[2], nowePunkty[6], nowePunkty[5]},
                new Punkt(punktA.x() + szerokosc, punktA.y() + wysokosc / 2, punktA.z() + dlugosc / 2));
        this.sciany.get(4).aktualizujSciane(0f, 0f, 1f,
                new Punkt[]{nowePunkty[2], nowePunkty[3], nowePunkty[7], nowePunkty[6]},
                new Punkt(punktA.x() + szerokosc / 2, punktA.y() + wysokosc / 2, punktA.z() + dlugosc));
        this.sciany.get(5).aktualizujSciane(0.5f, 1f, 1f,
                new Punkt[]{nowePunkty[3], nowePunkty[0], nowePunkty[4], nowePunkty[7]},
                new Punkt(punktA.x(), punktA.y() + wysokosc / 2, punktA.z() + dlugosc / 2));
        this.setSrodekCiezkosciProstopadloscianu(new Punkt(punktA.x() + szerokosc / 2, punktA.y() + wysokosc / 2, punktA.z() + dlugosc / 2));
    }

    private void setSrodekCiezkosciProstopadloscianu(Punkt srodekCiezkosciProstopadloscianu) {
        this.srodekCiezkosciProstopadloscianu = srodekCiezkosciProstopadloscianu;
    }

    void przesun(float x, float y, float z) {
        List<Punkt> nowePunktyLista = new ArrayList<>();
        for (Punkt p : punkty) {
            p.przesun(x, y, z);
            nowePunktyLista.add(p);
        }
        Punkt[] nowePunkty = new Punkt[punkty.length];
        for (int i = 0; i < punkty.length; i++) {
            nowePunkty[i] = nowePunktyLista.get(i);
        }
        this.aktualizujProstopadloscian(nowePunkty);
    }

    void obroc(float katX, float katY, float katZ) {
        List<Punkt> nowePunktyLista = new ArrayList<>();
        for (Punkt p : punkty) {
            p.obroc(katX, katY, katZ);
            nowePunktyLista.add(p);
        }
        Punkt[] nowePunkty = new Punkt[punkty.length];
        for (int i = 0; i < punkty.length; i++) {
            nowePunkty[i] = nowePunktyLista.get(i);
        }
        this.aktualizujProstopadloscian(nowePunkty);
    }

    void zmienPerspektywe(float factor) {
        List<Punkt> nowePunktyLista = new ArrayList<>();
        for (Punkt p : punkty) {
            p.zmienPerspektywe(factor);
            nowePunktyLista.add(p);
        }
        Punkt[] nowePunkty = new Punkt[punkty.length];
        for (int i = 0; i < punkty.length; i++) {
            nowePunkty[i] = nowePunktyLista.get(i);
        }
        this.aktualizujProstopadloscian(nowePunkty);
        this.aktualizujProstopadloscian(nowePunkty);
    }

    void wyswietlFigure(GL2 gl) {
        sciany.sort((sciana1, sciana2) -> Float.compare(sciana2.odlegloscScianyOdKamery(), sciana1.odlegloscScianyOdKamery()));
        for (Sciana s : sciany) {
            s.wyswietlSciane(gl);
        }
    }

    float odlegloscOdKamery() {
        return srodekCiezkosciProstopadloscianu.obliczOdlegloscOdKamery();
    }
}


