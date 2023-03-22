import java.util.*;

public class AIAssignmentTwo {

    static class node {
        private final LinkedList<Integer> state;
        private node parent;
        private String action;
        private int pathCost;

        public node(LinkedList<Integer> state, node parent, String action, int pathCost) {
            this.state = state;
            this.parent = parent;
            this.action = action;
            this.pathCost = pathCost;
        }

        public void setAction(node node, String action) {
            node.action = action;
        }

        public void setPathCost(node node, int pathCost) {
            node.pathCost = pathCost;
        }

        public void setParent(node node, node parent) {
            node.parent = parent;
        }

        public node(node copy) {
            this(deepCopy(copy.state), copy.parent, copy.action, copy.pathCost);
        }
    }

    public static LinkedList<node> solution = new LinkedList<>();
    public static LinkedList<String> solutionSteps = new LinkedList<>();
    public static LinkedList<Integer> solvedList = new LinkedList<>();
    public static LinkedList<Integer> scrambledList = new LinkedList<>();
    public static String[] positions = {"top-left", "top-mid", "top-right", "mid-left", "mid", "mid-right", "bot-left",
            "bot-mid", "bot-right"};
    public static LinkedList<Integer> solvableList = new LinkedList<>();

    public static boolean equalCheck(LinkedList<Integer> original, LinkedList<Integer> current) {
        return original.equals(current);
    }

