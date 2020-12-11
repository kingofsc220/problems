package Array;


/**
 * LeetCode problem 33   Search in Rotated Sorted Array
 *
 *
 * You are given an integer array nums sorted in ascending order, and an integer target.
 *
 * Suppose that nums is rotated at some pivot unknown to you beforehand
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * If target is found in the array return its index, otherwise, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 *
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 *
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 *
 * Constraints:
 *      1 <= nums.length <= 5000
 *     -10^4 <= nums[i] <= 10^4
 *      All values of nums are unique.
 *      nums is guranteed to be rotated at some pivot.
 *     -10^4 <= target <= 10^4
 */
public class Pro33_SearchInRotatedSortedArray {

    /**
     * 还是二分查找问题
     *
     * 由于经过翻折，其只有半边满足从小到大顺序，确定哪一边是关键
     *
     * 1. n[i] > tar,  如果 n[l] > tar 则说明 n[l~i]都大于 tar，右边迭代搜索
     *                 如果 n[l] < tar 左边迭代搜索
     *
     * 2. n[i] < tar,  如果 n[r] < tar 则说明 n[i~r]都小于 tar，左边迭代搜索
     *                 如果 n[r] > tar 右边迭代搜索
     *
     * 3. n[i] == tar, 返回 i，循环结束返回 -1
     *
     */
    public int search(int[] nums, int target) {
        return binarySearch(0, nums.length-1, target, nums);
    }



    private int binarySearch(int l, int r, int tar, int[] nums) {
        if (l > r) return -1;
        if (nums[l] == tar) return l;
        int i = (r + l) / 2;
        if (nums[i] == tar) return i;

        if ((nums[i] > tar && nums[l] > tar) || (nums[i] < tar && nums[l] < tar)) {
            return binarySearch(i+1, r, tar, nums);
        } else  {
            return binarySearch(l+1, i-1, tar, nums);
        }


//        if (nums[i] > tar) {
//            if (nums[l] > tar) {
//                return binarySearch(i+1, r, tar, nums);
//            } else if (nums[l] < tar) {
//                return binarySearch(l+1, i-1, tar, nums);
//            } else {
//                return l;
//            }
//        } else if (nums[i] < tar) {
//            if (nums[l] < tar) {
//                return binarySearch(i+1, r, tar, nums);
//            } else if (nums[l] > tar) {
//                return binarySearch(l+1, i-1, tar, nums);
//            } else {
//                return l;
//            }
//        } else {
//            return i;
//        }
    }

    public static void main(String[] args) {
        int[] tmp = new int[] {4,5,6,7,8,1,2,3};
        Pro33_SearchInRotatedSortedArray solution = new Pro33_SearchInRotatedSortedArray();
        System.out.println(solution.search(tmp, 8));
    }


}
