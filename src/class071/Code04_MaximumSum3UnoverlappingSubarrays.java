package class071;

// 三个无重叠子数组的最大和
// 给你一个整数数组 nums 和一个整数 k
// 找出三个长度为 k 、互不重叠、且全部数字和（3 * k 项）最大的子数组
// 并返回这三个子数组
// 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置
// 如果有多个结果，返回字典序最小的一个
// 测试链接 : https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/
public class Code04_MaximumSum3UnoverlappingSubarrays {

	public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int n = nums.length;
		int[] sums = new int[n];
		for (int i = 0, j = 0, sum = 0; j < n; j++) {
			sum += nums[j];
			if (j - i + 1 == k) {
				sums[i] = sum;
				sum -= nums[i];
				i++;
			}
		}
		int[] prefix = new int[n];
		for (int i = 1, j = k; j < n; i++, j++) {
			if (sums[i] > sums[prefix[j - 1]]) {
				prefix[j] = i;
			} else {
				prefix[j] = prefix[j - 1];
			}
		}
		int[] suffix = new int[n];
		suffix[n - k] = n - k;
		for (int i = n - k - 1; i >= 0; i--) {
			if (sums[i] >= sums[suffix[i + 1]]) {
				suffix[i] = i;
			} else {
				suffix[i] = suffix[i + 1];
			}
		}
		int a = 0, b = 0, c = 0, max = 0;
		for (int i = k, j = 2 * k - 1; j < n - k; i++, j++) {
			if (sums[prefix[i - 1]] + sums[i] + sums[suffix[j + 1]] > max) {
				max = sums[prefix[i - 1]] + sums[i] + sums[suffix[j + 1]];
				a = prefix[i - 1];
				b = i;
				c = suffix[j + 1];
			}
		}
		return new int[] { a, b, c };
	}

}