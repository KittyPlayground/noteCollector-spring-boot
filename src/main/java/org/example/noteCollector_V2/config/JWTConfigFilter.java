package org.example.noteCollector_V2.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Configuration
public class JWTConfigFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String intToken = request.getHeader("Authorization");
        String userEmail;
        String extractedUserJwtToken;

        if(StringUtils.isEmpty(intToken) || !intToken.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        extractedUserJwtToken = intToken.substring(7);
        userEmail = extractedUserJwtToken.substring(0,extractedUserJwtToken.indexOf("."));
        request.setAttribute("userEmail",userEmail);
        filterChain.doFilter(request,response);



    }
}
