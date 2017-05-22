package jp.co.cococoa.business.reserve;

import java.util.ArrayList;
import java.util.List;

public class ReserveBean {
    /** 放送ID */
    private String broadcastid;
	/** オーナーID */
	private String ownerid;
	/** ルームID */
	private String boothid;
    /** コンテンツリスト */
	private List<ReserveContentsBean> contentsList;

	public String getBroadcastid() {
		return broadcastid;
	}
	public void setBroadcastId(String broadcastId) {
		this.broadcastid = broadcastId;
	}
	public String getOwnerid() {
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
	public List<ReserveContentsBean> getContentsList() {
		return contentsList;
	}
	public void setContentsList(List<ReserveContentsBean> contentsList) {
		this.contentsList = contentsList;
	}
	public void addContentsList(ReserveContentsBean contentsBean) {
		if (null == this.contentsList) {
			this.contentsList = new ArrayList<ReserveContentsBean>();
		}
		this.contentsList.add(contentsBean);
	}
}
