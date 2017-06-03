package jp.co.cococoa.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.cococoa.business.reserve.ReserveBean;
import jp.co.cococoa.business.reserve.ReserveBusiness;
import jp.co.cococoa.common.bean.AuthInfoBean;
import jp.co.cococoa.common.bean.Messagebean;

/**
 * Servlet implementation class Login
 */
public class ContentsAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentsAction() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = "/contents.jsp";
        Messagebean msgBean = new Messagebean();

        HttpSession session = request.getSession();
        AuthInfoBean loginBean = (AuthInfoBean) session.getAttribute("login");

        String ownerid  = request.getParameter("o");
        String boothid  = request.getParameter("b");
        String broadcastid  = request.getParameter("a");

        System.out.println("ownerid：" + ownerid);
        System.out.println("boothid：" + boothid);
        System.out.println("broadcastid：" + broadcastid);

        ReserveBusiness business = new ReserveBusiness();
        ReserveBean bean= new ReserveBean();
        bean = business.init(loginBean.getOwnerid(), loginBean.getBoothid());
        bean.setOwnerid(loginBean.getOwnerid());
        bean.setBoothid(loginBean.getBoothid());

        session.setAttribute("reserveBean", bean);
        session.setAttribute("message", msgBean);

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
