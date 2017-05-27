package jp.co.cococoa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.cococoa.business.reserve.ReserveBean;
import jp.co.cococoa.business.reserve.ReserveBusiness;
import jp.co.cococoa.business.reserve.ReserveContentsBean;
import jp.co.cococoa.common.bean.AuthInfoBean;
import jp.co.cococoa.util.DateUtil;

/**
 * Servlet implementation class Login
 */
public class ReserveAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserveAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String nextPage = "/reserve.jsp";
        ReserveBean bean = new ReserveBean();

        HttpSession session = request.getSession();
        AuthInfoBean loginBean = (AuthInfoBean) session.getAttribute("login");

        String key  = request.getParameter("key");

		String values[] = request.getParameterValues("collecting");

        ReserveBusiness business = new ReserveBusiness();

        if ("init".equals(key)) {
            bean = business.init(loginBean.getOwnerid(), loginBean.getBoothid());
            bean.setOwnerid(loginBean.getOwnerid());
            bean.setBoothid(loginBean.getBoothid());
        } else if("save".equals(key)) {
        	bean.setOwnerid(loginBean.getOwnerid());
        	bean.setBoothid(loginBean.getBoothid());
        	bean.setBroadcastId("");
        	bean.setContentsList(makePalam(request, loginBean.getOwnerid(), loginBean.getBoothid()));
        	business.save(bean);
        } else if("reserve".equals(key)) {
        	business.reserve(loginBean.getOwnerid(), loginBean.getBoothid());
            bean = business.init(loginBean.getOwnerid(), loginBean.getBoothid());
        } else if("cancel".equals(key)) {
        	business.cancel(loginBean.getOwnerid(), loginBean.getBoothid());
            bean = business.init(loginBean.getOwnerid(), loginBean.getBoothid());
        }

        session.setAttribute("reserveBean", bean);

		RequestDispatcher dispatcher = null;
        dispatcher =  request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

	private List<ReserveContentsBean> makePalam(HttpServletRequest request, String ownerid, String boothid) {

		List<ReserveContentsBean> list = new ArrayList<ReserveContentsBean>();

		String createymd = DateUtil.getDateKey();
		String[] values  = request.getParameterValues("collecting");

        if(null != values && values.length>0) {
        	for(int i=0; i<values.length; i++) {
                System.out.println("collecting:" + values[i]);
        	}
        }

        //オープニング
        ReserveContentsBean opBean = new ReserveContentsBean();
        opBean.setBoothid(boothid);
        opBean.setOwnerid(ownerid);
        opBean.setDelflg("0");
        opBean.setCreateymd(createymd);
        if (Arrays.asList(values).contains("C0001")) {
            opBean.setCntributflg("1");
        } else {
            opBean.setCntributflg("0");
        }
        opBean.setContentid(request.getParameter("idOp"));
        opBean.setTitle(request.getParameter("titleOp"));
        opBean.setDescription(request.getParameter("textOp"));
        opBean.setScript(request.getParameter("scriptOp"));
        list.add(opBean);

        //フリー
        ReserveContentsBean frBean = new ReserveContentsBean();
        frBean.setBoothid(boothid);
        frBean.setOwnerid(ownerid);
        frBean.setDelflg("0");
        frBean.setCreateymd(createymd);
        if (Arrays.asList(values).contains("C0010")) {
        	frBean.setCntributflg("1");
        } else {
        	frBean.setCntributflg("0");
        }
        frBean.setContentid(request.getParameter("idFr"));
        frBean.setTitle(request.getParameter("titleFr"));
        frBean.setDescription(request.getParameter("textFr"));
        frBean.setScript(request.getParameter("scriptFr"));
        list.add(frBean);

        //フリー
        ReserveContentsBean tmBean = new ReserveContentsBean();
        tmBean.setBoothid(boothid);
        tmBean.setOwnerid(ownerid);
        tmBean.setDelflg("0");
        tmBean.setCreateymd(createymd);
        if (Arrays.asList(values).contains("C0011")) {
        	tmBean.setCntributflg("1");
        } else {
        	tmBean.setCntributflg("0");
        }
        tmBean.setContentid(request.getParameter("idTm"));
        tmBean.setTitle(request.getParameter("titleTm"));
        tmBean.setDescription(request.getParameter("textTm"));
        tmBean.setScript(request.getParameter("scriptTm"));
        list.add(tmBean);

        //コンテンツ1
        ReserveContentsBean c1Bean = new ReserveContentsBean();
        c1Bean.setBoothid(boothid);
        c1Bean.setOwnerid(ownerid);
        c1Bean.setDelflg("0");
        c1Bean.setCreateymd(createymd);
        if (Arrays.asList(values).contains("C0012")) {
        	c1Bean.setCntributflg("1");
        } else {
        	c1Bean.setCntributflg("0");
        }
        c1Bean.setContentid(request.getParameter("idC1"));
        c1Bean.setTitle(request.getParameter("titleC1"));
        c1Bean.setDescription(request.getParameter("textC1"));
        c1Bean.setScript(request.getParameter("scriptC1"));
        list.add(c1Bean);

        //コンテンツ2
        ReserveContentsBean c2Bean = new ReserveContentsBean();
        c2Bean.setBoothid(boothid);
        c2Bean.setOwnerid(ownerid);
        c2Bean.setDelflg("0");
        c2Bean.setCreateymd(createymd);
        if (Arrays.asList(values).contains("C0013")) {
        	c2Bean.setCntributflg("1");
        } else {
        	c2Bean.setCntributflg("0");
        }
        c2Bean.setContentid(request.getParameter("idC2"));
        c2Bean.setTitle(request.getParameter("titleC2"));
        c2Bean.setDescription(request.getParameter("textC2"));
        c2Bean.setScript(request.getParameter("scriptC2"));
        list.add(c2Bean);

        //クローズ
        ReserveContentsBean clBean = new ReserveContentsBean();
        clBean.setBoothid(boothid);
        clBean.setOwnerid(ownerid);
        clBean.setDelflg("0");
        clBean.setCreateymd(createymd);
        if (Arrays.asList(values).contains("C0090")) {
        	clBean.setCntributflg("1");
        } else {
        	clBean.setCntributflg("0");
        }
        clBean.setContentid(request.getParameter("idCl"));
        clBean.setTitle(request.getParameter("titleCl"));
        clBean.setDescription(request.getParameter("textCl"));
        clBean.setScript(request.getParameter("scriptCl"));
        list.add(clBean);

        return list;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
