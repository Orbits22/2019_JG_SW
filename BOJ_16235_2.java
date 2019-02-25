package JG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//나무 재테크
public class BOJ_16235_2 {

	static int N, M, K, map[][], A[][];
	static int[] dx = {-1,-1,-1,0,0,1,1,1}, dy = {-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //땅 크기
		M = Integer.parseInt(st.nextToken()); //초기 나무 개수
		K = Integer.parseInt(st.nextToken()); //K년 후
		map = new int[N+1][N+1]; //남아있는 양분 
		A = new int[N+1][N+1]; //겨울에 각 칸에 추가할 양분
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; //초기 양분
			}
		}
		PriorityQueue<Tree> pq = new PriorityQueue<>(new Comparator<Tree>() { //살아있는 나무들

			@Override
			public int compare(Tree o1, Tree o2) { //나이 오름차순으로 정렬
				return o1.z-o2.z;
			}
		});
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			pq.add(new Tree(x, y, z));
		}
		Queue<Tree> temp = new LinkedList<>(); //다음 턴에 추가할 나무
		Queue<Info> temp_q = new LinkedList<>(); //죽은 나무 양분 변환 위함
		for(int time=1; time<=K; time++) {
			int qSize = pq.size();
			for(int i=0; i<qSize; i++) {
				Tree tree = pq.poll();
				if(map[tree.x][tree.y]>=tree.z) { //양분 섭취 가능
					//양분섭취
					map[tree.x][tree.y] -= tree.z; //땅의 양분 감소
					tree.z++; //나이 증가
					temp.add(tree);
					if(tree.z%5==0) { //증가한 나이가 5의 배수이면 번식
						for(int d=0; d<8; d++) {
							int px = tree.x+dx[d];
							int py = tree.y+dy[d];
							if(px<=0||px>N||py<=0||py>N) continue;
							temp.add(new Tree(px, py, 1));
						}
					}
				}else { //양분 섭취 불가능
					temp_q.add(new Info(tree.x, tree.y, tree.z/2));
				}
			}
			//정보 변경된 나무, 새로 생성된 나무 pq에 추가
			while(!temp.isEmpty()) {
				pq.add(temp.poll());
			}
			//A만큼 각 칸에 양분 추가
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					map[i][j]+=A[i][j];
				}
			}
			//죽은 나무 양분으로 추가
			while(!temp_q.isEmpty()) {
				Info info = temp_q.poll();
				map[info.x][info.y]+=info.v;
			}
		}
		
		System.out.println(pq.size());
	}

}
class Tree{
	int x, y, z; //위치, 나이
	Tree(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
class Info{
	int x, y, v; //위치, 양
	Info(int x, int y, int v){
		this.x = x;
		this.y = y;
		this.v = v;
	}
}