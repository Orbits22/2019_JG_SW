import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ6087: 레이저 통신
 * @author rlaau
 *
 */
public class mwkim_20190405_02 {
	static int H, W, min;
	static char[][] map;
	static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북동남서
	static int[][] reflection = {{1, 0, 3, 2}, {3, 2, 1, 0}}; //[0] : '/' [1] : '＼'

	static void move(int[] first, int[] last) {
		Queue<int[]>queue = new LinkedList<int[]>();
		queue.add(first);
		int[][][] visit = new int[H][W][4];
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			//System.out.println("[" + cur[0] + "][" + cur[1] + "]: " + cur[2] + " | " + cur[3]);
			
			if(cur[0] == last[0] && cur[1] == last[1]) {
				min = Math.min(min, cur[3]);
			}
			
			//거울 사용 안하는 경우
			int[] next = new int[4];
			next[0] = cur[0] + move[cur[2]][0];
			next[1] = cur[1] + move[cur[2]][1];
			next[2] = cur[2];
			next[3] = cur[3];
			if(next[0] < H && next[1] < W && next[0] >= 0 && next[1] >= 0) {
				if(map[next[0]][next[1]] == '.' && (visit[next[0]][next[1]][next[2]] == 0 || visit[next[0]][next[1]][next[2]] > next[3])) {
					visit[next[0]][next[1]][next[2]] = next[3];
					queue.add(next);
				}
			}
			
			//거울 사용하는 경우
			for(int type = 0; type < 2; type++) {
				next = new int[4];
				next[0] = cur[0] + move[cur[2]][0];
				next[1] = cur[1] + move[cur[2]][1];
				next[2] = reflection[type][cur[2]];
				next[3] = cur[3] + 1;
				if(next[0] < H && next[1] < W && next[0] >= 0 && next[1] >= 0) {
					if(map[next[0]][next[1]] == '.' && (visit[next[0]][next[1]][next[2]] == 0 || visit[next[0]][next[1]][next[2]] > next[3])) {
						visit[next[0]][next[1]][next[2]] = next[3];
						queue.add(next);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		int[][] laser = new int[2][4]; //y, x, 방향, 거울사용 횟수, 움직인 횟수
		int l = 0;
		min = Integer.MAX_VALUE;
		
		for(int y = 0; y < H; y++) {
			String temp = br.readLine();
			map[y] = temp.toCharArray();
			for(int x = 0; x < W; x++) {
				if(map[y][x] == 'C') {
					map[y][x] = '.';
					laser[l][0] = y;
					laser[l][1] = x;
					laser[l][3] = 0;
					l++;
				}
			}
		}
		
		for(int d = 0; d < 4; d++) {
			laser[0][2] = d;
			move(laser[0], laser[1]);
		}
		
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}

}
