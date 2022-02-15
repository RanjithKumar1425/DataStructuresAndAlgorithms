package my.learning.dsa.p46;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        Permutations p = new Permutations();
        System.out.println(p.permute(new int[]{1, 2, 3})); // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        System.out.println(p.permute(new int[]{0, 1})); // [[0,1],[1,0]]
        System.out.println(p.permute(new int[]{1})); // [[1]]
    }

    /** Recursive and Backtracking
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, 0, new ArrayList<Integer>(), result);
        return result;

    }

    private void permute(int[] nums, int index, ArrayList<Integer> entry, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(entry));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            entry.add(nums[i]);
            swap(nums, i, index);
            permute(nums, index + 1, entry, result);
            swap(nums, i, index);
            entry.remove(entry.size() - 1);
        }

    }

    private void swap(int[] nums, int i, int index) {
        int t = nums[i];
        nums[i] = nums[index];
        nums[index] = t;
    }

}
