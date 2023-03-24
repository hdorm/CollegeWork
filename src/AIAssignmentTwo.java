import java.util.*;
import java.lang.Math;

public class AIAssignmentTwo {

    //variables used through the program
    public static int UCSNodesVisited = 0;
    public static int DFSNodesVisited = 0;
    public static int BFSNodesVisited = 0;
    public static int AStarNodesVisited = 0;
    public static LinkedList<Integer> solvedList = new LinkedList<>();
    public static LinkedList<Integer> scrambledList = new LinkedList<>();
    public static LinkedList<int[]> manhattanDistance = new LinkedList<>();
    public static String[] positions = {"top-left", "top-mid", "top-right", "mid-left", "mid", "mid-right", "bot-left",
            "bot-mid", "bot-right"};

    //node class which is used throughout the program with necessary attributes and methods
    static class node {
        private final LinkedList<Integer> state;
        private node parent;
        private String action;
        private int pathCost;
        private int manhattanDistance;

        //used to generate nodes
        public node(LinkedList<Integer> state, node parent, String action, int pathCost, int manhattanDistance) {
            this.state = state;
            this.parent = parent;
            this.action = action;
            this.pathCost = pathCost;
            this.manhattanDistance = manhattanDistance;
        }

        //methods for changing an attribute of a node
        public void setAction(node node, String action) {
            node.action = action;
        }

        public void setPathCost(node node, int pathCost) {
            node.pathCost = pathCost;
        }

        public void setParent(node node, node parent) {
            node.parent = parent;
        }

        public void setManhattanDistance(node node, int manhattanDistance) {
            node.manhattanDistance = manhattanDistance;
        }

        //method for creating a deep copy of a node to prevent messing up a previous node because of references
        public node(node copy) {
            this(deepCopy(copy.state), copy.parent, copy.action, copy.pathCost, copy.manhattanDistance);
        }
    }

    //comparator for uniform-cost search which sorts based on lowest path cost
    static class UCSComparator implements Comparator<node> {
        public int compare(node nodeOne, node nodeTwo) {
            if (nodeOne.pathCost < nodeTwo.pathCost) {
                return -1;
            } else if (nodeOne.pathCost > nodeTwo.pathCost) {
                return 1;
            }
            return 0;
        }
    }

    //comparator for best-first search which sorts based on lowest manhattan distance
    static class BFSComparator implements Comparator<node> {
        public int compare(node nodeOne, node nodeTwo) {
            if (nodeOne.manhattanDistance < nodeTwo.manhattanDistance) {
                return -1;
            } else if (nodeOne.manhattanDistance > nodeTwo.manhattanDistance) {
                return 1;
            }
            return 0;
        }
    }

    //comparator for A* which sorts by lowest combination of pathCost and manhattan distance
    static class AStarComparator implements Comparator<node> {
        public int compare(node nodeOne, node nodeTwo) {
            if ((nodeOne.manhattanDistance + nodeOne.pathCost) < (nodeTwo.manhattanDistance + nodeTwo.pathCost)) {
                return -1;
            } else if ((nodeOne.manhattanDistance + nodeOne.pathCost) > (nodeTwo.manhattanDistance + nodeTwo.pathCost)) {
                return 1;
            }
            return 0;
        }
    }

    //comparator for depth-first search which orders the queue by the node with the highest pathCost, i.e. the deepest
    static class DFSComparator implements Comparator<node> {
        public int compare(node nodeOne, node nodeTwo) {
            if (nodeOne.pathCost < nodeTwo.pathCost) {
                return 1;
            } else if (nodeOne.pathCost > nodeTwo.pathCost) {
                return -1;
            }
            return 0;
        }
    }

    //generates the manhattan score of a current node's square state to be used by best-first search and A*
    public static int BFSHeuristicScore(node node) {
        int score = 0;
        node tempNode = new node(node);
        LinkedList<Integer> tempList = deepCopy(tempNode.state);
        for (int i = 0; i < tempList.size(); i++) {
            int[] coordinateArray = manhattanDistance.get(findCorrectPosition(tempList.get(i)));
            score += Math.abs(manhattanDistance.get(i)[0] - coordinateArray[0]) + Math.abs(manhattanDistance.get(i)[1] - coordinateArray[1]);
        }
        return score;
    }

