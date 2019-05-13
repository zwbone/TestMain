package com.io.serializable;

import java.io.*;

/**
 * Create with IntelliJ IDEA
 * Create By zhangwenbao
 * Date:{$Date}
 * Time:{TIME}
 */
public class SerializableFileHelper {

    private String fileName;
    public SerializableFileHelper(String fileName){
        this.fileName=fileName;
    }
    /*
     * 将SerializableObj
     * 对象保存到文件中
     */
    public void saveObjToFile(SerializableObj Obj){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(Obj);
            oos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*
     * 从文件中读出对象
     */
    public Object getObjFromFile(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            Object obj = ois.readObject();
            return obj;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
