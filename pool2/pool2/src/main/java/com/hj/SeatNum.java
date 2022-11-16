package com.hj;


import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class SeatNum {

    int num = 100;

    void doSomething() {
        System.out.println("引用前的座位数量：" + this.num);
        num -= 40;
        System.out.println("引用后的座位数量：" + this.num);
    }

    public static void main(String[] args) {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(5);
        genericObjectPoolConfig.setMinIdle(2);//最小空闲数量，也是默认初始化的数量
        genericObjectPoolConfig.setMaxIdle(2);
        genericObjectPoolConfig.setMinEvictableIdleTimeMillis(1000);
        genericObjectPoolConfig.setTestOnBorrow(true);// 引用对象后(对象已存在，且重复使用)调用验证validateObject（常用）
//        genericObjectPoolConfig.setTestOnReturn(true);// 停用对象前(对象已存在)调用验证validateObject（常用）
//        genericObjectPoolConfig.setTestOnCreate(true);// 创建对象(对象未存在)时验证validateObject（极少情况采用）
//        genericObjectPoolConfig.setTestWhileIdle(true);// 对象一直空闲时验证validateObject（极少情况采用）
        GenericObjectPool<SeatNum> objectPool = new GenericObjectPool<>(new SeatNumFactory(), genericObjectPoolConfig);

        SeatNum powerBank = null;
        for (int i = 0; i < 10; i++) {
            try {
                objectPool.preparePool();// 默认初始化
                System.out.println("====================【" + i + "】===================");
                powerBank = objectPool.borrowObject();
                powerBank.doSomething();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (powerBank != null) {
                    objectPool.returnObject(powerBank);
                }
            }
        }
    }


}