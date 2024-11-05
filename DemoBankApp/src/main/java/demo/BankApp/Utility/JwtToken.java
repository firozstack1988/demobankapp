package demo.BankApp.Utility;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;


public class JwtToken implements Serializable{

	private static final long TOKEN_VALIDITY= 1000;
	private static final long serialVersionUID=-1L;
	   // @Value("${jwt_secret}")
	    private final static String SECRET_KEY="A@bEdgh3sf";

		public static String generateToken(UserDetails userDetails) {
			Map<String,Object> claims=new HashMap<>();
			return doGenerateToken(claims,userDetails.getUsername());
		}
		
		private static String doGenerateToken(Map<String,Object> claims,String subject) {
			 
			return Jwts.builder().setClaims(claims).setSubject(subject)
					.setIssuedAt(new Date(System.currentTimeMillis())).
					setExpiration(new Date(System.currentTimeMillis()+TOKEN_VALIDITY *30))
					.signWith(SignatureAlgorithm.HS256,TextCodec.BASE64.encode(SECRET_KEY))
					.compact();
		}
}
