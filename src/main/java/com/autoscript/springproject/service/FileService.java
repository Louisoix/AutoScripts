package com.autoscript.springproject.service;

import java.io.*;

public class FileService {
    //upload file
    public static void uploadFile(byte[] rawfile, String path, String filename) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(path+"/"+filename);
        out.write(rawfile);
        out.flush();
        out.close();
    }
    //read a file
    public static String readFile(String path) throws IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader(path));
        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }

        String result = buffer.toString();
        return result;
    }
    //download a file
    public static String downloadFile(String path) throws IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader(path));
        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }

        String result = buffer.toString();
        return result;
    }
}
