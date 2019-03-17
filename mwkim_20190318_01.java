import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 1859 백만장자 프로젝트
 * @author 김명우
 *
 */
public class mwkim_20190318_01 {
	static long result;
	static int N;
	static int[] price;
	
	static void buySell() {
		int sell = price[N - 1];
		for(int i = N - 2; i >= 0; i--) {
			if(price[i] < sell)
				result += sell - price[i];
			else
				sell = price[i];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			N = Integer.parseInt(br.readLine());
			price = new int[N];
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			buySell();
			
			System.out.println("#" + test + " " + result);
		}
	}

}
