package jp.co.cococoa.business.msg;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.cococoa.business.SuperBusiness;
import jp.co.cococoa.business.reserve.ReserveBean;
import jp.co.cococoa.business.reserve.ReserveContentsBean;
import jp.co.cococoa.dao.MessageDao;
import jp.co.cococoa.dao.ReserveDao;

public class MessageBusiness extends SuperBusiness {
    /**
     *  メッセージ検索
     *  @param id
     *  @param pw
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public MessageListBean find(String ownerid, String boothid, String broadcastid, String contentsid){

        MessageDao dao = new MessageDao();
        ReserveDao rdao = new ReserveDao();
        List<MessageBean> beanList = null;

        MessageListBean bean = new MessageListBean();
        bean.setOwnerid(ownerid);
        bean.setBoothid(boothid);
        bean.setBroadcastid(broadcastid);
        bean.setContentid(contentsid);

        try {
            //メッセージを取得
        	if (null == contentsid || "".equals(contentsid)) {
                beanList = dao.find(getConnection(), ownerid, boothid, broadcastid);
        	} else {
                beanList = dao.find(getConnection(), ownerid, boothid, broadcastid, contentsid);
        	}
            bean.setList(beanList);

            ReserveBean rbean = rdao.getReserve(getConnection(), boothid, ownerid);
            List<ReserveContentsBean> list = rbean.getContentsList();
            if(null != list && list.size() > 0) {
                Map<String, String> map = new HashMap<String, String>();
                Iterator<ReserveContentsBean> it = list.iterator();
                while(it.hasNext()) {
                	ReserveContentsBean cbean = it.next();
                	map.put(cbean.getContentid(), cbean.getTitle());
                }
                bean.setContentMap(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return bean;
    }

    /**
     *  メッセージ登録
     *  @param id
     *  @param pw
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public int getRegist(MessageListBean meeageListBean){

        MessageDao dao = new MessageDao();
        int count = 0;

        try {
            //ブースNoに紐付く最新の予約情報を取得
            count = dao.create(getConnection(), meeageListBean);
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return count;
    }

    /**
     *  ポイント更新
     *  @param ownerid
     *  @param boothid
     *  @param broadcastid
     *  @param msgid
     *  @param point
     *  @return 更新件数
     * */
    public int putPoint(String ownerid, String boothid, String broadcastid, String msgid, String point){

        MessageDao dao = new MessageDao();
        int count = 0;

        try {
            //ブースNoに紐付く最新の予約情報を取得
            count = dao.putPoint(getConnection(), ownerid, boothid, broadcastid, msgid, point);
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return count;
    }
}
