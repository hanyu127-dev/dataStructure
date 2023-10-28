package com.hanyu.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    // 存储顶点的集合
    private ArrayList<String> vertexList;
    // 存储图的对应的邻接矩阵
    private int[][] edges;
    // 表示边的数目
    private int numOfEdges;
    // 表示某个节点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        // 测试图是否创建
        int n = 5;
        String[] vertexts = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(n);
        // 添加顶点
        for (String vertext : vertexts) {
            graph.insertVertex(vertext);
        }

        // 添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1);
        graph.insertEdge(0, 2);
        graph.insertEdge(1, 2);
        graph.insertEdge(1, 3);
        graph.insertEdge(1, 4);

        //显示图对应的矩阵
        graph.showGraph();

        graph.DFS();

    }

    // 构造器
    private Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    // 深度优先遍历算法

    /**
     * 深度优先遍历算法
     * @param isVisited 数组，用来判断是否被访问
     * @param i  判断哪个是否被判断
     */
    public void DFS(boolean[] isVisited,int i){
        // 输出该节点
        System.out.print(getValueByIndex(i)+"->");
        // 设置该节点被访问
        isVisited[i] = true;
        // 查找v的下一个节点w
        int w = getFirstNeighbor(i);
        while (w!=-1){
            // 判断是否被访问
            if (!isVisited[w]){
                DFS(isVisited,w);
            }
            // 如果w节点已经被访问过
            w = getNextNeiphbor(i,w);
        }

        // w不存在时，则返回上一个节点，继续
    }
    // 对dfs进行重载，遍历我们所有的节点

    public void DFS(){
        // 遍历所有的节点，进行DFS
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                DFS(isVisited,i);
            }
        }
    }

    //根据前一个邻结节点的下标，来获取下一个邻接节点
    public int getNextNeiphbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 得到第一个邻接节点的下标W
    public int getFirstNeighbor(int index) {
        for (int j = index; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }


    // 图中常用的方法
    // 返回节点的数目
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 返回节点i（下标）对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }


    // 返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }


    // 插入顶点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边
    public void insertEdge(int v1, int v2) {
        edges[v1][v2] = 1;
        edges[v2][v1] = 1;
        numOfEdges++;
    }
}
