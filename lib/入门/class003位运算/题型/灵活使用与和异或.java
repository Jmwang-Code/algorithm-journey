package 入门.class003位运算.题型;

import static 入门.class003位运算.题型.灵活使用与和异或.Leetcode137.singleNumber;

public class 灵活使用与和异或 {

    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 2};
        System.out.println(singleNumber(arr));
    }


    static class Leetcode136 {
        public static int singleNumber(int[] nums) {
            int ans = 0;
            for (int num : nums) {
                ans ^= num;
            }
            return ans;
        }
    }

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
}
