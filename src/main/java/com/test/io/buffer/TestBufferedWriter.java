package com.test.io.buffer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author shiwei 2013-3-25 <br/>
 * 浣跨敤BufferedWriter杩涜鏂囦欢鐨勫啓鎿嶄綔銆�
 * 浣跨敤BufferedWriter璁板緱瑕佽皟鐢╢lush鏂规硶锛屽埛鏂般��
 */
public class TestBufferedWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{

		FileWriter fw = new FileWriter("demo.txt");
		
		//涓轰簡鎻愰珮鍐欏叆鐨勬晥鐜囥�備娇鐢ㄤ簡瀛楃娴佺殑缂撳啿鍖恒��
		//鍒涘缓浜嗕竴涓瓧绗﹀啓鍏ユ祦鐨勭紦鍐插尯瀵硅薄锛屽苟鍜屾寚瀹氳琚紦鍐茬殑娴佸璞＄浉鍏宠仈
		BufferedWriter bw = new BufferedWriter(fw);
		
		//浣跨敤缂撳啿鍖虹殑鍐欏叆鏂规硶灏嗘暟鎹厛鍐欏叆鍒扮紦鍐插尯涓��
		bw.write("abcde");
		bw.newLine();
		bw.write("fghigklm");
		
		for (int i=0;i<1000000;i++) {
			bw.write("abcdefegesdafadfad");
			bw.newLine();
		}
		
		//鍒锋柊缂撳啿鍖�:浣跨敤缂撳啿鍖虹殑鍒锋柊鏂规硶灏嗘暟鎹埛鐩殑鍦颁腑銆�
		bw.flush();
		
		//鍏抽棴缂撳啿鍖猴細浣跨敤BufferedWriter鐨刢lose()锛屽疄闄呬笂鍏抽棴鐨勫氨鏄疻riter锛孊ufferedWriter 鍙槸浼樺寲锛屼笉浼氬紑鍚祦銆�
		bw.close();
		
	}

}

