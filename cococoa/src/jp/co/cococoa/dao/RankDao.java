package jp.co.cococoa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.cococoa.business.rank.RankUserBean;

public class RankDao extends SuperDao {


    public List<RankUserBean> getRankList(Connection connection,
    		String ownerid, String boothid, String broadcastid) throws SQLException {
        List<RankUserBean> beanList = new ArrayList<RankUserBean>();
        try {

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("    ownerid, ");
            sql.append("    boothid, ");
            sql.append("    broadcastid, ");
            sql.append("    postid, ");
            sql.append("    postname, ");
            sql.append("    COUNT(postname) As total, ");
            sql.append("    SUM(point) As point ");
            sql.append("FROM ");
            sql.append("    n1_msg ");
            sql.append("WHERE ");
            sql.append("    delflg = 0 AND ");
            sql.append("    ownerid = ? AND ");
            sql.append("    boothid = ? AND ");
            sql.append("    broadcastid = ? ");
            sql.append("GROUP BY ");
            sql.append("    ownerid, ");
            sql.append("    boothid, ");
            sql.append("    postid, ");
            sql.append("    broadcastid, ");
            sql.append("    postname ");
            sql.append("ORDER BY ");
            sql.append("    point desc ");

            String[] paramArray = {ownerid, boothid, broadcastid};
            List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

            //�Y�����[�U�[�����݂��Ȃ��ꍇ
            if(null == resultList || resultList.size() == 0) {
               return beanList;
            }
            Iterator<Map<String, String>> it = resultList.iterator();
            while(it.hasNext()) {
            	RankUserBean bean = new RankUserBean();

                Map<String, String> map = it.next();
                bean.setOwnerid(map.get("ownerid"));
                bean.setBoothid(map.get("boothid"));
                bean.setBroadcastid(map.get("broadcastid"));
                bean.setPostid(map.get("postid"));
                bean.setPostname(map.get("postname"));
                bean.setTotalpost(map.get("total"));
                bean.setPoint(map.get("point"));

                beanList.add(bean);
            }
        } catch (SQLException e) {
            throw e;
        }
        return beanList;
    }

    public List<RankUserBean> getRankList(Connection connection, String ownerid, String boothid) throws SQLException {

        List<RankUserBean> beanList = new ArrayList<RankUserBean>();
        try {

            // �I�[�i�[�ɕR�t���u�[�X���擾����
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("    ownerid, ");
            sql.append("    boothid, ");
            sql.append("    broadcastid, ");
            sql.append("    postid, ");
            sql.append("    postname, ");
            sql.append("    COUNT(postname) As total, ");
            sql.append("    SUM(point) As point ");
            sql.append("FROM ");
            sql.append("    n1_msg ");
            sql.append("WHERE ");
            sql.append("    delflg = 0 AND ");
            sql.append("    ownerid = ? AND ");
            sql.append("    boothid = ? ");
            sql.append("GROUP BY ");
            sql.append("    ownerid, ");
            sql.append("    boothid, ");
            sql.append("    postid, ");
            sql.append("    broadcastid, ");
            sql.append("    postname ");
            sql.append("ORDER BY ");
            sql.append("    point desc ");

            String[] paramArray = {ownerid, boothid};
            List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

            //�Y�����[�U�[�����݂��Ȃ��ꍇ
            if(null == resultList || resultList.size() == 0) {
               return beanList;
            }
            Iterator<Map<String, String>> it = resultList.iterator();
            while(it.hasNext()) {
            	RankUserBean bean = new RankUserBean();

                Map<String, String> map = it.next();
                bean.setOwnerid(map.get("ownerid"));
                bean.setBoothid(map.get("boothid"));
                bean.setBroadcastid(map.get("broadcastid"));
                bean.setPostid(map.get("postid"));
                bean.setPostname(map.get("postname"));
                bean.setTotalpost(map.get("total"));
                bean.setPoint(map.get("point"));

                beanList.add(bean);
            }
        } catch (SQLException e) {
            throw e;
        }
        return beanList;
    }
}
