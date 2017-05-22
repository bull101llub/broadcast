package jp.co.cococoa.business.postmessage;

public class PostMessageContentsBean {
    private String ownerid;
    private String boothid;
    private String broadcastid;
    private String contentid;
    private int    sortno;
    private String cntributflg;
    private String title;
    private String description;
    private String script;
    private String delflg;
    private String createymd;
    private String updateymd;

    public String getBroadcastid() {
		return broadcastid;
	}
	public void setBroadcastid(String broadcastid) {
		this.broadcastid = broadcastid;
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
    public String getContentid() {
        return contentid;
    }
    public void setContentid(String contentid) {
        this.contentid = contentid;
    }
    public int getSortno() {
        return sortno;
    }
    public void setSortno(int sortno) {
        this.sortno = sortno;
    }
    public String getCntributflg() {
        return cntributflg;
    }
    public void setCntributflg(String cntributflg) {
        this.cntributflg = cntributflg;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getScript() {
        return script;
    }
    public void setScript(String script) {
        this.script = script;
    }
    public String getDelflg() {
        return delflg;
    }
    public void setDelflg(String delflg) {
        this.delflg = delflg;
    }
    public String getCreateymd() {
        return createymd;
    }
    public void setCreateymd(String createymd) {
        this.createymd = createymd;
    }
    public String getUpdateymd() {
        return updateymd;
    }
    public void setUpdateymd(String updateymd) {
        this.updateymd = updateymd;
    }

}
