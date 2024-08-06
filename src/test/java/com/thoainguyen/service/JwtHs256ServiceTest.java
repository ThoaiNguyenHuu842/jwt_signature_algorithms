package com.thoainguyen.service;

import io.jsonwebtoken.Claims;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtHs256ServiceTest {
  private JwtHs256Service jwtHs256Service = new JwtHs256Service();

  @Test
  void test() {
    String jwt = jwtHs256Service.createJWT("1", "thoai", "chris", List.of("read", "write", "delete"),10000);
    Claims claims = jwtHs256Service.decodeJWT(jwt);
    Assertions.assertEquals(claims.getIssuer(), "thoai");
    Assertions.assertEquals(claims.getSubject(), "chris");
    Assertions.assertEquals(claims.get("permissions"),  List.of("read", "write", "delete"));
  }
}
