package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//보호 필름
public class SWEA_2112 {

	static int D, W, K, map[][], ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); //두께
			W = Integer.parseInt(st.nextToken()); //가로 크기
			K = Integer.parseInt(st.nextToken()); //합격 기준
			map = new int[D][W];
			ans = D;
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, 0);
			System.out.println("#"+T+" "+ans);
		}
	}
	static void dfs(int idx, int cnt) { //막 번호, 투입 횟수
		if(cnt>=ans) return;
		if(idx==D) { //모든 막에 대한 선택 완료
			if(check()) {
				ans = Math.min(ans, cnt);
			}
			return;
		}
		int[] temp = new int[W];
		for(int j=0; j<W; j++) {
			temp[j] = map[idx][j];
		}
		
		//약품 처리 x
		dfs(idx+1, cnt);
		
		//A약품 투입
		for(int j=0; j<W; j++) {
			map[idx][j] = 0;
		}
		dfs(idx+1, cnt+1);
		
		//B약품 투입
		for(int j=0; j<W; j++) {
			map[idx][j] = 1;
		}
		dfs(idx+1, cnt+1);
		
		//백트래킹
		for(int j=0; j<W; j++) {
			map[idx][j] = temp[j];
		}
	}
	static boolean check() {
		for(int j=0; j<W; j++) {
			int cnt = 1; //연속된 셀의 개수
			int s = map[0][j];
			boolean chk = false;
			for(int i=1; i<D; i++) {
				if(map[i][j]==s) {
					cnt++;
				}
				else {
					cnt = 1;
					s = map[i][j];
				}
				if(K<=cnt) {
					chk = true;
					break;
				}
			}
			if(!chk) return false;
		}
		return true;
	}
}
