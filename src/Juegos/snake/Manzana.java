package Juegos.snake;

import java.util.Random;
import java.util.TimerTask;

public class Manzana extends TimerTask {
    private int posX;
    private int posY;
    private Serpiente serpiente;

    public Manzana(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

    public Manzana() {
        this.posX = 25 * new Random().nextInt(20);
        this.posY = 25 * new Random().nextInt(20);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public void run() {
        if (this.serpiente.getManzana() == null) {
            this.serpiente.setManzana(new Manzana());
        }
    }
}
