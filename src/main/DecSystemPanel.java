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
public class DecSystemPanel extends UnitConverterPanel {

    public DecSystemPanel() {
        super(DataSystemEncoding.DEC, "DEC");
    }

    @Override
    public void interpretStringTo() {
        //split then reduce
        String s = this.textArea.getText();
        if (s.length() == 0) {
            Main.form.converterPanel1.updateAllExcept((UnitConverterPanel) this, new int[0]);
        }
        s = s.toLowerCase().replace(this.dataSystemEncoding.prefix, ""); //Get rid of all prefixes
        List<String> matches = Util.matchAllRegex(s, "([0-9]{1,3})");
        List<Integer> values = new ArrayList<>();
        String carry ="";
        for (Iterator<String> it = matches.iterator(); it.hasNext();) {
            String thisMatch = it.next();
            if(Integer.parseInt(thisMatch) <= 255){
                values.add(Integer.parseInt(thisMatch));
            }else{
                values.add(Integer.parseInt(thisMatch.substring(0, 2)));
                values.add(Integer.parseInt(thisMatch.substring(2, 3)));
            }
        }

        int[] groups = new int[values.size()];
        for (int idx = 0; idx < values.size(); idx++) {
            groups[idx] = (int) values.get(idx);
        }
        Main.form.converterPanel1.updateAllExcept((UnitConverterPanel) this, groups);
    }

    @Override
    public void formatFromData() {
        String s = "" + Main.form.converterPanel1.converterValues[0];
        for (int i = 1; i < Main.form.converterPanel1.converterValues.length; i++) {
            s += " "+Main.form.converterPanel1.converterValues[i];
        }
        //s = this.dataSystemEncoding.formatText(s, (String)Main.form.converterPanel1.getDelimiter());
        this.textArea.setText(s);
    }
}
