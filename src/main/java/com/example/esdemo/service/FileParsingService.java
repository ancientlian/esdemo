package com.example.esdemo.service;

import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import com.example.esdemo.entity.ItemValue;
import com.example.esdemo.entity.ModuleTestData;
import com.example.esdemo.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class FileParsingService {


    @Autowired
    ModuleRepository moduleRepository;

    public void fun() {
        String zipOutPath = "C:\\Users\\Lian\\Desktop\\test\\";
        List<File> fileList = getFiles(zipOutPath);
        System.out.println(fileList);
        try {
            for (File file : fileList) {
                contextLoads(file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 循环读取文件
     */
    private static List<File> getFiles(String path) {
        File root = new File(path);
        List<File> files = new ArrayList<>();
        if (!root.isDirectory()) {
            files.add(root);
        } else {
            File[] subFiles = root.listFiles();
            assert subFiles != null;
            for (File f : subFiles) {
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

    public void contextLoads(File file) {

        CsvReader csvReader = new CsvReader();
        CsvData read = csvReader.read(file);
        List<CsvRow> rows = read.getRows();

        List<List<String>> allCsvData = new ArrayList<>();
        for (CsvRow row : rows) {
            allCsvData.add(row.getRawList());
        }

        //分批次存list
        List<List<ModuleTestData>> lists = new ArrayList<>();
        int perSize = allCsvData.size() / 100;
        for (int i = 0; i < perSize + 1; i++) {
            lists.add(new ArrayList<>());
        }

        //读取行数据
        for (int i = 6; i < allCsvData.size(); i++) {
            String tpId = allCsvData.get(i).get(0);
            String testTime = allCsvData.get(i).get(1);
            String sbin = allCsvData.get(i).get(2);
            String passFail = allCsvData.get(i).get(3);
            String barcode = allCsvData.get(i).get(4);
            String site = allCsvData.get(i).get(5);
            String firstFail = allCsvData.get(i).get(6);

            LinkedHashMap<String, ItemValue> itemValueHashMap = new LinkedHashMap<>();
            ModuleTestData moduleTestData = new ModuleTestData(
                    null,
                    tpId,
                    testTime,
                    sbin,
                    passFail,
                    barcode,
                    site,
                    firstFail,
                    null
            );
            for (int j = 7; j < allCsvData.get(0).size() - 1; j++) {
                ItemValue itemValues = creatTestItemValue(allCsvData, i, j);
                for (int k = 0; k < 100; k++) {
                    itemValueHashMap.put(itemValues.getTestDesp() + "" + k, itemValues);
                }
            }
            moduleTestData.setItemValue(itemValueHashMap);
            for (int n = 0; n < 500; n++) {
                lists.get(i / 100).add(moduleTestData);
            }
        }


        for (List<ModuleTestData> list : lists) {
            moduleRepository.saveAll(list);
        }

    }

    private ItemValue creatTestItemValue(List<List<String>> allCsvData, int row, int column) {
        return new ItemValue(
                allCsvData.get(1).get(column),
                allCsvData.get(2).get(column),
                allCsvData.get(3).get(column),
                allCsvData.get(4).get(column),
                allCsvData.get(5).get(column),
                allCsvData.get(row).get(column),
                false);
    }

}
