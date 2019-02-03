package zadanie1;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class Scena {

    private final List<Prostopadloscian> prostopadlosciany;

    public Scena(@JsonProperty("prostopadlosciany") List<Prostopadloscian> prostopadlosciany) {
        this.prostopadlosciany = prostopadlosciany;
    }

    List<Prostopadloscian> getProstopadlosciany() {
        prostopadlosciany
                .sort((prostopadloscian1, prostopadloscian2)
                        -> Float.compare(
                                prostopadloscian2.odlegloscOdKamery(),
                                prostopadloscian1.odlegloscOdKamery()));
        return prostopadlosciany;
    }

    void przesunOX(float x) {
        for (Prostopadloscian prostopadloscian : prostopadlosciany)
            prostopadloscian.przesun(x, 0, 0);
    }

    void przesunOY(float y) {
        for (Prostopadloscian prostopadloscian : prostopadlosciany)
            prostopadloscian.przesun(0, y, 0);
    }

    void przesunOZ(float z) {
        for (Prostopadloscian prostopadloscian : prostopadlosciany)
            prostopadloscian.przesun(0, 0, z);
    }

    void obrocOX(float katX) {
        for (Prostopadloscian prostopadloscian : prostopadlosciany)
            prostopadloscian.obroc(katX, 0, 0);
    }

    void obrocOY(float katY) {
        for (Prostopadloscian prostopadloscian : prostopadlosciany)
            prostopadloscian.obroc(0, katY, 0);
    }

    void obrocOZ(float katZ) {
        for (Prostopadloscian prostopadloscian : prostopadlosciany)
            prostopadloscian.obroc(0, 0, katZ);
    }

    void zmienPerspektywe(float wektor) {
        for (Prostopadloscian prostopadloscian : prostopadlosciany)
            prostopadloscian.zmienPerspektywe(wektor);
    }

    static Scena wczytajScene() {
        try {
            InputStream strumien = ClassLoader.getSystemResourceAsStream("sceny/scena.json");
            return new ObjectMapper().readValue(strumien, Scena.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Nie można wczytać sceny!");
        }
    }
}
