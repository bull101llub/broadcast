package jp.co.cococoa.business.reserve;

import jp.co.cococoa.business.SuperBusiness;
import jp.co.cococoa.dao.ReserveDao;

public class ReserveBusiness extends SuperBusiness {

    /**
     *  予約情報初期検索処理
     *  最新の予約情報を取得
     *  @param roomNo ルームNo
     *  @return 予約情報格納Bean
     * */
    public ReserveBean init(String ownerid, String boothno){

        ReserveDao dao = new ReserveDao();
        ReserveBean bean = null;

        try {
            //ブースNoに紐付く最新の予約情報を取得
            bean = dao.getReserve(getConnection(), boothno, ownerid);
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return bean;
    }

    /**
     *  予約情報保存処理
     *  @param roomNo ルームNo
     *  @param reserveId 予約番号
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public ReserveBean save(ReserveBean reserveBean){

        ReserveDao dao = new ReserveDao();
        ReserveBean bean = null;
        try {
            //ルームNoに紐付く最新の予約情報を取得
            dao.updatetContents(getConnection(), reserveBean);
            bean = dao.getReserve(getConnection(), reserveBean.getBoothid(), reserveBean.getOwnerid());
            commit();
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return bean;
    }

    /**
     *  予約処理
     *  @param id
     *  @param pw
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public ReserveBean reserve(String ownerid, String boothid){

        ReserveDao dao = new ReserveDao();
        ReserveBean reserveBean = new ReserveBean();

        try {
          int i = dao.reserve(getConnection(), ownerid, boothid);
          commit();
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return reserveBean;
    }

    /**
     *  予約情報取消処理
     *  @param id
     *  @param pw
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public ReserveBean cancel(String ownerid, String boothid){

        ReserveDao dao = new ReserveDao();
        ReserveBean reserveBean = new ReserveBean();

        try {
          int i = dao.cancel(getConnection(), ownerid, boothid);
          commit();
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return reserveBean;
    }
}
