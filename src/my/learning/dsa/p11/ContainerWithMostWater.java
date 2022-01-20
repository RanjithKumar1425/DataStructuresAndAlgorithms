package my.learning.dsa.p11;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        ContainerWithMostWater cwmw = new ContainerWithMostWater();
        System.out.println(cwmw.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    /**
     * Brute Force approach. Time Complexity : O(n^2)
     * Loop through the array for all combination and find the max.
     *
     * @param height
     * @return
     */
    public int maxArea_1(int[] height) {
        int n = height.length ;
        int max = 0;
        for (int k = 0; k < n; k++) {
            for (int l = k=1; l < n; l++) {
                max = Math.max(max,Math.min(height[k], height[l]) * (l - k));
            }
        }
        return max;
    }

    /**
     * Two pointer method. Time Complexity : O(n)
     * Start by calculating area of max width 0(left pointer and n-1 (right pointer)
     *
     * If height[left pointer] is less than height[right pointer] we move left pointer else right pointer.
     * Reason: Area is calculated by multiplying Minimum on left pointer and right pointer and width,
     *         to find max we have to move towards larger height.
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int max = 0;
        while(i<j){
            max = Math.max(max,Math.min(height[i], height[j]) * (j - i));
            if(height[i]<height[j]){
                i++;
            }else{
                j--;
            }
        }
        return max;
    }
}
