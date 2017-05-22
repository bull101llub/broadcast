package jp.co.cococoa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.cococoa.business.postmessage.PostMessageBean;
import jp.co.cococoa.business.postmessage.PostMessageContentsBean;

public class PostMessageDao extends SuperDao {

    /*  �u�[�X���ꗗ�擾
     *  @param ownerid   �I�[�i�[ID
     *  @return �u�[�X���ꗗ
     * */
    public PostMessageBean getContents(Connection connection,
                                             String ownerid, String boothid, String broadcastid) throws SQLException {

    	PostMessageBean bean = new PostMessageBean();
        List<PostMessageContentsBean> beanList = new ArrayList<PostMessageContentsBean>();
        try {

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("    N1_BROADCAST.OWNERID, ");
            sql.append("    N1_BROADCAST.BOOTHID, ");
            sql.append("    N1_BROADCAST.BROADCASTID, ");
            sql.append("    N1_CONTENTS.SORTNO, ");
            sql.append("    N1_CONTENTS.CNTRIBUTFLG, ");
            sql.append("    N1_CONTENTS.CONTENTID, ");
            sql.append("    N1_CONTENTS.TITLE, ");
            sql.append("    N1_CONTENTS.DESCRIPTION, ");
            sql.append("    N1_CONTENTS.SCRIPT, ");
            sql.append("    N1_CONTENTS.DELFLG, ");
            sql.append("    N1_CONTENTS.CREATEYMD, ");
            sql.append("    N1_CONTENTS.UPDATEYMD ");
            sql.append("FROM ");
            sql.append("    N1_BROADCAST LEFT JOIN N1_CONTENTS ON (N1_BROADCAST.BOOTHID = N1_CONTENTS.BOOTHID) AND ");
            sql.append("                                          (N1_BROADCAST.OWNERID = N1_CONTENTS.OWNERID) ");
            sql.append("WHERE ");
            sql.append("    N1_CONTENTS.OWNERID = ? AND ");
            sql.append("    N1_CONTENTS.BOOTHID = ? AND ");
            sql.append("    N1_BROADCAST.BROADCASTID = ? AND ");
            sql.append("    N1_CONTENTS.CNTRIBUTFLG = 1 AND     ");
            sql.append("    N1_CONTENTS.DELFLG = 0 AND ");
            sql.append("    N1_BROADCAST.COMP_FLG = 0 AND ");
            sql.append("    N1_BROADCAST.CANCEL_FLG = 0 ");
            sql.append("ORDER BY ");
            sql.append("    N1_CONTENTS.SORTNO ");

            String[] paramArray = {ownerid, boothid, broadcastid};
            List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

            if(null == resultList || resultList.size() == 0) {
               return null;
            }
            Iterator<Map<String, String>> it = resultList.iterator();
            while(it.hasNext()) {
            	PostMessageContentsBean cintentsBean = new PostMessageContentsBean();

                Map<String, String> map = it.next();
                cintentsBean.setOwnerid(map.get("ownerid"));
                cintentsBean.setBoothid(map.get("boothid"));
                cintentsBean.setSortno(Integer.parseInt(map.get("sortno")));
                cintentsBean.setCntributflg(map.get("cntributflg"));
                cintentsBean.setContentid(map.get("contentid"));
                cintentsBean.setTitle(map.get("title"));
                cintentsBean.setDescription(map.get("description"));
                cintentsBean.setScript(map.get("script"));
                cintentsBean.setDelflg(map.get("delflg"));
                cintentsBean.setCreateymd(map.get("createymd"));
                cintentsBean.setUpdateymd(map.get("updateymd"));

                beanList.add(cintentsBean);
            }
            bean.setBoothid(boothid);
            bean.setOwnerid(ownerid);
            bean.setBroadcastid(broadcastid);
            bean.setPostMessageContentsList(beanList);

        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }
}
