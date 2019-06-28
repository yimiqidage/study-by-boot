package com.test.io.file;


import java.io.File;
import java.io.FilenameFilter;


/**
 * @author shiwei 2013-3-27 <br/>
 * 鏂囦欢杩囨护鍣細鍙渶瑕佷互 filterName 缁撳熬鐨勬枃浠� 
 *
 */
public class TestFilterFileByName implements FilenameFilter {

	private String filterName ; 
	
	public TestFilterFileByName (String filterName){
		this.filterName = filterName;
	}
	
	@Override
	public boolean accept(File dir, String name) {

		return name.toLowerCase().endsWith(filterName);
	}


}
