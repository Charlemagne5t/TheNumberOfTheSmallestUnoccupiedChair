import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[][] tf = new int[times.length][3];
        for (int i = 0; i < times.length; i++) {
            tf[i][0] = times[i][0];
            tf[i][1] = times[i][1];
            tf[i][2] = i;
        }
        Arrays.sort(tf, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> vacant = new PriorityQueue<>();

        int c = 0;

        for (int i = 0; i < tf.length; i++) {
            int start = tf[i][0];
            int end = tf[i][1];
            int friend = tf[i][2];

            while (!pq.isEmpty() && pq.peek()[1] <= start) {
                vacant.offer(pq.poll()[0]);
            }
            if (vacant.isEmpty()) {
                pq.offer(new int[]{c, end});
                if (friend == targetFriend) {
                    return c;
                }
                c++;
            } else {
                int chair = vacant.poll();
                if (friend == targetFriend) {
                    return chair;
                }
                pq.offer(new int[]{chair, end});
            }
        }

        return -1;
    }
}