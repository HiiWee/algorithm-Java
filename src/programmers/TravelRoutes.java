package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TravelRoutes {

    List<String> routes = new ArrayList<>();
    boolean[] visited;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", tickets, 1);
        Collections.sort(routes);
        return routes.get(0).split(" ");
    }

    public void dfs(String currentRoute, String routeSum, String[][] tickets, int count) {
        if (count == tickets.length + 1) {
            routes.add(routeSum);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && currentRoute.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(tickets[i][1], routeSum + " " + tickets[i][1], tickets, count + 1);
                visited[i] = false;
            }
        }
    }
}
