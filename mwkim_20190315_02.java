import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SWEA 1868: 파핑파핑 지뢰찾기
 * @author 김명우
 *
 */
public class mwkim_20190315_02 {
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			result = 0;
			
			System.out.println("#" + test + " " + result);
		}
	}
}
