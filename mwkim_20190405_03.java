import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ16928
 * @author rlaau
 *
 */
public class mwkim_20190405_03 {
	static int[] map, visit;
	static int N, M, result;

	static void move() {
		Queue<int[]> queue = new LinkedList<int[]>(); //현재위치, 주사위던진수
		int[] temp = new int[2];
		temp[0] = 1;
		temp[1] = 0;
		queue.add(temp);
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if(cur[0] == 100) {
				result = Math.min(result, cur[1]);
			}
			
			for(int i = 1; i <= 6; i++) {
				int[] next = new int[2];
				next[0] = cur[0] + i;
				next[1] = cur[1] + 1;
				
				if(next[0] > 100)
					continue;
				
				if(map[next[0]] != 0)
					next[0] = map[next[0]];
				
				if(visit[next[0]] > 0 && visit[next[0]] <= next[1])
					continue;
				
				visit[next[0]] = next[1];
				queue.add(next);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[101];
		visit = new int[101];
		result = Integer.MAX_VALUE;
		for(int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map[start] = end;
		}
		
		move();
		
		System.out.println(result);
	}

}
