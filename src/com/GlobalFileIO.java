package com;

import com.util.io.FileOut;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class GlobalFileIO {

    public static List<String> read(String inputfile) {
        List<String> datalist = new ArrayList<String>();
        File f = new File(inputfile);
        FileReader fr = null;
        try {
            fr = new FileReader(f.getPath());
            LineNumberReader lnr = new LineNumberReader(fr);
            String one = lnr.readLine();
            while (one != null) {
                datalist.add(one);
                one = lnr.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return datalist;
    }
}
