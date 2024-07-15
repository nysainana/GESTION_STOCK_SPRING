package mg.njaka.gestionstock.config;

import mg.njaka.gestionstock.services.auth.ApplicationsUserDetailService;
import mg.njaka.gestionstock.utils.JwtUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApplicationRequestFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtil;
    private final ApplicationsUserDetailService applicationsUserDetailService;

    @Autowired
    public ApplicationRequestFilter(
            JwtUtils jwtUtil,
            ApplicationsUserDetailService applicationsUserDetailService
    ) {
        this.jwtUtil = jwtUtil;
        this.applicationsUserDetailService = applicationsUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String autHeader = request.getHeader("Authorization");

        String userEmail = null;
        String jwt = null;
        String idEntreprise = null;

        if(StringUtils.hasLength(autHeader) && autHeader.startsWith("Bearer ")){
            jwt = autHeader.substring(7);
            userEmail = jwtUtil.extractUsername(jwt);
            idEntreprise = jwtUtil.extractIdEntreprise(jwt);
        }

        if(StringUtils.hasLength(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = applicationsUserDetailService.loadUserByUsername(userEmail);
            if(jwtUtil.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        MDC.put("isEntreprise", idEntreprise);
        filterChain.doFilter(request, response);
    }
}
