package jp.co.cococoa.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.cococoa.business.postmessage.PostMessageBean;
import jp.co.cococoa.business.postmessage.PostMessageBusiness;

/**
 * Servlet implementation class Login
 */
public class PostMessageAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostMessageAction() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String nextPage = "/postmessage.jsp";
        PostMessageBean bean = new PostMessageBean();

        HttpSession session = request.getSession();

        String key  = request.getParameter("key");
        String ownerid  = request.getParameter("o");
        String boothid  = request.getParameter("b");
        String broadcastid  = request.getParameter("a");

        System.out.println("ownerid：" + ownerid);
        System.out.println("boothid：" + boothid);
        System.out.println("broadcastid：" + broadcastid);

        PostMessageBusiness business = new PostMessageBusiness();

        if ("init".equals(key)) {
            bean = business.init(ownerid, boothid, broadcastid);
        } else if("save".equals(key)) {
        }

        session.setAttribute("postMessageBean", bean);

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
