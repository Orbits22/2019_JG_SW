#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <queue>
#include <vector>
using namespace std;

char map[101][101] = { 0, };
int visit[101][101] = { 0, };
int W = 0, H = 0;
int razor[2][2] = { 0, };
int mov_r[4] = { -1,0,1,0 };
int mov_c[4] = { 0,1,0,-1 };
int min_result = 99999;

int bfs() {
	queue<pair<pair<int, int>, int>> q;
	q.push({{ razor[0][0],razor[0][1] }, 1});
	while (!q.empty()) {
		int tmp_r = q.front().first.first;
		int tmp_c = q.front().first.second;
		int tmp_deep = q.front().second;
		q.pop();

		if (min_result + 1 <= tmp_deep){
			return 0;
		}

		for (int i = 0; i < 4; i++) {
			int next_r = tmp_r;
			int next_c = tmp_c;
			while (1) {
				next_r = next_r + mov_r[i];
				next_c = next_c + mov_c[i];
				
				if (!((0 <= next_r && next_r < H) && (0 <= next_c && next_c < W)) || map[next_r][next_c] == '*') { // 범위 벗어나거나 벽인 경우
					break;
				}
				if (next_r == razor[1][0] && next_c == razor[1][1]) { // C 에 도달한 경우
					if (min_result > tmp_deep) {
						min_result = tmp_deep;
					}
					break;
				}
				if (visit[next_r][next_c] != 0 && visit[next_r][next_c] <= tmp_deep || (next_r == razor[0][0] && next_c == razor[0][1])) { // 다음좌표가 이미 방문했던 좌표고 필요거울 개수가 현재가 더 크면 넘어감 || 다음 좌표가 첫 C인 경우
					break;
				}
				
				if (map[tmp_r][tmp_c] == 'C') {
					q.push({ { next_r, next_c }, tmp_deep});
					visit[next_r][next_c] = tmp_deep;
				}
				else {
					q.push({ { next_r, next_c }, tmp_deep + 1 });
					visit[next_r][next_c] = tmp_deep + 1;
				}
			}
		}
	}
	return 0;
}

int main() {
	int i = 0;
	int r_cnt = 0;
	char trash = 0;
	scanf("%d %d\n", &W, &H);
	for (int i = 0; i < H; i++) {
		for (int j = 0; j < W; j++) {
			scanf("%c", &map[i][j]);
			if (map[i][j] == 'C') {
				razor[r_cnt][0] = i;
				razor[r_cnt][1] = j;
				r_cnt++;
			}
		}
		scanf("%c", &trash);
	}
	//printf("--------------------\n");
	//for (int i = 0; i < H; i++) {
	//	for (int j = 0; j < W; j++) {
	//		printf("%c", map[i][j]);
	//	}
	//	printf("\n");
	//}
	bfs();
	printf("%d",min_result);
	return 0;
}