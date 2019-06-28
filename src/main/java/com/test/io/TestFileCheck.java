package com.test.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
* @classname Check
* @description 检查一下，ROOT目录中是否包含了全部修改过的文件
* @author shiwei
* @date 2014-6-10 下午1:42:28
*
*/ 
public class TestFileCheck {

    public static void main(String[] args) throws Exception {
        
        check();
    }
    
    /**
     * 检查一下，ROOT目录中是否包含了全部修改过的文件
     * @title check
     * @throws Exception
     */
    public static void check() throws Exception{
        List<String>fileList = new ArrayList<String>();
        getFile(fileList, "D:/test/0610/ROOT");
        String fp = "D:/test/0610/shiwei/修改文件列表.txt";
        List<String>updatedFile = new ArrayList<String>();
        FileReader fr = new FileReader(fp);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line=br.readLine())!=null){
            if(line.equals("") || line.startsWith("#"))
                continue;
           
            if(line.endsWith(".java")){
                line = line.substring(0,line.length()-5);
            }
            
            updatedFile.add(line);
        }
        Set<String>set = new HashSet<String>();
        //直接使用list.contains 方法，针对 / 或者 \ 会有问题
        for(String updated:updatedFile){
            boolean isIn = false;
            for(String f:fileList){
                if(f.indexOf(updated)>-1){
                    isIn = true;
                    break;
                }
            }
            if(!isIn){
                set.add(updated);
            }

        }
        System.out.println(set);
    }
    
    /**
     * 获取所有的文件夹路径
     * @title getFile
     * @param fileList
     * @param allFiles
     */
    public static void getFile(List<String>fileList,String allFiles){
        
        File filePath = new File(allFiles);
        File[] files = filePath.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                getFile(fileList, file.getPath());
            }else{
                String fileStr = file.getPath();
                if(file.getPath().endsWith("class")){
                    fileStr= file.getPath().substring(0, file.getPath().length()-6);
                }
                if(fileStr.indexOf("\\")>0){
                    fileStr =  fileStr.replaceAll("\\\\", "/");
                }
                fileList.add(fileStr);
            }
           
        }
    }
 
   
}
