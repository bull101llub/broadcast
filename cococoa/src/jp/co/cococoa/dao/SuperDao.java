package jp.co.cococoa.dao;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SuperDao {

    public List<Map<String, String>> select(Connection connection, String sql, String[] param) throws SQLException {
        //Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String, String>> resultList= null;

        try {
            // テーブル照会実行
            pstmt = connection.prepareStatement(sql);

            for(int i=1; i< param.length+1; i++) {
                pstmt.setString(i, param[i-1]);
            }

            rs = pstmt.executeQuery();

            if (null != rs) {
                ResultSetMetaData rsmd= rs.getMetaData();
                resultList= new ArrayList<Map<String, String>>();

                // テーブル照会結果を出力
                int columnCount = rsmd.getColumnCount();

                while (rs.next()) {
                    Map<String, String> map = new HashMap<String, String>();

                    for (int i=1; i < columnCount+1; i++) {
                        map.put(rsmd.getColumnLabel(i), rs.getString(rsmd.getColumnLabel(i)));
                    }
                    resultList.add(map);
                }
            }
        } catch (SQLException e) {
        	e.getStackTrace();
            throw e;
        }finally{
            try {
            	if (null != rs && !rs.isClosed()) {
                    rs.close();
            	}
            	if (null != pstmt && !pstmt.isClosed()) {
                    pstmt.close();
            	}
            } catch (SQLException e) {
            	e.getStackTrace();
                throw e;
            }
        }
        return resultList;
    }

    public int executeUpdate(Connection connection, String sql, String[] param) throws SQLException {
        PreparedStatement pstmt = null;
        int updateCount = 0;

        try {
            // テーブル照会実行
        	pstmt = connection.prepareStatement(sql);
        	ParameterMetaData meta = pstmt.getParameterMetaData();

            for(int i=0; i< param.length; i++) {

                System.out.println(meta.getParameterClassName(i+1));

            	if("java.lang.Integer".equals(meta.getParameterClassName(i+1))) {
                   pstmt.setInt(i+1, Integer.parseInt(param[i]));
        	   } else {
                   pstmt.setString(i+1,param[i]);
        	   }
            }
            updateCount = pstmt.executeUpdate();
        } catch (SQLException e) {
        	e.getStackTrace();
            throw e;
        }finally{
            try {
            	if (null != pstmt && !pstmt.isClosed()) {
                    pstmt.close();
            	}
            } catch (SQLException e) {
            	e.getStackTrace();
                throw e;
            }
        }
        return updateCount;
    }

    public int executeBatch(Connection connection, String sql, List<String[]> paramList) throws SQLException {
        PreparedStatement pstmt = null;
        int updateCount = 0;

        try {
            // テーブル照会実行
        	pstmt = connection.prepareStatement(sql);

        	ParameterMetaData meta = pstmt.getParameterMetaData();
            Iterator<String[]> it = paramList.iterator();
            while(it.hasNext()) {
               String[] paramArray = it.next();
               for (int i=0; i<paramArray.length; i++) {
                   System.out.println(meta.getParameterClassName(i+1));

            	   if("java.lang.Integer".equals(meta.getParameterClassName(i+1))) {
                       pstmt.setInt(i+1, Integer.parseInt(paramArray[i]));
            	   } else {
                       pstmt.setString(i+1,paramArray[i]);
            	   }
               }
               pstmt.addBatch();
            }

            int result[]=pstmt.executeBatch();
            updateCount = result.length;
        } catch (SQLException e) {
        	e.getStackTrace();
            throw e;
        }finally{
            try {
            	if (null != pstmt && !pstmt.isClosed()) {
                    pstmt.close();
            	}
            } catch (SQLException e) {
            	e.getStackTrace();
                throw e;
            }
        }
        return updateCount;
    }

}
