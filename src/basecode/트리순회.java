package basecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://hoehen-flug.tistory.com/48

public class 트리순회 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuilder builder = new StringBuilder();

    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());

        BinaryTree tree = new BinaryTree();


        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(input.readLine());
            char A = tokens.nextToken().charAt(0);
            char B = tokens.nextToken().charAt(0);
            char C = tokens.nextToken().charAt(0);

            tree.insert(A, B, C);
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
            if (root == null) {// 루트가 비었으면 그냥 넣어
                root = new Node(now);
                if (left != '.') {
                    root.left = new Node(left);
                }
                if (right != '.') {
                    root.right = new Node(right);
                }
            } else {// 아니면 연결된 노드 찾아
                searchNode(root, now, left, right);
            }
        }

        void searchNode(Node node, char now, char left, char right) {
            if (node == null) return;// 그럴일 없겠지만 null이면 그냥 찾지마
            if (node.name == now) {// 이번 노드의 값과 내가 찾아야 할 값이 같다면 이 노드의 left, right를 넣어줘
                if (left != '.') {
                    node.left = new Node(left);
                }
                if (right != '.') {
                    node.right = new Node(right);
                }
            } else {
                searchNode(node.left, now, left, right);// 이번 노드의 값이 내가 찾아야 할 값과 다르다면 왼쪽부터 찾아
                searchNode(node.right, now, left, right);// 왼쪽에서 못찾았으면 오른쪽도 찾아
            }
        }

        void preOrder(Node node) {// 전위 순회 루트 -> 왼쪽 -> 오른쪽
            builder.append(node.name);
            if (node.left != null) preOrder(node.left);
            if (node.right != null) preOrder(node.right);
        }

        void inOrder(Node node) {// 중위 순회 왼쪽 -> 루트 -> 오른쪽
            if (node.left != null) inOrder(node.left);
            builder.append(node.name);
            if (node.right != null) inOrder(node.right);
        }

        void postOrder(Node node) {// 후위 순회 왼쪽 -> 오른쪽 -> 루트
            if (node.left != null) postOrder(node.left);
            if (node.right != null) postOrder(node.right);
            builder.append(node.name);
        }
    }
}
