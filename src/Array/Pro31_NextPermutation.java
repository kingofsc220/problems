package Array;

/**
 * LeetCode problem 31     Next Permutation
 *
 *
 *
 * Implement next permutation, which rearranges numbers
 * into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it
 * as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 *
 *
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 *
 *
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 *
 *
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *      1 <= nums.length <= 100
 *      0 <= nums[i] <= 100
 *
 */
public class Pro31_NextPermutation {


    /**
     * 全排列问题
     *
     * [1,2,3]的全排列有（按递增顺序）:
     *    [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]
     *
     * 发现规律如下:
     *   1.进位条件，观察 [1,2,3] -> [1,3,2]  通过[3]，判断[2]要进位
     *                 [1,3,2] -> [2,1,3]  通过[3,2]，判断[1]要进位
     *                 [2,3,1] -> [3,1,2]  通过[3,1]，判断[2]要进位
     *                 [3,2,1] -> [x,x,x]  通过[3,2,1]，判断要进位但无位可进
     *
     *             思路，[1,3,2]为例，分为子数组[1] 和 [3,2]，
     *                  观察[3,2]发现，按照全排列的要求，[3,2]已经是最大排列
     *                  因此只能对[1]进行进位，才能让排列变得更大。
     *
     *   2.进位方法，观察 [1,2,3] -> [1,3,2]  将[2] 和 [3] 中的 [3] 进行了交换
     *                 [1,3,2] -> [2,1,3]  将[1] 和 [3,2] 中的 [2] 进行了交换，同时[3,1]排序成[1,3]
     *                 [2,3,1] -> [3,1,2]  将[2] 和 [3,1] 中的 [3] 进行了交换，同时[2,1]排序成[1,2]
     *
     *             思路，[1,3,2]为例，找到字数组[3,2]中所有比[1]大的，取其中最小的那个[2]，与[1]做交换
     *                  得到[2,3,1]，再对[3,1]进行升序排序得到[1,3]
     *
     *
     */
    public void nextPermutation(int[] nums) {
        int l = nums.length - 1, r = l;

        //找到最大排列子数组 [l ~ nums.length-1]
        while (0 < l && nums[l] <= nums[l - 1]) {
            --l;
        }
        //已经是最大排序就反转数组
        if (l == 0) {
            swap(nums, 0, nums.length - 1);
            return;
        }

        int i = l - 1; //记录要进位的位置
        swap(nums, l, r); //l ~ r进行排序

        while (l <= r) {
            if (nums[l] > nums[i]) {
                nums[l] = nums[l] ^ nums[i];
                nums[i] = nums[i] ^ nums[l];
                nums[l] = nums[l] ^ nums[i];
                break;
            } else {
                ++l;
            }
        }
    }

    private void swap(int[] nums, int l, int r) {
        while (l < r) {
            nums[l] = nums[l] ^ nums[r];
            nums[r] = nums[r] ^ nums[l];
            nums[l] = nums[l] ^ nums[r];
            ++l;
            --r;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Pro31_NextPermutation solution = new Pro31_NextPermutation();
        solution.nextPermutation(nums);
    }

}
