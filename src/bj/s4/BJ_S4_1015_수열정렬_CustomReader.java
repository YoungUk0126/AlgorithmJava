package bj.s4;

import java.util.*;

public class BJ_S4_1015_수열정렬_CustomReader {
    static Reader in = new Reader();
    static StringBuilder builder = new StringBuilder();

    static ArrayList<Queue> arr = new ArrayList<>();
    static int[] sortedSequence;
    static int[] sequence;
    static int N;

    public static void main(String[] args) throws Exception {
        N = in.nextInt();
        sequence = new int[N];
        sortedSequence = new int[N];
        for(int i=0; i<=1000; i++) {
            arr.add(new ArrayDeque());
        }

        for(int i=0; i<N; i++) {
            sequence[i] = in.nextInt();
            sortedSequence[i] = sequence[i];
        }
        Arrays.sort(sortedSequence);

        for(int i=0; i<N; i++) {
            arr.get(sortedSequence[i]).offer(i);
        }

        for(int num: sequence) {
            builder.append(arr.get(num).poll()).append(" ");
        }
        System.out.println(builder);
    }

    static class Reader {
        final int SIZE = 1 << 13;
        byte[] buffer = new byte[SIZE];
        int index, size;

        int nextInt() throws Exception {
            int n = 0;
            byte c;
            while ((c = read()) <= 32);
            do n = (n << 3) + (n << 1) + (c & 15);
            while (isNumber(c = read()));
            return n;
        }

        boolean isNumber(byte c) {
            return 47 < c && c < 58;
        }

        byte read() throws Exception {
            if (index == size) {
                size = System.in.read(buffer, index = 0, SIZE);
                if (size < 0)
                    buffer[0] = -1;
            }
            return buffer[index++];
        }
    }
}
