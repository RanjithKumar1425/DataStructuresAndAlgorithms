package my.learning.dsa.p15;

import kotlin.Pair;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        System.out.println(ts.threeSum(new int[]{-1, 0, 1, 2, -1, -4})); //[[-1,-1,2],[-1,0,1]]
    }

    /**
     * Sort and Two Pointer Methods. Time Complexity - O(N^2) Space Complexity - O(N)
     *
     * 1. Sort the Input Array
     * 2. Loop through 0..n-2
     * 3. Ignore the number if the number is duplicate.
     * 4. Initialize left = i and right = n-1 . sum = 0 - num[i]
     * 5. Loop until left < right
     * 5.1  num(left) + num(right) == sum // combination found
     * Increase left pointer if next num is same.
     * Decrease right pointer if next right is same.
     * To Continue further we increase left and decrease right pointer.
     * 5.2  num(left) + num(right) > sum // decrease right pointer
     * 5.3 increase left pointer
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum_1(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Check if the Current int is duplicate or not.
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {

                int left = i + 1, right = nums.length - 1, sum = 0 - nums[i];

                while (left < right) {
                    int tsum = nums[left] + nums[right];
                    if (tsum == sum) { // Combination found
                        result.add(Arrays.asList(nums[i], nums[left], nums[right]));
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

        return result;
    }


    /**
     * Hashing Method . Time Complexity :  O(N^2) Space Complexity : O(N^2)
     *
     * Have two Set and HashMap
     *
     * First set used to Store and Check Duplicate First number in triplet
     * Second set used to store the unique sequence.
     *
     * HashMap is used to store visited number and current processing I index
     *
     * Run two Loops , Calulcate complement of two number at i & j.
     * Check if Complement key existing in Hashmap and index maches.
     * To check uniqueness , we find min and max on three no. and add then to Second set.
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        HashSet<Integer> firstInTriplet = new HashSet<>();
        HashMap<Integer, Integer> visitedNumsNIndex = new HashMap<>();
        HashSet<Pair> uniqueness = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (firstInTriplet.add(nums[i])) { // Check if num is already processed and ignore.
                for (int j = i + 1; j < nums.length; j++) {
                    int x = -(nums[i] + nums[j]);
                    if (visitedNumsNIndex.containsKey(x) && visitedNumsNIndex.get(x) == i) {
                        //Calculate Min and Max of three no, which can be used to check if sequence is already processed.
                        int n1 = Math.min(nums[i], Math.min(nums[j], x));
                        int n2 = Math.max(nums[i], Math.max(nums[j], x));

                        if (uniqueness.add(new Pair(n1, n2))) { // Check if Sequence is already processed.
                            result.add(Arrays.asList(nums[i], x, nums[j]));
                        }
                    }
                    visitedNumsNIndex.put(nums[j], i);
                }
            }
        }
        return result;
    }
}
