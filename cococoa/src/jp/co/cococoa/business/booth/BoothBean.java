package jp.co.cococoa.business.booth;

public class BoothBean {

    /** ブースID */
    private String boothid;
    /** ブース名 */
    private String bootname;

    public String getBoothid() {
		return boothid;
	}
	public void setBoothid(String boothid) {
		this.boothid = boothid;
	}
	public String getBootname() {
		return bootname;
	}
	public void setBootname(String bootname) {
		this.bootname = bootname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/** ブース説明 */
    private String description;

}
