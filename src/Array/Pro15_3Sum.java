package Array;


import java.util.*;

/**
 * LeetCode problem 15   3Sum
 *
 *
 * Given an array nums of n integers,
 * are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 *
 * Example 2:
 *
 * Input: nums = []
 * Output: []
 *
 *
 * Example 3:
 *
 * Input: nums = [0]
 * Output: []
 *
 *
 * Constraints:
 *      0 <= nums.length <= 3000
 *     -105 <= nums[i] <= 105
 */
public class Pro15_3Sum {


    /**
     * todo: 从TwoSum中我们得到了两种方法：缓存等待值法 和 双指针逼近法
     *
     * todo: 方法1：缓存等待值法
     * 而 ThreeSum其实就是这两种方法的拓展
     * 通过最外层 for循环固定一个数，原问题就简化为 TwoSum问题
     *
     * 注意：该方法能够正确执行，但可能会超时！！！
     */
     public List<List<Integer>> threeSum1_1(int[] nums) {
         if (nums == null || nums.length < 3) {
             return new ArrayList<>();
         }

         Set<List<Integer>> set = new HashSet<>();
         for (int i = 0; i < nums.length; i++) {
             Map<Integer, Integer> map = new HashMap<>();
             for (int j = i + 1; j < nums.length; j++) {
                 if (map.containsKey(nums[j])) {
                     List<Integer> list = Arrays.asList(nums[i], map.get(nums[j]), nums[j]);
                     // Set<List>判断重复调用的是 ArrayList的 equals()方法，
                     // 查看源码得知，该方法通过 for循环逐个元素比较是否相同
                     // 因此判断 [0,1,-1] 与 [-1,0,1] 是不重复的
                     // 因此在存入Set之前，要对列表排个序，保证Set能够正确去重
                     list.sort(Comparator.naturalOrder());
                     set.add(list);
                 } else {
                     map.put(-nums[i] - nums[j], nums[j]);
                 }
             }
         }
         return new ArrayList<>(set);
     }


    /**
     * todo: 方法1：缓存等待值法（优化）
     * 注意：该方法能够正确执行，但非常缓慢！！！
     */
    public List<List<Integer>> threeSum1_2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue; //前面已经计算过了
            }

            Map<Integer, Integer> map = new HashMap<>();
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (map.containsKey(nums[j])) {

                    if (set.contains(nums[j])) {
                        continue; //前面已经计算过了
                    } else {
                        set.add(nums[j]);
                    }

                    List<Integer> list = Arrays.asList(nums[i], map.get(nums[j]), nums[j]);
                    list.sort(Comparator.naturalOrder());
                    resultSet.add(list);
                } else {
                    map.put(-nums[i] - nums[j], nums[j]);
                }
            }
        }
        return new ArrayList<>(resultSet);
    }




    /**
     * todo: 方法2：双指针逼近法
     * 3个数的查找，想要 O(n)结束战斗肯定是不可能了，
     * 那就可以先排序 O(n*log(n))，再想办法了
     *
     * 排序后，就转化成了{@link Pro167_TwoSumII_InputArrayIsSorted}
     * 中的问题，因此参照它的解决方法即可
     */
    public List<List<Integer>> threeSum2_1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (0 == sum) {
                    //添加到set后，仍要继续搜索其他解
                    set.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    ++l;
                    --r;
                } else if (0 < sum) {
                    --r;
                } else {
                    ++l;
                }
            }
        }
        return new ArrayList<>(set);
    }


    /**
     * todo: 方法2：双指针逼近法（优化）
     * 局部优化，主要是从避免无效计算下手
     */
    public List<List<Integer>> threeSum2_2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1;
            int r = nums.length - 1;

            if (nums[i] > 0) {
                break; //nums[i]>0，则sum肯定>0，后面就不用算了
            }
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue; //前面 i-1时已经算过了
            }

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (0 == sum) {
                    set.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                    while (l < r && nums[l] == nums[l - 1])
                        ++l; //前面 l-1时已经算过了
                    while (l < r && nums[r] == nums[r + 1])
                        --r; //前面 r+1时已经算过了
                } else if (0 < sum) {
                    --r;
                } else {
                    ++l;
                }
            }
        }

        return new ArrayList<>(set);
    }


    public static void main(String[] args) {
        Pro15_3Sum solution = new Pro15_3Sum();

        int [] a = new int[] {-1,0,1,2,-1,-4};
        System.out.println(solution.threeSum1_1(a));
        System.out.println(solution.threeSum1_2(a));
        System.out.println(solution.threeSum2_1(a));
        System.out.println(solution.threeSum2_2(a));
    }
}
