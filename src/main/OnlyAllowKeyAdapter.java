/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OnlyAllowKeyAdapter extends KeyAdapter{
    String allowed;
    public OnlyAllowKeyAdapter(DataSystemEncoding allowed){
        this.allowed = allowed.chars;
    }
    @Override
    public void keyTyped(KeyEvent ke) {
        char typedKey = ke.getKeyChar();
        if(!(
                typedKey==' ' || this.allowed.indexOf(typedKey) != -1)
            ){
            ke.consume();
            return;
        }
    }
}
