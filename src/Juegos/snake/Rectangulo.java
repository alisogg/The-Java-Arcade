package Juegos.snake;

public class Rectangulo {

    private int posX;
    private int posY;

    public static final int ANCHO_REC = 25;
    public static final int ALTO_REC = 25;

    public Rectangulo(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean intersecta(Rectangulo r) {
        return this.posX == r.getPosX() && this.posY == r.getPosY();
    }

    public void setPosX(int incremento) {
        this.posX = this.posX + incremento;
    }

    public void setPosY(int incremento) {
        this.posY = this.posY + incremento;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }
}
