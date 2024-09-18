package boj;

import java.io.*;
import java.util.*;

class Boj21276 {

    static int n, m;
    static Map<String, List<String>> graph = new HashMap<>();
    static Map<String, List<String>> family = new HashMap<>();
    static Map<String, Integer> inDegree = new HashMap<>();
    static List<String> roots = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String name = st.nextToken();
            family.put(name, new ArrayList<>());
            graph.put(name, new ArrayList<>());
            inDegree.put(name, 0);
        }
        m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();
            String parent = st.nextToken();
            inDegree.put(child, inDegree.get(child) + 1);
            graph.get(parent).add(child);
        }

        Queue<String> que = new ArrayDeque<>();
        for (String key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                que.offer(key);
                roots.add(key);
            }
        }
        roots.sort(Comparator.naturalOrder());

        while (!que.isEmpty()) {
            String parent = que.poll();

            for (String child : graph.get(parent)) {
                int count = inDegree.get(child);
                count--;
                if (count == 0) {
                    family.get(parent).add(child);
                    que.offer(child);
                    inDegree.remove(child);
                } else {
                    inDegree.put(child, count);
                }
            }
        }
        StringBuilder answer = new StringBuilder();
        answer.append(roots.size()).append("\n");
        for (String root : roots) {
            answer.append(root).append(" ");
        }
        answer.append("\n");
        List<String> names = new ArrayList<>(family.keySet());
        names.sort(Comparator.naturalOrder());

        for (String name : names) {
            answer.append(name).append(" ");
            List<String> children = family.get(name);
            if (children.isEmpty()) {
                answer.append("0\n");
            } else {
                children.sort(Comparator.naturalOrder());
                answer.append(children.size()).append(" ");
                for (String child : children) {
                    answer.append(child).append(" ");
                }
                answer.append("\n");
            }
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
    }
}
