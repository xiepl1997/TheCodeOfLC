package xpl;

import org.w3c.dom.NodeList;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 剑指offer
 */
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
            return 1;
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
     * 面试题14-2 剪绳子2
     * @param n
     * @return
     */
    public int cuttingRope_2(int n) {
        if(n == 2)
            return 1;
        if(n == 3)
            return 2;
        long res = 1;
        while(n > 4){
            res *= 3;
            res = res % 1000000007;
            n -= 3;
        }
        return (int)(res * n % 1000000007);
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

    /**
     * 面试题16 数值的整数次方
     * 快速幂方法
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(n < 0){
            x = 1/x;
            n = -n;
        }
        return f_16(x, n);
    }
    public double f_16(double x, int n){
        if(n == 0)
            return 1.0;
        double half = f_16(x, n/2);
        if(n/2 % 2 == 0)
            return half*half;
        else
            return half*half*x;
    }

    /**
     * 面试题17 打印从1到最大的n位数
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        n = (int)Math.pow(10, n)-1;
        int[] res = new int[n];
        for(int i = 1; i <= n; i++){
            res[i-1] = i;
        }
        return res;
    }

    /**
     * 面试题18 删除链表的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode now = head;
        ListNode pre = head;
        while(now != null){
            if(now.val == val){
                //判断是否是头节点
                if(now == head){
                    return head.next;
                }
                //判断是否是尾节点
                if(now.next == null){
                    pre.next = null;
                    return head;
                }
                pre.next = now.next;
                return head;
            }
            pre = now;
            now = now.next;
        }
        return null;
    }

    /**
     * 面试题21 调整数组顺序使奇数位于偶数前面
     * 例如[1,2,3,4]，调整为[1,3,2,4]或[3,1,2,4]
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        while(l < r){
            while(l<r && nums[l] % 2 == 1)l++;
            while(l<r && nums[r] % 2 == 0)r--;
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
        return nums;
    }

    /**
     * 面试题22 链表中倒数第k个节点
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head == null)
            return head;
        ListNode l1 = head;
        ListNode l2 = head;
        while(k-- != 0 && l1 != null){
            l1 = l1.next;
        }
        while(l1 != null){
            l1 = l1.next;
            l2 = l2.next;
        }
        return l2;
    }

    /**
     * 面试题24 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode pre = null;
        ListNode now = head;
        while(now != null){
            ListNode temp = now.next;
            now.next = pre;
            pre = now;
            now = temp;
        }
        return pre;
    }

    /**
     * 面试题25 合并两个排序的链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 面试题27 二叉树的镜像
     * 递归
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root == null)
            return root;
        //如果是叶子，则不必翻转
        if(root.left == null && root.right == null)
            return root;
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 面试题28 对称的二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return zz(root.left, root.right);
    }
    public boolean zz(TreeNode l1, TreeNode l2){
        if(l1 == null && l2 == null)
            return true;
        if(l1 == null || l2 == null)
            return false;
        if(l1.val == l2.val && zz(l1.left, l2.right) && zz(l1.right, l2.left))
            return true;
        return false;
    }

    /**
     * 面试题29 顺时针打印矩阵
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length == 0)
            return new int[0];
        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        int[] res = new int[matrix.length*matrix[0].length];
        int index = 0;
        while(true){
            if(left <= right){
                for(int i = left; i <= right; i++){
                    res[index++] = matrix[top][i];
                }
            }
            else break;
            top++;
            if(top <= bottom){
                for(int i = top; i <= bottom; i++){
                    res[index++] = matrix[i][right];
                }
            }
            else break;
            right--;
            if(right >= left){
                for(int i = right; i >= left; i--){
                    res[index++] = matrix[bottom][i];
                }
            }
            else break;
            bottom--;
            if(bottom >= top){
                for(int i = bottom; i >= top; i--){
                    res[index++] = matrix[i][left];
                }
            }
            else break;
            left++;

        }
        return res;
    }

    /**
     * 面试题30 包含min函数的栈
     * min,push,pop复杂度为O(1)
     */
    class MinStack {
        List<Integer> list;
        int min;
        /** initialize your data structure here. */
        public MinStack() {
            list = new ArrayList<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if(list.size() == 0)
                min = Integer.MAX_VALUE;
            else
                min = list.get(list.size()-1);
            list.add(x);
            if(min > x)
                min = x;
            list.add(min);
        }

        public void pop() {
            list.remove(list.size()-1);
            list.remove(list.size()-1);
        }

        public int top() {
            return list.get(list.size()-2);
        }

        public int min() {
            return list.get(list.size()-1);
        }
    }

    /**
     * 面试题31 栈的压入、弹出序列
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int len = pushed.length;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for(int x : pushed){
            stack.push(x);
            while(!stack.isEmpty() && j < len && stack.peek() == popped[j]){
                stack.pop();
                j++;
            }
        }
        return j == len;
    }

    /**
     * 面试题32 从上到下打印二叉树(层次遍历)
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        if(root == null)
            return new int[0];
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            for(int i = 0; i < len; i++){
                TreeNode t = queue.poll();
                if(t != null){
                    res.add(t.val);
                    queue.add(t.left);
                    queue.add(t.right);
                }

            }
        }
        int[] r = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            r[i] = res.get(i);
        }
        return r;
    }

    /**
     * 面试题32-2 从上到下打印二叉树Ⅱ
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_2(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int len = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode t = queue.poll();
                if(t != null){
                    temp.add(t.val);
                    if(t.left!=null)queue.add(t.left);
                    if(t.right!=null)queue.add(t.right);
                }
            }
            res.add(temp);
        }
        return res;
    }

    /**
     * 面试题32-3 从上到下打印二叉树Ⅲ
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_3(TreeNode root) {
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int flag = -1;
        while(!queue.isEmpty()){
            int len = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < len; i++){
                TreeNode t = queue.poll();
                if(t != null){
                    temp.add(t.val);
                    if(t.left!=null)queue.add(t.left);
                    if(t.right!=null)queue.add(t.right);
                }
            }
            if(flag == -1){
                Collections.reverse(temp);
                res.add(temp);
            }
            else
                res.add(temp);
            flag = -flag;
        }
        return res;
    }

    /**
     * 面试题34 二叉树中和为某一值的路径
     * @param root
     * @param sum
     * @return
     */
    List<List<Integer>> res_34 = new ArrayList<>();
    List<Integer> temp_34 = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null)
            return res_34;
        sum -= root.val;
        temp_34.add(root.val);
        //如果为叶子节点
        if(root.left == null && root.right == null){
            if(sum == 0){
                res_34.add(new ArrayList<>(temp_34));
            }
        }
        if(root.left != null)
            pathSum(root.left, sum);
        if(root.right != null)
            pathSum(root.right, sum);
        temp_34.remove(temp_34.size()-1);
        return res_34;
    }

    /**
     * 面试题39 数组中出现此树超过一半的数字
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
//        排序法
//        Arrays.sort(nums);
//        return nums[nums.length/2];

//        摩尔投票法
        int member = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(count == 0){
                member = nums[i];
                count = 1;
                continue;
            }
            if(member != nums[i])
                count--;
            else
                count++;
        }
        return member;
    }

    /**
     * 面试题40 最小的k个数
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        Arrays.sort(arr);
        for(int i = 0; i < k; i++)
            res[i] = arr[i];
        return res;
    }

    /**
     * 面试题42 连续子数组的最大和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(max < 0)
                max = nums[i];
            else
                max += nums[i];
            res = Math.max(res, max);
        }
        return res;
    }

    /**
     * 面试题47 礼物的最大价值
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        if(grid == null || grid.length == 0 )
            return 0;
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        int maxvalue = dp[0][0];
        for(int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
            maxvalue = Math.max(maxvalue, dp[0][i]);
        }
        for(int i = 1; i < grid.length; i++){
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            maxvalue = Math.max(maxvalue, dp[i][0]);
        }
        for(int i = 1; i < grid.length; i++){
            for(int j = 1; j < grid[0].length; j++){
                dp[i][j] = Math.max(grid[i][j]+dp[i-1][j], grid[i][j]+dp[i][j-1]);
                maxvalue = Math.max(maxvalue, dp[i][j]);
            }
        }
        return maxvalue;
    }

    /**
     * 面试题50 第一个只出现一次的字符
     * 例如 abaccdeff，返回 b
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c:s.toCharArray()){
            if(map.containsKey(c))
                map.put(c, map.get(c)+1);
            else
                map.put(c, 1);
        }
        for(char c:s.toCharArray()){
            if(map.get(c) == 1)
                return c;
        }
        return ' ';
    }

    /**
     * 面试题52 两个链表中的第一个公共节点
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA;
        ListNode l2 = headB;
        if(headA == null || headB == null)
            return null;
        while(l1 != l2){
            l1 = l1.next;
            l2 = l2.next;
            if(l1 == null && l2 == null)
                return null;
            if(l1 == null)
                l1 = headB;
            if(l2 == null)
                l2 = headA;
        }
        return l1;
    }

    /**
     * 面试题53-1 在排序数组中查找数字
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1, mid = 0;
        int count = 0;
        while(l <= r){
            mid = l + (r - l)/2;
            if(nums[mid] == target){
                count++;
                int t = mid;
                while(++t <= nums.length-1 && nums[t] == target) count++;
                t = mid;
                while(--t >= 0 && nums[t] == target) count++;
                break;
            }
            else if(nums[mid] < target)
                l = mid+1;
            else
                r = mid-1;
        }
        return count;
    }

    /**
     * 面试题53-2 0~n-1中缺失的数字
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length-1, mid = 0;
        while(l < r){
            mid = l + (r-l)>>1;
            if(nums[mid] == mid)
                l = mid+1;
            else
                r = mid-1;
        }
        return nums[l] == l ? nums[l]+1 : nums[l]-1;
    }

    /**
     * 面试题54 二叉搜索树的第K大节点
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode t = root;
        while(t != null || !stack.isEmpty()){
            if(t != null){
                stack.push(t);
                t = t.left;
            }
            else{
                t = stack.pop();
                list.add(t.val);
                t = t.right;
            }
        }
        return list.get(list.size() - k);
    }

    /**
     * 面试题55-1 二叉树的最大深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        int ld = maxDepth(root.left);
        int rd = maxDepth(root.right);
        if(ld < rd)
            return rd + 1;
        else
            return ld + 1;
    }

    /**
     * 面试题55-2 判断是否是平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null)
            return true;
        int k = Math.abs(maxDepth(root.left) - maxDepth(root.right));
        if(k <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;
        else
            return false;
    }

    /**
     * 面试题57 和为s的两个数
     * 双指针法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while(true){
            if(nums[l] + nums[r] > target)
                r--;
            else if(nums[l] + nums[r] < target)
                l++;
            else
                break;
        }
        return new int[]{nums[l], nums[r]};
    }

    /**
     * 面试题57-Ⅱ 和为s的连续整数序列
     * 双指针
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int temp = 0;
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 1,j = 2 ; i < j;){
            temp = (i + j)*(j - i + 1)/2;
            if(temp == target){
                int s = i;
                List<Integer> l = new ArrayList<>();
                while(s <= j){
                    l.add(s);
                    s++;
                }
                res.add(l);
                i++;
            }
            else if(temp > target)
                i++;
            else
                j++;
        }
        int[][] r = new int[res.size()][];
        for(int i = 0; i < res.size(); i++){
            r[i] = new int[res.get(i).size()];
            for(int j = 0; j < res.get(i).size(); j++){
                r[i][j] = res.get(i).get(j);
            }
        }
        return r;
    }

    /**
     * 面试题58-1 翻转单词顺序
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        if(s == null || s.length()==0)
            return "";
        s = s.trim();
        String[] g = s.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(g[g.length-1]);
        for(int i = g.length-2; i >= 0; i--){
            if(!g[i].trim().equals(" "))
                sb.append(" "+g[i].trim());
        }
        return sb.toString();
    }

    /**
     * 面试题58-2 左旋转字符串
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        if(n >= s.length())
            return s;
        String s1 = s.substring(0, n);
        String s2 = s.substring(n);
        return s2+s1;
    }

    /**
     * 面试题62 圆圈中最后剩下的数字
     * 方法一：采用链表
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++)
            list.add(i);
        int c = (m-1)%n;
        while(list.size() != 1){
            list.remove(c);
            c = (c+m-1)%list.size();
        }
        return list.get(0);
    }

    /**
     * 面试题68-2 二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return root;
        if(root == p || root == q)
            return root;
        TreeNode ln = lowestCommonAncestor(root.left, p, q);
        TreeNode rn = lowestCommonAncestor(root.right, p, q);
        if(ln != null && rn != null)
            return root;
        if(ln != null)
            return ln;
        if(rn != null)
            return rn;
        return null;
    }
}
