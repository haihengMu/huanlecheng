package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/4.
 */

public class CaipiaoDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "caipiao.db";
    private static final String TABLENAME = "bigname";
    private static final String TABLE_NAME = "Play_name";
    private static final String TABLE_TOP = "table_new_top";

    public CaipiaoDBHelper(Context context) {//创建构造创建数据库
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * tpid
     * nameId名的id
     * zero 百分数
     * today_money当前奖金
     * hx_name大游戏名
     * NAME 游戏名
     * Number 投注号码
     * Code_Text 注数
     * Multiple倍数
     * Mode 投注模式
     * money投注钱数
     * ID游戏id
     * time当前系统的时间
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(_id integer primary key autoincrement,nameId varchar(30),zero varchar(30),today_money varchar(30)," +
                "hx_name varchar(30),name varchar(30),number varchar(30),code_text varchar(30),multiple varchar(30)," +
                "mode varchar(30),money varchar(30),tid varchar(30),mount varchar(30),tipId varchar(30),idea varchar(30),time varchar(30))");
        db.execSQL("create table " + TABLENAME + "(_id integer primary key autoincrement,name varchar(30),tid varchar(30),introduce varchar(30))");
       /* db.execSQL("create table "+TABLE_TOP+"(_id integer primary key autoincrement,G_P_AddTime varchar(30),G_P_AmountStep varchar(30),G_P_Bonus varchar(30)" +
                ",G_P_Decimal varchar(30),G_P_Id varchar(30),G_P_MaxBetsMoney varchar(30),G_P_MaxBonus varchar(30),G_P_MaxBonusMode varchar(30)" +
                ",G_P_Maximum varchar(30),G_P_MaximumBonusRebate varchar(30),G_P_MinBetsMoney varchar(30),G_P_MinimumBonusRebate varchar(30),G_P_Name varchar(30)," +
                "G_P_NameId varchar(30),G_P_NumberName varchar(30),G_P_Off varchar(30),G_P_OneAmount varchar(30),G_P_RebateStep varchar(30),G_P_ReturnOff varchar(30)" +
                ",G_P_Rules varchar(30),G_P_Sort varchar(30),G_P_Text varchar(30),G_P_TypeId varchar(30),G_P_WapOff varchar(30),title varchar)");*/
        db.execSQL("create table " + TABLE_TOP + "(_id integer primary key autoincrement,h_g_p_name varchar(10),h_g_p_id varchar(30),h_g_p_cid varchar(30)" +
                ",h_g_p_nid varchar(30),h_g_p_tid varchar(30),h_g_p_gid varchar(30),h_g_p_rid varchar(30),h_g_p_one_amount varchar(30)" +
                ",h_g_p_max_bet_mum varchar(30),h_g_p_bonus varchar(30),h_g_p_amount_step varchar(30),h_g_p_rebate_step varchar(30),h_g_p_decimal varchar(30)," +
                "h_g_p_return_off varchar(30),h_g_p_introduction varchar(30),h_g_p_example varchar(30),h_g_p_max_imumbonus_rebate varchar(30),h_g_p_mini_mumbonus_rebate varchar(30),h_g_p_mini_bet_money varchar(30)" +
                ",h_g_p_max_bet_money varchar(30),h_g_p_max_bonus varchar(30),h_g_p_max_bonus_mode varchar(30),h_g_p_not_bet_code varchar(30),h_g_p_singled_num varchar(30),h_g_p_singled_max_bonus varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
