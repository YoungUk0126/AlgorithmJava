package bj.s2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1부터 N까지의 수를 스택에 넣었다가 뽑아서 수열을 만들거임
 * 스택에 push하는 순서는 반드시 오름차순을 지키자.
 * 임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지,
 * 있다면 어떤 순서로 push와 pop을 해야하는지
 *
 * 스택에는 1부터 N까지 오름차순 순서로 push를 함
 * 입력에서 주어지는 첫번째 숫자가 나올때까지 push한 후
 * stack.peek랑 입력이랑 같으면 pop '-'출력
 * 이런식으로 입력을 계속 받아 N이랑 스택이랑 push받은 횟수가 같아지면
 * 종료하고 스택에 남은거 있으면 NO출력
 */
public class BJ_S2_1874_스택수열 {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder builder = new StringBuilder();


    static Stack<Integer> stack = new Stack<Integer>();
    static Queue<Integer> inputQueue = new ArrayDeque<>();
    static int N;
    static int needPop;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(input.readLine());

        for(int i=0; i<N; i++){
            inputQueue.offer(Integer.parseInt(input.readLine()));
        }
        needPop = inputQueue.poll();
        int i = 1;
        while(i <= N){
            if(stack.isEmpty() || stack.peek() != needPop) {
                builder.append("+\n");
                stack.push(i++);
            }
            else {
                builder.append("-\n");
                stack.pop();
                if(inputQueue.isEmpty()) break;
                needPop = inputQueue.poll();
            }
        }
        // 스택에 남은 값이 있으면 또 비교해
        while(!stack.isEmpty()){
            if(stack.peek() == needPop){
                builder.append("-\n");
                stack.pop();
                if(inputQueue.isEmpty()) break;
                needPop = inputQueue.poll();
            } else {
                System.out.println("NO");
                break;
            }
        }
        if(stack.isEmpty())
            System.out.print(builder);

    }
}
