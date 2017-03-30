package bean;

public class UserResponse {
	
	private String error="";
	private String msg;
	private String U_Head;
	private String U_RebateA;
	private String U_RebateB;

	public String getU_Head() {
		return U_Head;
	}
	public void setU_Head(String u_Head) {
		U_Head = u_Head;
	}
	public String getU_RebateA() {
		return U_RebateA;
	}
	public void setU_RebateA(String u_RebateA) {
		U_RebateA = u_RebateA;
	}
	public String getU_RebateB() {
		return U_RebateB;
	}
	public void setU_RebateB(String u_RebateB) {
		U_RebateB = u_RebateB;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	String U_UserName;
    String U_Money;
	public String getU_UserName() {
		return U_UserName;
	}
	public void setU_UserName(String u_UserName) {
		U_UserName = u_UserName;
	}
	public String getU_Money() {
		return U_Money;
	}
	public void setU_Money(String u_Money) {
		U_Money = u_Money;
	}
    
}
