package WanFa;

import java.util.ArrayList;
import java.util.List;

/**
 * 时时彩中的组选4 10 5
 * Created by Administrator on 2017/3/30.
 */

public class ZuXuanwusi {
    private char[] charss;
    private int num;
    private int num1;
    private char[] chars2;
    private int number;


    public String FiveGame(String it, String positionstring) {
        if (it.equals("0")) {
            num = positionstring.length();
            charss = positionstring.toCharArray();
        } else if (it.equals("1")) {
            num1 = positionstring.length();
            chars2 = positionstring.toCharArray();
        }
        List<String> mlist = new ArrayList<>();
        List<String> mmlist = new ArrayList<>();
        if (charss != null && chars2 != null) {
            for (int i = 0; i < charss.length; i++) {
                mlist.add(charss[i] + "");
            }
            for (int i = 0; i < chars2.length; i++) {
                mmlist.add(chars2[i] + "");
            }
            number = 0;
            for (int q=0;q<mlist.size();q++){
                for (int k=0;k<mmlist.size();k++){
                    if (mlist.get(q).equals(mmlist.get(k))){
                        number++;
                        continue;
                    }
                }
            }
        }

        int num5 = num * num1-number;
        return num5 + "";
    }

    public String FiveUrl(String it, String positionstring) {
        if (it.equals("0")) {
            num = positionstring.length();
            charss = positionstring.toCharArray();
        } else if (it.equals("1")) {
            num1 = positionstring.length();
            chars2 = positionstring.toCharArray();
        }
        String url1 = "";
        String url2 = "";
        if (charss != null && chars2 != null) {
            for (int i = 0; i < charss.length; i++) {
                if (charss.length - 1 == i) {
                    url1 += charss[i] + "";
                } else {
                    url1 += charss[i] + ",";
                }
            }
            for (int i = 0; i < chars2.length; i++) {
                if (chars2.length - 1 == i) {
                    url2 += chars2[i] + "";
                } else {
                    url2 += chars2[i] + ",";
                }
            }

            String url = url1 + "|" + url2;
            return url + "";
        } else {
            return "";
        }
    }
}
