package Juegos.snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * Código referenciado del siguiente link y autor: https://github.com/javacodingcommunity/Snake-Game-Java
 * Autor: javacodingcommunity
 */


public class MainSnake extends JFrame implements KeyListener, ActionListener {

    public static void main(String[] args) {
        EventQueue.invokeLater(MainSnake::new);
    }

    Serpiente serpiente;

    public MainSnake() {
        this.serpiente = new Serpiente(this);

        Timer timer = new Timer(150, this);
        timer.start();

        java.util.Timer drawApples = new java.util.Timer();
        Manzana manzana = new Manzana(this.serpiente);
        ManzanaEspecial manzanaEspecial = new ManzanaEspecial(this.serpiente);
        drawApples.schedule(manzana,0,3000);
        drawApples.schedule(manzanaEspecial,0,100000);

        add(this.serpiente);
        setTitle("Snake");
        setSize(800, 500);
        this.addKeyListener(this);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        if (c == 39 && !this.serpiente.getDireccion().equals("izquierda")) {
            this.serpiente.setDireccion("derecha"); // right arrow pressed
        }

        else if (c == 37 && !this.serpiente.getDireccion().equals("derecha")) {
            this.serpiente.setDireccion("izquierda"); // left arrow pressed
        }

        else if (c == 38 && !this.serpiente.getDireccion().equals("abajo")) {
            this.serpiente.setDireccion("arriba"); // up arrow pressed
        }

        else if (c == 40 && !this.serpiente.getDireccion().equals("arriba")) {
            this.serpiente.setDireccion("abajo"); // down arrow pressed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
