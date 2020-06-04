package com.util.io;

import java.io.File;

public class MavenCleaner {
    private static int emptyDirectoryNum = 0;
    private static int Directorywithoutjar = 0;
    private static int lastUpdateNum = 0;
    private int emptyNum = 0;

    /**
     * 要清理的内容包括
     * 1. 所有的.lastUpdated 结尾的文件
     */
    public static void main(String[] args) {
        final String mavenRepositoryPath = "C:\\Users\\HUALINDAI\\.m2\\repository";
        File f = new File(mavenRepositoryPath);
        MavenCleaner clean = new MavenCleaner(); //while(clean.emptyNum!=0&&clean.emptyNum!=-1) {
        // 这里一次执行清理不完 多次执行直到打印出0
        clean.cleanLastUpdate(f);
        clean.cleanEmptyDirectory(f);
        System.out.println("删除文件 " + lastUpdateNum + "个, 删除空文件夹 " + emptyDirectoryNum + "个, 删除没有jar文件的文件夹 " + Directorywithoutjar + "个");
        //}
    }

    /**
     * 删除所有.lastUpdated结尾的文件
     *
     * @param f
     */
    public void cleanLastUpdate(File f) {
        if (f != null) {
            if (f.getName().endsWith("lastUpdated")) {
                lastUpdateNum++;
                System.out.println("要删除的文件是：" + f.getAbsolutePath());
                f.delete();
            }
            if (f.isDirectory()) {
                File[] fs = f.listFiles();
                for (File fItem : fs) {
                    cleanLastUpdate(fItem);
                }
            }
        }
    }

    /**
     * 删除所有的空文件夹
     *
     * @param f
     */
    public int cleanEmptyDirectory(File f) {
        if (f != null && f.isDirectory()) {
            File[] fs = f.listFiles();
            if (fs.length == 0) {
                emptyDirectoryNum++;
                emptyNum++;
                System.out.println(f.getAbsolutePath() + " 是空文件夹被删除");
                f.delete();
            } else {
                int dir_num = 0;
                int jar_file = 0;
                for (File fc : fs) {
                    if (fc != null && fc.isDirectory()) {
                        dir_num++;
                        cleanEmptyDirectory(fc);
                    }
                    if(fc.getName().endsWith(".jar"))
                    {
                        jar_file++;
                    }
                }
                if (dir_num == 0 && jar_file == 0) {
                    System.out.println(f.getAbsolutePath() + " 未含有文件夹或者jar文件 被删除");
                    Directorywithoutjar++;
                    deleteDirectory(f);
                }
            }
        }
        return emptyNum;
    }

    public static boolean deleteDirectory(File dirFile) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
//        if (!dir.endsWith(File.separator))
//            dir = dir + File.separator;
//        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
//        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
//            System.out.println("删除目录失败：" + dir + "不存在！");
//            return false;
//        }
        String dir=dirFile.getAbsolutePath();
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = MavenCleaner.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = MavenCleaner.deleteDirectory(files[i]);
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

}