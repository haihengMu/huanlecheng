package WanFa;

/**
 * Created by Administrator on 2017/3/30.
 */

public class Sanxing {
    private char[] charss;
    private int num;
    private int num1;
    private char[] chars2;
    private int num2;
    private char[] chars3;


    public String FiveGame(String it, String positionstring) {
        if (it.equals("0")) {
            num = positionstring.length();
            charss = positionstring.toCharArray();
        } else if (it.equals("1")) {
            num1 = positionstring.length();
            chars2 = positionstring.toCharArray();
        } else if (it.equals("2")) {
            num2 = positionstring.length();
            chars3 = positionstring.toCharArray();
        }
        int num5=num*num1*num2;
        return num5+"";
    }
    public String FiveUrl(String it, String positionstring) {
        if (it.equals("0")) {
            num = positionstring.length();
            charss = positionstring.toCharArray();
        } else if (it.equals("1")) {
            num1 = positionstring.length();
            chars2 = positionstring.toCharArray();
        } else if (it.equals("2")) {
            num2 = positionstring.length();
            chars3 = positionstring.toCharArray();
        }
        String url1="";
        String url2="";
        String url3="";
        if (charss !=null &&chars2!=null && chars3 !=null ){
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
            for (int i=0;i<chars3.length;i++){
                if (chars3.length-1==i){
                    url3 +=chars3[i]+"";
                }else {
                    url3 +=chars3[i]+",";
                }
            }
            String url=url1+"|"+url2+"|"+url3;
            return url+"";
        }else {
            return "";
        }
    }
}