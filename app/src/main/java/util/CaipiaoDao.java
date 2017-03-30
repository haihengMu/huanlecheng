package util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bean.CaiPiaoNewTopBean;
import bean.CaipiaoBean;
import bean.NameBean;

/**
 * 彩票信息数据库的dao,
 * 增删改查
 * Created by Administrator on 2016/11/4.
 */

public class CaipiaoDao {

    private final CaipiaoDBHelper helper;
    private static final String TABLE_NAME = "Play_name";
    private static final String TABLENAME = "bigname";
    private List<NameBean> data;
    private static final String TABLE_TOP = "table_new_top";
    /**
     * 只有一个有参的构造,要求必须传入上下文
     *
     * @param context
     */
    public CaipiaoDao(Context context) {
        helper = new CaipiaoDBHelper(context);
    }

    /**
     * 增加一个游戏的追号单
     * hx_name 大游戏名
     * NAME 游戏名
     * Number 投注号码
     * Code_Text 注数
     * Multiple倍数
     * Mode 投注模式
     * money投注钱数
     * tid投注的游戏的id
     */
    public boolean add(String nameId, String zero, String today_money, String hx_name, String name, String number,
                       String code_text, String multiple, String mode, String money, String tid, String mount,
                       String tipId, String idea, String time) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nameId", nameId);
        values.put("zero", zero);
        values.put("today_money", today_money);
        values.put("hx_name", hx_name);
        values.put("name", name);
        values.put("number", number);
        values.put("code_text", code_text);
        values.put("multiple", multiple);
        values.put("mode", mode);
        values.put("money", money);
        values.put("tid", tid);
        values.put("mount", mount);
        values.put("tipId", tipId);
        values.put("idea", idea);
        values.put("time", time);
        long result = db.insert(TABLE_NAME, null, values);
        db.close();//关闭数据库释放资源
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据时间删除
     *
     * @return
     */
    public void delByTime(String time) {
        String sql = "delete from " + TABLE_NAME + " where time =?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[]{time});
        db.close();
    }

    /**
     * 根据id删除
     *
     * @return
     */
    public void delById(String name) {
        String sql = "delete from " + TABLENAME + " where name =?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[]{name});
        db.close();
    }

    public void delByTitle(String name) {
        String sql = "delete from " + TABLE_NAME + " where hx_name=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[]{name});
        db.close();
    }

    /**
     * top
     *
     * @param name
     */
    public void delByname(String name) {
        String sql = "delete from " + TABLE_TOP + " where G_P_Name =?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[]{name});
        db.close();
    }

    /**
     * 根据名删除
     *
     * @param name
     * @return
     */
    public void delTop(String name) {
        String sql = "delete from " + TABLE_TOP + "where G_P_Id=?";
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(sql, new String[]{name});
        db.close();

    }

