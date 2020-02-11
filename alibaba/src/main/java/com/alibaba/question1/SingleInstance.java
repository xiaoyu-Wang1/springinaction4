package com.alibaba.question1;

/**
 * 懒汉模式获取 SingleInstance 类的单例
 *
 * @author wangyuquan
 */
public class SingleInstance {

    public static SingleInstance getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static SingleInstance instance = new SingleInstance();
    }

    private SingleInstance() {
    }
}
