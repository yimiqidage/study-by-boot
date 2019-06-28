package com.test.common.test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TestCommon {

	public static void main(String[] args) throws IOException {
		
//		System.out.println(getSql("10022976892","20141121","20141122"));
//		listPrint();

//		BigDecimal b = new BigDecimal(10.00);
//		System.out.println(b.multiply(new BigDecimal(-1)));
		//6577464192
		
		long a = 0;
		for(int i=0;i<50;i++){
			a+=131910123;
		}
		System.out.println(a);
		
		test();
		
	}
	
	public static void test(){
		StringBuffer sb = new StringBuffer();
		long start = System.currentTimeMillis();
		long next = 123456;
		String seq = null;
		String nextStr = String.valueOf(next);
		if(next>99){
			seq = nextStr.substring(nextStr.length()-3);
		}else{
			DecimalFormat df = new DecimalFormat("000");
			seq = df.format(next);
		}
		long end = System.currentTimeMillis();
		System.out.println(seq+","+(end-start));
	}
	
	public static void listPrint(){
		List<String>list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		List<String>sub = list.subList(1, 4);
		System.out.println(sub.toString());
		System.out.println(list2Str(sub));
	}
	
	
	public static String list2Str(List<String>list){
		StringBuffer sb = new StringBuffer();
		if(list!=null && list.size()>0){
			for(String str:list){
				sb.append(str).append(",");
			}
		}
		return sb.toString();
	}
	
	public static String getSql(String exMerchId,String startDate,String endDate){
		return 
				 "update NRC"
						+ exMerchId
						+ " tl set tl.NS_RECON_STS = '1'  where  exists (select 1 from NR"
						+ exMerchId
						+ " clr "
						+ "where clr.order_id = tl.order_id and clr.ccy_tp = tl.ccy_tp and clr.amount = tl.amount) "
						// and clr.merch_id = tl.merch_id
//						+ " where  tl.NS_RECON_STS <> '1' and clr.NS_RECON_STS = '1' and tl1.rowid = tl.rowid) ";
						+ " and tl.ex_sttl_dt >= to_date('"+startDate+"','yyyymmdd') and tl.ex_sttl_dt < to_date('"+endDate+"','yyyymmdd') ";
}
	
	
}
