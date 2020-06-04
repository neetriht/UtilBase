package com.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: neetriht
 * Date: 2018-10-31
 * Time: 08:40
 * <p>
 * Description:
 */
public class FileNIOUtil {

    public void outputdata(String filename, String mesg) throws IOException {
        File file = new File(filename);
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel channel = outputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //String string = words;
        buffer.put(mesg.getBytes());

//        for (int i=0; i<mesg.length; ++i) {
//            buffer.put( mesg[i] );
//        }


        buffer.flip();     //此处必须要调用buffer的flip方法

        channel.write(buffer);
        channel.close();
        outputStream.close();
    }

    public void readedata(String inputfile) throws IOException {
        FileInputStream fin = new FileInputStream(inputfile);

        // 获取通道
        FileChannel fc = fin.getChannel();

        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据到缓冲区
        fc.read(buffer);

        buffer.flip();

        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.print(((char) b));
        }

        fin.close();
    }

    public void CopyFile(String oldfile, String newfile) throws IOException {
        String infile = oldfile;
        String outfile = newfile;
        // 获取源文件和目标文件的输入输出流
        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);
        // 获取输入输出通道
        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            // clear方法重设缓冲区，使它可以接受读入的数据
            buffer.clear();
            // 从输入通道中将数据读到缓冲区
            int r = fcin.read(buffer);
            // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
            if (r == -1) {
                break;
            }
            // flip方法让缓冲区可以将新读入的数据写入另一个通道
            buffer.flip();
            // 从输出通道中将数据写入缓冲区
            fcout.write(buffer);
        }
    }
}
