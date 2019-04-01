package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//동철이의 일 분배
public class SWEA_1865 {

	static int N;
	static boolean[] selected;
	static double P[][], ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			N = Integer.parseInt(br.readLine());
			P = new double[N][N];
			selected = new boolean[N];
			ans = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					P[i][j] = Integer.parseInt(st.nextToken())/100.0;
				}
			}
			dfs(0, 100);
			sb.append("#"+T+" "+String.format("%.6f", ans)+'\n');
		}
		System.out.println(sb);
	}
	static void dfs(int idx, double p) {
		if(ans>=p) return;
		if(idx==N) {
			ans = Math.max(ans, p);
			return;
		}
		for(int i=0; i<N; i++) {
			if(!selected[i]) {
				selected[i] = true;
				dfs(idx+1, p*P[idx][i]);
				selected[i] = false;
			}
		}
	}

}
