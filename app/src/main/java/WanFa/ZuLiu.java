package WanFa;

/**
 * 组六
 * Created by Administrator on 2017/3/30.
 */

public class ZuLiu {
    private char[] chars;
    private int a;

    public String zuliu(String positionstring) {
        if (!positionstring.equals("")) {
            chars = positionstring.toCharArray();
            if (chars != null) {
                if (chars.length > 2) {
                    a = 0;
                    for (int j = 0; j < chars.length; j++) {
                        for (int k = 0; k < chars.length; k++) {
                            for (int q=0 ;q<chars.length;q++){
                              if (chars[j]<chars[k] &&chars[k]<chars[q]){
                                  a++;
                              }
                            }
                        }
                    }
                }else {
                    return  "0";
                }
            }
            return a + "";
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