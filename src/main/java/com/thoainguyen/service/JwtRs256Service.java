package com.thoainguyen.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.SignatureAlgorithm;
import java.security.KeyPair;
import java.util.Date;
import java.util.List;

public class JwtRs256Service {
  private static KeyPair keyPair = Jwts.SIG.RS256.keyPair().build();

  public String createJWT(String id, String issuer, String subject, List<String> permissions, long ttlMillis) {
    SignatureAlgorithm alg = SIG.RS256;
    long nowMillis = System.currentTimeMillis();
    long expMillis = nowMillis + ttlMillis;
    Date exp = new Date(expMillis);
    JwtBuilder b = Jwts.builder()
      .id(id)
      .issuer(issuer)
      .subject(subject)
      .claim("permissions", permissions)
      .expiration(exp);
    return b.signWith(keyPair.getPrivate(), SIG.RS256).compact();
  }

  public Claims decodeJWT(String jwt) {
    JwtParser parser = Jwts.parser().verifyWith(keyPair.getPublic()).build();
    Jws<Claims> claims = parser.parseSignedClaims(jwt);
    return claims.getPayload();
  }
}
