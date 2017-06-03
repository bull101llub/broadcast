package jp.co.cococoa.business.rank;

import java.util.List;

import jp.co.cococoa.business.SuperBusiness;
import jp.co.cococoa.dao.RankDao;

public class RankBusiness extends SuperBusiness {

	/**
     *  ランク情報検索処理
     *  @param ownerid
     *  @param boothid
     *  @param broadcastid
     *  @return
     * */
    public RankBean find(String ownerid, String boothid, String broadcastid){
    	RankDao dao = new RankDao();
    	RankBean rankBean = new RankBean();
        List<RankUserBean> beanList = null;

        try {
        	if (null == broadcastid || broadcastid == "") {
                beanList = dao.getRankList(getConnection(), ownerid, boothid);
        	} else {
            	beanList = dao.getRankList(getConnection(), ownerid, boothid, broadcastid);
        	}
            rankBean.setRankUserBeanList(beanList);
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return rankBean;
    }
}
