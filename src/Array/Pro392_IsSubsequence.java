package Array;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符
 * 而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 *  
 *
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 *
 *
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 *  
 *
 * 提示：
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 */
public class Pro392_IsSubsequence {

    /**
     * 就主串从左往右，一个一个找和字串对应的
     */
    public boolean isSubsequence(String s, String t) {
        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();
        int is = 0, jt = 0, ls = s.length(), lt = t.length();
        while(is < ls && jt < lt) {
            if (arrS[is] == arrT[jt]) {
                is++;
            }
            jt++;
        }
        return is == ls;
    }

    public static void main(String[] args) {
        String s = "ace", t = "abcde";

        Pro392_IsSubsequence pro = new Pro392_IsSubsequence();
        pro.isSubsequence(s, t);

    }
}
