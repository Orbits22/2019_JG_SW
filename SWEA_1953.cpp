#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<queue>
using namespace std;

typedef struct Point {
	int r, c, type, side, depth;
}Point;

int N, M, R, C, L;
int map[51][51];
bool visited[51][51];
int dr[4] = { -1,1,0,0 };
int dc[4] = { 0,0,-1,1 };
int cnt;

queue<Point> Q;

void init() {
	scanf("%d %d %d %d %d", &N, &M, &R, &C, &L);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			scanf("%d", &map[i][j]);
			visited[i][j] = false;
		}
	}

	cnt = 0;
}

bool isConnected(int R, int C, int preType, int inputSide) {
	switch (map[R][C]) {
	case 1: {
		if (inputSide == 0) {
			if (preType == 1 || preType == 2 || preType == 4 || preType == 7)
				return true;
		}
		else if (inputSide == 1) {
			if (preType == 1 || preType == 2 || preType == 5 || preType == 6)
				return true;
		}
		else if (inputSide == 2) {
			if (preType == 1 || preType == 3 || preType == 6 || preType == 7)
				return true;
		}
		else if (inputSide == 3) {
			if (preType == 1 || preType == 3 || preType == 4 || preType == 5)
				return true;
		}
		break;
	}
	case 2: {
		if (inputSide == 0) {
			if (preType == 1 || preType == 2 || preType == 4 || preType == 7)
				return true;
		}
		else if (inputSide == 1) {
			if (preType == 1 || preType == 2 || preType == 5 || preType == 6)
				return true;
		}
		break;
	}
	case 3: {
		if (inputSide == 2) {
			if (preType == 1 || preType == 3 || preType == 6 || preType == 7)
				return true;
		}
		else if (inputSide == 3) {
			if (preType == 1 || preType == 3 || preType == 4 || preType == 5)
				return true;
		}
		break;
	}
	case 4: {
		if (inputSide == 1) {
			if (preType == 1 || preType == 2 || preType == 5 || preType == 6)
				return true;
		}
		else if (inputSide == 2) {
			if (preType == 1 || preType == 3 || preType == 6 || preType == 7)
				return true;
		}
		break;
	}
	case 5: {
		if (inputSide == 0) {
			if (preType == 1 || preType == 2 || preType == 4 || preType == 7)
				return true;
		}
		else if (inputSide == 2) {
			if (preType == 1 || preType == 3 || preType == 6 || preType == 7)
				return true;
		}
		break;
	}
	case 6: {
		if (inputSide == 0) {
			if (preType == 1 || preType == 2 || preType == 4 || preType == 7)
				return true;
		}
		else if (inputSide == 3) {
			if (preType == 1 || preType == 3 || preType == 4 || preType == 5)
				return true;
		}
		break;
	}
	case 7: {
		if (inputSide == 1) {
			if (preType == 1 || preType == 2 || preType == 5 || preType == 6)
				return true;
		}
		else if (inputSide == 3) {
			if (preType == 1 || preType == 3 || preType == 4 || preType == 5)
				return true;
		}
		break;
	}
	}
	return false;
}

void bfs(int R, int C, int type) {
	Q.push({ R,C, type, 1 ,1 });
	visited[R][C] = true;
	cnt++;

	while (!Q.empty()) 
	{
		int rr, cc, t, s, d;
		Point p = Q.front();
		Q.pop();

		t = p.type;
		s = p.side;
		d = p.depth;

		for (int i = 0; i < 4; i++) {
			rr = p.r + dr[i];
			cc = p.c + dc[i];

			if (0 <= rr && rr <= N &&
				0 <= cc && cc <= M) {

				if (visited[rr][cc] == false &&
					isConnected(rr, cc, t, i) &&
					d+1 <= L)
				{
					Q.push({ rr,cc,map[rr][cc],i,d+1 });
					visited[rr][cc] = true;
					cnt++;
				}
			}
		}
	}
}

int solve() {
	bfs(R,C,map[R][C]);
	return cnt;
}

int main() {
	int T, answer;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		init();
		answer = solve();
		printf("#%d %d\n", t, answer);
	}
	return 0;
}