package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */

public class PlayGameType implements Serializable {

    /**
     * G_T_Id : 1001001
     * G_T_NameId : 1
     * G_T_Title : 多星
     */

    private List<HXGameTypeBean> HX_Game_Type;

    public List<HXGameTypeBean> getHX_Game_Type() {
        return HX_Game_Type;
    }

    public void setHX_Game_Type(List<HXGameTypeBean> HX_Game_Type) {
        this.HX_Game_Type = HX_Game_Type;
    }
}
