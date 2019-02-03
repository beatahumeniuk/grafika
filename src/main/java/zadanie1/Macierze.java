package zadanie1;

import static com.jogamp.opengl.math.FloatUtil.cos;
import static com.jogamp.opengl.math.FloatUtil.sin;

class Macierze {

    static float[][] przesuniecie(float tx, float ty, float tz) {
        return new float[][]{
                {1, 0, 0, tx},
                {0, 1, 0, ty},
                {0, 0, 1, tz},
                {0, 0, 0, 1}
        };
    }

    static float[][] obrotOX(float kat) {
        return new float[][]{
                {1, 0, 0, 0},
                {0, cos(kat), -sin(kat), 0},
                {0, sin(kat), cos(kat), 0},
                {0, 0, 0, 1}
        };
    }

    static float[][] obrotOY(float kat) {
        return new float[][]{
                {cos(kat), 0, sin(kat), 0},
                {0, 1, 0, 0},
                {-sin(kat), 0, cos(kat), 0},
                {0, 0, 0, 1}
        };
    }

    static float[][] obrotOZ(float kat) {
        return new float[][]{
                {cos(kat), -sin(kat), 0, 0},
                {sin(kat), cos(kat), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
    }
    static float[][] przeksztalceniePerspektywiczne(float wektor) {
        return new float[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, wektor, 0},
                {0, 0, 0, 1}
        };
    }

    static float[] wykonajTransformacje(float[][] transformacja, float[] punkty) {
        float[] wynik = new float[punkty.length];
        for (int i = 0; i < transformacja.length; i++) {
            float val = 0;
            for (int j = 0; j < transformacja[i].length; j++) {
                val += transformacja[i][j] * punkty[j];
            }
            wynik[i] = val;
        }

        return wynik;
    }
}
