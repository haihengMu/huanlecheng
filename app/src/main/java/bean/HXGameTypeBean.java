package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/22.
 */

public class HXGameTypeBean implements Serializable {
    private String G_T_Id;
    private String G_T_NameId;
    private String G_T_Title;

    public String getG_T_Id() {
        return G_T_Id;
    }

    public void setG_T_Id(String g_T_Id) {
        G_T_Id = g_T_Id;
    }

    public String getG_T_NameId() {
        return G_T_NameId;
    }

    public void setG_T_NameId(String g_T_NameId) {
        G_T_NameId = g_T_NameId;
    }

    public String getG_T_Title() {
        return G_T_Title;
    }

    public void setG_T_Title(String g_T_Title) {
        G_T_Title = g_T_Title;
    }
}
