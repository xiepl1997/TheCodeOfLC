package xpl;

import java.util.*;

import javax.xml.transform.Templates;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class LeetCodes {
	//_1两数之和
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map=new HashMap<>();
		for(int i=0;i<nums.length;i++) {
			int temp=target-nums[i];
			if(map.containsKey(temp)) {
				return new int[] {map.get(temp), i};
			}
			map.put(nums[i], i);
		}
		throw new IllegalArgumentException("");
	}
	
	//_2两数相加     8ms
	public class ListNode {
	     int val;
		 ListNode next;
	     ListNode(int x) { val = x; }
	}
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode a = l1, b = l2;
        int puls, ex;
        puls = a.val+b.val;
        ListNode c = new ListNode(puls%10);
        ListNode d = c;
        ex = (a.val+b.val)/10;
        a = a.next;
        b = b.next;
        while(true){
            if(a!=null && b!=null){
                puls = ex + a.val + b.val;
                ex = puls/10;
                c.next = new ListNode(puls%10);
                a = a.next;
                b = b.next;
            }
            else if(a!=null && b==null){
                puls = ex + a.val;
                ex = puls/10;
                c.next = new ListNode(puls%10);
                a = a.next;
            }
            else if(a==null && b!=null){
                puls = ex + b.val;
                ex = puls/10;
                c.next = new ListNode(puls%10);
                b = b.next;
            }       
            else
                break;
            c = c.next;
        }
        if(ex!=0)
            c.next = new ListNode(ex);
        return d;
    }
	
	//_3无重复字符的最长子串
	public int lengthOfLongestSubstring(String s) {
		/*int len = s.length();
		int maxlen = 0;
		int temp = 0;
		ArrayList<Character> list = new ArrayList<Character>();
		for(int i = 0; i < len; i++) {
			if(list.contains(s.charAt(i))) {
				if(maxlen < temp) {
					maxlen = temp;
				}
				temp = 1;
				list.clear();
				list.add(s.charAt(i));
			}
			else {
				list.add(s.charAt(i));
				temp++;
			}
		}
		if(maxlen < temp) {
			maxlen = temp;
		}
		return maxlen;
		*/
		int len = s.length();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int start = 0;
		int now = 0;
		int maxlen = 0;
		for(int i = 0; i < len; i++) {
			if(!map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), i);
				now++;
			}
			else {
				int index = map.get(s.charAt(i));
				for(int j = start; j <= index; j++) {
					map.remove(s.charAt(j));
				}
				start = index + 1;
				map.put(s.charAt(i), i);
				now = i - index;
			}
			maxlen = Math.max(maxlen, now);
		}
		return maxlen;
	}

	/**
	 * 5 最长回文子串， 马拉车算法
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		int mi = 0;
		int right = 0;
		int maxlength = 0;
		int maxpoint = 0;
		String temp = "@#";
		for(int i = 0; i < s.length(); i++){
			temp += s.charAt(i);
			temp += "#";
		}
		temp += "*";

		int[] p = new int[temp.length()];
		for(int i = 0; i < temp.length(); i++){
			p[i] = 0;
		}
		for(int i = 1; i < temp.length()-1; i++){
			p[i] = right > i? Math.min(p[2*mi-i], right - i) : 1;
			while(temp.charAt(i+p[i]) == temp.charAt(i-p[i])){
				p[i]++;
			}
			if(i + p[i] > right){
				right = i + p[i];
				mi = i;
			}
			if(maxlength < p[i]){
				maxlength = p[i];
				maxpoint = i;
			}
		}
		//(maxpoint - maxlength)/2为最长回文数的起始点，maxlength为最长回文数的长度
		return s.substring((maxpoint - maxlength)/2, (maxpoint - maxlength)/2 + maxlength - 1);

	}

	//6 Z型变化
	public String convert(String s, int numRows) {
        String result = "";
        
        
        
        return result;
    }
	
	//7_整数反转
	public int reverse(int x) {
		long result = 0;
		int flag = 0;
		if(x < 0)
			flag = 1;
		x = Math.abs(x);
		while(x!=0) {
			result = result*10 + x%10;
			x = x/10;
		}
		if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			return 0;
		}
		if(flag == 1)
			result = -result;
		return (int)result;
		
	}
	
	//8 字符串转化为整数
	public int myAtoi(String str) {
        long result = 0;
        
        //flag为1表示负数，为0 表示正数
        int flag = 0;
        
		str = str.trim();
		
		int len = str.length();
		if(len == 0)
			return 0;
		if(!Character.isDigit(str.charAt(0)) && str.charAt(0)!='+' && str.charAt(0)!='-') {
			return 0;
		}
		for(int i = 0; i < len; i++) {
			if((str.charAt(i) != '-' && str.charAt(i)!='+' && !Character.isDigit(str.charAt(i))) && i == 0 ) {
				return 0;
			}
			
			if(!Character.isDigit(str.charAt(i)) && i != 0) {
				break;
			}
			
			if(str.charAt(i) == '-' && i == 0) {
				flag = 1;
			}
			
			if(Character.isDigit(str.charAt(i)) ) {
				int temp;
				if(flag == 1) {
					temp = Integer.valueOf(String.valueOf(str.charAt(i)));
					temp = -temp;
				}
				else {
					temp = Integer.valueOf(String.valueOf(str.charAt(i)));
				}
				result = result * 10 + temp;
			}
			if(result > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			else if(result < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
		}
		
        return (int)result;
    }
	
	//9 回文数
	public boolean isPalindrome(int x) {
        if(x < 0)
        	return false;
		int temp = 0;
        int y = x;
        boolean result = false;
        while(y != 0) {
        	temp = temp*10 + y%10;
        	y = y/10;
        }
        if(temp == x)
        	result = true;
        return result;
    }

	/**
	 * 11盛水最多的容器
	 * @param
	 * @return
	 */
