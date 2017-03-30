package WanFa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class ZuSanDT {
    private int num;
    private char[] chars2;
    private String nnn;


    public String FiveGame(String it, String positionstring) {
        if (it.equals("0")) {
            nnn = positionstring;
        } else if (it.equals("1")) {
            chars2 = positionstring.toCharArray();
        }
        if (!nnn.equals("")) {
            if (nnn.length() == 1 && chars2 != null) {
                List<String> mlist = new ArrayList<>();
                for (int i = 0; i < chars2.length; i++) {
                    mlist.add(chars2[i] + "");
                }
                if (mlist.indexOf(nnn) == -1) {
                    return mlist.size() * 2 + "";
                } else {
                    return "0";
                }
            } else {
                return "0";
            }
        }else {
            return "0";
        }
    }

    public String FiveUrl(String it, String positionstring) {
        if (it.equals("0")) {
            nnn = positionstring;
        } else if (it.equals("1")) {
            chars2 = positionstring.toCharArray();
        }
        String url2 = "";
        if (!nnn.equals("")) {
            if (nnn.length() == 1 && chars2 != null) {
                for (int i = 0; i < chars2.length; i++) {
                    if (chars2.length - 1 == i) {
                        url2 += chars2[i] + "";
                    } else {
                        url2 += chars2[i] + ",";
                    }
                }
            }
            String url = nnn + "|" + url2;
            return url + "";
        } else {
            return "";
        }

    }
}