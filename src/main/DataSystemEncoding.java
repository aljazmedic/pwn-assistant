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
        this.chars = value;
        this.ignoreWhitespace = ignoreWhitespace;
        this.groupBy = groupBy;
        this.prefix = prefix;
        this.pad = pad;
        this.forwardPad = forwardPad;
        assert !chars.contains(this.pad) : "Pad in allowed characters!"; //just to be sure
        assert !chars.contains(this.prefix) : "Prefix in allowed characters!";
        hasPrefix = prefix.length() != 0;
    }
    public String formatText(String txtIn, String delimiter) {
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
    /* public String formatText(int[] txtIn, boolean usePrefix) {
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
        for (int i = 1; i < (txtIn.length() - 1) / this.groupBy + 1; i++) {
            r += this.delimiter;
            if (hasPrefix && usePrefix) {
                r += prefix;
            }
            r += extractBetween(txtIn, i * groupBy, (i + 1) * groupBy);
        }
        return r;
    }*/
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

    /*public String[] parseText(String txtIn) {
        System.out.println(txtIn);
        if(hasPrefix){
            txtIn = txtIn.replaceAll(prefix, "");
        }
        System.out.println(txtIn); //TODO Regex match by whitespace
        if (this.ignoreWhitespace) {
            String[] parts = txtIn.split("[\t \n\r]+");
        }
        return txtIn;
    }*/

    public static void main(String[] args) {
        String[] tests = new String[]{
            "YW5     5IGNhc  m5hbCBwbGVhc3VyZQ",
            "2320b1230brewfrgdrte as daas asd gthgdfsfghz4terhgdb",
            "21e3wrf    w efdd  qewdfdsarewgfdfgdsg"
        };
        DataSystemEncoding dse = DataSystemEncoding.OCT;
    }
}
