import java.util.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

// Graphs and Spanning Trees Notes:
//   - Constraints for a graph (G) to be called a "Spanning Tree" (G')
//      - Vertices for both G and G' must equal each other
//      - A connected graph can contain more than one spanning tree
//      - Edges of G' = # of G vertices - 1
//      - Must not contain any cycle
//  ?   - If the given graph is a complete graph, then # of possible spanning trees will be V^(V-2)
//      - Spanning trees cannot be disconnected
//      - If there are multiple edges with the same weight, then there is the possibility of having
//      - more than one minimum spanning tree (same lowest cost)
// ? - If cyclic graph (# of vertices = # of edges),  # of possible spanning trees = # of vertices
//   - Minimum Spanning Trees
//      - Minimum -> lowest cost to connect between vertices
//      - Keep track of the total and continue to pick the lowest cost edge
//      - The cheapest edge is always in the minimum spanning tree
//   - 
//   - 

// Kruskal:
//   - Picks the cheapest edge and adds it to the tree, making sure it does not introduce a cycle

// Prims:
//   - Picks a vertex and explores all edges and picks the cheapest one

// Union Find Data Structure:
//   - Using vertices and sets to connect a tree
//   - Keeps track of elements which are split into one or more disjoint sets

// How to implement Union Find in Krsukal's 
// 1. Sort edges in ascending edge weight
// 2. Look through sorted edges and look at the two vertices
//    that the edge belongs to, if they are already unified through another
//    edge, do not unify them. If not, include in MST and unify them
// 3. The process terminates when all vertices have been unified

// Union Operation: To UNIFY two vertices by finding the root 
// vertices of each and if they are different,
// make one of the root vertices the parent of the other

// STEPS:
// 1. Create graph with vertices and edges connected
// 2. Assign random weight to each edge
// 3. Apply Kruskal's algorithm picking the edge with the smallest weight
// 4. Create the minimum spanning tree
// 5. Render and draw walls at the end of a tree branch, where there is no edge connecting the two

class Maze extends World {
  // FIELDS

  int boardWidth;
  int boardHeight;
  int tileSize;
  int rows;
  int columns;
  int counter;

  Random rand;

  Vertex start;
  Vertex end;

  ArrayList<ArrayList<Vertex>> board;
  HashMap<Vertex, Vertex> hm;
  ArrayList<Edge> edgeList;
  ArrayList<Vertex> workList;
  ArrayList<Vertex> visited;
  ArrayList<Vertex> path;

  WorldScene sc;

  // CONSTRUCTORS

  // Initializes an empty Maze with a random seed
  Maze(int boardWidth, int boardHeight, int tileSize, int seed) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    this.tileSize = tileSize;
    this.rand = new Random();
    this.rand.setSeed(seed);
    this.rows = boardHeight / tileSize;
    this.columns = boardWidth / tileSize;

    this.counter = 0;

    this.board = new ArrayList<ArrayList<Vertex>>();

    this.hm = new HashMap<Vertex, Vertex>();

    this.edgeList = new ArrayList<Edge>();

    this.visited = new ArrayList<Vertex>();
    this.workList = new ArrayList<Vertex>();
    this.path = new ArrayList<Vertex>();

    this.createMaze();
    this.sc = this.drawMaze();

    this.start = this.board.get(0).get(0);
    this.end = this.board.get(this.board.size() - 1).get(this.board.get(0).size() - 1);

    if (this.tileSize > this.boardWidth) {
      throw new IllegalArgumentException(
          "Your tile size cannot be larger than the width of the board.");
    }

    if (this.tileSize > this.boardHeight) {
      throw new IllegalArgumentException(
          "Your tile size cannot be larger than the height of the board.");
    }

