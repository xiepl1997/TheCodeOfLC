package xpl;

public class PointToOffer {
    /**
     * 面试题3 数组中重复的数字
     * 给一个长度为n的数组，里面的数字都在0~n-1范围内，找任意一个重复数字
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1)
            return -1;
        for(int i = 0; i < nums.length; i++){
            if(i == nums[i])
                continue;
            if(nums[nums[i]] == nums[i])
                return nums[i];
            else{
                int temp = nums[i];
                nums[i] = nums[nums[i]];
                nums[temp] = temp;
                i--;
            }
        }
        return -1;
    }

    /**
     * 二维数组中的查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 )
            return false;
        int i = 0; //右上角的点的x坐标
        int j = matrix[0].length-1; //右上角的点的y坐标
        while(i <= matrix.length && j >= 0){
            if(target == matrix[i][j])
                return true;
            else if(target < matrix[i][j])
                j--;
            else
                i++;
        }
        return false;
    }

    /**
     * 面试题05 替换空格
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if(s.isEmpty())
            return s;
        String res = "";
        for(char c : s.toCharArray()){
            if(c != ' ')
                res += c;
            else
                res += "%20";
        }
        return res;
    }

    /**
     * 面试题06 从尾到头打印链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if(head == null)
            return new int[0];
        ListNode pre = null;
        ListNode now = head;
        int len = 0;
        while(now != null){
            ListNode temp = head.next;
            now.next = pre;
            pre = now;
            now = temp;
            len ++;
        }
        len = 0;
        int[] res = new int[len];
        while(pre != null){
            res[len++] = pre.val;
            pre = pre.next;
        }
        return res;
    }

    /**
     * 面试题10-1 斐波那契数列
     * @param n
     * @return
     */
    int[] fa = new int[101];
    public int fib(int n) {
        return (int)f(n)%1000000007;
    }
    public long f(int n){
        if(fa[n] != 0)
            return fa[n];
        if(n == 0)
            return fa[0] = 0;
        if(n == 1)
            return fa[1] = 1;
        if(n == 2)
            return fa[2] = 1;
        return fa[n]=fib(n-1)+fib(n-2);
    }

    /**
     * 面试题10-2 青蛙跳台阶
     * @param n
     * @return
     */
    int[] f_10 = new int[101];
    public int numWays(int n) {
        return (int)f_102(n)%1000000007;
    }
    public long f_102(int n){
        if(f_10[n] != 0)
            return f_10[n];
        if(n == 0)
            return f_10[0] = 1;
        if(n == 1)
            return f_10[1] = 1;
        if(n == 2)
            return f_10[2] = 2;
        return f_10[n] = numWays(n-1) + numWays(n-2);
    }

    /**
     * 面试题11 旋转数组的最小数字
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length-1, mid = 0;
        while(left < right){
            mid = left + (right-left)/2;
            if(numbers[mid] < numbers[right])
                right = mid;
            else if(numbers[mid] > numbers[right])
                left = mid+1;
            else
                right -= 1;
        }
        return numbers[left];
    }

    /**
     * 面试题12 矩阵中的路径
     * @param board
     * @param word
     * @return
     */
    char[][] board;
    int[][] flag;
    String word;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        if(board==null || board.length == 0)
            return false;
        flag = new int[board.length][board[0].length];
        for(int i = 0 ;i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    flag[i][j] = 1;
                    if(word.length()==1||dfs_12(0, i, j)) {
                        return true;
                    }
                    flag[i][j] = 0;
                }
            }
        }
        return false;
    }

    /**
     * 深度搜索
     * @param index
     * @param i
     * @param j
     * @return
     */
    public boolean dfs_12(int index, int i, int j){
        if(!(i >= 0 && i <= board.length-1 && j >= 0 && j <= board[0].length-1))
            return false;
        if(index > word.length()-1)
            return false;
        if(word.charAt(index) != board[i][j])
            return false;
        if(index == word.length()-1)
            return true;
        //向四个方向搜索
        if(i+1<=board.length-1 && flag[i+1][j] == 0){
            flag[i+1][j] = 1;
            if(dfs_12(index+1, i+1, j))
                return true;
            flag[i+1][j] = 0;
        }
        if(i-1>=0 && flag[i-1][j] == 0){
            flag[i-1][j] = 1;
            if(dfs_12(index+1, i-1, j))
                return true;
            flag[i-1][j] = 0;
        }
        if(j-1>=0 && flag[i][j-1] == 0){
            flag[i][j-1] = 1;
            if(dfs_12(index+1, i, j-1))
                return true;
            flag[i][j-1] = 0;
        }
        if(j+1<=board[0].length-1 && flag[i][j+1] == 0){
            flag[i][j+1] = 1;
            if(dfs_12(index+1, i, j+1))
                return true;
            flag[i][j+1] = 0;
        }
        return false;
    }

    /**
     * 面试题13 机器人的运动范围
     * @param m
     * @param n
     * @param k
     * @return
     */
    int k;
    int count;
    public int movingCount(int m, int n, int k) {
        if(m == 0 || n == 0)
            return 0;
        this.k = k;
        int[][] matrix = new int[m][n];
        dfs_13( 0, 0, matrix);
        return count;
    }
    public void dfs_13(int i, int j, int[][] matrix){
        //越界
        if(!(i >= 0 && i <= matrix.length-1 && j >= 0 && j <= matrix[0].length-1))
            return;
        if(func(i)+func(j) <= k){
            count++;
            matrix[i][j] = 1;
        }
        else
            return;
        if(i-1 >= 0 && matrix[i-1][j] == 0){
            dfs_13(i-1, j, matrix);
        }
        if(i+1 <= matrix.length-1 && matrix[i+1][j] == 0){
            dfs_13(i+1, j, matrix);
        }
        if(j-1 >= 0 && matrix[i][j-1] == 0){
            dfs_13(i, j-1, matrix);
        }
        if(j+1 <= matrix[0].length && matrix[i][j+1] == 0){
            dfs_13(i, j+1, matrix);
        }
    }
    public int func(int a){
        int sum = 0;
        while(a != 0){
            sum += a%10;
            a /= 10;
        }
        return sum;
    }

    /**
     * 面试题14-1 剪绳子
     * 使用数学方法，得：尽可能将每一段剪成相同长度（最好为3）时，乘积最大
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if(n == 2)
            return 2;
        if(n == 3)
            return 2;
        int res = 1;
        while(n > 4){
            res *= 3;
            n -= 3;
        }
        return res * n;
    }

    /**
     * 面试题15 二进制中1的个数
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int sum = 0;
        int mask = 1;
        for(int i = 0; i < 32; i++){
            if((n & mask) != 0)
                sum++;
            mask<<=1;
        }
        return sum;
    }
}
