import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA 7272. 안경이 없어!
 * @author 김명우
 *
 */
public class mwkim_20190401_02 {
	
	static int alphabet(char ch) {
		if(ch == 'B')
			return 2;
		else if(ch == 'A' || ch == 'D' || ch == 'O' || ch == 'P' || ch == 'Q' || ch == 'R')
			return 1;
		else
			return 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int test = 1; test <= testCase; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char[] string1 = st.nextToken().toCharArray();
			char[] string2 = st.nextToken().toCharArray();
			
			if(string1.length != string2.length) {
				System.out.println("#" + test + " DIFF");
			}
			else {
				boolean pass = false;
				for(int i = 0; i < string1.length; i++) {
					int a= 0, b = 0;
					a = alphabet(string1[i]);
					b = alphabet(string2[i]);
					
					if(a != b) {
						System.out.println("#" + test + " DIFF");
						pass = false;
						break;
					}
					pass = true;
				}
				if(pass)
					System.out.println("#" + test + " SAME");
			}
		}
	}

}
