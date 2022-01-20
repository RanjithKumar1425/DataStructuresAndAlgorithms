package my.learning.dsa.p12;

public class IntegerToRoman {

    enum Roman {
        M("M", 1000),
        CM("CM", 900),
        D("D", 500),
        CD("CD", 400),
        C("C", 100),
        XC("XC", 90),
        L("L", 50),
        XL("XL", 40),
        X("X", 10),
        IX("IX", 9),
        V("V", 5),
        IV("IV", 4),
        I("I", 1);

        String rNum;
        int num;

        Roman(String rNum, int num) {
            this.rNum = rNum;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        IntegerToRoman itr = new IntegerToRoman();
        System.out.println(itr.intToRoman(1)); // I
        System.out.println(itr.intToRoman(4)); // IV
        System.out.println(itr.intToRoman(6)); // VI
        System.out.println(itr.intToRoman(10)); // X
        System.out.println(itr.intToRoman(58)); // LVIII
        System.out.println(itr.intToRoman(1994)); // MCMXCIV
    }

    /**
     * Iterate over the roman number from higher to lower including the edge cases.
     * Append roman number based on no of digits need to be added.
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        for (Roman r: Roman.values()) {
                int noofdig = num / r.num;
                sb.append(r.rNum.repeat(noofdig));
                num%=r.num;
        }
        return sb.toString();
    }
}
