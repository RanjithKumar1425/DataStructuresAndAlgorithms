package my.learning.dsa.p26;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray solution = new RemoveDuplicatesFromSortedArray();
        System.out.println(solution.removeDuplicates(new int[]{1, 1, 2})); // 2
        System.out.println(solution.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4})); // 5
    }

    public int removeDuplicates(int[] nums) {
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                nums[count++] = nums[i];
                count++;
            }
        }
        return count;
    }

}
