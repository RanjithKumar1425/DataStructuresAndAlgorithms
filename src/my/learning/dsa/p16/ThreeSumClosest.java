package my.learning.dsa.p16;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        ThreeSumClosest tsc = new ThreeSumClosest();
        System.out.println(tsc.threeSumClosest(new int[]{-1, 2, 1, -4}, 1)); // 2
        System.out.println(tsc.threeSumClosest(new int[]{0, 0, 0}, 1)); // 0
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {

        int result = nums[0] + nums[1] + nums[nums.length - 1];
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // Check if the Current int is duplicate or not.
            if (i == 0 || (nums[i] != nums[i - 1])) {
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == target) { // Combination found
                        return sum;
                    } else if (sum > target) { // when sum is greater than target, decrease right index
                        while (left < right && nums[right] == nums[right - 1])
                            right--;
                        right--;
                    } else { // when sum is less tha target , increase left index
                        while (left < right && nums[left] == nums[left + 1])
                            left++; // move left index if next num is same as current
                        left++;
                    }
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                }

            }
        }
        return result;
    }


}
