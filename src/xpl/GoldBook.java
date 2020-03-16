package xpl;

/**
 * 程序员面试金典
 */
public class GoldBook {

    /**
     * 面试题01.06 字符串压缩
     * @param S
     * @return
     */
    public String compressString(String S) {
        String res = "";
        if(S == null || S.length() == 0)
            return res;
        char ch = S.charAt(0);
        res += ch;
        int cnt = 1;
        for(int i = 1; i < S.length(); i++){
            //如果和上一个字符不一样
            if(ch != S.charAt(i)){
                ch = S.charAt(i);
                res += String.valueOf(cnt);
                res += S.charAt(i);
                cnt = 1;
            }
            else
                cnt++;
            if(S.length()-1 == i){
                res += String.valueOf(cnt);
            }
        }
        return res.length() < S.length() ? res : S;
    }

    /**
     * 面试题10.1 合并排序的数组
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m-1;
        int j = n-1;
        for(int z = m+n-1; z >= 0; z--){
            if(i >= 0 && j >= 0)
                A[z] = A[i] > B[j] ? A[i--] : B[j--];
            else if(i < 0)
                A[z] = B[j--];
            else if(j < 0)
                A[z] = A[i--];
        }
    }
}
