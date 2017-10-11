package com.example.myapplication.Utils;

public class Base64Decode {
    private static final int DF[] = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1,
            -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    };
    private static final int SKIP = -1;
    private static final int EQUALS = -2;

    private Base64Decode() {
    }

    public static byte[] decode(String str) {
        int p = 0;
        int state = 0;
        int value = 0;
        int op = 0;
        byte[] in = str.getBytes();
        int len = in.length;
        byte[] output = new byte[len * 3 / 4];

        while (p < len) {
            if (state == 0) {
                while (p + 4 <= len
                        && (value = ((DF[in[p] & 0xff] << 18) | (DF[in[p + 1] & 0xff] << 12)
                                | (DF[in[p + 2] & 0xff] << 6) | (DF[in[p + 3] & 0xff]))) >= 0) {
                    output[op + 2] = (byte) value;
                    output[op + 1] = (byte) (value >> 8);
                    output[op] = (byte) (value >> 16);
                    op += 3;
                    p += 4;
                }
                if (p >= len)
                    break;
            }

            int d = DF[in[p++] & 0xff];

            switch (state) {
                case 0:
                    if (d >= 0) {
                        value = d;
                        ++state;
                    } else if (d != SKIP) {
                        throw new IllegalArgumentException("bad base-64Decode");
                    }
                    break;
                case 1:
                    if (d >= 0) {
                        value = (value << 6) | d;
                        ++state;
                    } else if (d != SKIP) {
                        throw new IllegalArgumentException("bad base-64Decode");
                    }
                    break;
                case 2:
                    if (d >= 0) {
                        value = (value << 6) | d;
                        ++state;
                    } else if (d == EQUALS) {
                        output[op++] = (byte) (value >> 4);
                        state = 4;
                    } else if (d != SKIP) {
                        throw new IllegalArgumentException("bad base-64Decode");
                    }
                    break;
                case 3:
                    if (d >= 0) {
                        value = (value << 6) | d;
                        output[op + 2] = (byte) value;
                        output[op + 1] = (byte) (value >> 8);
                        output[op] = (byte) (value >> 16);
                        op += 3;
                        state = 0;
                    } else if (d == EQUALS) {
                        output[op + 1] = (byte) (value >> 2);
                        output[op] = (byte) (value >> 10);
                        op += 2;
                        state = 5;
                    } else if (d != SKIP) {
                        throw new IllegalArgumentException("bad base-64Decode");
                    }
                    break;
                case 4:
                    if (d == EQUALS)
                        ++state;
                    else if (d != SKIP)
                        throw new IllegalArgumentException("bad base-64Decode");
                    break;
                case 5:
                    if (d != SKIP)
                        throw new IllegalArgumentException("bad base-64Decode");
                    break;
            }
        }

        switch (state) {
            case 1:
            case 4:
                throw new IllegalArgumentException("bad base-64Decode");
            case 2:
                output[op++] = (byte) (value >> 4);
                break;
            case 3:
                output[op++] = (byte) (value >> 10);
                output[op++] = (byte) (value >> 2);
                break;
        }
        if (op != output.length) {
            byte[] temp = new byte[op];
            System.arraycopy(output, 0, temp, 0, op);
            return temp;
        }
        return output;
    }
}
