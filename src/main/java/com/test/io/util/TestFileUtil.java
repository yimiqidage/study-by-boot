package com.test.io.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestFileUtil {

    //换行分隔符 \r\n
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    //文件分隔符 /
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    //路径分隔符 ;
    private static final String PATH_SEPARATOR = System.getProperty("path.separator");

    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    
    private static final String SUCCESS="SUCCESS";
    
    private static final String ERROR="ERROR";
    
    public static void main(String[] args) {
        
        writeToFile("F:\\test", "test.txt", "192.168.100.251", true);
    }
    
    public static String writeToFile(String path,String fileName,Object content,boolean isAppend) {
        if(content==null) return ERROR;
        String result = SUCCESS;
        
        FileWriter fw = null;
        try{
            if(!path.endsWith(FILE_SEPARATOR)){
                path += FILE_SEPARATOR;
            }
           
            File pathFile = new File(path); 
            if(!pathFile.exists()){
                pathFile.mkdirs();
            }
            String filePath = path+fileName;
            fw = new FileWriter(filePath,isAppend);
            fw.write(convertToString(new Date())+"  "+content.toString());
            fw.write(LINE_SEPARATOR);
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
            result = ERROR;
        }catch(Exception e){
            e.printStackTrace();
            result = ERROR;
        }finally{
            if(fw!=null){ //一定要判断是否为null。因为假如fw在创建的时候，文件不存在，会抛出FileNotFoundException ，fw就会是null。
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    result = ERROR;
                }
            }
        }
        
        return result;
    }
    
    public static String convertToString(Object o){
        if(o instanceof Date){
            Date date = (Date)o;
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DEFAULT);
            return sdf.format(date);
        }else if(o instanceof Number){
            return o.toString();
        }else{
            return o.toString();
        }
    }
}
