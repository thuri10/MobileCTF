package com.example.test.ctf02;

/* loaded from: classes.dex */
public class Check {
    public boolean checkPassword(String str) {
        char[] pass = str.toCharArray();
        if (pass.length != 12) {
            return false;
        }
        for (int len = 0; len < pass.length; len++) {
            pass[len] = (char) (((255 - len) - 100) - pass[len]);
            if (pass[len] != '0' || len >= 12) {
                return false;
            }
        }
        return true;
    }
}
