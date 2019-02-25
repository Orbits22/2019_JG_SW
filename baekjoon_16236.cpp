#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <memory.h>
using namespace std;

int N = 0;
int map[20][20] = { 0, };
bool visit[20][20] = { 0, };
int move_r[4] = { -1, 0, 1, 0 };// 북 동 남 서
int move_c[4] = { 0, 1, 0, -1 }; 
typedef pair<int, int> pair_rc;
vector<pair<int,pair_rc>> fishes; // distance, r,c
int s_r, s_c, s_size = 2, s_eat = 0;

pair<int,pair_rc> bfs() {
	int bfs_r = 0 , bfs_c = 0;
	memset(visit, 0, sizeof(visit)); // visit 초기화
	vector<pair<int, pair_rc>> tmp_fishes;
	queue<pair<int, pair_rc>> q;
	q.push({ 0, {s_r, s_c } }); // dist, r,c  // start node가 상어 위치
	while (!q.empty()) {
		int tmp_r = q.front().second.first;
		int tmp_c = q.front().second.second;
		int tmp_dist = q.front().first;
		q.pop();

		if (visit[tmp_r][tmp_c] == true) // 이미 방문 했던 곳이면 팝만해서 제거하고 다음 큐를 팝
			continue;

		if (map[tmp_r][tmp_c] != 0 && map[tmp_r][tmp_c] < s_size) // bfs로 이동해 나가면서 먹을 수 있는 물고기를 판단하고 벡터에 저장
			tmp_fishes.push_back({ tmp_dist, {tmp_r, tmp_c} });	      // 이렇게 하면 bfs를 하면서 먹을 수 있는 물고기를 찾음과 동시에 거리 계산 가능

		visit[tmp_r][tmp_c] = true; // 첫 방문이면 방문지역이라고 체크

		for (int i = 0; i < 4; i++) { // 4방향 탐색
			bfs_r = tmp_r + move_r[i];
			bfs_c = tmp_c + move_c[i];
			if ((0 <= bfs_r && bfs_r < N) && (0 <= bfs_c && bfs_c < N) && (map[bfs_r][bfs_c] <= s_size))// 4방향 탐색 후 이동할 수 있으면 q에 push
				q.push({ tmp_dist + 1, {bfs_r, bfs_c} }); 
		}
	}
	if (tmp_fishes.empty() == true)
		return{ -1, {-1,-1} };

	sort(tmp_fishes.begin(), tmp_fishes.end()); // 가까운 거리 순으로 sorting
	//printf("==================================\n");
	//for (int i = 0; i < tmp_fishes.size(); i++)
	//{
	//	printf("%d : (%d, %d)\n", tmp_fishes[i].first, tmp_fishes[i].second.first, tmp_fishes[i].second.second);
	//}
	return{ tmp_fishes[0].first, { tmp_fishes[0].second.first, tmp_fishes[0].second.second } }; // sorting된 후 첫 번째가 가장 가깝고 먹을 수 있는 물고기이기에 그 정보 리턴
}

int eat(pair<int, pair_rc> eat_fish) {

	s_eat++; // eat함수에 들어오면 무조건 가까운 1마리 먹는다는 것이기에 먹은 물고기 크기가 아닌 숫자를 의미하는 s_eat을 ++

	if (s_eat == s_size) {// 먹은 물고기 수와 상어 크기가 같을 때 1성장
		s_size++;
		s_eat = 0;         // 먹은 물고기 초기화
	}
	//map[s_r][s_c] = 0;
	s_r = eat_fish.second.first;  // 먹은 물고기 좌표로 상어 좌표 대체
	s_c = eat_fish.second.second; 
	map[eat_fish.second.first][eat_fish.second.second] = 0; // 먹은 물고기 0으로

	return 0;
}

int main() {

	int i = 0;
	int j = 0;
	int time = 0;
	pair<int, pair_rc> tmp;
	scanf("%d", &N);
	for (i = 0; i < N; i++){
		for (j = 0; j < N; j++) {
			scanf("%d", &map[i][j]);
			if (map[i][j] != 0) {
				if (map[i][j] == 9) {
					s_r = i;
					s_c = j;
				}
				else {
					fishes.push_back({ 0,{i,j} });
				}
			}
		}
	}

	while (1) {
		tmp = bfs();
		if (tmp.first == -1) // 거리 탐색 -1은 갈수있는 경우가 없을 때
			break;

		eat(tmp); // 먹고 성장, 먹은 물고기map값 0만들기
		time = time + tmp.first;

		//printf("------------------------------\n");
		//for (i = 0; i < N; i++) {
		//	for (j = 0; j < N; j++) {
		//		printf("%d ", map[i][j]);
		//	}
		//	printf("\n");
		//}
		//printf("%d %d %d \n",time, s_size, s_eat);
	}
	printf("%d", time);
	//printf("end");
}

