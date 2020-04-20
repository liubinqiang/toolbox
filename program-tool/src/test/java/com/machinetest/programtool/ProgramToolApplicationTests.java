package com.machinetest.programtool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProgramToolApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramToolApplicationTests.class);

    @Test
    public void contextLoads() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        LOGGER.info(address.getHostName());
    }

}
