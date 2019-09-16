package xpl;

public class test {
    public static void main(String[] args) {
        ListNodes list = new ListNodes();
        list.val = 123456;
        System.out.println(list.val);
        ListNodes l2 = list;
        l2.val = 132;
        System.out.println(l2.val+" "+list.val);
    }

    int pointcount = 7;
    int[][] matrix = new int[pointcount][pointcount]; //距离矩阵
    int[] dis = new int[pointcount]; // 存储估计值
    int[] flag = new int[pointcount]; //标记是否有访问到该点

    /**
     * 初始化距离矩阵
     */
    public void init(){
        matrix = new int[][]{
                {0,1,3,0,4,1,0},
                {1,0,5,0,2,4,7},
                {3,5,0,1,4,2,0},
                {0,0,1,0,3,2,1},
                {4,2,4,3,0,1,0},
                {1,4,2,2,1,0,0},
                {0,7,0,1,0,0,0}
        };
    }

    /**
     * dijkstra算法，计算起点到终点的距离
     * @param u 起点
     * @param v 终点
     */
    public void dijkstra(int u, int v){
        init();
        for(int i = 0; i < pointcount; i++){
            dis[i] = 0x3f3f3f3f;
            if(matrix[u][i] != 0)
                dis[i] = matrix[u][i];
        }
        int start = u;
        flag[start] = 1;
        dis[start] = 0;
        for(int i = 0; i < pointcount - 1; i++){
            int mins = 0x3f3f3f3f;
            for(int j = 0; j < pointcount; j++){
                if(flag[j] == 0 && mins > dis[j]){
                    mins = dis[j];
                    start = j;
                }
            }
            flag[start] = 1;
            for(int j = 0; j < pointcount; j++){
                if(flag[j] == 0){
                    dis[j] = Math.min(dis[j], dis[start] + matrix[start][j]);
                }
            }
        }
    }
}

class ListNodes{
    int val;
    ListNode next;
}
