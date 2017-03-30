package WanFa;

import java.util.ArrayList;
import java.util.List;

/**
 * 前四后四组选6
 * Created by Administrator on 2017/3/30.
 */

public class ZuXuanS {

    private char[] chars;
    private int a;

    public String zuliu(String positionstring) {
        if (!positionstring.equals("")) {
            chars = positionstring.toCharArray();
            if (chars != null) {
                if (chars.length > 1) {
                    List<String> mlist = new ArrayList<>();
                    for (int i = 0; i < chars.length; i++) {
                        mlist.add(chars[i] + "");
                    }
                    a = 0;
                    for (int j = 0; j < mlist.size(); j++) {
                        for (int k = 0; k < mlist.size(); k++) {
                            if (!mlist.get(j).equals(mlist.get(k))) {
                                a++;
                            }
                        }
                    }
                }

            }
            return a / 2 + "";
        }else {
            return  "0";
        }
    }

    public String purl(String positionstring) {
        chars = positionstring.toCharArray();
        if (chars !=null) {
            String url1 = "";
            for (int i = 0; i < chars.length; i++) {
                if (chars.length - 1 == i) {
                    url1 += chars[i] + "";
                } else {
                    url1 += chars[i] + ",";
                }
            }
            String url = url1;
            return url + "";
        }else {
            return "";
        }
    }
}