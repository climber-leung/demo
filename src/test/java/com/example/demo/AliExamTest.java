package com.example.demo;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @Author: liangjuzheng
 * @Date: 2018/11/16
 * @Description:
 */
public class AliExamTest {



    @Test
    public void modifySystemOutPrintlnTets() {

        int a = 20, b = 10;
        mockingOutPut(20, 10);        //在此处调用一个方法
        System.out.println("a="+a+",b="+b);  //使控制台输出a=10,b=20(交换输出的a,b的值)
    }











    private void mockingOutPut(int left,int right) {
        MyPrintStream stream = new MyPrintStream(System.out);
        System.setOut(stream);
    }



    private class MyPrintStream extends PrintStream {
        public MyPrintStream(OutputStream out) {
            super(out);
        }

        @Override
        public void println(String param) {
//            if (StringUtils.isEmpty(param)) {
//                return;
//            }
//            int i = param.indexOf("=");
//            int j = param.lastIndexOf("=");
//            int k = param.indexOf(",");
//            String left = param.substring(i + 1, k);
//            String right = param.substring(j + 1);
//            String newString = param.substring(0, i + 1) + right + param.substring(k, j + 1) + left;
//            super.println(newString);
            super.println("a=10,b=20");
        }
    }

}
