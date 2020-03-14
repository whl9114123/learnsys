package com.whl.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {
    private static final String SECRET = "com.whl.jwt";
    private static final String ISSUER = "user";

    /**
     * 生成token
     *
     * @param claims
     * @return
     */
    public static String createToken(Map<String, String> claims) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTCreator.Builder builder = JWT.create()
                    .withIssuer(ISSUER)
                    //设置过期时间为2小时
                    .withExpiresAt(DateUtils.addHours(new Date(), 2));
            claims.forEach(builder::withClaim);
            String sign = builder.sign(algorithm);

            return sign;
        } catch (IllegalArgumentException e) {
            return null;
           
        }
    }

    /**
     * 验证jwt，并返回数据
     */
    public static Map<String, String> verifyToken(String token)  {
        Algorithm algorithm;
        Map<String, Claim> map = null;
        try {
            algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT jwt = verifier.verify(token);
            map = jwt.getClaims();

        } catch (Exception e) {
            log.error("鉴权失败");
        }finally {
            if (map==null){
               return null;
            }
            Map<String, String> resultMap = new HashMap<>();
            map.forEach((k, v) -> resultMap.put(k, v.asString()));
            return resultMap;
        }



    }

    public static void main(String[] args) {
        Map <String,String> a=new HashMap<>(11) ;
        Map <String,String> b=new HashMap<>(11) ;
        b.put("123","456");
        a.put("22","45ss6");
        String token = JwtUtils.createToken(a);
        String token1 = JwtUtils.createToken(b);
        System.out.println(token1);
        System.out.println(token);
        String to="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.JTdCJTIyMTIzJTIyJTNBJTIyNDVzczYlMjIlMkMlMjJpc3MlMjIlM0ElMjJ1c3NzZXIlMjIlMkMlMjJleHAlMjIlM0ExNTg0MTc5MTE4JTdEJTAwJTAw.JfPQnezwoB8-umDNmdOmlYgyTdzX6V-EN-xTNFQshLM";
       String t2="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.JTdCJTIyMjIlMjIlM0ElMjI0NWRzYXNzNiUyMiUyQyUyMmlzcyUyMiUzQSUyMnVzZXIlMjIlMkMlMjJleHAlMjIlM0ExNTg0MTc5MzMzJTdEJTAw.6zY3w3OkX7FLRBbr0mxanOU0QZI-vmknO4wzStV-bSw";
//        Map<String, String> stringStringMap = JwtUtils.verifyToken(to);
        Map<String, String> stringStringMap2= JwtUtils.verifyToken(t2);

        System.out.println(stringStringMap2);

    }
}