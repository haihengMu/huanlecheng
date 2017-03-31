package bean;

/**
 * Created by Mhc on 2017/3/31.
 */

public class FirstEvent {
    private String mMsg;
    private String gameId;


    private int mlistViewPosition;
    private boolean isChecked;

    public String getmMsg() {
        return mMsg;
    }

    public void setmMsg(String mMsg) {
        this.mMsg = mMsg;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getMlistViewPosition() {
        return mlistViewPosition;
    }

    public void setMlistViewPosition(int mlistViewPosition) {
        this.mlistViewPosition = mlistViewPosition;
    }

    @Override
    public String toString() {
        return "FirstEvent{" +
                "mMsg='" + mMsg + '\'' +
                ", gameId='" + gameId + '\'' +
                ", mlistViewPosition=" + mlistViewPosition +
                ", isChecked=" + isChecked +
                '}';
    }
}
