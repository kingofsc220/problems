package Array;

import java.util.*;

/**
 * LeetCode problem 18      4Sum
 *
 *
 * Given an array nums of n integers and an integer target,
 * are there elements a, b, c, and d in nums
 * such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 *
 * Notice that the solution set must not contain duplicate quadruplets.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 *
 * Example 2:
 *
 * Input: nums = [], target = 0
 * Output: []
 *
 *
 * Constraints:
 *       0 <= nums.length <= 200
 *      -10^9 <= nums[i] <= 10^9
 *      -10^9 <= target <= 10^9
 */
public class Pro18_4Sum {

    /**
     * 这道题和 {@link Pro15_3Sum} 方法是一样的
     * 可能考察的是 kSum 这样的通用范式
     *
     * 先看下 3Sum：
     *     for (int i = 0; i < nums.length - 2; i++) {
     *         int l = i + 1;
     *         int r = nums.length - 1;
     *
     *         while (l < r) {
     *             int sum = nums[i] + nums[l] + nums[r];
     *
     *             if (0 == sum) {
     *                 set.add(Arrays.asList(nums[i], nums[l], nums[r]));
     *                 ++l;
     *                 --r;
     *             } else if (0 < sum) {
     *                 --r;
     *             } else {
     *                 ++l;
     *             }
     *         }
     *     }
     *
     * 大致思路：
     *    如果 k=2，则按照2Sum进行计算
     *    如果 k>2，则递归调用(k-1)
     *    先排序，再使用双指针逼近法
     *
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int k = 4;
        if (nums == null || nums.length < k) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        return new ArrayList<>(kSum(nums, k, 0, target));

    }

    /**
     *
     * @param nums      输入数组
     * @param k         计算 k sum
     * @param start     搜索起始值，即取值范围为：nums[start]~nums[nums.length-1]
     * @param target    k sum 目标值，即 kSum() = target
     */
    private Set<List<Integer>> kSum(int[] nums, int k, int start, int target) {
        Set<List<Integer>> resultSet = new HashSet<>();

        if (k * nums[start] > target)
            return resultSet; //k被最小值，已经大于target，后续组合都大于target

        if (target > k * nums[nums.length - 1])
            return resultSet; //k被最大值，都小于target，所有组合都小于target

        if (k == 2) {
            int l = start;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    List<Integer> list = new ArrayList<>();
                    //通过Set去重List，是按元素逐个比较的，
                    //元素存入List要按顺序，从小到大或从大到小依次存入
                    //这里 nums[l] 和 nums[r] 是最大的两个数
                    //因此采用从大到小的插入方式
                    list.add(nums[r--]);
                    list.add(nums[l++]);
                    resultSet.add(list);

                    while (l < r && nums[l] == nums[l - 1])
                        ++l; //下一个元素与本次重复，直接跳过
                    while (l < r && nums[r] == nums[r + 1])
                        --r; //下一个元素与本次重复，直接跳过
                } else if (sum > target) {
                    --r;
                } else {
                    ++l;
                }
            }
        } else {
            for (int i = start; i < nums.length - k + 1; i++) {
                if (i != start && nums[i] == nums[i - 1]) {
                    continue;  //前面 i-1时已经算过了
                }
                Set<List<Integer>> set = kSum(nums, k - 1, i + 1, target - nums[i]);
                for (List<Integer> list : set){
                    list.add(nums[i]);
                    resultSet.add(list);
                }
            }
        }

        return resultSet;
    }


    public static void main(String[] args) {
        Pro18_4Sum solution = new Pro18_4Sum();
        System.out.println(solution.fourSum(new int[]{1,0,-1,0,-2,2}, 0));
    }

}
