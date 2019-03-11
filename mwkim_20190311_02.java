import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ 3055
 * @author rlaau
 *
 */
public class mwkim_20190311_02 {
	static int R, C, result;
	static char[][] map;
	static Queue<int[]> water, S;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean[][] visit;

	static void moving() {
		while(!S.isEmpty()) {
			int[] cur = S.poll();
			/*
			 * System.out.println("[" + cur[2] + "]: =================[" + cur[0] + "][" +
			 * cur[1] + "]"); for(int y = 0; y < R; y++) { for(int x = 0; x < C; x++) {
			 * System.out.print(map[y][x] + "\t"); } System.out.println(""); }
			 */
			if(map[cur[0]][cur[1]] == 'D') {
				result = Math.min(result, cur[2]);
				break;
			}
			
			//물
			while(!water.isEmpty() && water.peek()[2] == cur[2]) {
				int[] curW = water.poll();
				
				for(int i = 0; i < 4; i++) {
					int[] nextW = new int[3];
					nextW[0] = curW[0] + move[i][0];
					nextW[1] = curW[1] + move[i][1];
					
					if(nextW[0] >= R || nextW[1] >= C || nextW[0] < 0 || nextW[1] < 0)
						continue;
					
					if(map[nextW[0]][nextW[1]] != '.')
						continue;
					
					map[nextW[0]][nextW[1]] = '*';
					nextW[2] = curW[2] + 1;
					water.add(nextW);
				}
			}
			
			//고슴도치
			for(int i = 0; i < 4; i++) {
				int[] nextS = new int[3];
				nextS[0] = cur[0] + move[i][0];
				nextS[1] = cur[1] + move[i][1];
				
				if(nextS[0] >= R || nextS[1] >= C || nextS[0] < 0 || nextS[1] < 0)
					continue;
				
				if(map[nextS[0]][nextS[1]] == '*' || map[nextS[0]][nextS[1]] == 'X')
					continue;
				
				boolean pass = true;
				for(int n = 0; n < 4; n++) {
					int nnY = nextS[0] + move[n][0];
					int nnX = nextS[1] + move[n][1];
					
					if(nnY >= R || nnX >= C || nnY < 0 || nnX < 0)
						continue;
					
					if(visit[nnY][nnX])
						continue;
					
					if(map[nnY][nnX] == '*' && n == i) {
						pass = false;
					}
				}
				
				if(map[nextS[0]][nextS[1]] == '.' && !pass)
					continue;
				
				if(visit[nextS[0]][nextS[1]])
					continue;
				
				visit[nextS[0]][nextS[1]] = true;
				nextS[2] = cur[2] + 1;
				S.add(nextS);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		result = Integer.MAX_VALUE;
		S = new LinkedList<int[]>();
		water = new LinkedList<int[]>();
		visit = new boolean[R][C];
		map = new char[R][C];
		
		for(int y = 0; y < R; y++) {
			String temp = br.readLine();
			map[y] = temp.toCharArray();
			
			for(int x = 0; x < C; x++) {
				if(map[y][x] == 'S') {
					map[y][x] = '.';
					int[] tmp = new int[3]; //y, x, 시간
					tmp[0] = y;
					tmp[1] = x;
					tmp[2] = 0;
					S.add(tmp);
				}
				else if(map[y][x] == '*') {
					int[] tmp = new int[3]; //y, x, 시간
					tmp[0] = y;
					tmp[1] = x;
					tmp[2] = 0;
					water.add(tmp);
				}
			}
		}
		moving();
		System.out.println(result == Integer.MAX_VALUE ? "KAKTUS" : result);
	}

}
