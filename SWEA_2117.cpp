#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
using namespace std;

int map[20][20];
int N, M, answer;

void input() {
	scanf("%d %d", &N, &M);
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++) 
			scanf("%d", &map[i][j]);
	answer = 0;
}

void solve(int r, int c, int k) {
	int cnt = 0, houseNum = 0;
	int cost, benefit;

	if (k == 1) {
		if (map[r][c] == 1)
			houseNum++;
	}
	else {
		for (int i = -(k - 1); i <= k - 1; i++) {

			if (i < 0) {
				for (int j = -cnt; j <= cnt; j++) {
					if ((0 <= r + i && r + i < N) && (0 <= c + j && c + j < N) && (map[r + i][c + j] == 1))
						houseNum++;
				}
				cnt++;
			}
			else if (i >= 0) {
				for (int j = -cnt; j <= cnt; j++) {
					if ((0 <= r + i && r + i < N) && (0 <= c + j && c + j < N) && (map[r + i][c + j] == 1))
						houseNum++;
				}
				cnt--;
			}
		}
	}
	cost = k * k + (k - 1) * (k - 1);
	benefit = (houseNum * M) - cost;

	if (benefit >= 0)
		answer = max(answer, houseNum);
}


int main() {
	int T, K;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		input();

		K = 1;
		while (true) 
		{
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					solve(i,j,K);
				}
			}

			if (K > N)	break;

			K++;
		}

		printf("#%d %d\n", t, answer);
	}
	return 0;
}

//
//void bfs(int r, int c, int endK) {
//	int K = 1, houseNum = 0;
//	int cost = 0, benefit = 0;
//
//	visited[r][c] = true;
//	if (map[r][c] == 1)
//		houseNum++;
//	Q.push(make_pair(make_pair(r, c), 1));
//
//	while (!Q.empty()) {
//		pair<pair<int, int>, int> p = Q.front();
//		Q.pop();
//
//		r = p.first.first;
//		c = p.first.second;
//		K = p.second;
//
//		if (endK + 1 == K) {
//			cost = K * K + (K - 1) * (K - 1);
//			benefit = (houseNum * M) - cost;
//
//			if (benefit >= 0)
//				answer = max(answer, houseNum);
//
//			break;
//		}
//
//		for (int i = 0; i < 4; i++) {
//			int calc_r = r + dr[i];
//			int calc_c = c + dc[i];
//
//			if ((0 <= calc_r && calc_r < N)
//				&& (0 <= calc_c && calc_c < N)
//				&& (visited[calc_r][calc_c] == false)) {
//
//				visited[calc_r][calc_c] = true;
//
//				if (map[calc_r][calc_c] == 1)
//					houseNum++;
//
//				Q.push(make_pair(make_pair(calc_r, calc_c), K + 1));
//			}
//		}
//
//	}
//	while (!Q.empty()) Q.pop();
//}