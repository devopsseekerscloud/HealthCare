package lk.ijse.healthcare.dao;

public class Test {
    public static void main(String[] args) {
        int ans1 = calc(10, 20); // [10,20]
        int ans2 = calc(10, 20, 30); //[10,20,30]
    }

    static int calc(int... params) {
        int ans = 0;
        for (int i = 0; i < params.length; i++) {
            ans += params[i];
        }
        return ans;
    }

}
