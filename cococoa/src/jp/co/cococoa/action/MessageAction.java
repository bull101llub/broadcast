package jp.co.cococoa.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

import jp.co.cococoa.business.msg.MessageBean;
import jp.co.cococoa.business.msg.MessageBusiness;
import jp.co.cococoa.business.msg.MessageListBean;
import jp.co.cococoa.common.bean.AuthInfoBean;
import jp.co.cococoa.util.DateUtil;

/**
 * Servlet implementation class Login
 */
public class MessageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String nextPage = "/table.jsp";
		//String nextPage = "";
        MessageListBean bean = new MessageListBean();
        HttpSession session = request.getSession();
        AuthInfoBean loginBean = (AuthInfoBean) session.getAttribute("login");

		String ownerid      = request.getParameter("o");
		String boothid      = request.getParameter("b");
		String broadcastid  = request.getParameter("a");
		String contentsid  = request.getParameter("c");

		String messageid = request.getParameter("messageid");
		String point = request.getParameter("point");

		if(null == ownerid || "".equals(ownerid)) {
			ownerid = loginBean.getOwnerid();
		}
		if(null == boothid || "".equals(boothid)) {
			boothid = loginBean.getBoothid();
		}
		if(null == broadcastid || "".equals(broadcastid)) {
			broadcastid = loginBean.getBroadcastid();
		}

		System.out.println("------------------------");
		System.out.println("ownerid:" + ownerid);
		System.out.println("boothid:" + boothid);
		System.out.println("broadcastid:" + broadcastid);
		System.out.println("contentsid:" + contentsid);
		System.out.println("------------------------");

		String key = request.getParameter("key");

		MessageBusiness business = new MessageBusiness();

        if ("create".equals(key)) {
        	bean = makeParam(request, ownerid, boothid, broadcastid);
        	business.getRegist(bean);
            nextPage = "/PostMessage?o=" + ownerid + "&b=" + boothid + "&a=" + broadcastid + "&key=init";
        } else if ("point".equals(key)) {
        	business.putPoint(ownerid, boothid, broadcastid, messageid, point);
        	nextPage = "Rank?a=" + broadcastid + "&key=init";
        } else if ("find".equals(key)) {
        	if(null == contentsid || "".equals(contentsid)) {
        		contentsid = "C0001";
        	}
        	bean = business.find(ownerid, boothid, broadcastid, contentsid);
        }

        session.setAttribute("messageListBean", bean);

		RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private MessageListBean makeParam(HttpServletRequest request, String ownerid, String boothid, String broadcastid) {
		MessageListBean listBean = new MessageListBean();

		String postname  = request.getParameter("postname");

		listBean.setOwnerid(ownerid);
		listBean.setBoothid(boothid);
		listBean.setBroadcastid(broadcastid);

		String cfree   = StringEscapeUtils.escapeHtml4(request.getParameter("C0010"));
		String cThema  = StringEscapeUtils.escapeHtml4(request.getParameter("C0011"));
		String c1      = StringEscapeUtils.escapeHtml4(request.getParameter("C0012"));
		String c2      = StringEscapeUtils.escapeHtml4(request.getParameter("C0013"));

		System.out.println(postname);
		System.out.println(cfree);
		System.out.println(cThema);
		System.out.println(c1);
		System.out.println(c2);


		String keyYmd = DateUtil.getDateKey();
		listBean.addList(
		createMessageBean(ownerid, boothid, broadcastid, "C0010", "", postname, cfree, keyYmd));
		listBean.addList(
		createMessageBean(ownerid, boothid, broadcastid, "C0011", "", postname, cThema, keyYmd));
		listBean.addList(
		createMessageBean(ownerid, boothid, broadcastid, "C0012", "", postname, c1, keyYmd));
		listBean.addList(
		createMessageBean(ownerid, boothid, broadcastid, "C0013", "", postname, c2, keyYmd));

        return listBean;
	}

    private MessageBean createMessageBean(String ownerid,
    		                              String booth,
    		                              String broadcastid,
    		                              String contentid,
    		                              String postid,
    		                              String postname,
    		                              String msg,
    		                              String keyYmd){
    	MessageBean bean = new MessageBean();

    	bean.setOwnerid(ownerid);
    	bean.setBoothid(booth);
    	bean.setBroadcastid(broadcastid);
    	bean.setMsgid(contentid + keyYmd);
    	bean.setContentid(contentid);
    	bean.setPostid(postid);
    	bean.setPostname(postname);
    	bean.setMsg(msg);
    	bean.setPoint(0);
    	bean.setReadflg(0);
    	bean.setDelflg(0);

    	return bean;
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
