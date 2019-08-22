package com.algorithm;

import java.util.Arrays;

/**
 * Creator weishi8
 * Date&Time 2019-08-22 09:58
 * description 冒泡排序
 * 主要逻辑：
 *  1、将相邻的两个要素，两两比较，当一个元素大于右侧元素时，交换他们的位置；
 *  2、当一个元素小于右侧元素时，位置不变；
 *  3、每一次循环，都是将最大的数值，像汽水一样，漂到了最右侧，因此叫冒泡排序。
 *
 *  时间复杂度：
 *  第1个元素，循环n-1次
 *  第2个元素，循环n-2次
 *  总时间 = 1+2+3...+n-1 = 0.5n*n + 0.5n - n = 0.5n*n = O(n*n)
 */
public class BubbleSort {


    public static void sort(int[] arr){
        int len = arr.length;
        // i 只是用来处理循环次数，不用做对比
        for (int i = 0; i < len-1 ; i++) {
            // 用作对比的，是当前元素arr[j] 和 arr[j]的下一个元素arr[j+1]
            for (int j = 0; j <len-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    /**
     * 处理数据交换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[]arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int [] arr = {1,3,4,7,4,2,3,5,9,5,3,8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
