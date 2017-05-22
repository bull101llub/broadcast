package jp.co.cococoa.business.postmessage;

import java.util.Iterator;
import java.util.List;

import jp.co.cococoa.business.SuperBusiness;
import jp.co.cococoa.business.booth.BoothBean;
import jp.co.cococoa.dao.BoothDao;
import jp.co.cococoa.dao.PostMessageDao;

public class PostMessageBusiness extends SuperBusiness {

    /**
     *  投稿用コンテンツ検索処理
     *  最新の予約情報を取得
     *  @param roomNo ルームNo
     *  @return 予約情報格納Bean
     * */
    public PostMessageBean init(String ownerid, String boothno, String broadcastid){

    	PostMessageDao dao = new PostMessageDao();
    	BoothDao boothdao = new BoothDao();
        PostMessageBean bean = null;

        try {
            //ブースNoに紐付く最新の予約情報を取得
            bean = dao.getContents(getConnection(), ownerid, boothno, broadcastid);

            List<BoothBean> list = boothdao.getBoothList(getConnection(), ownerid);
            Iterator<BoothBean> it = list.iterator();
            while(it.hasNext()) {
            	BoothBean booth = it.next();
            	bean.setBoothnm(booth.getBootname());
            }

        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return bean;
    }
}
