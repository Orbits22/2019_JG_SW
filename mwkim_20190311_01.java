import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 2112
 * @author 김명우
 *
 */
public class mwkim_20190311_01 {
	static int D, W, K, result;
	static int[][] films;
	
	static boolean test() {
		boolean pass = false;
		for(int x = 0; x < W; x++) {
			int film = 0;
			int count = 0;
			for(int y = 0; y < D; y++){
				if(film == films[y][x]) {
					count++;
					if(count >= K) {
						pass = true;
						break;
					}
				}
				else {
					film = films[y][x];
					count = 1;
					pass = false;
				}
			}
			if(!pass)
				break;
		}
		
		return pass;
	}
	
	static void insert(int cur_y, int num) {
		if(result > num && test()) {
			result = num;
			return;
		}
		
		if(num >= K)
			return;

		if(cur_y == D) {
			return;
		}
		
		int[] temp = films[cur_y].clone();
		for(int i = 0; i < 2; i++) {
			for(int x = 0; x < W; x++) {
				films[cur_y][x] = i;
			}
			insert(cur_y + 1, num + 1);
		}
		films[cur_y] = temp.clone();
		insert(cur_y + 1, num);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			films = new int[D][W];
			
			for(int y = 0; y < D; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < W; x++) {
					films[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = Integer.MAX_VALUE;
			
			insert(0, 0);
			System.out.println("#" + test + " " + result);
		}
	}

}
