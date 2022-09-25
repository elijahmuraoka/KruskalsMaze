import java.awt.Color;
import java.util.*;
import javalib.impworld.*;
import javalib.worldimages.*;
import tester.Tester;

class ExamplesMaze {
  ExamplesMaze() {

  }

  // 3 x 4 Maze

  Vertex v1;
  Vertex v2;
  Vertex v3;
  Vertex v4;
  Vertex v5;
  Vertex v6;
  Vertex v7;
  Vertex v8;
  Vertex v9;
  Vertex v10;
  Vertex v11;
  Vertex v12;

  Edge e1;
  Edge e2;
  Edge e3;
  Edge e4;
  Edge e5;
  Edge e6;
  Edge e7;
  Edge e8;
  Edge e9;
  Edge e10;
  Edge e11;
  Edge e12;
  Edge e13;
  Edge e14;
  Edge e15;
  Edge e16;
  Edge e17;

  Edge e1R;
  Edge e2R;
  Edge e3R;
  Edge e4R;
  Edge e5R;
  Edge e6R;
  Edge e7R;
  Edge e8R;
  Edge e9R;
  Edge e10R;
  Edge e11R;
  Edge e12R;
  Edge e13R;
  Edge e14R;
  Edge e15R;
  Edge e16R;
  Edge e17R;

  HashMap<Vertex, Vertex> hm;
  ArrayList<Edge> el;

  Maze m;

  // 2 x 2 Maze

  Vertex nv1;
  Vertex nv2;
  Vertex nv3;
  Vertex nv4;
  Vertex nv5;
  Vertex nv6;
  Vertex nv7;
  Vertex nv8;
  Vertex nv9;

  Edge ne1;
  Edge ne2;
  Edge ne3;
  Edge ne4;
  Edge ne5;
  Edge ne6;
  Edge ne7;
  Edge ne8;
  Edge ne9;
  Edge ne10;
  Edge ne11;
  Edge ne12;

  Edge neR1;
  Edge neR2;
  Edge neR3;
  Edge neR4;
  Edge neR5;
  Edge neR6;
  Edge neR7;
  Edge neR8;
  Edge neR9;
  Edge neR10;
  Edge neR11;
  Edge neR12;

  HashMap<Vertex, Vertex> nhm;
  ArrayList<Edge> nel;

  Maze nm;

