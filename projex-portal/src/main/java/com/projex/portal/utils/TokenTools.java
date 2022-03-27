package com.projex.portal.utils;

import com.projex.portal.vo.TokenResult;
import io.jsonwebtoken.*;

import java.util.Date;

public class TokenTools {
    final static String base64EncodedSecretKey = "projex"; //私钥
    final static long TOKEN_EXP = 1000 * 60 * 60 * 24; //过期时间，24h

    public static String getToken(int userId, String username){
        JwtBuilder jwtBuilder = Jwts.builder();
        return jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("userId", userId)
                .claim("username", username)
                .setIssuedAt(new Date()) //当前时间
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP))
                //signature
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }
    public static TokenResult getUsernameByToken(String token){
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(base64EncodedSecretKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        int userId = (int) claims.get("userId");
        String username = (String) claims.get("username");
        Date issuedAt = claims.getIssuedAt();
        Date expiration = claims.getExpiration();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setUserId(userId);
        tokenResult.setUsername(username);
        tokenResult.setTokenBeginTime(issuedAt);
        tokenResult.setTokenEndTime(expiration);
        return tokenResult;
    }
}
