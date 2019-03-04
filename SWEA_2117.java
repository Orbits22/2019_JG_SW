package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//홈 방범 서비스
public class SWEA_2117 {

	static int N, M, map[][], ans, house;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ans = 0;
			house = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) house++;
				}
			}
			for(int i=0; i<=N; i++) {
				for(int j=0; j<=N; j++) {//중심좌표(i,j)
					for(int k=N+1; k>=1; k--) { //영역의 크기
						int c = k*k+(k-1)*(k-1); //운영 비용
						if(c>house*M) continue; //모든 집 포함해도 운영 비용이 더 많이 든다
						int h = count(i, j, k); //영역 안에 있는 집의 수
						if(h*M>=c) { //손해x
							ans = Math.max(ans, h);
							break; //더 작은 k는 검사할 필요x
						}
					}
				}
			}
			System.out.println("#"+T+" "+ans);
		}
	}
	static int count(int x, int y, int k) {
		int cnt = 0;
		int len = 1;
		for(int i=x-k+1; i<=x+k-1; i++) {
			for(int j=y-len+1; j<=y+len-1; j++) {
				if(i<0||i>=N||j<0||j>=N) continue;
				if(map[i][j]==1) cnt++;
			}
			if(i-(x-k+1)+1<k) len++;
			else len--;
		}
		return cnt;
	}

}
