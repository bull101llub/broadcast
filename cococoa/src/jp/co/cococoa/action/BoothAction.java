package jp.co.cococoa.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.cococoa.business.booth.BoothBean;
import jp.co.cococoa.business.booth.BoothBusiness;
import jp.co.cococoa.business.booth.BoothListBean;
import jp.co.cococoa.common.bean.AuthInfoBean;

/**
 * Servlet implementation class Login
 */
public class BoothAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoothAction() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String nextPage = "/booth.jsp";

        HttpSession session = request.getSession();
        AuthInfoBean loginBean = (AuthInfoBean) session.getAttribute("login");

        BoothListBean beanList = null;
        BoothBean bean = null;

        String id      = request.getParameter("roomid");
        String name    = request.getParameter("roomname");
        String comment = request.getParameter("roommessage");
        String key = request.getParameter("key");
        String key2 = (String)request.getAttribute("key");

        System.out.println(id);
        System.out.println(name);
        System.out.println(comment);
        System.out.println(key);

        BoothBusiness business = new BoothBusiness();

        if ("find".equals(key) || "find".equals(key2)) {
        	beanList = business.find(loginBean.getOwnerid());

        	if (null != beanList && null != beanList.getBoothList() && beanList.getBoothList().size() > 0) {
        		String boothid = beanList.getBoothList().get(0).getBoothid();
        		String boothname = beanList.getBoothList().get(0).getBootname();
            	loginBean.setBoothid(boothid);
            	loginBean.setBoothname(boothname);
        	}
            session.setAttribute("boothListBean", beanList);
        } else if("create".equals(key)) {
            bean = business.create("", name, comment);
        } else if("save".equals(key)) {
            business.update(id, name, comment);
        	beanList = business.find(loginBean.getOwnerid());
        }

        session.setAttribute("boothListBean", beanList);
        session.setAttribute("login", loginBean);

        RequestDispatcher dispatcher = null;
        dispatcher =  request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
