package dairy.project.production.security.jwt;

import dairy.project.production.component.DairyUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

import static dairy.project.production.entity.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static dairy.project.production.entity.Constants.SIGNING_KEY;

@Component
public class TokenProvider implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);



    public String generateJwtToken(Authentication authentication) {

        DairyUser userPrincipal = (DairyUser) authentication.getPrincipal();
       System.out.println(new Date());
        return Jwts.builder()
		                .setSubject((userPrincipal.getUsername()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + ACCESS_TOKEN_VALIDITY_SECONDS))
		                .signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
		                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(SIGNING_KEY)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }

    }