package com.wyj.jvm.BeanUtilsTest;

import org.springframework.beans.BeanUtils;

/**
 * Created by 2018/9/10
 *
 * 所以：Double和double在copy的时候，在为null的时候会出现异常，因为double不能为null，而Double是可以为null的
 */
public class CopyTest {

    public static void main(String[] args) {
        double d = 145.122;
        Double D = null;

        UserSource userSource = new UserSource(D);
        UserTarget userTarget = new UserTarget(d);
        BeanUtils.copyProperties(userSource, userTarget);

        System.out.println(userTarget.getD());
    }
}
