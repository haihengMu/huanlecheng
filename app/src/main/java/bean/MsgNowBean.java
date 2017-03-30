package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/13.
 */

public class MsgNowBean implements Serializable {
    private String U_UserName;
    private String U_Money;
    private String U_UpAgent;
    private String U_TheLoginTime;

    public String getU_Money() {
        return U_Money;
    }

    public void setU_Money(String u_Money) {
        U_Money = u_Money;
    }

    public String getU_UpAgent() {
        return U_UpAgent;
    }

    public void setU_UpAgent(String u_UpAgent) {
        U_UpAgent = u_UpAgent;
    }

    public String getU_TheLoginTime() {
        return U_TheLoginTime;
    }

    public void setU_TheLoginTime(String u_TheLoginTime) {
        U_TheLoginTime = u_TheLoginTime;
    }

    public String getU_UserName() {
        return U_UserName;
    }

    public void setU_UserName(String u_UserName) {
        U_UserName = u_UserName;
    }
}
