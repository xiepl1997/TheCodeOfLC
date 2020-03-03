package xpl;

/**
 * 程序员面试金典
 */
public class GoldBook {

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
