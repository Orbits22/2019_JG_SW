#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<queue>
using namespace std;

typedef struct Point {
	int r, c;
}Point;

int N;
int map[100][100];
int dist[100][100];
int dr[4] = { 0,1,0,-1 };
int dc[4] = { 1,0,-1,0 };

queue<Point> Q;

void init() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%1d", &map[i][j]);
			dist[i][j] = 987654321;
		}
	}
}

void bfs(int r, int c) {
	int rr, cc;
	Point p = { r,c };

	Q.push(p);
	dist[0][0] = 0;
	while (!Q.empty()) {
		p = Q.front();
		Q.pop();

		r = p.r;
		c = p.c;

		for (int i = 0; i < 4; i++) {
			rr = r + dr[i];
			cc = c + dc[i];
			if (0 <= rr && rr < N &&
				0 <= cc && cc < N &&
				dist[rr][cc] > map[rr][cc] + dist[r][c])
			{
				dist[rr][cc] = map[rr][cc] + dist[r][c];
				Q.push({ rr,cc });
			}
		}
	}
}

int main() {
	int T;
	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {

		init();
		bfs(0, 0);

		printf("#%d %d\n", t, dist[N - 1][N - 1]);
	}
	return 0;
}