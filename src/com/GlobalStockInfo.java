package com;


import com.bean.InfoBean;

import java.util.List;

/**
 * @Auther: neetriht scott
 * @Date: 1/1/2021 - 4:13 PM
 * @Software: IntelliJ IDEA
 * @Description:
 */

public class GlobalStockInfo {

    public static void printInfoBean(String stock_code, InfoBean info) {

        System.out.println("股票代码：" + info.getSTOCK_CODE());
        System.out.println("报告日期: " + info.getTRADING_DT());

        System.out.println("所属地区：" + info.getLOCATION());
        System.out.println("上市时间：" + info.getLIST_DT());
        System.out.println("每股现金流(元)：" + info.getFCFPS());
        System.out.println("净利润增长率(%)：" + info.getPRONW());

        System.out.println("总股本：" + info.getEQUITY());
        System.out.println("流通A股(亿)：" + info.getLIQUID());
        System.out.println("每股公积金(元)：" + info.getEUPF());
        System.out.println("每股未分配利润：" + info.getUPPS());

        System.out.println("每股收益：" + info.getEPS());
        System.out.println("每股净资产：" + info.getNAPS());
        System.out.println("净资产收益率(%)：" + info.getROE());
        System.out.println("主营收入增长率(%): " + info.getPIGR());

        System.out.println("投资亮点: " + info.getDESC());
    }

    public static InfoBean setValue(List<String> l) {
        InfoBean info = new InfoBean();
        info.setSTOCK_CODE(l.remove(0));  // 股票代码
        info.setTRADING_DT(l.remove(0));  // 报告日期
        info.setLOCATION(l.remove(0));    // 所属地区
        info.setLIST_DT(l.remove(0));     // 上市时间
        info.setFCFPS(l.remove(0));       // 每股现金流(元)
        info.setPRONW(l.remove(0));       // 净利润增长率(%)

        info.setEQUITY(l.remove(0));      // 总股本
        info.setLIQUID(l.remove(0));      // 流通A股(亿)
        info.setEUPF(l.remove(0));        // 每股公积金(元)
        info.setUPPS(l.remove(0));        // 每股未分配利润

        info.setEPS(l.remove(0));         // 每股收益
        info.setNAPS(l.remove(0));        // 每股净资产
        info.setROE(l.remove(0));         // 净资产收益率(%)
        info.setPIGR(l.remove(0));        // 主营收入增长率(%)

        info.setDESC(l.remove(0));        // 投资亮点
        return info;
    }


//    public static void printInfoBean(String stock_code, InfoBean info) {
//
//        System.out.println("股票代码：" + info.getSTOCK_CODE());
//        System.out.println("报告日期: " + info.getTRADING_DT());
//
//        System.out.println("所属地区：" + info.getLOCATION());
//        System.out.println("上市时间：" + info.getLIST_DT());
//        System.out.println("每股现金流(元)：" + info.getFCFPS());
//        System.out.println("净利润增长率(%)：" + info.getPRONW());
//
//        System.out.println("总股本：" + info.getEQUITY());
//        System.out.println("流通A股(亿)：" + info.getLIQUID());
//        System.out.println("每股公积金(元)：" + info.getEUPF());
//        System.out.println("每股未分配利润：" + info.getUPPS());
//
//        System.out.println("每股收益：" + info.getEPS());
//        System.out.println("每股净资产：" + info.getNAPS());
//        System.out.println("净资产收益率(%)：" + info.getROE());
//        System.out.println("主营收入增长率(%): " + info.getPIGR());
//
//        System.out.println("投资亮点: " + info.getDESC());
//    }
}
