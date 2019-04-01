package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//안경이 없어!
public class SWEA_7272 {

	static String[] str;
	static int[] H;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		H = new int['Z'+1];
		H['B'] = 2;
		H['A'] = H['D'] = H['O'] = H['P'] = H['Q'] = H['R'] = 1;
		int tc = Integer.parseInt(br.readLine());
		for(int T=1; T<=tc; T++) {
			str = new String[2];
			st = new StringTokenizer(br.readLine());
			str[0] = st.nextToken();
			str[1] = st.nextToken();
			String ans = "SAME";
			if(str[0].length()!=str[1].length()) {
				ans = "DIFF";
			}else {
				for(int i=0; i<str[0].length(); i++) {
					if(H[str[0].charAt(i)]!=H[str[1].charAt(i)]) {
						ans = "DIFF";
						break;
					}
				}
			}
			sb.append("#"+T+" "+ans+'\n');
		}
		System.out.println(sb);
	}

}
