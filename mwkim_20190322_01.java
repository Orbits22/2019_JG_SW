import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1953. 탈주범검거 (40분)
 * @author rlaau
 *
 */
public class mwkim_20190322_01 {
	static int N, M, result, L;
	static int[][] map;
	static boolean[][] tunnel = {{false, false, false, false}, 
											{true, true, true, true}, 
											{true, false, true, false}, 
											{false, true, false, true}, 
											{true, true, false, false}, 
											{false, true, true, false}, 
											{false, false, true, true}, 
											{true, false, false, true}}; //북동남서
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean[][] visit;
	
	static void moving(int[] first) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(first);
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curT = map[cur[0]][cur[1]];
			
			//System.out.println(cur[2] + "시간: [" + cur[0] + "][" + cur[1] + "]: " + curT);
			
			for(int i = 0; i < 4; i++) {
				if(!tunnel[curT][i])
					continue;
				
				int[] next = new int[3];
				next[0] = cur[0] + (tunnel[curT][i] ? move[i][0] : 0);
				next[1] = cur[1] + (tunnel[curT][i] ? move[i][1] : 0);
				
				if(next[0] >= N || next[1] >= M || next[0] < 0 || next[1] < 0)
					continue;
				
				int nextT = map[next[0]][next[1]];
				if(nextT == 0)
					continue;
				
				if(!tunnel[nextT][(i + 2) % 4])
					continue;
				
				if(visit[next[0]][next[1]])
					continue;
				
				next[2] = cur[2] + 1;
				if(next[2] > L)
					continue;
				
				visit[next[0]][next[1]] = true;
				result++;
				queue.add(next);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int[] first = new int[3]; //y, x, 시간
			first[0] = Integer.parseInt(st.nextToken());
			first[1] = Integer.parseInt(st.nextToken());
			first[2] = 1;
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visit = new boolean[N][M];
			visit[first[0]][first[1]] = true;
			result = 1;

			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < M; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			moving(first);
			
			System.out.println("#" + test + " " + result);
		}
	}

}
