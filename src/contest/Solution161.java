package contest;

public class Solution161 {
    /**
     * 5247. 交换字符使得字符串相同
     * @param s1
     * @param s2
     * @return
     */
    public int minimumSwap(String s1, String s2) {
        int n1 = 0;
        int n2 = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) == 'x' && s2.charAt(i) == 'y'){
                n1++;
            }
            if(s1.charAt(i) == 'y' && s2.charAt(i) == 'x'){
                n2++;
            }
        }
        if(n1 % 2 + n2 % 2 == 1){
            return -1;
        }
        int res = n1/2 + n2/2;
        if(n1%2 == 1)
            res += 2;
        return res;
    }
}
