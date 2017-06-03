package jp.co.cococoa.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import jp.co.cococoa.business.login.LoginBusiness;
import jp.co.cococoa.business.login.UserBean;
import jp.co.cococoa.common.bean.AuthInfoBean;
import jp.co.cococoa.common.bean.Messagebean;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

	Logger log= Logger.getLogger(Login.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	log.info(Login.class.getName() + " -- in");
    	log.warn(Login.class.getName() + " -- in");
    	log.error(Login.class.getName() + " -- in");

    	String nextPage = "/login.jsp";
        UserBean bean= new UserBean();
        Messagebean msgBean = new Messagebean();
        AuthInfoBean loginBean = new AuthInfoBean();

        HttpSession session = request.getSession();

        String userid      = request.getParameter("userid");
        String password    = request.getParameter("password");
        String passwordnew = request.getParameter("passwordnew");
        String key = request.getParameter("key");

        System.out.println(userid);
        System.out.println(password);
        System.out.println(passwordnew);
        System.out.println(key);

        LoginBusiness business = new LoginBusiness();

        if ("login".equals(key)) {        //���O�C��
            bean = business.login(userid, password);
        } else if("create".equals(key)) {  //���[�U�[�V�K�o�^
            bean = business.create(userid, password);
        } else if("change".equals(key)) {
            int cnt = business.passwordChange(userid, password, passwordnew); //�p�X���[�h�ύX
            if (cnt == 1) {
            	bean.setUserid(userid);
            	bean.setExists(true);
            }
        }

        //認証できた場合
        if (bean.isExists()) {
        	loginBean.setUserid(bean.getUserid());
            session.setAttribute("login", loginBean);
            request.setAttribute("key", "find");
            nextPage = "Owner";
        }

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
