package com.io.serializable;

/**
 * Create with IntelliJ IDEA
 * Create By zhangwenbao
 * 测试序列化对象到本地文件
 * 测试从文件反序列化对象
 */
public class TestFileSerializable {
    public static void main(String[] args){
        SerializableFileHelper serializableFileHelper = new SerializableFileHelper("E:/serializedObject.txt");

        SerializableObj serializableObj = new SerializableObj();
        serializableObj.setName("发福了大哥");
        serializableObj.setYear(30);
        serializableObj.setCity("上海");
        serializableFileHelper.saveObjToFile(serializableObj);//存入SerializableObj对象
        SerializableObj obj = (SerializableObj)serializableFileHelper.getObjFromFile();//取出SerializableObj对象
        System.out.println(obj.getName());
        System.out.println(obj.toString());
    }
}
