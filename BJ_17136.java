import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17136 {
	static int[][] paper;
	static int result;
	static int[] used;
	
	static boolean test() {
		boolean pass = true;
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				if(paper[y][x] != 0) {
					return false;
				}
			}
		}
		
		return pass;
	}
	
	static void arrayCopy(int[][] temp, int[][] origin) {
		for(int y = 0; y < 10; y++) {
			for(int x = 0; x < 10; x++) {
				temp[y][x] = origin[y][x];
			}
		}
	}
	
	static void dfs(int cur_y, int cur_x, int num) {
		if(cur_y == 10 || test()) {
			result = Math.min(result, num);
			return;
		}
		
		if(cur_x == 10) {
			dfs(cur_y + 1, 0, num);
			return;
		}
		
		if(paper[cur_y][cur_x] == 0) {
			dfs(cur_y, cur_x + 1, num);
			return;
		}
		
		int[][] temp = new int[10][10];
		arrayCopy(temp, paper);
		for(int p = 4; p >= 0; p--) {
			boolean pass = true;
			loop: 
			for(int y = cur_y; y <= cur_y + p; y++) {
				if(y >= 10) {
					pass = false;
					break loop;
				}
				for(int x = cur_x; x <= cur_x + p; x++) {
					if(x >= 10) {
						pass = false;
						break loop;
					}
					
					if(paper[y][x] == 0) {
						pass = false;
						break loop;
					}
				}
			}
			
			if(!pass)
				continue;
			
			if(used[p] >= 5)
				continue;
			
			if(result <= num + 1)
				continue;
			
			for(int y = cur_y; y <= cur_y + p; y++) {
				for(int x = cur_x; x <= cur_x + p; x++) {
					paper[y][x]--;
				}
			}
			
			used[p]++;
			dfs(cur_y, cur_x + 1, num + 1);
			used[p]--;
			arrayCopy(paper, temp);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		paper = new int[10][10];
		used = new int[5];
		result = Integer.MAX_VALUE;
		for(int y = 0; y < 10; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x = 0; x < 10; x++) {
				paper[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

}
