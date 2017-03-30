package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/13.
 */

public class BankLieBean implements Serializable {

    /**
     * H_U_B_L_Bank_Account : 6212***********3430
     * H_U_B_L_Bank_Id : 1
     * H_U_B_L_Id : 30109
     * H_U_B_L_Opening_Address : 北京市 东城
     */

    private String H_U_B_L_Bank_Account;
    private String H_U_B_L_Bank_Id;
    private String H_U_B_L_Id;
    private String H_U_B_L_Opening_Address;

    public String getH_U_B_L_Bank_Account() {
        return H_U_B_L_Bank_Account;
    }

    public void setH_U_B_L_Bank_Account(String H_U_B_L_Bank_Account) {
        this.H_U_B_L_Bank_Account = H_U_B_L_Bank_Account;
    }

    public String getH_U_B_L_Bank_Id() {
        return H_U_B_L_Bank_Id;
    }

    public void setH_U_B_L_Bank_Id(String H_U_B_L_Bank_Id) {
        this.H_U_B_L_Bank_Id = H_U_B_L_Bank_Id;
    }

    public String getH_U_B_L_Id() {
        return H_U_B_L_Id;
    }

    public void setH_U_B_L_Id(String H_U_B_L_Id) {
        this.H_U_B_L_Id = H_U_B_L_Id;
    }

    public String getH_U_B_L_Opening_Address() {
        return H_U_B_L_Opening_Address;
    }

    public void setH_U_B_L_Opening_Address(String H_U_B_L_Opening_Address) {
        this.H_U_B_L_Opening_Address = H_U_B_L_Opening_Address;
    }
}