  // constructs a full grid/graph
  // Set the board height to 600 and width to 800 before using
  void initConditions() {
    // Vertices
    this.v1 = new Vertex(0, 0);
    this.v2 = new Vertex(1, 0);
    this.v3 = new Vertex(2, 0);
    this.v4 = new Vertex(3, 0);
    this.v5 = new Vertex(0, 1);
    this.v6 = new Vertex(1, 1);
    this.v7 = new Vertex(2, 1);
    this.v8 = new Vertex(3, 1);
    this.v9 = new Vertex(0, 2);
    this.v10 = new Vertex(1, 2);
    this.v11 = new Vertex(2, 2);
    this.v12 = new Vertex(3, 2);

    // Edges connecting ALL vertices (for graph)
    // MAKE BI-DIRECTIONAL
    // Row 1 left to right
    this.e1 = new Edge(this.v1, this.v2);
    this.e2 = new Edge(this.v2, this.v3);
    this.e3 = new Edge(this.v3, this.v4);

    // Row 2 left to right
    this.e4 = new Edge(this.v5, this.v6);
    this.e5 = new Edge(this.v6, this.v7);
    this.e6 = new Edge(this.v7, this.v8);

    // Row 3 left to right
    this.e7 = new Edge(this.v9, this.v10);
    this.e8 = new Edge(this.v10, this.v11);
    this.e9 = new Edge(this.v11, this.v12);

    // Column 1 top to bottom
    this.e10 = new Edge(this.v1, this.v5);
    this.e11 = new Edge(this.v5, this.v9);

    // Column 2 top to bottom
    this.e12 = new Edge(this.v2, this.v6);
    this.e13 = new Edge(this.v6, this.v10);

    // Column 3 top to bottom
    this.e14 = new Edge(this.v3, this.v7);
    this.e15 = new Edge(this.v7, this.v11);

    // Column 4 top to bottom
    this.e16 = new Edge(this.v4, this.v8);
    this.e17 = new Edge(this.v8, this.v12);

    // creating the neighbors for all the vertices
    // Row 1
    this.v1.neighbors = new ArrayList<Edge>(Arrays.asList(this.e1, e10));
    this.v2.neighbors = new ArrayList<Edge>(Arrays.asList(this.e1, e12, this.e2));
    this.v3.neighbors = new ArrayList<Edge>(Arrays.asList(this.e2, this.e14, this.e3));
    this.v4.neighbors = new ArrayList<Edge>(Arrays.asList(this.e3, this.e16));

    // Row 2
    this.v5.neighbors = new ArrayList<Edge>(Arrays.asList(this.e10, this.e4, this.e11));
    this.v6.neighbors = new ArrayList<Edge>(Arrays.asList(this.e4, this.e12, this.e5, this.e13));
    this.v7.neighbors = new ArrayList<Edge>(Arrays.asList(this.e5, this.e14, this.e6, this.e15));
    this.v8.neighbors = new ArrayList<Edge>(Arrays.asList(this.e6, this.e16, this.e17));

    // Row 3
    this.v9.neighbors = new ArrayList<Edge>(Arrays.asList(this.e11, this.e7));
    this.v10.neighbors = new ArrayList<Edge>(Arrays.asList(this.e7, this.e13, this.e8));
    this.v11.neighbors = new ArrayList<Edge>(Arrays.asList(this.e8, this.e15, this.e9));
    this.v12.neighbors = new ArrayList<Edge>(Arrays.asList(this.e9, e17));

    // HashMap
    this.hm = new HashMap<Vertex, Vertex>();
    this.hm.put(this.v1, this.v1);
    this.hm.put(this.v2, this.v2);
    this.hm.put(this.v3, this.v3);
    this.hm.put(this.v4, this.v4);
    this.hm.put(this.v5, this.v5);
    this.hm.put(this.v6, this.v6);
    this.hm.put(this.v7, this.v7);
    this.hm.put(this.v8, this.v8);
    this.hm.put(this.v9, this.v9);
    this.hm.put(this.v10, this.v10);
    this.hm.put(this.v11, this.v11);
    this.hm.put(this.v12, this.v12);

    // Adds the edges to the edge list of the maze
    this.el = new ArrayList<Edge>();
    this.el.add(this.e1);
    this.el.add(this.e2);
    this.el.add(this.e3);
    this.el.add(this.e4);
    this.el.add(this.e5);
    this.el.add(this.e6);
    this.el.add(this.e7);
    this.el.add(this.e8);
    this.el.add(this.e9);
    this.el.add(this.e10);
    this.el.add(this.e11);
    this.el.add(this.e12);
    this.el.add(this.e13);
    this.el.add(this.e14);
    this.el.add(this.e15);
    this.el.add(this.e16);
    this.el.add(this.e17);

    // Maze
    this.m = new Maze(800, 600, 200, 50);
    m.board = new ArrayList<ArrayList<Vertex>>(
        Arrays.asList(new ArrayList<Vertex>(Arrays.asList(this.v1, this.v2, this.v3, this.v4)),
            new ArrayList<Vertex>(Arrays.asList(this.v5, this.v6, this.v7, this.v8)),
            new ArrayList<Vertex>(Arrays.asList(this.v9, this.v10, this.v11, this.v12))));
    this.m.hm = this.hm;
    this.m.edgeList = this.el;
  }

  // constructs a maze with weights
  void initConditions2() {
    initConditions();

    this.e1.weight = 5;
    this.e2.weight = 28;
    this.e3.weight = 4;
    this.e4.weight = 12;
    this.e5.weight = 45;
    this.e6.weight = 57;
    this.e7.weight = 31;
    this.e8.weight = 9;
    this.e9.weight = 27;
    this.e10.weight = 43;
    this.e11.weight = 19;
    this.e12.weight = 17;
    this.e13.weight = 2;
    this.e14.weight = 7;
    this.e15.weight = 1;
    this.e16.weight = 33;
    this.e17.weight = 14;

  }

