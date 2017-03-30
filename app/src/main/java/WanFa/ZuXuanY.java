package WanFa;

/**
 * 组选120
 * Created by Administrator on 2017/3/30.
 */

public class ZuXuanY {

    private int a;
    private String url;

    public String FiveGame(String positionstring) {
        a = 0;
        if (positionstring.length() == 5) {
            a = 1;
        } else if (positionstring.length() == 6) {
            a = 6;
        } else if (positionstring.length() == 7) {
            a = 21;
        } else if (positionstring.length() == 8) {
            a = 56;
        }else if (positionstring.length() == 9) {
            a = 126;
        }else if (positionstring.length() == 10) {
            a = 252;
        }
        return a + "";
    }
    public String purl(String positionstring) {
        if (positionstring.length()>=5){
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
