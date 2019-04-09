import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * SWEA_7333: 한솔이의 택배 아르바이트
 * @author 김명우
 *
 */
public class mwkim_20190408_03 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			int result = 0;
			int N = Integer.parseInt(br.readLine());
			int[] box = new int[N + 1];
			for(int i = 1; i <= N; i++) {
				box[i] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(box);
			
			int min = 0;
			for(int max = N; max >= 1; max--) {
				if(box[max] >= 50) {
					result++;
					//System.out.println(result + ": " + box[max]);
				}
				else {
					int boxes = 1;
					for(int idx = min; idx < max; idx++) {
						if(box[max] * (boxes) >= 50) {
							min = idx;
							result++;
							//System.out.println(result + ": " + box[max] + " * " + boxes + ": " + min);
							break;
						}
						boxes++;
					}
				}
			}
			
			System.out.println("#" + test + " " + result);
		}
	}

}