    //used to check whether two lists are identical
    public static boolean equalCheck(LinkedList<Integer> original, LinkedList<Integer> current) {
        return original.equals(current);
    }

    //used to produce a deep copy of a list to prevent unwanted changes to references of an older list
    public static LinkedList<Integer> deepCopy(LinkedList<Integer> list) {
        LinkedList<Integer> copiedList = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            copiedList.add(list.get(i));
        }
        return copiedList;
    }

    //randomized one of the lists to be used by the algorithms
    public static LinkedList<Integer> randomizeList() {
        Collections.shuffle(scrambledList);
        return scrambledList;
    }

    //adds the correct numbers to the lists
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
        manhattanDistance.add(new int[]{1, 1});
        manhattanDistance.add(new int[]{1, 2});
        manhattanDistance.add(new int[]{1, 3});
        manhattanDistance.add(new int[]{2, 1});
        manhattanDistance.add(new int[]{2, 2});
        manhattanDistance.add(new int[]{2, 3});
        manhattanDistance.add(new int[]{3, 1});
        manhattanDistance.add(new int[]{3, 2});
        manhattanDistance.add(new int[]{3, 3});
    }

    //finds the correct position of the number being passed to it for the manhattan calculation
    public static int findCorrectPosition(int number) {
        int correctPosition = 0;
        for (int i = 0; i < solvedList.size(); i++) {
            if (solvedList.get(i) == number) {
                correctPosition = i;
            }
        }
        return correctPosition;
    }

    //finds the position of zero by looping through the provided list
    public static int positionOfZero(LinkedList<Integer> list) {
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                break;
            }
        }
        return i;
    }

    //generates an array of possible moves depending on the position of the blank square passed to it as an argument
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

    //these methods swap the position of the blank square with one of the squares adjacent to it using some basic math
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

    //prints the square using the linked list in a way that looks good in output
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

    //all of these algorithms function exactly the same except for changes in the comparator being used by the
    //priority queue. they are called by the main method with the randomized square as an argument
    public static int DFS(LinkedList<Integer> list) {
        //a base node is created to represent the starting location of the blank space
        node currentNode = new node(list, null, "Initial", 0, 0);
        //a priority queue is created to hold the frontier and is sorted by the specific comparator for this algorithm
        PriorityQueue<node> frontier = new PriorityQueue<>(new DFSComparator());
        //the base node is added to the frontier
        frontier.add(currentNode);
        //a hashmap is created to hold the nodes that have already been visited with the key being the square states
        Map<LinkedList<Integer>, node> reached = new HashMap<>();
        //the base node is added to the frontier
        reached.put(list, frontier.peek());
        //a linked list is created to hold generated child nodes
        LinkedList<node> expandedNodes;
        //the visited nodes is incremented since we are about to check the base node for a solution
        DFSNodesVisited += 1;
        //this while loop runs until the frontier is emptied
        while (!frontier.isEmpty()) {
            //this calls "equalCheck" which checks whether the current front of queue is equal to the solved square
            if (equalCheck(solvedList, frontier.peek().state)) {
                currentNode = new node(frontier.peek());
                //while loop prints the steps to a solution in reverse by going backwards through the parent nodes
                //and the actions that were taken by them until the base node is reached
                while (currentNode.parent != null) {
                    System.out.println(currentNode.action);
                    currentNode = new node(currentNode.parent);
                }
                //loop is broken since we have found a solution
                break;
                //we keep expanding new nodes if a solution is not found
            } else {
                //the expand method is called with the popped queue item to decrease the size of the frontier and
                //generate new nodes
                expandedNodes = expandFunction(frontier.poll());
                //for loop goes through each generated node, increments the nodes visited, adds reached nodes to
                //the hashmap if they have not been visited before, and adds the node to the frontier to check
                //if it is a solution or generate more child nodes from it
                for (node expandedNode : expandedNodes) {
                    DFSNodesVisited += 1;
                    node tempNode = new node(expandedNode);
                    //this for loop is implemented on every algorithm as a depth limit to keep the algorithms from
                    //going in an infinite loop and/or to prevent the solution from being a mile long list of actions
                    //as the book specifies that the longest solution to an 8 puzzle is 31 moves. so anything deeper
                    //than 31 moves means there is some unnecessary looping involved
                    if (tempNode.pathCost < 32) {
                        if (!reached.containsKey(tempNode.state) || tempNode.pathCost < reached.get(tempNode.state).pathCost) {
                            reached.put(tempNode.state, tempNode);
                            frontier.add(tempNode);
                        }
                    }
                }
                //the expanded nodes are cleared for a new set of nodes to be investigated
                expandedNodes.clear();
            }
        }
        //prints that the puzzle was not solvable if the queue is empty and the while loop has not been broken
        if (frontier.isEmpty()) {
            System.out.println("The puzzle is unsolvable.");
        }
        //returns the number of nodes visited by the algorithm
        return DFSNodesVisited;
    }

    //same as DFS except for using a different comparator
    public static int BFS(LinkedList<Integer> list) {
        node currentNode = new node(list, null, "Initial", 0, 0);
        //different comparator
        PriorityQueue<node> frontier = new PriorityQueue<>(new BFSComparator());
        frontier.add(currentNode);
        Map<LinkedList<Integer>, node> reached = new HashMap<>();
        reached.put(list, frontier.peek());
        LinkedList<node> expandedNodes;
        BFSNodesVisited += 1;
        while (!frontier.isEmpty()) {
            if (equalCheck(solvedList, frontier.peek().state)) {
                currentNode = new node(frontier.peek());
                while (currentNode.parent != null) {
                    System.out.println(currentNode.action);
                    currentNode = new node(currentNode.parent);
                }
                break;
            } else {
                expandedNodes = expandFunction(frontier.poll());
                for (node expandedNode : expandedNodes) {
                    BFSNodesVisited += 1;
                    node tempNode = new node(expandedNode);
                    if (tempNode.pathCost < 32) {
                        if (!reached.containsKey(tempNode.state) || tempNode.pathCost < reached.get(tempNode.state).pathCost) {
                            reached.put(tempNode.state, tempNode);
                            frontier.add(tempNode);
                        }
                    }
                }
                expandedNodes.clear();
            }
        }
        if (frontier.isEmpty()) {
            System.out.println("The puzzle is unsolvable.");
        }
        return BFSNodesVisited;
    }

    //same as DFS except for using a different comparator
    public static int UCS(LinkedList<Integer> list) {
        node currentNode = new node(list, null, "Initial", 0, 0);
        //different comparator
        PriorityQueue<node> frontier = new PriorityQueue<>(new UCSComparator());
        frontier.add(currentNode);
        Map<LinkedList<Integer>, node> reached = new HashMap<>();
        reached.put(list, frontier.peek());
        LinkedList<node> expandedNodes;
        UCSNodesVisited += 1;
        while (!frontier.isEmpty()) {
            if (equalCheck(solvedList, frontier.peek().state)) {
                currentNode = new node(frontier.peek());
                while (currentNode.parent != null) {
                    System.out.println(currentNode.action);
                    currentNode = new node(currentNode.parent);
                }
                break;
            } else {
                expandedNodes = expandFunction(frontier.poll());
                for (node expandedNode : expandedNodes) {
                    UCSNodesVisited += 1;
                    node tempNode = new node(expandedNode);
                    if (tempNode.pathCost < 32) {
                        if (!reached.containsKey(tempNode.state) || tempNode.pathCost < reached.get(tempNode.state).pathCost) {
                            reached.put(tempNode.state, tempNode);
                            frontier.add(tempNode);
                        }
                    }
                }
                expandedNodes.clear();
            }
        }
        if (frontier.isEmpty()) {
            System.out.println("The puzzle is unsolvable.");
        }
        return UCSNodesVisited;
    }

    //same as DFS except for using a different comparator
    public static int AStar(LinkedList<Integer> list) {
        node currentNode = new node(list, null, "Initial", 0, 0);
        //different comparator
        PriorityQueue<node> frontier = new PriorityQueue<>(new AStarComparator());
        frontier.add(currentNode);
        Map<LinkedList<Integer>, node> reached = new HashMap<>();
        reached.put(list, frontier.peek());
        LinkedList<node> expandedNodes;
        AStarNodesVisited += 1;
        while (!frontier.isEmpty()) {
            if (equalCheck(solvedList, frontier.peek().state)) {
                currentNode = new node(frontier.peek());
                while (currentNode.parent != null) {
                    System.out.println(currentNode.action);
                    currentNode = new node(currentNode.parent);
                }
                break;
            } else {
                expandedNodes = expandFunction(frontier.poll());
                for (node expandedNode : expandedNodes) {
                    AStarNodesVisited += 1;
                    node tempNode = new node(expandedNode);
                    if (tempNode.pathCost < 32) {
                        if (!reached.containsKey(tempNode.state) || tempNode.pathCost < reached.get(tempNode.state).pathCost) {
                            reached.put(tempNode.state, tempNode);
                            frontier.add(tempNode);
                        }
                    }
                }
                expandedNodes.clear();
            }
        }
        if (frontier.isEmpty()) {
            System.out.println("The puzzle is unsolvable.");
        }
        return AStarNodesVisited;
    }

    //expand function which is used by each algorithm to generate child nodes of each node currently being evaluated
    public static LinkedList<node> expandFunction(final node parentNode) {
        //creates a linked list to be used to store nodes that need evaluating
        LinkedList<node> expandedNodes = new LinkedList<>();
        //creates integer array to store ways the current empty square can be moved
        Integer[] availableMoves;
        //uses the "possibleMoves" method to fill the array with the current available moves
        availableMoves = possibleMoves(positionOfZero(parentNode.state));
        //finds the current position of the empty square so the results of the move can be found
        int zeroPosition = positionOfZero(parentNode.state);
        //goes through each possible move and generates a new node based on the effects of the current move
        for (Integer availableMove : availableMoves) {
            //creates a new node with new parent, action being taken, pathCost, manhattanDistance, and square positions
            //and adds them to the array holding child nodes
            if (availableMove == 1) {
                node tempRight = new node(parentNode);
                tempRight.setParent(tempRight, parentNode);
                tempRight.setAction(tempRight, "Right");
                tempRight.setPathCost(tempRight, tempRight.pathCost + 1);
                tempRight.setManhattanDistance(tempRight, BFSHeuristicScore(tempRight));
                swapRight(tempRight.state, zeroPosition);
                expandedNodes.add(new node(tempRight));
            } else if (availableMove == 2) {
                node tempLeft = new node(parentNode);
                tempLeft.setParent(tempLeft, parentNode);
                tempLeft.setAction(tempLeft, "Left");
                tempLeft.setPathCost(tempLeft, tempLeft.pathCost + 1);
                tempLeft.setManhattanDistance(tempLeft, BFSHeuristicScore(tempLeft));
                swapLeft(tempLeft.state, zeroPosition);
                expandedNodes.add(new node(tempLeft));
            } else if (availableMove == 3) {
                node tempUp = new node(parentNode);
                tempUp.setParent(tempUp, parentNode);
                tempUp.setAction(tempUp, "Up");
                tempUp.setPathCost(tempUp, tempUp.pathCost + 1);
                tempUp.setManhattanDistance(tempUp, BFSHeuristicScore(tempUp));
                swapUp(tempUp.state, zeroPosition);
                expandedNodes.add(new node(tempUp));
            } else if (availableMove == 4) {
                node tempDown = new node(parentNode);
                tempDown.setParent(tempDown, parentNode);
                tempDown.setAction(tempDown, "Down");
                tempDown.setPathCost(tempDown, tempDown.pathCost + 1);
                tempDown.setManhattanDistance(tempDown, BFSHeuristicScore(tempDown));
                swapDown(tempDown.state, zeroPosition);
                expandedNodes.add(new node(tempDown));
            }
        }
        //returns the generated child nodes to the algorithm for evaluation
        return expandedNodes;
    }

    // main method which populates the squares, creates a random square, and runs the search algorithms
    public static void main(String[] args) {
        // populates the lists
        populateLists();
        // randomizes one of the lists for the algorithms to solve
        LinkedList<Integer> newList = randomizeList();
        // prints the randomized list that is being solved
        printSquare(deepCopy(scrambledList));
        // runs algorithms and prints the number of nodes visited by each algorithm
        System.out.println("DFS Results:");
        System.out.println("Nodes visited: " + DFS(deepCopy(newList)));
        System.out.println();
        System.out.println("BFS Results:");
        System.out.println("Nodes visited: " + BFS(deepCopy(newList)));
        System.out.println();
        System.out.println("UCS Results:");
        System.out.println("Nodes visited: " + UCS(deepCopy(newList)));
        System.out.println();
        System.out.println("A* Results:");
        System.out.println("Nodes visited: " + AStar(deepCopy(newList)));
    }
}
