
package com.mycompany.onlineresturant.Configuration;

import com.mycompany.onlineresturant.Model.Users;
import java.time.Instant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author asus
 */
public class JwtToken {
    public static String createJwt(Users u){
        //save current timestamp to the variable
        Instant now = Instant.now();
        //Take out user information we wanted to add to the payload of the token
        Integer id = u.getId();
        String name = u.getName()+" "+ u.getFamily();
        String email = u.getEmail();
        Integer category = u.getCategory();
        
        String token = Jwts.builder()
                .setIssuer("Hx")
                .setSubject("Backend2")
                .claim("id", id)
                .claim("name", name)
                .claim("email", email)
                .claim("category", category)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=")
                )
                .compact();
        
        return token;
    }
    
    public static int decodeJwt(String token){
        try{
            byte[] secret = Base64.getDecoder().decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=");
            Jws<Claims> result;
            result = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secret)).parseClaimsJws(token);
            int id = result.getBody().get("id", Integer.class);
            String email = result.getBody().get("email", String.class);
            String name = result.getBody().get("name", String.class);
            System.out.println("token id: " + id);
            System.out.println("token email: " + email);
            
            Users u = new Users(id);
            System.out.println("id: " + u.getId());
            System.out.println("email: " + u.getEmail());
            
            if(u.getId() == id && u.getEmail().equals(email) && u.getCategory()== 1){
                return 1;
            }
            else if (u.getCategory() != 1){
                return 2;
            }
            else{
                return 3;
            }
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 4;
        }
    }
}
