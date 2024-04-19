import Juegos.flappy_bird.FlappyBird;
import Juegos.ping_pong.PingPong;
import Juegos.snake.MainSnake;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Fondo
        Image imagenFondo = new Image("Img/Fondo.jpg");
        ImageView Fondo = new ImageView(imagenFondo);
        //Ajustar el tamaño del fondo a la ventana
        Fondo.setFitHeight(500);
        Fondo.setFitWidth(800);

        //Agregamos la imagen que contiene el titulo del programa
        Image imagenTitulo = new Image("Img/Titulo.png");
        ImageView Titulo = new ImageView(imagenTitulo);
        //Ajustar imagen del titulo
        Titulo.setX(200);
        Titulo.setY(100);
        //Acomodar el titulo con el fondo
        StackPane panelImagen = new StackPane();
        panelImagen.getChildren().addAll(Fondo,Titulo);
        //Cambiar tamaño del titulo
        Titulo.setFitHeight(200);
        Titulo.setFitWidth(400);

        //Botones que dirigiran a cada juego
        VBox contenedorVertical = new VBox();
        HBox contenedorHorizontal = new HBox();
        Button BFP = new Button("Flappy Bird");
        BFP.setId("flapiberd");
        BFP.setTranslateY(-150);
        Button BPP = new Button("Ping-Pong");
        BPP.setId("pinpon");
        BPP.setTranslateY(-150);
        Button BS = new Button("Snake");
        BS.setId("esneik");
        BS.setTranslateY(-150);
        //Orden de los botones (como una tabla)
        HBox cajaBotones = new HBox(50, BFP, BPP, BS);
        cajaBotones.setAlignment(Pos.CENTER);

        //Correr el flappy bird cuando se presione el boton
        BFP.setOnAction(e -> {
            try {
                FlappyBird VentanaFB = new FlappyBird();
                VentanaFB.main(null);
            }catch(Exception ex) {
            }
        });
        //Correr el ping pong cuando se presione el boton
        BPP.setOnAction(e -> {
            PingPong VentanaPP = new PingPong();
            try {
                VentanaPP.start(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        //Correr el snake cuando se presione el boton
        BS.setOnAction(e -> {
            MainSnake VentanaS= new MainSnake();
            VentanaS.actionPerformed(null);
        });

        //El setup de acomodo de la ventana
        BorderPane panelPrincipal = new BorderPane();
        panelPrincipal.setCenter(Fondo);
        panelPrincipal.setCenter(panelImagen);
        panelPrincipal.getChildren().addAll(Fondo,Titulo);
        panelPrincipal.setBottom(cajaBotones);

        // Acomodo de la ventana
        Scene ventana = new Scene(panelPrincipal, 800, 500);



        //Agregar los css para poder cambiar el estilo a los botones
        ventana.getStylesheets().add("CSS/FB.css");
        ventana.getStylesheets().add("CSS/PP.css");
        ventana.getStylesheets().add("CSS/S.css");
        //Nombre de la ventana
        primaryStage.setTitle("The Java Arcade");
        //Agregar icono
        Image icono = new Image("Img/Icon.png");
        primaryStage.getIcons().add(icono);
        primaryStage.setScene(ventana);
        //Mostrar la ventana
        primaryStage.show();
    }

    //Poder correr el 'popup' de la ventana
    public static void main(String[] args) {
        launch(args);
    }
}
