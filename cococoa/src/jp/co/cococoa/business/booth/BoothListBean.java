package jp.co.cococoa.business.booth;

import java.util.ArrayList;
import java.util.List;

public class BoothListBean {

    /** ルーム情報 */
    private List<BoothBean> boothList;

    /** オーナーID */
    private String ownerid;

    public List<BoothBean> getBoothList() {
        return boothList;
    }
    public void setBoothList(List<BoothBean> boothList) {
        this.boothList = boothList;
    }

    public void addBooth(BoothBean boothbean){
        if (null == this.boothList) {
        	boothList = new ArrayList<BoothBean>();
        }
        this.boothList.add(boothbean);
    }

    public String getOwnerid() {
        return ownerid;
    }
    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

}
