package com.thoainguyen.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import javax.crypto.SecretKey;

public class JwtHs256Service {
  private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";

  public String createJWT(String id, String issuer, String subject, List<String> permissions, long ttlMillis) {
    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    long nowMillis = System.currentTimeMillis();
    long expMillis = nowMillis + ttlMillis;
    Date exp = new Date(expMillis);
    JwtBuilder b = Jwts.builder()
      .id(id)
      .issuer(issuer)
      .subject(subject)
      .claim("permissions", permissions)
      .expiration(exp);
    return b.signWith(key, SIG.HS256).compact();
  }

  public Claims decodeJWT(String jwt) {
    SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    JwtParser parser = Jwts.parser().verifyWith(key).build();
    Jws<Claims> claims = parser.parseSignedClaims(jwt);
    return claims.getPayload();
  }
}
