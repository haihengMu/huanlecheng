package WanFa;

import java.util.ArrayList;
import java.util.List;

/**
 * 组选60
 * Created by Administrator on 2017/3/30.
 */

public class ZuxuanLiu {

    private char[] chars;
    private char[] chars1;
    private int a;

    public String zuliu(String it, String positionstring) {
        if (it.equals("0")) {
            chars = positionstring.toCharArray();
        } else {
            chars1 = positionstring.toCharArray();
        }
        if (chars != null && chars1 != null&&chars1.length>2) {
            List<String> mllist = new ArrayList<>();
            List<String> mmllist = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                mllist.add(chars[i] + "");
            }
            a = 0;
            for (int p = 0; p < chars1.length; p++) {
                for (int k=0;k<chars1.length;k++){
                    for (int t=0;t<chars1.length;t++){
                        if (chars1[p] !=chars1[k]&& chars1[p] !=chars1[t] &&chars1[k] !=chars1[t]){
                            if (chars1[t]>chars1[k]&&chars1[k]>chars1[p]) {
                                mmllist.add(chars1[p] + "" + chars1[k] + "" + chars1[t]);
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < mllist.size(); i++) {
                for (int j = 0; j < mmllist.size(); j++) {
                    if (mmllist.get(j).indexOf(mllist.get(i))==-1){
                        a++;
                    }
                }
            }
        }
        return a+"";
    }
    public String purl(String it, String positionstring){
        if (it.equals("0")) {
            chars = positionstring.toCharArray();
        } else if (it.equals("1")) {
            chars1 = positionstring.toCharArray();
        }
        String url1 = "";
        String url2 = "";
        if (chars != null && chars1 != null) {
            for (int i = 0; i < chars.length; i++) {
                if (chars.length - 1 == i) {
                    url1 += chars[i] + "";
                } else {
                    url1 += chars[i] + ",";
                }
            }
            for (int i = 0; i < chars1.length; i++) {
                if (chars1.length - 1 == i) {
                    url2 += chars1[i] + "";
                } else {
                    url2 += chars1[i] + ",";
                }
            }
            String url = url1 + "|" + url2;
            return url + "";
        } else {
            return "";
        }
    }
}
