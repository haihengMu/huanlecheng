package WanFa;

/**
 * 五星复试
 * Created by Administrator on 2017/3/30.
 */

public class FivePlay {

    private char[] charss;
    private int num;
    private int num1;
    private char[] chars2;
    private int num2;
    private char[] chars3;
    private int num3;
    private char[] chars4;
    private int num4;
    private char[] chars5;

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
        } else if (it.equals("3")) {
            num3 = positionstring.length();
            chars4 = positionstring.toCharArray();
        } else if (it.equals("4")) {
            num4 = positionstring.length();
            chars5 = positionstring.toCharArray();
        }
        int num5=num*num1*num2*num3*num4;
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
        } else if (it.equals("3")) {
            num3 = positionstring.length();
            chars4 = positionstring.toCharArray();
        } else if (it.equals("4")) {
            num4 = positionstring.length();
            chars5 = positionstring.toCharArray();
        }
        String url1="";
        String url2="";
        String url3="";
        String url4="";
        String url5="";
        if (charss !=null &&chars2!=null && chars3 !=null &&chars4!=null &&chars5 !=null){
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
            for (int i=0;i<chars4.length;i++){
                if (chars4.length-1==i){
                    url4 +=chars4[i]+"";
                }else {
                    url4 +=chars4[i]+",";
                }
            }   for (int i=0;i<chars5.length;i++){
                if (chars5.length-1==i){
                    url5 +=chars5[i]+"";
                }else {
                    url5 +=chars5[i]+",";
                }
            }
            String url=url1+"|"+url2+"|"+url3+"|"+url4+"|"+url5;
                return url+"";
        }else {
            return "";
        }
    }
}
