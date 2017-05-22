package jp.co.cococoa.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.cococoa.business.reserve.ReserveBean;
import jp.co.cococoa.business.reserve.ReserveContentsBean;
import jp.co.cococoa.util.DateUtil;

public class ReserveDao extends SuperDao {

     /*  �\����擾
     *  @param roomNo ���[��No
     *  @return �ŐV�\����
     * */
    public ReserveBean getReserve(Connection connection, String boothno, String ownerid) throws SQLException {
        ReserveBean bean = new ReserveBean();
        try {

        	StringBuilder sql = new StringBuilder();
            sql.append("SELECT");
            sql.append("    N1_BROADCAST.OWNERID,");
            sql.append("    N1_BROADCAST.BOOTHID,");
            sql.append("    N1_BROADCAST.BROADCASTID,");

            sql.append("    N1_CONTENTS.SORTNO,");
            sql.append("    N1_CONTENTS.CNTRIBUTFLG,");
            sql.append("    N1_CONTENTS.CONTENTID,");
            sql.append("    N1_CONTENTS.TITLE,");
            sql.append("    N1_CONTENTS.DESCRIPTION,");
            sql.append("    N1_CONTENTS.SCRIPT,");
            sql.append("    N1_CONTENTS.DELFLG,");
            sql.append("    N1_CONTENTS.CREATEYMD,");
            sql.append("    N1_CONTENTS.UPDATEYMD");
            sql.append(" FROM");
            sql.append("    N1_CONTENTS LEFT JOIN N1_BROADCAST ON N1_CONTENTS.BOOTHID = N1_BROADCAST.BOOTHID AND");
            sql.append("                                          N1_CONTENTS.OWNERID = N1_BROADCAST.OWNERID");
            sql.append(" WHERE");
            sql.append("    N1_CONTENTS.OWNERID = ? AND");
            sql.append("    N1_CONTENTS.BOOTHID = ? AND");
            sql.append("    N1_CONTENTS.DELFLG = 0 ");
          //  sql.append(" AND N1_BROADCAST.COMP_FLG = 0 AND");
          //  sql.append("    N1_BROADCAST.CANCEL_FLG = 0");
            sql.append(" ORDER BY");
            sql.append("    N1_CONTENTS.SORTNO");

           String[] paramArray = {ownerid, boothno};

           List<Map<String, String>> resultList = select(connection, sql.toString(), paramArray);

           if (null == resultList || resultList.size() == 0) {

        	   List<ReserveContentsBean> list = makePalam(ownerid, boothno);
        	   bean.setOwnerid(ownerid);
        	   bean.setBoothid(boothno);
        	   bean.setContentsList(list);
        	   insertContents(connection, bean);
           } else {
               Iterator<Map<String, String>> it = resultList.iterator();
               while(it.hasNext()) {
                   Map<String, String> map = it.next();
                   ReserveContentsBean contentBean = new ReserveContentsBean();
                   bean.setBroadcastId(map.get("broadcastid"));
                   contentBean.setOwnerid(map.get("ownerid"));
                   contentBean.setBoothid(map.get("boothid"));
                   contentBean.setContentid(map.get("contentid"));
                   contentBean.setSortno(Integer.parseInt(map.get("sortno")));
                   contentBean.setCntributflg(map.get("cntributflg"));
                   contentBean.setTitle(map.get("title"));
                   contentBean.setDescription(map.get("description"));
                   contentBean.setScript(map.get("script"));
                   contentBean.setDelflg(map.get("delflg"));
                   contentBean.setCreateymd(map.get("createymd"));
                   contentBean.setUpdateymd(map.get("updateymd"));
                   bean.addContentsList(contentBean);
               }
           }

        } catch (SQLException e) {
            throw e;
        }
        return bean;
    }