    public boolean addname(String name, String tid) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("tid", tid);
        long result = db.insert(TABLENAME, null, values);
        db.close();
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public List<NameBean> findAllname() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<NameBean> list = new ArrayList<>();
        Cursor cursor = db.query(TABLENAME, new String[]{"name", "tid"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String tid = cursor.getString(1);
            NameBean nameBean = new NameBean();
            nameBean.setName(name);
            nameBean.setTid(tid);
            list.add(nameBean);
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 跟局名查找所有符合的数据集合
     *
     * @param dname
     * @return
     */
    public List<CaipiaoBean> findName(String dname) {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<CaipiaoBean> mlist = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"nameId", "zero", "today_money", "hx_name", "name", "number", "code_text", "multiple", "mode",
                "money", "tid", "mount", "tipId", "idea", "time"}, "hx_name=?", new String[]{dname}, null, null, null);
        while (cursor.moveToNext()) {
            String nameId = cursor.getString(0);
            String zero = cursor.getString(1);
            String today_money = cursor.getString(2);
            String hx_name = cursor.getString(3);
            String name = cursor.getString(4);
            String number = cursor.getString(5);
            String code_text = cursor.getString(6);
            String multiple = cursor.getString(7);
            String mode = cursor.getString(8);
            String money = cursor.getString(9);
            String tid = cursor.getString(10);
            String mount = cursor.getString(11);
            String tipId = cursor.getString(12);
            String idea = cursor.getString(13);
            String time = cursor.getString(14);
            CaipiaoBean caipiaoBean = new CaipiaoBean();
            caipiaoBean.setMount(mount);
            caipiaoBean.setNameId(nameId);
            caipiaoBean.setZero(zero);
            caipiaoBean.setToday_money(today_money);
            caipiaoBean.setHx_name(hx_name);
            caipiaoBean.setName(name);
            caipiaoBean.setNumber(number);
            caipiaoBean.setCode_text(code_text);
            caipiaoBean.setMultiple(multiple);
            caipiaoBean.setMode(mode);
            caipiaoBean.setMoney(money);
            caipiaoBean.setTid(tid);
            caipiaoBean.setTipId(tipId);
            caipiaoBean.setIdea(idea);
            caipiaoBean.setTime(time);
            mlist.add(caipiaoBean);
        }
        cursor.close();
        db.close();
        return mlist;
    }

    /**
     * 根据id删除一张追号单
     */
    public void delete(int _id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where _id=?", new Object[]{_id});
        db.close();//释放资源
    }


    /**
     * 删除全部
     */
    public void deleteAll() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
        db.close();
    }

    public void deleteAllName() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from " + TABLENAME);
        db.close();
    }

    /**
     * 根据id查询投注单
     * 如果返回的null这表示不存在
     */
    public boolean find(String hx_name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select hx_name from " + TABLE_NAME + " where hx_name=?", new String[]{hx_name});
        boolean result = cursor.moveToNext();
        return result;
    }

    /**
     * 查找表中所有字段的id
     */
    public List<CaipiaoBean> findAll() {
        SQLiteDatabase db = helper.getReadableDatabase();
        List<CaipiaoBean> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"nameId", "zero", "today_money", "hx_name", "name", "number",
                "code_text", "multiple", "mode", "money", "tid", "mount", "tipId", "idea", "time"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String nameId = cursor.getString(0);
            String zero = cursor.getString(1);
            String today_money = cursor.getString(2);
            String hx_name = cursor.getString(3);
            String name = cursor.getString(4);
            String number = cursor.getString(5);
            String code_text = cursor.getString(6);
            String multiple = cursor.getString(7);
            String mode = cursor.getString(8);
            String money = cursor.getString(9);
            String tid = cursor.getString(10);
            String mount = cursor.getString(11);
            String tipId = cursor.getString(12);
            String idea = cursor.getString(13);
            String time = cursor.getString(14);
            CaipiaoBean caipiaoBean = new CaipiaoBean();
            caipiaoBean.setMount(mount);
            caipiaoBean.setNameId(nameId);
            caipiaoBean.setZero(zero);
            caipiaoBean.setToday_money(today_money);
            caipiaoBean.setHx_name(hx_name);
            caipiaoBean.setName(name);
            caipiaoBean.setNumber(number);
            caipiaoBean.setCode_text(code_text);
            caipiaoBean.setMultiple(multiple);
            caipiaoBean.setMode(mode);
            caipiaoBean.setMoney(money);
            caipiaoBean.setTid(tid);
            caipiaoBean.setTipId(tipId);
            caipiaoBean.setIdea(idea);
            caipiaoBean.setTime(time);
            list.add(caipiaoBean);
        }
        cursor.close();
        db.close();
        return list;
    }
