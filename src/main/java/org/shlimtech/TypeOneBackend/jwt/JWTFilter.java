package org.shlimtech.TypeOneBackend.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.shlimtech.TypeOneBackend.model.User;
import org.shlimtech.TypeOneBackend.service.UsersService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtProvider;
    private final UsersService usersService;


    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String token = getTokenFromRequest(request);
        if (token != null) {
            int id = jwtProvider.validateTokenAndRetrieveClaim(token);
            User user = usersService.getUserById(id);
            if (user == null) {
                throw new RuntimeException("no such user");
            }
            JWTAuthentication authentication = new JWTAuthentication(token, id);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}

