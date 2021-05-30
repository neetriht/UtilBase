package com.lottery;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.stock.dbpool.DataSourceOper;

public class GetBallValues {
    DataSourceOper sconn = new DataSourceOper();
    //DataSourceOper sconn = new DataSourceOper();
    String[][] ball = new String[3000][7];
    String[][] issuetime = new String[3000][2];
    int total = 0;
    int all = 0;

    private String[] getRedBall(ResultSet rsquery) throws Exception {
        ResultSet rs = rsquery;

        String[] redball = {rs.getString(2), rs.getString(3), rs.getString(4),
                rs.getString(5), rs.getString(6), rs.getString(7),
                rs.getString(8)};
        return redball;
    }

    private String[] getRedBlueBall(ResultSet rsquery) throws Exception {
        ResultSet rs = rsquery;

        String[] redball = {rs.getString(3), rs.getString(4), rs.getString(5),
                rs.getString(6), rs.getString(7), rs.getString(8),
                rs.getString(9)};
        return redball;
    }

    private String[] getIssueTime(ResultSet rsquery) throws Exception {
        ResultSet rs = rsquery;
        String[] redball = {rs.getString(1), rs.getString(2)};
        return redball;
    }

    public void checkBall(String red01) {
        /*
         * List mlist = new ArrayList(); int j; for( j=0;j < 2000;j++) { mlist =
         * Arrays.asList(ball[j]); if (mlist.contains(red01)) {
         * System.out.println(mlist.toString()); total +=1; } }
         */
        this.checkBall(new String[]{red01});
    }

    public void checkBall(List slist, boolean mes) {
        // getAllBall();
        List<String> mlist = new ArrayList<String>();
        total = 0;
        SortedMap<String, List> numbers = new TreeMap<String, List>(); // =new
        // SortedMap<String,
        // List>;
        // Map n = new SortedMap<String, List>;
        int j;
        if (mes)
            System.out.println("Search balls: " + slist.toString());
        for (j = 0; j < 2000; j++) {
            mlist = Arrays.asList(ball[j]);

            if (mlist.containsAll(slist)) {
                List tlist = Arrays.asList(issuetime[j]);

                //System.out.println(tlist.toString());
                if (numbers == null || !numbers.containsKey(mlist.get(0)))
//					System.out.println(mlist.get(0).toString());
                    numbers.put(mlist.get(0).toString(), mlist);
                //System.out.println(slist.toString());

                total += 1;
            }
        }
        if (mes)
            System.out.println("Searched matching number: " + total);

        if (total > 0) {
            //System.out.println(numbers.size());
            while (numbers.size() > 0)
                System.out.println(numbers.remove(numbers.firstKey()));
//			//for (int i = 0; i < numbers.size(); i++)
//				//System.out.println(numbers.remove());
//				{System.out.println(numbers.size());

        }
        System.out.println("##########################");
        // System.out.println(all);
        // System.out.println(slist.toString()+":"+total/all);
    }

    public void checkBall(String[] redballs) {
        List<String> slist = Arrays.asList(redballs);
        this.checkBall(slist, true);
    }

    public boolean checknumber() {
        ResultSet rsquery;
        try {
            String sqltitle = "select count(*) from red_blue";

            System.out.println(ball.length);
            if (ball.length == sconn.executeGetInteger(sqltitle, 1))
                return true;

            else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public void getAllBall() {
        int i = 0;
        try {
            String sqltitle = "select * from red_blue";
            List<String[]> balllist =  sconn.executeGetStringGroup(sqltitle, 1,2, 3,4,5,6,7);
//            for (String[] balls: balllist) {
//                for(String ball : balls)
//                    ball[i] = this.getRedBall(rsquery);
//                issuetime[i] = this.getIssueTime(rsquery);
//                i++;
//            }
            all = i;
            System.out.println("Searched total number: " + all);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllBall(Date d1) {
        int i = 0;
        try {
            String sqltitle = "select * from red_blue where open_dt > " + d1;
          List<String[]> balllist = sconn.executeGetStringGroup(sqltitle, 1,2,3,4,5,6,7);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void getAllBall(Date d1, Date d2) {
//        ResultSet rsquery;
//        int i = 0;
//        try {
//            String sqltitle = "select * from red_blue where open_dt between "
//                    + d1 + " and " + d2;
//            rsquery = sconn.executeQuery(sqltitle);
//            while (rsquery.next()) {
//                ball[i] = this.getRedBall(rsquery);
//                issuetime[i] = this.getIssueTime(rsquery);
//                i++;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public List<String[]> getRecentOne() {

        try {
            String sqltitle = "select * from red_blue where open_dt in (select MAX(open_dt) from red_blue)";
            return  sconn.executeGetStringGroup(sqltitle,1,2,3,4,5,6,7);

//
//            if (rs.next() != false) {
//                String[] info = {rs.getString(1), rs.getString(2),
//                        rs.getString(3), rs.getString(4), rs.getString(5),
//                        rs.getString(6), rs.getString(7), rs.getString(8),
//                        rs.getString(9)};
//                return info;
//            } else
//                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String[]> getRecentOne(int a) {

        int i = 0;
        try {
            String sqltitle = "select * from red_blue where issue > ((select max(issue) from red_blue) - "
                    + a + ")";
            return  sconn.executeGetStringGroup(sqltitle,1,2,3,4,5,6,7);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public String[][] getStartOne(String start_date) {
//        return getStartOne(start_date, "2099-01-01");
//        /*
//         * ResultSet rs; int i = 0; try { String sqltitle =
//         * "select * from red_blue where open_dt >= '" + start_date + "'";//in
//         * (select MAX(open_dt) from red_blue)"; rs=
//         * sconn.executeQuery(sqltitle); while (rs.next()) { ball[i] =
//         * this.getRedBlueBall(rs); //issuetime[i] = this.getIssueTime(rs);
//         * //System.out.println(i); i++; } return ball; } catch (Exception e) {
//         * e.printStackTrace(); return null; }
//         */
//    }

   // public String[][] getStartOne() {
//        return getStartOne("2000-01-01");
//    }

    public List<String[]> getStartOne(String start_date, String end_date) {
        ResultSet rs;
        int i = 0;
        try {
            String sqltitle = "select * from red_blue where open_dt >= '"
                    + start_date + "' and open_dt <= '" + end_date + "'";// in
            // (select
            // MAX(open_dt)
            // from
            // red_blue)";
            return  sconn.executeGetStringGroup(sqltitle,1,2,3,4,5,6,7);
//            while (rs.next()) {
//                ball[i] = this.getRedBlueBall(rs);
//                // issuetime[i] = this.getIssueTime(rs);
//                // System.out.println(i);
//                i++;
//            }
//            return ball;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