    if (this.boardWidth % this.tileSize != 0 && this.boardHeight % this.tileSize != 0) {
      throw new IllegalArgumentException("Your tile size does not fit properly in your board.");
    }
  }

  // Initializes an empty Maze
  Maze(int boardWidth, int boardHeight, int tileSize) {
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    this.tileSize = tileSize;
    this.rows = boardHeight / tileSize;
    this.columns = boardWidth / tileSize;

    this.rand = new Random();

    this.counter = 0;

    this.board = new ArrayList<ArrayList<Vertex>>();

    this.hm = new HashMap<Vertex, Vertex>();

    this.edgeList = new ArrayList<Edge>();

    this.visited = new ArrayList<Vertex>();
    this.workList = new ArrayList<Vertex>();
    this.path = new ArrayList<Vertex>();

    this.createMaze();
    this.sc = this.drawMaze();

    this.start = this.board.get(0).get(0);
    this.end = this.board.get(this.board.size() - 1).get(this.board.get(0).size() - 1);

    if (this.tileSize > this.boardWidth) {
      throw new IllegalArgumentException(
          "Your tile size cannot be larger than the width of the board.");
    }

    if (this.tileSize > this.boardHeight) {
      throw new IllegalArgumentException(
          "Your tile size cannot be larger than the height of the board.");
    }

    if (this.boardWidth % this.tileSize != 0 && this.boardHeight % this.tileSize != 0) {
      throw new IllegalArgumentException("Your tile size does not fit properly in your board.");
    }
  }

  // PART 1 METHODS

  // creates the board with vertices and edges connected appropriately
  void createGrid() {
    ArrayList<ArrayList<Vertex>> result = new ArrayList<ArrayList<Vertex>>();
    for (int i = 0; i < this.rows; i++) {
      ArrayList<Vertex> newRow = new ArrayList<Vertex>();
      for (int j = 0; j < this.columns; j++) {
        Vertex v = new Vertex(j, i);
        newRow.add(v);
      }
      result.add(newRow);
    }
    this.board = result;
    this.generateEdges();
  }

  // generates edges and adds neighbors appropriately between vertices in a maze
  // board
  void generateEdges() {
    // for each row
    for (int i = 0; i < this.rows; i++) {
      // for each vertex in each column
      for (int j = 0; j < this.columns; j++) {
        Vertex current = this.board.get(i).get(j);

        if (current.x > 0) {
          // ADDS EDGE TO LEFT
          Edge leftEdge = new Edge(this.board.get(i).get(j - 1), current, this.rand.nextInt(1000));
          this.edgeList.add(leftEdge);
        }

        if (current.x < this.columns - 1) {
          // ADDS EDGE TO RIGHT
          Edge rightEdge = new Edge(this.board.get(i).get(j + 1), current, this.rand.nextInt(1000));
          this.edgeList.add(rightEdge);
        }

        if (current.y > 0) {
          // ADDS EDGE TO TOP
          Edge topEdge = new Edge(this.board.get(i - 1).get(j), current, this.rand.nextInt(1000));
          this.edgeList.add(topEdge);
        }

        if (current.y < this.rows - 1) {
          // ADDS EDGE TO BOTTOM
          Edge bottomEdge = new Edge(this.board.get(i + 1).get(j), current,
              this.rand.nextInt(1000));
          this.edgeList.add(bottomEdge);
        }

        // hashes all the vertices and puts them in a hashmap where they reference
        // themselves
        this.hm.put(current, current);
      }
    }
  }

  // sorts the list of edges according to the given comparator
  // in this case, ascending edge weight
  void sort(ArrayList<Edge> arr) {
    Comparator<Edge> c = (Edge e1, Edge e2) -> e1.weight - e2.weight;
    arr.sort(c);
  }

  // joins together the vertices in the board according to the sorted list of
  // edges using the Unify and Find methods, placing the valid edges in the
  // MST, make sure to add edges bi-directionally, and
  // add neighbors simultaneously as you put the edges in
  ArrayList<Edge> kruskals() {
    this.sort(this.edgeList);
    ArrayList<Edge> mst = new ArrayList<Edge>();
    for (int i = 0; i < this.edgeList.size(); i++) {
      Edge current = this.edgeList.get(i);

      // if they are not connected, add the edge to the mst
      if (!this.find(current.to).equals(this.find(current.from))) {
        this.unify(current.to, current.from);
        mst.add(current);
        current.from.neighbors.add(current);
        Edge newEdge = new Edge(current.from, current.to, current.weight);
        current.to.neighbors.add(newEdge);
        this.edgeList.remove(current);
      }
    }
    this.edgeList = mst;
    return mst;
  }

  // Union Operation: To UNIFY two vertices by finding the root vertices of each
  // and if they are different,
  // make one of the root vertices the parent of the other
  void unify(Vertex v1, Vertex v2) {
    if (this.find(v1).equals(this.find(v2))) {
      // same group, do not unify
      return;
    }

    Vertex root1 = this.find(v1);
    Vertex root2 = this.find(v2);

    // compares group sizes and places smaller one in the bigger one
    if (this.numGroup(root1) < this.numGroup(root2)) {
      // replaces the root value of the vertex in the smaller group in the hashmap
      // so that its new root value is that of the vertex in the larger group
      this.hm.put(v1, root2);
      // path compression to give us amortized constant time complexity
      // while root value is not equal to v

      // find all keys with this vertex as its root value and
      // change to the new root value
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.columns; j++) {
          Vertex current = this.board.get(i).get(j);
          if (this.find(current).equals(root1)) {
            this.hm.put(current, root2);
          }
        }
      }
    }
    else {
      this.hm.put(v2, root1);
      for (int i = 0; i < this.rows; i++) {
        for (int j = 0; j < this.columns; j++) {
          Vertex current = this.board.get(i).get(j);
          if (this.find(current).equals(root2)) {
            this.hm.put(current, root1);
          }
        }
      }
    }
  }

  // Find Operation: To FIND which group a particular vertex belongs to and the
  // group's root vertex,
  // which is accomplished by following the parent vertices until a self loop is
  // reached (parent is itself)
  public Vertex find(Vertex v) {
    Vertex root = v;
    // while root is not itself
    while (!root.equals(this.hm.get(v))) {
      // set root value equal to the root of vertex v
      root = this.hm.get(v);
      this.hm.put(v, root);
    }
    return root;
  }

  // finds how many vertices a given vertex has in its union
  // How many keys have the same value?
  int numGroup(Vertex v) {
    int k = 0;
    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        Vertex current = this.board.get(i).get(j);
        if (this.find(current).equals(v)) {
          k++;
        }
      }
    }
    return k;
  }

  // renders the walls of the maze appropriately, where vertices have no edges
  // adjacently
  void renderWalls() {
    // for each row
    for (int i = 0; i < this.rows; i++) {
      // for each vertex in each column
      for (int j = 0; j < this.columns; j++) {
        Vertex current = this.board.get(i).get(j);

        // for each neighbor of the current vertex (from)
        for (int k = 0; k < current.neighbors.size(); k++) {
          Vertex neighbor = current.neighbors.get(k).to; // (to)
          if (neighbor != null) {
            if (current.x > neighbor.x && current.y == neighbor.y) {
              // Neighbor on the LEFT
              // Get rid of wall in between
              current.left = true;
            }

            if (current.x < neighbor.x && current.y == neighbor.y) {
              // Neighbor on the RIGHT
              // Get rid of wall in between
              current.right = true;
            }

            if (current.x == neighbor.x && current.y < neighbor.y) {
              // Neighbor on the bottom
              // Get rid of wall in between
              current.bottom = true;
            }

            if (current.x == neighbor.x && current.y > neighbor.y) {
              // Neighbor on the TOP
              // Get rid of wall in between
              current.top = true;
            }
          }
          else {
            return;
          }
        }
      }
    }
  }

  // creates a working maze using the other methods
  void createMaze() {
    this.createGrid();
    this.kruskals();
    this.renderWalls();
  }

  // PART 2 METHODS

  // boolean bfs(Vertex current, Vertex end) {
  // boolean result = false;
  // while (!current.equals(end)) {
  // if (current.equals(end)) {
  // result = true;
  // break;
  // }
  // System.out.print("hi");
  // if (!this.visited.contains(current)) {
  // for (Edge e : current.neighbors) {
  // this.visited.add(current);
  // // for each row
  // this.bfs(e.to, end);
  // }
  // }
  // }
  // return result;
  // }

  // if (current.equals(end)) {
  // return true;
  // }
  // else {
  // System.out.print("hi");
  // if (!visited.contains(current)) {
  // for (Edge e : current.neighbors) {
  // visited.add(current);
  // current.color = Color.BLUE;
  // this.bfsHelper(e.to, end, visited);
  // }
  // }
  // }
  // return false;
  // }

  /// breadth first search
  boolean dfs() {
    boolean success = false;
    this.workList.add(this.start);
    this.hm = new HashMap<Vertex, Vertex>();

    while (this.workList.size() > 0) {
      Vertex v = this.workList.remove(0);
      if (v.equals(this.end)) {
        this.reconstructPath();
        success = true;
        // trigger end scene here
        // endScene(Vertex end, Map cameFromEdges)
        // current = end
        // while current not equal to top left vertex
        // Find where current came from,
        // make hashmap during the search as a local variable
        // when adding a node to the worklist, creates a map from edge -> node
        break;
      }
      else if (visited.contains(v)) {
        // don't do anything
      }
      else {

        for (Edge e : v.neighbors) {
          if (!this.visited.contains(e.to)) {
            workList.add(0, e.to);
            this.hm.put(e.to, v);
          }
          this.visited.add(v);
        }
      }
    }
    return success;
  }

  // breadth first search
  boolean bfs() {
    boolean success = false;
    this.workList.add(this.start);
    this.hm = new HashMap<Vertex, Vertex>();

    while (this.workList.size() > 0) {
      Vertex v = this.workList.remove(0);
      if (v.equals(this.end)) {
        this.visited.add(v);
        this.reconstructPath();
        success = true;
        // trigger end scene here
        // endScene(Vertex end, Map cameFromEdges)
        // current = end
        // while current not equal to top left vertex
        // Find where current came from,
        // make hashmap during the search as a local variable
        // when adding a node to the worklist, creates a map from edge -> node
        break;
      }
      else if (visited.contains(v)) {
        // don't do anything
      }
      else {

        for (Edge e : v.neighbors) {
          if (!this.visited.contains(e.to)) {
            workList.add(e.to);
            this.hm.put(e.to, v);
          }
          this.visited.add(v);
        }
      }
    }
    return success;
  }

  // reconstructs the exact path from finish to start after using bfs or dfs
  ArrayList<Vertex> reconstructPath() {
    Vertex current = end;
    while (!current.equals(start)) {
      this.path.add(current);
      //System.out.println(String.format("(%d, %d)", current.x, current.y));
      current = this.hm.get(current);
    }
    this.path.add(current);
    return this.path;
  }

  // WORLD AND BIG-BANG METHODS

  //on a key calls the respective function
  public void onKeyEvent(String key) {
    if (key.equals("b")) {
      this.bfs();
    }
    if (key.equals("d")) {
      this.dfs();
    }
    if (key.equals("r")) {
      this.reset();
    }

    if (key.equals("s")) {
      this.skip();
    }
  }

  // calls a specific function on tick
  public void onTick() {
    this.drawVisitedColor();
    if (this.counter == this.visited.size() - 1) {
      this.sc = this.drawPath();
    }
  }

  @Override
  // draws the given WorldScene in Big Bang
  public WorldScene makeScene() {
    return this.sc;
  }

  // animates the search algorithm
  void drawVisitedColor() {
    if (this.visited.size() > 0 && this.counter < this.visited.size() - 1) {
      // this.visited.get(this.counter).changeColor(Color.CYAN, this.rows, this.columns, this.board);
      this.visited.get(this.counter).drawVertex(this.sc, this.tileSize, Color.CYAN);
      this.start.drawVertex(this.sc, this.tileSize, Color.GREEN);
      this.end.drawVertex(this.sc, this.tileSize, Color.MAGENTA);
      this.counter++;
    }
  }

  // resets the board and visitors
  void reset() {
    this.visited = new ArrayList<Vertex>();
    this.workList = new ArrayList<Vertex>();
    this.path = new ArrayList<Vertex>();
    this.counter = 0;
    // colors in the visited cells
    for (int i = 0; i < this.rows; i++) {
      // for each vertex in each column
      for (int j = 0; j < this.columns; j++) {
        Vertex current = this.board.get(i).get(j);
        current.drawVertex(this.sc, this.tileSize, Color.GRAY);
      }
    }
    this.start.drawVertex(this.sc, this.tileSize, Color.GREEN);
    this.end.drawVertex(this.sc, this.tileSize, Color.MAGENTA);
  }

  // draws the path list on the maze
  WorldScene drawPath() {
    for (Vertex v : this.path) {
      v.drawVertex(this.sc, this.tileSize, Color.YELLOW);
    }
    return this.sc;
  }

  // skips the animation and shows the direct path
  void skip() {
    this.visited = new ArrayList<Vertex>();
    this.workList = new ArrayList<Vertex>();
    this.counter = 0;
    // colors in the visited cells
    for (int i = 0; i < this.rows; i++) {
      // for each vertex in each column
      for (int j = 0; j < this.columns; j++) {
        Vertex current = this.board.get(i).get(j);
        current.drawVertex(this.sc, this.tileSize, Color.GRAY);
      }
    }
    this.sc = this.drawPath();
  }

  // draws the maze
  WorldScene drawMaze() {
    this.sc = this.getEmptyScene();
    // draws every vertex in the board
    for (ArrayList<Vertex> arr : this.board) {
      for (Vertex v : arr) {
        v.drawVertex(this.sc, this.tileSize, Color.GRAY);
      }
    }
    // draws the first Vertex tile in the board
    this.sc.placeImageXY(
        new RectangleImage(this.tileSize, this.tileSize, OutlineMode.SOLID, Color.GREEN),
        this.tileSize / 2, this.tileSize / 2);
    // draws the last Vertex tile in the board
    this.sc.placeImageXY(
        new RectangleImage(this.tileSize, this.tileSize, OutlineMode.SOLID, Color.MAGENTA),
        this.boardWidth - this.tileSize / 2, this.boardHeight - this.tileSize / 2);
    // places a border on top of the maze
    this.sc.placeImageXY(
        new RectangleImage(this.boardWidth, this.boardHeight, OutlineMode.OUTLINE, Color.BLACK),
        this.boardWidth / 2, this.boardHeight / 2);

    return this.sc;
  }

  //  @Override
  //  // method to determine and display the ending worldScene
  //  public WorldEnd worldEnds() {
  //    Vertex start = this.board.get(0).get(0);
  //    Vertex end = this.board.get(this.board.size() - 1).get(this.board.get(0).size() - 1);
  //    if (this.counter >= ((this.rows + 1) * (this.columns + 1))) {
  //      return new WorldEnd(true, this.makeFinalScene());
  //    }
  //    return new WorldEnd(true, this.makeScene());
  //  }
  //
  //  // makes the final scene of when the game is completed
  //  WorldScene makeFinalScene() {
  //    this.placeWinningText();
  //    return this.sc;
  //  }
  //
  //  public WorldScene placeWinningText() {
  //    this.sc.placeImageXY(new TextImage("Congrats Gamer!", 50, FontStyle.BOLD, Color.BLACK),
  //        this.boardWidth / 2, this.boardHeight / 2);
  //    return this.sc;
  //  }
}