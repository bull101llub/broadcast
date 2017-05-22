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

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        try{
            String target = ((HttpServletRequest)request).getRequestURI();

            HttpSession session = ((HttpServletRequest)request).getSession();

            if (session == null){
            /* まだ認証されていない */
                session = ((HttpServletRequest)request).getSession(true);
                session.setAttribute("target", target);

                ((HttpServletResponse)response).sendRedirect("/auth/Login");
            }else{
                Object loginCheck = session.getAttribute("login");
                if (loginCheck == null){
                    /* まだ認証されていない */
                    session.setAttribute("target", target);
                   ((HttpServletResponse)response).sendRedirect("/auth/Login");
            }
        }

        chain.doFilter(request, response);
    }catch (ServletException se){
    }catch (IOException e){
    }
  }

  public void init(FilterConfig filterConfig) throws ServletException{
  }

  public void destroy(){
  }

  /**

  <filter>
    <filter-name>AuthFilter</filter-name>
    <filter-class>AuthFilter1</filter-class>
  </filter>

  <filter-mapping>
    <filter-name>AuthFilter</filter-name>
    <url-pattern>/CustomAuth1</url-pattern>
  </filter-mapping>

  <servlet>
    <servlet-name>CustomAuth2</servlet-name>
    <servlet-class>CustomAuth6</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>CustomAuth2</servlet-name>
    <url-pattern>/CustomAuth2</url-pattern>
  </servlet-mapping>

  <servlet>
    <servlet-name>Logout</servlet-name>
    <servlet-class>Logout1</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>Logout</servlet-name>
    <url-pattern>/Logout</url-pattern>
  </servlet-mapping>
*/




}
