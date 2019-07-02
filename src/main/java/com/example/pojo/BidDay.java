/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static jxl.biff.BaseCellFeatures.logger;

/**
 *
 * @author Administrator
 */
public class BidDay {

    public int id;
    public String author;
    public String day_1_count;
    public String day_2_count;
    public String day_3_count;
    public String day_4_count;
    public String day_5_count;
    public String url;
    public String project;
    public String auditor;
    public int label;
    private static Connection conn;

    public BidDay(int id, String author, String day_1_count, String day_2_count, String day_3_count, String day_4_count, String day_5_count, String url, String project, String auditor, int label) {
        this.id = id;
        this.author = author;
        this.day_1_count = day_1_count;
        this.day_2_count = day_2_count;
        this.day_3_count = day_3_count;
        this.day_4_count = day_4_count;
        this.day_5_count = day_5_count;
        this.url = url;
        this.project = project;
        this.auditor = auditor;
        this.label = label;
    }

    public BidDay() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.252:3306/suidaobig?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8", "root", "123asd123asd");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/suidaobig?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8", "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            logger.error("链接数据库时异常！异常信息：" + e.getMessage());
        }
    }

    public void getDay_1_count() {
        try {
            String sql1 = "SELECT id,name,day_1_count,update_time  from `stang_bid_worning`";

            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pstmt1.executeQuery();

            while (rs1.next()) {
                String author = rs1.getString("name");
                int day_1_count = rs1.getInt("day_1_count");
                int add_time = rs1.getInt("update_time");
                int label = 1;
                if (add_time <= 1561651200) {
                    label = 0;
                }
                String sql2 = "update stang_bid_day_worning set day_1_count = ?,label=? where author = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, day_1_count);
                pstmt2.setInt(2, label);
                pstmt2.setString(3, author);
                pstmt2.executeUpdate();
                System.out.println("day_1_count更新成功+" + author);
                conn.commit();
            }
            System.out.println("==========================  day_1_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_1_count  更新成功 发生错误：" + e.toString());
        }
    }

    public void getDay_2_count() {
        try {
            String sql1 = "SELECT author,day_1_count  from `stang_bid_day_worning`";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                String author = rs1.getString("author");
                int day_1_count = rs1.getInt("day_1_count");
                String sql2 = "update stang_bid_day_worning set day_2_count = ? where author = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, day_1_count);
                pstmt2.setString(2, author);
                pstmt2.executeUpdate();
                conn.commit();
                System.out.println("day_2_count更新成功+" + author);
            }
            System.out.println("==========================  day_2_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_4_count  更新成功 发生错误：" + e.toString());
        }
    }

    public void getDay_3_count() {
        try {
            String sql1 = "SELECT author,day_2_count  from `stang_bid_day_worning`";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                String author = rs1.getString("author");
                int day_2_count = rs1.getInt("day_2_count");
                String sql2 = "update stang_bid_day_worning set day_3_count = ? where author = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, day_2_count);
                pstmt2.setString(2, author);
                pstmt2.executeUpdate();
                conn.commit();
                System.out.println("day_3_count更新成功+" + author);
            }
            System.out.println("==========================  day_3_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_3_count  更新成功 发生错误：" + e.toString());
        }
    }

    public void getDay_4_count() {
        try {
            String sql1 = "SELECT author,day_3_count  from `stang_bid_day_worning`";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                String author = rs1.getString("author");
                int day_3_count = rs1.getInt("day_3_count");
                String sql2 = "update stang_bid_day_worning set day_4_count = ? where author = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, day_3_count);
                pstmt2.setString(2, author);
                pstmt2.executeUpdate();
                conn.commit();
                System.out.println("day_4_count更新成功+" + author);
            }
            System.out.println("==========================  day_4_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_4_count  更新成功 发生错误：" + e.toString());
        }
    }

    public void getDay_5_count() {
        try {
            String sql1 = "SELECT author,day_4_count  from `stang_bid_day_worning`";
            PreparedStatement pstmt1 = conn.prepareStatement(sql1);
            ResultSet rs1 = pstmt1.executeQuery();
            while (rs1.next()) {
                String author = rs1.getString("author");
                int day_4_count = rs1.getInt("day_4_count");
                String sql2 = "update stang_bid_day_worning set day_5_count = ? where author = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setInt(1, day_4_count);
                pstmt2.setString(2, author);
                pstmt2.executeUpdate();
                conn.commit();
                System.out.println("day_5_count更新成功+" + author);
            }
            System.out.println("==========================  day_5_count  更新成功完成================");
        } catch (Exception e) {
            System.out.println("day_5_count  更新成功 发生错误：" + e.toString());
        }
    }

    public String getUrl() {
        return url;
    }

    public String getProject() {
        return project;
    }

    public int getLabel() {
        return label;
    }

    public String getAuditor() {
        return auditor;
    }

}
