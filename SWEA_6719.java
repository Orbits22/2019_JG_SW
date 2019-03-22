package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//성수의 프로그래밍 강좌 시청
public class SWEA_6719 {

	static int N, K, M[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				M[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(M); //1.정렬
			double A = 0;
			for(int i=N-K; i<N; i++) { //2.가장 큰 값 K개를 작은 값부터 처리해줌
				A = (A+M[i])/2;
			}
			System.out.println("#"+T+" "+A);
		}
	}

}
