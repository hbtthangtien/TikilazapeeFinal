/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import DAO.UserDAO;
import Model.User.User;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hbtth
 */
public class filterAuthentication implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public filterAuthentication() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("filterAuthentication:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("filterAuthentication:DoAfterProcessing");
        }

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("filterAuthentication:doFilter()");
        }

        doBeforeProcessing(request, response);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserDAO db = new UserDAO();
        String username = null;
        String password = null;
        Cookie[] cookie = req.getCookies();
        if (cookie != null) {
            for (Cookie i : cookie) {
                if (i.getName().equals("c_s_u_tikilazapee")) {
                    username = i.getValue();
                }
                if (i.getName().equals("c_s_p_tikilazapee")) {
                    password = i.getValue();
                    i.setMaxAge(0);
                    resp.addCookie(i);
                }
                if (i.getName().equals("c_s_p_tikilazapee")) {
                    password = i.getValue();
                    i.setMaxAge(0);
                    resp.addCookie(i);
                }
            }
            HttpSession session = req.getSession();
            User user = db.getUser(username, password);
            if (user != null) {
                // check the account is authenticated, isn't it, if isn't asked user to authenticate account
                if (user.isAuth() == false) {
                    User userIsNotAuth = new User();
                    userIsNotAuth.setUsername(username);
                    userIsNotAuth.setEmail(user.getEmail());
                    // insert  user into session(username, email)
                    session.setAttribute("s_u_is_check_authentication", user);
                    resp.sendRedirect("sendcaptcha");
                    //
                } else if (user.isAuth() == true) {
                    session.setAttribute("s_u_tikilazapee", user);
                    Cookie c_u_tikilazapee = new Cookie("c_s_u_tikilazapee", username);
                    Cookie c_p_tikilazapee = new Cookie("c_s_p_tikilazapee", user.getPassword());
                    c_p_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                    c_u_tikilazapee.setMaxAge(60 * 60 * 24 * 3);
                    resp.addCookie(c_u_tikilazapee);
                    resp.addCookie(c_p_tikilazapee);
                    resp.sendRedirect("home");
                }
            }

        }

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {

            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("filterAuthentication:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("filterAuthentication()");
        }
        StringBuffer sb = new StringBuffer("filterAuthentication(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
