import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 5650 핀볼 게임
 * @author rlaau
 *
 */
public class mwkim_20190315_03 {
	static int result, N;
	static int[][] map;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][][] w_hole;
	static boolean check[][][];
	
	static int changeD(int type, int d) {
		int new_d = (d + 2) % 4; //0 -> 2 | 1 -> 3 | 2 -> 0 | 3 -> 1
		
		switch(type) {
		case 1:
			switch(d) {
			case 2:
				new_d = 1;
				break;
			case 3:
				new_d = 0;
				break;
			}
			break;
		case 2:
			switch(d) {
			case 0:
				new_d = 1;
				break;
			case 3:
				new_d = 2;
				break;
			}
			break;
		case 3:
			switch(d) {
			case 0:
				new_d = 3;
				break;
			case 1:
				new_d = 2;
				break;
			}
			break;
		case 4:
			switch(d) {
			case 1:
				new_d = 0;
				break;
			case 2:
				new_d = 3;
				break;
			}
			break;
		}
		
		return new_d;
	}
	
	static void moving(int[] first) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(first);
		int moved = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int[] next = new int[4];
			next[0] = cur[0] + move[cur[2]][0];
			next[1] = cur[1] + move[cur[2]][1];
			
			if(next[0] > N || next[1] > N || next[0] < 1 || next[1] < 1) {
				next[2] = changeD(0, cur[2]);
				next[3] = cur[3] + 1;
			}
			else if(map[next[0]][next[1]] > 0 && map[next[0]][next[1]] < 6) {
				next[2] = changeD(map[next[0]][next[1]], cur[2]);
				next[3] = cur[3] + 1;
			}
			else if(map[next[0]][next[1]] > 5) {
				int wh = map[next[0]][next[1]] - 6;
				for(int i = 0; i < 2; i++) {
					if(w_hole[wh][i][0] != next[0] && w_hole[wh][i][1] != next[1]) {
						next[0] = w_hole[wh][i][0];
						next[1] = w_hole[wh][i][1];
						break;
					}
				}
				next[2] = cur[2];
				next[3] = cur[3];
			}
			else {
				next[2] = cur[2];
				next[3] = cur[3];
			}

			if(check[next[0]][next[1]][next[2]])
				continue;
			
			//if(first[0] == 3 && first[1] == 4)
			//System.out.println("[" + first[0] + "][" +first[1] +  "][" + first[2] + "] | [" + cur[0] + "][" + cur[1] + "][" + cur[2] + "] -> [" + next[0] + "][" + next[1] + "][" + next[2] + "] | " + next[3] + " :::: " + map[next[0]][next[1]]);
			
			if((moved > 0 && next[0] == first[0] && next[1] == first[1]) || map[next[0]][next[1]] == -1) {
				result = Math.max(result, cur[3]);
				return;
			}
			
			check[next[0]][next[1]][next[2]] = true;
			queue.add(next);
			
			moved++;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine().trim());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			result = 0;
			map = new int[N + 2][N + 2];
			w_hole = new int[5][2][2];
			int[] w_holes = new int[5];
			
			for(int y = 1; y <= N; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 1; x <= N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					
					if(map[y][x] > 5) {
						w_hole[map[y][x] - 6][w_holes[map[y][x] - 6]][0] = y;
						w_hole[map[y][x] - 6][w_holes[map[y][x] - 6]][1] = x;
						w_holes[map[y][x] - 6]++;
					}
				}
			}
			
			for(int y = 1; y <= N; y++) {
				for(int x = 1; x <= N; x++) {
					if(map[y][x] != 0)
						continue;
					
					int[] first = new int[4]; //y좌표 x좌표 방향 포인트
					first[0] = y;
					first[1] = x;
					first[3] = 0;
					check = new boolean[N + 2][N + 2][4];
					for(int d = 0; d < 4; d++) {
						first[2] = d;
						moving(first);
					}
				}
			}
			
			System.out.println("#" + test + " " + result);
		}
	}

}
