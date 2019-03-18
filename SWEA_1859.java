package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백만 장자 프로젝트
public class SWEA_1859 {

	static int N, arr[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 0;
			long sum = 0;
			for(int i=N-1; i>=0; i--) { //뒤에서부터 비교
				if(max<arr[i]) {
					max = arr[i];
				}else {
					sum+=max-arr[i];
				}
			}
			System.out.println("#"+T+" "+sum);
		}
	}

}
