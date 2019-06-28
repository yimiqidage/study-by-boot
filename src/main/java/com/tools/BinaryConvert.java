package com.tools;

/**
 * @author weishi8
 * @create 2019-05-17
 * @description 进制转换
 */
public class BinaryConvert {

    //二进制转十进制
    public static int binary2Int(String binary){
        return Integer.parseInt(binary,2);
    }

    //十六进制转二进制
    public static String hex2binary(String hex){
        // 十六进制转换为十进制
        int para10 = Integer.parseInt(hex,16);
        // 十进制转换为二进制
        String binary = Integer.toBinaryString(para10);
        binary = addZero(binary,16);
        System.out.format("十六进制，转换前字符串:\"%s\",转换后二进制字符串为\"%s\"",hex,binary).println("");
        return binary;
    }

    /**
     * 补零
     * @param str
     * @param len
     * @return
     */
    public static String addZero(String str,int len){
        StringBuffer sb =  new StringBuffer(str);
        for (int i = 0; i < len - str.length() ; i++) {
            sb.insert(0,"0");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        hex2binary("0001");
        hex2binary("0010");
        hex2binary("0020");
        hex2binary("0200");
        hex2binary("0400");
        hex2binary("1000");
        hex2binary("2000");
        hex2binary("4000");
        hex2binary("0021");
    }

}
