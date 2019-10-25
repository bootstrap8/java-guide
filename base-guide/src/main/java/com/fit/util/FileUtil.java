package com.fit.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-10-24
 */
@Slf4j
public class FileUtil {

    public static String CHARSET = "UTF-8";

    public static void write2File(String txt, File file) {
        try {
            IOUtils.write(txt, new FileOutputStream(file), CHARSET);
        } catch (IOException e) {
            log.error("write {} to {} error:", txt, file.getName(), e);
        }
    }

    public static List<String> readLines(File file) {
        try {
            return IOUtils.readLines(new FileInputStream(file), CHARSET);
        } catch (IOException e) {
            log.error("read line from {} error:", file.getName(), e);
        }
        return Lists.newArrayList();
    }

}
