package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/21.
 */

public class LoginGXBean implements Serializable {

    /**
     * h_u_id : 11
     * h_u_gid : 8
     * h_u_proxy_id : 7
     * h_u_proxy_list : ["hxgzs001","hxgzs002","hxgzs003","hxgzs004"]
     * h_u_name : chenzhen
     * h_u_real_name :
     * h_u_login_count : 14
     * h_u_max_rebate : 12.60
     * h_u_current_rebate : 12.60
     * h_u_same_rebate : 0.00
     * h_u_balance : 0.0000
     * h_u_frozen_balance : 0.0000
     * h_u_dividends_balance : 0.0000
     * h_u_is_lock_bank_info : 0
     * h_u_is_withdraw : 1
     * h_u_is_deposit : 1
     * h_u_is_lower_transfer : 0
     * h_u_is_lower_deposit : 0
     * h_u_is_music : 1
     * h_u_level : 0
     * h_u_eredit_level : 0
     * h_u_integral : 0
     * h_u_experience : 0
     * h_u_add_users : ["8"]
     * h_u_nickname : chenzhen
     * h_u_email :
     * h_u_qq :
     * h_u_mobile :
     * h_u_reserved_info :
     * h_u_safety_qid : 0
     * h_u_chat_avatar :
     * h_u_chat_cid : 0
     * h_u_chat_status : 0
     * h_u_chat_signature : 这家伙很懒,什么都没留下.
     * h_u_pt_username :
     * h_u_pt_balance : 0.00
     * h_u_ag_username :
     * h_u_ag_balance : 0.00
     * h_u_bbin_username :
     * h_u_bbin_balance : 0.00
     * h_u_gg_username :
     * h_u_gg_balance : 0.00
     * h_u_dj_username :
     * h_u_dj_balance : 0.00
     * h_u_token : eb12daaabebbbfc0f551afa2f06ee84f
     * h_u_sid : 1
     * h_u_the_login_time : 2017-02-21 13:36:41
     * h_u_the_login_ip : 1.58.74.159
     * h_u_the_login_system : 未知
     * h_u_last_login_time : 2017-02-21 13:31:05
     * h_u_last_login_ip : 1.58.74.159
     * h_u_last_login_system : 未知
     * h_u_online_time : 2017-02-21 13:36:41
     * h_u_regtime : 2017-02-21 09:22:14
     */

    private LoginGxDataBean data;

    public LoginGxDataBean getData() {
        return data;
    }

    public void setData(LoginGxDataBean data) {
        this.data = data;
    }
}
