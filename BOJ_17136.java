package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//색종이 붙이기
public class BOJ_17136 {

	static int R=10, C=10, map[][], num[], ans, INF=987654321;;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[R][C];
		num = new int[5];
		num[0] = num[1] = num[2] = num[3] = num[4] = 5;
		ans = INF;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0, 0);
		if(ans==INF) ans = -1;
		System.out.println(ans);
	}
	static void dfs(int x, int y, int cnt) {
		for(int i=x; i<R; i++) {
			for(int j=y; j<C; j++) {
				if(map[i][j]==1) {
					for(int k=4; k>=0; k--) {
						if(num[k]==0) continue;
						if(check(i, j, k)) {
							reverse(i, j, k);
							num[k]--;
							dfs(i, j, cnt+1);
							reverse(i, j, k);
							num[k]++;
						}
					}
					return;
				}
			}
			y=0;
		}
		//다 덮임
		ans = Math.min(ans, cnt);
	}
	static void reverse(int i, int j, int k) {
		for(int r=i; r<=i+k; r++) {
			for(int c=j; c<=j+k; c++) {
				map[r][c]=1-map[r][c];
			}
		}
	}
	static boolean check(int i, int j, int k) {
		for(int r=i; r<=i+k; r++) {
			for(int c=j; c<=j+k; c++) {
				if(r<0||r>=R||c<0||c>=C) return false;
				if(map[r][c]==0) return false;
			}
		}
		return true;
	}

}
