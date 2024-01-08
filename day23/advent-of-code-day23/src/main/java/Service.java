import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final List<List<Character>> board = new ArrayList<>();
    private Cord start;
    private Cord end;

    private final List<Cord> nodes = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();

    private int maxLength = -1;
    public void readInput(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                for (Character c: line.toCharArray()) {
                    row.add(c);
                }
                board.add(row);
            }
            findStartEnd();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path, ioe);
        }
    }

    private void findStartEnd() {
        for (int x = 0; x < board.getFirst().size(); x++) {
            if (board.getFirst().get(x) == '.') {
                start = new Cord(x, 0);
            }

            if (board.getLast().get(x) == '.') {
                end = new Cord(x, board.size()-1);
            }
        }
    }

    private List<Cord> findNextMoves(Cord defPos, int part) {
        Character defSign = board.get(defPos.getPosY()).get(defPos.getPosX());
        List<Cord> nextCords = new ArrayList<>();

        Cord up = defPos.addCord(Direction.UP.getCord());
        Cord down = defPos.addCord(Direction.DOWN.getCord());
        Cord left = defPos.addCord(Direction.LEFT.getCord());
        Cord right = defPos.addCord(Direction.RIGHT.getCord());

        if (part == 1) {

            switch (defSign) {
                case '>':
                    nextCords.add(right);
                    break;
                case '<':
                    nextCords.add(left);
                    break;
                case 'v':
                    nextCords.add(down);
                    break;
                case '^':
                    nextCords.add(up);
                    break;
                default:
                    checkSpaceOnBoard(up, down, left, right, nextCords);
                    break;
            }
        } else {
            checkSpaceOnBoard(up, down, left, right, nextCords);
        }
        return nextCords;
    }

    private void findNodes(int part) {
        for (int y = 0; y < board.size(); y++) {
            for (int x = 0; x < board.getFirst().size(); x++) {
                if (!board.get(y).get(x).equals('#')) {
                    Cord defCord = new Cord(x,y);
                    if (findNextMoves(defCord, part).size() > 2) {
                        nodes.add(defCord);
                    }
                }
            }
        }
    }

    private void findEdge(Cord startNode, Cord defNode, List<Cord> seen, int counter, int part) {
        if (!defNode.equals(startNode) && nodes.contains(defNode)) {
            edges.add(new Edge(startNode, defNode, counter));
            return;
        }
        List<Cord> nextMoves = findNextMoves(defNode, part);
        seen.add(defNode);
        counter++;
        for (Cord nextMove: nextMoves) {
            if (!seen.contains(nextMove)) {
                findEdge(startNode, nextMove, seen, counter, part);
            }
        }
    }
    private void findAllEdges(int part) {
        for (Cord node: nodes) {
            findEdge(node, node,new ArrayList<>(),0, part);
        }
    }

    private void findPath(Cord defNode, List<Edge> path, List<Cord> seen) {
        if (defNode.equals(nodes.getLast())) {
            maxLength = Math.max(maxLength,
                    path.stream()
                            .mapToInt(Edge::getLength)
                            .sum()
            );
            return;
        }

        for (Edge edge: edges) {
            if (edge.getStartNode().equals(defNode) &&
                    !path.contains(edge) &&
                    !seen.contains(edge.getEndNode())) {
                List<Edge> defPath = new ArrayList<>(path);
                defPath.add(edge);
                List<Cord> defSeen = new ArrayList<>(seen);
                defSeen.add(edge.getStartNode());
                defSeen.add(edge.getEndNode());
                findPath(edge.getEndNode(), defPath, defSeen);
            }
        }
    }

    private void checkSpaceOnBoard(Cord up, Cord down, Cord left, Cord right, List<Cord> nextCords) {
        List<Cord> directions = new ArrayList<>();
        directions.add(up);
        directions.add(down);
        directions.add(left);
        directions.add(right);

        for (Cord d: directions) {
            if (d.getPosX() >= 0 && d.getPosX() < board.getFirst().size() &&
                    d.getPosY() >= 0 && d.getPosY() < board.size() &&
                    board.get(d.getPosY()).get(d.getPosX()) != '#') {
                nextCords.add(d);
            }
        }
    }

    public long partOne() {
        nodes.add(start);
        findNodes(1);
        nodes.add(end);
        findAllEdges(1);
        findPath(nodes.getFirst(), new ArrayList<>(), new ArrayList<>());

        return maxLength;
    }
    public int partTwo() {
        nodes.clear();
        edges.clear();
        maxLength = 0;

        nodes.add(start);
        findNodes(2);
        nodes.add(end);
        findAllEdges(2);
        findPath(nodes.getFirst(), new ArrayList<>(), new ArrayList<>());

        return maxLength;
    }
}
