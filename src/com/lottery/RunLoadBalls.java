package com.lottery;

/**
 * describe:
 *
 * @author scott dai
 * @date 2018/10/25
 */
public class RunLoadBalls {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

//        Iterator<SortedMap.Entry<String, Integer>> it = new DoSearch(50).gothroughStock("国际","333").iterator();
//        while (it.hasNext()) {
//            SortedMap.Entry<String, Integer> entry = it.next();
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
        String p = "0";
        System.out.println("===> Start to Get YunCai News!!!");
        if (args.length > 1)
            System.out.println("please input most only one parameter!!!");
        else {
            try {
                if (args.length > 0)
                    p = args[0];
                if (p.equals("1")) {
                    ImportNewData hb = new ImportNewData();
                    hb.ReadValues();
                } else if (p.equals("2")) {
                    ImportLotto hb = new ImportLotto();
                    hb.ReadValues();
                } else if (p.equals("3")) {
                    Import7star hb = new Import7star();
                    hb.ReadValues();
                } else System.out.println("please only input 1 or 2 as parameter!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
