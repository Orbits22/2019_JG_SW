import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3055 {
	static int R, C, result;
	static char[][] map;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static Queue<int[]> water;
	static boolean[][] visit;
	
	static void run(int[] S) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(S);
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			//System.out.println(cur[2] + "초 [" + cur[0] + "][" + cur[1] + "]: " + map[cur[0]][cur[1]]);
			
			if(map[cur[0]][cur[1]] == 'D') {
				result = Math.min(result, cur[2]);
				break;
			}

			
			//물
			while(!water.isEmpty() && water.peek()[2] == cur[2]) {
				int[] curWater = water.poll();
				for(int i = 0; i < 4; i++) {
					int[] newW = new int[3];
					newW[0] = curWater[0] + move[i][0];
					newW[1] = curWater[1] + move[i][1];
					
					if(newW[0] >= R || newW[1] >= C || newW[0] < 0 || newW[1] < 0)
						continue;
					
					if(map[newW[0]][newW[1]] == '.') {
						map[newW[0]][newW[1]] = '*';
						newW[2] = curWater[2] + 1;
						water.add(newW);
					}
				}
			}
			
			//고슴도치
			for(int i = 0; i < 4; i++) {
				int[] newS = new int[3];
				newS[0] = cur[0] + move[i][0];
				newS[1] = cur[1] + move[i][1];
				
				if(newS[0] >= R || newS[1] >= C || newS[0] < 0 || newS[1] < 0)
					continue;
				
				if(visit[newS[0]][newS[1]])
					continue;
				
				loop:
				for(int w = 0; w < 4; w++) {
					int new_y = newS[0] + move[i][0];
					int new_x = newS[1] + move[i][1];
					
					if(new_y >= R || new_x >= C || new_y < 0 || new_x < 0)
						continue;
					
					if(map[newS[0]][newS[1]] == '.' && map[new_y][new_x] == '*')
						continue loop;
				}
				
				if(map[newS[0]][newS[1]] == '.' || map[newS[0]][newS[1]] == 'D') {
					newS[2] = cur[2] + 1;
					visit[newS[0]][newS[1]] = true;
					queue.add(newS);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[] S = new int[3];
		int[] W = new int[3];
		water = new LinkedList<int[]>();
		map = new char[R][C];
		visit = new boolean[R][C];
		result = Integer.MAX_VALUE;
		
		for(int y = 0; y < R; y++) {
			String temp = br.readLine();
			map[y] = temp.toCharArray();
			
			for(int x = 0; x < C; x++) {
				if(map[y][x] == 'S') {
					S[0] = y;
					S[1] = x;
					S[2] = 0;
					map[y][x] = '.';
					visit[y][x] = true;
				}
				else if(map[y][x] == '*') {
					W[0] = y;
					W[1] = x;
					W[2] = 0;
					water.add(W);
				}
			}
		}
		run(S);
		
		System.out.println(result == Integer.MAX_VALUE ? "KAKTUS" : result);
	}

}
