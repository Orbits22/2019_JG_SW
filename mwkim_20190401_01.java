import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1865. 동철이의 일 분배
 * @author 김명우
 *
 */
public class mwkim_20190401_01 {
	static int N;
	static double result;
	static double[][] map;
	static boolean[] work;
	static double[] worked;
	
	public static void distribution(int p, double calculated) {
		if(result >= calculated)
			return;
		
		if(p == N) {
			result = Math.max(result, calculated);
			return;
		}

		for(int x = 0; x < N; x++) {
			if(work[x])
				continue;
			
			work[x] = true;
			distribution(p + 1, calculated * map[p][x]);
			work[x] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			result = 0.0;
			N = Integer.parseInt(br.readLine());
			map = new double[N][N];
			worked = new double[N];
			work = new boolean[N];
			
			for(int y = 0; y < N; y++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; x++) {
					map[y][x] = (double)Integer.parseInt(st.nextToken()) / 100.0;
				}
			}
			distribution(0, 100);
			System.out.printf("#" + test + " %.6f", result);
			System.out.println("");
		}
	}

}
