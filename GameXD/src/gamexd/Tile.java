/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamexd;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jorge Julian Sanchez
 */
public class Tile extends Rectangle{
    
    public Tile(int x, int y){
       setWidth(GameXD.Tile_size);
       setHeight(GameXD.Tile_size);
       
       
       setFill(Color.ALICEBLUE);
       setStroke(Color.BLACK);
       
        
    }
    
        
}
