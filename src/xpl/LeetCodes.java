package xpl;

import javafx.scene.layout.Priority;

import java.lang.reflect.Array;
import java.util.*;

import javax.xml.transform.Templates;


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
     * 3 优化的滑动窗口，无重复的最长字串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_1(String s) {
        int n = s.length();
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0, j = 0; j < n; j++){
            if(map.containsKey(s.charAt(j))){
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j-i+1);
            map.put(s.charAt(j), j+1);
        }
        return ans;
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

	/**
	 * 6 Z型变换
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert(String s, int numRows) {
        if(numRows == 1){
        	return s;
		}
        List<StringBuilder> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
        	list.add(new StringBuilder());
		}
        int flag = -1;
        int j = 0;
        for(int i = 0; i < s.length(); i++){
        	list.get(j).append(s.charAt(i));
        	if(j == 0 || j == numRows-1){
        		flag = -flag;
			}
        	j += flag;
		}
        String str = "";
        for(int i = 0; i < list.size(); i++){
        	str += list.get(i).toString();
		}

        return str;
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
	 * 使用双指针
	 * @param
	 * @return
	 */
	public int maxArea(int[] height) {
		int i = 0, j = height.length-1;
		int sum = 0;
		while(true){
			int temp = (j-i)*(height[i] <= height[j] ? height[i++] : height[j--]);
			sum = sum < temp ? temp : sum;
			if(i >= j)
				break;
		}
		return sum;
	}
	
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
	 * 15 三数之和，三指针
	 * 思路：首先对数组进行排序利用三指针。k指针遍历每一个元素，i和j分别指向nums[k]的后面部分的起始端和结尾端，判断
	 * s=nums[k]+nums[i]+nums[j]是否等于0，如果s=0，则将结果添加到list中，如果s<0，则说明nums[i]+[j]的值过小，则需要将
	 * i++，以此来提高num[i]的值；如果s>0，则说明nums[i]+nums[j]的值过大，需要将j--，以此来减小nums[j]的值。
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		for(int k = 0; k < nums.length-2; k++){
			int i = k + 1, j = nums.length - 1;
			if(nums[k] > 0){
				break;
			}
			else if(k > 0 && nums[k] == nums[k-1]){
				continue;
			}
			while(i < j){
				int s = nums[k] + nums[i] + nums[j];
				if(s > 0){
					while(i < j && nums[j] == nums[--j]);
				}
				else if(s < 0){
					while(i < j && nums[i] == nums[++i]);
				}
				else{
					result.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
					while(i < j && nums[i] == nums[++i]);
					while(i < j && nums[j] == nums[--j]);
				}
			}
		}
		return result;
	}

	/**
	 * 16 最接近的三数和，三指针
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int min = 0x3f3f3f3f;
		int result = 0;
		for(int k = 0; k < nums.length-2; k++){
			int i = k + 1, j = nums.length - 1;
			while(i < j){
				int s = nums[k] + nums[i] + nums[j];
				//如果三数和大于target，就将j--
				if(s > target){
					while(i < j && nums[j] == nums[--j]);
				}
				else if(s < target){
					while(i < j && nums[i] == nums[++i]);
				}
				else{
					return s;
				}
				result = min > Math.abs(s - target) ? s : result;
				min = Math.min(min, Math.abs(s - target));
			}
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

    public ListNode mergeTwoLists_1(ListNode l1, ListNode l2){
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists_1(l1.next, l2);
			return l1;
		}
		else{
			l2.next = mergeTwoLists_1(l1, l2.next);
			return l2;
		}
	}

	/**
	 * 22 括号生成
	 * 采用回溯法
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		dg(res, "", 0, 0, n);
		return res;
	}
	//递归
	public void dg(List<String> res, String s, int lcount, int rcount, int n){
		if(lcount > n || rcount > n)
			return;
		if(lcount == n && rcount == n){
			res.add(s);
			return;
		}
		//时刻保持左边括号数量大于右边括号数量
		if(lcount >= rcount){
			String str = s;
			dg(res, str+"(", lcount+1, rcount, n);
			dg(res, str+")", lcount, rcount+1, n);
		}
	}

	/**
	 * 24 两两交换链表中的节点
	 * @param head
	 * @return
	 */
	public ListNode swapPairs(ListNode head) {
		if(head == null || head.next == null){
			return head;
		}
		ListNode p1 = head.next;
		head.next = p1.next;
		p1.next = head;
		head = p1;
		p1 = p1.next.next;
		ListNode p2 = head.next;
		ListNode temp = null;
		while(p1 != null && p1.next != null){
			temp = p1.next;
			p1.next = p1.next.next;
			temp.next = p1;
			p2.next = temp;
			p2 = temp.next;
			p1 = p1.next;
		}
		return head;
	}

	/**
	 * 25 k个一组翻转链表
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode temp = head, curr = head,pre = dummy , later;
		int len = 0;
		while(temp != null){
			len++;
			temp = temp.next;
		}
		for(int i = 0; i < len / k; i++){
			for(int j = 0; j < k-1; j++){
				later = curr.next;
				curr.next = curr.next.next;
				later.next = pre.next;
				pre.next = later;
			}
			pre = curr;
			curr = pre.next;
		}
		return dummy.next;

	}

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
	public int strStr(String haystack, String needle) {
		if(needle == null || needle.length() == 0){
			return 0;
		}
		if(haystack.length() < needle.length()){
			return -1;
		}
		int s = 0;
		int e = 0;
		for(int i = 0; i < haystack.length(); i++){
			if(haystack.charAt(i) == needle.charAt(0)){
				if(needle.length() == 1)
					return 0;
				if(i == haystack.length()-1){
					if(needle.length() == 1){
						return i;
					}
					else{
						return -1;
					}
				}
				s = 0;
				for(int j = i+1; ; j++){
					if(j == haystack.length() || s+1 == needle.length())
						return -1;
					if(haystack.charAt(j) == needle.charAt(++s)){
						if(s == needle.length()-1){
							return i;
						}
					}
					else{
						break;
					}
				}
			}
		}
		return -1;

	}

    /**
     * 29 两数相除
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if(dividend < divisor){
        	return 0;
		}
        if(dividend == divisor){
        	return 1;
		}
        if(dividend == Integer.MIN_VALUE && divisor == -1){
        	return Integer.MAX_VALUE;
		}
        boolean f = (dividend ^ divisor) < 0;
        long a1 = (long)Math.abs(dividend);
        long a2 = (long)Math.abs(divisor);
        int res = 0;
        for(int i = 31; i >= 0; i--){
        	if((a1 >> i) >= a2){
        		res += 1 << i;
        		a1 -= a2 << i;
			}
		}
        return f ? -res : res;
    }

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
	 * 32 最长有效括号
	 * 使用栈
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		int maxLen = 0;
		stack.push(-1);
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == '(')
				stack.push(i);
			else{
				stack.pop();
				if(stack.empty()){
					stack.push(i);
				}
				else{
					maxLen = Math.max(maxLen, i - stack.peek());
				}
			}
		}
		return maxLen;
	}

	/**
	 * 32 最长的有效括号
	 * 使用动态规划
	 * @param s
	 * @return
	 */
	public int longestValidParentheses_1(String s) {
		int res = 0;
		int[] dp = new int[s.length()];
		for(int i = 1; i < s.length(); i++){
			//判断两位
			if(s.charAt(i) == ')'){
				//如果是 “()”
				if(s.charAt(i-1) == '('){
					dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
				}
				//如果是 “))”
				else if(i-1-dp[i-1]>=0 && s.charAt(i-1-dp[i-1]) == '('){
					dp[i] = dp[i-1]+(i-1-dp[i-1]-1>0?dp[i-1-dp[i-1]-1]:0)+2;
				}
				res = Math.max(res, dp[i]);
			}
		}
		return res;
	}

	/**
	 * 33 搜索旋转排序数组（二分法）
	 * 数组旋转后一定有一半有序，一半无序，有序的部分按照二分法处理，无序的部分则再一分为二，分为有序的一半和无序的一半
	 * @param nums
	 * @param target
	 * @return
	 */
	public int search(int[] nums, int target) {
		int left = 0, right = nums.length-1, mid = right/2;
		while(left <= right){
			if(nums[mid] == target)
				return mid;
			if(nums[left] <= nums[mid]){ //左边升序
				if(target >= nums[left] && target <= nums[mid])
					right = mid-1;
				else
					left = mid+1;
			}
			else{
				if(target >= nums[mid] && target <= nums[right])
					left = mid+1;
				else
					right = mid-1;
			}
			mid = left + (right - left)/2;
		}
		return -1;
	}

	/**
	 * 34 在排序数组中查找元素的第一个和最后一个位置
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] searchRange(int[] nums, int target) {
		if(nums.length == 0){
			return new int[]{-1,-1};
		}
		if(nums.length == 1 && nums[0] == target){
			return new int[]{0,0};
		}
		else if(nums.length == 1){
			return new int[]{-1,-1};
		}
		int left = 0;
		int right = nums.length-1;
		while(left < right){
			int m = left+(right - left)/2;
			if(nums[m] == target){
				int l = m, r = m;
				while(--l >= 0 && nums[l] == target);
				while(++r < nums.length && nums[r] == target);
				l++;
				r--;
				return new int[]{l, r};
			}
			else if(nums[m] > target){
				right = m - 1;
			}
			else if(nums[m] < target){
				left = m + 1;
			}
		}
		return new int[]{-1, -1};
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
	 * 36 有效的数独
	 * @param board
	 * @return
	 */