//	public int maxArea(int[] height) {
//
//	}
	
	//14最长公共前缀
	public String longestCommonPrefix(String[] strs) {
		if(strs.length == 1) {
			return strs[0];
		}
		if(strs == null || strs.length == 0)
			return "";
        String result = "" ;
        int minlength = Integer.MAX_VALUE;
        for(int i = 0 ;i < strs.length; i++) {
        	if(strs[i].length() < minlength)
        		minlength = strs[i].length();
        }
        if(minlength == 0) {
        	return "";
        }
        
        LABEL:
        for(int i = 0; i < minlength; i++) {
        	for(int j = 0; j < strs.length-1; j++) {
        		if(strs[j].charAt(i) != strs[j+1].charAt(i)) {
        			break LABEL;
        		}
        	}
        	result += strs[0].charAt(i);
        }
        
        return result;
    }
	
	//13罗马数字转整数
	public int romanToInt(String s) {
        int result = 0;
        int len = s.length();
        int i = 0;
        for(i = 0; i < len-1; i++) {
        	if(s.charAt(i) == 'I') {
        		if(s.charAt(i+1) == 'V') {
        			result += 4;
        			i++;
        		}
        		else if(s.charAt(i+1) == 'X') {
        			result += 9;
        			i++;
        		}
        		else {
					result += 1;
				}
        	}
        	else if(s.charAt(i) == 'V') {
        		result += 5;
        	}
        	else if(s.charAt(i) == 'X') {
        		if(s.charAt(i+1) == 'L') {
        			result += 40;
        			i++;
        		}
        		else if(s.charAt(i+1) == 'C') {
        			result += 90;
        			i++;
        		}
        		else {
					result += 10;
				}
        	}
        	else if(s.charAt(i) == 'L') {
        		result += 50;
        	}
        	else if(s.charAt(i) == 'C') {
        		if(s.charAt(i+1) == 'D') {
        			result += 400;
        			i++;
        		}
        		else if(s.charAt(i+1) == 'M') {
        			result += 900;
        			i++;
        		}
        		else {
					result += 100;
				}
        	}
        	else if(s.charAt(i) == 'D') {
        		result += 500;
        	}
        	else if(s.charAt(i) == 'M') {
        		result += 1000;
        	}
        }
        if(i == len-1) {
        	if(s.charAt(i) == 'I')result += 1;
        	else if(s.charAt(i) == 'V')result += 5;
        	else if(s.charAt(i) == 'X')result += 10;
        	else if(s.charAt(i) == 'L')result += 50;
        	else if(s.charAt(i) == 'C')result += 100;
        	else if(s.charAt(i) == 'D')result += 500;
        	else if(s.charAt(i) == 'M')result += 1000;
        }
        return result;
    }

	/**
	 * 17 电话号码的组合，采用递归
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(2,"abc");
		map.put(3,"def");
		map.put(4,"ghi");
		map.put(5,"jkl");
		map.put(6,"mno");
		map.put(7,"pqrs");
		map.put(8,"tuv");
		map.put(9,"wxyz");
		return letterCombinations(digits, map);
	}
	public List<String> letterCombinations(String digits, Map<Integer, String> map){

		List<String> result = new LinkedList<>();

		if(digits.length() == 0)
			return result;

		if(digits.length() == 1){
			String s = map.get(Integer.parseInt(digits));
			for(int i = 0; i < s.length(); i++){
				result.add("" + s.charAt(i));
			}
			return result;
		}

		List<String> p = letterCombinations(digits.substring(1), map);
		String h = map.get(Integer.parseInt(digits.substring(0, 1)));
		for(String s : p){
			for(int i = 0; i < h.length(); i++){
				result.add(h.charAt(i) + s);
			}
		}

		return result;
	}

	/**
	 * 19 删除链表的倒数第N个节点(一遍循环)，用双指针
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode i = head;
		ListNode j = head;
		if(head == null)
			return null;
		for(int a = 0; a < n; a++){
			j = j.next;
		}
		if(j == null){
			return head.next;
		}
		while(j.next != null){
			i = i.next;
			j = j.next;
		}
		if(n == 1){
			i.next = null;
		}
		else{
			i.next = i.next.next;
		}
		return head;
    }

	/**
	 * 20 有效的括号
	 * @param s
	 * @return
	 */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        int len = s.length();
        if(s == "" || len == 0)
        	return true;
        for(int i = 0; i < len; i++) {
        	char ch = s.charAt(i);
        	if(ch == '(' || ch == '[' || ch == '{'){
        		stack.add(ch);
        	}
        	else if(ch == ')') {
        		if(stack.size() == 0)
        			return false;
        		if(stack.peek() == '(') {
        			stack.pop();
        		}
        		else {
					return false;
				}
        	}
        	else if(ch == ']') {
        		if(stack.size() == 0)
        			return false;
        		if(stack.peek() == '[')
        			stack.pop();
        		else {
					return false;
				}
        	}
        	else if(ch == '}') {
        		if(stack.size() == 0)
        			return false;
        		if(stack.peek() == '{') {
        			stack.pop();
        		}
        		else {
					return false;
				}
        	}
        }
        if(stack.size() != 0)
        	return false;
        
        return true;
    }

	/**
	 * 21 合并两个有序链表
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode listNode;
    	if(l1 == null)
    		return l2;
    	if(l2 == null)
    		return l1;
    	if(l1.val < l2.val) {
    		listNode = l1;
    		listNode.next = mergeTwoLists(l1.next, l2);
    	}
    	else {
    		listNode = l2;
    		listNode.next = mergeTwoLists(l1, l2.next);
    	}
    	return listNode;
    }

	/**
	 * 22 括号生成
	 * @param n
	 * @return
	 */
