package constants;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class UserInfo {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public UserInfo(Context context) {
        sp = context.getSharedPreferences(Constants.DATABASE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void setRealName(String realName) {
        editor.putString(UserDataModel.RealName, realName).toString();
        editor.commit();
    }

    public String getRealName() {
        return sp.getString(UserDataModel.RealName, "");
    }

    public void cleanUserinfo() {
        editor.clear();
        editor.commit();
    }

    public String getU_UserName() {
        return sp.getString(UserDataModel.U_UserName, "");
    }

    public void setU_UserName(String username) {
        editor.putString(UserDataModel.U_UserName, username).toString();
        editor.commit();
    }

    public String getU_Money() {
        return sp.getString(UserDataModel.U_Money, "");
    }

    public void setU_Money(String password) {
        editor.putString(UserDataModel.U_Money, password).toString();
        editor.commit();
    }

    public String getU_Head() {
        return sp.getString(UserDataModel.U_Head, "");
    }

    public void setU_Head(String nickname) {
        editor.putString(UserDataModel.U_Head, nickname).toString();
        editor.commit();
    }

    public String getU_RebateA() {
        return sp.getString(UserDataModel.U_RebateA, "");
    }

    public void setU_RebateA(String realname) {
        editor.putString(UserDataModel.U_RebateA, realname).toString();
        editor.commit();
    }

    public String getU_RebateB() {
        return sp.getString(UserDataModel.U_RebateB, "");
    }

    public void setU_RebateB(String headimg) {
        editor.putString(UserDataModel.U_RebateB, headimg).toString();
        editor.commit();
    }

    public String getCorner() {
        return sp.getString(UserDataModel.CORNER, "");
    }

    public void setCorner(String headimg) {
        editor.putString(UserDataModel.CORNER, headimg).toString();
        editor.commit();
    }

    public String getLogin_Name() {
        return sp.getString(UserDataModel.Login_Name, "");
    }

    public void setLogin_Name(String headimg) {
        editor.putString(UserDataModel.Login_Name, headimg).toString();
        editor.commit();
    }

    public String getPassword() {
        return sp.getString(UserDataModel.Password, "");
    }

    public void setPassword(String headimg) {
        editor.putString(UserDataModel.Password, headimg).toString();
        editor.commit();
    }

    public void setU_id(String uid) {
        editor.putString(UserDataModel.U_id, uid).toString();
        editor.commit();
    }

    public String getUid() {
        return sp.getString(UserDataModel.U_id, "");
    }
}
