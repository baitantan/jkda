package com.cs.log4j;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenshuai
 * @date 2020/2/16 15:12
 * HelloWord.class
 */
public class HelloWord {
    static {


    }
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("HelloWord");
        logger.info("Hello Word!");
    }
}
