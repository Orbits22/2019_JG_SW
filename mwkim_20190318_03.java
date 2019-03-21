import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * SWEA 통합검색 4727. 견우와 직녀 (18.8.6 수정)
 * @author rlaau
 *
 */
public class mwkim_20190318_03 {
	static int result, N, M;
	static int[][] map;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] visit;

	static void moving(int cur_y, int cur_x, int time, int recentB, boolean crossOver) {
		//System.out.println(time + "초: [" + cur_y + "][" + cur_x + "]: " + recentB + " | " + crossOver);
		if(cur_y == N - 1 && cur_x == N - 1) {
			result = Math.min(result, time);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int new_y = cur_y + move[i][0];
			int new_x = cur_x + move[i][1];
			
			if(new_y >= N || new_x >= N || new_y < 0 || new_x < 0)
				continue;
			
			if(visit[new_y][new_x] > 0 && visit[new_y][new_x] <= time)
				continue;
			
			int waiting = 1;
			int newB = recentB;
			boolean newCross = crossOver;
			//주기 다리를 건넌다
			if(map[new_y][new_x] > 1) {
				//최근에 다리를 건넜으면 건너띄기
				if(newCross)
					continue;
				
				if(map[new_y][new_x] > time)
					waiting = map[new_y][new_x] - time;
				newCross = true;
			}
			//다리를 만든다
			else if(map[new_y][new_x] == 0) {
				if(newCross)
					continue;
				
				//교차로 검증
				boolean conn = false;
				for(int b = 0; b < 5; b++) { //북동남서북
					int b_y = new_y + move[b % 4][0];
					int b_x = new_x + move[b % 4][1];
					
					if(b_y >= N || b_x >= N || b_y < 0 || b_x < 0)
						continue;
					
					if(map[b_y][b_x] == 0) {
						if(conn)
							break;
						else
							conn = true;
					}
					else
						conn = false;
				}
				//이어진 부분이 3개이상이면 건너띄기
				if(conn)
					continue;
				
				newB = recentB + M;
				newCross = true;
				//현재 시각이 최근다리만든 시각 + M보다 작으면 대기시간 있음
				if(time < newB) {
					waiting = recentB + M - time;
				}
				else {
					newB = time + M;
					waiting = M;
				}
			}
			else
				newCross = false;
			
			visit[new_y][new_x] = time + waiting;
			moving(new_y, new_x, time + waiting, newB, newCross);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			result = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visit = new int[N][N];
			
			for(int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			visit[0][0] = 0;
			moving(0, 0, 0, 0, false);
			System.out.println("#" + test + " " + (result == Integer.MAX_VALUE ? 0 : result));
		}
	}

}
