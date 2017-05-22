package jp.co.cococoa.business.login;

import jp.co.cococoa.business.SuperBusiness;
import jp.co.cococoa.dao.LoginDao;

public class LoginBusiness extends SuperBusiness {
    /**
     * ログイン処理
     *  @param id
     *  @param pw
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public UserBean login(String userid, String password){
        LoginDao dao = new LoginDao();
        UserBean loginBean = new UserBean();

        try {
            loginBean = dao.getUser(getConnection(), userid, password);

            //ユーザーIDが取得できている場合は認証OK
            if (null != loginBean.getUserid()) {
            	loginBean.setExists(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            rollback();
        } finally {
            close();
        }
        return loginBean;
    }

    /**
     * ユーザー情報登録
     *  @param id
     *  @param pw
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public UserBean create(String userid, String password){
        LoginDao dao = new LoginDao();
        UserBean bean = new UserBean();
        try {
            bean = dao.createUser(getConnection(), userid, password);

            //ユーザーIDが取得できている場合は認証OK
            if (null != bean.getUserid()) {
                bean.setExists(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            rollback();
        } finally {
            close();
        }
        return bean;
    }

    /**
     * パスワード変更
     *  @param userid ユーザーID
     *  @param passwordOld 旧パスワード
     *  @param passwordNew 新パスワード
     *  @return true:ログイン成功 false:ログイン失敗
     * */
    public int passwordChange(String userid, String passwordOld, String passwordNew){
        LoginDao dao = new LoginDao();
        int count = 0;

        try {
            count = dao.passwordChange(getConnection(), userid, passwordOld, passwordNew);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            rollback();
        } finally {
            close();
        }
        return count;
    }
}
