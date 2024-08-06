package com.thoainguyen.service;

import io.jsonwebtoken.Claims;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtRs256ServiceTest {
  private JwtRs256Service jwtRs256Service = new JwtRs256Service();

  @Test
  void test() {
    String jwt = jwtRs256Service.createJWT("1", "thoai", "chris", List.of("read", "write", "delete"),10000);
    Claims claims = jwtRs256Service.decodeJWT(jwt);
    Assertions.assertEquals(claims.getIssuer(), "thoai");
    Assertions.assertEquals(claims.getSubject(), "chris");
    Assertions.assertEquals(claims.get("permissions"),  List.of("read", "write", "delete"));
  }
}
