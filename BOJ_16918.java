package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//봄버맨
public class BOJ_16918 {

	static int R, C, N, map[][];
	static int[] dx = {-1,1,0,0}, dy = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			String row = br.readLine();
			for(int j=0; j<C; j++) {
				char ch = row.charAt(j);
				if(ch=='O') map[i][j] = 3;
			}
		}
		for(int t=1; t<=N; t++) {
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j]!=0) {
						map[i][j]--;
						if(map[i][j]==0) {
							for(int d=0; d<4; d++) {
								int px = i+dx[d];
								int py = j+dy[d];
								if(px<0||px>=R||py<0||py>=C) continue;
								if(map[px][py]==1&&(i+j<=px+py)) {
									//폭탄은 내버려둠~
								}else map[px][py] = 0;
							}
						}
					}else if(t%2==0){
						map[i][j] = 3;
					}
				}
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]==0) System.out.print(".");
				else System.out.print('O');
			}System.out.println();
		}
	}

}
