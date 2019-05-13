package com.base.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class PrivateDefinedClassLoder extends ClassLoader {
	private String root;
	public Class<?> findClass(String className) throws ClassNotFoundException{
		byte[] classData = loadClassData(className);
		if(null == classData){
			throw new ClassNotFoundException();
		}else{
			Class<?> classLoader = defineClass(className,classData,0,classData.length);
			return classLoader;
		}
	}
	public byte[] loadClassData(String className){
		
		String fileName = root+File.separatorChar+ className.replace(".", File.separator)+".class";
		
		try {
			FileInputStream ins = new FileInputStream(fileName);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];
			int length=0;
			while((length=ins.read(buffer))!=-1){
				bos.write(buffer, 0, length);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
	
	public static void main(String[] args) {
		
		ClassLoader loader = new PrivateDefinedClassLoder();
        System.out.println("loader--->" + loader);
        System.out.println("loader parent--->" + loader.getParent());
		try {
			 Class<?> c3 = loader.loadClass("com.base.classload.PersionObject");
			Object obj3 = c3.newInstance();
	        Field f = c3.getDeclaredField("age");
	        System.out.println(f.get(obj3));//15
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
