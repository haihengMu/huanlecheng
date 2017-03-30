package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public class NewPlayGameNameChildMode implements Serializable {
    private int line;
    private boolean is_self_pick=false;
    private List<Integer> win;
    private List<ListBean> list;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public boolean isIs_self_pick() {
        return is_self_pick;
    }

    public void setIs_self_pick(boolean is_self_pick) {
        this.is_self_pick = is_self_pick;
    }

    public List<Integer> getWin() {
        return win;
    }

    public void setWin(List<Integer> win) {
        this.win = win;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private List<String> 万位;
        private List<String> 千位;
        private List<String> 百位;
        private List<String> 十位;
        private List<String> 个位;
        private List<String> 组120;
        private List<String> 二重号;
        private List<String> 单号;
        private List<String> 三重号;
        private List<String> 四重号;
        private List<String> 组选24;
        private List<String> 组选6;
        private List<String> 组三;
        private List<String> 胆码;
        private List<String> 拖码;
        private List<String> 组六;
        private List<String> 前二组选;
        private List<String> 后二组选;
        private List<String> 前三;
        private List<String> 中三;
        private List<String> 后三;
        private List<String> 前三跨度;
        private List<String> 中三跨度;
        private List<String> 后三跨度;
        private List<String> 前二跨度;
        private List<String> 后二跨度;
        private List<String> 任三组三;
        private List<String> 任三组六;
        private List<String> 任二组选;
        private List<String> 前三直选;
        private List<String> 中三直选;
        private List<String> 后三直选;
        private List<String> 前二直选;
        private List<String> 后二直选;
        private List<String> 前三组选;
        private List<String> 中三组选;
        private List<String> 后三组选;
        private List<String> 前三尾数;
        private List<String> 中三尾数;
        private List<String> 后三尾数;
        private List<String> 一帆风顺;
        private List<String> 好事成双;
        private List<String> 三星报喜;
        private List<String> 四季发财;
        private List<String> 万千;
        private List<String> 万百;
        private List<String> 万十;
        private List<String> 万个;
        private List<String> 千百;
        private List<String> 千十;
        private List<String> 千个;
        private List<String> 百十;
        private List<String> 百个;
        private List<String> 十个;

        public List<String> get组120() {
            return 组120;
        }

        public void set组120(List<String> 组120) {
            this.组120 = 组120;
        }

        public List<String> get二重号() {
            return 二重号;
        }

        public void set二重号(List<String> 二重号) {
            this.二重号 = 二重号;
        }

        public List<String> get单号() {
            return 单号;
        }

        public void set单号(List<String> 单号) {
            this.单号 = 单号;
        }

        public List<String> get三重号() {
            return 三重号;
        }

        public void set三重号(List<String> 三重号) {
            this.三重号 = 三重号;
        }

        public List<String> get四重号() {
            return 四重号;
        }

        public void set四重号(List<String> 四重号) {
            this.四重号 = 四重号;
        }

        public List<String> get组选24() {
            return 组选24;
        }

        public void set组选24(List<String> 组选24) {
            this.组选24 = 组选24;
        }

        public List<String> get组选6() {
            return 组选6;
        }

        public void set组选6(List<String> 组选6) {
            this.组选6 = 组选6;
        }

        public List<String> get组三() {
            return 组三;
        }

        public void set组三(List<String> 组三) {
            this.组三 = 组三;
        }

        public List<String> get胆码() {
            return 胆码;
        }

        public void set胆码(List<String> 胆码) {
            this.胆码 = 胆码;
        }

        public List<String> get拖码() {
            return 拖码;
        }

        public void set拖码(List<String> 拖码) {
            this.拖码 = 拖码;
        }

        public List<String> get组六() {
            return 组六;
        }

        public void set组六(List<String> 组六) {
            this.组六 = 组六;
        }

        public List<String> get前二组选() {
            return 前二组选;
        }

        public void set前二组选(List<String> 前二组选) {
            this.前二组选 = 前二组选;
        }

        public List<String> get后二组选() {
            return 后二组选;
        }

        public void set后二组选(List<String> 后二组选) {
            this.后二组选 = 后二组选;
        }

        public List<String> get前三() {
            return 前三;
        }

        public void set前三(List<String> 前三) {
            this.前三 = 前三;
        }

        public List<String> get中三() {
            return 中三;
        }

        public void set中三(List<String> 中三) {
            this.中三 = 中三;
        }

        public List<String> get后三() {
            return 后三;
        }

        public void set后三(List<String> 后三) {
            this.后三 = 后三;
        }

        public List<String> get前三跨度() {
            return 前三跨度;
        }

        public void set前三跨度(List<String> 前三跨度) {
            this.前三跨度 = 前三跨度;
        }

        public List<String> get中三跨度() {
            return 中三跨度;
        }

        public void set中三跨度(List<String> 中三跨度) {
            this.中三跨度 = 中三跨度;
        }

        public List<String> get后三跨度() {
            return 后三跨度;
        }

        public void set后三跨度(List<String> 后三跨度) {
            this.后三跨度 = 后三跨度;
        }

        public List<String> get前二跨度() {
            return 前二跨度;
        }

        public void set前二跨度(List<String> 前二跨度) {
            this.前二跨度 = 前二跨度;
        }

        public List<String> get后二跨度() {
            return 后二跨度;
        }

        public void set后二跨度(List<String> 后二跨度) {
            this.后二跨度 = 后二跨度;
        }

        public List<String> get任三组三() {
            return 任三组三;
        }

        public void set任三组三(List<String> 任三组三) {
            this.任三组三 = 任三组三;
        }

        public List<String> get任三组六() {
            return 任三组六;
        }

        public void set任三组六(List<String> 任三组六) {
            this.任三组六 = 任三组六;
        }

        public List<String> get任二组选() {
            return 任二组选;
        }

        public void set任二组选(List<String> 任二组选) {
            this.任二组选 = 任二组选;
        }

        public List<String> get前三直选() {
            return 前三直选;
        }

        public void set前三直选(List<String> 前三直选) {
            this.前三直选 = 前三直选;
        }

        public List<String> get中三直选() {
            return 中三直选;
        }

        public void set中三直选(List<String> 中三直选) {
            this.中三直选 = 中三直选;
        }

        public List<String> get后三直选() {
            return 后三直选;
        }

        public void set后三直选(List<String> 后三直选) {
            this.后三直选 = 后三直选;
        }

        public List<String> get前二直选() {
            return 前二直选;
        }

        public void set前二直选(List<String> 前二直选) {
            this.前二直选 = 前二直选;
        }

        public List<String> get后二直选() {
            return 后二直选;
        }

        public void set后二直选(List<String> 后二直选) {
            this.后二直选 = 后二直选;
        }

        public List<String> get前三组选() {
            return 前三组选;
        }

        public void set前三组选(List<String> 前三组选) {
            this.前三组选 = 前三组选;
        }

        public List<String> get中三组选() {
            return 中三组选;
        }

        public void set中三组选(List<String> 中三组选) {
            this.中三组选 = 中三组选;
        }

        public List<String> get后三组选() {
            return 后三组选;
        }

        public void set后三组选(List<String> 后三组选) {
            this.后三组选 = 后三组选;
        }

        public List<String> get前三尾数() {
            return 前三尾数;
        }

        public void set前三尾数(List<String> 前三尾数) {
            this.前三尾数 = 前三尾数;
        }

        public List<String> get中三尾数() {
            return 中三尾数;
        }

        public void set中三尾数(List<String> 中三尾数) {
            this.中三尾数 = 中三尾数;
        }

        public List<String> get后三尾数() {
            return 后三尾数;
        }

        public void set后三尾数(List<String> 后三尾数) {
            this.后三尾数 = 后三尾数;
        }

        public List<String> get一帆风顺() {
            return 一帆风顺;
        }

        public void set一帆风顺(List<String> 一帆风顺) {
            this.一帆风顺 = 一帆风顺;
        }

        public List<String> get好事成双() {
            return 好事成双;
        }

        public void set好事成双(List<String> 好事成双) {
            this.好事成双 = 好事成双;
        }

        public List<String> get三星报喜() {
            return 三星报喜;
        }

        public void set三星报喜(List<String> 三星报喜) {
            this.三星报喜 = 三星报喜;
        }

        public List<String> get四季发财() {
            return 四季发财;
        }

        public void set四季发财(List<String> 四季发财) {
            this.四季发财 = 四季发财;
        }

        public List<String> get万千() {
            return 万千;
        }

        public void set万千(List<String> 万千) {
            this.万千 = 万千;
        }

        public List<String> get万百() {
            return 万百;
        }

        public void set万百(List<String> 万百) {
            this.万百 = 万百;
        }

        public List<String> get万十() {
            return 万十;
        }

        public void set万十(List<String> 万十) {
            this.万十 = 万十;
        }

        public List<String> get万个() {
            return 万个;
        }

        public void set万个(List<String> 万个) {
            this.万个 = 万个;
        }

        public List<String> get千百() {
            return 千百;
        }

        public void set千百(List<String> 千百) {
            this.千百 = 千百;
        }

        public List<String> get千十() {
            return 千十;
        }

        public void set千十(List<String> 千十) {
            this.千十 = 千十;
        }

        public List<String> get千个() {
            return 千个;
        }

        public void set千个(List<String> 千个) {
            this.千个 = 千个;
        }

        public List<String> get百十() {
            return 百十;
        }

        public void set百十(List<String> 百十) {
            this.百十 = 百十;
        }

        public List<String> get百个() {
            return 百个;
        }

        public void set百个(List<String> 百个) {
            this.百个 = 百个;
        }

        public List<String> get十个() {
            return 十个;
        }

        public void set十个(List<String> 十个) {
            this.十个 = 十个;
        }

        public List<String> get千位() {
            return 千位;
        }

        public void set千位(List<String> 千位) {
            this.千位 = 千位;
        }

        public List<String> get百位() {
            return 百位;
        }

        public void set百位(List<String> 百位) {
            this.百位 = 百位;
        }

        public List<String> get十位() {
            return 十位;
        }

        public void set十位(List<String> 十位) {
            this.十位 = 十位;
        }

        public List<String> get个位() {
            return 个位;
        }

        public void set个位(List<String> 个位) {
            this.个位 = 个位;
        }

        public List<String> get万位() {
            return 万位;
        }

        public void set万位(List<String> 万位) {
            this.万位 = 万位;
        }
    }
}
