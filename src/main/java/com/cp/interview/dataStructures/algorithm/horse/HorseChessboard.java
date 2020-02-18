package com.cp.interview.dataStructures.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘（骑士周游问题）
 */
public class HorseChessboard {
    private static int X; //棋盘列数
    private static int Y;//棋盘行数
    //标记棋盘各个位置是否被访问过
    private static boolean visited[];
    //标记是否棋盘所有位置都被访问
    private static boolean finished;//true  表示成功

    public static void main(String[] args) {
        System.out.println("骑士周游算法开始运行。。");
        X = 6;
        Y = 6;
        int row = 1;//初始位置的行，从1开始编号
        int column = 1;//初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始都是false

        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) + "毫秒");

        //输出棋盘最后情况
        for(int[] rows: chessboard){
            for(int step: rows){
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游算法
     * @param chessboard 棋盘
     * @param row 当前马儿位置的行， 从0开始
     * @param column 当前马儿位置的列， 从0开始
     * @param step 是第几步，初始位置从第一步开始
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step){
        chessboard[row][column] = step;
        visited[row * X + column] = true;//标记位置已访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序，ps中所有point对象的下一步的位置数目进行非递减排序
        sort(ps);
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断改点是否已经访问过
            if(!visited[p.y * X + p.x]){//还没访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断是否完成了任务，
        //step < X * Y 成立的情况有俩种
        //1.棋盘到目前位置，仍然没有走完
        //2.期盘处于一个回溯过程
        if(step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        }else {
            finished = true;
        }
    }

    /**
     * 根据当前位置（Point对象） 计算马还能走哪些位置，并放入一个集合中
     * 最多8个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //表示马可以走位置5
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }
        //表示马可以走位置6
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }
        //表示马可以走位置7
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }
        //表示马可以走位置0
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }
        //表示马可以走位置1
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        //表示马可以走位置2
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }
        //表示马可以走位置3
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }
        //表示马可以走位置4
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        return ps;
    }

    //用贪心算法进行优化
    //根据当前这一步的所有的下一步的选择位置进行非递减排序,减少回溯次数
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置的个数
                int count1 = next(o1).size();
                //获取到o2的下一步的所有位置的个数
                int count2 = next(o2).size();
                if(count1 < count2){
                    return -1;
                }else if(count1 == count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
