/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author HP Omen
 */
public class AsciiSystemPanel extends UnitConverterPanel {

    public AsciiSystemPanel() {
        super(DataSystemEncoding.ASCII, "ASCII");
    }

    @Override
    public void interpretStringTo() {
        String s = this.textArea.getText();
        int[] groups = new int[s.length()];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = (int)s.charAt(i);
        }
        Main.form.converterPanel1.updateAllExcept((UnitConverterPanel) this, groups);
    }

    @Override
    public void formatFromData() {
        String built = "";
        for(int i : Main.form.converterPanel1.converterValues){
            built+= ((char)i);
        }
        this.textArea.setText(built);
    }
}
