package my.learning.dsa.p47;

import java.util.*;

public class PermutationsII {

    public static void main(String[] args) {
        PermutationsII p = new PermutationsII();
        System.out.println(p.permute(new int[]{0, 1, 0, 0, 9}));
        // [[0,0,0,1,9],[0,0,0,9,1],[0,0,1,0,9],[0,0,1,9,0],[0,0,9,0,1],[0,0,9,1,0],
        // [0,1,0,0,9],[0,1,0,9,0],[0,1,9,0,0],[0,9,0,0,1],[0,9,0,1,0],[0,9,1,0,0],
        // [1,0,0,0,9],[1,0,0,9,0],[1,0,9,0,0],[1,9,0,0,0],[9,0,0,0,1],[9,0,0,1,0],[9,0,1,0,0],[9,1,0,0,0]]
        System.out.println(p.permute(new int[]{1, 1, 2})); // [[1,1,2], [1,2,1], [2,1,1]]
        System.out.println(p.permute(new int[]{1, 2, 1})); // [[1,1,2], [1,2,1], [2,1,1]]
        System.out.println(p.permute(new int[]{1, 2, 3})); // [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        System.out.println(p.permute(new int[]{0, 1})); // [[0,1],[1,0]]
        System.out.println(p.permute(new int[]{1})); // [[1]]
    }

    private List<List<Integer>> permute(int[] nums) {
        // needed if result need to be lexicographically sorted
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        HashMap<Integer, Integer> nums_cnt = new HashMap<>();
        for (int num : nums) {
            Integer cnt = nums_cnt.get(num);
            nums_cnt.put(num, cnt == null ? 1 : cnt + 1);
        }

        permute(nums_cnt, nums.length, new LinkedList<Integer>(), result);
        return result;

    }

    private void permute(HashMap<Integer, Integer> nums, int length, LinkedList<Integer> currentResult, List<List<Integer>> results) {
        if (currentResult.size() == length) {
            results.add(new ArrayList<>(currentResult));
            return;
        }

        for (Map.Entry<Integer,Integer> entry : nums.entrySet()) {
            Integer value = entry.getValue();
            if(value == 0) continue;

            Integer key = entry.getKey();
            currentResult.add(key);
            nums.put(key, value -1);

            permute(nums,length,currentResult,results);

            currentResult.removeLast();
            nums.put(key, value);
        }


    }
    
}
