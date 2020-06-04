package com.util.io;

import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

public class FileIn {
    File inFile;
    int error_rerun_flag = 0;
    String filename;

    String url;

    InputStreamReader inbr;
    URL u;
    InputStream in;

    public BufferedReader getBufferedReaderFromURL(URL url) throws Exception {
        try {

            URLConnection uc = url.openConnection();
            u = url;
            uc.setConnectTimeout(10000);
            uc.setReadTimeout(25000);


            InputStream in = uc.getInputStream();
            for (int i = 0; i < 10; i++) {
                if (in == null) {
                    Thread.sleep(1000);
                    in = uc.getInputStream();
                    System.out.println("Timeout times" + i);
                } else
                    break;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(in, "GBK"));
            //@@@
            // network problem, loop 10 times and out
            for (int i = 0; i < 10; i++) {
                if (br == null) {
                    Thread.sleep(1000);
                    br = new BufferedReader(new InputStreamReader(in, "GBK"));
                    System.out.println("Timeout times" + i);
                } else
                    break;
            }
            if (br == null)
                throw new TimeOutException();

            error_rerun_flag = 0;
            return br;
            // filename = infile;
        } catch (SocketTimeoutException ioe) {
            error_rerun_flag++;
            if (error_rerun_flag < 10) {
                System.out.println(url);
                System.out.println("Happen SocketTimeoutException: rerun " + error_rerun_flag);
                return this.doAgain(url);
            } else {
                ioe.printStackTrace();
                System.out.println("Scrapy Error - s002: BufferReader error");
                return null;
            }
        }
    }

    private BufferedReader doAgain(URL url) throws Exception {
        return getBufferedReaderFromURL(url);
    }

    public BufferedReader getBufferedReader(String _infile) {
        try {
            BufferedReader br = null;
            // System.out.println(inFile.getPath());
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(_infile))));
            return br;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
