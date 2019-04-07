import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16986 {
	static int[][] table;
	static int N, K, result;
	static int[][] player;
	static boolean[] played;
	static int[] victory;
	
	static void playGame(int games) {
		if(victory[0] == K) {
			if(victory[0] > victory[1] && victory[0] > victory[2]) {
				result = 1;
			}
			return;
		}
		
		if(games == 20)
			return;
		
		int[] tmpV = victory.clone();
		boolean[] tmpP = played.clone();
		for(int p = 0; p < 2; p++) {
			for(int i = 1; i <= N; i++) {
				if(played[i])
					continue;

				if(table[i][player[p][games]] == 2)
					victory[0]++;
				else
					victory[p + 1]++;
				
				played[i] = true;
				break;
			}
		}
		playGame(games + 1);
		played = tmpP.clone();
		victory = tmpV.clone();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		table = new int[N + 1][N + 1];
		played = new boolean[N + 1];
		result = 0;
		victory = new int[3];
		for(int y = 1; y <= N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 1; x <= N; x++) {
				table[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		player = new int[2][20];
		for(int p = 0; p < 2; p++) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 20; i++) {
				player[p][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		playGame(0);

		System.out.println(result);
	}

}
