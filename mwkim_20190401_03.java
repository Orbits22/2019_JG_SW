import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 4408. 자기 방으로 돌아가기
 * @author rlaau
 *
 */
public class mwkim_20190401_03 {
	static int N;
	static int[][] student;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			student = new int[N][2]; //현재방, 돌아갈방
			int[] root = new int[401];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				student[i][0] = Integer.parseInt(st.nextToken());
				student[i][1] = Integer.parseInt(st.nextToken());
				if(student[i][0] > student[i][1]) {
					int temp = student[i][0];
					student[i][0] = student[i][1];
					student[i][1] = temp;
				}
				int remainder = student[i][1] % 2;
				for(int r = student[i][0]; r <= (student[i][1] + remainder); r++) {
					root[r]++;
				}
			}
			
			Arrays.sort(root);
			
			System.out.println("#" + test + " " + root[400]);
		}
	}

}
