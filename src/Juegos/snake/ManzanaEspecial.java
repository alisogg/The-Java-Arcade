package Juegos.snake;

import java.util.Random;
import java.util.TimerTask;

public class ManzanaEspecial extends TimerTask {
    private int posX;
    private int posY;
    private Serpiente serpiente;

    public ManzanaEspecial(Serpiente serpiente) {
        this.serpiente = serpiente;
    }

    public ManzanaEspecial() {
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
        if (this.serpiente.getManzanaEspecial() == null) {
            this.serpiente.setManzanaEspecial(new ManzanaEspecial());
        }
    }
}
