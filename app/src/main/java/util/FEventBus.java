package util;

/**
 * Created by Administrator on 2017/3/29.
 */

public class FEventBus {
    private String name;
    private String number;

    public FEventBus(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
