package my.learning.dsa.p18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static void main(String[] args) {
        FourSum fs = new FourSum();
        System.out.println(fs.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0)); // [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        System.out.println(fs.fourSum(new int[]{2, 2, 2, 2, 2}, 8)); // [[2,2,2,2]]
    }

    /**
     * 3Sum Extension
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum_1(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            // Check if the Current int is duplicate or not.
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                for (int j = i + 1; j < nums.length - 2; j++) {
                    // Check if the Current int is duplicate or not.
                    if (j == i + 1 || (j > i + 1 && nums[j] != nums[j - 1])) {
                        //Two Sum logic follows
                        int left = j + 1, right = nums.length - 1, sum = target - (nums[i] + nums[j]);
                        while (left < right) {
                            int tsum = nums[left] + nums[right];
                            if (tsum == sum) { // Combination found
                                result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                                while (left < right && nums[left] == nums[left + 1])
                                    left++; // move left index if next num is same as current
                                while (left < right && nums[right] == nums[right - 1])
                                    right--;// move right index if next num is same as current
                                right--;
                                left++;
                            } else if (tsum > sum) { // when 2 sum is greater than sum, decrease right index
                                right--;
                            } else { // when 2 sum is less tha sum , increase left index
                                left++;
                            }
                        }

                    }
                }
            }
        }

        return result;

    }


    /**
     * KSum : Generic approach.
     * <p>
     * Ksum can be broken down to K-1 Sum problem and recursively solve the until 2sum.
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = ksum(nums, 0, 4, target);
        return result;
    }

    private List<List<Integer>> ksum(int[] nums, int index, int k, int target) {
        if ((index + k) > nums.length - 1) {
            return null;
        }
        if (k == 2) {
            return twoSum(nums, index, target);
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = index; i < nums.length - k; i++) {
            if (i == index || (nums[i] != nums[i - 1])) {
                List<List<Integer>> kresult = ksum(nums, i + 1, k - 1, target - nums[i]);
                if (kresult != null) {
                    for (List<Integer> kr : kresult) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.addAll(kr);
                        result.add(temp);
                    }

                }
            }
        }
        return result;
    }

    private List<List<Integer>> twoSum(int[] nums, int index, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int left = index, right = nums.length - 1;
        while (left < right) {
            int tsum = nums[left] + nums[right];
            if (tsum == target) {
                result.add(Arrays.asList(nums[left], nums[right]));
                while (left < right && nums[left] == nums[left + 1])
                    left++; // move left index if next num is same as current
                while (left < right && nums[right] == nums[right - 1])
                    right--;// move right index if next num is same as current
                left++;
                right--;
            } else if (tsum > target) { // when 2 sum is greater than sum, decrease right index
                right--;
            } else { // when 2 sum is less tha sum , increase left index
                left++;
            }
        }
        return result;
    }
}
