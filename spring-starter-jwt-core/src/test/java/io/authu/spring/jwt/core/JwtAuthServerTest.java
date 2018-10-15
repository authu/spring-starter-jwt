package io.authu.spring.jwt.core;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2018/10/15.
 */
@Slf4j
public class JwtAuthServerTest {

    private JwtAuthServer jwtAuthServer;

    @Before
    public void setUp(){
        jwtAuthServer = new JwtAuthServer();
        ReflectionTestUtils.setField(jwtAuthServer, "properties", new JwtProperties());
    }

    @Test
    public void generateToken() {
        String token = jwtAuthServer.generateToken("test", "junit", "all", Duration.ofDays(9));
        log.info(token);
        Assert.assertNotNull(token);
    }

}