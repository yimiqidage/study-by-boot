package com.suanfa;

/**
 * @author weishi8
 * @create 2019-05-21
 * @description 菲波那切数列
 */
public class Fib {
    public static int cout = 0;
    public static int[] fibo(int num){
        int [] fibs = new int[num];
        if(num==1){
            fibs[0] = 1;
            return fibs;
        }
        if(num==2){
            fibs[0] = 1;
            fibs[1] = 1;
            return fibs;
        }

        for (int i = 0; i < num ; i++) {
            fibs[i] = getFib(fibs,i);
        }
        return fibs;
    }

    public static int getFib(int[]fibs,int index){
        if(index<=1) return 1;
        return fibs[index-1]+fibs[index-2];
    }

    /**
     * 通过递归调用，实现斐波那契数列
     * @param index
     * @return
     */
    public static int getFib2(int index){
        cout++;
        System.out.println("调用值："+index);
        if(index<=2) return 1;
        return getFib2(index-1)+getFib2(index-2);
    }

    public static String arrayToString(int[] fibs){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fibs.length; i++) {
            sb.append(fibs[i]).append(",");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int[] fibs = fibo(i);
            System.out.println(arrayToString(fibs));
        }
        System.out.println( getFib2(8));
        System.out.println("count："+cout);
    }
}
