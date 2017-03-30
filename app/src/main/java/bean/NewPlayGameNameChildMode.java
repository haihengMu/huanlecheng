package bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public class NewPlayGameNameChildMode implements Serializable {
    private int line;
    private boolean is_self_pick;
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

        public List<String> get万位() {
            return 万位;
        }

        public void set万位(List<String> 万位) {
            this.万位 = 万位;
        }
    }
}