    /**
     *  �\����ۑ�
     *  @param  reserveBean �\����i�[Bean
     *  @return �ۑ�����
     * */
    public int insertContents(Connection connection, ReserveBean reserveBean) throws SQLException {
        int count = 0;

        String ownerid = reserveBean.getOwnerid();
        String boothid = reserveBean.getBoothid();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO n1_broadcast( ");
            sql.append("            ownerid, ");
            sql.append("            boothid, ");
            sql.append("            broadcastid, ");
            sql.append("            reserveymd, ");
            sql.append("            oaymd_from, ");
            sql.append("            oaymd_to,  ");
            sql.append("            comp_flg, ");
            sql.append("            cancel_flg, ");
            sql.append("            createymd) ");
            sql.append("    VALUES (?, ");
            sql.append("            ?, ");
            sql.append("            '', ");
            sql.append("            '', ");
            sql.append("            '', ");
            sql.append("            '', ");
            sql.append("            0, ");
            sql.append("            0, ");
            sql.append("            to_char(current_timestamp,'YYYYMMDDHH24MMSSMS'))");

            String[] paramArray1 = {reserveBean.getOwnerid(),reserveBean.getBoothid()};

            count = executeUpdate(connection, sql.toString(), paramArray1);

            sql = new StringBuilder();
                          sql.append("INSERT INTO N1_CONTENTS(");
                          sql.append("    OWNERID,");
                          sql.append("    BOOTHID,");
                          sql.append("    SORTNO,");
                          sql.append("    CNTRIBUTFLG,");
                          sql.append("    CONTENTID,");
                          sql.append("    TITLE,");
                          sql.append("    DESCRIPTION,");
                          sql.append("    SCRIPT,");
                          sql.append("    DELFLG,");
                          sql.append("    CREATEYMD");
                          sql.append(")VALUES(");
                          sql.append("    ?,");
                          sql.append("    ?,");
                          sql.append("    ?,");
                          sql.append("    ?,");
                          sql.append("    ?,");
                          sql.append("    ?,");
                          sql.append("    ?,");
                          sql.append("    ?,");
                          sql.append("    0,");
                          sql.append("    to_char(current_timestamp,'YYYYMMDDHH24MMSSMS')    ");
                          sql.append(")");

            List<String[]> patramList = new ArrayList<String[]>();


            List<ReserveContentsBean>beanList = reserveBean.getContentsList();
            Iterator<ReserveContentsBean> it = beanList.iterator();
            while(it.hasNext()){
                ReserveContentsBean bean = it.next();
                String[] paramArray2 = {ownerid,
                		               boothid,
                		               Integer.toString(bean.getSortno()),
                		               bean.getCntributflg(),
                		               bean.getContentid(),
                		               bean.getTitle(),
                		               bean.getDescription(),
                		               bean.getScript()};
                patramList.add(paramArray2);
            }

           count = executeBatch(connection, sql.toString(), patramList);

        } catch (SQLException e) {
            throw e;
        }
        return count;
    }

