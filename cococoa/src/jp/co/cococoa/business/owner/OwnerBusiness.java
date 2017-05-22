package jp.co.cococoa.business.owner;

import jp.co.cococoa.business.SuperBusiness;
import jp.co.cococoa.dao.OwnerDao;

public class OwnerBusiness extends SuperBusiness {

	/**
     *  オーナー情報検索処理
     *  @param id
     *  @param name
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public OwnerBean find(String userid){
        OwnerDao dao = new OwnerDao();
        OwnerBean bean = null;

        try {
            bean = dao.getOwner(getConnection(), userid);
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return bean;
    }

	/**
     *  オーナー情報登録処理
     *  @param userid      ユーザーID
     *  @param ownerid     オーナーID
     *  @param ownername   オーナー名
     *  @param description 説明
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public OwnerBean create(String userid, String ownerid, String ownername, String description){
        OwnerDao dao = new OwnerDao();
        OwnerBean bean = null;

        try {
            bean = dao.createOwner(getConnection(), userid, ownerid, ownername, description);
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
        return bean;
    }

    /**
     *  オーナー情報保存処理
     *  @param id
     *  @param name
     *  @param comment
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public OwnerBean update(String userid, String ownerid, String ownername, String description){
        OwnerDao dao = new OwnerDao();
        OwnerBean bean = new OwnerBean();

        try {
            bean = dao.updateOwner(getConnection(), userid, ownerid, ownername, description);
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
    	return bean;
    }

    /**
     *  オーナー情報保存処理
     *  @param id
     *  @param name
     *  @param comment
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public int delete(String userid, String ownerid, String ownername){
        OwnerDao dao = new OwnerDao();
        int count = 0;

        try {
        	count = dao.deleteOwner(getConnection(), userid, ownerid);
        } catch (Exception e) {
        	e.printStackTrace();
            rollback();
        } finally {
            close();
        }
    	return count;
    }
}
