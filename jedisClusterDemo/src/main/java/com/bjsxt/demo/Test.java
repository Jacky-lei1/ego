package com.bjsxt.demo;

/**
 * @author lvyelanshan
 * @create 2019-10-24 8:48
 */
public class Test {
    public static void main(String[] args) {

        int[] a = {4,6,7,10,5,3,8};

        int temp = 0;
        for (int i = 0; i <a.length-1; ++i) {
            //通过符号位可以减少无谓的比较，如果已经有序了，就退出循环
            int flag = 0;
            for (int j = 0; j <a.length-1-i ; ++j) {
                if (a[j + 1] < a[j]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = 1;
                }
            }
            if(flag == 0){
                break;
            }
        }
        for(int b: a){
            System.out.print(b+",");
        }
    }
}



