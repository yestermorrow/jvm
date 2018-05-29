package com.wyj.jvm.mock;


import mockit.Mocked;
import org.junit.Test;
import org.test4j.module.jmockit.IMockict;

/**
 * Created by wyj on 2018/5/4
 */
public class MyFirstJMockitTest {

    // 用@Mocked标注的对象，不需要赋值，jmockit自动mock
    @Mocked
    MockObject mockObject;

    @Test
    public void helloTest() {
        MockObject mockObject = new MockObject();
        //录制预期模拟行为
        new IMockict.Expectations() {{
            mockObject.hello("wanyajing");
            returns("Hello wanyajing");
            //也可以使用
            //result = "Hello wanyajing";
        }};
//        assertEquals

    }
}
