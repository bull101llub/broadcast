package jp.co.cococoa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.cococoa.business.owner.OwnerBean;

public class OwnerDao extends SuperDao {

    /*  �I�[�i�[���擾
     *  @param userid   ���[�U�[ID
     *  @return ���[�U�[���
     * */
    public OwnerBean getOwner(Connection connection, String userid) throws SQLException {
        OwnerBean bean = new OwnerBean();

        try {

            StringBuilder sql = new StringBuilder();
                          sql.append("SELECT ");
                          sql.append("    N1_OWNER.USERID, ");
                          sql.append("    N1_OWNER.OWNERID, ");
                          sql.append("    N1_OWNER.OWNERNAME, ");
                          sql.append("    N1_OWNER.DESCRIPTION, ");
                          sql.append("    N1_OWNER.DELFLG ");
                          sql.append("FROM ");
                          sql.append("    N1_OWNER ");
                          sql.append("WHERE ");
                          sql.append("    N1_OWNER.USERID = ? AND");
                          sql.append("    N1_OWNER.DELFLG = 0 ");

            String[] paramArray = {userid};
            List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

            //�Y�����[�U�[�����݂��Ȃ��ꍇ
            if(null == resultList || resultList.size() == 0) {
               return bean;
            }
            Iterator<Map<String, String>> it = resultList.iterator();
            while(it.hasNext()) {
                Map<String, String> map = it.next();
                bean.setUserid(map.get("userid"));
                bean.setOwnerid(map.get("ownerid"));
                bean.setOwnername(map.get("ownername"));
                bean.setDescription(map.get("description"));
            }

        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }

    /**
     *  �I�[�i�[���o�^
     *  @param String userid  ���[�U�[ID
     *  @param String ownerid �I�[�i�[ID
     *  @param String oname   �I�[�i�[��
     *  @param String description ����
     *  @return �o�^����
     * */
    public OwnerBean createOwner(Connection connection,
                                                  String userid,
                                                  String ownerid,
                                                  String oname,
                                                  String description) throws SQLException {
        int count = 0;
        OwnerBean bean = null;

        try {
            StringBuilder sql = new StringBuilder();
                          sql.append("INSERT INTO N1_OWNER( ");
                          sql.append("    USERID, ");
                          sql.append("    OWNERID, ");
                          sql.append("    OWNERNAME, ");
                          sql.append("    DESCRIPTION, ");
                          sql.append("    DELFLG, ");
                          sql.append("    CREATEYMD) ");
                          sql.append(") VALUES ( ");
                          sql.append("?, ");
                          sql.append("?, ");
                          sql.append("?, ");
                          sql.append("?, ");
                          sql.append("0, ");
                          sql.append("to_char(current_timestamp,'YYYYMMDDHH24MMSSMS') ");
                          sql.append(") ");

            String[] paramArray = {userid, ownerid, oname, description};
            count = executeUpdate(connection, sql.toString(), paramArray);
            if (count > 0) {
                bean = new OwnerBean();
                bean.setUserid(userid);
                bean.setOwnerid(ownerid);
                bean.setOwnername(oname);
                bean.setDescription(description);
            }
        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }

    /**
     *  �I�[�i�[���X�V
     *  @param userid ���[�U�[ID
     *  @param ownerid �I�[�i�[ID
     *  @param ownername �I�[�i�[��
     *  @param description ����
     *  @return boolean true:�ύX���� false:�ύX���s
     * */
    public OwnerBean updateOwner(Connection connection, String userid,
                                                        String ownerid,
                                                        String ownername,
                                                        String description) throws SQLException {
        int count = 0;
        OwnerBean bean = null;

        try {
            StringBuilder sql = new StringBuilder();
                          sql.append("UPDATE ");
                          sql.append("    N1_OWNER ");
                          sql.append("SET ");
                          sql.append("    N1_OWNER.OWNERNAME = ?, ");
                          sql.append("    N1_OWNER.DESCRIPTION = ?, ");
                          sql.append("    N1_OWNER.UPDATEYMD = to_char(current_timestamp,'YYYYMMDDHH24MMSSMS') ");
                          sql.append("WHERE ");
                          sql.append("    N1_OWNER.USERID = ? AND ");
                          sql.append("    N1_OWNER.OWNERID = ? AND ");
                          sql.append("    N1_OWNER.DELFLG = 0 ");

            String[] paramArray = {ownername, description, userid, ownerid};

            count = executeUpdate(connection, sql.toString(), paramArray);
            if (count > 0) {
                bean = new OwnerBean();
                bean.setUserid(userid);
                bean.setOwnerid(ownerid);
                bean.setOwnername(ownername);
                bean.setDescription(description);
            }
        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }

    /**
     *  �I�[�i�[���폜
     *  @param userid ���[�U�[ID
     *  @param ownerid �I�[�i�[ID
     *  @return boolean true:�ύX���� false:�ύX���s
     * */
    public int deleteOwner(Connection connection,  String userid,
                                                      String ownerid) throws SQLException {
        int count = 0;

        try {
            StringBuilder sql = new StringBuilder();
                          sql.append("UPDATE ");
                          sql.append("    N1_OWNER ");
                          sql.append("SET ");
                          sql.append("    N1_OWNER.DELFLG = 1, ");
                          sql.append("    N1_OWNER.UPDATEYMD = to_char(current_timestamp,'YYYYMMDDHH24MMSSMS') ");
                          sql.append("WHERE ");
                          sql.append("    N1_OWNER.USERID = ? AND ");
                          sql.append("    N1_OWNER.OWNERID = ? AND ");
                          sql.append("    N1_OWNER.DELFLG = 0 ");

            String[] paramArray = {userid, ownerid};

            count = executeUpdate(connection, sql.toString(), paramArray);
        } catch (SQLException e) {
            throw e;
        }
        return count;
    }
}
