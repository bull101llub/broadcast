package jp.co.cococoa.business.msg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageListBean {

	private String ownerid;
	private String boothid;
	private String broadcastid;
	private String msgid;
	private String contentid;
	List<MessageBean> list;
    Map<String, String>contentMap = new HashMap<String, String>();

	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
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
	public String getBroadcastid() {
		return broadcastid;
	}
	public void setBroadcastid(String broadcastid) {
		this.broadcastid = broadcastid;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public List<MessageBean> getList() {
		return list;
	}
	public void setList(List<MessageBean> list) {
		this.list = list;
	}
	public void addList(MessageBean bean) {
		if (null == list) {
			list = new ArrayList<MessageBean>();
		}
		this.list.add(bean);
	}
	public Map<String, String> getContentMap() {
		return contentMap;
	}
	public void setContentMap(Map<String, String> contentMap) {
		this.contentMap = contentMap;
	}


}
