package jp.co.cococoa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class EncodeingFilter
 */
public class AuthFilter implements Filter {
    public String path1 = "login.jsp";
    public String path2 = "Login";
    public String path3 = "PostMessage";
    public String path4 = "Message";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        try{
            String target = ((HttpServletRequest)request).getRequestURI();

            HttpSession session = ((HttpServletRequest)request).getSession();
            if (!target.endsWith(path1)
            		&& !target.endsWith(path2)
            		&& !target.endsWith(path3)
            		&& !target.endsWith(path4)) {
                if (session == null){
                /* まだ認証されていない */
                    session = ((HttpServletRequest)request).getSession(true);
                    session.setAttribute("target", target);

                    ((HttpServletResponse)response).sendRedirect("Login");
                }else{
                    Object loginCheck = session.getAttribute("login");
                    if (loginCheck == null){
                        /* まだ認証されていない */
                        session.setAttribute("target", target);
                        ((HttpServletResponse)response).sendRedirect("Login");
                    }
                }
            }
            chain.doFilter(request, response);
        }catch (ServletException se){
        	se.printStackTrace();
        }catch (IOException e){
        	e.printStackTrace();
        }
  }

  public void init(FilterConfig filterConfig) throws ServletException{
  }

  public void destroy(){
  }
}
