package pulse.com.br.app.config.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MyFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getHeader("Authorization") != null) {
            System.out.println(request.getHeader("Authorization"));
            Authentication auth = TokenUtil.decode(request);
            if(auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            else {
                System.out.println("Error no token");
                response.setStatus(401);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
