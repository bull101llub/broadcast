package jp.co.cococoa.business.postmessage;

import java.util.List;

public class PostMessageBean {
	/** オーナーID */
	private String ownerid;
	/** ブースID */
	private String boothid;
	/** ブース名 */
	private String boothnm;
	/** 放送ID */
	private String broadcastid;
    /** コンテンツリスト */
    private List<PostMessageContentsBean>  postMessageContentsList;

	public String getBoothnm() {
		return boothnm;
	}
	public void setBoothnm(String boothnm) {
		this.boothnm = boothnm;
	}	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getBoothid() {
		return boothid;
	}
	public void setBoothid(String boothid) {
		this.boothid = boothid;
	}
	public String getBroadcastid() {
		return broadcastid;
	}
	public void setBroadcastid(String broadcastid) {
		this.broadcastid = broadcastid;
	}
	public List<PostMessageContentsBean> getPostMessageContentsList() {
		return postMessageContentsList;
	}
	public void setPostMessageContentsList(List<PostMessageContentsBean> postMessageContentsList) {
		this.postMessageContentsList = postMessageContentsList;
	}
	public void addPostMessageContentsList(PostMessageContentsBean postMessageContentsBean) {
		this.postMessageContentsList.add(postMessageContentsBean);
	}


}
