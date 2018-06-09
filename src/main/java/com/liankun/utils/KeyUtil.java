package com.liankun.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成主键
     * @return
     */
    public static synchronized String genUniqueKey(){

        Random random = new Random();

        Integer integer = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(integer);

    }
}
