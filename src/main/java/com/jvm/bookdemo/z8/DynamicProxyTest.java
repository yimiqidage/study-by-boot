package com.jvm.bookdemo.z8;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author weishi8
 * @create 2019-05-13
 * @description
 */
public class DynamicProxyTest {
    interface IHello{
        public void sayHello();
    }
    static class Hello implements IHello{
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object orignProxy;

        Object bind (Object orignObj){
            this.orignProxy = orignObj;
            return Proxy.newProxyInstance(orignObj.getClass().getClassLoader(),orignObj.getClass().getInterfaces(),this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(orignProxy,args);
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello)new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}
