package my.learning.dsa.p06;

public class ZigzagConversion {

    public static void main(String[] args) {
        ZigzagConversion zzc = new ZigzagConversion();
        System.out.println(zzc.convert("PAYPALISHIRING", 3)); //PAHNAPLSIIGYIR
        System.out.println(zzc.convert("PAYPALISHIRING", 4)); //PINALSIGYAHRPI
        System.out.println(zzc.convert("A", 1)); //A
    }

    /**
     * Time Complexity O(N) and Space Complexity O(N)
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        StringBuilder sb = new StringBuilder();

        int n = s.length();
        int interval = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            int j = i;
            while (j < n) {
                if (interval != 0)
                    sb.append(s.charAt(j));
                j = j + interval;
                if (i != 0 && j < n)
                    sb.append(s.charAt(j));
                j = j + 2 * i;
            }
            interval -= 2;
        }

        return sb.toString();

    }

    /**
     * Time Complexity O(N+M)  and Space Complexity O(2N)
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert_1(String s, int numRows) {

        StringBuilder sb = new StringBuilder();
        StringBuilder[] sb_column = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sb_column[i] = new StringBuilder();
        }
        int l = 0;
        int cln = 0;
        int n = s.length();
        while (l < n) {
            if (cln % 2 == 0) {
                for (int i = 0; i < numRows && l < n; i++) {
                    sb_column[i].append(s.charAt(l));
                    l++;
                }
            } else {
                for (int i = numRows - 2; i > 0 && l < n; i--) {
                    sb_column[i].append(s.charAt(l));
                    l++;
                }
            }
            cln++;
        }

        for (int i = 0; i < numRows; i++) {
            if (sb_column[i] != null) {
                sb.append(sb_column[i].toString());
            }
        }

        return sb.toString();

    }
}