  // constructs a 2 x 2 maze with weights
  // Set the board height and width to 800 before using
  void initConditions3() {

    this.nm = new Maze(800, 800, 400, 1);

    // Vertices
    this.nv1 = new Vertex(0, 0);
    this.nv2 = new Vertex(1, 0);
    this.nv3 = new Vertex(2, 0);
    this.nv4 = new Vertex(0, 1);
    this.nv5 = new Vertex(1, 1);
    this.nv6 = new Vertex(2, 1);
    this.nv7 = new Vertex(0, 2);
    this.nv8 = new Vertex(1, 2);
    this.nv9 = new Vertex(2, 2);

    // Edges connecting ALL vertices (for graph)
    // MAKE BI-DIRECTIONAL
    // Row 1 right to left
    this.ne1 = new Edge(this.nv1, this.nv2);
    this.ne2 = new Edge(this.nv2, this.nv3);

    // Row 2 right to left
    this.ne3 = new Edge(this.nv4, this.nv5);
    this.ne4 = new Edge(this.nv5, this.nv6);

    // Row 3 right to left
    this.ne5 = new Edge(this.nv7, this.nv8);
    this.ne6 = new Edge(this.nv8, this.nv9);

    // Column 1 bpttom to top
    this.ne7 = new Edge(this.nv1, this.nv4);
    this.ne8 = new Edge(this.nv4, this.nv7);

    // Column 2 bpttom to top
    this.ne9 = new Edge(this.nv2, this.nv5);
    this.ne10 = new Edge(this.nv5, this.nv8);

    // Column 3 bpttom to top
    this.ne11 = new Edge(this.nv3, this.nv6);
    this.ne12 = new Edge(this.nv6, this.nv9);

    // Reversed Edges

    // Row 1 left to right
    this.neR1 = new Edge(this.nv2, this.nv1);
    this.neR2 = new Edge(this.nv3, this.nv2);

    // Row 2 left to right
    this.neR3 = new Edge(this.nv5, this.nv4);
    this.neR4 = new Edge(this.nv6, this.nv5);

    // Row 3 left to right
    this.neR5 = new Edge(this.nv8, this.nv7);
    this.neR6 = new Edge(this.nv9, this.nv8);

    // Column 1 top to bottom
    this.neR7 = new Edge(this.nv4, this.nv1);
    this.neR8 = new Edge(this.nv7, this.nv4);

    // Column 2 top to bottom
    this.neR9 = new Edge(this.nv5, this.nv2);
    this.neR10 = new Edge(this.nv8, this.nv5);

    // Column 1 top to bottom
    this.neR11 = new Edge(this.nv6, this.nv3);
    this.neR12 = new Edge(this.nv9, this.nv6);

    // All Edge Weights

    this.ne1.weight = 5;
    this.ne2.weight = 28;
    this.ne3.weight = 4;
    this.ne4.weight = 12;
    this.ne5.weight = 45;
    this.ne6.weight = 57;
    this.ne7.weight = 31;
    this.ne8.weight = 9;
    this.ne9.weight = 27;
    this.ne10.weight = 43;
    this.ne11.weight = 19;
    this.ne12.weight = 17;

    this.neR1.weight = 5;
    this.neR2.weight = 28;
    this.neR3.weight = 4;
    this.neR4.weight = 12;
    this.neR5.weight = 45;
    this.neR6.weight = 57;
    this.neR7.weight = 31;
    this.neR8.weight = 9;
    this.neR9.weight = 27;
    this.neR10.weight = 43;
    this.neR11.weight = 19;
    this.neR12.weight = 17;

    // creating the neighbors for all the vertices
    // Row 1
    this.nv1.neighbors = new ArrayList<Edge>(Arrays.asList(this.neR1, this.neR7));
    this.nv2.neighbors = new ArrayList<Edge>(Arrays.asList(this.ne2, this.neR9, this.neR2));
    this.nv3.neighbors = new ArrayList<Edge>(Arrays.asList(this.ne2, this.neR11));
    this.nv4.neighbors = new ArrayList<Edge>(Arrays.asList(this.ne7, this.neR3, this.neR8));
    this.nv5.neighbors = new ArrayList<Edge>(
        Arrays.asList(this.ne3, this.ne9, this.neR4, this.neR10));
    this.nv6.neighbors = new ArrayList<Edge>(Arrays.asList(this.ne4, this.ne11, this.neR12));
    this.nv7.neighbors = new ArrayList<Edge>(Arrays.asList(this.ne8, this.neR5));
    this.nv8.neighbors = new ArrayList<Edge>(Arrays.asList(this.ne5, this.ne10, this.neR6));
    this.nv9.neighbors = new ArrayList<Edge>(Arrays.asList(this.ne6, this.ne12));

    // HashMap
    this.nhm = new HashMap<Vertex, Vertex>();
    this.nhm.put(this.nv1, this.nv1);
    this.nhm.put(this.nv2, this.nv2);
    this.nhm.put(this.nv3, this.nv3);
    this.nhm.put(this.nv4, this.nv4);
    this.nhm.put(this.nv5, this.nv5);
    this.nhm.put(this.nv6, this.nv6);
    this.nhm.put(this.nv7, this.nv7);
    this.nhm.put(this.nv8, this.nv8);
    this.nhm.put(this.nv9, this.nv9);

    // Adds the edges to the edge list of the maze
    this.nel = new ArrayList<Edge>();
    this.nel.add(this.ne1);
    this.nel.add(this.ne2);
    this.nel.add(this.ne3);
    this.nel.add(this.ne4);
    this.nel.add(this.ne5);
    this.nel.add(this.ne6);
    this.nel.add(this.ne7);
    this.nel.add(this.ne8);
    this.nel.add(this.ne9);
    this.nel.add(this.ne10);
    this.nel.add(this.ne11);
    this.nel.add(this.ne12);

    this.nel.add(this.neR1);
    this.nel.add(this.neR2);
    this.nel.add(this.neR3);
    this.nel.add(this.neR4);
    this.nel.add(this.neR5);
    this.nel.add(this.neR6);
    this.nel.add(this.neR7);
    this.nel.add(this.neR8);
    this.nel.add(this.neR9);
    this.nel.add(this.neR10);
    this.nel.add(this.neR11);
    this.nel.add(this.neR12);

    // Maze
    nm.board = new ArrayList<ArrayList<Vertex>>(
        Arrays.asList(new ArrayList<Vertex>(Arrays.asList(this.nv1, this.nv2, this.nv3)),
            new ArrayList<Vertex>(Arrays.asList(this.nv4, this.nv5, this.nv6)),
            new ArrayList<Vertex>(Arrays.asList(this.nv7, this.nv8, this.nv9))));
    this.nm.hm = this.nhm;
    this.nm.edgeList = this.nel;
  }