//	public List<String> generateParenthesis(int n) {
//
//	}

	/**
	 * 26 删除排序数组中的重复项
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		if(nums.length == 0)
			return 0;

		int j = 0;
		for(int i = 1; i  < nums.length; i++){
			if(nums[i] != nums[j]){
				j++;
				nums[j] = nums[i];
			}
		}

		return j+1;
	}

	/**
	 * 27移除元素
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {
		if(nums.length == 0){
			return 0;
		}
		int j = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] != val ){
				nums[j] = nums[i];
				j++;
			}
		}
		return j+1;
	}

	/**
	 * 28 实现strStr()
	 * @param haystack
	 * @param needle
	 * @return
	 */
//	public int strStr(String haystack, String needle) {
//		if(needle == null){
//			return 0;
//		}
//
//	}

	/**
	 * 31 下一个排列
	 * 从右往左找到第一对nums[i]>nums[i-1]，然后再从最右边往前找，找到第一个大于nums[i-1]的元素nums[j]，交换nums[i-1]和nums[j]，
	 * 最后再将nums[i-1]之后的元素反转。
	 * @param nums
	 */
	public void nextPermutation(int[] nums) {
		int i = nums.length-1;
		int flag = 0; //用于标记序列是否是全降序，0是，1不是
		for(; i > 0; i--){
			if(nums[i] > nums[i-1]){
				i--;
				flag = 1;
				break;
			}
		}
		if(flag == 1) {
			int j = nums.length - 1;
			for (; j >= 0; j--) {
				if (nums[j] > nums[i]) {
					int temp = nums[j];
					nums[j] = nums[i];
					nums[i] = temp;
					break;
				}
			}
			for (int k = i + 1, l = nums.length - 1; k < nums.length; k++, l--) {
				if (k > (i + nums.length) / 2) {
					break;
				}
				int temp = nums[k];
				nums[k] = nums[l];
				nums[l] = temp;
			}
		}
		else{
			for(int k = 0,l = nums.length-1; k < nums.length; k++, l--){
				if(k > (nums.length-1)/2){
					break;
				}
				int temp = nums[k];
				nums[k] = nums[l];
				nums[l] = temp;
			}
		}
	}

	/**
	 * 35 搜索插入位置，使用二分法
	 * @param nums
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
		int left = 0;
		int right = nums.length-1;
		int mid = 0;
		if(target > nums[nums.length-1])
			return nums.length;
		while(left <= right){
			mid = (left + right) / 2;
			if(nums[mid] == target){
				return mid;
			}
			else if(nums[mid] < target){
				left = mid+1;
			}
			else if(nums[mid] > target){
				right = mid - 1;
			}
		}
		return left;

	}

	/**
	 * 46 全排列
	 * @param nums
	 * @return
	 */
