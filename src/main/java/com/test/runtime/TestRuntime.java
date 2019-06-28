package com.test.runtime;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author shiwei 2013-3-22 <br/>
 * Runtime可以获得程序的运行环境，并且执行指定的应用程序。<br/>
 * 	注意两点：<br/>
 * 		1.取得对象的方式：Runtime.getRuntime();<br/>
 * 		2.抛出异常，注意处理。<br/>
 * 		3.可以指定应用程序打开特定的文件，中间添加空格即可，文件名称为全路径。
 */
public class TestRuntime {

	
	/**
	 * 测试runtime的exec()方法。
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void runtime() throws IOException,InterruptedException{
		
		Runtime r = Runtime.getRuntime();
		
		//test1:执行指定的程序。
		r.exec("notepad.exe");
		
		//test2:指定程序，打开对应的文件。
		r.exec("notepad.exe D:\\test\\qiushi.txt");
		
		//test3：执行指定程序，并获得当前进程，然后杀死。使用distory方法。
		Process p = r.exec("notepad.exe");
		Thread.sleep(1000);
		p.destroy();
		
	}
	
	
	/**
	 * Runtime的 exec(String command, String[] envp, File dir) 方法的演示
	 * @title execute
	 * @param command
	 * @param envp
	 * @param executePath
	 * @return 执行结果：0为执行成功
	 */
    public static int execute(String command, String[] envp, File executePath) {
        int exitVal = -1;
        try {
            Runtime rt = Runtime.getRuntime();

            // jdk的说明文档上是这么描述这个方法的：在指定环境和工作目录的独立进程中执行指定的命令和变量
            // exec(String command, String[] envp, File dir)
            // 其中：command 是需要执行的命令；envp是设置环境变量，格式为：name=value；dir
            // 是子进程的工作目录，也就是执行命令时的目录。这个很有用，比如在加载多个jar包时，就可以在jar包的路径下执行
            Process proc = rt.exec(command, envp, executePath);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;

            // 获取输出的日志信息
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            
            InputStream is = proc.getInputStream();
            InputStreamReader isr2 = new InputStreamReader(is);
            BufferedReader br2 = new BufferedReader(isr2); 
            String line2 = null;
            // 获取输出的日志信息
            while ((line2 = br2.readLine()) != null) {
                System.out.println(line2);
            }
            
            exitVal = proc.waitFor();
            // exitVal=0 表示编译成功
            if (exitVal == 0) {
                System.out.println("编译成功");
            } else {
                System.out.println("编译失败");
            }
            
            
        }catch (Throwable t) {
            t.printStackTrace();
        }
        return exitVal;
    }
	public static void compile() {
        try {

                // 拼接所有的jar包，编译时，使用
                String jars = getJar();
               
                //编译java文件后，class文件的存放路径
                String exportFile = "F:\\compile\\test\\WEB-INF\\classes\\";
                File export = new File(exportFile);
                if(!export.exists()){
                    export.mkdirs();
                }
                String compileSourceFile = "F:\\compile\\test\\Test.java";
                // -encoding UTF-8 是指定编码
                // -d exportFile 是指定输出文件的路径
                String command = "javac -encoding UTF-8 -d " + exportFile + " -classpath .;" + jars +" "+compileSourceFile;
                
                //在这个路径下，执行cmd命令
                File executePath = new File("F:\\compile\\test\\");
                
                int result = execute(command, null, executePath);
                
                executePath = new File("F:\\compile\\test\\WEB-INF\\classes");
                //如果编译成功，执行java文件：
                if(result==0){
                    execute("java Test", null, executePath);
                }
                
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
	
	/**
     * 拼接所有的jar包，编译时使用，拼接后的样式为： a.jar;b.jar;c.jar;
     * @title getJar
     * @return
     */
    public static String getJar(){
       String path = "F:\\compile";
       File pathFile = new File(path);
       StringBuffer sb = new StringBuffer();
       if(pathFile.isDirectory()){
           File[] files = pathFile.listFiles();
           if(files!=null && files.length>0){
              for(File file:files){
                 sb.append(file.getName()).append(";");
              }
           }
       }
       return sb.toString();
    }
    
	/**
	 * 
	 */
	public static void main(String[] args) throws Exception {
	    compile();
	}

}
