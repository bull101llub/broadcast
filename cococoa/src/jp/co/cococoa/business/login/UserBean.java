package jp.co.cococoa.business.login;

public class UserBean {
    /** 認証結果 */
    private boolean exists;
    /** ユーザーID */
    private String userid;
    /** パスワード */
    private String password;
	public boolean isExists() {
		return exists;
	}
	public void setExists(boolean exists) {
		this.exists = exists;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
