package com.test.xml.dom4j;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author s
 * 
 * 创建document的三种方式：createDocument1(),createDocument2(),createDocument3().
 * 
 */
public class TestXmlDom4j {

	/**
	 * 主动创建document对象
	 */
	public static Document createDocument1(){
		return  DocumentHelper.createDocument();
		
	}
	/**
	 * 读取XML文件，获得document对象。
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Document createDocument2(String path) throws DocumentException{
		return new SAXReader().read(new File(path));
		
	}
	
	/**
	 * 解析xml的文本，获得document对象
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Document createDocument3(String xml) throws DocumentException {
		return DocumentHelper.parseText(xml);
		
	}
	
	/**
	 * 将map数据转换成xml格式的数据
	 * @param paramMap
	 * @return
	 */
	public static String mapToXml (Map<String,String> paramMap,String charset){
		
		if(charset==null || charset.equals("")) charset = "GBK";
		
		//①：使用DocumentHelper创建document
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("info"); 
		doc.setRootElement(root);
		
		//②：注意：如果设置的值为null，会抛出异常。
		
		for(String param:paramMap.keySet()){
			root.addElement(param).setText(paramMap.get(param));
		}
		
		//③：直接使用document.asXml()方法，会出现汉字编码不正常的情况，所以使用xmlWriter 进行转换。
		OutputFormat format = OutputFormat.createPrettyPrint(); 
        format.setEncoding(charset); 
        StringWriter sw = new StringWriter(); 
        XMLWriter xw = new XMLWriter(sw, format); 
        try { 
                xw.write(doc); 
                xw.flush(); 
                xw.close(); 
        } catch (IOException e) { 
                e.printStackTrace();
        } 
		
		return sw.toString();
	}
	
	/**
	 * 将xml格式的字符串转换成map
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlToMap(String xml) throws DocumentException{
		Map<String,String> map = new HashMap<String, String>();
		Document doc = createDocument3(xml);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for(Element e:list){
			String eleName = e.getName();
			List<Attribute> attrs = e.attributes();
			if(attrs!=null && attrs.size()>0){
				for(Attribute attr:attrs){
					String name = attr.getName();
					String value = attr.getStringValue();
					map.put(eleName+"_"+name, value);
				}
			}
			
			map.put(eleName, e.getStringValue());
		}
		System.out.println(map);
		return map;
	}
	
	public static void main(String[] args) throws DocumentException{
		xmlToMap("<school><class name=\"一年级一班\" >班主任：张三</class><teacher>校长：李四</teacher></school>");
	}
}
