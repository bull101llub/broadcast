package jp.co.cococoa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.cococoa.business.msg.MessageBean;
import jp.co.cococoa.business.msg.MessageListBean;

public class MessageDao extends SuperDao {


    public List<MessageBean> find(Connection connection, String ownerid, String boothid, String broadcastid, String contentid) throws SQLException {
        List<MessageBean> beanList = new ArrayList<MessageBean>();
        try {
            // SQL
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("    N1_MSG.OWNERID,");
            sql.append("    N1_MSG.BOOTHID,");
            sql.append("    N1_MSG.BROADCASTID,");
            sql.append("    N1_MSG.MSGID,");
            sql.append("    N1_MSG.CONTENTID,");
            sql.append("    N1_MSG.POSTID,");
            sql.append("    N1_MSG.POSTNAME,");
            sql.append("    N1_MSG.MSG,");
            sql.append("    N1_MSG.POINT,");
            sql.append("    N1_MSG.READFLG,");
            sql.append("    N1_MSG.DELFLG,");
            sql.append("    N1_MSG.CREATEYMD,");
            sql.append("    N1_MSG.UPDATEYMD ");
            sql.append("FROM ");
            sql.append("    N1_MSG ");
            sql.append("WHERE ");
            sql.append("    N1_MSG.OWNERID = ? AND ");
            sql.append("    N1_MSG.BOOTHID = ? AND ");
            sql.append("    N1_MSG.BROADCASTID = ? AND ");
            sql.append("    N1_MSG.CONTENTID = ? AND ");
            sql.append("    N1_MSG.DELFLG = 0 ");
            sql.append("ORDER BY ");
            sql.append("    N1_MSG.CREATEYMD; ");

            String[] paramArray = {ownerid, boothid, broadcastid, contentid};
            List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

            if(null == resultList || resultList.size() == 0) {
               return beanList;
            }
            Iterator<Map<String, String>> it = resultList.iterator();
            while(it.hasNext()) {
                Map<String, String> map = it.next();
                MessageBean bean = new MessageBean();
                bean.setOwnerid(map.get("ownerid"));
                bean.setBoothid(map.get("boothid"));
                bean.setBroadcastid(map.get("broadcastid"));
                bean.setMsgid(map.get("msgid"));
                bean.setContentid(map.get("contentid"));
                bean.setPostid(map.get("postid"));
                bean.setPostname(map.get("postname"));
                bean.setMsg(map.get("msg"));
                bean.setPoint(Integer.parseInt(map.get("point")));
                bean.setReadflg(Integer.parseInt(map.get("readflg")));
                bean.setDelflg(Integer.parseInt(map.get("delflg")));
                bean.setCreateymd(map.get("createymd"));
                bean.setUpdateymd(map.get("updateymd"));

                beanList.add(bean);
            }
        } catch (SQLException e) {
            throw e;
        }
        return beanList;
    }

    public List<MessageBean> find(Connection connection, String ownerid, String boothid, String broadcastid) throws SQLException {
        List<MessageBean> beanList = new ArrayList<MessageBean>();
        try {
            // SQL
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("    N1_MSG.OWNERID,");
            sql.append("    N1_MSG.BOOTHID,");
            sql.append("    N1_MSG.BROADCASTID,");
            sql.append("    N1_MSG.MSGID,");
            sql.append("    N1_MSG.CONTENTID,");
            sql.append("    N1_MSG.POSTID,");
            sql.append("    N1_MSG.POSTNAME,");
            sql.append("    N1_MSG.MSG,");
            sql.append("    N1_MSG.POINT,");
            sql.append("    N1_MSG.READFLG,");
            sql.append("    N1_MSG.DELFLG,");
            sql.append("    N1_MSG.CREATEYMD,");
            sql.append("    N1_MSG.UPDATEYMD");
            sql.append(" FROM ");
            sql.append("    N1_MSG ");
            sql.append(" WHERE ");
            sql.append("    N1_MSG.OWNERID = ? AND ");
            sql.append("    N1_MSG.BOOTHID = ? AND ");
            sql.append("    N1_MSG.BROADCASTID = ? AND ");
            sql.append("    N1_MSG.DELFLG = 0 ");
            sql.append(" ORDER BY ");
            sql.append("    N1_MSG.CREATEYMD; ");

            String[] paramArray = {ownerid, boothid, broadcastid};
            List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

            if(null == resultList || resultList.size() == 0) {
               return beanList;
            }
            Iterator<Map<String, String>> it = resultList.iterator();
            while(it.hasNext()) {
                Map<String, String> map = it.next();
                MessageBean bean = new MessageBean();
                bean.setOwnerid(map.get("ownerid"));
                bean.setBoothid(map.get("boothid"));
                bean.setBroadcastid(map.get("broadcastid"));
                bean.setMsgid(map.get("msgid"));
                bean.setContentid(map.get("contentid"));
                bean.setPostid(map.get("postid"));
                bean.setPostname(map.get("postname"));
                bean.setMsg(map.get("msg"));
                bean.setPoint(Integer.parseInt(map.get("point")));
                bean.setReadflg(Integer.parseInt(map.get("readflg")));
                bean.setDelflg(Integer.parseInt(map.get("delflg")));
                bean.setCreateymd(map.get("createymd"));
                bean.setUpdateymd(map.get("updateymd"));

                beanList.add(bean);
            }
        } catch (SQLException e) {
            throw e;
        }
        return beanList;
    }

    public int create(Connection connection,	MessageListBean messageListBean) throws SQLException {
        int count = 0;
        try {
            // SQL
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO n1_msg( ");
            sql.append("    ownerid, ");
            sql.append("    boothid, ");
            sql.append("    broadcastid, ");
            sql.append("    msgid, ");
            sql.append("    contentid, ");
            sql.append("    postid, ");
            sql.append("    postname, ");
            sql.append("    msg, ");
            sql.append("    point, ");
            sql.append("    readflg, ");
            sql.append("    delflg, ");
            sql.append("    createymd ");
            sql.append(") VALUES ( ");
            sql.append("    ?, ");
            sql.append("    ?, ");
            sql.append("    ?, ");
            sql.append("    ?, ");
            sql.append("    ?, ");
            sql.append("    ?, ");
            sql.append("    ?, ");
            sql.append("    ?, ");
            sql.append("    0, ");
            sql.append("    0, ");
            sql.append("    0, ");
            sql.append("    to_char(current_timestamp,'YYYYMMDDHH24MMSSMS') ");
            sql.append(")  ");

            List<String[]> patramList = new ArrayList<String[]>();

            List<MessageBean> beanList = messageListBean.getList();
            Iterator<MessageBean> it = beanList.iterator();
            while(it.hasNext()){
            	MessageBean bean = it.next();
                String[] paramArray = {
                		bean.getOwnerid(),
                		bean.getBoothid(),
                		bean.getBroadcastid(),
                		bean.getMsgid(),
                		bean.getContentid(),
                		bean.getPostid(),
                		bean.getPostname(),
                		bean.getMsg()};
                patramList.add(paramArray);
            }
            count = executeBatch(connection, sql.toString(), patramList);
        } catch (SQLException e) {
            throw e;
        }
        return count;
    }
}
