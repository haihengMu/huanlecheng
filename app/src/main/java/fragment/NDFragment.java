package fragment;

/**
 * Created by Administrator on 2017/3/23.
 */

public class NDFragment extends GFragment {
    /** 标志位，标志已经初始化完成 */
    private boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    private boolean mHasLoadedOnce;

    /**
     * 重写父类的方法取消预加载
     */
    @Override
    protected void lazyLoad() {

    }
}
