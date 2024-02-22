package programmers;

import java.util.*;

class 이중우선순위큐 {
    static class Data implements Comparable<Data> {
        int data;
        boolean isPoll;

        public Data(int data) {
            this.data = data;
        }

        @Override
        public int compareTo(Data d) {
            return data - d.data;
        }

        @Override
        public String toString() {
            return "{ data=" + data + ", isPoll=" + isPoll + " }";
        }
    }

    PriorityQueue<Data> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Data> minHeap = new PriorityQueue<>(Comparator.naturalOrder());

    public int[] solution(String[] operations) {

        for (String op : operations) {
            StringTokenizer st = new StringTokenizer(op);
            String symbol = st.nextToken();
            int number = Integer.parseInt(st.nextToken());

            if (symbol.equals("I")) {
                Data data = new Data(number);
                maxHeap.offer(data);
                minHeap.offer(data);
            } else if (number == 1) {
                removeDataFrom(maxHeap);
            } else {
                removeDataFrom(minHeap);
            }
        }

        Data maxData = getDataFrom(maxHeap);
        Data minData = getDataFrom(minHeap);

        if (maxData == null) {
            return new int[2];
        }
        return new int[] {maxData.data, minData.data};
    }

    public void removeDataFrom(PriorityQueue<Data> heap) {
        while (!heap.isEmpty()) {
            Data data = heap.poll();
            if (!data.isPoll) {
                data.isPoll = true;
                break;
            }
        }
    }

    public Data getDataFrom(PriorityQueue<Data> heap) {
        while (!heap.isEmpty()) {
            Data data = heap.poll();
            if (!data.isPoll) {
                return data;
            }
        }
        return null;
    }
}
