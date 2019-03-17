import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * SWEA 5658
 * @author 김명우
 *
 */
public class mwkim_20190318_02 {
	static int result, N, K, M;
	static char[] pw;
	static ArrayList<Integer>pwList;

	static void makePW() {
		char[] newArr = pw.clone();
		
		for(int R = 0; R < N; R++) {
			if(R == N - 1)
				pw[0] = newArr[N - 1];
			else
				pw[R+1] = newArr[R];
		}
		addPW();
	}
	
	static void addPW() {
		for(int y = 0; y < 4; y++) {
			String temp = "";
			for(int x = 0; x < M; x++) {
				temp += Character.toString(pw[M * y + x]);
			}
			int hex = Integer.parseInt(temp, 16);
			boolean isExist = false;
			for(int i = 0; i < pwList.size(); i++) {
				if(pwList.get(i) == hex) {
					isExist = true;
					break;
				}
			}
			if(!isExist)
				pwList.add(hex);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = N / 4;
			pwList = new ArrayList<Integer>();
			String temp = br.readLine();
			pw = temp.toCharArray();
			addPW();
			for(int i = 0; i < M; i++) {			
				makePW();
			}
			Collections.sort(pwList);
			result = pwList.get(pwList.size() - K);
			
			System.out.println("#" + test + " " + result);
		}
	}

}
