package jp.co.cococoa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.cococoa.business.booth.BoothBean;

public class BoothDao extends SuperDao {

    public List<BoothBean> getBoothList(Connection connection,
                                             String ownerid) throws SQLException {

        List<BoothBean> beanList = new ArrayList<BoothBean>();
        try {

            StringBuilder sql = new StringBuilder();
                          sql.append("SELECT ");
                          sql.append("    N1_BOOTH.BOOTHID, ");
                          sql.append("    N1_BOOTH.BOOTHNM, ");
                          sql.append("    N1_BOOTH.OWNERID, ");
                          sql.append("    N1_BOOTH.DESCRIPTION, ");
                          sql.append("    N1_BOOTH.DELFLG ");
                          sql.append("FROM ");
                          sql.append("    N1_BOOTH ");
                          sql.append("WHERE ");
                          sql.append("    N1_BOOTH.OWNERID = ? AND ");
                          sql.append("    N1_BOOTH.DELFLG = 0 ");
                          sql.append("ORDER BY  ");
                          sql.append("    CREATEYMD ");
            String[] paramArray = {ownerid};
            List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

            if(null == resultList || resultList.size() == 0) {
               return beanList;
            }
            Iterator<Map<String, String>> it = resultList.iterator();
            while(it.hasNext()) {
                BoothBean bean = new BoothBean();

                Map<String, String> map = it.next();
                bean.setBoothid(map.get("boothid"));
                bean.setBootname(map.get("boothnm"));
                bean.setDescription(map.get("description"));

                beanList.add(bean);
            }
        } catch (SQLException e) {
            throw e;
        }
        return beanList;
    }

    /**
     *  �u�[�X���o�^
     *  @param String boothname �u�[�X��
     *  @param String ownerid   �I�[�i�[ID
     *  @param String description ����
     *  @return �o�^����
     * */
    public BoothBean createBooth(Connection connection,
                                                  String boothname,
                                                  String ownerid,
                                                  String description) throws SQLException {
        int count = 0;
        BoothBean bean = null;
        String boothid = "A" + "20170519162432123";
        try {
            StringBuilder sql = new StringBuilder();
                          sql.append("INSERT INTO N1_BOOTH ( ");
                          sql.append("    BOOTHID, ");
                          sql.append("    BOOTHNM, ");
                          sql.append("    OWNERID, ");
                          sql.append("    DESCRIPTION, ");
                          sql.append("    DELFLG, ");
                          sql.append("    CREATEYMD ");
                          sql.append(") VALUES ( ");
                          sql.append("    ?, ");
                          sql.append("    ?, ");
                          sql.append("    ?, ");
                          sql.append("    ?, ");
                          sql.append("    0, ");
                          sql.append("    to_char(current_timestamp,'YYYYMMDDHH24MMSSMS')) ");

            String[] paramArray = {boothid, boothname, ownerid, description};
            count = executeUpdate(connection, sql.toString(), paramArray);
            if (count > 0) {
                bean = new BoothBean();
                bean.setBoothid(boothid);
                bean.setBootname(boothname);
                bean.setDescription(description);
            }
        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }

    /**
     *  �u�[�X���X�V
     *  @param String boothid   �u�[�XID
     *  @param String boothname �u�[�X��
     *  @param String description ����
     *  @return boolean true:�ύX���� false:�ύX���s
     * */
    public BoothBean updateBooth(Connection connection, String boothid,
                                                        String boothname,
                                                        String description) throws SQLException {
        int count = 0;
        BoothBean bean = null;

        try {
            StringBuilder sql = new StringBuilder();
                          sql.append("UPDATE N1_BOOTH SET ");
                          sql.append("    BOOTHNM = ?, ");
                          sql.append("    DESCRIPTION = ?, ");
                          sql.append("    UPDATEYMD = to_char(current_timestamp,'YYYYMMDDHH24MMSSMS') ");
                          sql.append("WHERE ");
                          sql.append("    BOOTHID = ? AND ");
                          sql.append("    DELFLG = 0 ");


            String[] paramArray = {boothname, description, boothid};

            count = executeUpdate(connection, sql.toString(), paramArray);
            if (count > 0) {
                bean = new BoothBean();
                bean.setBoothid(boothid);
                bean.setBootname(boothname);
                bean.setDescription(description);
            }
        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }

    /**
     *  �u�[�X���폜
     *  @param boothid �u�[�XID
     *  @return boolean true:�ύX���� false:�ύX���s
     * */
    public int deleteBooth(Connection connection,  String boothid) throws SQLException {
        int count = 0;
        try {
            StringBuilder sql = new StringBuilder();
                          sql.append("UPDATE N1_BOOTH SET ");
                          sql.append("    DELFLG = 1, ");
                          sql.append("    UPDATEYMD = to_char(current_timestamp,'YYYYMMDDHH24MMSSMS') ");
                          sql.append("WHERE ");
                          sql.append("    BOOTHID = ? AND ");
                          sql.append("    DELFLG = 0 ");

            String[] paramArray = {boothid};

            count = executeUpdate(connection, sql.toString(), paramArray);
        } catch (SQLException e) {
            throw e;
        }
        return count;
    }
}
