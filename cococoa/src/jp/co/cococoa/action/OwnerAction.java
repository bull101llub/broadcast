package jp.co.cococoa.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.cococoa.business.owner.OwnerBean;
import jp.co.cococoa.business.owner.OwnerBusiness;
import jp.co.cococoa.common.bean.AuthInfoBean;

/**
 * Servlet implementation class Login
 */
public class OwnerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String nextPage = "/owner.jsp";
        OwnerBean bean = new OwnerBean();


        HttpSession session = request.getSession();
        AuthInfoBean loginBean = (AuthInfoBean) session.getAttribute("login");

		String userid      = request.getParameter("userid");
		String ownerid      = request.getParameter("ownerid");
		String ownername    = request.getParameter("ownername");
		String description = request.getParameter("description");
		String key = request.getParameter("key");
		String key2 = (String) request.getAttribute("key");

		System.out.println(ownerid);
		System.out.println(ownername);
		System.out.println(description);
		System.out.println(key);
		System.out.println(key2);

		OwnerBusiness owner = new OwnerBusiness();

        if ("find".equals(key) || "find".equals(key2)) {

        	bean = owner.find(loginBean.getUserid());

            // オーナーが不在な場合
            if (bean == null || bean.getOwnerid() == null) {
            	nextPage = "/owner.jsp";
            } else {
            	nextPage = "Booth";
            	request.setAttribute("key", "find");
            }
        } else if("create".equals(key)) {
        	bean = owner.create(userid, ownerid, ownername, description);
        } else if("save".equals(key)) {
        	bean = owner.update(userid, ownerid, ownername, description);
        }

        loginBean.setOwnerid(bean.getOwnerid());
        session.setAttribute("ownerBean", bean);
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
