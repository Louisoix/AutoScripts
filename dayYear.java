/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

import java.util.Scanner;

public class dayYear {
    public static void main (String[]args){
        int day=0;
        int month=0;
        int year=0;
        int sum=0;
        int leap;
        year=Integer.parseInt(args[0]);
        month=Integer.parseInt(args[1]);
        day=Integer.parseInt(args[2]);
        switch(month)
        {
            case 1:
                sum=0;break;
            case 2:
                sum=31;break;
            case 3:
                sum=59;break;
            case 4:
                sum=90;break;
            case 5:
                sum=120;break;
            case 6:
                sum=151;break;
            case 7:
                sum=181;break;
            case 8:
                sum=212;break;
            case 9:
                sum=243;break;
            case 10:
                sum=273;break;
            case 11:
                sum=304;break;
            case 12:
                sum=334;break;
            default:
                System.out.println("data error");break;
        }
        sum=sum+day; /*再加上某天的天数*/
        if(year%400==0||(year%4==0&&year%100!=0)){
            leap=1;
        } else {
            leap=0;
        }
        if(leap==1 && month>2) {
            sum++;
        }
        System.out.println("It is the the day:"+sum);
    }
}
