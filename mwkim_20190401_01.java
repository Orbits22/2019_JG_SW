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
	static int[][] map;
	static boolean[] work;
	static double[] worked;
	
	public static void distribution(int p) {
		if(p == N) {
			result = Math.max(result, worked[N - 1] * 100.0);
			return;
		}

		for(int x = 0; x < N; x++) {
			if(work[x])
				continue;
			
			double probability = (p == 0 ? ((double)map[p][x] / 100.0) : worked[p - 1] * ((double)map[p][x] / 100.0));
			if(probability <= worked[p])
				continue;
			
			work[x] = true;
			worked[p] = probability;
			distribution(p + 1);
			worked[p] = 0;
			work[x] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			result = 0.0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			worked = new double[N];
			work = new boolean[N];
			
			for(int y = 0; y < N; y++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			distribution(0);
			System.out.printf("#" + test + " %.6f", result);
		}
	}

}
