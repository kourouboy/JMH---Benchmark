package com.kourou.cases;
;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * 目标：找到com.kourou.cases.cases
         * 转换:能找到存放 com.kourou.cases.cases 这个包的目录 扫描这个目录下的所有*.class
         */

        /**怎么找到目录**/
        ClassLoader classLoader = Main.class.getClassLoader();
        Enumeration<URL> url = classLoader.getResources("com/kourou/cases/cases");
        //迭代器
        while (url.hasMoreElements()){
            URL url1 = url.nextElement();

           /* System.out.println(url1.getPath());//找到类所在位置
            System.out.println(url1.getProtocol());
            System.out.println("__________________");
*/
           //只能处理*.class的情况，无法处理JAR包
            File dir = new File(url1.getPath());
            if (!dir.isDirectory()){
                continue;
            }
            File[] files = dir.listFiles();
            if (files == null){
                continue;
            }
            for (File file : files){
                String filename = file.getName();
                String className = filename.substring(0,filename.length() - 6);


                Class<?> cls = Class.forName("com.kourou.cases.cases." + className);


                //利用Case接口，找出需要的class
                Class<?>[] interfaces = cls.getInterfaces();
                for (Class<?> interf : interfaces){
                    if (interf == Case.class){
                        System.out.println(className);
                    }
                }
            }
        }
    }

}
