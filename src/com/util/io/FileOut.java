package com.util.io;

import java.io.*;

public class FileOut {

    public DataOutputStream getOutFile(String fileName) {
        DataOutputStream dos;
        try {
            fdel.checkdeleteFile(fileName);
            File f = new File(fileName);
            f.createNewFile();
            dos = new DataOutputStream(new FileOutputStream(f));
        } catch (IOException ex) {
            return (null);
        }
        return dos;
    }

    public boolean writeToFile(DataOutputStream dwr, String outline) throws IOException {
        dwr.writeBytes(outline);
        this.PrintEnter(dwr);
        return true;
    }

    public boolean writeFile(DataOutputStream dwr, String outline) throws IOException {
        dwr.writeBytes(outline);
        return true;
    }

    public boolean writeFileW(OutputStreamWriter dwr, String outline) throws IOException {
        dwr.write(outline);
        return true;
    }

    public boolean writeToFileW(OutputStreamWriter dwr, String outline) throws IOException {
        dwr.write(outline);
        dwr.write('\r');
        dwr.write('\n');
        return true;
    }

    public boolean writeToFileDouble(DataOutputStream dwr, String outline) throws IOException {
        dwr.writeUTF(outline);
        this.PrintEnter(dwr);
        return true;
    }

    public void PrintEnter(DataOutputStream wrr) {
        try {
            wrr.writeByte('\r');
            wrr.writeByte('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void outputtoFile(String[] onebean, DataOutputStream doc) {
        try {
            // fo.writeToFile(doc1, mem);
            writeFile(doc, onebean[2] + "  ");
            writeToFileDouble(doc, onebean[1]);
            // fo.PrintEnter(doc);
            // fo.writeToFile(doc, new String(mem.getBytes(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    FileDel fdel = new FileDel();
}