    public static LinkedList<Integer> deepCopy(LinkedList<Integer> list) {
        LinkedList<Integer> copiedList = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            copiedList.add(list.get(i));
        }
        return copiedList;
    }

    public static LinkedList<Integer> randomizeList() {
        Collections.shuffle(scrambledList);
        return scrambledList;
    }

    public static void populateLists() {
        solvedList.add(1);
        solvedList.add(2);
        solvedList.add(3);
        solvedList.add(8);
        solvedList.add(0);
        solvedList.add(4);
        solvedList.add(7);
        solvedList.add(6);
        solvedList.add(5);
        scrambledList.add(1);
        scrambledList.add(2);
        scrambledList.add(3);
        scrambledList.add(8);
        scrambledList.add(0);
        scrambledList.add(4);
        scrambledList.add(7);
        scrambledList.add(6);
        scrambledList.add(5);
        solvableList.add(1);
        solvableList.add(3);
        solvableList.add(4);
        solvableList.add(8);
        solvableList.add(0);
        solvableList.add(5);
        solvableList.add(7);
        solvableList.add(2);
        solvableList.add(6);
    }

    public static int positionOfZero(LinkedList<Integer> list) {
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                break;
            }
        }
        return i;
    }

    public static Integer[] possibleMoves(int currentPosition) {
        String position = positions[currentPosition];
        Integer[] possibleMoves;
        if (Objects.equals(position, "top-left")) {
            possibleMoves = new Integer[]{1, 4};
        } else if (Objects.equals(position, "top-mid")) {
            possibleMoves = new Integer[]{1, 2, 4};
        } else if (Objects.equals(position, "top-right")) {
            possibleMoves = new Integer[]{2, 4};
        } else if (Objects.equals(position, "mid-left")) {
            possibleMoves = new Integer[]{1, 3, 4};
        } else if (Objects.equals(position, "mid")) {
            possibleMoves = new Integer[]{1, 2, 3, 4};
        } else if (Objects.equals(position, "mid-right")) {
            possibleMoves = new Integer[]{2, 3, 4};
        } else if (Objects.equals(position, "bot-left")) {
            possibleMoves = new Integer[]{1, 3};
        } else if (Objects.equals(position, "bot-mid")) {
            possibleMoves = new Integer[]{1, 2, 3};
        } else {
            possibleMoves = new Integer[]{2, 3};
        }
        return possibleMoves;
    }

    public static void swapRight(LinkedList<Integer> list, int i) {
        int currentRight = list.get(i + 1);
        list.set(i, currentRight);
        list.set(i + 1, 0);
    }

    public static void swapLeft(LinkedList<Integer> list, int i) {
        int currentLeft = list.get(i - 1);
        list.set(i, currentLeft);
        list.set(i - 1, 0);
    }

    public static void swapUp(LinkedList<Integer> list, int i) {
        int currentUp = list.get(i - 3);
        list.set(i, currentUp);
        list.set(i - 3, 0);
    }

    public static void swapDown(LinkedList<Integer> list, int i) {
        int currentDown = list.get(i + 3);
        list.set(i, currentDown);
        list.set(i + 3, 0);
    }

    public static void printSquare(LinkedList<Integer> list) {
        System.out.println(" _______");
        System.out.print("| ");
        for (int i = 0; i < 9; i++) {
            if (i == 3 || i == 6) {
                System.out.print("|");
                System.out.println();
                System.out.print("| ");
            }
            System.out.print(list.get(i) + " ");
        }
        System.out.print("|");
        System.out.println();
        System.out.print("---------");
        System.out.println();
    }

    public static class bestFirstComparator implements Comparator<node> {
        @Override
        public int compare(node one, node two) {
            return Integer.compare(one.pathCost, two.pathCost);
        }
    }

    public static void BFS(LinkedList<Integer> list) {
        node currentNode = new node(list, null, "Initial", 0);
        Comparator<node> comparator = new bestFirstComparator();
        PriorityQueue<node> frontier = new PriorityQueue<>(comparator);
        frontier.add(currentNode);
        Map<LinkedList<Integer>, node> reached = new HashMap<>();
        reached.put(list, frontier.peek());
        LinkedList<node> expandedNodes;
        while (!frontier.isEmpty()) {
            if (equalCheck(solvedList, frontier.peek().state)) {
                currentNode = new node(frontier.peek());
                printSquare(currentNode.state);
                while (currentNode.parent != null) {
                    System.out.println(currentNode.action);
                    solutionSteps.add(currentNode.action);
                    currentNode = new node(currentNode.parent);
                }
                solution.add(currentNode);
                break;
            } else {
                expandedNodes = expandFunction(frontier.poll());
                for (node expandedNode : expandedNodes) {
                    node tempNode = new node(expandedNode);
                    if (!reached.containsKey(tempNode.state) || tempNode.pathCost < reached.get(tempNode.state).pathCost) {
                        printSquare(tempNode.state);
                        reached.put(tempNode.state, tempNode);
                        frontier.add(tempNode);
                    }
                }
                expandedNodes.clear();
            }
        }
    }

    public static LinkedList<node> expandFunction(final node parentNode) {
        LinkedList<node> expandedNodes = new LinkedList<>();
        Integer[] availableMoves;
        availableMoves = possibleMoves(positionOfZero(parentNode.state));
        int zeroPosition = positionOfZero(parentNode.state);
        for (Integer availableMove : availableMoves) {
            if (availableMove == 1) {
                node tempRight = new node(parentNode);
                tempRight.setParent(tempRight, parentNode);
                tempRight.setAction(tempRight, "Right");
                tempRight.setPathCost(tempRight, tempRight.pathCost + 1);
                swapRight(tempRight.state, zeroPosition);
                expandedNodes.add(new node(tempRight));
            } else if (availableMove == 2) {
                node tempLeft = new node(parentNode);
                tempLeft.setParent(tempLeft, parentNode);
                tempLeft.setAction(tempLeft, "Left");
                tempLeft.setPathCost(tempLeft, tempLeft.pathCost + 1);
                swapLeft(tempLeft.state, zeroPosition);
                expandedNodes.add(new node(tempLeft));
            } else if (availableMove == 3) {
                node tempUp = new node(parentNode);
                tempUp.setParent(tempUp, parentNode);
                tempUp.setAction(tempUp, "Up");
                tempUp.setPathCost(tempUp, tempUp.pathCost + 1);
                swapUp(tempUp.state, zeroPosition);
                expandedNodes.add(new node(tempUp));
            } else if (availableMove == 4) {
                node tempDown = new node(parentNode);
                tempDown.setParent(tempDown, parentNode);
                tempDown.setAction(tempDown, "Down");
                tempDown.setPathCost(tempDown, tempDown.pathCost + 1);
                swapDown(tempDown.state, zeroPosition);
                expandedNodes.add(new node(tempDown));
            }
        }
        return expandedNodes;
    }

    public static void main(String[] args) {
        populateLists();
        LinkedList<Integer> newList = randomizeList();
        LinkedList<Integer> randomizedList = deepCopy(newList);
        printSquare(solvedList);
        BFS((randomizedList));
    }
}
