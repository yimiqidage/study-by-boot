package com.test.freemarker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 发送邮件，使用freemarker的service
 * @author weishi8
 *
 */
@Service
public class FreeMarkerTest {

	/**
	 * freemarker的模板位置
	 */
	public static final String FREEMARKER_PATH = "/freemarker/template/";

	/**
	 * 编码
	 */
	public static final String CHARSET_UTF8 = "UTF-8";


	/**
	 * 根据配置的freemarker文件名称，以及参数，返回替换后的html文件<br/>
	 * 说明如下：<br/>
	 * <ul>
	 *   <li>
	 *   	<p>如果为web项目，需要在webapp目录下，创建/freemarker/template/，然后将ftl文件放到该目录下</p>
	 *   </li>
	 *   <li>
	 *   	<p>如果为java项目，需要在resources目录下，创建/freemarker/template/，然后将ftl文件放到该目录下</p>
	 *   </li>
	 * <ul>
	 * @param dataMap 参数集合，为map格式
	 * @param freeMarkerFile freemarker文件名称，需要放到 resources/email_template 目录下
	 * @return 替换后的html
	 */
	public String getHtml(Map<String,Object>dataMap,String freeMarkerFile) {

		String freeMarkerStr = "";
		//1、创建freeMarker配置实例，使用带有版本号的构造方法
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
		ByteArrayOutputStream byteArrayOutputStream = null;
		Writer writer = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();

			//2、 获取模版路径
			URL classPath = Thread.currentThread().getContextClassLoader().getResource("");
			String proRootPath = System.getProperty("user.dir");
			String projectName = this.getLastPath(proRootPath);
			String path = classPath.getPath().replace("test-classes", projectName)+FREEMARKER_PATH;
			if( path.indexOf("WEB-INF") != -1){
				path = new String(path.substring(0,path.indexOf("WEB-INF")-1));
			}
			File file = new File(path);

			System.out.println(("获取的当前freemarker文件路径为：【"+file.getPath()+"】，文件名称【"+freeMarkerFile)+"】");

			configuration.setDefaultEncoding(CHARSET_UTF8);
			configuration.setDirectoryForTemplateLoading(file);

			//3、加载模版文件
			Template template = configuration.getTemplate(freeMarkerFile);
			//4、生成数据
			writer = new OutputStreamWriter(byteArrayOutputStream);
			//5、把数据源写到模板中
			template.process(dataMap, writer);

			freeMarkerStr = new String(byteArrayOutputStream.toByteArray(), CHARSET_UTF8);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(byteArrayOutputStream!=null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return freeMarkerStr;
	}

	/**
	 * 截取路径的最后一段
	 * @param path
	 * @return
	 */
	public String getLastPath(String path) {
		String regEx = ".+/(.+)$";
		// String regEx = ".+\\\\(.+)$";
		// String str = "c:\\dir1\\dir2\\文件.pdf";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(path);
		if (!m.find()) {
			System.out.println("文件路径格式错误!");
			return null;
		}
		return m.group(1);
	}

	public static void main(String[] args) {
		Map<String,Object>dataMap = new HashMap<String,Object>();
		dataMap.put("name_demo", "张三");
		String htmlStr = new FreeMarkerTest().getHtml(dataMap, "demo_tpl.ftl");
		System.out.println(htmlStr);
	}
}