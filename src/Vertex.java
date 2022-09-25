import java.util.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

class Vertex {
  // FIELDS
  int x;
  int y;
  ArrayList<Edge> neighbors;
  Boolean left;
  Boolean top;
  Boolean right;
  Boolean bottom;
  Boolean visited;
  Color color;

  // CONSTRUCTORS
  Vertex(int x, int y) {
    this.x = x;
    this.y = y;
    this.neighbors = new ArrayList<Edge>();
    this.left = false;
    this.top = false;
    this.right = false;
    this.bottom = false;
    this.visited = false;
    this.color = Color.GRAY;
  }

  // METHODS

  // overrides the hashCode method
  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  // overrides the equals method
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    else if (!(other instanceof Vertex)) {
      return false;
    }
    Vertex v = (Vertex) other;
    return this.x == (v.x) && this.y == (v.y) && this.neighbors.equals(v.neighbors)
        && this.left == v.left && this.right == v.right && this.top == v.top
        && this.bottom == v.bottom;
  }

  // changes the color of this vertex accordingly
  void changeColor(Color c, int rows, int columns, ArrayList<ArrayList<Vertex>> board) {
    for (int i = 0; i < rows; i++) {
      // for each vertex in each column
      for (int j = 0; j < columns; j++) {
        Vertex boardRep = board.get(i).get(j);
        if (this.equals(boardRep)) {
          boardRep.color = c;
        }
      }
    }
  }

  // BIG-BANG METHODS

  // draws the Vertex according to its rendered edges
  public WorldScene drawVertex(WorldScene ws, int tileSize, Color c) {
    ws.placeImageXY(new RectangleImage(tileSize, tileSize, OutlineMode.SOLID, c),
        this.x * tileSize + tileSize / 2, this.y * tileSize + tileSize / 2);
    for (Edge e : this.neighbors) {
      if (!this.right) {
        ws.placeImageXY(e.drawWallVertical(tileSize), (this.x * tileSize) + tileSize,
            (this.y * tileSize) + tileSize / 2);
      }
      if (!this.left) {
        ws.placeImageXY(e.drawWallVertical(tileSize), (this.x * tileSize),
            (this.y * tileSize) + tileSize / 2);
      }
      if (!this.top) {
        ws.placeImageXY(e.drawWallHorizontal(tileSize), (this.x * tileSize) + (tileSize / 2),
            (this.y * tileSize));
      }
      if (!this.bottom) {
        ws.placeImageXY(e.drawWallHorizontal(tileSize), (this.x * tileSize) + (tileSize / 2),
            (this.y * tileSize) + tileSize);
      }
    }
    return ws;
  }

}