package com.ohap.monitor.owner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MonitorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {

    }

    @Test
    public void jwt() throws Exception {
/*		String secretKey = "abcdefghijklmnopqrstuvwxyz";
		Claims claims = Jwts.claims().setSubject("niceboyone");
		claims.put("roles", Arrays.asList("ADMIN"));
		Date now = new Date();
		String result = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + 60 * 60 * 1000L))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();



		System.out.println("");
		System.out.println("==========");
		System.out.println(result);
		System.out.println("");
		System.out.println(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(result).getBody().getSubject());
		System.out.println("");
		System.out.println("==========");

		System.out.println(passwordEncoder.encode("password"));

		System.out.println("");

		System.out.println(provider.getEmailFromJWT("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXdAZ21haWwuY29tIiwiaWF0IjoxNTY0Mzc3NjQxLCJleHAiOjE1NjQzODEyNDF9 .9 AKXCeU_uUGbH2vaUCm_bFGCDq5lXOUDrbRoZ2pbyS0"));*/
    }

}
