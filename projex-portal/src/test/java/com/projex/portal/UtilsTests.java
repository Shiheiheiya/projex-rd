package com.projex.portal;

import com.projex.portal.utils.TokenTools;
import com.projex.portal.vo.TokenResult;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UtilsTests {
    @Test
    public void testToken(){
        String token = TokenTools.getToken(1001, "admin");
        TokenResult usernameByToken = TokenTools.getUsernameByToken(token);
        System.out.println(usernameByToken);
    }
}
