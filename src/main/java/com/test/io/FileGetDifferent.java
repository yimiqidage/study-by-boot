package com.test.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
* @classname FileGetDifferent
* @description 找到目录a下，比目录b下缺少的文件，并进行拷贝。目前只是拷贝了jar，并且在同一个目录下
* @author shiwei
* @date 2014-6-10 下午1:45:35
*
*/ 
public class FileGetDifferent {

    public static final String fromFilePath = "E:\\workspace\\koo-framework-sso\\WebContent\\WEB-INF\\lib\\";
    
    public static final String toFilePath = "E:\\test\\notContainFile\\";
    
    public static void main(String[] args) throws Exception {
        String fp1 = "E:\\workspace\\koo-framework-sso\\WebContent\\WEB-INF\\lib";
        String fp2 = "E:\\workspace_c2c\\pay\\WebRoot\\WEB-INF\\lib";
        List<String>notContain = execute(fp1, fp2);
        for(String fp:notContain){
            copyFile(fp);
        }
    }
    
    public static void copyFile (String filePath) throws IOException {
        
        filePath = filePath.replaceAll("\\\\", "/");
        
        String toPath = toFilePath+filePath;
        toPath = toPath.replaceAll("\\\\", "/");
        
        FileInputStream fis = new FileInputStream(new File(fromFilePath+filePath));
        String path = toPath.substring(0, toPath.lastIndexOf("/"));
        if(!new File(path).exists()){
            new File(path).mkdirs();
        }
        File toFile = new File(toPath);
        if(!toFile.exists()){
            toFile.createNewFile();
        }
        
        int count = -1;
        byte buf[] = new byte[2048];
        FileOutputStream fos = new FileOutputStream(toFile);    
        BufferedOutputStream bos = new BufferedOutputStream(fos, 2048);   
        while((count = fis.read(buf)) > -1){    
            bos.write(buf, 0, count );    
        }
        bos.close();
        fos.close();    
        fis.close(); 
        System.out.println("成功复制文件："+filePath);
    }
    
    /**
     * fp1的文件，在fp2中没有的。
     * @title execute
     * @param fp1
     * @param fp2
     */
    public static List<String> execute(String fp1,String fp2){
        List<String>fileList1 = new ArrayList<String>();
        CompareUtil.getFile(fileList1, fp1);
        
        List<String>fileList2 = new ArrayList<String>();
        CompareUtil.getFile(fileList2, fp2);
        
        List<String>notContain = new ArrayList<String>();
        for(String fp:fileList1){
            fp = fp.substring(fp.lastIndexOf("/")+1);
            
            boolean isIn = false;
            for(String fp_1:fileList2){
                if(fp_1.indexOf(fp)>-1){
                    isIn = true;
                    break;
                }
            }
            if(!isIn){
                notContain.add(fp);
                System.out.println(fp);
            }
        }
        System.out.println(notContain.size()+","+fileList1.size()+","+fileList2.size());
        return notContain;
    }
    
    
}