  // COMPARATOR TESTS

  // tests the compare method in WeightComp
  void testCompare(Tester t) {
    initConditions2();

    t.checkExpect((new CompareWeight()).compare(this.e1, this.e2), -23);
    t.checkExpect((new CompareWeight()).compare(this.e2, this.e1), 23);
    t.checkExpect((new CompareWeight()).compare(this.e2, this.e3), 24);
    t.checkExpect((new CompareWeight()).compare(this.e3, this.e1), -1);
  }

  // VERTEX TESTS

  // tests change color method
  void testChangeColor(Tester t) {
    Maze m1 = new Maze(200, 200, 10, 5);
    m1.createGrid();
    Vertex v1 = m1.board.get(0).get(0);
    Vertex v2 = m1.board.get(0).get(3);
    Vertex v3 = m1.board.get(2).get(2);
    Vertex v4 = m1.board.get(4).get(5);

    t.checkExpect(v1.color, Color.GRAY);
    t.checkExpect(v2.color, Color.GRAY);
    t.checkExpect(v3.color, Color.GRAY);
    t.checkExpect(v4.color, Color.GRAY);

    v1.changeColor(Color.CYAN, m1.rows, m1.columns, m1.board);
    v2.changeColor(Color.CYAN, m1.rows, m1.columns, m1.board);
    v3.changeColor(Color.CYAN, m1.rows, m1.columns, m1.board);

    t.checkExpect(v1.color, Color.CYAN);
    t.checkExpect(v2.color, Color.CYAN);
    t.checkExpect(v3.color, Color.CYAN);
    t.checkExpect(v4.color, Color.GRAY);
  }

  // tests the hashCode method
  void testHashCode(Tester t) {
    initConditions3();

    this.nm.hm.put(this.nv1, this.nv5);
    t.checkExpect(this.nm.hm.get(this.nv1), this.nv5);
    this.nm.hm.put(this.nv2, this.nv5);
    t.checkExpect(this.nm.hm.get(this.nv2), this.nv5);
    this.nm.hm.put(this.nv4, this.nv1);
    t.checkExpect(this.nm.hm.get(this.nv4), this.nv1);
    this.nm.hm.put(this.nv3, this.nv8);
    t.checkExpect(this.nm.hm.get(this.nv3), this.nv8);
    t.checkExpect(this.nm.hm.containsKey(this.nv1), true);
    t.checkExpect(this.nm.hm.get(this.nv7), this.nv7);
    Vertex vTest = new Vertex(17, 3);
    t.checkExpect(this.nm.hm.containsKey(vTest), false);
    t.checkExpect(this.nm.hm.get(vTest), null);
  }