//	public boolean isValidSudoku(char[][] board) {
//
//	}

	/**
	 * 38 报数
	 * @param n
	 * @return
	 */
	public String countAndSay(int n) {

	}

	/**
	 * 39 组合总数
	 * 回溯算法
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(candidates);
		combinationSum39(res, target, candidates, new ArrayList<>(), 0, 0);
		return res;
	}
	public void combinationSum39(List<List<Integer>> res, int target, int[] candidates, List<Integer> temp, int index, int sum){
		if(sum == target){
			res.add(new ArrayList<>(temp));
			return;
		}
		if(sum > target){
			return;
		}
		for(int i = index; i < candidates.length; i++){
			temp.add(candidates[i]);
			combinationSum39(res, target, candidates, temp, i, sum+candidates[i]);
			temp.remove(temp.size()-1);
		}
	}

	/**
	 * 40 组合总数2
	 * 回溯法
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(candidates);
		combinationSum39_2(res, target, candidates, new ArrayList<>(), -1, 0);
		return res;
	}
	public void combinationSum39_2(List<List<Integer>> res, int target, int[] candidates, List<Integer> temp, int index, int sum){
		if(sum == target){
			if(!res.contains(temp)){
				res.add(new ArrayList<>(temp));
			}
			return;
		}
		if(sum > target){
			return;
		}
		for(int i = index+1; i < candidates.length; i++){
			temp.add(candidates[i]);
			combinationSum39_2(res, target, candidates, temp, i, sum+candidates[i]);
			temp.remove(temp.size()-1);
			if(sum+candidates[i] > target){
				break;
			}
		}
	}

    /**
     * 45 跳跃游戏Ⅱ
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int end = 0;
        int max = 0;
        int res = 0;
        for(int i = 0; i < nums.length-1; i++){
            max = max > nums[i]+i ? max : nums[i]+i;
            if(i == end){
                end = max;
                res++;
            }
        }
        return res;
    }

	/**
	 * 46 全排列，采用回溯算法，深度搜索寻找所有结果
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if(nums.length == 0){
			return res;
		}
		boolean[] flag = new boolean[nums.length];
		dfs(nums, res, new Stack<Integer>(), flag, 0);
		return res;
	}

	/**
	 *
	 * @param nums
	 * @param res 结果集
	 * @param stack 单个结果
	 * @param flag 访问标记
	 * @param index 开始下标
	 */
	public void dfs(int[] nums, List<List<Integer>> res, Stack<Integer> stack, boolean[] flag, int index){
		if(index == nums.length){
			res.add(new ArrayList<>(stack));
			return;
		}
		for(int i = 0; i < nums.length; i++){
			if(flag[i] != true){
				stack.push(nums[i]);
				flag[i] = true;
				dfs(nums, res, stack, flag, index + 1);
				flag[i] = false; //状态重置
				stack.pop();
			}
		}
	}

	/**
	 * 47 全排列2，返回所有不重复的全排列
	 * 回溯+减枝，与46题的区别在于：1.首先对nums数组进行排序，在dfs中
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if(nums.length == 0)
			return res;
		boolean[] flag = new boolean[nums.length];
		Arrays.sort(nums);
		dfs_47(nums, res, new Stack<Integer>(), flag, 0);
		return res;
	}

	public static void dfs_47(int[] nums, List<List<Integer>> res, Stack<Integer> stack, boolean[] flag, int index){
		if(index == nums.length){
			res.add(new ArrayList<>(stack));
			return;
		}
		for(int i = 0; i < nums.length; i++){
			//和全排列的区别在于这里，每次判断是否是与前一个数相同，若与前一个数相同，则直接跳过。避免重复
//			if(i != 0 && nums[i] == nums[i-1]){
//				continue;
//			}
			if(flag[i] != true){
				if(i != 0 && nums[i] == nums[i-1] && flag[i-1] == false){
					continue;
				}
				stack.push(nums[i]);
				flag[i] = true;
				dfs_47(nums, res, stack, flag, index + 1);
				flag[i] = false; // 状态重置
				stack.pop();
			}
		}
	}

	/**
	 * 48 旋转图像，首先将上下对调，再将矩阵左上角到右下角对角交换
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		int s = 0, e = n-1;
		while(s < e){
			for(int i = 0; i < n; i++){
				int temp = matrix[s][i];
				matrix[s][i] = matrix[e][i];
				matrix[e][i] = temp;
			}
			s++;
			e--;
		}
		for(int i = 0; i < n; i++){
			for(int j = i+1; j < n; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}

		}

	}

	/**
	 * 49 字母异位词分组
	 * @param strs
	 * @return
	 */
	public List<List<String>> groupAnagrams(String[] strs) {
		if(strs.length == 0)
			return new ArrayList<>();
		Map<String, List> ans = new HashMap<>();
		int[] count = new int[26];
		for(String s : strs){
			Arrays.fill(count, 0);
			for(char c : s.toCharArray()){
				count[c-'a']++;
			}
			StringBuilder sb = new StringBuilder("");
			for(int i : count){
				sb.append('#');
				sb.append(i);
			}
			String key = sb.toString();
			if(!ans.containsKey(key))
				ans.put(key, new ArrayList());
			ans.get(key).add(s);
		}
		return new ArrayList(ans.values());
	}

	/**
	 * 50 pow(x, n)，计算x的y次幂，暴力法（超时）
     * 只需模拟x相乘n次的过程，当n小于0时，用1/x代替x，用-n代替n
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow_1(double x, int n) {
        long N = n;
        if(N < 0){
            x = 1/x;
            N = -N;
        }
        double ans = 1;
        for(int i = 1; i <= N; i++){
            ans = ans * x;
        }
        return ans;
	}

    /**
     * 50 pow(x, n)，计算x的y次幂，递归快速幂算法
     * 假设已经得到了x^(n/2)，
     * @param x
     * @param n
     * @return
     */
	public double myPow_2(double x, int n){
        int N = n;
        if(N < 0){
            N = -N;
            x = 1/x;
        }
        return fastPow(x, n);
    }
    public double fastPow(double x, long n){
        if(n == 0)
            return 1.0;
        double half = fastPow(x, n/2);
        if(n % 2 == 0)
            return half*half;
        else
            return half*half*x;
    }

    /**
     * 50 pow(x,n) 计算x的y次幂，
     * @param x
     * @param n
     * @return
     */
    public double myPow_3(double x, int n){

    }

	/**
	 * 51 N皇后
	 * @param n
	 * @return
	 */
	private List<List<String>> output = new ArrayList<>();
	//用于标记是否被列方向的皇后攻击
	int[] rows;
	//用于标记是否被主对角线方向的皇后攻击
	int[] mains;
	//用于标记是否被次对角线方向的皇后攻击
	int[] secondary;
	//用于存储皇后放置的位置
	int[] queens;
	int n;
	public List<List<String>> solveNQueens(int n) {
		//初始化
		rows = new int[n];
		mains = new int[2*n-1];
		secondary = new int[2*n-1];
		queens = new int[n];
		this.n = n;
		//从第一行开始求解N皇后
		backtrack(0);
		return output;
	}
	//在第一行中放置一个皇后
	private void backtrack(int row){
		if(row >= n)
			return;
		//分别尝试在第row行中的每一列中放置皇后
		for(int col = 0; col < n; col++){
			//判断当前放置的皇后是否被攻击
			if(isNotUnderAttack(row, col)){
				//在当前的位置上放置皇后
				placeQueen(row, col);
				if(row == n-1)
					addSolution();
				backtrack(row+1);
				removeQueen(row, col);
			}
		}
	}
	//判断row行，col列这个位置有没有其他方向的皇后攻击
	private boolean isNotUnderAttack(int row, int col){
		/*
		判断的逻辑是：
			当前位置列方向没有皇后攻击；
			当前位置主对角线方向没有皇后攻击；
			当前位置次对角线方向没有皇后攻击；
		 */
		int res = rows[col]+mains[row-col+n-1]+secondary[row+col];
		return res == 0;
	}
	//在指定的位置上放置皇后
	private void placeQueen(int row, int col){
		//在row行col列上放置皇后
		queens[row] = col;
		//当前位置的列方向设置为存在皇后
		rows[col] = 1;
		//当前位置的主对角线方向设置为存在皇后
		mains[row-col+n-1] = 1;
		//当前位置的次对角线方向设置为存在皇后
		secondary[row+col] = 1;
	}
	//移除元素
	private void removeQueen(int row, int col){
		queens[row] = 0;
		rows[col] = 0;
		mains[row-col+n-1] = 0;
		secondary[row+col] = 0;
	}
	//将满足条件的皇后位置放入output中
	private void addSolution(){
		List<String> solution = new ArrayList<>();
		for(int i = 0; i < n; i++){
			int col = queens[i];
			String str = "";
			for(int j = 0; j < col; j++){
				str += ".";
			}
			str += "Q";
			for(int j = col+1; j < n; j++){
				str += ".";
			}
			solution.add(str);
		}
		output.add(solution);
	}

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
	public boolean canJump(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
		}
		return lastPos == 0;
	}

	/**
	 * 56 合并区间
	 * @param intervals
	 * @return
	 */
	public int[][] merge(int[][] intervals) {
		Comparator<int[]> comparator = new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] == b[0] ? a[1]-b[1] : a[0]-b[0];
			}
		};

		List<int[]> list = new ArrayList<>();

		Arrays.sort(intervals, comparator);
		for(int i = 0; i < intervals.length; i++){
			int left = intervals[i][0];
			int right = intervals[i][1];
			while(i < intervals.length-1 && intervals[i+1][0] >= left && intervals[i+1][0] <= right ){
				right = Math.max(right, intervals[i+1][1]);
				i++;
			}
			list.add(new int[]{left, right});
		}
		return list.toArray(new int[list.size()][2]);

	}

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
	 * 60 第k个排列
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation(int n, int k) {

	}

	/**
	 * 61 旋转链表
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode rotateRight(ListNode head, int k) {
		if(head == null || k == 0)
			return head;
		int len = 0;
		ListNode l = head;
		while(l != null){
			len++;
			l = l.next;
		}
		if(len == 1 || len == k)
			return head;
		if(k > len)
			k = k % len;
		if(k == 0)
			return head;
		l = head;
		for(int i = 1; i < len-k; i++){
			l = l.next;
		}
		ListNode l2 = l;
		l = l.next;
		ListNode l3 = l;
		l2.next = null;
		while(l.next != null)
			l = l.next;
		l.next = head;
		return l3;

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

	//二分法
	public int mySqrt2(int x){
		if(x == 0 || x == 1)
			return x;
		long left = 0, right = x, mid = 0;
		while(left <= right){
			mid = left + (right - left)/2;
			if(mid * mid == x)
				return (int)mid;
			else if(mid *mid < x && (mid+1)*(mid+1) > x)
				return (int)mid;
			else if(mid * mid < x)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return 0;
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
	 * 75 颜色分类（荷兰国旗问题）
	 * @param nums
	 */
	public void sortColors(int[] nums) {
//		使用两趟扫描方法，重写数组
//		int i = 0, j = 0, z = 0;
//		for(int p = 0; p < nums.length; p++){
//			if(nums[p] == 0)
//				i++;
//			else if(nums[p] == 1)
//				j++;
//			else
//				z++;
//		}
//		int p = 0;
//		for(;p < p+i; p++)
//			nums[p] = 0;
//		for(;p < p+j; p++)
//			nums[p] = 1;
//		for(;p < p+z; p++)
//			nums[p] = 2;

//		使用常数空间，扫描一趟，双指针
		int p1 = 0, p2 = nums.length-1;
		int cur = 0;
		int temp;
		while(cur <= p2){
			//如果为0，则与p1交换
			if(nums[cur] == 0){
				temp = nums[cur];
				nums[cur++] = nums[p1];
				nums[p1++] = temp;
			}
			else if(nums[cur] == 2){
				temp = nums[cur];
				nums[cur] = nums[p2];
				nums[p2--] = temp;
			}
			else cur++;
		}
	}

	/**
	 * 77 组合
	 * @param n
	 * @param k
	 * @return
	 */
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
		if(k > n)
			return res;
		dg_77(0, res, new ArrayList<>(), n, k);
		return res;
	}
	public void dg_77(int index, List<List<Integer>> res, List<Integer> temp, int n, int k){
		if(temp.size() == k) {
			res.add(new ArrayList<>(temp));
			return;
		}
		for(int i = index; i < n; i++){
			temp.add(i + 1);
			dg_77(i + 1, res, temp, n, k);
			temp.remove(temp.size() - 1);
		}
	}

    /**
     * 78 子集，递归算法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
		dg_78(0, res, nums, new ArrayList<>());
		return res;
    }
    private void dg_78(int index, List<List<Integer>> res, int[] nums, ArrayList<Integer> temp){
		res.add(new ArrayList<>(temp));
		for(int i = index; i < nums.length; i++){
			temp.add(nums[i]);
			dg_78(i+1, res, nums, temp);
			temp.remove(temp.size()-1);
		}
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
	 * 84 柱状图中最大的矩形
	 * @param heights
	 * @return
	 */
	public int largestRectangleArea(int[] heights) {
		if(heights == null || heights.length == 0)
			return 0;
		int max = 0;
		for(int i = 0; i < heights.length; i++){
			int l = i-1, r = i+1;
			while(l>=0 && heights[l]>=heights[i]) l--;
			while(r<=heights.length-1 && heights[r]>=heights[i]) r++;
			max = max > (r-l)*heights[i] ? max : (r-l)*heights[i];
		}
		return max;
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
	 * 90 子集Ⅱ，78题剪枝
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		dg_90(0, res, nums, new ArrayList<>());
		return res;
	}
	private void dg_90(int index, List<List<Integer>> res, int[] nums, List<Integer> temp){
		res.add(new ArrayList<>(temp));
		for(int i = index; i < nums.length; i++){
			if(i-1>=index && nums[i] == nums[i-1]){
				continue;
			}
			temp.add(nums[i]);
			dg_90(i+1, res, nums, temp);
			temp.remove(temp.size()-1);
		}
	}

	/**
	 * 91 解码方法
	 * @param s
	 * @return
	 */
	public int numDecodings(String s) {
		if(s == null || s.charAt(0)=='0'){
			return 0;
		}
		int[] dp = new int[s.length()+1];
		dp[0] = 1;
		for(int i = 1; i < dp.length; i++){
			dp[i] = (s.charAt(i-1)=='0'?0:dp[i-1]);
			if(i >= 2 && (s.charAt(i-2) == '1' || (s.charAt(i-2) == '2' && s.charAt(i-1) <= '6'))){
				dp[i] += dp[i-2];
			}
		}
		return dp[dp.length-1];
	}

	/**
	 * 94 二叉树的中序遍历（递归）
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		h(root, result);
		return result;
	}
	public void h(TreeNode t, List<Integer> res){
		if(t != null){
			if(t.left != null){
				h(t.left, res);
			}
			res.add(t.val);
			if(t.right != null){
				h(t.right, res);
			}
		}
	}

	/**
	 * 94 二叉树的中序遍历（非递归）
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal_2(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode t = root;
		while(t != null || !stack.isEmpty()){
			if(t != null){
				stack.push(t);
				t = t.left;
			}
			else{
				t = stack.pop();
				result.add(t.val);
				t = t.right;
			}
		}
		return result;
	}

    /**
     * 98 验证二叉搜索树（二叉排序树）
     * 检查二叉树的中序遍历是否是升序
     * @param root
     * @return
     */
    //使用中序遍历，如果结果是递增的，则是二叉搜索树，否则不是
    public boolean isValidBST(TreeNode root) {
        if(root == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode t = root;
        long pre = -Long.MAX_VALUE;
        while(t != null || !stack.isEmpty()){
            if(t != null){
                stack.push(t);
                t = t.left;
            }
            else{
                t = stack.pop();
                if(pre < t.val)
                    pre = t.val;
                else
                    return false;
                t = t.right;
            }
        }
        return true;
    }

	/**
	 * 100 相同的树
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null){
			return true;
		}
		if(p != null && q != null && p.val == q.val){
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}
		else{
			return false;
		}
	}

	/**
	 * 101 对称二叉树
	 * 检查一个二叉树，检查是否是镜像对称
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
		if(root == null){
			return true;
		}
		return jg(root.left, root.right);
	}
	public boolean jg(TreeNode t1, TreeNode t2){
		if(t1 == null && t2 == null)
			return true;
		if(t1 == null || t2 == null)
			return false;
		if(t1.val == t2.val && jg(t1.left, t2.right) && jg(t1.right, t2.left)){
			return true;
		}
		return false;
	}

	/**
	 * 102 二叉树的层次遍历
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if(root == null)
			return result;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int count = queue.size();
			List<Integer> temp = new ArrayList<>();
			while(count-- != 0){
				TreeNode t = queue.peek();
				if(t.left != null){
					queue.offer(t.left);
				}
				if(t.right != null){
					queue.offer(t.right);
				}
				temp.add(t.val);
				queue.poll();
			}
			result.add(temp);
		}
		return result;
	}

	/**
	 * 103 二叉树的锯齿形层次遍历, 使用双端队列
	 * @param root
	 * @return
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if(root == null)
			return result;
		//Queue<TreeNode> queue = new LinkedList<>();
		Deque<TreeNode> deque = new ArrayDeque<>();
		deque.offerFirst(root);
		int flag = 1;
		while(!deque.isEmpty()){
			int count = deque.size();
			List<Integer> temp = new ArrayList<>();
			while(count -- != 0){
				if(flag == 1){
					TreeNode t = deque.peekFirst();
					if(t.left != null){
						deque.offerLast(t.left);
					}
					if(t.right != null){
						deque.offerLast(t.right);
					}
					temp.add(t.val);
					deque.pollFirst();
				}
				else{
					TreeNode t = deque.peekLast();
					if(t.right != null){
						deque.offerFirst(t.right);
					}
					if(t.left != null){
						deque.offerFirst(t.left);
					}
					temp.add(t.val);
					deque.pollLast();
				}
			}
			flag = -flag;
			result.add(temp);
		}
		return result;
	}

	/**
	 * 104 二叉树的最大深度
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		if(root == null)
			return 0;
		int rightdepth = maxDepth(root.right);
		int leftdepth = maxDepth(root.left);
		if(rightdepth > leftdepth){
			return rightdepth + 1;
		}
		else{
			return leftdepth + 1;
		}
	}

    /**
     * 105 从前序与中序遍历序列构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */
    int[] pre_105;
    int[] in_105;
    public TreeNode buildTree_105(int[] preorder, int[] inorder) {
        pre_105 = preorder;
        in_105 = inorder;
        return create_105(0, preorder.length-1, 0, preorder.length-1);
    }
    public TreeNode create_105(int postL, int postR, int inL, int inR){
        if(postL > postR)
            return null;
        TreeNode t = new TreeNode(pre_105[postL]);
        int pos = 0;
        for(pos = inL; pos <= inR; pos++){
            if(in_105[pos] == t.val)
                break;
        }
        t.left = create_105(postL+1, postL+pos-inL, inL, pos-1);
        t.right = create_105(postL+pos-inL+1, postR, pos+1, inR);
        return t;
    }

	/**
	 * 106 从中序与后序遍历序列构造二叉树
	 * @param inorder
	 * @param postorder
	 * @return
	 */
	int[] in_169;
	int[] post_168;
	public TreeNode buildTree(int[] inorder, int[] postorder) {
        in_169 = inorder;
        post_168 = postorder;
        return createNode(0, inorder.length-1, 0, inorder.length-1);
	}
	public TreeNode createNode(int postL, int postR, int inL, int inR){
        if(postL > postR){
            return null;
        }
        TreeNode t = new TreeNode(post_168[postR]);
        int pos = 0;
        for(pos = inL; pos <= inR; pos++){
            if(in_169[pos] == t.val)
                break;
        }
        t.left = createNode(postL, postL+pos-inL-1, inL, pos-1);
        t.right = createNode(postL+pos-inL, postR-1, pos+1, inR);
        return t;
    }

	/**
	 * 107 二叉树的层次遍历Ⅱ
	 * 添加一个栈来将102题的结果倒置
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if(root == null)
			return result;
		Queue<TreeNode> queue = new LinkedList<>();
		Stack<List<Integer>> stack = new Stack<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int s = queue.size();
			List<Integer> temp = new ArrayList<>();
			while(s-- != 0){
				TreeNode t = queue.peek();
				if(t.left != null){
					queue.offer(t.left);
				}
				if(t.right != null){
					queue.offer(t.right);
				}
				temp.add(t.val);
				queue.poll();
			}
			stack.push(temp);
		}
		while(!stack.empty()){
			result.add(stack.peek());
			stack.pop();
		}
		return result;
	}

	/**
	 * 108 将有序数组转化为二叉搜索树（高度平衡二叉树）
	 * @param nums
	 * @return
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		return helper(nums, 0, nums.length - 1);
	}
	public TreeNode helper(int[] nums, int left, int right){
		if(left > right) return null;
		int p = (left + right) / 2;
		TreeNode root = new TreeNode(nums[p]);
		root.left = helper(nums, left, p-1);
		root.right = helper(nums, p +1 ,right);
		return root;
	}

	/**
	 * 110 平衡二叉树
	 * 给定一个二叉树，判断是否是平衡二叉树
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		if(root == null){
			return true;
		}
		int k = Math.abs(getd(root.left) - getd(root.right));
		if(k <= 1 && isBalanced(root.left) && isBalanced(root.right)){
			return true;
		}
		else{
			return false;
		}
	}
	public int getd(TreeNode t){
		if(t == null)
			return 0;
		int ld = getd(t.left);
		int rd = getd(t.right);
		if(ld > rd)
			return ld + 1;
		else
			return rd + 1;
	}

	/**
	 * 111 二叉树的最小深度
	 * 层次遍历
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
		if(root == null)
			return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		int len = 0;
		while(!queue.isEmpty()){
			int count = queue.size();
			len++;
			while(count-- != 0){
				TreeNode t = queue.peek();
				if(t.left == null && t.right == null)
					return len;
				if(t.left != null)
					queue.offer(t.left);
				if(t.right != null)
					queue.offer(t.right);
				queue.poll();
			}
		}
		return len;
	}

	/**
	 * 112 路径总和
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null)
			return false;
		sum = sum - root.val;
		if(root.left == null && root.right == null)
			return (sum == 0);
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}

    /**
     * 113 路径总和Ⅱ
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
	 * 114 二叉树转化为链表
	 * 使用后序遍历
	 * @param root
	 */
	public void flatten(TreeNode root) {
		if(root == null)
			return;
		if(root != null){
			flatten(root.right);
			flatten(root.left);
			TreeNode t = root.right;
			root.right = root.left;
			root.left = null;
			while(root.right != null){
				root = root.right;
			}
			root.right = t;

		}
	}

	/**
	 * 118 杨辉三角
	 * @param numRows
	 * @return
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<>();
		if(numRows == 0)
			return result;
		for(int i = 0; i < numRows; i++){
			if(i == 0){
				List<Integer> t = new ArrayList<>();
				t.add(1);
				result.add(t);
			}
			else if(i == 1){
				List<Integer> t = new ArrayList<>();
				t.add(1);
				t.add(1);
				result.add(t);
			}
			else{
				List<Integer> t = new ArrayList<>();
				t.add(1);
				for(int j = 1; j <= i; j++){
					if(j == i){
						t.add(1);
					}
					else{
						t.add(result.get(i-1).get(j-1)+result.get(i-1).get(j));
					}
				}
				result.add(t);
			}
		}
		return result;
	}

	/**
	 * 118 杨辉三角（方法2）
	 * @param numRows
	 * @return
	 */
	public List<List<Integer>> generate1(int numRows) {
		List<List<Integer>> res = new ArrayList<>();
		if(numRows == 0)
			return res;
		res.add(new ArrayList<>());
		res.get(0).add(1);
		for(int i = 1; i < numRows; i++){
			List<Integer> pre = res.get(i-1);
			List<Integer> newRow = new ArrayList<>();

			newRow.add(1);
			for(int j = 1; j < i; j++){
				newRow.add(pre.get(j-1)+pre.get(j));
			}
			newRow.add(1);
			res.add(newRow);

		}
		return res;
	}

	/**
	 * 119 杨辉三角2 空间复杂度优化到O(k)
	 * @param rowIndex
	 * @return
	 */
	public List<Integer> getRow(int rowIndex) {
		List<Integer> res = new ArrayList<>();
		res.add(1);
		if(rowIndex == 0)
			return res;
		int pre_n = 0, n = 1;
		for(int i = 1; i <= rowIndex; i++){
			for(int j = 1; j <= i; j++){
				if(j == i){
					res.add(1);
					break;
				}
				pre_n = n;
				n = res.get(j);
				res.set(j, pre_n+n);
			}

		}
		return res;
	}

	/**
	 * 120 三角形最小路径和
	 * dp动态规划（自顶向下）
	 * @param triangle
	 * @return
	 */
	public int minimumTotal(List<List<Integer>> triangle) {
		if(triangle == null || triangle.size() == 0)
			return 0;
		int[][] dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
		dp[0][0] = triangle.get(0).get(0);
		if(triangle.size() == 1)
			return dp[0][0];
		dp[1][0] = dp[0][0] + triangle.get(1).get(0);
		dp[1][1] = dp[0][0] + triangle.get(1).get(1);
		for(int i = 2; i < triangle.size(); i++){
			for(int j = 0; j < triangle.get(i).size(); j++){
				//最左侧
				if(j == 0){
					dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
				}
				//最右侧
				else if(j == triangle.get(i).size()-1){
					dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
				}
				else{
					dp[i][j] = Math.min(dp[i-1][j-1]+triangle.get(i).get(j), dp[i-1][j]+triangle.get(i).get(j));
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < dp[dp.length-1].length; i++){
			min = Math.min(min, dp[dp.length-1][i]);
		}
		return min;
	}

	/**
	 * 120 三角形最小路径和
	 * dp动态规划（自底向上）
	 * @param triangle
	 * @return
	 */
	public int minimumTotal_1(List<List<Integer>> triangle) {
		if(triangle == null || triangle.size() == 0)
			return 0;
		for(int i = triangle.size()-2; i >= 0; i--){
			for(int j = 0; j < triangle.get(i).size(); j++){
				int temp = triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
				triangle.get(i).set(j, temp);
			}
		}
		return triangle.get(0).get(0);
	}

	/**
	 * 121 买卖股票的最佳时机
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int minprice = Integer.MAX_VALUE;
		int maxprofit = 0;
		for(int i = 0; i < prices.length; i++){
			if(minprice > prices[i])
				minprice = prices[i];
			else if(prices[i] - minprice > maxprofit){
				maxprofit = prices[i] - minprice;
			}
		}
		return maxprofit;
	}

	/**
	 * 122 买卖股票的最佳时机Ⅱ
	 * 计算所有的上升线段
	 * @param prices
	 * @return
	 */
	public int maxProfit_2(int[] prices) {
		int now = 0;
		int maxprofit = 0;
		for(int i = 1; i < prices.length; i++){
			if(prices[i] < prices[i-1])
			{
				maxprofit += prices[i-1]-prices[now];
				now = i;
			}
			else if(i == prices.length-1)
				maxprofit += prices[i]-prices[now];

		}
		return maxprofit;
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
	 * 131 分割回文串
	 * @param s
	 * @return
	 */
//	public List<List<String>> partition(String s) {
//
//	}

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
	 * 139 单词拆分，
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public boolean wordBreak(String s, List<String> wordDict) {
		return word_Break(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
	}
	public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] panduan){
		if(start == s.length())
			return true;
		if(panduan[start] != null) {
			return panduan[start];
		}
		for(int end = start + 1; end <= s.length(); end++){
			if(wordDict.contains(s.substring(start, end)) && word_Break(s, wordDict, end, panduan)){
				return panduan[start] = true;
			}
		}
		return panduan[start] = false;
	}

	/**
	 * 141 环形链表
	 * 使用快慢指针可以使空间复杂度为O(1)，快指针每次走两步，慢指针每次走一步，当快指针追上慢指针时，说明有环路
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
		if(head == null)
			return false;
		ListNode p1 = head.next; //快指针
		ListNode p2 = head; //慢指针
		while(p1 != null && p1.next != null){
			if(p1 == p2)
				return true;
			p2 = p2.next;
			p1 = p1.next.next;
		}
		return false;
	}

	/**
	 * 144 二叉树的前序遍历（递归）
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		t144(res, root);
		return res;
	}
	public void t144(List<Integer> res, TreeNode t){
		if(t != null){
			res.add(t.val);
			t144(res, t.left);
			t144(res, t.right);
		}
	}

	/**
	 * 144 二叉树的前序遍历（非递归）
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal_2(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		List<Integer> res = new ArrayList<>();
		TreeNode t = root;
		while(t != null || !stack.isEmpty()){
			if(t != null){
				stack.push(t);
				res.add(t.val);
				t = t.left;
			}
			else{
				t = stack.peek();
				stack.pop();
				t = t.right;
			}
		}
		return res;
	}

    /**
     * 145 二叉树的后续遍历（递归）
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        ad(res, root);
        return res;
    }
    public void ad(List<Integer> res, TreeNode t){
        if(t != null){
            ad(res, t.left);
            ad(res, t.right);
            res.add(t.val);
        }
    }

	/**
	 * 145 二叉树的后序遍历（非递归）
	 * @param root
	 * @return
	 */
	public List<Integer> postorderTraversal_2(TreeNode root) {
		//一个栈
//		LinkedList<TreeNode> stack = new LinkedList<>();
//		LinkedList<Integer> res = new LinkedList<>();
//		if(root == null)
//		    return res;
//		stack.add(root);
//		while(!stack.isEmpty()){
//		    TreeNode t = stack.pollLast();
//		    res.addFirst(t.val);
//		    if(t.left != null){
//		        stack.add(t.left);
//            }
//		    if(t.right != null){
//		        stack.add(t.right);
//            }
//        }
//        return res;

		//两个栈
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		List<Integer> res = new ArrayList<>();
		if(root == null)
			return res;
		stack1.push(root);
		while(!stack1.isEmpty()){
			TreeNode temp = stack1.pop();
			stack2.push(temp);
			if(temp.left != null)
				stack1.push(temp.left);
			if(temp.right != null)
				stack1.push(temp.right);
		}
		while(!stack2.isEmpty()){
			res.add(stack2.pop().val);
		}
		return res;
	}

	/**
	 * 152 乘积最大子序列
	 * @param nums
	 * @return
	 */
	public int maxProduct(int[] nums) {
//		if(nums.length == 0)
//			return 0;
//		if(nums.length == 1)
//			return nums[0];
//		int max = nums[0];
//		int result = -0x3f3f3f3f;
//		for(int i = 1; i < nums.length; i++){
//			if(max * nums[i] > max){
//				max = max * nums[i];
//			}
//			else{
//				max = nums[i];
//			}
//			result = Math.max(result, max);
//		}
//		return result;

		//因为一个绝对值很大的负数乘上一个负数（整数）是会变成很大的正数，所以要计算出数组中的最大乘积和最小乘积
		int[] p = new int[nums.length];
		int[] n = new int[nums.length];
		p[0] = nums[0];
		n[0] = nums[0];
		int result = nums[0];
		for(int i = 1; i < nums.length; i++){
			p[i] = Math.max(nums[i], Math.max(p[i-1]*nums[i], n[i-1]*nums[i]));
			n[i] = Math.min(nums[i], Math.min(p[i-1]*nums[i], n[i-1]*nums[i]));
			result = Math.max(result, p[i]);
		}
		return result;
	}

	/**
	 * 153 寻找旋转排序数组中的最小值
     * 无重复数
	 * @param nums
	 * @return
	 */
	public int findMin(int[] nums) {
		int left = 0, right = nums.length-1, mid = 0;
		while(left < right){
			mid = left + (right-left)/2;
			if(nums[mid] < nums[right])
				right = mid;
			else
				left = mid+1;
		}
		return nums[left];
	}

    /**
     * 154 寻找旋转排序数组中的最小值2
     * 有重复数
     * @param nums
     * @return
     */
    public int findMin_2(int[] nums) {
        int left = 0, right = nums.length-1, mid = 0;
        while(left < right){
            mid = left + (right-left)/2;
            if(nums[mid] < nums[right])
                right = mid;
            else if(nums[mid] > nums[right])
                left = mid+1;
            else
                right -= 1;
        }
        return nums[left];
    }

	/**
	 * 155 最小栈
	 * 维护一个最小值min，每次push的时候比较元素是否比min小，若小则将min更新，每次push都push两次，多push的一次就是将当前的
	 * min添加到栈中，每次出栈都出两次
	 */
	/** initialize your data structure here. */
	List<Integer> list;
	int min;
	public void MinStack() {
		list = new ArrayList<>();
		min = Integer.MAX_VALUE;
	}

	public void push(int x) {
		if(list.size() == 0){
			min = Integer.MAX_VALUE;
		}
		else{
			min = list.get(list.size()-1);
		}
		list.add(x);
		if(min > x){
			min = x;
		}
		list.add(min);
	}

	public void pop() {
		list.remove(list.size()-1);
		list.remove(list.size()-1);
	}

	public int top() {
		return list.get(list.size()-2);
	}

	public int getMin() {
		return list.get(list.size()-1);
	}

	/**
	 * 160 相交链表
	 *
	 * @param headA
	 * @param headB
	 * @return
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if(headA == null || headB == null)
			return null;
		ListNode n1 = headA;
		ListNode n2 = headB;
		while(n1 != n2){
			n1 = n1.next;
			n2 = n2.next;
			if(n1 == null && n2 == null)
				return null;
			if(n1 == null)
				n1 = headB;
			if(n2 == null)
				n2 = headA;
		}
		return n1;
	}

	/**
	 * 162 寻找峰值(二分）
	 * @param nums
	 * @return
	 */
	public int findPeakElement(int[] nums) {
		int left = 0, right = nums.length-1, mid = 0;
		while(left < right){
			mid = left+(right-left)/2;
			if(nums[mid] > nums[mid+1])
				right = mid;
			else
				left = mid+1;
		}
		return left;
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
	 * 172 阶乘后的0
	 * 解析：归根结底，产生0是由2*5得来的，4*20=（2*2）*（2*2*5），要计算0的树就要计算2*5的个数，而2的数量肯定大于5的数量，
	 * 问题也就转化为了求5的个数。
	 * @param n
	 * @return
	 */
	public int trailingZeroes(int n) {
		int count = 0;
		while(n >= 5){
			count = count += n/5;
			n = n / 5;
		}
		return count;
	}

	/**
	 * 173 二叉搜索树迭代器
     * next将返回二叉搜索树中的下一个最小的数
	 * @param root
	 */
	int index_173  = 0; //记录当前节点索引
    List<Integer> list_173 = new ArrayList<>(); //保存二叉搜索树的中序遍历
	public void BSTIterator(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode t = root;
        while(t!=null || !s.isEmpty()){
            if(t != null){
                s.add(t);
                t = t.left;
            }
            else{
                t = s.pop();
                list_173.add(t.val);
                t = t.right;
            }
        }
	}

	/** @return the next smallest number */
	public int next() {
        return list_173.get(index_173++);
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
        if(index_173 >= list_173.size())
            return false;
        return true;
	}

	/**
	 * 181 超过经理收入的员工
	 */
	//select e.Name Employee from Employee e where e.Salary > (select Salary from Employee b where b.Id = e.ManagerId)

	/**
	 * 182 查找重复的电子邮箱
	 */
	//select Email from Person group by Email having count(*)>1

	/**
	 * 183 从不订购的客户
	 */
	/*
	# Write your MySQL query statement below
	select Name Customers from Customers c where c.Id not in(select o.CustomerId from Orders o)
	 */

	/**
	 * 184 部门工资最高的员工
	 */
	/*
	# Write your MySQL query statement below
	select d.name Department,e.name Employee,e.Salary
	from Employee e, Department d
	where e.DepartmentId = d.Id
	and (e.Salary, e.DepartmentId) in (select max(Salary), DepartmentId from Employee group by DepartmentId)
	 */

    /**
     * 189 旋转数组
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        if(nums.length == 1 || nums.length == 0)
            return;
        k = k % nums.length;
        for(int i = 0, j = nums.length-1; i < j; i++, j--){
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        for(int i = 0, j = k-1; i < j; i++, j--){
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        for(int i = k, j = nums.length-1; i < j; i++, j--){
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
    }

	/**
	 * 191 位1的个数
	 * @param n
	 * @return
	 */
	public int hammingWeight(int n) {
		int res = 0;
		int mask = 1;
		for(int i = 0; i < 32; i++){
			if((n & mask) != 0)
				res++;
			mask<<=1;
		}
		return res;
	}

	/**
	 * 198 打家劫舍
	 * 动态规划
	 * @param nums
	 * @return
	 */
	public int rob(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		int[] dp = new int[nums.length];
		dp[0] = nums[0];

		if(nums.length > 1){
			dp[1] = Math.max(nums[0], nums[1]);
		}
		for(int i = 2; i < nums.length; i++){
			dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
		}
		return dp[dp.length-1];
	}

	/**
	 * 199 二叉树的右视图
	 * @param root
	 * @return
	 */
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if(root == null)
			return res;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()){
			int len = queue.size();
			for(int i = 0; i < len; i++) {
				TreeNode node = queue.poll();
				if(i == len-1)
					res.add(node.val);
				if(node.left != null) queue.add(node.left);
				if(node.right != null) queue.add(node.right);
			}
		}
		return res;
	}

	/**
	 * 200 岛屿数量， dfs
	 * @param grid
	 * @return
	 */
	public int numIslands(char[][] grid) {
		int res = 0;
		if(grid == null || grid.length == 0)
			return res;
		int m = grid.length;
		int n = grid[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(grid[i][j] == '1'){
					res++;
					dfs_200(grid, i, j);
				}
			}
		}
		return res;
	}
	public void dfs_200(char[][] grid, int i, int j){
		grid[i][j] = '0';
		if(i + 1 < grid.length && grid[i+1][j] == '1') dfs_200(grid, i+1, j);
		if(i - 1 >= 0 && grid[i-1][j] == '1') dfs_200(grid, i-1, j);
		if(j + 1 < grid[0].length && grid[i][j+1] == '1') dfs_200(grid, i, j + 1);
		if(j - 1 >= 0 && grid[i][j-1] == '1') dfs_200(grid, i, j - 1);
	}
	/**
	 * 204 计算质数，厄拉多赛筛法求素数
	 * @param n
	 * @return
	 */
	public int countPrimes(int n) {
		int[] flag = new int[n];
		for(int i = 0; i < n; i++){
			flag[i] = 1;
		}
		int result = 0;
		for(int i = 2; i < n; i++){
			//如果当前值为素数
			if(flag[i] == 1){
				for(int j = 2; j*i < n; j++){
					flag[j*i] = 0;
				}
			}
		}
		for(int i = 2; i < n; i++){
			if(flag[i] == 1)
				result++;
		}
		return result;
	}

    /**
     * 206 (easy) 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

	/**
	 * 213 打家劫舍Ⅱ
	 * 和198不同的是，房屋是围成一圈的
	 * 分两个dp，一个从0偷到n-1，另一个从1偷到n
	 * @param nums
	 * @return
	 */
	public int rob2(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		if(nums.length == 1)
			return nums[0];
		int[] dp1 = new int[nums.length];
		int[] dp2 = new int[nums.length];
		dp1[1] = nums[0];
		dp2[1] = nums[1];
		for(int i = 2; i < nums.length; i++){
			dp1[i] = Math.max(dp1[i-2]+nums[i-1], dp1[i-1]);
			dp2[i] = Math.max(dp2[i-2]+nums[i], dp2[i-1]);
		}
		return Math.max(dp1[nums.length-1], dp2[nums.length-1]);
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
	 * 217 存在重复元素
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < nums.length; i++){
			if(!map.containsKey(nums[i])){
				map.put(nums[i], 1);
			}
			else{
				map.put(nums[i], map.get(nums[i])+1);
			}
		}
		for(int a : map.keySet()){
			if(map.get(a) > 1)
				return true;
		}
		return false;
	}

	/**
	 * 221 最大的正方形
	 * 给一个矩阵，数据有0和1，找出1组成的最大的正方形，并返回其面积
	 * 解法：动态规划。首先初始化一个和给出矩阵一样大的二维数组dp，初始化为0，dp[i][j]存储以(i, j)为右下角节点的正方形的边长。
	 * dp[i][j] = dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
	 * @param matrix
	 * @return
	 */
	public int maximalSquare(char[][] matrix) {
		if(matrix == null || matrix.length == 0)
			return 0;
		int[][] dp = new int[matrix.length][matrix[0].length];
		int maxlength = 0;
		//首先初始化第一行和第一列
		for(int i = 0; i < matrix[0].length; i++){
			dp[0][i] = matrix[0][i] - '0';
			maxlength = maxlength < dp[0][i] ? dp[0][i] : maxlength;
		}
		for(int i = 0; i < matrix.length; i++){
			dp[i][0] = matrix[i][0] - '0';
			maxlength = maxlength < dp[i][0] ? dp[i][0] : maxlength;
		}
		for(int i = 1; i < matrix.length; i++){
			for(int j = 1; j < matrix[0].length; j++){
				if(matrix[i][j] == '1'){
					dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
					maxlength = maxlength < dp[i][j] ? dp[i][j] : maxlength;
				}
			}
		}
		return maxlength * maxlength;
	}

	/**
	 * 223 矩形重叠的面积
	 * (A,B) (C,D) (E,F) (G,H)为给出的四个点，计算重叠面积
	 * 解析：首先判断是否有重叠，有的话直接返回两个矩形的面积之和，否则返回两个矩形的面积之和再减去重叠部分面积。
	 * @param A
	 * @param B
	 * @param C
	 * @param D
	 * @param E
	 * @param F
	 * @param G
	 * @param H
	 * @return
	 */
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		//如果不重叠
		if(A >= G || E >= C || B >= H || F >= D){
			return (C-A)*(D-B) + (G-E)*(H-F);
		}
		int l1 = C-A;
		int w1 = D-B;
		int l2 = G-E;
		int w2 = H-F;

		//重叠的四个点
		int left = E > A ? E : A;
		int right = C < G ? C : G;
		int top = H < D ? H : D;
		int down = B > F ? B : F;
		return l1*w1 + l2*w2 - (right-left)*(top-down);
	}

	/**
	 * 226 翻转二叉树
	 * @param root
	 * @return
	 */
	public TreeNode invertTree(TreeNode root) {
		if(root == null)
			return root;
		TreeNode temp = root.left;
		root.left = invertTree(root.right);
		root.right = invertTree(temp);

		return root;
	}

    /**
     * 231 2 的幂
     * 检查n是否是2的幂
     * 用位运算，n & (n-1) == 0 的话，n就是2的幂，因为2的幂的二进制形式中只有一个1.
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        if(n == 0)
            return false;
        long x = (long)n;
        return (x & (x-1)) == 0;
    }

	/**
	 * 233 数字1的个数
	 * @param n
	 * @return
	 */
	public int countDigitOne(int n) {
		int res = 0;
		for(long i = 1; i <= n; i *= 10){
			res += (n / (i*10)) * i + Math.min(Math.max(n%(i*10)-i+1, 0), i);
		}
		return res;
	}

	/**
	 * 235 二叉搜索树的最近公共祖先
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestors(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null)
			return root;
		if(root.val > p.val && root.val > q.val)
			return lowestCommonAncestors(root.left, p, q);
		if(root.val < p.val && root.val < q.val)
			return lowestCommonAncestors(root.right, p, q);
		return root;
	}

	/**
	 * 236 二叉树的最近的公共祖先
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
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if(left != null && right != null){
			return root;
		}
		if(left != null){
			return left;
		}
		if(right != null){
			return right;
		}
		return null;
	}

	/**
	 * 237 删除链表中的节点
	 * 只给出要删除的那个节点
	 * @param node
	 */
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}

	/**
	 * 238 除自身以外数组的乘积
	 * 要求为复杂度为O(n)，利用两个数组L和R，L[i]保留nums[i]的前面的乘积，R[i]保留nums[i]之后的乘积
	 * @param nums
	 * @return
	 */
	public int[] productExceptSelf(int[] nums) {
		if(nums.length == 1)
			return nums;
		int[] res = new int[nums.length];
		int[] L = new int[nums.length];
		int[] R = new int[nums.length];
		L[0] = 1;
		R[nums.length - 1] = 1;
		for(int i = 1; i < nums.length; i++){
			L[i] = L[i-1] * nums[i-1];
		}
		for(int i = nums.length-2; i >= 0; i--){
			R[i] = R[i+1] * nums[i+1];
		}
		for(int i = 0; i < nums.length; i++){
			res[i] = L[i] * R[i];
		}
		return res;
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
	 * 242 有效的字母异位词
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isAnagram(String s, String t) {
		Map<Character, Integer> map1 = new HashMap<>();
		Map<Character, Integer> map2 = new HashMap<>();
		for(int i = 0; i < s.length(); i++){
			if(!map1.containsKey(s.charAt(i))){
				map1.put(s.charAt(i), 1);
			}
			else{
				map1.put(s.charAt(i), map1.get(s.charAt(i))+1);
			}
		}
		for(int i = 0; i < t.length(); i++){
			if(!map2.containsKey(t.charAt(i))){
				map2.put(t.charAt(i), 1);
			}
			else{
				map2.put(t.charAt(i), map2.get(t.charAt(i))+1);
			}
		}
//		if(map1.size() != map2.size())
//			return false;
		for(Character c : map1.keySet()){
			if(!map2.containsKey(c))
				return false;
			if(!map1.get(c).equals(map2.get(c)))
				return false;
		}
		return true;
	}

	/**
	 * 257 二叉树的所有路径
	 * @param root
	 * @return
	 */
	public List<String> binaryTreePaths(TreeNode root) {
			List<String> res = new ArrayList<>();
			bianli(res, root, "");
		return res;
	}
	public void bianli(List<String> res, TreeNode t, String s){
		if(t == null){
			return;
		}
		String str = "";
		if(s == ""){
			str = String.valueOf(t.val);
		}
		else{
			str = s + "->" + String.valueOf(t.val);
		}
		if(t.left == null && t.right == null){
			res.add(str);
		}
		bianli(res, t.left, str);
		bianli(res, t.right, str);
	}

	/**
	 * 278 第一个错误的版本(二分法)
	 * @param n
	 * @return
	 */
	public int firstBadVersion(int n) {
		int left = 1, right = n, mid = 0;
		while(left <= right){
			mid = left + (right - left)/2;
			if(isBadVersion(mid)) {
				if(mid == 1 || !isBadVersion(mid-1))
					return mid;
				right = mid-1;
			}
			else
				left = mid+1;
		}
		return -1;
	}

	/**
	 * 283 移动0，将所有的0放到后面且不影响其他的
	 * @param nums
	 */
	public void moveZeroes(int[] nums) {
//			方法1 ：o(n^2)复杂度，仿照冒泡排序
//		for(int i = 0; i < nums.length-1; i++) {
//			for (int j = 0; j < nums.length - 1 - i; j++) {
//				if (nums[j] == 0) {
//					int temp = nums[j];
//					nums[j] = nums[j + 1];
//					nums[j + 1] = temp;
//				}
//			}
//		}
//			方法2 双指针，O(n)
			int i = 0, j = 0;
			while(j < nums.length){
				if(nums[j] != 0){
					nums[i] = nums[j];
					i++;
				}
				j++;
			}
			for(; i < nums.length; i++){
				nums[i] = 0;
			}
	}

	/**
	 * 287 寻找重复数
	 * @param nums
	 * @return
	 */
	public int findDuplicate(int[] nums) {
		int[] arr = new int[nums.length];
		int res = 0;
		for(int i = 0; i < nums.length; i++) {
			if(arr[nums[i]-1] == 0)
				arr[nums[i]-1] = nums[i];
			else{
				res = nums[i];
				break;
			}
		}
		return res;
	}

	/**
	 * 292 Nim游戏
	 * @param n
	 * @return
	 */
	public boolean canWinNim(int n) {
		return (n%4 != 0);
	}

	/**
	 * 295 数据流的中位数
	 */
	/** initialize your data structure here. */
	ListNode listNode;
	public void MedianFinder() {
	}

	public void addNum(int num) {

	}
//
//	public double findMedian() {
//
//	}

	/**
	 * 300 最长上升子序列LIS
	 * @param nums
	 * @return
	 */
	public int lengthOfLIS(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		int[] f = new int[nums.length];//用于记录该位置最长上升子序列的长度
		for(int i = 0; i < nums.length; i++)
			f[i] = 1;
		int res = f[0];
		for(int i = 1; i < nums.length; i++){
			for(int j = 0; j < i; j++){
				if(nums[i] > nums[j]){
					f[i] = Math.max(f[i], f[j]+1);
				}
			}
			res = Math.max(res, f[i]);
		}
		return res;
	}

	/**
	 * 303 区域和检索 - 数组不可变
	 */
	int[] dp_303 = null;
	public NumArray_303(int[] nums) {
		if(nums.length == 0)
			return;
		dp_303 = new int[nums.length];
		dp_303[0] = nums[0];
		for(int i = 1; i < nums.length; i++){
			dp_303[i] = nums[i] + dp_303[i-1];
		}
	}
	public int sumRange_303(int i, int j) {
		if(i == 0)
			return dp_303[j];
		return dp_303[j] - dp_303[i-1];
	}

	/**
	 * 304 二维区域和检测 - 矩阵不可变
	 */
	int[][] dp_304 = null;
	public NumMatrix_304(int[][] matrix) {
		if(matrix == null || matrix.length == 0)
			return;
		dp_304 = new int[matrix.length][matrix[0].length];
		dp_304[0][0] = matrix[0][0];
		for(int i = 1; i < matrix.length; i++)
			dp_304[i][0] = matrix[i][0] + dp_304[i-1][0];
		for(int i = 1; i < matrix[0].length; i++)
			dp_304[0][i] = matrix[0][i] + dp_304[0][i-1];
		for(int i = 1; i < matrix.length; i++){
			for(int j = 1; j < matrix[0].length; j++){
				dp_304[i][j] = matrix[i][j] + dp_304[i][j-1] + dp_304[i-1][j] - dp_304[i-1][j-1];
			}
		}
	}

	public int sumRegion_304(int row1, int col1, int row2, int col2) {
		int sum = dp_304[row2][col2];
		if(col1 != 0)
			sum -= dp_304[row2][col1-1];
		if(row1 != 0)
			sum -= dp_304[row1-1][col2];
		if(row1 != 0 && col1 != 0)
			sum += dp_304[row1-1][col1-1];
		return sum;
	}

	/**
	 * 322 零钱兑换（动态规划）
	 * @param coins
	 * @param amount
	 * @return
	 */
	public int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount+1];
		for(int i = 0; i < amount+1; i++)
			dp[i] = amount + 1;
		dp[0] = 0;
		for(int i = 0; i < dp.length; i++){
			for(int coin : coins){
				if(i - coin < 0)
					continue;
				dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
			}
		}
		return dp[amount] == amount+1 ? -1 : dp[amount];
	}

	/**
	 * 334 递增的三元子序列
	 * 分析：初始化a、b为无穷大，然后遍历nums数组，首先判断nums[i]是否小于a，如果小于a，则a=nums[i]，否则与b比较，如果比b小，则
	 * b=nums[i]，否则返回true。b若被赋值，说明在b这个值之前肯定有比b小的数。当nums[i]小于被赋值的b时，就说明存在递增的三元子序列
	 * @param nums
	 * @return
	 */
	public boolean increasingTriplet(int[] nums) {
		int len = nums.length;
		if(len < 3)
			return false;
		int a = Integer.MAX_VALUE;
		int b = Integer.MAX_VALUE;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] <= b){
				b = nums[i];
			}
			else if(nums[i]	<= a){
				a = nums[i];
			}
			else
				return true;
		}
		return false;
	}

	/**
	 * 343 整数拆分
	 * @param n
	 * @return
	 */
	public int integerBreak(int n) {
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
	 * 344 反转字符串，原地修改数组
	 * @param s
	 */
	public void reverseString(char[] s) {
		if(s == null || s.length == 0)
			return;
		int left = 0, right = s.length - 1;
		while(left < right){
			char temp = s[left];
			s[left] = s[right];
			s[right] = temp;
			left++;
			right--;
		}
	}

	/**
	 * 349 两个数组的交集
	 * 输出结果中的每个元素一定是唯一的
	 * 可以不考虑输出结果的顺序
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		List<Integer> t = new ArrayList<>();
		List<Integer> g = new ArrayList<>();
		for(int i = 0; i < nums1.length; i++){
			t.add(nums1[i]);
		}
		for(int i = 0; i < nums2.length; i++){
			if(t.contains(nums2[i]) && !g.contains(nums2[i])){
				g.add(nums2[i]);
			}
		}
		int[] r = new int[g.size()];
		for(int i = 0; i < g.size(); i++){
			r[i] = g.get(i);
		}
		return r;
	}

	/**
	 * 350 两个数组的交集Ⅱ
	 * 输出结果中每个元素出现的次数，应该与元素在两个数组中出现的次数一致
	 * 可以不考虑输出结果的顺序
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < nums1.length; i++){
			if(!map.containsKey(nums1[i])){
				map.put(nums1[i], 1);
			}
			else{
				map.put(nums1[i], map.get(nums1[i])+1);
			}
		}
		for(int i = 0; i < nums2.length; i++){
			if(map.containsKey(nums2[i])){
				list.add(nums2[i]);
				if(map.get(nums2[i])-1 == 0){
					map.remove(nums2[i]);
				}
				else{
					map.put(nums2[i], map.get(nums2[i])-1);
				}
			}
		}
		int[] r = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			r[i] = list.get(i);
		}
		return r;
	}

	/**
	 * 365 水壶问题
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public boolean canMeasureWater(int x, int y, int z) {
		if(x + y < z)
			return false;
		if(x == 0 || y == 0)
			return z == 0 || x + y == z;
		return z % gcd(x, y) == 0;
	}

	/**
	 * 374 猜数字大小
	 * @param n
	 * @return
	 */
	public int guessNumber(int n) {
		int left = 0, right = n, mid = 0;
		while(left <= right){
			mid = left + (right - left)/2;
			if(guess(mid) == 0)
				return mid;
			else if(guess(mid) == -1)
				right = mid - 1;
			else
				left = mid + 1;
		}
		return 0;
	}

	/**
	 * 384 打乱数组（洗牌算法）
	 * @param nums
	 */
	int[] nums = null;
	int[] nums_old = null;
	public void Solution(int[] nums) {
		this.nums = nums;
		this.nums_old = Arrays.copyOf(nums, nums.length);
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return this.nums_old;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int len = this.nums.length;
		Random random = new Random();
		for(int i = len-1; i >= 1; i--){
			int s = random.nextInt(i+1);
			int temp = nums[i];
			nums[i] = nums[s];
			nums[s] = temp;
		}
		return nums;
	}

	/**
	 * 387 字符串中第一个唯一字符
	 * @param s
	 * @return
	 */
	public int firstUniqChar(String s) {
		int result = -1;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++){
			if(!map.containsKey(s.charAt(i))){
				map.put(s.charAt(i), 1);
			}
			else{
				map.put(s.charAt(i), map.get(s.charAt(i))+1);
			}
		}
		for(int i = 0; i < s.length(); i++){
			if(map.get(s.charAt(i)) == 1)
				return i;
		}
		return result;
	}

	/**
	 * 392 判断子序列
	 * 判断s是否是t的子序列
	 * s为abc，t为akgbdc，则返回true
	 * 双指针
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isSubsequence(String s, String t) {
		int i = 0;
		int j = 0;
		while(i < s.length() && j < t.length()){
			if(s.charAt(i) == t.charAt(j)){
				i++;
				j++;
			}
			else
				j++;
		}
		if(i == s.length())
			return true;
		return false;
	}

	/**
	 * 404 左叶子之和
	 * @param root
	 * @return
	 */
	int res_404 = 0;
	public int sumOfLeftLeaves(TreeNode root) {
		if(root == null)
			return 0;
		if(root.left != null){
			if(root.left.left == null && root.left.right == null){
				res_404 += root.left.val;
			}
			else
				sumOfLeftLeaves(root.left);
		}
		if(root.right != null)
			sumOfLeftLeaves(root.right);
		return res_404;
	}

	/**
	 * 409 最长回文串
	 * @param s
	 * @return
	 */
	public int longestPalindrome_409(String s) {
		Set<Character> set = new HashSet<>();
		int len = 0;
		for(char ch : s.toCharArray()){
			if(!set.contains(ch))
				set.add(ch);
			else{
				set.remove(ch);
				len += 2;
			}
		}
		if(!set.isEmpty())
			len++;
		return len;
	}

	/**
	 * 416 分割等和子集，给定一个只包含正整数的非空数组，问是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
	 * 分析：求得数组中是否存在加起来为sum/2的数
	 * 01背包变形，将sum/2看成是背包容量，把nums中的每个值都看成是一个物品，例如nums[i] 就看成是第i个物品，它的体积是nums[i],
	 * 价值也是nums[i]，然后对其进行动态规划，计算dp[][]。dpp[i][j]表示当背包容量为j时的前i个物品的最优解.
	 * @param nums
	 * @return
	 */
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for(int num : nums){
			sum += num;
		}
		if(sum % 2 != 0)
			return false;
		int[][] dp = new int[nums.length+1][sum/2+1];
		for(int i = 0; i < dp.length; i++){
			dp[i][0] = 0;
		}
		for(int i = 0; i < dp[0].length; i++){
			dp[0][i] = 0;
		}
		for(int i = 1; i < nums.length; i++){
			for(int j = 1; j < dp[0].length; j++){
				if(nums[i] > j){
					dp[i][j] = dp[i-1][j];
				}
				else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i]]+nums[i]);
				}
				if(dp[i][j] == sum/2)
					return true;
			}
		}
		return false;


	}

    /**
     * 453 最小移动次数使数组元素相等
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        if(nums == null || nums.length == 0)
            return 0;
        for(int i = 0; i < nums.length; i++){
            min = min < nums[i] ? min : nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            res += nums[i] - min;
        }
        return res;
    }

    /**
     * 509 斐波那契数
     * @param N
     * @return
     */
    Map<Integer,Integer> fib_map = new HashMap<>();
    public int fib(int N) {
        if(N < 2)
            return N;
        if(fib_map.containsKey(N))
            return fib_map.get(N);
        fib_map.put(N, fib(N-1)+fib(N-2));
        return fib_map.get(N);
    }

	/**
	 * 542 01矩阵 BFS
	 * @param matrix
	 * @return
	 */
	public int[][] updateMatrix(int[][] matrix) {
		int[][] res = null;
		if(matrix == null || matrix.length == 0)
			return res;
		int m = matrix.length;
		int n = matrix[0].length;
		res = new int[m][n];
		int[][] visited = new int[m][n];
		Queue<int[]> queue = new LinkedList<>();
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(matrix[i][j] == 0) {
					queue.add(new int[]{i, j});
					visited[i][j] = 1;
				}
			}
		}
		int[] x = new int[]{0, 1, -1, 0};
		int[] y = new int[]{1, 0, 0, -1};
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				int[] top = queue.poll();
				for(int j = 0; j < 4; j++){
					int newX = top[0] + x[j];
					int newY = top[1] + y[j];
					if(newX < 0 || newX >= m || newY < 0 || newY >=n || visited[newX][newY] == 1 )
						continue;
					visited[newX][newY] = 1;
					res[newX][newY] = res[top[0]][top[1]] + 1;
					queue.add(new int[]{newX, newY});
				}
			}
		}
		return res;
	}

	/**
	 * 543 二叉树的直径
	 * 二叉树的直径就是任意两个节点路径长度中的最大值，这条路径可以穿过根节点
	 * 解析：二叉树的直径可以理解为以每个节点作为根节点取其中的左子树的最大深度和右子树的最大深度之和
	 * @param root
	 * @return
	 */
	int max_543 = 0;
	public int diameterOfBinaryTree(TreeNode root) {
//		if(root == null){
//			return 0;
//		}
		getR(root);
		return max_543;
	}
	public int getR(TreeNode t){
		if(t == null)
			return 0;
		int ld = getR(t.left);
		int rd = getR(t.right);
//		if(ld > rd){
//			return ld + 1;
//		}
//		else{
//			return rd + 1;
//		}
		if(max_543 < ld + rd){
			max_543 = ld + rd;
		}
		return Math.max(ld, rd) + 1;
	}

	/**
	 * 557 	反转字符串中的单词Ⅲ
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
		String[] words = s.split(" ");
		String res = "";
		for(int i = 0; i < words.length; i++){
			res += vers(words[i])+" ";
		}
		return res.trim();
	}
	private String vers(String s){
		StringBuilder sb = new StringBuilder(s);
		return sb.reverse().toString();
	}

	/**
	 * 560 和为k的子数组
	 * @param nums
	 * @param k
	 * @return
	 */
	public int subarraySum(int[] nums, int k) {
		if(nums == null || nums.length == 0)
			return 0;
		int start = 0, end = 0, sum = nums[0];
		int res = 0;
		while(end < nums.length){
			if(sum < k){
				end++;
				if(end < nums.length) {
					sum += nums[end];
				}
			}
			else if(sum == k){
				res++;
				sum -= nums[start];
				start++;
				end++;
				if(end < nums.length)
					sum += nums[end];

			}
			else{
				sum -= nums[start];
				start++;
			}

		}
		return res;
	}

    /**
     * 572 另一个树的子树easy
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null)
            return false;
        if(t == null)
            return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = s;
        while(temp != null || !stack.isEmpty()){
            if(temp != null){
                stack.push(temp);
                temp = temp.left;
            }
            else{
                temp = stack.pop();
                if(temp.val == t.val && isSameTree572(temp, t))
                    return true;
                temp = temp.right;
            }
        }
        return false;
    }
    public boolean isSameTree572(TreeNode s, TreeNode t){
        if(s == null && t == null)
            return true;
        if(s == null || t == null)
            return false;
        if(s.val == t.val && isSameTree572(s.left, t.left) && isSameTree572(s.right, t.right))
            return true;
        return false;
    }

	public boolean isSubtree572_2(TreeNode s, TreeNode t) {
		if (t == null) return true;   // t 为 null 一定都是 true
		if (s == null) return false;  // 这里 t 一定不为 null, 只要 s 为 null，肯定是 false
		return isSubtree572_2(s.left, t) || isSubtree572_2(s.right, t) || isSameTree_572(s,t);
	}

	/**
	 * 判断两棵树是否相同
	 */
	public boolean isSameTree_572(TreeNode s, TreeNode t){
		if (s == null && t == null) return true;
		if (s == null || t == null) return false;
		if (s.val != t.val) return false;
		return isSameTree_572(s.left, t.left) && isSameTree_572(s.right, t.right);
	}


	/**
	 * 590 N叉树的后序遍历
	 * @param root
	 * @return
	 */
	public List<Integer> postorder(Node root) {
		List<Integer> res = new ArrayList<>();
		if(root == null)
			return res;
		Stack<Node> stack1 = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
		stack1.push(root);
		List<Node> templist = null;
		while(!stack1.isEmpty()){
			Node t = stack1.pop();
			stack2.push(t);
			templist = t.children;
			for(Node n : templist){
				stack1.push(n);
			}
		}
		while(!stack2.isEmpty())
			res.add(stack2.pop().val);
		return res;
	}

	/**
	 * 658 找到k个最接近的数
	 * @param arr
	 * @param k
	 * @param x
	 * @return
	 */
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		List<Integer> res = new ArrayList<>();
		int left = 0, right = arr.length-1;
		while(right - left + 1 > k){
			int q = Math.abs(arr[right] - x);
			int p = Math.abs(arr[left] - x);
			if(q >= p)
				right--;
			else
				left++;
		}
		for(int i = left; i < left+k; i++)
			res.add(arr[i]);
		return res;
	}

	/**
	 * 687 最长同值路径
	 * @param root
	 * @return
	 */
	int max_687 = 0;
	public int longestUnivaluePath(TreeNode root) {
		if(root == null)
			return 0;
		ss(root);
		return max_687;
	}
	public int ss(TreeNode t){
		if(t == null)
			return 0;
		int left = ss(t.left);
		int right = ss(t.right);
		int l = 0, r = 0;
		if(t.left != null && t.left.val == t.val){
			l += left + 1;
		}
		if(t.right != null && t.right.val == t.val) {
			r += right + 1;
		}
		max_687 = Math.max(max_687, l + r);
		return Math.max(l, r);
	}

	/**
//	 * 887 鸡蛋掉落
//	 * @param K
//	 * @param N
//	 * @return
//	 */
//	public int superEggDrop(int K, int N) {
//
//	}

    /**
     * 695 岛屿的最大面积（深度优先遍历）
     * @param grid
     * @return
     */
    int flag_695[][];
    int max_695 = 0;
    int temp_695 = 0;
    int[] x_695 = new int[]{1,0,-1,0};
    int[] y_695 = new int[]{0,1,0,-1};
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0)
            return 0;
        flag_695 = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                temp_695 = 0;
                dfs_695(i, j, grid);
                max_695 = max_695 < temp_695 ? temp_695 : max_695;
            }
        }
        return max_695;
    }
    public void dfs_695(int i, int j, int[][] grid){
        if(flag_695[i][j] == 1)
            return;
        if(grid[i][j] == 0){
            flag_695[i][j] = 1;
            return;
        }
        if(grid[i][j] == 1){
            flag_695[i][j] = 1;
            temp_695 ++;
        }
        for(int k = 0; k < 4; k++){
            if(i+x_695[k] >= 0 && i+x_695[k] < grid.length && j+y_695[k] >= 0 && j+y_695[k] < grid[0].length)
                dfs_695(i+x_695[k], j+y_695[k], grid);
        }
    }

	/**
	 * 820 单词的压缩编码
	 * @param words
	 * @return
	 */
	public int minimumLengthEncoding(String[] words) {

	}

	/**
	 * 836 矩形重叠
	 * @param rec1
	 * @param rec2
	 * @return
	 */
	public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		if(rec1[0] >= rec2[2] || rec1[2] <= rec2[0] || rec1[1] >= rec2[3] || rec1[3] <= rec2[1])
			return false;
		return true;
	}

	/**
	 * 876 链表的中间节点
	 * @param head
	 * @return
	 */
	public ListNode middleNode(ListNode head) {
		if(head == null)
			return head;
		int len = 0;
		ListNode temp = head;
		while(temp != null){
			temp = temp.next;
			len++;
		}
		len /= 2;
		temp = head;
		for(int i = 0; i < len; i++)
			temp = temp.next;
		return temp;
	}

	/**
	 * 892 三维形体的表面积
	 * @param grid
	 * @return
	 */
	public int surfaceArea(int[][] grid) {
		if(grid == null || grid.length == 0)
			return 0;
		int res = 0;
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				if(grid[i][j] != 0)
					res += 6*grid[i][j]-2*(grid[i][j]-1);
				if(j-1>0){
					res -= Math.min(grid[i][j-1], grid[i][j])*2;
				}
				if(i-1>0)
					res -= Math.min(grid[i-1][j], grid[i][j])*2;
			}
		}
		return res;
	}

	/**
	 * 914 卡牌分组
	 * @param deck
	 * @return
	 */
	public boolean hasGroupsSizeX(int[] deck) {
		int[] a = new int[10000];
		for(int i = 0; i < deck.length; i++){
			a[deck[i]]++;
		}
		int s = 0;
		for(int i = 0; i < 10000; i++){
			s = gcd_914(s, a[i]);
			if(s == 1)
				return false;
		}
		return s >= 2;
	}
	public int gcd_914(int x, int y){
		return y == 0 ? x : gcd(y, x % y);
	}

	/**
	 * 945 使数组唯一的最小增量
	 * @param A
	 * @return
	 */
	public int minIncrementForUnique(int[] A) {
		Arrays.sort(A);
		int res = 0;
		for(int i = 1; i < A.length; i++){
			if(A[i] <= A[i-1]){
				int temp = A[i];
				A[i] = A[i-1]+1;
				res += A[i]-temp;
			}
		}
		return res;
	}

    /**
     * 946 验证栈序列
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
	 * 983 最低票价（动态规划）
	 * @param days
	 * @param costs
	 * @return
	 */
	public int mincostTickets(int[] days, int[] costs) {
		int[] dp = new int[days.length];
		dp[0] = Math.min(costs[0], Math.min(costs[1], costs[2]));

		for(int i = 1; i < days.length; i++){
			int c1 = dp[i-1] + costs[0];
			int c2 = Integer.MAX_VALUE;
			int c3 = Integer.MAX_VALUE;
			int j;
			for(j = i-1; j >= 0; j--){
				if(days[i] - days[j] >= 7){
					break;
				}
			}
			c2 = j >= 0 ? dp[j] + costs[1] : costs[1];
			for(j = i-1; j >= 0; j--){
				if(days[i] - days[j] >= 30){
					break;
				}
			}
			c3 = j >= 0 ? dp[j-1] + costs[2] : costs[2];

			dp[i] = Math.min(c1, Math.min(c2, c3));
		}
		return dp[days.length-1];
	}

    /**
     * 994 腐烂的橘子
     * 多源广度优先
     */
    class Orange{
        int x;
        int y;
        public Orange(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public int orangesRotting(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        Queue<Orange> queue = new ArrayDeque<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] == 2)
                    queue.add(new Orange(i, j));
            }
        }
        int count = 0;
        int[] posx = new int[]{1,0,-1,0};
        int[] posy = new int[]{0,1,0,-1};
        while(!queue.isEmpty()){
            int flag = 0;
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Orange orange = queue.poll();
                for(int j = 0; j < 4; j++){
                    if(orange.x+posx[j] < 0 || orange.x+posx[j] >= r ||
                        orange.y+posy[j] < 0 || orange.y+posy[j] >= c ||
                        grid[orange.x+posx[j]][orange.y+posy[j]] != 1)
                        continue;
                    grid[orange.x+posx[j]][orange.y+posy[j]] = 2;
                    queue.add(new Orange(orange.x+posx[j],orange.y+posy[j]));
                    flag = 1;
                }
            }
            if(flag == 1)
                count++;
        }
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(grid[i][j] == 1)
                    return -1;
            }
        }
        return count;
    }

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

	/**
	 * 1013 将数组分成h和相等的三个部分
	 * @param A
	 * @return
	 */
	public boolean canThreePartsEqualSum(int[] A) {
		int sum = 0;
		for(int i = 0; i < A.length; i++){
			sum += A[i];
		}
		if(sum % 3 != 0)
			return false;
		int t = sum / 3;
		int temp = 0;
		int count = 0;
		for(int i = 0; i < A.length; i++){
			temp += A[i];
			if(temp == t){
				temp = 0;
				count++;
			}
		}
		return count == 3;
	}

	/**
	 * 1071 字符串的最大公因子（辗转相除法）
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String gcdOfStrings(String str1, String str2) {
		if(!(str1+str2).equals(str2+str1))
			return "";
		return str1.substring(0, gcd(str1.length(), str2.length()));
	}
	public int gcd(int a, int b){
		return b == 0 ? a : gcd(b, a % b);
	}

    /**
     * 1103 分糖果Ⅱ
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        if(num_people == 0)
            return res;
        int index = 0;
        int temp = 1;
        while(candies > 0){
            if(index == num_people)
                index = 0;

            if(candies - temp > 0){
                res[index++] += temp;
            }
            else{
                res[index++] += candies;
            }
            candies -= temp;
            temp++;
        }
        return res;
    }

	/**
	 * 1160 拼写单词
	 * @param words
	 * @param chars
	 * @return
	 */
	public int countCharacters(String[] words, String chars) {
		int[] c = new int[26];
		for(char ch : chars.toCharArray()){
			c[ch-'a'] ++;
		}
		int res = 0;
		a:for(String s : words){
			int[] w = new int[26];
			for(char ch : s.toCharArray())
				w[ch-'a']++;
			for(int i = 0; i < 26; i++){
				if(w[i] > c[i])
					continue a;
			}
			res += s.length();
		}
		return res;
	}

	/**
	 * 1162 地图分析
	 * @param grid
	 * @return
	 */
	public int maxDistance(int[][] grid) {
		int max = -1;
		if(grid == null || grid.length == 0)
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){

			}
		}
		return max;
	}

	/**
	 * 1207 独一无二的出现次数
	 * @param arr
	 * @return
	 */
	public boolean uniqueOccurrences(int[] arr) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < arr.length; i++){
			if(map.containsKey(arr[i])){
				map.put(arr[i], map.get(arr[i])+1);
			}
			else{
				map.put(arr[i], 1);
			}
		}
		Set<Integer> set = new HashSet<>(map.values());
		return map.size() == set.size();
	}

	/**
	 * 1248 统计优美子数组， 滑动窗口
	 * @param nums
	 * @param k
	 * @return
	 */
	public int numberOfSubarrays(int[] nums, int k) {
		int res = 0;
		int[] temp = new int[nums.length + 2];
		int index = 0;
		for(int i = 0; i < nums.length; i++){
			if(nums[i] % 2 != 0){
				temp[++index] = i;
			}
		}
		temp[0] = -1;
		temp[index+1] = nums.length;
		for(int i = 1; i + k < index + 2; i++){
			res += (temp[i]-temp[i - 1])*(temp[i + k]-temp[i + k - 1]);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] test = new int[]{2,3,6,7};
		int target = 7;
		//List<List<Integer>> res = combinationSum(test, target);
	}

	/**
	 * 1431 拥有最多糖果的孩子
	 * @param candies
	 * @param extraCandies
	 * @return
	 */
	public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
		List<Boolean> res = new ArrayList<>();
		int max = -1;
		for(int i = 0; i < candies.length; i++){
			max = max < candies[i] ? candies[i] : max;
		}
		for(int i = 0; i < candies.length; i++){
			if(candies[i] + extraCandies >= max)
				res.add(true);
			else
				res.add(false);
		}
		return res;
	}

	/**
	 * 插入一个5，使得整数N变为最大
	 * @param N
	 * @return
	 */
    public static int htt(int N){
        if(N == 0)
            return 50;
        int max = N*10+5;
        int temp = N;
        int flag = 1;
        if(N < 0)
            flag = -1;
        int i = 10;
        while(Math.abs(temp) != 0){
            int t = N % i;
            temp = N / i;
            max = Math.max(max, temp*(i*10) + 5*i*flag + t);
            i *= 10;
        }
        return max;
    }

	/**
	 * 5342 最多可以参加的会议数目
	 * @param events
	 * @return
	 */
	public int maxEvents(int[][] events) {
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return a[0] - b[0]!=0?a[0]-b[0]:a[1]-b[1];
			}
		});
		for(int i =0 ; i < events.length; i++)
			queue.add(events[i]);
		int cnt = 0;
		int cur = 0;
		while(!queue.isEmpty()){
			int[] temp = queue.poll();
			if(cur < temp[0]){
				cnt++;
				cur = temp[0]+1;
			}
			else if(cur >= temp[0] && cur <= temp[1]){
				cnt++;
				cur++;
			}
			//重新计算优先级
			while(!queue.isEmpty()){
				temp = queue.poll();
				if(cur > temp[0]){
					temp[0] = cur;
					if(temp[1] >= temp[0])
						queue.add(temp);
				}
				else if(cur <= temp[0]){
					queue.add(temp);
					break;
				}
			}
		}
		return cnt;
	}

	/**
	 * 5343 多次求和构造目标函数
	 * @param target
	 * @return
	 */
	public boolean isPossible(int[] target) {
		long sum = 0;
		Comparator<Long> comparator = new Comparator<Long>() {
			@Override
			public int compare(Long aLong, Long t1) {
				return (int)(t1-aLong);
			}
		};
		Queue<Long> queue = new PriorityQueue<>(target.length, comparator);
		for(long x: target){
			sum += x;
			queue.add(x);
		}

		while(queue.peek() > 1){
			long mx = queue.poll();
			long delta = sum - mx;
			if(mx <= delta)
				return false;
			mx -= delta;
			sum -= delta;
			queue.add(mx);
		}
		return true;
	}

	/**
	 * Search in a Binary Search Tree
	 * 在二叉搜索树中查找
	 * @param root
	 * @param val
	 * @return
	 */
	public TreeNode searchBST(TreeNode root, int val) {
		if(root == null)
			return null;
		if(root.val == val)
			return root;
		if(root.val < val)
			return searchBST(root.right, val);
		return searchBST(root.left, val);
	}

	/**
	 * Insert into a Binary Search Tree
	 * 在二叉搜索树中插入值
	 * @param root
	 * @param val
	 * @return
	 */
	public TreeNode insertIntoBST(TreeNode root, int val) {
		if(root.val > val && root.left == null)
			root.left = new TreeNode(val);
		else if(root.val < val && root.right == null)
			root.right = new TreeNode(val);
		else if(root.val > val){
			insertIntoBST(root.left, val);
		}
		else{
			insertIntoBST(root.right, val);
		}
		return root;
	}

}

/**
 * 链表节点定义
 */
class ListNode {
    int val;
  	ListNode next;
 	ListNode(int x) { val = x; }
}

/**
 * 二叉树节点定义
 */
class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){
		val = x;
	}
}
class Node {
	public int val;
	public List<Node> children;

	public Node() {}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, List<Node> _children) {
		val = _val;
		children = _children;
	}
};


















