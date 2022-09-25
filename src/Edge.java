import java.util.*;
import java.awt.Color;
import javalib.worldimages.*;

class CompareWeight implements Comparator<Edge> {

  @Override
  public int compare(Edge v1, Edge v2) {
    // if v1's weight is larger, result will be positive.
    // if not, result will be negative.
    return v1.weight - v2.weight;
  }

}

class Edge {
  // FIELDS
  // assign random edge weight
  int weight;
  Vertex to;
  Vertex from;

  // CONSTANTS

  // CONSTRUCTORS

  // random weight constructor with seed
  Edge(Vertex to, Vertex from, int weight) {
    this.to = to;
    this.from = from;
    this.weight = weight;
  }

  // random weight constructor without seed
  Edge(Vertex to, Vertex from) {
    this.weight = (new Random()).nextInt(1000);
    this.to = to;
    this.from = from;
  }

  // METHODS

  // draws an edge vertically
  public WorldImage drawWallVertical(int tileSize) {
    WorldImage wi = new LineImage(new Posn(0, tileSize), Color.BLACK);
    return wi;
  }

  // draws an edge horizontally
  public WorldImage drawWallHorizontal(int tileSize) {
    WorldImage wi = new LineImage(new Posn(tileSize, 0), Color.BLACK);
    return wi;
  }
}