  // tests the equals method
  void testEquals(Tester t) {
    initConditions();
    t.checkExpect(this.v1.equals(this.v1), true);
    Vertex newV1 = new Vertex(0, 0);
    Vertex newV2 = new Vertex(0, 0);
    Vertex newV3 = new Vertex(5, 7);
    t.checkExpect(newV1.equals(newV2), true);
    t.checkExpect(newV1.equals(newV3), false);
    Edge v1andv2 = new Edge(newV2, newV1);
    newV1.neighbors.add(v1andv2);
    t.checkExpect(newV1.equals(newV2), false);
    newV2.neighbors.add(v1andv2);
    t.checkExpect(newV1.equals(newV2), true);
    newV1.left = true;
    t.checkExpect(newV1.equals(newV2), false);
    newV2.left = true;
    t.checkExpect(newV1.equals(newV2), true);
  }

  // tests the drawVertex method
  void testDrawVertex(Tester t) {
    initConditions();
    Maze m1 = new Maze(500, 500, 50, 50);
    WorldScene ws = (new Maze(500, 500, 50, 50)).getEmptyScene();

    ws.placeImageXY(new RectangleImage(m1.tileSize, m1.tileSize, OutlineMode.SOLID, Color.GRAY),
        this.v5.x * m1.tileSize + m1.tileSize / 2, this.v5.y * m1.tileSize + m1.tileSize / 2);
    for (Edge e : this.v5.neighbors) {
      if (!this.v5.right) {
        ws.placeImageXY(e.drawWallVertical(m1.tileSize), (this.v5.x * m1.tileSize) + m1.tileSize,
            (this.v5.y * m1.tileSize) + m1.tileSize / 2);
      }
      if (!this.v5.left) {
        ws.placeImageXY(e.drawWallVertical(m1.tileSize), (this.v5.x * m1.tileSize),
            (this.v5.y * m1.tileSize) + m1.tileSize / 2);
      }
      if (!this.v5.top) {
        ws.placeImageXY(e.drawWallHorizontal(m1.tileSize),
            (this.v5.x * m1.tileSize) + (m1.tileSize / 2), (this.v5.y * m1.tileSize));
      }
      if (!this.v5.bottom) {
        ws.placeImageXY(e.drawWallHorizontal(m1.tileSize),
            (this.v5.x * m1.tileSize) + (m1.tileSize / 2), (this.v5.y * m1.tileSize) + m1.tileSize);
      }
    }

    t.checkExpect(this.v5.drawVertex(m.sc, m.tileSize, Color.GRAY), ws);
  }

  // EDGE TESTS

  // tests both the draw methods in the Edge class
  void testDrawEdge(Tester t) {
    initConditions();

    WorldImage wi1 = new LineImage(new Posn(m.tileSize, 0), Color.BLACK);
    t.checkExpect(this.e1.drawWallHorizontal(m.tileSize), wi1);

    WorldImage wi2 = new LineImage(new Posn(0, m.tileSize), Color.BLACK);
    t.checkExpect(this.e10.drawWallVertical(m.tileSize), wi2);
  }

  // MAZE TESTS

  // tests the Unify, Find, and numGroup method
  // set board height to 600 and width to 800 before testing
  void testUnionFind(Tester t) {
    initConditions();
    t.checkExpect(this.m.find(this.v1), this.v1);
    t.checkExpect(this.m.find(this.hm.get(this.v1)), this.v1);
    t.checkExpect(this.m.numGroup(this.v1), 1);

    this.m.hm.put(this.v4, this.v1);
    t.checkExpect(this.m.find(this.v1), this.v1);
    t.checkExpect(this.m.find(this.v4), this.v1);
    t.checkExpect(this.m.numGroup(this.v1), 2);

    this.m.hm.put(this.v7, this.v1);
    t.checkExpect(this.m.find(this.v7), this.v1);
    t.checkExpect(this.m.numGroup(this.v1), 3);

    this.m.unify(this.v7, this.v9);
    t.checkExpect(this.m.find(this.v9), this.v1);
    t.checkExpect(this.m.find(this.v1), this.v1);
    t.checkExpect(this.m.find(this.v4), this.v1);
    t.checkExpect(this.m.find(this.v7), this.v1);
    t.checkExpect(this.m.numGroup(this.v1), 4);

    t.checkExpect(this.m.numGroup(this.v2), 1);
    this.m.unify(this.v2, this.v3);
    t.checkExpect(this.m.numGroup(this.v2), 2);
    t.checkExpect(this.m.find(this.v3), this.v2);
    this.m.unify(this.v3, this.v5);
    t.checkExpect(this.m.numGroup(this.v2), 3);
    t.checkExpect(this.m.find(this.v5), this.v2);

    this.m.unify(this.v5, this.v9);
    t.checkExpect(this.m.numGroup(this.v1), 7);
    t.checkExpect(this.m.find(this.v5), this.v1);
    t.checkExpect(this.m.find(this.v2), this.v1);
    t.checkExpect(this.m.find(this.v3), this.v1);
    this.m.unify(this.v2, this.v3);
    t.checkExpect(this.m.numGroup(this.v1), 7);
    t.checkExpect(this.m.find(this.v2), this.v1);
    t.checkExpect(this.m.find(this.v3), this.v1);
  }