//#include <iostream>
//#include <queue>
//#include <vector>
//#include <algorithm>
//using namespace std;
//
//int N = 0;
//int map[20][20] = { 0, };
//int min_d = 9999;
//
//typedef struct fish {
//	int distance, r, c, f_size;
//}Fish;
//typedef struct shark {
//	int r, c;
//	int s_size = 2;
//	int eat_size;
//}Shark;
//
//vector<Fish> fishes;
//vector<Fish> go_eat_fishes;
//int go_eat_fishes_cnt = 0;
//int move_flag = 0;
//
//queue<Fish> eat_fish_Q;
//Shark baby_shark;
//
////int sort() {
////	int i = 0;
////	int j = 0;
////	int t0 = 0;
////	int t1 = 0;
////	int t2 = 0;
////
////	for (i = 0; i < go_eat_fishes_cnt; i++) {
////		for (j = 0; j < go_eat_fishes_cnt; j++) {
////			if (go_eat_fishes[i].distance > go_eat_fishes[j].distance)
////			{
////				t0 = go_eat_fishes[i].r;
////				t1 = go_eat_fishes[i].c;
////				t2 = go_eat_fishes[i].distance;
////				go_eat_fishes[i].r = go_eat_fishes[j].r;
////				go_eat_fishes[i].c = go_eat_fishes[j].c;
////				go_eat_fishes[i].distance = go_eat_fishes[j].distance;
////				go_eat_fishes[j].r = t0;
////				go_eat_fishes[j].c = t1;
////				go_eat_fishes[j].distance = t2;
////			}
////		}
////	}
////	return 0;
////}
//
//int find() {
//	int cnt = 0;
//	Fish tmp;
//	queue<Fish> tmpQ;
//	for (int i = 0; i < N; i++) {
//		for (int j = 0; j < N; j++) {
//			if (map[i][j] < baby_shark.s_size && map[i][j] > 0) {
//				tmp.r = i;
//				tmp.c = j;
//				tmp.f_size = map[i][j];
//				//tmpQ.push(tmp);
//				eat_fish_Q.push(tmp);
//			}
//		}
//	}
//	//for (int i = 0; i < tmpQ.size(); i++) {
//	//	tmp = tmpQ.back();
//	//	tmpQ.pop();
//	//	printf("%d %d : %d\n", tmp.r, tmp.c , tmp.f_size);
//	//}
//	if (eat_fish_Q.empty() == 1)
//		return 0;
//	return eat_fish_Q.size();
//}
//int move(int s_r, int s_c, int f_r, int f_c, int prev_dir, int level) {
//
//}
//int move_check() {
//	Fish temp;
//	temp = eat_fish_Q.back();
//	eat_fish_Q.pop();
//	move(baby_shark.r, baby_shark.c, temp.r, temp.c, 0, 1);
//	if (move_flag == 1) {
//		go_eat_fishes[go_eat_fishes_cnt].r = temp.r;
//		go_eat_fishes[go_eat_fishes_cnt].c = temp.c;
//		go_eat_fishes[go_eat_fishes_cnt++].distance = min_d;
//		min_d = 9999;
//	}
//	return 0;
//}
//
//int grow() {
//	if (baby_shark.eat_size >= baby_shark.s_size) {
//		baby_shark.s_size++;
//		return 1;
//	}
//	return 0;
//}
//
//int eat(int num) {
//	baby_shark.eat_size = baby_shark.eat_size + map[go_eat_fishes[num].r][go_eat_fishes[num].c];
//	map[go_eat_fishes[num].r][go_eat_fishes[num].c] = 0;
//	return 0;
//}
//
//int main() {
//
//	int i = 0;
//	int j = 0;
//	int time = 0;
//	int eat_fish_num = 0;
//	int go_fish_num = 0; // 갈 수 있는 먹을 고기 수
//	bool grow_flag = 1; // 처음엔 find할수있도록 1로 초기화
//	Fish tmp;
//
//	scanf("%d", &N);
//	for (i = 0; i < N; i++)
//		for (j = 0; j < N; j++) {
//			scanf("%d", &map[i][j]);
//			if (map[i][j] == 9) {
//				baby_shark.r = i;
//				baby_shark.c = j;
//			}
//		}
//
//	tmp
//
//		sort(tmp.begin(), tmp.end());
//
//	while (1)
//	{
//		if (grow_flag == 1) {
//			eat_fish_num = find();
//			grow_flag = 0;
//		}
//		if (eat_fish_num == 0)// 먹을수 있는 물고기 x break;
//		{
//			break;
//		}
//		else if (eat_fish_num == 1)//1마리일때
//		{
//			move_check();
//			if (go_eat_fishes.empty() == true)// 이동가능 판단
//				break;
//			eat_fish_num = eat(0);
//			grow_flag = grow();
//		}
//		else if (eat_fish_num > 1) // 1마리이상
//		{
//			for (i = 0; i < eat_fish_num; i++) {
//				move_check();  // 이동 불가능한지 판단
//				if (go_eat_fishes.empty() == true)// 이동 모두 불가능 판단
//					break;
//			}
//			//sort();
//			eat_fish_num = eat(0);
//			grow_flag = grow();
//		}
//		time = time + go_eat_fishes[0].distance;
//	}
//
//	printf("%d ", time);
//}