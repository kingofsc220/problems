package Array;

import java.util.Arrays;
import java.util.HashMap;


/**
 * LeetCode problem 1   Two Sum
 *
 *
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution,
 * and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 *
 * Constraints:
 *      2 <= nums.length <= 10^3
 *     -10^9 <= nums[i] <= 10^9
 *     -10^9 <= target <= 10^9
 * Only one valid answer exists.
 *
 *
 *
 * Accepted 3,574,903
 * Submissions 7,785,125
 */
public class TwoSum {

    /**
     * 缓存等待法:
     * <p/>
     * 在依次执行过程中，
     * 每个节点都检查并完成别的节点对自己的需求，
     * 并把自己对别的节点的需求缓存起来，
     * 则在依次遍历后，所有节点的需求都可以得到满足
     * <p/>
     * 该方法也是解决循环依赖问题的核心，
     * 如：
     *   对象 [A, B, C] 需要依次实例化，其中A依赖B,C， B依赖A
     *   其中set存放已经加载好的类实例 {a, b, c}
     * 实例化过程：
     *   1.在A实例化时，依赖B,C
     *     在set中查找已经实例化的对象，发现没有B和C
     *     则实例化A a(不完全A)，并存入set中，即set.add(a)
     *     并把自己的需求存入map中，即map.put(B,a), map.put(C,a)
     *   2.在B实例化时，依赖A
     *     在set中查找已经实例化的对象，发现有A
     *     则实例化B b(完全B)，并存入set中，即set.add(b)
     *     并查看map是否有依赖自己的，发现A依赖自己，则对A进行补充
     *   3.在C实例化时，不依赖
     *      则实例化C c(完全C)，并存入set中，即set.add(c)
     *     并查看map是否有依赖自己的，发现A依赖自己，则对A进行补充(完全A)
     *   4.销毁map得到加载后的实例集合set {a, b, c}
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[] {map.get(nums[i]), i};
            } else {
                map.put(target - nums[i], i);
            }
        }

        return new int[2];
    }



    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();

        int [] a = new int[] {3,2,4};
        System.out.println(Arrays.toString(twoSum.twoSum(a, 6)));

    }

}
