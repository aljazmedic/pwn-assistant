/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Dijak
 */
public class Main {
    public static Obrazec form;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        form = new Obrazec();
        form.setVisible(true);
        form.requestFocus();
    }
}
