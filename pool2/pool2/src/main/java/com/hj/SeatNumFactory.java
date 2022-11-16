package com.hj;


import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class SeatNumFactory implements PooledObjectFactory<SeatNum> {

    // 创建对象 或 引用现有对象
    @Override
    public PooledObject<SeatNum> makeObject() throws Exception {
        System.out.println("【创建对象】");
        return new DefaultPooledObject<SeatNum>(new SeatNum());
    }
    // 销毁对象
    @Override
    public void destroyObject(PooledObject<SeatNum> pooledObject) throws Exception {
        System.out.println("【销毁对象】,剩余数量="+pooledObject.getObject().num);
        pooledObject.deallocate();// 销毁
    }
    // 验证对象
    @Override
    public boolean validateObject(PooledObject<SeatNum> pooledObject) {
        System.out.println("【验证对象】数量="+pooledObject.getObject().num);
        return pooledObject.getObject().num > 0; // 对象的一个销毁条件
    }

    // 活动对象
    @Override
    public void activateObject(PooledObject<SeatNum> pooledObject) throws Exception {
        System.out.println("【活动对象】初始化前剩余数量="+pooledObject.getObject().num);
//        pooledObject.getObject().num = 100;
//        System.out.println("【活动对象】初始化后剩余数量="+pooledObject.getObject().num);
    }

    // 停用(归还)对象
    @Override
    public void passivateObject(PooledObject<SeatNum> pooledObject) throws Exception {
        System.out.println("【停用对象】数量="+pooledObject.getObject().num);

    }
}