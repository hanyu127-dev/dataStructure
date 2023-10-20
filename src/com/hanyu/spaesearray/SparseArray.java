package com.hanyu.spaesearray;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) throws IOException {
//        // 创建原始的二维数组 11*11
//        // 0:代表没有棋子，1：表示黑子 2：表示蓝子
//        int[][] chessArr1 = new int[11][11];
//        chessArr1[1][2] = 1;
//        chessArr1[2][3] = 2;
//        int i = 0;
//        // 输出原始的数组
//        for (int[] row : chessArr1) {
//            for (int data : row) {
//                System.out.printf("%d\t",data);
//                i ++;
//                if (i%11==0){
//                    System.out.println();
//                }
//            }
//        }
//        // 遍历原始数组，得到非0数组的个数
//        int sum = 0;
//        for (int j = 0; j < 11; j++) {
//            for (int k = 0; k < 11; k++) {
//                if (chessArr1[j][k]!=0){
//                    sum ++;
//                }
//            }
//        }
//        System.out.println("sum="+sum);
//        // 创建稀疏数组
//        int[][] sparseArr = new int[sum+1][3];
//        // 第一行
//        sparseArr[0][0] = 11;
//        sparseArr[0][1] = 11;
//        sparseArr[0][2] = sum;
//        // 第二行,第三行......赋值
//        int count = 1;
//        for (int j = 0; j < 11; j++) {
//            for (int k = 0; k < 11; k++) {
//                if (chessArr1[j][k]!=0){
//                    sparseArr[count][0] = j;
//                    sparseArr[count][1] = k;
//                    sparseArr[count][2] = chessArr1[j][k];
//                    count++;
//                }
//            }
//        }
//
//        // 输出稀疏数组
//        i = 0;
//        System.out.println();
//        System.out.println("得到的稀疏数组为：");
//        for (int j = 0; j < sparseArr.length; j++) {
//            for (int k = 0; k < sparseArr[j].length; k++) {
//                if (i%3==0){
//                    System.out.println();
//                }
//                i++;
//                System.out.printf("%d\t",sparseArr[j][k]);
//            }
//
//        }
//
//        /**
//         *  将稀疏数组还原成二维数组
//         */
//
//
//        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
//        for (int j = 1; j < sparseArr.length; j++) {
//            chessArr2[sparseArr[j][0]][sparseArr[j][1]] = sparseArr[j][2];
//        }
//
//
//        // 输出恢复的原始数组
//        System.out.println();
//        System.out.println("输出恢复的原始数组");
//        i =0 ;
//        for (int[] row : chessArr2) {
//            for (int data : row) {
//                System.out.printf("%d\t",data);
//                i ++;
//                if (i%11==0){
//                    System.out.println();
//                }
//            }
//        }
        int[][] chessArr3 = getSparse("map.data");
        int i =0 ;
        for (int[] row : chessArr3) {
            for (int data : row) {
                System.out.printf("%d\t",data);
                i ++;
                if (i%11==0){
                    System.out.println();
                }
            }
        }



    }


    public static void saveSparse(int[][] sparseArr, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                String msg = String.valueOf(sparseArr[i][j]);
                writer.write(msg);
                writer.write("\t");
            }
            writer.write("\n");
        }
        writer.close();

    }

    public static int[][] getSparse(String fileName) throws IOException {
        FileReader reader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String msg = bufferedReader.readLine();
        // 存储第一行的数组
        int[] arr = new int[3];
        String[] strs = msg.split("\t");
        for (int i = 0; i < strs.length; i++) {
            arr[i] = Integer.parseInt(strs[i]);
        }
        // 创建二维数组
        int[][] chessArr3 = new int[arr[0]][arr[1]];
        msg = bufferedReader.readLine();
        while (msg != null) {
            String[] strs1 = msg.split("\t");
            int hang = Integer.parseInt(strs1[0]);
            int lie = Integer.parseInt(strs1[1]);
            int zhi = Integer.parseInt(strs1[2]);
            chessArr3[hang][lie] = zhi;
            msg = bufferedReader.readLine();
        }

        return chessArr3;
    }
}