  // tests both the createGrid and the generateEdges methods
  void testCreateGrid(Tester t) {
    initConditions();
    // tileSize 400 should create a 2 x 2
    Maze m1 = new Maze(800, 800, 400, 50);
    m1.edgeList = new ArrayList<Edge>();
    m1.board = new ArrayList<ArrayList<Vertex>>();
    t.checkExpect(m1.edgeList.size(), 0);
    t.checkExpect(m1.board.size(), 0);
    m1.createGrid();
    t.checkExpect(m1.board.size(), 2);
    t.checkExpect(m1.board.get(0).size(), 2);
    t.checkExpect(m1.board.size(), 2);
    t.checkExpect(m1.board.get(0).size(), 2);
    t.checkExpect(m1.edgeList.size(), 8);
  }

  // tests the sort method
  void testSort(Tester t) {
    initConditions2();
    this.m.sort(m.edgeList);
    ArrayList<Edge> elSorted = new ArrayList<Edge>(
        Arrays.asList(this.e15, this.e13, this.e3, this.e1, this.e14, this.e8, this.e4, this.e17,
            this.e12, this.e11, this.e9, this.e2, this.e7, this.e16, this.e10, this.e5, this.e6));
    t.checkExpect(this.m.edgeList, elSorted);
    ArrayList<Edge> el1 = new ArrayList<Edge>();
    t.checkExpect(el1, new ArrayList<Edge>());
  }

  // tests the kruskals method
  void testKruskals(Tester t) {
    initConditions3();
    Maze m1 = new Maze(600, 800, 200, 1);
    m1.edgeList = new ArrayList<Edge>();
    m1.board = new ArrayList<ArrayList<Vertex>>();
    t.checkExpect(m1.edgeList.size(), 0);
    m1.createGrid();
    m1.kruskals();
    // the list of edges becomes the mst which is correct because it is one less
    // than the board size which is a 3 x 4
    t.checkExpect(m1.edgeList.size(), 11);
  }

  // tests the renderWalls method
  void testRenderWalls(Tester t) {
    initConditions3();
    Maze m1 = new Maze(800, 800, 400, 1);
    m1.createGrid();

    t.checkExpect(this.nm.board.get(0).get(0).left, false);
    t.checkExpect(this.nm.board.get(1).get(0).right, false);
    t.checkExpect(this.nm.board.get(0).get(2).bottom, false);
    t.checkExpect(this.nm.board.get(2).get(2).top, false);
    this.nm.renderWalls();
    t.checkExpect(this.nm.board.get(0).get(0).right, true);
    t.checkExpect(this.nm.board.get(1).get(0).bottom, true);
    t.checkExpect(this.nm.board.get(0).get(2).top, false);

    t.checkExpect(m1.board.get(0).get(0).left, false);
    t.checkExpect(m1.board.get(1).get(0).right, false);
    t.checkExpect(m1.board.get(0).get(1).bottom, false);
    t.checkExpect(m1.board.get(1).get(0).bottom, false);
    t.checkExpect(m1.board.get(1).get(0).top, false);
    t.checkExpect(m1.board.get(1).get(1).left, false);
    t.checkExpect(m1.board.get(0).get(1).left, false);
    t.checkExpect(m1.board.get(0).get(1).right, false);
    t.checkExpect(m1.board.get(1).get(1).top, false);
  }

