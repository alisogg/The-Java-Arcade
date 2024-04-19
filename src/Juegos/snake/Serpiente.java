package Juegos.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static Juegos.snake.Rectangulo.ALTO_REC;
import static Juegos.snake.Rectangulo.ANCHO_REC;

public class Serpiente extends JPanel {

    private static final Color color = new Color(115, 162, 78);
    private static final int inicio = 250;
    private static final int velocidad = 25;

    private ArrayList<Rectangulo> cuerpo;
    private String direccion;
    private Manzana manzana;
    private ManzanaEspecial manzanaEspecial;
    private final MainSnake ventana;

    public Serpiente(Juegos.snake.MainSnake ventana) {
        this.ventana = ventana;

        this.cuerpo = new ArrayList<>();
        cuerpo.add(new Rectangulo(inicio, inicio));
        Rectangulo ultimo = this.cuerpo.get(0);
        cuerpo.add(new Rectangulo(ultimo.getPosX() - ANCHO_REC, ultimo.getPosY()));
        Rectangulo ultimo_2 = this.cuerpo.get(1);
        cuerpo.add(new Rectangulo(ultimo_2.getPosX() - ANCHO_REC, ultimo_2.getPosY()));

        this.direccion = "derecha";
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void agregarParteNormal() {
        Rectangulo ultimo = this.cuerpo.get(this.cuerpo.size() - 1);

        switch (this.direccion) {
            case "derecha":
                this.cuerpo.add(new Rectangulo(ultimo.getPosX() - ANCHO_REC, ultimo.getPosY()));
                break;
            case "izquierda":
                this.cuerpo.add(new Rectangulo(ultimo.getPosX() + ANCHO_REC, ultimo.getPosY()));
                break;
            case "arriba":
                this.cuerpo.add(new Rectangulo(ultimo.getPosX(), ultimo.getPosY() + ALTO_REC));
                break;
            case "abajo":
                this.cuerpo.add(new Rectangulo(ultimo.getPosX(), ultimo.getPosY() - ALTO_REC));
                break;
        }
    }

    public void agregarPartePoder() {
        Rectangulo ultimo = this.cuerpo.get(this.cuerpo.size() - 1);

        switch (this.direccion) {
            case "derecha":
                this.cuerpo.add(new Rectangulo(ultimo.getPosX() - ANCHO_REC, ultimo.getPosY()));
                this.cuerpo.add(new Rectangulo(ultimo.getPosX() - ANCHO_REC, ultimo.getPosY()));
                this.cuerpo.add(new Rectangulo(ultimo.getPosX() - ANCHO_REC, ultimo.getPosY()));
                break;
            case "izquierda":
                this.cuerpo.add(new Rectangulo(ultimo.getPosX() + ANCHO_REC, ultimo.getPosY()));
                this.cuerpo.add(new Rectangulo(ultimo.getPosX() + ANCHO_REC, ultimo.getPosY()));
                this.cuerpo.add(new Rectangulo(ultimo.getPosX() + ANCHO_REC, ultimo.getPosY()));
                break;
            case "arriba":
                this.cuerpo.add(new Rectangulo(ultimo.getPosX(), ultimo.getPosY() + ALTO_REC));
                this.cuerpo.add(new Rectangulo(ultimo.getPosX(), ultimo.getPosY() + ALTO_REC));
                this.cuerpo.add(new Rectangulo(ultimo.getPosX(), ultimo.getPosY() + ALTO_REC));
                break;
            case "abajo":
                this.cuerpo.add(new Rectangulo(ultimo.getPosX(), ultimo.getPosY() - ALTO_REC));
                this.cuerpo.add(new Rectangulo(ultimo.getPosX(), ultimo.getPosY() - ALTO_REC));
                this.cuerpo.add(new Rectangulo(ultimo.getPosX(), ultimo.getPosY() - ALTO_REC));
                break;
        }
    }

    public void checarColision() {
        Rectangulo r3 = this.cuerpo.get(0);

        for (int i = 1; i < this.cuerpo.size(); i++) {
            Rectangulo r2 = this.cuerpo.get(i);
            if (r3.intersecta(r2)) {
                System.out.println("¡Perdiste!");
                this.ventana.setVisible(false);

                JFrame frame = new JFrame("Juego terminado.");
                JOptionPane.showMessageDialog(frame, "Puntaje obtenido: " + (this.cuerpo.size()));

                this.ventana.dispatchEvent(new WindowEvent(this.ventana, WindowEvent.WINDOW_CLOSING));
                System.exit(0);
            }
        }

        if (this.manzana != null) {
            if (r3.intersecta(new Rectangulo(this.manzana.getPosX(), this.manzana.getPosY()))) {
                this.manzana = null;
                this.agregarParteNormal();
            }
        }

        if (this.manzanaEspecial != null) {
            if (r3.intersecta(new Rectangulo(this.manzanaEspecial.getPosX(), this.manzanaEspecial.getPosY()))) {
                this.manzanaEspecial = null;
                this.agregarPartePoder();
            }
        }
    }

    public void moverSerpiente() {
        ArrayList<Rectangulo> nuevoCuerpo = new ArrayList<>();

        Rectangulo primero = this.cuerpo.get(0);
        Rectangulo cabeza = new Rectangulo(primero.getPosX(), primero.getPosY());

        switch (this.direccion) {
            case "derecha":
                cabeza.setPosX(velocidad);
                break;
            case "izquierda":
                cabeza.setPosX(-velocidad);
                break;
            case "arriba":
                cabeza.setPosY(-velocidad);
                break;
            case "abajo":
                cabeza.setPosY(velocidad);
                break;
        }

        nuevoCuerpo.add(cabeza);

        for (int i = 1; i < this.cuerpo.size(); i++) {
            Rectangulo previo = this.cuerpo.get(i - 1);
            Rectangulo nuevo = new Rectangulo(previo.getPosX(), previo.getPosY());
            nuevoCuerpo.add(nuevo);
        }

        this.cuerpo = nuevoCuerpo;
        checarColision();
    }

    private void dibujarSerpiente(Graphics g) {
        moverSerpiente();

        Graphics2D g2d = (Graphics2D) g;

        if (this.manzana != null) {
            g2d.setPaint(Color.RED);
            g2d.drawRect(this.manzana.getPosX(), this.manzana.getPosY(), ANCHO_REC, ALTO_REC);
            g2d.fillRect(this.manzana.getPosX(), this.manzana.getPosY(), ANCHO_REC, ALTO_REC);
        }

        if (this.manzanaEspecial != null) {
            g2d.setPaint(Color.YELLOW);
            g2d.drawRect(this.manzanaEspecial.getPosX(), this.manzanaEspecial.getPosY(), ANCHO_REC, ALTO_REC);
            g2d.fillRect(this.manzanaEspecial.getPosX(), this.manzanaEspecial.getPosY(), ANCHO_REC, ALTO_REC);
        }

        g2d.setPaint(Color.BLUE);

        for (Rectangulo r : this.cuerpo) {
            g2d.drawRect(r.getPosX(), r.getPosY(), ANCHO_REC, ALTO_REC);
            g2d.fillRect(r.getPosX(), r.getPosY(), ANCHO_REC, ALTO_REC);
        }
    }

    public void setManzana(Manzana manzana) {
        this.manzana = manzana;
    }

    public void setManzanaEspecial (ManzanaEspecial manzanaEspecial) {
        this.manzanaEspecial = manzanaEspecial;
    }

    public Manzana getManzana() {
        return this.manzana;
    }

    public ManzanaEspecial getManzanaEspecial() {
        return this.manzanaEspecial;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(color);
        dibujarSerpiente(g);
    }
}
