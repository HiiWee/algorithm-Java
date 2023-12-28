package boj;

/*
    폴더의 포함관계와
    폴더 내의 파일 저장을 분리하여 저장함

    폴더 포함 관계는 트리구조
    폴더 내 파일은, 폴더에 대해 Map 자료구조를 사용하여 O(1)탐색을 할 수 있게끔함
    또한 폴더 내의 파일은 HashSet으로 관리하여 중복을 제거할 수 있다.
*/
import java.io.*;
import java.util.*;

class Boj22861 {

    static class Folder {
        String name;
        List<Folder> sub = new ArrayList<>();

        public Folder(String name) {
            this.name = name;
        }
    }
    static final Map<String, Folder> folders = new HashMap<>();
    static final Map<String, Set<String>> files = new HashMap<>();

    static int n, m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Folder main = new Folder("main");
        files.put(main.name, new HashSet<>());
        folders.put(main.name, main);

        for (int i = 0; i < n + m; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            if (!folders.containsKey(parent)) {
                folders.put(parent, new Folder(parent));
            }
            String child = st.nextToken();
            int type = Integer.parseInt(st.nextToken());

            // 파일이라면 파일에 존재하는지 확인 후 추가함
            if (type == 0) {
                if (files.containsKey(parent)) {
                    files.get(parent).add(child);
                } else {
                    Set<String> curFiles = new HashSet<>();
                    curFiles.add(child);
                    files.put(parent, curFiles);
                }
            } else {
                // 폴더라면 parent, child폴더가 모두 존재하는지 확인 및 생성 이후 트리 관계로 표현
                if (!folders.containsKey(parent)) {
                    folders.put(parent, new Folder(parent));
                }
                // 빈 폴더일 수 있으니 미리 파일 목록 빈 셋으로 초기화
                if (!files.containsKey(parent)) {
                    files.put(parent, new HashSet<>());
                }
                if (!folders.containsKey(child)) {
                    folders.put(child, new Folder(child));
                }
                // 빈 폴더일 수 있으니 미리 파일 목록 빈 셋으로 초기화
                if (!files.containsKey(child)) {
                    files.put(child, new HashSet<>());
                }
                Folder parentFolder = folders.get(parent);
                Folder childFolder = folders.get(child);
                parentFolder.sub.add(childFolder);
            }
        }

        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            String[] aSplit = a.split("/");
            String[] bSplit = b.split("/");

            // 마지막 폴더의 상위 폴더 가져옴
            Folder aParent = folders.get(aSplit[aSplit.length - 2]);
            // 마지막 폴더
            Folder aFolder = folders.get(aSplit[aSplit.length - 1]);
            // 옮길 폴더 가져옴
            Folder bFolder = folders.get(bSplit[bSplit.length - 1]);

            // 폴더 옮기기
            // 1. 기존 상위 폴더에서 옮길 폴더 이동 및 기존 폴더 삭제
            bFolder.sub.addAll(aFolder.sub);
            aParent.sub.remove(aFolder);

            // 2. 파일들을 해당 폴더에서 이동 및 기존 파일 삭제
            Set<String> aFiles = files.get(aFolder.name); // 옮길 파일들
            files.get(bFolder.name).addAll(aFiles); // 파일 옮기기
            files.remove(aFolder.name); // 기존 폴더는 빈 폴더가 되므로 삭제
        }

        int q = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        while (q-- > 0) {
            String[] paths = br.readLine().split("/");
            Folder start = folders.get(paths[paths.length - 1]);
            Set<String> uniqueFiles = new HashSet<>();
            int totalCount = search(start, uniqueFiles);
            result.append(uniqueFiles.size()).append(" ").append(totalCount).append("\n");
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    public static int search(Folder curFolder, Set<String> uniqueFiles) {
        int count = files.get(curFolder.name).size();
        uniqueFiles.addAll(files.get(curFolder.name));
        for (Folder nextFolder : curFolder.sub) {
            count += search(nextFolder, uniqueFiles);
        }
        return count;
    }
}