//	public List<List<Integer>> permute(int[] nums) {
//
//	}

    /**
     * 53.最大子序和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int sum = 0;
        int result = nums[0];
        for(int i = 0; i < nums.length; i++){
        	if(sum > 0)
        		sum += nums[i]; //若当前值为正数，则继续加
        	else
        		sum = nums[i]; //若当前值为负数，则取当前值也比（负数+当前值）更大
			result = Math.max(result, sum);
		}
        return result;
    }

	/**
	 * 54 螺旋矩阵
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if(matrix.length == 0)
			return result;
		int m = matrix[0].length;
		int n = matrix.length;
		int up = 0, down = n-1, left = 0, right = m-1;
		while(true){
			for(int i = left; i <= right; i++){
				result.add(matrix[up][i]);
			}
			if(++up > down)
				break;
			for(int i = up; i <= down; i++){
				result.add(matrix[i][right]);
			}
			if(--right < left)
				break;
			for(int i = right; i >= left; i--){
				result.add(matrix[down][i]);
			}
			if(--down < up)
				break;
			for(int i = down; i >= up; i--){
				result.add(matrix[i][left]);
			}
			if(++left > right)
				break;
		}
		return result;
	}

	/**
	 * 55 跳跃游戏
	 * @param nums
	 * @return
	 */
//	public boolean canJump(int[] nums) {
//		int len = nums.length;
//
//	}

	/**
	 * 56 合并区间
	 * @param intervals
	 * @return
	 */
