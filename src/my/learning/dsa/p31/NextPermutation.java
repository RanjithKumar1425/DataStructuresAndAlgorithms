package my.learning.dsa.p31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class NextPermutation {
    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] inp = new int[]{1, 2, 3};
        np.nextPermutation(inp);
        System.out.println(Arrays.toString(inp)); // [1,3,2]
        inp = new int[]{3, 2, 1};
        np.nextPermutation(inp);
        System.out.println(Arrays.toString(inp)); // [1,2,3]
        inp = new int[]{1, 1, 5};
        np.nextPermutation(inp);
        System.out.println(Arrays.toString(inp)); // [1,5,1]
    }

    /**
     * BruteForce Approach
     * <p>
     * 1. Generate all permutation for the given array of integers
     * 2. Sort all the permutation.
     * 3. Initialize a counter for tracking loop 3 and 4
     * 4. Loop until the permutation matches the input array of integers
     * 5. Loop until the permutation does match the input array of integers.
     * 6. if counter is equal to size of all permutation. set the value to ZERO
     * 7. Copy the result permutation at counter to input array.
     *
     * @param nums
     */
    public void nextPermutation_1(int[] nums) {
        ArrayList<int[]> result = new ArrayList<>();
        nextPermutation_bf(0, nums, result);
        result.sort(Comparator.comparing(Arrays::toString));
        String oriNumStr = Arrays.toString(nums);
        int i = 0;
        while (i < result.size()) {
            if (oriNumStr.equals(Arrays.toString(result.get(i)))) {
                break;
            }
            i++;
        }
        while (i < result.size() && oriNumStr.equals(Arrays.toString(result.get(i)))) {
            i++;
        }
        if (i == result.size()) i = 0;
        System.arraycopy(result.get(i), 0, nums, 0, nums.length);
    }

    /**
     * Single Pass Approach
     *
     * 1. Initialize the index to length -2
     * 2. Iterate until the index is greater that or equal to ZERO and nums[index] is greater than nums[index+1]
     * 3. FInd the next number greater tha number at index and swap the numbers
     * 4. Reverse the remaining number to get the next number in permutation
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int index = nums.length - 2;
        while (index >= 0 && nums[index + 1] <= nums[index]) index--;

        if (index >= 0) {
            int nextMaxIndex = nums.length - 1;
            while (nums[nextMaxIndex] <= nums[index]) {
                nextMaxIndex--;
            }
            swap(nums, index, nextMaxIndex);
        }
        reverse(nums, index + 1);
    }

    private void reverse(int[] nums, int i) {
        int start = i, end = nums.length - 1;
        while (end > start) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private void nextPermutation_bf(int index, int[] nums, ArrayList<int[]> result) {
        if (index == nums.length) {
            result.add(nums.clone());
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            nextPermutation_bf(index + 1, nums, result);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int one, int two) {
        int t = nums[one];
        nums[one] = nums[two];
        nums[two] = t;
    }
}
