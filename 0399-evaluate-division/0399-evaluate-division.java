import java.util.*;

class Solution {

    Map<String, Map<String, Double>> graph = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {

        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b, value);
            graph.get(b).put(a, 1.0 / value);
        }

        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);

            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                ans[i] = -1.0;
            } else if (src.equals(dest)) {
                ans[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                ans[i] = dfs(src, dest, 1.0, visited);
            }
        }

        return ans;
    }

    private double dfs(String curr, String target, double product,
                       Set<String> visited) {

        if (curr.equals(target))
            return product;

        visited.add(curr);

        for (String next : graph.get(curr).keySet()) {

            if (!visited.contains(next)) {

                double result = dfs(next, target,
                        product * graph.get(curr).get(next),
                        visited);

                if (result != -1.0)
                    return result;
            }
        }

        return -1.0;
    }
}

        
    
