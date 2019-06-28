package com.test.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.test.io.file.TestFilterFileByName;


/**
 * @author shiwei 2013-3-27 <br/>
 * 
 * constractor()：示例几种File的构造函数的用法。
 * method(): 示例File的方法。
 * 		注意：①：file.getPath(); // 实践证明：File.getPath返回的是相对路径，实际上就是在构造file对象时，传入的path。
 * 				如果传入的path为D:\\demo.txt，则这个方法就返回D:\\demo.txt，如果传入的是demo.txt，返回的是demo.txt。使用三种构造函数，效果都是一样的。
 * 
 * 			  ②：file.getParent(); //返回所在目录的绝对路径，如：D:\\test\\demo.txt ，返回的是： D:\test
 * 
 * 			  ③：f.deleteOnExit(); //告诉虚拟机，要删除这个文件。好处就是，如果发生了异常之类的，直接使用f.delete() 不会执行后面的代码，这个可以写到前面，让虚拟机删除。
 * 			
 * 			  ④：创建文件，使用createNewFile，创建文件夹，使用mkdir、mkdirs
 * 
 * 			  ⑤：file.delete() : windows 的目录删除是从里往外删除，如果目录里面有内容，目录是不会删除的。已证实：linux下效果是一样的。
 * 
 * 			  ⑥：file.renameTo() : 如果源和目的文件不在一个目录下，则会剪切到要修改的目录下面。
 * 
 * 			  ⑦：file.list() , file.list(FilenameFilter filter) : 根据过滤器规则去过滤文件，如：只需要java文件，则过滤器可以参照 TestFilterFileByName.java .
 * 
 * 			  ⑧：
 * 
 * 
 */			  
public class TestFile {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		
	}

	/**
	 * 示例File的三个构造函数的用法。
	 * @SuppressWarnings("unchecked")
	 */
	public static void constractor() {
		
		 //File(String pathname) 
		//使用环境：只操作一个文件。
		
		
		File  f1 = new File("D:\\demo.txt");
		
		//File(String parent, String child) 
		//使用环境：路径固定，名称不固定。即操作同一个目录下的文件。
		@SuppressWarnings("unused")
		File f2 = new File("D:\\","demo.txt"); 
		
		File f = new File("D:\\");
		
		//File(File parent, String child)
		//使用环境：操作路径对象，即：路径不固定，名称固定。
		File f3 = new File(f,"demo.txt");  

	}
	
	public static void method() throws IOException{
		File f = new File("demo.txt");
		
		String absPath = f.getAbsolutePath();
		
		String path = f.getPath(); // 实践证明：File.getPath返回的是相对路径，实际上就是在构造file对象时，传入的path。如果传入的path为D:\\demo.txt，则这个方法就返回D:\\demo.txt，如果传入的是demo.txt，返回的是demo.txt。使用三种构造函数，效果都是一样的。
					
		String parent = f.getParent(); //返回传入路径path的上级目录。例如：如果传入D:\\demo.txt 返回 D:\\ ，如果传入 demo.txt ，返回是null。
		
		
		long l_date = f.lastModified();
		Date date = new Date(l_date);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str_date = format.format(date);
		
		
		f.createNewFile(); // 没有则创建，有就不创建。
		
		
		f.deleteOnExit(); //告诉虚拟机，要删除这个文件。好处就是，如果发生了异常之类的，直接使用f.delete() 不会执行后面的代码，这个可以写到前面，让虚拟机删除。
		
		System.out.println("name:"+f.getName()); //调用deleteOnExit 方法以后，可以继续操作file对象。
		
		
		File path_n = new File("D:\\test_file");
		path_n.mkdirs();  //创建文件夹使用mkdir , mkdirs
		
		
		
		File file_r1 = new File("D:\\abc.txt");
		boolean b_r1 = file_r1.createNewFile();
		
		File file_r2 = new File("E:\\def.txt");
		boolean b_r2 = file_r1.renameTo(file_r2); //renameTo : 如果源和目的文件不在一个目录下，则会剪切到要修改的目录下面。
		
		
		long freeSpace = path_n.getFreeSpace();
		System.out.println(b_r1+","+b_r2);
		
		System.out.println("absPath:"+absPath);
		System.out.println("path:"+path);
		System.out.println("parent:"+parent);
		System.out.println("date:"+str_date);
		
		
		System.out.println("freeSpace:"+freeSpace);
	}
	
	/**
	 * 
	 */
	public static void method2 (){
		
		String path = "D:\\test";
		File file = new File(path);
		FilenameFilter filter = new TestFilterFileByName(".java") ;
		String[] names = file.list(filter);
		if(names.length>0){
			for(String name:names){
				System.out.println(name);
			}
		}
		
	}
	
	
}
