package 入门.class003位运算;

/**
 * 二进制和位运算
 */
public class 算法讲解003二进制和位运算 {

    /**
     * 有符号二进制 第一位表示符号
     */

    /**
     * 正数 变成负数 =>
     * var a = 0001 （表示1）
     * 取反 +1
     * ~a + 1 = 1111 （表示-1）
     */
    public static void main(String[] args) {

        int a = 1;
        System.out.println(Integer.toBinaryString(a));

        System.out.println(Integer.toBinaryString(-a));

    }
}
