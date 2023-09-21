package boj;

import java.io.*;
import java.util.*;

class Boj22860 {
    private static final int FOLDER = 1;
    private static final int FILE = 0;

    static class File {
        int type;
        String name;

        File(int type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    private static int fileCount;
    private static Map<String, List<File>> allFiles = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String child = st.nextToken();
            int type = Integer.parseInt(st.nextToken());
            if (allFiles.containsKey(parent)) {
                allFiles.get(parent).add(new File(type, child));
            } else {
                List<File> files = new ArrayList<>();
                files.add(new File(type, child));
                allFiles.put(parent, files);
            }
        }
        int queryCount = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        while (queryCount-- > 0) {
            fileCount = 0;
            String path = br.readLine();
            int index = path.lastIndexOf("/");
            if (index != -1) {
                path = path.substring(index + 1);
            }
            Set<String> fileTypes = new HashSet<>();
            if (allFiles.containsKey(path)) {
                for (File file : allFiles.get(path)) {
                    searchFolder(file, fileTypes);
                }
                result.append(fileTypes.size() + " " + fileCount).append("\n");
            } else {
                result.append("0 0\n");
            }
        }

        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static void searchFolder(File file, Set<String> fileTypes) {
        if (file.type == FOLDER) {
            if (allFiles.containsKey(file.name)) {
                for (File nextFile : allFiles.get(file.name)) {
                    searchFolder(nextFile, fileTypes);
                }
            }
        } else {
            fileCount++;
            fileTypes.add(file.name);
        }
    }
}