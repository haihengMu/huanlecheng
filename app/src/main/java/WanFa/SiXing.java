package WanFa;

/**
 * Created by Administrator on 2017/3/30.
 */

public class SiXing {

    private int a;
    private String url;

    public String FiveGame(String positionstring) {
        a = 0;
        if (positionstring.length() == 4) {
            a = 1;
        } else if (positionstring.length() == 5) {
            a = 5;
        } else if (positionstring.length() == 6) {
            a = 15;
        } else if (positionstring.length() == 7) {
            a = 35;
        }else if (positionstring.length() == 8) {
            a = 70;
        }else if (positionstring.length() == 9) {
            a = 126;
        }else if (positionstring.length() == 10) {
            a = 210;
        }
        return a + "";
    }
    public String purl(String positionstring) {
        if (positionstring.length()>=4){
            char[] chars = positionstring.toCharArray();
            url = "";
            for (int i=0;i<chars.length;i++){
                if (chars.length-1==i){
                    url +=chars[i];
                }else {
                    url +=chars[i]+",";
                }
            }
        }
        return url;
    }
}

