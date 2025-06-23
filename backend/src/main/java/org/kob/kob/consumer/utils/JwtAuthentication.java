package org.kob.kob.consumer.utils;

import io.jsonwebtoken.Claims;
import org.kob.kob.utils.JwtUtil;

public class JwtAuthentication {

    public static Integer getUserId(String token){
        int userId = -1;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userId;
    }
}
