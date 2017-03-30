package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */

public class BankCZBean implements Serializable {

    /**
     * H_T_P_T_BankCode : [{"code":"SCANCODE-WEIXIN_PAY-P2P","id":"27"}]
     * H_T_P_T_Id : 14
     * H_T_P_T_Nickname : 信用卡/微信支付
     */

    private String H_T_P_T_Id;
    private String H_T_P_T_Nickname;
    /**
     * code : SCANCODE-WEIXIN_PAY-P2P
     * id : 27
     */

    private List<HTPTBankCodeBean> H_T_P_T_BankCode;

    public String getH_T_P_T_Id() {
        return H_T_P_T_Id;
    }

    public void setH_T_P_T_Id(String h_T_P_T_Id) {
        H_T_P_T_Id = h_T_P_T_Id;
    }

    public String getH_T_P_T_Nickname() {
        return H_T_P_T_Nickname;
    }

    public void setH_T_P_T_Nickname(String h_T_P_T_Nickname) {
        H_T_P_T_Nickname = h_T_P_T_Nickname;
    }

    public List<HTPTBankCodeBean> getH_T_P_T_BankCode() {
        return H_T_P_T_BankCode;
    }

    public void setH_T_P_T_BankCode(List<HTPTBankCodeBean> h_T_P_T_BankCode) {
        H_T_P_T_BankCode = h_T_P_T_BankCode;
    }
}
