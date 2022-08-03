package fr.m2i.webdistributeur.filter;

import fr.m2i.webdistributeur.entity.Role;
import fr.m2i.webdistributeur.entity.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RoleFilter implements Filter {

    private ServletContext context;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("RoleFilter initialized");
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        // On cast en HttpServletRequest pour ensuite récupérer la session
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        User user = session !=null ? (User)session.getAttribute("user"): null;

        // On vérifie qu'un user est connecté
        boolean isLoggedIn = session != null && user!= null;

        
        if (isLoggedIn) {
            String urlPattern = getUrlPattern(req);
            Role role = user.getRole();
            
            if(urlPattern !=null){
                //if( (user.getRole() == Role.ADMIN)&&("admin/*".equals(urlPattern))
                //  ||(user.getRole() == Role.PROVIDER)&&("provider/*".equals(urlPattern))
                 // ||(user.getRole() == Role.USER)&&("customer/BuyProduct*".equals(urlPattern))
                //  )
                if( (user.getRole() == Role.ADMIN)&&(urlPattern.startsWith("/admin/"))
                  ||(user.getRole() == Role.PROVIDER)&&(urlPattern.startsWith("/provider/"))
                  ||(user.getRole() == Role.USER)&&(urlPattern.startsWith("/customer/"))
                  )
                {
                    chain.doFilter(request, response);
                    return;
                }
                
            }
        }
        
         this.context.log("vous n'avez pas les drois d'accés a cette page");
         //request.getRequestDispatcher("/META-INF/login.jsp").forward(request, response);
         resp.sendRedirect("../LoginServlet");


       
    }
    
    private static String getUrlPattern(HttpServletRequest request) {
      ServletContext servletContext = request.getServletContext();
      String servletPath = request.getServletPath();
      String pathInfo = request.getPathInfo();

      String urlPattern = null;
      if (servletPath != null) {
         urlPattern = servletPath + "/*";
      }
       return urlPattern;
    }
      
    public void destroy() {
        //we can close resources here
    }
}
