package com.forkliu.protobuf;

import org.junit.Assert;
import org.junit.Test;

public class TestDataInfo {
    @Test
    public void testStudent(){
        DataInfo.Student student = DataInfo.Student.newBuilder().
                setName("张三").setAge(20).setAddress("北京").build();

        //将对象转译成字节数组,序列化


        byte[] student2ByteArray = student.toByteArray();

        //将字节数组转译成对象,反序列化
        try {
            DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

            System.out.println(student2.getName());
            System.out.println(student2.getAge());
            System.out.println(student2.getAddress());
        }catch (Exception e){
            Assert.assertFalse(true);
        }
    }
}
