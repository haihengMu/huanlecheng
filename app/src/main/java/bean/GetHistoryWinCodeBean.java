package bean;

/**
 * 开奖信息的bean
 */
public class GetHistoryWinCodeBean {
	/**
	 * code : 1,9,3,8,5
	 * issue : 808794
	 * native_code : 02,07,10,12,13,15,18,23,30,39,46,48,49,51,52,56,57,60,63,65 01
	 * time : 21:30:22
	 */

	private String code;
	private String issue;
	private String native_code;
	private String time;
	//private String error="";

/*	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}*/

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getNative_code() {
		return native_code;
	}

	public void setNative_code(String native_code) {
		this.native_code = native_code;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
