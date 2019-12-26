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
public class BinSystemPanel extends JUnitConverterPanel {

    public BinSystemPanel() {
        super(DataSystemEncoding.BIN, "BIN");
    }
    
    
    @Override
    public void interpretStringTo() {
        //split then reduce
        String s = this.textArea.getText();
        if(s.length()==0)
            Main.form.converterPanel1.updateAllExcept((JUnitConverterPanel) this, new int[0]);
        s = s.toLowerCase().replace(this.dataSystemEncoding.prefix, " "+this.dataSystemEncoding.prefix); //Get rid of all prefixes
        s = s.toLowerCase().replace(this.dataSystemEncoding.prefix, ""); //Get rid of all prefixes
        List<String> matches = Util.matchAllRegex(s, "(?:0[bB])?([0-1]{1,8})");
        List<Integer> values = new ArrayList<>();
        for (Iterator<String> it = matches.iterator(); it.hasNext();) {
            String thisMatch = it.next();
            int i = 0;
            while(i < thisMatch.length()){
                String built = "";
                do{
                    built += thisMatch.charAt(i);
                    i++;
                }while(i < thisMatch.length() && Integer.parseInt(built, 2) <= 128);
                values.add(Integer.parseInt(built, 2));
            }
        }
        
        int[] groups = new int[values.size()];
        for(int idx = 0; idx <values.size(); idx++){
            groups[idx] = (int) values.get(idx);
        }
        Main.form.converterPanel1.updateAllExcept((JUnitConverterPanel) this, groups);
    }

    @Override
    public void formatFromData() {
        String s = String.format("%8s", Integer.toBinaryString(Main.form.converterPanel1.converterValues[0])).replace(' ', '0');
        for(int i = 1; i < Main.form.converterPanel1.converterValues.length; i++){
            s+=" "+String.format("%8s", Integer.toBinaryString(Main.form.converterPanel1.converterValues[i])).replace(' ', '0');
        }
        s = this.dataSystemEncoding.formatText(s,
                (String)Main.form.converterPanel1.getDelimiter(),
                this.checkBoxUePrefix.isSelected(),
                prefixEntry.getText());this.textArea.setText(s);
    }
    
}

