package WanFa;

/**
 * 组三组选
 * Created by Administrator on 2017/3/30.
 */

public class QerZX {
    private char[] charss;
    private int num;

    public String FiveGame(String positionstring) {
        if (!positionstring.equals("")) {
            num = positionstring.length();
            int num5 = num * (num - 1);
            return num5 + "";
        } else {
            return "0";
        }
    }

    public String FiveUrl(String positionstring) {
        if (!positionstring.equals("")) {
            num = positionstring.length();
            charss = positionstring.toCharArray();
            String url1 = "";
            for (int i = 0; i < charss.length; i++) {
                if (charss.length - 1 == i) {
                    url1 += charss[i] + "";
                } else {
                    url1 += charss[i] + ",";
                }
            }
            String url = url1;
            return url + "";
        } else {
            return "";
        }
    }
}
