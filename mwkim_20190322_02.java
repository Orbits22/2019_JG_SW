import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA 6719. 성수의 프로그래밍 강좌 시청
 * @author 김명우
 *
 */
public class mwkim_20190322_02 {
	static double result;
	static int N, K;
	static int[] program;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			program = new int[N];
			result = 0.0;
			for(int i = 0; i < N; i++) {
				program[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(program);
			for(int i = N - K; i < N; i++) {
				result = (result + (double)program[i]) / 2;
			}
			
			System.out.printf("#%S %.6f", test, result);
			System.out.println("");
		}
	}

}