/*
    *//**
     * 滑动栏的所有数据
     */
    public boolean addtop(String G_P_AddTime, String G_P_AmountStep, String G_P_Bonus, String G_P_Decimal, String G_P_Id, String G_P_MaxBetsMoney,
                          String G_P_MaxBonus, String G_P_MaxBonusMode, String G_P_Maximum, String G_P_MaximumBonusRebate, String G_P_MinBetsMoney, String G_P_MinimumBonusRebate,
                          String G_P_Name, String G_P_NameId, String G_P_NumberName, String G_P_Off, String G_P_OneAmount, String G_P_RebateStep, String G_P_ReturnOff, String G_P_Rules, String G_P_Sort
            , String G_P_Text, String G_P_TypeId, String G_P_WapOff, String title) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("G_P_AddTime", G_P_AddTime);
        values.put("G_P_AmountStep", G_P_AmountStep);
        values.put("G_P_Bonus", G_P_Bonus);
        values.put("G_P_Decimal", G_P_Decimal);
        values.put("G_P_Id", G_P_Id);
        values.put("G_P_MaxBetsMoney", G_P_MaxBetsMoney);
        values.put("G_P_MaxBonus", G_P_MaxBonus);
        values.put("G_P_MaxBonusMode", G_P_MaxBonusMode);
        values.put("G_P_Maximum", G_P_Maximum);
        values.put("G_P_MaximumBonusRebate", G_P_MaximumBonusRebate);
        values.put("G_P_MinBetsMoney", G_P_MinBetsMoney);
        values.put("G_P_MinimumBonusRebate", G_P_MinimumBonusRebate);
        values.put("G_P_Name", G_P_Name);
        values.put("G_P_NameId", G_P_NameId);
        values.put("G_P_NumberName", G_P_NumberName);
        values.put("G_P_Off", G_P_Off);
        values.put("G_P_OneAmount", G_P_OneAmount);
        values.put("G_P_RebateStep", G_P_RebateStep);
        values.put("G_P_ReturnOff", G_P_ReturnOff);
        values.put("G_P_Rules", G_P_Rules);
        values.put("G_P_Sort", G_P_Sort);
        values.put("G_P_Text", G_P_Text);
        values.put("G_P_TypeId", G_P_TypeId);
        values.put("G_P_WapOff", G_P_WapOff);
        values.put("title", title);
        long result = db.insert(TABLE_TOP, null, values);
        db.close();//关闭数据库释放资源
        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

