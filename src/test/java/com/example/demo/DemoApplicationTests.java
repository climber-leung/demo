package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Closeable;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Test
	public void proxyTest() {

		Object
		//增强print方法
		PrintStream printStream = new PrintStream(System.out);
		Class<PrintStream> aClass = PrintStream.class;
		Class<?>[] interfaces = aClass.getInterfaces();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(aClass);
		enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            if (method.getName().equals("println")) {
                return methodProxy.invokeSuper(o, new Object[]{"a=10,b=20"});
            }
            return methodProxy.invokeSuper(o, objects);
        });
		PrintStream out = System.out;
		PrintStream o = (PrintStream)enhancer.create(new Class[]{OutputStream.class}, new Object[]{out});

		System.setOut(o);

		int a = 20;
		int b = 10;

		System.out.println("a="+a+",b="+b);

	}






}
