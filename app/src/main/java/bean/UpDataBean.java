package bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/30.
 */

public class UpDataBean implements Serializable {

    /**
     * note : 新增自动升级检测功能新增分享功能演示页面 新增推送功能演示页面
     * title : 乐丰国际版本更新
     * url : https://www.pgyer.com/lfgj_android
     * version : 1.0
     */

    private AndroidBean Android;
    /**
     * Android : {"note":"新增自动升级检测功能新增分享功能演示页面 新增推送功能演示页面 ","title":"乐丰国际版本更新","url":"https://www.pgyer.com/lfgj_android","version":"1.0"}
     * appid : lefun2.com
     * iOS : {"note":"修改了绑定银行卡闪退的Bug.","title":"乐丰国际版本更新","url":"https://www.pgyer.com/lfgj_ios","version":"1.4"}
     */

    private String appid;
    /**
     * note : 修改了绑定银行卡闪退的Bug.
     * title : 乐丰国际版本更新
     * url : https://www.pgyer.com/lfgj_ios
     * version : 1.4
     */

    private IOSBean iOS;

    public AndroidBean getAndroid() {
        return Android;
    }

    public void setAndroid(AndroidBean android) {
        Android = android;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public IOSBean getiOS() {
        return iOS;
    }

    public void setiOS(IOSBean iOS) {
        this.iOS = iOS;
    }
}