/**
     * 查找所有的
     * @return
     */
    public List<CaiPiaoNewTopBean> findAllTop() {
        SQLiteDatabase db = helper.getWritableDatabase();
        List<CaiPiaoNewTopBean> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_TOP, new String[]{"h_g_p_name", "h_g_p_id", "h_g_p_cid", "h_g_p_nid", "h_g_p_tid", "h_g_p_gid",
                "h_g_p_rid", "h_g_p_one_amount", "h_g_p_max_bet_mum", "h_g_p_bonus", "h_g_p_amount_step", "h_g_p_rebate_step", "h_g_p_decimal",
                "h_g_p_return_off", "h_g_p_introduction", "h_g_p_example", "h_g_p_max_imumbonus_rebate", "h_g_p_mini_mumbonus_rebate", "h_g_p_mini_bet_money",
                "h_g_p_max_bet_money", "h_g_p_max_bonus", "h_g_p_max_bonus_mode", "h_g_p_not_bet_code",
                "h_g_p_singled_num", "h_g_p_singled_max_bonus"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String h_g_p_name = cursor.getString(0);
            String h_g_p_id = cursor.getString(1);
            String h_g_p_cid = cursor.getString(2);
            String h_g_p_nid = cursor.getString(3);
            String h_g_p_tid = cursor.getString(4);
            String h_g_p_gid = cursor.getString(5);
            String h_g_p_rid = cursor.getString(6);
            String h_g_p_one_amount = cursor.getString(7);
            String h_g_p_max_bet_mum = cursor.getString(8);
            String h_g_p_bonus = cursor.getString(9);
            String h_g_p_amount_step = cursor.getString(10);
            String h_g_p_rebate_step = cursor.getString(11);
            String h_g_p_decimal = cursor.getString(12);
            String h_g_p_return_off = cursor.getString(13);
            String h_g_p_introduction = cursor.getString(14);
            String h_g_p_example = cursor.getString(15);
            String h_g_p_max_imumbonus_rebate = cursor.getString(16);
            String h_g_p_mini_mumbonus_rebate = cursor.getString(17);
            String h_g_p_mini_bet_money = cursor.getString(18);
            String h_g_p_max_bet_money = cursor.getString(19);
            String h_g_p_max_bonus = cursor.getString(20);
            String h_g_p_max_bonus_mode = cursor.getString(21);
            String h_g_p_not_bet_code = cursor.getString(22);
            String h_g_p_singled_num = cursor.getString(23);
            String h_g_p_singled_max_bonus = cursor.getString(23);
            CaiPiaoNewTopBean caiPiaoNewTopBean = new CaiPiaoNewTopBean();
            caiPiaoNewTopBean.setH_g_p_name(h_g_p_name);
            caiPiaoNewTopBean.setH_g_p_id(h_g_p_id);
            caiPiaoNewTopBean.setH_g_p_cid(h_g_p_cid);
            caiPiaoNewTopBean.setH_g_p_nid(h_g_p_nid);
            caiPiaoNewTopBean.setH_g_p_tid(h_g_p_tid);
            caiPiaoNewTopBean.setH_g_p_gid(h_g_p_gid);
            caiPiaoNewTopBean.setH_g_p_rid(h_g_p_rid);
            caiPiaoNewTopBean.setH_g_p_one_amount(h_g_p_one_amount);
            caiPiaoNewTopBean.setH_g_p_max_bet_mum(h_g_p_max_bet_mum);
            caiPiaoNewTopBean.setH_g_p_bonus(h_g_p_bonus);
            caiPiaoNewTopBean.setH_g_p_amount_step(h_g_p_amount_step);
            caiPiaoNewTopBean.setH_g_p_rebate_step(h_g_p_rebate_step);
            caiPiaoNewTopBean.setH_g_p_decimal(h_g_p_decimal);
            caiPiaoNewTopBean.setH_g_p_return_off(h_g_p_return_off);
            caiPiaoNewTopBean.setH_g_p_introduction(h_g_p_introduction);
            caiPiaoNewTopBean.setH_g_p_example(h_g_p_example);
            caiPiaoNewTopBean.setH_g_p_max_imumbonus_rebate(h_g_p_max_imumbonus_rebate);
            caiPiaoNewTopBean.setH_g_p_mini_mumbonus_rebate(h_g_p_mini_mumbonus_rebate);
            caiPiaoNewTopBean.setH_g_p_mini_bet_money(h_g_p_mini_bet_money);
            caiPiaoNewTopBean.setH_g_p_max_bet_money(h_g_p_max_bet_money);
            caiPiaoNewTopBean.setH_g_p_max_bonus(h_g_p_max_bonus);
            caiPiaoNewTopBean.setH_g_p_max_bonus_mode(h_g_p_max_bonus_mode);
            caiPiaoNewTopBean.setH_g_p_not_bet_code(h_g_p_not_bet_code);
            caiPiaoNewTopBean.setH_g_p_singled_num(h_g_p_singled_num);
            caiPiaoNewTopBean.setH_g_p_singled_max_bonus(h_g_p_singled_max_bonus);
            list.add(caiPiaoNewTopBean);
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 根据名字修改倍数
     */
    public void updatebei(String multiple, String hx_name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update " + TABLE_NAME + " set multiple =? where hx_name=?", new Object[]{multiple, hx_name});
        db.close();//释放资源
    }

    /**
     * 根据名字修改模式
     */
    public void updateMode(String mode, String hx_name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update " + TABLE_NAME + " set mode =? where hx_name=?", new Object[]{mode, hx_name});
        db.close();//释放资源
    }

    /**
     * 根据名字修改钱数
     */
    public void updateMoney(String money, String hx_name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update " + TABLE_NAME + " set money =? where hx_name=?", new Object[]{money, hx_name});
        db.close();//释放资源
    }

}
