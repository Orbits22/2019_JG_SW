import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA 1824
 * @author 김명우
 *
 */
public class mwkim_20190308_01 {
	static int R, C, memory;
	static String result;
	static char[][] cmd;
	static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //동남서북
	
	static void execute() {
		Queue<int[]> queue = new LinkedList<int[]>();
		int[] temp = new int[3];
		temp[0] = 0;
		temp[1] = 0;
		temp[2] = 0;
		queue.add(temp);
		int loop = 0;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if(loop > (R * C * 2)) { //R * C 명령어를 2회전 했다면...
				return;
			}
			//System.out.println(loop + ": [" + cur[0] + "][" + cur[1] + "]: " + cmd[cur[0]][cur[1]]);
			
			switch(cmd[cur[0]][cur[1]]) {
			case '<':
				cur[2] = 2;
				break;
			case '>':
				cur[2] = 0;
				break;
			case '^':
				cur[2] = 3;
				break;
			case 'v':
				cur[2] = 1;
				break;
			case '_':
				if(memory == 0) cur[2] = 0;
				else cur[2] = 2;
				break;
			case '|':
				if(memory == 0) cur[2] = 1;
				else cur[2] = 3;
				break;
			case '?':
				for(int i = 0; i < 4; i++) {
					int[] next = new int[3];
					next[0] = cur[0] + move[i][0];
					next[1] = cur[1] + move[i][1];
					next[2] = i;
					
					if(i == 0 && next[1] >= C)
						next[1] = 0;
					else if(i == 1 && next[0] >= R)
						next[0] = 0;
					else if(i == 2 && next[1] < 0)
						next[1] = C - 1;
					else if(i == 3 && next[0] < 0)
						next[0] = R - 1;
					
					queue.add(next);
				}
				continue;
			case '.':
				break;
			case '@':
				result = "YES";
				return;
			case '+':
				if(memory == 15) memory = 0;
				else memory++;
				break;
			case '-':
				if(memory == 0) memory = 15;
				else memory--;
				break;
			default:
				memory = Character.getNumericValue(cmd[cur[0]][cur[1]]);
				break;
			}
			int[] next = new int[3];
			next[0] = cur[0] + move[cur[2]][0];
			next[1] = cur[1] + move[cur[2]][1];
			next[2] = cur[2];
			
			if(next[2] == 0 && next[1] >= C)
				next[1] = 0;
			else if(next[2] == 1 && next[0] >= R)
				next[0] = 0;
			else if(next[2] == 2 && next[1] < 0)
				next[1] = C - 1;
			else if(next[2] == 3 && next[0] < 0)
				next[0] = R - 1;
			
			loop++;
			queue.add(next);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			result = "NO";
			cmd = new char[R][C];
			memory = 0;
			
			for(int y = 0; y < R; y++) {
				String temp = br.readLine();
				cmd[y] = temp.toCharArray();
			}
			execute();
			System.out.println("#" + test + " " + result);
		}
	}

}
