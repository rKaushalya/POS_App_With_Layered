package lk.ijse.JavaEE.filter;

import lk.ijse.JavaEE.util.ResponseUtil;

import javax.json.JsonObject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*",filterName = "B")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Auth Filter Init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println("Auth Filter Do Filter Invoked");

        String auth = req.getHeader("Auth");

        if (auth != null && auth.equals("user=admin,pass=admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.addHeader("Content-Type", "application/json");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            JsonObject jsonObject = ResponseUtil.genJson("Auth-Error", "You are not Authenticated to use this Service.!");
            res.getWriter().print(jsonObject);
        }

        String method = req.getMethod();
        if (method.equals("OPTIONS")){
            res.setStatus(200);
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.addHeader("Access-Control-Allow-Methods", "PUT, DELETE");
            res.addHeader("Access-Control-Allow-Headers", "content-type,auth");
        }else{
            res.addHeader("Access-Control-Allow-Origin", "*");
        }

    }

    @Override
    public void destroy() {

    }
}
