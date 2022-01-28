package my.learning.dsa.p27;

import java.util.Arrays;

public class RemoveElement {
    public static void main(String[] args) {
        RemoveElement re = new RemoveElement();
        System.out.println(re.removeElement(new int[]{3, 2, 2, 3}, 3)); // 3
        System.out.println(re.removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));// 5
    }

    /**
     * Interactive and Preserving the Original array order.
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement_1(int[] nums, int val) {

        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index++] = nums[i];
            }
        }
        return index;
    }

    /**
     * Two Pointer approach
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0)
            return 0;
        int first = 0;
        int last = nums.length;
        while (first < last) {
            if (nums[first] == val) {
                nums[first] = nums[last - 1];
                last--;
            } else {
                first++;
            }
        }
        return last;
    }
}
