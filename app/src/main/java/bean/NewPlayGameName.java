package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public class NewPlayGameName implements Serializable {

    /**
     * h_g_p_id : 104
     * h_g_p_name : 五星复式
     * h_g_p_cid : 1
     * h_g_p_nid : 2
     * h_g_p_tid : 15
     * h_g_p_gid : 27
     * h_g_p_rid : 1
     * h_g_p_config : {"line":5,"win":[0,1,2,3,4],"list":[{"万位":[0,1,2,3,4,5,6,7,8,9]},{"千位":[0,1,2,3,4,5,6,7,8,9]},{"百位":[0,1,2,3,4,5,6,7,8,9]},{"十位":[0,1,2,3,4,5,6,7,8,9]},{"个位":[0,1,2,3,4,5,6,7,8,9]}],"is_self_pick":true}
     * h_g_p_one_amount : 1.0000
     * h_g_p_max_bet_mum : 80000
     * h_g_p_bonus : 85000.00
     * h_g_p_amount_step : 1000.00
     * h_g_p_rebate_step : 0.1000
     * h_g_p_decimal : 4
     * h_g_p_return_off : 1
     * h_g_p_introduction : 从个、十、百、千、万位各选一个号码组成一注。
     * h_g_p_example : 23456；开奖号码：23456</br>
     玩法：从万位、千位、百位、十位、个位中选择一个5位数号码组成一注，所选号码与开奖号码全部相同，且顺序一致，即为中奖。</br>
     玩法奖金：85000
     * h_g_p_max_imumbonus_rebate : 0
     * h_g_p_mini_mumbonus_rebate : 0
     * h_g_p_mini_bet_money : 0.0000
     * h_g_p_max_bet_money : 0.0000
     * h_g_p_max_bonus : 200000.0000
     * h_g_p_max_bonus_mode : 0
     * h_g_p_not_bet_code :
     * h_g_p_singled_num : 3500
     * h_g_p_singled_max_bonus : 18000.0000
     */

    private List<NewPlayGameNameChild> data;
    private String error="";

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<NewPlayGameNameChild> getData() {
        return data;
    }

    public void setData(List<NewPlayGameNameChild> data) {
        this.data = data;
    }
}
