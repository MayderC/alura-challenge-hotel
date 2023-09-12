package com.mayderc.hotel.infrastructure.helper;
import com.mayderc.hotel.application.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;


import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    public String getUsername(String token) {
        return  extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = getAllClaims(token);
        return  claimResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", ((User) userDetails).getId());
        claims.put("email", userDetails.getUsername());


        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        claims.put("role", roles);
        //print authoirities
        System.out.println("authorities: " + userDetails.getAuthorities());
        return generateToken(claims, userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        //print
        System.out.println("isTokenValid");
        final String username = getUsername(token);
        var result =(username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        //print result
        System.out.println("result: " + result);
        return result;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraCalims, UserDetails userDatails){
        //set role to extra claims from user details Role
        return Jwts
                .builder()
                .setClaims(extraCalims)
                .setSubject(userDatails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ( 1000 * 60 * 5)))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode("AAAAAAAASXXXXXXXDSDSDCSCSDCSDHCUYGFSDYUCSDOIUCSDHUYCSCGBSDUYCGSBUYASAXSAXSASXAS64X16SASX8A4XASX68A4");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
