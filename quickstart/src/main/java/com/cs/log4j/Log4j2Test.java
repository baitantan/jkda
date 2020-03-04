package com.cs.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenshuai
 * @date 2020/2/16 16:32
 * Log4j2Test.class
 */
public class Log4j2Test {

     /**
     * Logger和LoggerFactory导入的是org.slf4j包
     */
    private final static Logger logger = LoggerFactory.getLogger("Console");
    public static void main(String[] args) {
        

        System.out.println(logger.getName());
        logger.info("info");
        logger.debug("debug");
        //logger.error("error");
    }
}
