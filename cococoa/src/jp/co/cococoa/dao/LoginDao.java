package jp.co.cococoa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.cococoa.business.login.UserBean;

public class LoginDao extends SuperDao {

    /*  ���[�U�[���擾
     *  @param userid   ���[�U�[ID
     *  @param password �p�X���[�h
     *  @return ���[�U�[���
     * */
    public UserBean getUser(Connection connection, String userid, String password) throws SQLException {
        UserBean bean = new UserBean();

        try {

            // �ŐV�̗\�񃌃R�[�h���擾����
            StringBuilder sql = new StringBuilder();
                          sql.append(" SELECT ");
                          sql.append("     N1_USER.USERID, ");
                          sql.append("     N1_USER.PASSWORD ");
                          sql.append(" FROM ");
                          sql.append("     N1_USER ");
                          sql.append(" WHERE ");
                          sql.append("     N1_USER.USERID = ? AND ");
                          sql.append("     N1_USER.PASSWORD = ? ");

            String[] paramArray = {userid, password};
            List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

            //�Y�����[�U�[�����݂��Ȃ��ꍇ
            if(null == resultList || resultList.size() == 0) {
               return bean;
            }
            Iterator<Map<String, String>> it = resultList.iterator();
            while(it.hasNext()) {
                Map<String, String> map = it.next();
                bean.setUserid(map.get("userid"));
                bean.setPassword(map.get("password"));
            }

        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }

    /**
     *  ���[�U�[���o�^
     *  @param userid   ���[�U�[ID
     *  @param password �p�X���[�h
     *  @return �o�^����
     * */
    public UserBean createUser(Connection connection,  String userid, String password) throws SQLException {
        UserBean bean = new UserBean();

        try {
            StringBuilder sql = new StringBuilder();
                          sql.append("INSERT INTO N1_USER  ");
                          sql.append("    (USERID, PASSWORD, CREATEYMD)  ");
                          sql.append("VALUES ");
                          sql.append("    (?, ?,Format(now(),yyyymmddHHmmss)) ");

            String[] paramArray = {userid, password};
            int count = executeUpdate(connection, sql.toString(), paramArray);
            bean.setUserid(userid);
            bean.setPassword(password);

        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }

    /**
     *  �p�X���[�h�ύX
     *  @param userid ���[�U�[ID
     *  @param passwordOld ���p�X���[�h
     *  @param passwordNew �V�p�X���[�h
     *  @return boolean true:�ύX���� false:�ύX���s
     * */
    public int passwordChange(Connection connection, String userid, String passwordOld, String passwordNew) throws SQLException {
        int count = 0;

        try {
            StringBuilder sql = new StringBuilder();
                          sql.append(" UPDATE N1_USER SET ");
                          sql.append("     PASSWORD = ?, ");
                          sql.append("     UPDATEYMD = Format(now(),yyyymmddHHmmss) ");
                          sql.append(" WHERE ");
                          sql.append("     N1_USER.USERID = ? AND ");
                          sql.append("     N1_USER.PASSWORD = ? ");

            String[] paramArray = {passwordNew, userid, passwordOld};
            count = executeUpdate(connection, sql.toString(), paramArray);

        } catch (SQLException e) {
            throw e;
        }
        return count;
    }

}
