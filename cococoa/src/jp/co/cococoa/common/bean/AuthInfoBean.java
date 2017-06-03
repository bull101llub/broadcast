package jp.co.cococoa.common.bean;

public class AuthInfoBean {
	private String userid;
	private String boothid;
	private String broadcastid;
	private String boothname;

	public String getBoothname() {
		return boothname;
	}
	public void setBoothname(String boothname) {
		this.boothname = boothname;
	}
	public String getBroadcastid() {
		return broadcastid;
	}
	public void setBroadcastid(String broadcastid) {
		this.broadcastid = broadcastid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBoothid() {
		return boothid;
	}
	public void setBoothid(String boothid) {
		this.boothid = boothid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	private String ownerid;
}
