/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamexd;

import java.io.FileInputStream;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Jorge Julian Sanchez
 */
public class GameXD extends Application {
     
               
       private int prefHeight;
       private int prefWidth;
       
       //
       public int rand;
       public Label randResult;
       // posicion del circulo que tendra el jugador
       public int CirclePos[][] = new int [10][10];
       public int leadderPosicion [][] = new int [6][3];
        int radius;
        
       // tamaño de mi cuadro de la aplicacion en este caso 10*10
       public static final int Tile_size = 80;
       public static final int width = 10;
       public static final int height = 10; 
       
       public Circle jugador1;
       public Circle jugador2;
       // Donde inician los jugadores en el juego
       public int jugadorPosicion = 1;
       public int jugador2Posicion = 1;
       
       // Donde daremos los turnos de los jugadores
       
       public boolean jugador1turno = true;
       public boolean jugador2turno = true;
       
       //
       public static int jugador1XPosicion = 40;
       public static int jugador1YPosicion = 760;
       
       public static int jugador2XPosicion = 40;
       public static int jugador2YPosicion = 760;
       //
       
       public boolean juegoInicio = false;
       public Button botoniniciar;
       
       
       String text;
       
       public int posicionCirculo1 = 1;
       public int posicionCirculo2 = 1;
       
      
       private Group tileGroup = new Group();
       
    
    private Parent createConetent (){
        Pane root = new Pane();
        root.setPrefSize(prefWidth= width * Tile_size, prefHeight=(height * Tile_size)+80);
        root.getChildren().add(tileGroup);
        
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                Tile tile = new Tile (Tile_size,Tile_size);
                tile.setTranslateX(j * Tile_size);
                tile.setTranslateY(i * Tile_size);
                tileGroup.getChildren().add(tile);
            }
        }
          
        
        // posicion de los cirulos que ontrolara a donde se va dirigir el circulo
        jugador1 = new Circle(radius = 40);
        jugador1.setId("Jugador1");
        jugador1.setFill(Color.AQUA);
        jugador1.getStyleClass().add("gamexd/style.css");
        jugador1.setTranslateX(jugador1XPosicion);
        jugador1.setTranslateY(jugador1YPosicion);
        
        jugador2 = new Circle(radius = 40);
        jugador2.setId("Jugador2");
        jugador2.setFill(Color.CHOCOLATE);
        jugador2.getStyleClass().add("gamexd/style.css");
        jugador2.setTranslateX(jugador2XPosicion);
        jugador2.setTranslateY(jugador2YPosicion);
        
        
        // Aca hago los botones para el jugador 1 y 2
        Button boton2jugador2 = new Button(text = "Jugador 2");
        boton2jugador2.setTranslateX(700);
        boton2jugador2.setTranslateY(820);
        boton2jugador2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (juegoInicio){
                    if(jugador1turno){
                        getValor();//obtiene el valor del obtenido del ramdom
                        randResult.setText(String.valueOf(rand));
                        moverJugador2();
                        trasladarJugador(jugador2XPosicion,jugador2YPosicion, jugador2);
                        jugador2turno = false;
                        jugador1turno = true;
                        jugador2Posicion+=rand;
                    }
                }
            }
        });
        // Aca el botono de iniciar la partida
        Button boton1jugador1 = new Button(text = "Jugador 1");
        boton1jugador1.setTranslateX(80);
        boton1jugador1.setTranslateY(820);
        boton1jugador1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (juegoInicio){
                    if(jugador2turno){
                        getValor();
                        randResult.setText(String.valueOf(rand));
                        moverJugador1();
                        trasladarJugador(jugador1XPosicion,jugador1YPosicion,jugador1);
                        jugador1turno=false;
                        jugador2turno=true;  // el jugador1 se puasa y el jugador 2 entra al ataque jejxd
                        
                        jugadorPosicion+=rand;
                    }
                }
            }
        });
        // Al presion el boton pues ya los jugadores se posicionan
        Button botoniniciar = new Button (text = "Juego inicio");
        botoniniciar.setTranslateX(380);
        botoniniciar.setTranslateY(820);
        botoniniciar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             botoniniciar.setText("Juego inciado");
             jugador1XPosicion=40;
             jugador1YPosicion=760;
             
             
             jugador2XPosicion=40;
             jugador2YPosicion=760;
             
             jugador1.setTranslateX(jugador1XPosicion);
             jugador1.setTranslateY(jugador1YPosicion);
             
             jugador2.setTranslateX(jugador2XPosicion);
             jugador2.setTranslateY(jugador2YPosicion);
             juegoInicio = true;
             
            }
        });
        
        randResult = new Label (text="0");
        randResult.setTranslateX(300);
        randResult.setTranslateY(800);
        // Foto de fondo del juego 
        Image image = new Image ("gamexd/fondodeljuegoxd.jpeg");
        //Configuraciones del tamaño
        ImageView verimagen = new ImageView();
        verimagen.setImage(image);
        verimagen.setFitHeight(800);
        verimagen.setFitWidth(800);
        
        tileGroup.getChildren().addAll(verimagen,jugador1,jugador2,boton2jugador2,boton1jugador1,botoniniciar, randResult);
        return root;
    }
    
    private void moverJugador1(){
        for (int i=0; i<rand; i++){
            if(posicionCirculo1 % 2 ==1){
                jugador1XPosicion+=80;
                
            }
            if(posicionCirculo1 %2 ==0){
                jugador1XPosicion-=80;
            }
            if(jugador1XPosicion >760){
                jugador1YPosicion-=80;
                jugador1XPosicion-=80;
                posicionCirculo1++;
                
            }
            if (jugador1XPosicion <40){
                jugador1YPosicion-=80;
                jugador1XPosicion+=80;
                posicionCirculo1++;
            }
            if(jugador1XPosicion <30 || jugador1YPosicion <30){
                jugador1YPosicion=40;
                jugador1XPosicion=40;
                juegoInicio= false;
                randResult.setText("El jugador 1 gano");
                botoniniciar.setText("Jugar de nuevo");
                posicionCirculo1++;
            }
            
            
            
        }
    }
    private void moverJugador2(){
        for (int i=0; i<rand; i++){
            if(posicionCirculo2 % 2 ==1){
                jugador2XPosicion+=80;
                
            }
            if(posicionCirculo2 %2 ==0){
                jugador2XPosicion-=80;
            }
            if(jugador2XPosicion >760){
                jugador2YPosicion-=80;
                jugador2XPosicion-=80;
                posicionCirculo2++;
                
            }
            if (jugador2XPosicion <40){
                jugador2YPosicion-=80;
                jugador2XPosicion+=80;
                posicionCirculo2++;
            }
            if(jugador2XPosicion <30 || jugador2YPosicion <30){
                jugador2YPosicion=40;
                jugador2XPosicion=40;
                juegoInicio= false;
                randResult.setText("El jugador 2 gano");
                botoniniciar.setText("Jugar de nuevo");
                
            }
            
            
            
        }
    }
       
    public void getValor(){
        rand = (int)(Math.random()*6+1);
    }
    
    
    public void trasladarJugador(int x,int y, Circle b){
        TranslateTransition animate = new TranslateTransition (Duration.millis(1000),b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }
    
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       Scene scene = new Scene(createConetent());   
        scene.getStylesheets().add("gamexd/style.css");   
        primaryStage.setTitle("Juegito!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

  

  
    
}
