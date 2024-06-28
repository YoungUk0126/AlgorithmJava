package bj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class BJ_S1_1991_트리순회 {

  static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer tokens;
  static StringBuilder builder = new StringBuilder();

  static int N;

  public static void main(String[] args) throws IOException {
    N = Integer.parseInt(input.readLine());

    BinaryTree tree = new BinaryTree();


    for(int i=0; i<N; i++){
      tokens = new StringTokenizer(input.readLine());
      char A = tokens.nextToken().charAt(0);
      char B = tokens.nextToken().charAt(0);
      char C = tokens.nextToken().charAt(0);

      tree.insert(A,B,C);
    }

    tree.preOrder(tree.root);
    builder.append("\n");
    tree.inOrder(tree.root);
    builder.append("\n");
    tree.postOrder(tree.root);

    System.out.println(builder);


  }

  static class Node {
    char name;
    Node left;
    Node right;

    public Node(char name) {
      this.name = name;
      this.left = null;
      this.right = null;
    }
    public Node(char name, Node left, Node right) {
      this.name = name;
      this.left = left;
      this.right = right;
    }
  }

  static class BinaryTree {
    Node root;

    void insert(char now, char left, char right) {
      if (root == null) {
        root = new Node(now);
        if(left != '.') {
          root.left = new Node(left);
        }
        if(right != '.') {
          root.right = new Node(right);
        }
      }
      else {
        searchNode(root, now, left, right);
      }
    }

    void searchNode(Node node, char now, char left, char right) {
      if(node == null) return;
      if(node.name == now) {
        if(left != '.') {
          node.left = new Node(left);
        }
        if(right != '.') {
          node.right =  new Node(right);
        }
      }
      else {
        searchNode(node.left, now, left, right);
        searchNode(node.right, now, left, right);
      }
    }

    void preOrder(Node node) {
      builder.append(node.name);
      if(node.left != null) preOrder(node.left);
      if(node.right != null) preOrder(node.right);
    }

    void inOrder(Node node) {
      if(node.left != null) inOrder(node.left);
      builder.append(node.name);
      if(node.right != null) inOrder(node.right);
    }

    void postOrder(Node node) {
      if(node.left != null) postOrder(node.left);
      if(node.right != null) postOrder(node.right);
      builder.append(node.name);
    }
  }
}
