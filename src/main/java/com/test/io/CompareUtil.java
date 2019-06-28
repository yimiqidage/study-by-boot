package com.test.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class CompareUtil {

    /**
     * 读取修改文件列表，并转换成list
     * @title: getUpdateFileList
     * @param updateFileTxtPath
     * @return
     * @throws Exception
     */
    public static List<String> getUpdateFileList(String updateFileTxtPath) throws Exception{
        List<String> updateFileList = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(new File(updateFileTxtPath)));
        String line = null;
        //循环处理，得到所有的更新文件列表
        while((line=br.readLine())!=null){
            if(line.equals("") || line.startsWith("#")){ //过滤掉注释行
                continue;
            }
            line = line.replaceAll("\\\\", "/"); //将反斜杠\ 替换成 斜杠 /
            updateFileList.add(line);
        }
        return updateFileList;
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
                
                if(fileStr.indexOf("\\")>0){
                    fileStr =  fileStr.replaceAll("\\\\", "/");
                }
                fileList.add(fileStr);
            }
           
        }
    }
}
