package 入门.class003位运算.题型;

import static 入门.class003位运算.题型.灵活使用与和异或.Leetcode137.singleNumber;

public class 灵活使用与和异或 {

    static class Leetcode136 {
        public static int singleNumber(int[] nums) {
            int ans = 0;
            for (int num : nums) {
                ans ^= num;
            }
            return ans;
        }
    }

    /**
     * 纵向对比
     */
    static class Leetcode137 {
        public static int singleNumber(int[] nums) {
            int ans = 0;
            for (int i = 0; i < 32; i++) {
                int count = 0;
                for (int num : nums) {
                    count += (num >> i) & 1;
                }
                if (count % 3 != 0) {
                    ans |= 1 << i;
                }
            }
            return ans;
        }
    }

    /**
     * 同一位 分成0和1 两队
     */
    static class Leetcode260 {
        public int[] singleNumber(int[] nums) {
            int xor = 0;
            for (int num : nums) {
                xor ^= num;
            }
            int mask = xor & (-xor);
            int a = 0, b = 0;
            for (int num : nums) {
                if ((num & mask) == 0) {
                    a ^= num;
                } else {
                    b ^= num;
                }
            }
            return new int[]{a, b};
        }
    }

    static class leetcode461{
        public int hammingDistance(int x, int y) {
            int k = x ^ y;
            int count = 0;
            for(int i = 30;i>=0;i--){
                if(((k >> i) & 1) == 1){
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * 纵向对比
     */
    static class leetcode476{
        //num
        //0101 5

        //k
        //0001  第一次 1
        //0011  第二次 3
        //0111  第三次 7

        //k ^ num
        //0101 0111
        //0010 2
        public int findComplement(int num) {
            int k = 0;
            while(k < num){
                k = (k << 1) | 1;
            }
            return k ^ num;
        }

        //num
        //0101 5

        //n
        //0101 5
        //0010 2  0111 7     1
        //0001 3  0111 7     2
        //0000 7  0111 7     4
        //0000 7  0111 7     8
        //0000 7  0111 7     16

        //0111 ^ 0101
        //0010 2
        public int findComplement2(int num) {
            int n = num;
            n |= n >>> 1;
            n |= n >>> 2;
            n |= n >>> 4;
            n |= n >>> 8;
            n |= n >>> 16;
            return n ^ num;
        }
    }

    static class leetcode762{
        public static int countPrimeSetBits(int left, int right) {
            int count = 0;
            for(int i = left;i<=right;i++){
                int k = 0;
                for(int j = 0;j<= 31;j++){
                    if((((i >> j) & 1)==1)){
                        k++;
                    }
                }
                if (isZhiShu(k) && k>1){
                    count++;
                }
            }

            return count;
        }

        public static boolean isZhiShu(int k){
            for(int i=2;i<k;i++){
                if(k%i==0){
                    return false;
                }
            }
            return true;
        }

        public static int countPrimeSetBits2(int left, int right) {
            int count = 0;
            for(int i = left;i<=right;i++){
                int k = Integer.bitCount(i);
                if (isZhiShu(k) && k>1){
                    count++;
                }
            }

            return count;
        }

    }

    static class leetcode693{
        //10101
        //^
        //1010

        //11111

        //100000
        //&
        //011111
        public static boolean hasAlternatingBits(int n) {
            int a = n ^ (n >> 1);
            return (a & (a + 1)) == 0;
        }
    }

    static class leetcode868{
        public static int binaryGap(int n) {
            int last = -1, ans = 0;
            for (int i = 0; n != 0; ++i) {
                if ((n & 1) == 1) {
                    if (last != -1) {
                        ans = Math.max(ans, i - last);
                    }
                    last = i;
                }
                n >>= 1;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        leetcode868.binaryGap(22);
        leetcode868.binaryGap(21);
    }
}
