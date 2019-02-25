#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<vector>
using namespace std;

int N, M, K;
int status[400][400]; // 1:비활성, 2:활성, 3:죽음
int maxLife[400][400]; // 생명력이 큰 것을 우선하기 위함
int dr[4] = { 0,0,-1,1 };
int dc[4] = { -1,1,0,0 };

typedef struct Cell {
	int r, c, life, changetime, status;
	bool first;
}Cell;

vector<Cell> cells, birth, alive;

void input() {
	scanf("%d %d %d", &N, &M, &K);

	cells.clear();
	for (int i = 0; i < 400; i++) {
		for (int j = 0; j < 400; j++) {
			status[i][j] = 0;
			maxLife[i][j] = 0;
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			int life;
			scanf("%d", &life);
			if (life != 0) {
				cells.push_back({ 150 + i,150 + j,life,life,1,true });
				status[150 + i][150 + j] = 1;
			}
		}
	}
}

int solve() {
	for (int time = 1; time <= K; time++) 
	{
		for (int i = 0; i < cells.size(); i++) {
			if (cells[i].status != 3) {

				if (cells[i].status == 1) { // 비활성
					if (time == cells[i].changetime) {
						cells[i].status = 2;
						status[cells[i].r][cells[i].c] = 2;
						cells[i].changetime = time + cells[i].life;
					}
				}

				else if (cells[i].status == 2) { // 활성
					if (cells[i].first == true) {//활성상태 첫 한시간
						//번식
						for (int p = 0; p < 4; p++) {
							int R, C;
							R = cells[i].r + dr[p];
							C = cells[i].c + dc[p];

							if (status[R][C] == 0 ) {
								if (maxLife[R][C] < cells[i].life) {
									birth.push_back({ R, C, cells[i].life,time+cells[i].life, 1, true });
									status[R][C] = 1;
									maxLife[R][C] = cells[i].life;
								}
							}
						}
						cells[i].first = false;
					}

					if (time == cells[i].changetime) {
						cells[i].status = 3;
						status[cells[i].r][cells[i].c] = 3;
					}
				}
			}
		}

		for (int j = 0; j < cells.size(); j++) {
			if (cells[j].status != 3)
				alive.push_back(cells[j]);
		}
		for (int j = 0; j < birth.size(); j++) {
			if (birth[j].life == maxLife[birth[j].r][birth[j].c]) {
				alive.push_back(birth[j]);
			}
		}

		cells.clear();
		for (int j = 0; j < alive.size(); j++) {
			cells.push_back(alive[j]);
		}

		alive.clear();
		birth.clear();
	}

	return (int)cells.size();
}

int main() {
	int T, answer;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		
		input();

		answer = solve();

		printf("#%d %d\n", t, answer);
	}
	return 0;
}