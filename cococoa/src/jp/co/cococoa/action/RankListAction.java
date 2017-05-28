package jp.co.cococoa.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.cococoa.business.rank.RankBean;
import jp.co.cococoa.business.rank.RankBusiness;
import jp.co.cococoa.common.bean.AuthInfoBean;

/**
 * Servlet implementation class Login
 */
public class RankListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String nextPage = "/rank.jsp";

        HttpSession session = request.getSession();
        AuthInfoBean loginBean = (AuthInfoBean) session.getAttribute("login");

		String ownerid      = loginBean.getOwnerid();
		String boothid      = loginBean.getBoothid();
		String broadcastid  = request.getParameter("a");
        String key          = request.getParameter("key");

        RankBusiness business = new RankBusiness();
        RankBean bean = null;

        if ("init".equals(key)) {
        	bean = business.find(ownerid, boothid, broadcastid);
        }

        session.setAttribute("rankBean", bean);

        RequestDispatcher dispatcher = null;
        dispatcher =  request.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