//	public int[][] merge(int[][] intervals) {
//
//	}

	/**
	 * 58 最后一个单词的长度
	 * @param s
	 * @return
	 */
	public int lengthOfLastWord(String s) {
		if(s.length() == 0)
			return 0;
		int len = s.length();
		int result = 0;
		int flag = 0;
		for(int i = len-1; i >= 0; i--){
			if(flag == 1){
				if(s.charAt(i) != ' ') {
					result ++;
				}
				else{
					break;
				}
			}
			//从末尾第一次遇到非空格字符标记flag
			if(flag == 0 && s.charAt(i) != ' '){
				flag = 1;
				result ++;
			}
		}
		return result;
	}

	/**
	 * 59 螺旋矩阵二
	 * @param n
	 * @return
	 */
	public int[][] generateMatrix(int n) {
		int temp = 0;
		int[][] result = new int[n][n];
		if(n == 0)
			return result;
		int up = 0, down = n-1, left = 0, right = n-1;
		while(true){
			for(int i = left; i <= right; i++)
				result[up][i] = ++temp;
			if(++up > down)
				break;
			for(int i = up; i <= down; i++)
				result[i][right] = ++temp;
			if(--right < left)
				break;
			for(int i = right; i >= left; i--)
				result[down][i] = ++temp;
			if(--down < up)
				break;
			for(int i = down; i >= up; i--)
				result[i][left] = ++temp;
			if(++left > right)
				break;
		}
		return result;
	}

	/**
	 * 62 不同路径，m*n矩阵中左上角到右下角的路径数量
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		int[][] r = new int[m][n];

		if(m == 1|| n == 1)
			return 1;

		for(int i = 0; i < m; i++){
			r[i][0] = 1;
		}
		for(int i = 0; i < n; i++){
			r[0][i] = 1;
		}
		for (int i = 1; i < m; i++){
			for(int j = 1; j < n; j++){
				r[i][j] = r[i-1][j] + r[i][j-1];
			}
		}
		return r[m-1][n-1];
	}

	/**
	 * 63 不同路径2（有障碍物）
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if(obstacleGrid.length == 0)
			return 0;
		int[][] r = new int[obstacleGrid.length][obstacleGrid[0].length];
		for(int i = 0; i < r.length; i++){
			if(obstacleGrid[i][0] != 1)
				r[i][0] = 1;
			else{
				for(int j = i; j < r.length; j++)
					r[j][0] = 0;
				break;
			}
		}
		for(int i = 0; i < r[0].length; i++){
			if(obstacleGrid[0][i] != 1)
				r[0][i] = 1;
			else{
				for(int j = i; j < r[0].length; j++)
					r[0][i] = 0;
				break;
			}
		}
		for(int i = 1; i < r.length; i++){
			for(int j = 1; j < r[0].length; j++){
				if(obstacleGrid[i][j] != 1){
					r[i][j] = r[i-1][j]+r[i][j-1];
				}
				else{
					r[i][j] = 0;
				}
			}
		}
		return r[obstacleGrid.length-1][obstacleGrid[0].length-1];
	}

	/**
	 * 64 最小路径和
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
		if(grid.length == 0)
			return 0;
		//辅助二维数组
		int[][] r = new int[grid.length][grid[0].length];
		r[0][0] = grid[0][0];
		for(int i = 1; i < r.length; i++){
			r[i][0] += grid[i][0] + r[i-1][0];
		}
		for(int i = 1; i < r[0].length; i++){
			r[0][i] += grid[0][i] + r[0][i-1];
		}
		for(int i = 1; i < r.length; i++){
			for(int j = 1; j < r[0].length; j++){
				r[i][j] += grid[i][j]+Math.min(r[i-1][j],r[i][j-1]);
			}
		}
		return r[r.length-1][r[0].length-1];
	}

	/**
	 * 66 加一
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {
		if(digits.length == 0)
			return new int[]{1};
		int len = digits.length - 1;
		int t = 1;
		int flag = 0;
		while(t != 0){
			if(digits[len]+t >= 10){
				digits[len] = (digits[len]+t)%10;
				if(len == 0){
					t = 0;
					flag = 1;
				}
			}
			else{
				digits[len]++;
				t = 0;
			}
			len--;
		}
		if(flag == 0)
			return digits;
		else{
			int[] a = new int[digits.length+1];
			a[0] = 1;
			for(int i = 1; i < digits.length+1; i++){
				a[i] = digits[i-1];
			}
			return a;
		}
	}

	/**
	 * 67 二进制求和
	 * @param a
	 * @param b
	 * @return
	 */
	public String addBinary(String a, String b) {
		StringBuilder ans = new StringBuilder();
		int p = 0;
		for(int i = a.length()-1,j = b.length()-1; i >= 0 || j >= 0; i--,j--){
			int sum = p;
			if(i >= 0)
				sum += a.charAt(i)-'0';
			if(j >= 0)
				sum += b.charAt(j)-'0';
			ans.append(sum%2);
			p = sum/2;
		}
		if(p == 1)
			ans.append(1);
		return ans.reverse().toString();
	}

	/**
	 * 68 文本左右对齐
	 * @param words
	 * @param maxWidth
	 * @return
	 */
