package com.test.sort;

import java.util.Arrays;

/**
 * binarySearch(): 二分法查找。
 * 
 * 
 */
public class TestSort {

	/**
	 * @param args
	 * 演示二分法查找，以及排序
	 */
	public static void main(String[] args) {
		int []arr = sort(new int[]{2,3,55,44,55,10});
		System.out.println(binarySearch(arr, 55));
		swapArray(arr);
	}

	
	/**
	 * 排序
	 * @param arr
	 * @return
	 */
	public static int[] sort(int ... arr){
		
		for(int i=0;i<arr.length;i++){
			for(int j=i;j<arr.length;j++){
				if(arr[i]>arr[j]){
					swap(arr,i,j);
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));
		return arr;
		
	}
	
	/**
	 * 交换
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arr,int i,int j){
		int temp = arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
		
	}
	
	/**
	 * 二分法查找：
	 * 		前提：数组是有序的。
	 * @param arr
	 * @return
	 */
	public static int binarySearch(int [] arr,int key){
		int max = arr.length-1;
		int min = 0;
		int mid = 0;
		
		while(min<=max){
			mid =(min+max)/2;
			if(arr[mid]>key) max = mid-1;
			else if(arr[mid]<key) min = mid+1;
			else return mid;
		}
		return -1;
	}
	
	/**
	 * 对数组反转
	 * @param arr
	 * @return
	 */
	public static int[] swapArray(int arr[]){
		
		for(int start=0 ,end = arr.length-1;start<end;start++,end--){
			swap(arr, start, end);
		}
		System.out.println(Arrays.toString(arr));
		return arr;
	}
}