    /**
     *  �\����ۑ�
     *  @param  reserveBean �\����i�[Bean
     *  @return �ۑ�����
     * */
    public int updatetContents(Connection connection, ReserveBean reserveBean) throws SQLException {
        int count = 0;

        String ownerid = reserveBean.getOwnerid();
        String boothid = reserveBean.getBoothid();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE N1_CONTENTS SET ");
            sql.append("    CNTRIBUTFLG = ?, ");
            sql.append("    TITLE       = ?, ");
            sql.append("    DESCRIPTION = ?, ");
            sql.append("    SCRIPT      = ?, ");
            sql.append("    UPDATEYMD   = to_char(current_timestamp,'YYYYMMDDHH24MMSSMS') ");
            sql.append("WHERE ");
            sql.append("    OWNERID   = ? AND ");
            sql.append("    BOOTHID   = ? AND ");
            sql.append("    CONTENTID = ? ");

            List<String[]> patramList = new ArrayList<String[]>();


            List<ReserveContentsBean>beanList = reserveBean.getContentsList();
            Iterator<ReserveContentsBean> it = beanList.iterator();
            while(it.hasNext()){
                ReserveContentsBean bean = it.next();
                String[] paramArray = {
                                       bean.getCntributflg(),
                                       bean.getTitle(),
                                       bean.getDescription(),
                                       bean.getScript(),
                                       ownerid,
                                       boothid,
                                       bean.getContentid()
                };

                patramList.add(paramArray);
            }

           count = executeBatch(connection, sql.toString(), patramList);

        } catch (SQLException e) {
            throw e;
        }
        return count;
    }


    /**
     *  �\��
     *  @param  roomNo    ���[��No
     *  @param  reserveId �\��ID
     *  @return �ۑ�����
     * */
    public int reserve(Connection connection, String ownerid, String boothid) throws SQLException {
        int count = 0;
        String ymd = DateUtil.getDateKey();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE N1_BROADCAST ");
            sql.append("    SET BROADCASTID = ?, RESERVEYMD = ?, CANCEL_FLG = 0, UPDATEYMD = ? ");
            sql.append("WHERE ");
            sql.append("    OWNERID = ? AND ");
            sql.append("    BOOTHID = ? AND");
            sql.append("    COMP_FLG = 0 AND");
            sql.append("    CANCEL_FLG = 0 ");

            String[] paramArray = {"A".concat(ymd), ymd, ymd, ownerid, boothid};
            count = executeUpdate(connection, sql.toString(), paramArray);

        } catch (SQLException e) {
            throw e;
        }
        return count;
    }

    /**
     *  �\��L�����Z��
     *  �������R�[�h�𕨗��폜���܂�
     *  @param  roomNo   ���[��No
     *  @param  brdcstId ����ID
     *  @return �폜����
     * */
    public int cancel(Connection connection,  String ownerid, String boothid) throws SQLException {
        int count = 0;
        String ymd = DateUtil.getDateKey();

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE N1_BROADCAST ");
            sql.append("    SET CANCEL_FLG = 1, UPDATEYMD = ? ");
            sql.append("WHERE ");
            sql.append("    BROADCASTID = (SELECT BROADCASTID FROM N1_BROADCAST WHERE OWNERID = ? AND BOOTHID = ? AND COMP_FLG = 0 AND CANCEL_FLG = 0) AND");
            sql.append("    OWNERID = ? AND ");
            sql.append("    BOOTHID = ? AND");
            sql.append("    COMP_FLG = 0 AND");
            sql.append("    CANCEL_FLG = 0 ");

            String[] paramArray = {ymd, ownerid, boothid, ownerid, boothid};
            count = executeUpdate(connection, sql.toString(), paramArray);

        } catch (SQLException e) {
            throw e;
        }
        return count;
    }


	private List<ReserveContentsBean> makePalam(String ownerid, String boothid) {

		List<ReserveContentsBean> list = new ArrayList<ReserveContentsBean>();
        String ymd = DateUtil.getDateKey();

        //オープニング
        ReserveContentsBean opBean = new ReserveContentsBean();
        opBean.setCntributflg("0");
        opBean.setContentid("C0001");
        opBean.setDelflg("0");
        opBean.setCreateymd(ymd);
        opBean.setOwnerid(ownerid);
        opBean.setBoothid(boothid);
        opBean.setScript("");
        opBean.setTitle("");
        opBean.setSortno(1);
        opBean.setDescription("");
        list.add(opBean);

        //フリー
        ReserveContentsBean frBean = new ReserveContentsBean();
        frBean.setCntributflg("1");
        frBean.setContentid("C0010");
        frBean.setDelflg("0");
        frBean.setCreateymd(ymd);
        frBean.setOwnerid(ownerid);
        frBean.setBoothid(boothid);
        frBean.setScript("");
        frBean.setTitle("");
        frBean.setSortno(2);
        frBean.setDescription("");
        list.add(frBean);

        //テーマ
        ReserveContentsBean tmBean = new ReserveContentsBean();
        tmBean.setCntributflg("1");
        tmBean.setContentid("C0011");
        tmBean.setDelflg("0");
        tmBean.setCreateymd(ymd);
        tmBean.setOwnerid(ownerid);
        tmBean.setBoothid(boothid);
        tmBean.setScript("");
        tmBean.setTitle("");
        tmBean.setSortno(3);
        tmBean.setDescription("");
        list.add(tmBean);

        //コンテンツ1
        ReserveContentsBean c1Bean = new ReserveContentsBean();
        c1Bean.setCntributflg("1");
        c1Bean.setContentid("C0012");
        c1Bean.setDelflg("0");
        c1Bean.setCreateymd(ymd);
        c1Bean.setOwnerid(ownerid);
        c1Bean.setBoothid(boothid);
        c1Bean.setScript("");
        c1Bean.setTitle("");
        c1Bean.setSortno(4);
        c1Bean.setDescription("");
        list.add(c1Bean);

        //コンテンツ2
        ReserveContentsBean c2Bean = new ReserveContentsBean();
        c2Bean.setCntributflg("1");
        c2Bean.setContentid("C0013");
        c2Bean.setDelflg("0");
        c2Bean.setCreateymd(ymd);
        c2Bean.setOwnerid(ownerid);
        c2Bean.setBoothid(boothid);
        c2Bean.setScript("");
        c2Bean.setTitle("");
        c2Bean.setSortno(5);
        c2Bean.setDescription("");
        list.add(c2Bean);

        //クローズ
        ReserveContentsBean clBean = new ReserveContentsBean();
        clBean.setCntributflg("0");
        clBean.setContentid("C0002");
        clBean.setDelflg("0");
        clBean.setCreateymd(ymd);
        clBean.setOwnerid(ownerid);
        clBean.setBoothid(boothid);
        clBean.setScript("");
        clBean.setTitle("");
        clBean.setSortno(6);
        clBean.setDescription("");
        list.add(clBean);

        return list;
	}




}
