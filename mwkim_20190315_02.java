import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SWEA 1868: 파핑파핑 지뢰찾기
 * @author 김명우
 *
 */
public class mwkim_20190315_02 {
	static int result, N, dotNum;
	static char[][] matrix;
	
	static boolean check() {
		int checked = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(matrix[y][x] == '-')
					checked++;
			}
		}
		return (checked == dotNum);
	}
	
	static void click() {
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			matrix = new char[N][N];
			dotNum = 0;
			for(int y = 0; y < N; y++) {
				String temp = br.readLine();
				matrix[y] = temp.toCharArray();
				for(int x = 0; x < N; x++) {
					if(matrix[y][x] == '.')
						dotNum++;
				}
			}
			
			for(int y = 0; y < N; y++) {
				for(int x = 0; x < N; x++) {
					
				}
			}
			
			System.out.println("#" + test + " " + result);
		}
	}
}
