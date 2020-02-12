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
}
