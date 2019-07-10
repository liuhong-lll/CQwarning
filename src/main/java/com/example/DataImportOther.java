/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Administrator
 */
public class DataImportOther {

    private static Connection conn;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DataImportOther.class);

    public DataImportOther() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.252:3306/suidaobig?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8", "root", "123asd123asd");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/suidaobig?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8", "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            logger.error("链接数据库时异常！异常信息：" + e.getMessage());
        }
    }

    public void run() throws SQLException, IOException {
//                更新day_5_count（前5天数量）
        try {
            String sql2 = "SELECT id, author,day_4_count from `stang_bid_day_worning`";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            ResultSet rs2 = pstmt2.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt("id");
                String author = rs2.getString("author");
                int day_4_count = rs2.getInt("day_4_count");
                String sql = "update stang_bid_day_worning set day_5_count = ? where id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, day_4_count);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
                conn.commit();
                System.out.println("day_5_count更新成功+" + author);
            }
            System.out.println("==========================  day_5_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_5_count  更新 发生错误：" + e.toString());
        }
        //        更新day_4_count（前4天数量）
        try {
            String sql2 = "SELECT id, author,day_3_count from `stang_bid_day_worning`";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            ResultSet rs2 = pstmt2.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt("id");
                int day_3_count = rs2.getInt("day_3_count");
                String author = rs2.getString("author");
                String sql = "update stang_bid_day_worning set day_4_count = ? where id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, day_3_count);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
                conn.commit();
                System.out.println("day_4_count更新成功+" + author);
            }
            System.out.println("==========================  day_4_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_4_count  更新 发生错误：" + e.toString());
        }

//        更新day_3_count（前3天数量）
        try {
            String sql2 = "SELECT id, author,day_2_count from `stang_bid_day_worning`";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            ResultSet rs2 = pstmt2.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt("id");
                int day_2_count = rs2.getInt("day_2_count");
                String author = rs2.getString("author");
                String sql = "update stang_bid_day_worning set day_3_count = ? where id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, day_2_count);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
                conn.commit();
                System.out.println("day_3_count更新成功+" + author);
            }
            System.out.println("==========================  day_3_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_3_count  更新 发生错误：" + e.toString());
        }
        //更新day_2_count（前2天数量）
        try {
            String sql1 = "SELECT id, author,day_1_count from `stang_bid_day_worning`";

            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pstmt1.executeQuery();

            while (rs1.next()) {
                int id = rs1.getInt("id");
                String author = rs1.getString("author");
                int day_1_count = rs1.getInt("day_1_count");
                String sql = "update stang_bid_day_worning set day_2_count = ? where id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, day_1_count);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
                System.out.println("day_2_count更新成功+" + author);
                conn.commit();
            }
            System.out.println("==========================  day_2_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_2_count  更新 发生错误：" + e.toString());
        }
        //更新day_1_count（前1天数量）
        try {
            String sql1 = "SELECT id, name,day_1_count,update_time,url  from `stang_bid_worning`";

            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pstmt1.executeQuery();

            while (rs1.next()) {
                int id = rs1.getInt("id");
                String author = rs1.getString("name");
                int day_1_count = rs1.getInt("day_1_count");
                int add_time = rs1.getInt("update_time");
                String url = rs1.getString("url");
                Date date = new Date();
                long time = date.getTime() / 1000;
                time = time - 86400 * 7;//七天前此刻时间戳
                int label = 1;
                if (add_time <= time) {
                    label = 0;//屏蔽七天未更新爬虫
                }
                String sql = "update stang_bid_day_worning set day_1_count = ?,label=? ,url=? where id = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, day_1_count);
                pstmt.setInt(2, label);
                pstmt.setString(3, url);
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
                System.out.println("day_1_count更新成功+" + author);
                conn.commit();
            }
            System.out.println("==========================  day_1_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_1_count  更新 发生错误：" + e.toString());
        }
        //更新新增数据
        try {
            String sql2 = "SELECT id,name,url,day_1_count,project,auditor,update_time FROM stang_bid_worning where id not in (select id from `stang_bid_day_worning`) and project  in('newspider3','cityspider','gitspider','ZZBS_Projects','ZZB_projects')";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            ResultSet rs2 = pstmt2.executeQuery();
            int n = 0;
            Date date = new Date();
            long time = date.getTime() / 1000;
            time = time - 86400 * 3;//3天前此刻时间戳：一天86400秒 
            while (rs2.next()) {
                int id = rs2.getInt("id");
                String author = rs2.getString("name");
                String url = rs2.getString("url");
                String day_1_count = rs2.getString("day_1_count");
                String project = rs2.getString("project");
                String auditor = rs2.getString("auditor");
                int add_time = rs2.getInt("update_time");
                int label = 1;

                if (add_time <= time) {
                    label = 0;
                }
                String sql1 = "INSERT INTO `stang_bid_day_worning`(id,author,url,day_1_count,project,auditor,label)values(?,?,?,?,?,?,?)";
                PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                pstmt1.setInt(1, id);
                pstmt1.setString(2, author);
                pstmt1.setString(3, url);
                pstmt1.setString(4, day_1_count);
                pstmt1.setString(5, project);
                pstmt1.setString(6, auditor);
                pstmt1.setInt(7, label);
                pstmt1.executeUpdate();
                conn.commit();
                n++;
                System.out.println("========================== " + author + " 新数据添加成功================");
            }
            System.out.println("==========================  新增数据  更新成功完成   共计" + n + "条================");
        } catch (Exception e) {
            System.out.println(" 新增数据 发生错误：" + e.toString());
        }
    }
//查询近三天数量均为0的网站

    public List<String> al() {
        PreparedStatement pstmt = null;
        List<String> list = new ArrayList<String>();
        try {
            String sql = "SELECT author FROM `stang_bid_day_worning` WHERE day_1_count=0 AND day_2_count=0 and day_3_count=0 and author   like'%公共资源%' and label = 1 and project !='ZZBS_Projects'";
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String author = rs.getString("author");
//                System.out.println("======================");
                list.add(author);//            System.out.println(al);
            }
        } catch (Exception e) {
            System.out.println("day_3_count  更新成功 发生错误：" + e.toString());
        } finally {
            close(pstmt);
        }
        return list;
    }

    //屏蔽该网站
    public void hideMsg(String message) {
        PreparedStatement pstmt = null;
        try {
            String sql = "update stang_bid_day_worning set label = 0 where author = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, message);
            pstmt.executeUpdate();
            System.out.println("注释网站成功+" + message);
            conn.commit();

        } catch (Exception e) {
            System.out.println("注释网站 发生错误：" + e.toString());
        } finally {
            close(pstmt);
        }
    }

    //显示该网站
    public void showMsg(String message) {
        PreparedStatement pstmt = null;
        try {
            String sql = "update stang_bid_day_worning set label = 1 where author = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, message);
            pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            System.out.println("显示网站 发生错误：" + e.toString());
        } finally {
            close(pstmt);
        }
    }

    public void close(AutoCloseable table) {
        try {
            table.close();
        } catch (Exception ex) {
            Logger.getLogger(DataImportOther.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        DataImportOther di = new DataImportOther();
        di.run();
    }
}
