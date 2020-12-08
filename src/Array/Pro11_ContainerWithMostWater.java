package Array;

/**
 * LeetCode problem 11   Container With Most Water
 *
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the
 * two endpoints of the line i is at (i, ai) and (i, 0).
 * Find two lines, which, together with the x-axis forms a container,
 * such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 *
 * Example 1:
 *
 *     |
 *   8 |     *                   *
 *   7 |     *                   *       *
 *   6 |     *   *               *       *
 *   5 |     *   *       *       *       *
 *   4 |     *   *       *   *   *       *
 *   3 |     *   *       *   *   *   *   *
 *   2 |     *   *   *   *   *   *   *   *
 *   1 | *   *   *   *   *   *   *   *   *
 *   0 ——————————————————————————————————————————————
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are
 *              represented by array [1,8,6,2,5,4,8,3,7].
 *              In this case, the max area of water (blue section)
 *              the container can contain is 49.
 *
 *
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 *
 * Example 3:
 *
 * Input: height = [4,3,2,1,4]
 * Output: 16
 *
 *
 * Example 4:
 *
 * Input: height = [1,2,1]
 * Output: 2
 *
 *
 * Constraints:
 *      n = height.length
 *      2 <= n <= 3 * 104
 *      0 <= height[i] <= 3 * 104
 */
public class Pro11_ContainerWithMostWater {


    /**
     * 双指针逼近法
     * The intuition behind this approach is that
     * the area formed between the lines will always
     * be limited by the height of the shorter line.
     * Further, the farther the lines, the more will be the area obtained.
     *
     * We take two pointers, one at the beginning and one at the end
     * of the array constituting the length of the lines.
     * Further, we maintain a variable result to store the maximum area obtained till now.
     *
     * At every step, we find out the area formed between them, update result
     * and move the pointer pointing to the shorter line towards the other end by one step.
     *
     *
     * How this approach works?
     *
     * Initially we consider the area constituting the exterior most lines.
     * Now, to maximize the area, we need to consider the area between the lines of larger lengths.
     * If we try to move the pointer at the longer line inwards,
     * we won't gain any increase in area, since it is limited by the shorter line.
     * But moving the shorter line's pointer could turn out to be beneficial,
     * as per the same argument, despite the reduction in the width.
     * This is done since a relatively longer line obtained by moving the
     * shorter line's pointer might overcome the reduction in area caused by the width reduction.
     *
     * For further clarification click here and for the proof click here.
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int result = 0;

        while (l < r) {
            int tmp = Math.min(height[r], height[l]) * (r - l);
            if (tmp > result) {
                result = tmp;
            }
            if (height[l] < height[r]) {
                int h = height[l]; //标记当前高度
                ++l;
                while (l < r && height[l] < height[h])
                    ++l; //直到找到一个更高的
            } else {
                int h = height[r]; //标记当前高度
                --r;
                while (l < r && height[r] < height[h])
                    --r;  //直到找到一个更高的
            }
        }

        return result;
    }


}
