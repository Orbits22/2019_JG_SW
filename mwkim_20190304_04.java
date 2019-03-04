import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * <pre>
 * SW Expert Academy 4014
 * </pre>
 * @author rlaau
 *
 */
public class mwkim_20190304_04 {
    static int N, L, result;
    static int[][] matrix;
    static int[][] build;
     
    static void check(boolean X) {
        if(X) {
            for(int y = 0; y < N; y++) {
                boolean possible = false;
                for(int x = 0; x < N - 1; x++) {
                    int sub = matrix[y][x] - matrix[y][x + 1];
                    if(sub == 0)
                        possible = true;
                    else if(sub == 1){
                        if(build[y][x + 1] == -1)
                            possible = true;
                        else {
                            possible = false;
                            break;
                        }
                    }
                    else if(sub == -1) {
                        if(build[y][x] == 1)
                            possible = true;
                        else {
                            possible = false;
                            break;
                        }
                    }
                    else {
                        possible = false;
                        break;
                    }
                }
                if(possible) {
                    /*
                     * System.out.println("XXXXXXXXX: " + y); for(int x = 0; x < N; x++) {
                     * System.out.print(matrix[y][x] + "(" + build[y][x] + ") \t"); }
                     * System.out.println("");
                     */
                    result++;
                }
            }
        }
        else {
            for(int x = 0; x < N; x++) {
                boolean possible = false;
                for(int y = 0; y < N - 1; y++) {
                    int sub = matrix[y][x] - matrix[y + 1][x];
                    if(sub == 0)
                        possible = true;
                    else if(sub == 1){
                        if(build[y + 1][x] == -1)
                            possible = true;
                        else {
                            possible = false;
                            break;
                        }
                    }
                    else if(sub == -1) {
                        if(build[y][x] == 1)
                            possible = true;
                        else {
                            possible = false;
                            break;
                        }
                    }
                    else {
                        possible = false;
                        break;
                    }
                }
                if(possible) {
                    /*
                     * System.out.println("YYYYYYYYY: " + x); for(int y = 0; y < N; y++) {
                     * System.out.print(matrix[y][x] + "(" + build[y][x] + ") \t"); }
                     * System.out.println("");
                     */
                    result++;
                }
            }
        }
    }
     
    static void buildingX(int y, int start, int end, boolean plus) {
        while(start != end) {
            if(plus) {
                if(start + L <= end) {
                    boolean isBuilded = false;
                    for(int x = start; x < start + L; x++) {
                        if(build[y][x] != 0) {
                            isBuilded = true;
                            break;
                        }
                    }
                    if(!isBuilded) {
                        boolean isCont = true;
                        for(int x = start; x < start + L; x++) {
                            if(isCont && matrix[y][start + L] - matrix[y][x] == 1) {
                                isCont = true;
                            }
                            else
                                isCont = false;
                        }
                         
                        if(isCont) {
                            for(int x = start; x < start + L; x++) {
                                build[y][x] = 1;
                            }
                        }
                    }
                }
 
                start++;
            }
            else {
                if(start - L >= end) {
                    boolean isBuilded = false;
                    for(int x = start; x > start - L; x--) {
                        if(build[y][x] != 0) {
                            isBuilded = true;
                            break;
                        }
                    }
                    if(!isBuilded) {
                        boolean isCont = true;
                        for(int x = start; x > start - L; x--) {
                            if(isCont && matrix[y][start - L] - matrix[y][x] == 1) {
                                isCont = true;
                            }
                            else
                                isCont = false;
                        }
                         
                        if(isCont) {
                            for(int x = start; x > start - L; x--) {
                                build[y][x] = -1;
                            }
                        }
                    }
                }
                start--;
            }
        }
    }
     
    static void buildingY(int x, int start, int end, boolean plus) {
        while(start != end) {
            if(plus) {
                if(start + L <= end) {
                    boolean isBuilded = false;
                    for(int y = start; y < start + L; y++) {
                        if(build[y][x] != 0) {
                            isBuilded = true;
                            break;
                        }
                    }
                    if(!isBuilded) {
                        boolean isCont = true;
                        for(int y = start; y < start + L; y++) {
                            if(isCont && matrix[start + L][x] - matrix[y][x] == 1) {
                                isCont = true;
                            }
                            else
                                isCont = false;
                        }
                         
                        if(isCont) {
                            for(int y = start; y < start + L; y++) {
                                build[y][x] = 1;
                            }
                        }
                    }
                }
 
                start++;
            }
            else {
                if(start - L >= end) {
                    boolean isBuilded = false;
                    for(int y = start; y > start - L; y--) {
                        if(build[y][x] != 0) {
                            isBuilded = true;
                            break;
                        }
                    }
                    if(!isBuilded) {
                        boolean isCont = true;
                        for(int y = start; y > start - L; y--) {
                            if(isCont && matrix[start - L][x] - matrix[y][x] == 1) {
                                isCont = true;
                            }
                            else
                                isCont = false;
                        }
                         
                        if(isCont) {
                            for(int y = start; y > start - L; y--) {
                                build[y][x] = -1;
                            }
                        }
                    }
                }
                start--;
            }
        }
    }
     
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        int testCase = Integer.parseInt(br.readLine());
         
        for(int test = 1; test <= testCase; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            matrix = new int[N][N];
            result = 0;
             
            for(int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine());
                for(int x = 0; x < N; x++) {
                    matrix[y][x] = Integer.parseInt(st.nextToken());
                }
            }
             
            //X에 대한 경사로 세우기
            build = new int[N][N]; //+되는 방향에서 설치하면 1, -되는 방향에서 설치하면 -1
            for(int y = 0; y < N; y++) {
                buildingX(y, 0, N - 1, true); //x가 +되는 방향으로 경사로 설치
                buildingX(y, N - 1, 0, false); //x가 -되는 방향으로 경사로 설치
            }
            check(true);
             
            //Y에 대한 경사로 세우기
            build = new int[N][N];
            for(int x = 0; x < N; x++) {
                buildingY(x, 0, N - 1, true);
                buildingY(x, N - 1, 0, false);
            }
            check(false);
             
            System.out.println("#" + test + " " + result);
        }
    }
 
}