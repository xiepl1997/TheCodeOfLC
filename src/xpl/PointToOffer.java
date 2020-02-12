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
}
