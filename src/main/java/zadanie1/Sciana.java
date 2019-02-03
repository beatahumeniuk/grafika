package zadanie1;

import com.jogamp.opengl.GL2;

class Sciana {
    private float r, g, b;
    private Punkt[] wierzcholki;
    private Punkt srodekCiezkosci;

    Sciana(float r, float g, float b, Punkt[] wierzcholki, Punkt srodekCiezkosci) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.wierzcholki = wierzcholki;
        this.srodekCiezkosci = srodekCiezkosci;
    }

    void aktualizujSciane(float r, float g, float b, Punkt[] wierzcholki, Punkt srodekCiezkosci){
        this.setWierzcholki(wierzcholki);
        this.setSrodekCiezkosci(srodekCiezkosci);
        this.setR(r);
        this.setG(g);
        this.setB(b);
    }

    void wyswietlSciane(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);
            gl.glColor3f(r, g, b);
        for (Punkt wierzcholek : wierzcholki) {
            gl.glVertex3f(wierzcholek.x(), wierzcholek.y(), wierzcholek.z());
        }
        gl.glEnd();
    }

    float odlegloscScianyOdKamery(){
        return srodekCiezkosci.obliczOdlegloscOdKamery();
    }

    private void setWierzcholki(Punkt[] wierzcholki) {
        this.wierzcholki = wierzcholki;
    }

    private void setSrodekCiezkosci(Punkt srodekCiezkosci) {
        this.srodekCiezkosci = srodekCiezkosci;
    }

    private void setR(float r) {
        this.r = r;
    }

    private void setG(float g) {
        this.g = g;
    }

    private void setB(float b) {
        this.b = b;
    }
}