  // tests the createMaze method
  void testCreateMaze(Tester t) {
    Maze m1 = new Maze(800, 800, 400, 1);
    m1.edgeList = new ArrayList<Edge>();
    m1.board = new ArrayList<ArrayList<Vertex>>();
    // edgeList initializes to an empty array list
    t.checkExpect(m1.edgeList.size(), 0);
    m1.createMaze();
    // number of rows and columns is two so total board size is four
    t.checkExpect(m1.board.size(), 2);
    t.checkExpect(m1.board.get(0).size(), 2);
    // shows we successfully created the minimum spanning tree since the number of
    // edges is one less than the total board size
    t.checkExpect(m1.edgeList.size(), 3);
  }

  // tests the makeScene method
  void testMakeScene(Tester t) {
    initConditions();

    Maze m1 = new Maze(600, 800, 50);
    Maze m2 = new Maze(600, 800, 50);
    WorldScene ws = m2.getEmptyScene();
    m1.makeScene();
    ws = m2.drawMaze();
    t.checkExpect(m1.sc, ws);

  }

  // tests the drawMaze method
  void testDrawMaze(Tester t) {
    Maze m1 = new Maze(400, 500, 50, 3);
    Maze m2 = new Maze(400, 500, 50, 3);
    WorldScene ws1 = m1.getEmptyScene();

    // draws the first Vertex tile in the board
    ws1.placeImageXY(new RectangleImage(m1.tileSize, m1.tileSize, OutlineMode.SOLID, Color.GREEN),
        m1.tileSize / 2, m1.tileSize / 2);
    // draws the last Vertex tile in the board
    m1.sc.placeImageXY(new RectangleImage(m1.tileSize, m1.tileSize, OutlineMode.SOLID, Color.PINK),
        m1.boardWidth - m1.tileSize / 2, m1.boardHeight - m1.tileSize / 2);
    for (ArrayList<Vertex> arr : m1.board) {
      for (Vertex v : arr) {
        v.drawVertex(m1.sc, m1.tileSize, Color.GRAY);
      }
    }
    // places a border on top of the maze
    m1.sc.placeImageXY(
        new RectangleImage(m1.boardWidth, m1.boardHeight, OutlineMode.OUTLINE, Color.GRAY),
        m1.boardWidth / 2, m1.boardHeight / 2);

    m2.drawMaze();

    t.checkExpect(m2.sc, ws1);
  }

  // tests the bfs and dfs
  void testSearch(Tester t) {
    Maze m1 = new Maze(600, 800, 200, 3);
    m1.createMaze();
    Vertex start = m1.board.get(0).get(0);
    Vertex end = m1.board.get(m1.board.size() - 1).get(m1.board.get(0).size() - 1);
    Vertex endFail = new Vertex(-35, 749);

    t.checkExpect(m1.bfs(), true);
    m1.start = start;
    m1.end = end;
    t.checkExpect(m1.bfs(), true);
    m1.start = end;
    m1.end = endFail;
    t.checkExpect(m1.bfs(), false);
    m1.start = endFail;
    m1.end = endFail;
    t.checkExpect(m1.bfs(), true);

    t.checkExpect(m1.dfs(), true);
    m1.start = start;
    m1.end = endFail;
    t.checkExpect(m1.dfs(), false);
    m1.start = end;
    m1.end = endFail;
    t.checkExpect(m1.dfs(), false);
    m1.start = endFail;
    m1.end = endFail;
    t.checkExpect(m1.dfs(), true);
  }

  // tests the ReconstructPath method
  void testReconstructPath(Tester t) {
    Maze m1 = new Maze(400, 500, 50, 3);

    t.checkExpect(m1.path.size(), 0);
    m1.bfs();
    m1.reconstructPath();
    t.checkExpect(m1.path.size() == 0, false);
    t.checkExpect(m1.path.size() > 0, true);
    t.checkExpect(m1.path.get(0), m1.end);
    t.checkExpect(m1.path.get(m1.path.size() - 1), m1.start);
  }

