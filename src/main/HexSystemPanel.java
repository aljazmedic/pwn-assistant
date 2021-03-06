/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author HP Omen
 */
public class HexSystemPanel extends UnitConverterPanel {

    public HexSystemPanel() {
        super(DataSystemEncoding.HEX, "HEX");
    }
    
    
    @Override
    public void interpretStringTo() {
        //split then reduce
        String s = this.textArea.getText();
        if(s.length()==0){
            Main.form.converterPanel1.updateAllExcept((UnitConverterPanel) this, new int[0]);
        }
        s = s.toLowerCase();
        s = s.replace(this.dataSystemEncoding.prefix, " "); //Znebi se vseh 
        List<String> matches = Util.matchAllRegex(s, "(?:0[xX])?([0-9a-fA-F]{1,2})");
        List<Integer> values = new ArrayList<>();
        for (Iterator<String> it = matches.iterator(); it.hasNext();) {
            String thisMatch = it.next();
                values.add(Integer.parseInt(thisMatch, 16));
            int i = 0;
        }
        
        int[] groups = new int[values.size()];
        for(int idx = 0; idx <values.size(); idx++){
            groups[idx] = (int) values.get(idx);
        }
        Main.form.converterPanel1.updateAllExcept((UnitConverterPanel) this, groups);
    }

    @Override
    public void formatFromData() {
        String s = String.format(" %02x", Main.form.converterPanel1.converterValues[0]);
        for(int i = 1; i < Main.form.converterPanel1.converterValues.length; i++){
            s+=String.format(" %02x", Main.form.converterPanel1.converterValues[i]);
        }
        s = this.dataSystemEncoding.formatText(s,
                (String)Main.form.converterPanel1.getDelimiter(),
                this.checkBoxUePrefix.isSelected(),
                prefixEntry.getText());
        this.textArea.setText(s);
    }
    
}
