/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class GetExcel {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Gson gson = new Gson();
    private Connection conn;

    /**
     * 此类用于自动生成excel
     */
    public GetExcel() throws ClassNotFoundException, SQLException {

        System.out.println("----------创建一个sql连接！！！----------");
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://192.168.1.252:3306/suidaobig?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8&allowMultiQueries=true", "root", "123asd123asd");
        conn.setAutoCommit(false);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("----------释放sql连接！！！----------");
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(GetExcel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void getExcel() {
        //创建excel
        HSSFWorkbook workbook = new HSSFWorkbook();

        //设置excel的格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平
        cellStyle.setWrapText(true);    //自动换行

        //创建一个工作簿
        HSSFSheet sheet = workbook.createSheet("问题爬虫");
        sheet.setDefaultRowHeightInPoints(25);   //全表默认行高25
        sheet.setDefaultColumnWidth(15);  //全表默认列宽15
        sheet.setColumnWidth(0, 20000);  //第一列列宽77
        sheet.setColumnWidth(2, 5000);
        sheet.setColumnWidth(4, 25000);

        //创建第一行
        setFirstRow(sheet, cellStyle);

        //为excel填充内容
        setValues(sheet, cellStyle);

        //输出为文件
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test.xls");
//            fileOut = new FileOutputStream("/home/suitang01/zhongjiaoexcel/" + sdf.format(new Date()) + ".xls");
            workbook.write(fileOut);
            //fileOut.close();  
            System.out.print("已生成excel文件");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block  
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 为excel填充内容
     *
     * @param sheet 工作簿
     * @param cellStyle excel格式风格
     *
     */
    public void setValues(HSSFSheet sheet, HSSFCellStyle cellStyle) {
        List<HashMap<String, String>> list = getDatas();

        for (int i = 0; i < list.size(); i++) {
            HashMap<String, String> map = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(map.get("author"));
            cell.setCellStyle(cellStyle);
            cell = row.createCell(1);
            cell.setCellValue(map.get("url"));
            cell.setCellStyle(cellStyle);
            cell = row.createCell(2);
        }
    }

    /**
     * 获取连续三天爬虫数量为0的数据
     *
     * @param
     * @return
     */
    public List<HashMap<String, String>> getDatas() {
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
//        Date date = new Date();
//        c.setTime(date);
//        c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
//        String time = sdf.format(c.getTime());

        try {
            PreparedStatement pstmt = null;
            String sql = null;

            sql = "SELECT author,url FROM `stang_bid_day_worning` WHERE day_1_count=0 AND day_2_count=0 AND day_3_count=0";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                HashMap<String, String> tmap = new HashMap<String, String>();
                String author = rs.getString("author");
                String url = rs.getString("url");
                tmap.put("author", author);
                tmap.put("url", url);
                list.add(tmap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * 创建表头
     *
     * @param sheet 工作簿
     * @param cellStyle excel的格式风格
     */
    public void setFirstRow(HSSFSheet sheet, HSSFCellStyle cellStyle) {
        HSSFRow firstrow = sheet.createRow(0);
        HSSFCell cell = firstrow.createCell(0);
        cell.setCellValue("网站名字");
        cell.setCellStyle(cellStyle);
        cell = firstrow.createCell(1);
        cell.setCellValue("网站链接");
    }

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        GetExcel c = new GetExcel();
        c.getExcel();
    }
}
