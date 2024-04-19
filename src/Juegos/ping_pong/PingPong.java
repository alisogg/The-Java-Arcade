package Juegos.ping_pong;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Código referenciado del siguiente link y autor: https://github.com/Gaspared/pong
 * Autor: Gaspared
 */

public class PingPong extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static final int ANCHO_PANTALLA = 800;
    private static final int ALTO_PANTALLA = 600;
    private static final int ALTO_JUGADOR = 100;
    private static final int ANCHO_JUGADOR = 15;
    private static final double RADIO_PELOTA = 15;
    private int velocidadPelotaY = 1;
    private int velocidadPelotaX = 1;
    private double posicionJugadorUnoY = ALTO_PANTALLA / 2;
    private double posicionJugadorDosY = ALTO_PANTALLA / 2;
    private double posicionPelotaX = ANCHO_PANTALLA / 2;
    private double posicionPelotaY = ALTO_PANTALLA / 2;
    private int puntajeJugadorUno = 0;
    private int puntajeJugadorDos = 0;
    private boolean juegoIniciado;
    private int posicionJugadorUnoX = 0;
    private double posicionJugadorDosX = ANCHO_PANTALLA - ANCHO_JUGADOR;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ping Pong");

        Canvas canvas = new Canvas(ANCHO_PANTALLA, ALTO_PANTALLA);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseMoved(e -> posicionJugadorUnoY = e.getY());
        canvas.setOnMouseClicked(e -> juegoIniciado = true);
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.show();
        tl.play();
    }

    public void run(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, ANCHO_PANTALLA, ALTO_PANTALLA);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if (juegoIniciado) {
            posicionPelotaX += velocidadPelotaX;
            posicionPelotaY += velocidadPelotaY;

            if (posicionPelotaX < ANCHO_PANTALLA - ANCHO_PANTALLA / 4) {
                posicionJugadorDosY = posicionPelotaY - ALTO_JUGADOR / 2;
            } else {
                posicionJugadorDosY = posicionPelotaY > posicionJugadorDosY + ALTO_JUGADOR / 2 ? posicionJugadorDosY += 1 : posicionJugadorDosY - 1;
            }

            gc.fillOval(posicionPelotaX, posicionPelotaY, RADIO_PELOTA, RADIO_PELOTA);
        } else {
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click para iniciar", ANCHO_PANTALLA / 2, ALTO_PANTALLA / 2);

            posicionPelotaX = ANCHO_PANTALLA / 2;
            posicionPelotaY = ALTO_PANTALLA / 2;

            velocidadPelotaX = new Random().nextInt(2) == 0 ? 1 : -1;
            velocidadPelotaY = new Random().nextInt(2) == 0 ? 1 : -1;
        }

        if (posicionPelotaY > ALTO_PANTALLA || posicionPelotaY < 0) velocidadPelotaY *= -1;

        if (posicionPelotaX < posicionJugadorUnoX - ANCHO_JUGADOR) {
            puntajeJugadorDos++;
            juegoIniciado = false;
        }

        if (posicionPelotaX > posicionJugadorDosX + ANCHO_JUGADOR) {
            puntajeJugadorUno++;
            juegoIniciado = false;
        }

        if (((posicionPelotaX + RADIO_PELOTA > posicionJugadorDosX) && posicionPelotaY >= posicionJugadorDosY && posicionPelotaY <= posicionJugadorDosY + ALTO_JUGADOR) ||
                ((posicionPelotaX < posicionJugadorUnoX + ANCHO_JUGADOR) && posicionPelotaY >= posicionJugadorUnoY && posicionPelotaY <= posicionJugadorUnoY + ALTO_JUGADOR)) {
            velocidadPelotaY += 1 * Math.signum(velocidadPelotaY);
            velocidadPelotaX += 1 * Math.signum(velocidadPelotaX);
            velocidadPelotaX *= -1;
            velocidadPelotaY *= -1;
        }

        gc.fillText(puntajeJugadorUno + "\t\t\t\t\t\t" + puntajeJugadorDos, ANCHO_PANTALLA / 2, 100);

        gc.fillRect(posicionJugadorDosX, posicionJugadorDosY, ANCHO_JUGADOR, ALTO_JUGADOR);
        gc.fillRect(posicionJugadorUnoX, posicionJugadorUnoY, ANCHO_JUGADOR, ALTO_JUGADOR);
    }



}
