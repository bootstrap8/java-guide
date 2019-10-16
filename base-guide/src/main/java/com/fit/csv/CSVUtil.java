package com.fit.csv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-16
 */
public class CSVUtil {

    public static char separator = ',';

    public static void main(String[] args) throws Exception {
        // 测试导出
        String filePath = "F:/22.csv";
//        List<String[]> dataList = new ArrayList<String[]>();
//        //添加标题
//        dataList.add(new String[]{"学号", "姓名", "分数"});
//        for (int i = 0; i < 10; i++) {
//            dataList.add(new String[]{"2010000" + i, "张三" + i, "8" + i});
//        }
//        createCSV(dataList, filePath);

        // 读取CSV文件
        List<String> lines = readCSV(filePath);
        System.out.println("size->" + lines.size());
        String[] titles = lines.get(0).split(",");
        String[] contents = lines.get(1).split(",");

        for (int i = 0; i < titles.length; i++) {
            System.out.println(titles[i].toLowerCase() + ":" + contents[i]);
        }


//        for(String[] line : lines){
//            System.out.println(Arrays.asList(line));
//        }

    }

    /**
     * 读取CSV文件
     *
     * @param filePath:全路径名
     */
    public static List<String> readCSV(String filePath) throws Exception {
        CsvReader reader = null;
        List<String> dataList = new ArrayList<String>();
        try {
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            reader = new CsvReader(filePath, separator, Charset.forName("GBK"));

            // 读取表头
//            reader.readHeaders();
//            String[] headArray = reader.getHeaders();//获取标题
//            System.out.println(headArray[0] + headArray[1] + headArray[2]);

            // 逐条读取记录，直至读完
            String content;
            while (reader.readRecord()) {
                // 读一整行
                content = reader.getRawRecord();
                System.out.println(content);
                System.out.println("-------------------------------");
                // 读这行的第一列
                // System.out.println(reader.get("学号"));
                // 读这行的第二列
                //System.out.println(reader.get(1));
                dataList.add(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        return dataList;
    }

    /**
     * 生成CSV文件
     *
     * @param dataList:数据集
     * @param filePath:全路径名
     */
    public static boolean createCSV(List<String[]> dataList, String filePath) throws Exception {
        boolean isSuccess = false;
        CsvWriter writer = null;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath, true);
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            writer = new CsvWriter(out, separator, Charset.forName("GBK"));
            for (String[] strs : dataList) {
                writer.writeRecord(strs);
            }
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSuccess;
    }

}
