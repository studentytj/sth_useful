package com.test.sth_useful.test;

import com.test.sth_useful.common.util.ExcelUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * @date 2018/12/19
 */
public class ExecuteExcelUtil {
    public static void main(String[] args) throws Exception {
        String property = System.getProperty("user.dir");
        String filePath = property + "/src/main/resources/excel/test.xlsx";
        File file = new File(filePath);
        InputStream ins = new FileInputStream(file);
        List<List<String>> lists = ExcelUtil.readFile(ins,  2);
        System.out.println(lists);
    }
}
