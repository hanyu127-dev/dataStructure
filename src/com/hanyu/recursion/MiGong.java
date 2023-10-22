package com.hanyu.recursion;

public class MiGong {
    public static void main(String[] args) {
        // 用数组模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1表示墙
        // 上下置为1
        for (int i =0;i<7;i++){
            map[0][i] = 1;
            map[7][i]= 1;
        }
        // 左右置为1
        for (int i =1;i<7;i++){
            map[i][0] = 1;
            map[i][6] = 1;

        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;



        System.out.println("地图的情况:");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路

//        setWay(map,1,1);
//        // 小球走过并标识的地图
//        System.out.println("小球走过并标识的地图（下，右，上，左）:");
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 7; j++) {
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        //使用递归回溯给小球找路

        setWay2(map,1,1);
        // 小球走过并标识的地图
        System.out.println("小球走过并标识的地图（上，右，下，左）:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }


    }

    // 使用递归回溯来给小球找路
    // 说明
    // 1.map表示地图
    // 2. i,j表示从哪个位置出发
    // 3. 如果小球能到map[6][5]位置，则说明通路找到
    // 4.约定： 当map[i][j]=0表示该点还没走过，为1表示不能走，为2表示同路，为3表示该位置已经走过，但是走不同
    // 5. 走迷宫时，需要确定一个策略 下->右->上->左，如果该点走不通，则回溯

    /**
     *
     * @param map 表示地图
     * @param i  表示第i行
     * @param j  表示第j列
     * @return  返回能否走通
     */
    public static boolean setWay(int[][] map,int i,int j){
        if (map[6][5]==2){
            return true;
        }else {
            if (map[i][j]==0){
                // 按照策略走
                // 假定该点可以走通
                map[i][j]=2;
                if (setWay(map,i+1,j)){ //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右走
                    return true;
                }else if (setWay(map,i-1,j)){ // 向上走
                    return true;
                }else if (setWay(map,i,j-1)){ //向左走
                    return true;
                }else {
                    map[i][j]=3;
                    return false;
                }
            }else {
                return false;
            }

        }
    }
    // 修改找路策略
    public static boolean setWay2(int[][] map,int i,int j){
        if (map[6][5]==2){
            return true;
        }else {
            if (map[i][j]==0){
                // 按照策略走
                // 假定该点可以走通
                map[i][j]=2;
                if (setWay2(map,i-1,j)){ //向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) { //向右走
                    return true;
                }else if (setWay2(map,i+1,j)){ // 向下走
                    return true;
                }else if (setWay2(map,i,j-1)){ //向左走
                    return true;
                }else {
                    map[i][j]=3;
                    return false;
                }
            }else {
                return false;
            }

        }
    }
}
