package Juegos.flappy_bird;
import Juegos.flappy_bird.FlappyBird;

import javax.swing.*;
import java.awt.*;

public class BirdRenderer extends JPanel {

    private static final long serialVersionUID = 1L;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        FlappyBird.flappyBird.pintar(g);
    }
}
