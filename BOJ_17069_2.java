package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//파이프 옮기기 2
public class BOJ_17069_2 {

	static int N, map[][];
	static long D[][][];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		D = new long[N+1][N+1][3]; //D[i][j][d]: (1,1),0방향 -> (i, j),d방향으로 가는 경우의 수
		D[1][2][0] = 1;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==1&&j==2) continue;
				if(map[i][j]==1) continue;
				D[i][j][0] = D[i][j-1][0]+D[i][j-1][2];
				D[i][j][1] = D[i-1][j][1]+D[i-1][j][2];
				if(map[i-1][j-1]==0&&map[i-1][j]==0&&map[i][j-1]==0) {
					D[i][j][2] = D[i-1][j-1][0]+D[i-1][j-1][1]+D[i-1][j-1][2];
				}
			}
		}
		System.out.println(D[N][N][0]+D[N][N][1]+D[N][N][2]);
	}
}
