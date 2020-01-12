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
public enum DataSystemEncoding {

    ASCII(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~",
            false, 0, "", "",  true),
    DEC("0123456789",
            true, 0, "", "0", true),
    BIN("01b",
            true, 8, "0b", "0", true),
    OCT("01234567o",
            true, 3, "0o", "0", true),
    HEX("0123456789abcdefABCDEFx",
            true, 2, "0x", "0",  true),
    CUS("0123456789abcdefABCDEF",
            true,0,"","0", true), //Custom dropdown system
    BASE64("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789/+=",
            true, 4, "", "=", false);

    public String chars;
    public boolean ignoreWhitespace;
    public int groupBy;
    public boolean hasPrefix, forwardPad;
    public String prefix, pad;

    private DataSystemEncoding(String value, boolean ignoreWhitespace, int groupBy, String prefix, String pad, boolean forwardPad) {
        //Napravi enum s predefiniranimi lastnostmi
        this.chars = value;
        this.ignoreWhitespace = ignoreWhitespace;
        this.groupBy = groupBy;
        this.prefix = prefix;
        this.pad = pad;
        this.forwardPad = forwardPad;
        this.hasPrefix = prefix.length() != 0;
    }
    public String formatText(String txtIn, String delimiter) {
        // Dodatna funkcija, ki dopušča rabo brez nekaterih argumentov
        return formatText(txtIn, delimiter, false, "");
    }

    public String formatText(String txtIn, String delimiter, boolean usePrefix, String prefix) {
        //txtIn = parseText(txtIn);
        String r = "";
        if (hasPrefix && usePrefix) {
            r += prefix;
        }
        //remove spaces asap
        if (this.ignoreWhitespace) {
            txtIn = txtIn.replaceAll("[\t \n\r]+", "");
        }
        if (this.groupBy == 0 || !this.ignoreWhitespace) {
            return r + txtIn;
        }
        //split into n-uples, join by space (and prefix)
        r += txtIn.substring(0, Math.min(groupBy, txtIn.length()));
        for (int i = 1; i < (txtIn.length() +1) / this.groupBy; i++) {
            r += delimiter;
            if (hasPrefix && usePrefix) {
                r += prefix;
            }
            r += extractBetween(txtIn, i * groupBy, (i + 1) * groupBy);
        }
        return r;
    }
    
    private String extractBetween(String s, int startIndex, int lastIndex) {
        //Nothing special
        if (lastIndex <= s.length()) {
            return s.substring(startIndex, lastIndex);
        }
        //Padding required
        String _pad = "";
        while (lastIndex > s.length()) {
            _pad += this.pad;//pad inbetween prefix and actual value
            lastIndex--;
        }
        if (this.forwardPad)//eg ---123
        {
            return _pad + s.substring(startIndex, s.length());
        } else //eg 123---
        {
            return s.substring(startIndex, s.length()) + _pad;
        }
    }
}
