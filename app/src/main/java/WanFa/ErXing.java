package WanFa;

/**
 * Created by Administrator on 2017/3/30.
 */

public class ErXing {
    private char[] charss;
    private int num;
    private int num1;
    private char[] chars2;


    public String FiveGame(String it, String positionstring) {
        if (it.equals("0")) {
            num = positionstring.length();
            charss = positionstring.toCharArray();
        } else if (it.equals("1")) {
            num1 = positionstring.length();
            chars2 = positionstring.toCharArray();
        }
        int num5=num*num1;
        return num5+"";
    }
    public String FiveUrl(String it, String positionstring) {
        if (it.equals("0")) {
            num = positionstring.length();
            charss = positionstring.toCharArray();
        } else if (it.equals("1")) {
            num1 = positionstring.length();
            chars2 = positionstring.toCharArray();
        }
        String url1="";
        String url2="";
        if (charss !=null &&chars2!=null){
            for (int i=0;i<charss.length;i++){
                if (charss.length-1==i){
                    url1 +=charss[i]+"";
                }else {
                    url1 +=charss[i]+",";
                }
            }
            for (int i=0;i<chars2.length;i++){
                if (chars2.length-1==i){
                    url2 +=chars2[i]+"";
                }else {
                    url2 +=chars2[i]+",";
                }
            }

            String url=url1+"|"+url2;
            return url+"";
        }else {
            return "";
        }
    }
}

