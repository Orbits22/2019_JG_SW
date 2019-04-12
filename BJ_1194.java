import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1194 {
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int N, M, result;
	static char[][] maze;
	static boolean[][][][][][][][] visit;
	
	static void moving(int[] first, boolean[][] finish) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(first);
		visit[first[0]][first[1]][0][0][0][0][0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			System.out.println(cur[2] + ": [" + cur[0] + "][" + cur[1] + "] - " + cur[3] + cur[4] + cur[5] + cur[6] + cur[7] + cur[8]);
			
			if(finish[cur[0]][cur[1]]) {
				result = Math.min(result, cur[2]);
				return;
			}
			
			for(int d = 0; d < 4; d++) {
				int[] next = cur.clone();
				next[0] = cur[0] + move[d][0];
				next[1] = cur[1] + move[d][1];
				
				if(next[0] >= N || next[1] >= M || next[0] < 0 || next[1] < 0)
					continue;
				
				if(maze[next[0]][next[1]] == '#')
					continue;
				
				//문자 (a ~ f: 97 ~ 102, A ~ F: 65 ~ 70)
				if(Character.isAlphabetic(maze[next[0]][next[1]]) && 
						Character.isUpperCase(maze[next[0]][next[1]])) {
					if(next[(int)maze[next[0]][next[1]] - 62] == 0)
						continue;
				}
				
				if(Character.isAlphabetic(maze[next[0]][next[1]]) && 
						Character.isLowerCase(maze[next[0]][next[1]])) {
					next[(int)maze[next[0]][next[1]] - 94] = 1;
				}
				
				if(visit[next[0]][next[1]][next[3]][next[4]][next[5]][next[6]][next[7]][next[8]])
					continue;
				
				visit[next[0]][next[1]][next[3]][next[4]][next[5]][next[6]][next[7]][next[8]] = true;
				next[2] = cur[2] + 1;
				queue.add(next);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		visit = new boolean[N][M][2][2][2][2][2][2]; //각 좌표 및 열쇠 유무
		result = Integer.MAX_VALUE;
		int[] first = new int[9]; //y, x, 이동횟수, 열쇠 a~f
		for(int i = 3; i < 9; i++) {
			first[i] = 0;
		}
		
		boolean[][] finish = new boolean[N][M];
		for(int y = 0; y < N; y++) {
			String temp = br.readLine();
			maze[y] = temp.toCharArray();
			for(int x = 0; x < M; x++) {
				if(maze[y][x] == '0') {
					maze[y][x] = '.';
					first[0] = y;
					first[1] = x;
					first[2] = 0;
				}
				else if(maze[y][x] == '1') {
					finish[y][x] = true;
				}
			}
		}
		
		moving(first, finish);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

}
