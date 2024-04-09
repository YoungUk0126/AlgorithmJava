package basecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 좋은저녁 {

  static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder builder = new StringBuilder();
  static StringTokenizer tokens;

  static int N, M, V;
  static LinkNode[] graph;

  public static void main(String[] args) throws IOException {
    // TODO Auto-generated method stub
    input = new BufferedReader(new StringReader(str));
    tokens = new StringTokenizer(input.readLine());

    N = Integer.parseInt(tokens.nextToken());
    M = Integer.parseInt(tokens.nextToken());
    V = Integer.parseInt(tokens.nextToken());

    // 1. 그래프 생성( 인접행렬로 주어졌나요? 노드의 방문 순서를 편집해야 하나요?), 방향성(단방향, 양방향), 가중치

    graph = new LinkNode[N + 1]; // 현재는 1 base index 사용 중

    // 얘는 그래프 상태를 표현한 따로 만들은 그래프
    for (int m = 0; m < M; m++) {
      tokens = new StringTokenizer(input.readLine());
      int from = Integer.parseInt(tokens.nextToken());
      int to = Integer.parseInt(tokens.nextToken());
      // 양방향 그래프
      graph[from] = new LinkNode(to, graph[from]);
      graph[to] = new LinkNode(from, graph[to]);
    }

    bfs();
    System.out.println(builder);
    // next에 null을 넣어도 따로 간선 연결 상황을 graph배열에 다 넣어줬기 때문에 알아서 찾아줌
    builder = new StringBuilder();
    dfs(new LinkNode(V, null), new boolean[N + 1]);
    System.out.println(builder);
  }

  static void bfs() {
    // 1. 준비물!(큐, 방문배열)
    Queue<LinkNode> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[N + 1];

    // 2. 초기설정 - 처음 방문할 노드
    queue.offer(new LinkNode(V, null));
    visited[V] = true;

    // 3. 탐색
    while (!queue.isEmpty()) {
//			int size = queue.size();
//			builder.append(depth+" : "+size + " >> ");
//			while (size-- > 0) {
      // 3-1. 대장 데려오기
      LinkNode head = queue.poll();
      builder.append("depth: " + head.depth + ", 부모는: " + head.parent);
      // 3-2. 대장 사용하기
      builder.append(", 나는 :" + head.i).append(", ");

      // 3-3. 미방문인 자식 탐색하기
      for (LinkNode child = graph[head.i]; child != null; child = child.next) {
        // child라는 곳에 head랑 연결된 자식 노드를 받아와, child가 null이 아닐때 까지, child의 다음 노드 가져와
        if (!visited[child.i]) {
          visited[child.i] = true;
          child.depth = head.depth + 1;
          child.parent = head;
          queue.offer(child);
        }
      }
//			}
      builder.append("\n");
      // 기존 size 소진 완료!!
//			depth++;
    }
  }

  static void dfs(LinkNode node, boolean[] visited) {
    // 1. 방문처리
    visited[node.i] = true;
    // 2. 사용하기
    builder.append("depth : " + node.depth + ", 부모: " + node.parent + ", 나는? " + node.i).append("\n");
    // 3. 다음 자식 찾기
    for (LinkNode child = graph[node.i]; child != null; child = child.next) {
      if (!visited[child.i]) {
        child.parent = node;
        child.depth = node.depth + 1;
        dfs(child, visited);
      }
    }
  }


  static class LinkNode {
    int i;
    int depth;
    LinkNode next;
    LinkNode parent;

    public LinkNode(int i, LinkNode next) {
      super();
      this.i = i;
      this.next = next;
    }

    @Override
    public String toString() {
      return "LinkNode [i=" + i + ", next=" + next + "]";
    }

  }

  private static String str = "5 5 3\r\n" +
          "5 4\r\n" +
          "5 2\r\n" +
          "1 2\r\n" +
          "3 4\r\n" +
          "3 1";

}
