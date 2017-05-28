package jp.co.cococoa.business.booth;

import jp.co.cococoa.business.SuperBusiness;
import jp.co.cococoa.dao.BoothDao;

public class BoothBusiness extends SuperBusiness {


	/**
     *  ブース情報検索処理
     *  @param ownerid オーナーID
     *  @param name
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public BoothListBean find(String ownerid){
        BoothDao dao = new BoothDao();
        BoothListBean beanList = new BoothListBean();
        beanList.setOwnerid(ownerid);
        try {
            beanList.setBoothList(dao.getBoothList(getConnection(), ownerid));
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return beanList;
    }

    /**
     *  ブース情報登録処理
     *  @param boothname   ブース名
     *  @param ownerid     オーナーID
     *  @param description 説明
     *  @return 登録結果
     * */
    public BoothBean create(String boothname, String ownerid, String description){
        BoothDao dao = new BoothDao();
        BoothBean bean = new BoothBean();

        try {
            bean = dao.createBooth(getConnection(), boothname, ownerid, description);
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return bean;
    }

	/**
     *  ブース情報更新
     *  @param boothid
     *  @param boothname
     *  @param description
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public BoothBean update(String boothid, String boothname, String description){
        BoothDao dao = new BoothDao();
        BoothBean bean = new BoothBean();

        try {
            bean = dao.updateBooth(getConnection(), boothid, boothname, description);
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return bean;
    }

	/**
     *  ブース情報更新
     *  @param boothid
     *  @param boothname
     *  @param description
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public int delete(String boothid){
        BoothDao dao = new BoothDao();
        BoothBean bean = new BoothBean();
        int cnt = 0;
        try {
            cnt = dao.deleteBooth(getConnection(), boothid);
        } catch (Exception e) {
            e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return cnt;
    }
}
