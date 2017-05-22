package jp.co.cococoa.business.owner;

public class OwnerBean {
	/** ユーザーID */
    private String userid;
    /** オーナーID */
    private String ownerid;
    /** オーナー名 */
    private String ownername;
    /** オーナーコメント */
    private String description;

    public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


}
