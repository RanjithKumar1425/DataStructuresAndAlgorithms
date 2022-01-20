package my.learning.dsa.p04;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        MedianOfTwoSortedArrays problem = new MedianOfTwoSortedArrays();
        int[] num1 = new int[]{1, 2};
        int[] num2 = new int[]{3, 4};
        System.out.println(problem.findMedianSortedArrays(num1, num2));
    }

    /**
     * Brute Force Solution - Time Complexity O(N+M), Space Complexity O(N+M)
     * Construct the new array based on the sorted array and find the median.
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] num3 = new int[nums1.length + nums2.length];

        int i = 0, j = 0, k = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                num3[k++] = nums1[i++];
            } else {
                num3[k++] = nums2[j++];
            }
        }
        while (i < nums1.length) {
            num3[k++] = nums1[i++];
        }
        while (j < nums2.length) {
            num3[k++] = nums2[j++];
        }

        if (num3.length % 2 == 0) {
            return (num3[(num3.length + 1) / 2] + num3[(num3.length - 1) / 2]) / 2.0d;
        } else {
            return num3[num3.length / 2];
        }
    }
}
