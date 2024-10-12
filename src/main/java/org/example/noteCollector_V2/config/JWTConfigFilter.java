package org.example.noteCollector_V2.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.noteCollector_V2.service.JWTService;
import org.example.noteCollector_V2.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
@Configuration
public class JWTConfigFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String intToken = request.getHeader("Authorization");
        String userEmail;
        String extractedJwtToken;

        if(StringUtils.isEmpty(intToken) || !intToken.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        extractedJwtToken = intToken.substring(7);
        userEmail = jwtService.extractUserName(extractedJwtToken);
        // user email
        if(StringUtils.isNotEmpty(userEmail) &&
                SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails =
                    userService.userDetailsService().loadUserByUsername(userEmail);
            if(jwtService.validateToken(extractedJwtToken, userDetails)) {
                //add user to the securoty context
                SecurityContext emptyContext =
                        SecurityContextHolder.createEmptyContext();
                var authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                emptyContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(emptyContext);





            }

        }
        filterChain.doFilter(request, response);
    }
}
