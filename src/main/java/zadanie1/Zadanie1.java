package zadanie1;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.jogamp.opengl.GL.*;

public class Zadanie1 implements GLEventListener, KeyListener {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private GLU glu = new GLU();

    private static Scena scena;
    private static ObslugaKlawiatury obslugaKlawiatury;
    private static GLCanvas glcanvas;

    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        scena.getProstopadlosciany().forEach(e -> e.wyswietlFigure(gl));
        TextRenderer tr = new TextRenderer(new java.awt.Font("Arial", java.awt.Font.BOLD, 16), true, false, null, false);
        tr.beginRendering(WIDTH, HEIGHT);
        tr.draw(obslugaKlawiatury.pomoc(), WIDTH / 2 - 150, HEIGHT - (HEIGHT - 20));
        tr.endRendering();
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0)
            height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {
    }

    public void init(GLAutoDrawable drawable) {
    }


    public static void main(String[] args) {
        scena = Scena.wczytajScene();
        obslugaKlawiatury = new ObslugaKlawiatury(scena);
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        glcanvas = new GLCanvas(capabilities);
        Zadanie1 zadanie1 = new Zadanie1();
        glcanvas.addGLEventListener(zadanie1);
        glcanvas.addKeyListener(zadanie1);
        glcanvas.setSize(WIDTH, HEIGHT);

        final JFrame frame = new JFrame("Grafika komputerowa i wizualizacja | Beata Humeniuk");
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        obslugaKlawiatury.klawiszWcisniety(keyEvent);
        glcanvas.display();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        obslugaKlawiatury.klawiszZwolniony();
    }
}