//	public List<String> fullJustify(String[] words, int maxWidth) {
//
//	}

	/**
	 * 69 求平方根，牛顿迭代
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		if(x == 0 )
			return 0;
		int a = x;
		while(a > x/a){
			a = (a + x/a)/2;
		}
		return (int)a;
	}

	/**
	 * 70 爬楼梯， 斐波那契数列, 剪纸
	 * @param n
	 * @return
	 */
	int[] result = new int[100];
	public int F(int a){
		if(result[a] != -1)
			return result[a];
		return result[a] = F(a-1) + F(a-2);
	}
	public int climbStairs(int n) {
		for(int i = 0; i < result.length; i++){
			result[i] = -1;
		}
		result[0] = result[1] = 1;
		return F(n);
	}

	/**
	 * 73 矩阵置零
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		int[] x = new int[matrix.length];
		int[] y = new int[matrix[0].length];
		for(int i = 0; i < x.length; i++)
			x[i] = 0;
		for(int i = 0; i < y.length; i++)
			y[i] = 0;
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				if(matrix[i][j] == 0){
					x[i] = 1;
					y[j] = 1;
				}
			}
		}
		for(int i = 0; i < x.length; i++) {
			if(x[i] == 1){
				for(int j = 0; j < matrix[i].length; j++){
					matrix[i][j] = 0;
				}
			}
		}
		for(int i = 0; i < y.length; i++) {
			if(y[i] == 1){
				for(int j = 0; j < matrix.length; j++){
					matrix[j][i] = 0;
				}
			}
		}
	}

	/**
	 * 74 搜索二维矩阵
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		for(int i = 0; i < matrix.length; i++){
			if(matrix[i].length == 0)
				continue;
			if(matrix[i][0] <= target && matrix[i][matrix[0].length] >= target){
				return judge(matrix[i], target);
			}
		}
		return false;
	}
	//二分法
	public boolean judge(int[] list, int target){
		int left = 0, right = list.length - 1, m = 0;
		while(left <= right){
			m = (left + right)/2;
			if(list[m] == target)
				return true;
			else if(list[m] > target){
				right = m - 1;
			}
			else if(list[m] < target){
				left = m + 1;
			}
		}
		return false;
	}

	/**
	 * 79 单词搜索 dfs
	 * @param board
	 * @param word
	 * @return
	 */
	int[][] b;
	public boolean exist(char[][] board, String word) {
		int m = board[0].length;
		int n = board.length;
		b = new int[n][m];
		int index = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(board[i][j] == word.charAt(0)){
					for(int k = 0; k < n; k++)
						for(int l = 0; l < m; l++)
							b[k][l] = 0;
					b[i][j] = 1;
					if(dfs(board, word, 0, i, j)){
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean dfs(char[][] board, String word, int index, int i, int j){
		if(index == word.length()-1 && word.charAt(index) == board[i][j])
			return true;
		if(index == word.length()-1 && word.charAt(index) != board[i][j])
			return false;
		//上
		if(i > 0 && b[i-1][j]==0){
			if(board[i-1][j] == word.charAt(index+1) ){
				b[i-1][j] = 1;
				if(dfs(board, word, index+1, i-1, j))
					return true;
				b[i-1][j] = 0;
			}
		}
		//下
		if(i < board.length-1 && b[i+1][j]==0){
			if(board[i+1][j] == word.charAt(index+1) ) {
				b[i+1][j] = 1;
				if (dfs(board, word, index + 1, i + 1, j))
					return true;
				b[i+1][j] = 0;
			}
		}
		//左
		if(j > 0 && b[i][j-1]==0){
			if(board[i][j-1] == word.charAt(index+1) ){
				b[i][j-1] = 1;
				if(dfs(board, word, index+1, i, j-1))
					return true;
				b[i][j-1] = 0;
			}
		}
		//右
		if(j < board[0].length-1 && b[i][j+1]==0){
			if(board[i][j+1] == word.charAt(index+1)){
				b[i][j+1] = 1;
				if(dfs(board, word, index+1, i, j+1))
					return true;
				b[i][j+1] = 0;
			}
		}
		return false;
	}

	/**
	 * 83 删除排序列表中的相同元素
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode l = head;
		while(l.next != null){
			if(l.val == l.next.val){
				l.next = l.next.next;
				continue;
			}
			l = l.next;
		}
		return head;
	}

	/**
	 * 88 合并两个有序数组
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		System.arraycopy(nums2, 0, nums1, m, n);
		Arrays.sort(nums1);
	}

	/**
	 * 125 验证回文串
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(String s) {
		if(s == null)
			return true;
		s = s.toLowerCase();
		StringBuilder sb = new StringBuilder("#");
		for(int i = 0; i < s.length(); i++){
			if((s.charAt(i) >= 'a' && s.charAt(i) <= 'z')||(s.charAt(i) >= '0' && s.charAt(i) <= '9')){
				sb.append(s.charAt(i));
				sb.append("#");
			}
		}
		System.out.println(sb.toString());
		String str = sb.toString();
		int m = str.length()/2;
		int i = 1;
		while(i <= m){
			if(str.charAt(m-i) == str.charAt(m+i)){
				i++;
			}
			else
				return false;
		}
		return true;
	}

    /**
     * 136 只出现一次的数字，要求时间复杂度在0(n)，除了一个只出现一次的数之外，其他的数都出现了两次，所以使用异或来计算整个数组，两个相同的数异或肯定是0，而与0异或则是其本身，
     * 所以最后异或的结果就是只出现一次的那个数
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            result = result ^ nums[i];
        }
        return result;
    }

	/**
	 * 152 最大乘积和
	 * @param nums
	 * @return
	 */
	public int maxProduct(int[] nums) {
		if(nums.length == 0)
			return 0;
		int max = nums[0];
		for(int i = 1; i < nums.length; i++){
			if(max * nums[i] < 0){

			}
		}
		return max;
	}

    /**
     * 169 求众数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length/2];
    }

	/**
	 * 215 数组中的第k大的数
	 * @param nums
	 * @param k
	 * @return
	 */
	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}

	/**
	 * 240 搜索二维矩阵2
	 * 从矩阵的左上角开始搜索，如果当前值大，则向下继续寻找，否则向左寻找
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix2(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
			return false;
		}
		int i = 0, j = matrix[0].length - 1;
		while(i < matrix.length && j >= 0){
			if(matrix[i][j] == target)
				return true;
			if(matrix[i][j] < target){
				i++;
			}
			else{
				j--;
			}
		}
		return false;
	}

	/**
	 * 887 鸡蛋掉落
	 * @param K
	 * @param N
	 * @return
	 */
//	public int superEggDrop(int K, int N) {
//
//	}

	/**
	 * 997 小镇的法官
	 * @param N
	 * @param trust
	 * @return
	 */
	public int findJudge(int N, int[][] trust) {
		int[] val1 = new int[N]; //每个人被相信的次数
		int[] val2 = new int[N]; //每个人相信别人的次数
		for(int i = 0;i < trust.length; i++){
			val1[trust[i][1]-1]++;
			val2[trust[i][0]-1]++;
		}
		for(int i = 0; i < N; i++){
			if(val1[i] == N-1 && val2[i] == 0){
				return i+1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		boolean t = isPalindrome("A man, a plan, a canal: Panama");
	}

}
class ListNode {
    int val;
  	ListNode next;
 	ListNode(int x) { val = x; }
}
















