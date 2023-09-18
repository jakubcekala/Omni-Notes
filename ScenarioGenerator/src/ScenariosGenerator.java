import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ScenariosGenerator {

    private List<Integer> path;

    public static Map<Integer, String> nodesNames;
    static {
        nodesNames = new HashMap<>();
        nodesNames.put(0, "START");
        nodesNames.put(1, "Create a new Text note");
        nodesNames.put(2, "Create a new Checklist note");
        nodesNames.put(3, "View an existing note");
        nodesNames.put(4, "Manage notes' groups");
        nodesNames.put(5, "Remove a note");
        nodesNames.put(6, "Archive a note");
        nodesNames.put(7, "Enter note details");
        nodesNames.put(8, "Edit note details");
        nodesNames.put(9, "Validate note content");
        nodesNames.put(10, "Edit note tags");
        nodesNames.put(11, "Add a new group");
        nodesNames.put(12, "Remove an existing group");
        nodesNames.put(13, "Validate note content");
        nodesNames.put(14, "END");
    }


    private final int[][] nodesDependencies = {
            {1,2,3,5,6},  //<- Node 0
            {7,9},          //<- Node 1
            {7,9},          //<- Node 2
            {4,8,14},         // ...
            {11,12,14},
            {14},
            {14},
            {9},
            {10,13},
            {14},
            {13},
            {14},
            {14},
            {14}
    };

    private ArrayList<List<Integer>> graphData = new ArrayList<>();

    public void initGraphData() {
        for (int i = 0; i < nodesDependencies.length; i++) {
            graphData.add(i, new ArrayList<Integer>());
            for (int j = 0; j < nodesDependencies[i].length; j++) {
                graphData.get(i).add(nodesDependencies[i][j]);
            }
        }
    }

    public void findAllPathsInGraph(List<List<Integer>> graph, int src, int dst) {
        path = new ArrayList<Integer>();
        path.add(src);
        DFS(graph, src, dst, path);
    }

    public void DFS(List<List<Integer>> graph, int src , int dst, List<Integer> path) {

        if (src == dst) {
            for (Integer node : path) {
                System.out.print(nodesNames.get(node));
                if (node != nodesNames.size() - 1) {
                    System.out.print(" -> ");
                } else {
                    System.out.println();
                }
            }
        } else {
            for (Integer adjnode : graph.get(src)) {
                path.add(adjnode);
                DFS(graph, adjnode, dst, path);
                path.remove(path.size()-1);
            }
        }
    }

    public static void main (String[] args) {
        ScenariosGenerator generator = new ScenariosGenerator();
        generator.initGraphData();
        generator.findAllPathsInGraph(generator.graphData, 0, generator.nodesDependencies.length);
    }
}