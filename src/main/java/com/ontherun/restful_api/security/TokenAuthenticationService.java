package com.ontherun.restful_api.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ontherun.restful_api.domain.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.IOException;
import java.util.List;

public class TokenAuthenticationService {
    private static final String SECRET = "mySecret";                      // JWT密码
    private static final String TOKEN_PREFIX = "Bearer";                // Token前缀
    private static final String HEADER_STRING = "Authorization";        // 存放Token的Header Key

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

    /**
     * Build JWT Token and return JWT Token to user
     */
    public static void addAuthentication(HttpServletResponse response, String userId) throws IOException {
        // Build JWT Token
        String jwt = Jwts.builder()
                // Need polish
                .claim("authorities", "ROLE_USER")
                .setSubject(userId)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        // Return JWT Token to user
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(TOKEN_PREFIX + " " + jwt);
    }

    /**
     * Authenticate JWT Token. Check if token is still valid.
     * @param request
     * @return Authentication
     */

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        logger.info("AWT Token: " + token);
        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))    // Remove Token prefix
                    .getBody();
            String userId = claims.getSubject();
            // Need polish
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList((String) claims.get("authorities"));
            if (userId == null ) return null;
            return new UsernamePasswordAuthenticationToken(userId, null, authorityList);
        }
        return null;
    }

}
