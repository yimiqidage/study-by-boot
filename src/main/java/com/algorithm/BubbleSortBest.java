package com.algorithm;

import java.util.Arrays;

/**
 * Creator weishi8
 * Date&Time 2019-08-22 09:58
 * description 冒泡排序优化-最优版-降低循环次数 P114
 *
 */
public class BubbleSortBest {

    public static int count = 0;
    public static void sort(int[] arr){
        int len = arr.length;
        //记录最后以一次交换的位置，这样后面有序的内容，就不需要再进行循环了。
        int sortBorder = len-1;
        // i 只是用来处理循环次数，不用做对比
        for (int i = 0; i < len-1 ; i++) {
            count++;
            boolean isSorted = true;
            // 用作对比的，是当前元素arr[j] 和 arr[j]的下一个元素arr[j+1]
            for (int j = 0; j <sortBorder; j++) {
                count++;
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                    isSorted = false;
                    sortBorder = j;
                }
            }
            if(isSorted){
                break;
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
        int [] arr = {5,4,6,7,9};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(count);
    }
}