  // tests the onKey method
  void testOnKey(Tester t) {
    Maze m1 = new Maze(400, 500, 50, 3);
    Maze m1Copy = new Maze(400, 500, 50, 3);

    // tests a false key
    t.checkExpect(m1.visited.size(), 0);
    m1.onKeyEvent("x");
    t.checkExpect(m1.visited.size(), 0);

    // tests that b, which calls bfs, changes the visited arrayList
    m1.onKeyEvent("b");
    t.checkExpect(m1.visited.size() > m1Copy.visited.size(), true);

    // checks that b, which calls reset, initializes the visited list back to empty
    m1.onKeyEvent("r");
    t.checkExpect(m1.visited.size() == m1Copy.visited.size(), true);

    // tests that d, which calls dfs, changes the visited arrayList
    m1.onKeyEvent("d");
    t.checkExpect(m1.visited.size() > m1Copy.visited.size(), true);

    // tests that two of the same mazes calling dfs have the same visited path
    // result
    m1Copy.onKeyEvent("d");
    t.checkExpect(m1.visited.size() == m1Copy.visited.size(), true);
  }

  // tests the reset method
  void testReset(Tester t) {
    Maze m1 = new Maze(400, 500, 50, 8);
    Maze m2 = new Maze(400, 500, 50, 3);

    t.checkExpect(m1.counter == m2.counter, true);
    m1.bfs();
    t.checkExpect(m1.counter != m2.counter, false);
    m1.reset();
    t.checkExpect(m1.counter == m2.counter, true);

  }

  // tests the skip method
  void testSkip(Tester t) {
    Maze m1 = new Maze(400, 500, 50, 8);
    t.checkExpect(m1.visited.size(), 0);
    m1.bfs();
    t.checkExpect(m1.visited.size() > 0, true);
    m1.skip();
    t.checkExpect(m1.visited.size(), 0);
    t.checkExpect(m1.end.color, Color.GRAY);

  }

  // tests the drawPath method
  void testDrawPath(Tester t) {
    Maze m1 = new Maze(200, 200, 10, 5);
    Maze m2 = new Maze(200, 200, 10, 5);

    t.checkExpect(m1.start.color, Color.GRAY);
    m1.bfs();
    m1.reconstructPath();
    m1.drawPath();
    t.checkExpect(m1.end.color, Color.GRAY);

    for (Vertex v : m2.path) {
      v.drawVertex(m2.sc, m2.tileSize, Color.YELLOW);
    }
    m2.start.drawVertex(m2.sc, m2.tileSize, Color.YELLOW);

    t.checkExpect(m1.sc, m2.sc);
  }

  // tests the onTick and the changeVisitedColor method together
  void testChangeVisitedColor(Tester t) {
    Maze m1 = new Maze(200, 200, 10, 5);
    m1.createGrid();
    Vertex v1 = m1.board.get(0).get(0);
    Vertex v2 = m1.board.get(0).get(3);
    Vertex v3 = m1.board.get(2).get(2);
    Vertex v4 = m1.board.get(4).get(5);

    t.checkExpect(v1.color, Color.GRAY);
    t.checkExpect(v2.color, Color.GRAY);
    t.checkExpect(v3.color, Color.GRAY);
    t.checkExpect(v4.color, Color.GRAY);

    m1.visited = new ArrayList<Vertex>();

    t.checkExpect(v1.color, Color.GRAY);
    t.checkExpect(v2.color, Color.GRAY);
    t.checkExpect(v3.color, Color.GRAY);
    t.checkExpect(v4.color, Color.GRAY);

    m1.visited = new ArrayList<Vertex>((Arrays.asList(v1, v2, v3)));

    m1.counter = 0;
    m1.drawVisitedColor();

    //t.checkExpect(v1.color, Color.CYAN);
    t.checkExpect(v2.color, Color.GRAY);
    t.checkExpect(v3.color, Color.GRAY);
    t.checkExpect(v4.color, Color.GRAY);

    m1.onTick();

    //t.checkExpect(v1.color, Color.CYAN);
    //t.checkExpect(v2.color, Color.CYAN);
    t.checkExpect(v3.color, Color.GRAY);
    t.checkExpect(v4.color, Color.GRAY);

    m1.onTick();

    //t.checkExpect(v1.color, Color.CYAN);
    //t.checkExpect(v2.color, Color.CYAN);
    //t.checkExpect(v3.color, Color.CYAN);
    t.checkExpect(v4.color, Color.GRAY);

  }

  // BIG-BANG TEST
  // renders and runs the maze in Big Bang
  void testMaze(Tester t) {
    Maze m1 = new Maze(1000, 800, 50);
    // System.out.println(m1.edgeList.size());
    int worldWidth = m1.boardWidth;
    int worldHeight = m1.boardHeight;
    m1.bigBang(worldWidth, worldHeight, .000001);
  }
}
