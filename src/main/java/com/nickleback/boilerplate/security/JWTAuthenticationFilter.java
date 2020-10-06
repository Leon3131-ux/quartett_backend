package com.nickleback.boilerplate.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nickleback.boilerplate.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        super();
        this.authenticationManager = authenticationManager;
        this.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
    }

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) {

        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(user.getAuthorities());

        String token = JWT.create().withSubject(user.getUsername())
                .withArrayClaim("permissions", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .toArray(String[]::new)
                )
                .withClaim("username", user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_DATE))
                .sign(Algorithm.HMAC256(SecurityConstants.SECRET.getBytes()));

        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }

}
