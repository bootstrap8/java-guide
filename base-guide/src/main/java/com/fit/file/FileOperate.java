package com.fit.file;

import java.io.*;
import java.util.TreeSet;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2020-04-13
 */
public class FileOperate {

    private static String path = System.getProperty("user.dir") + File.separator + "json.txt";

    private static void readFile1(File fin) throws IOException {
        FileInputStream fis = new FileInputStream(fin);

        //Construct BufferedReader from InputStreamReader
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
        fis.close();
    }


    private static void readFile2(File fin) throws IOException {
        // Construct BufferedReader from FileReader
        BufferedReader br = new BufferedReader(new FileReader(fin));

        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }

    /**
     * 覆写文件内容
     *
     * @param fileName
     * @param clist
     * @throws IOException
     */
    public void fileWriter(String fileName, TreeSet<String> clist) throws IOException {        //创建一个FileWriter对象
        FileWriter fw = new FileWriter(fileName);        //遍历clist集合写入到fileName中
        for (String str : clist) {
            fw.write(str);
            fw.write("\n");
        }        //刷新缓冲区
        fw.flush();        //关闭文件流对象
        fw.close();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(path);
        FileWriter fw = new FileWriter(path);
        for (String str : "456,abc,def,123".split(",")) {
            fw.write(str);
            fw.write("\n");
        }
        fw.flush();
        fw.close();
    }

}
