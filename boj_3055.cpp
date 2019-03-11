#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<queue>
#include<cstring>
#include<algorithm>
using namespace std;

typedef struct point {
	int r, c, d;
}Point;

queue<Point> nowQ, nextQ, waterStartPoints;
queue<Point> gosmNowQ, gosmNextQ;
Point S, D;

char map[50][50], waterMap[50][50];
int Gvisited[50][50], Wvisited[50][50];
int dr[4] = {-1,1,0,0};
int dc[4] = {0,0,-1,1};
int R, C, answer = 987654321;

void copyQ(queue<Point>& now, queue<Point>& next) {
	while (!next.empty()) {
		now.push(next.front());
		next.pop();
	}
}

void flood() {
	Point w;
	int r, c;

	while (!waterStartPoints.empty()) {
		w = waterStartPoints.front();
		waterStartPoints.pop();

		r = w.r;
		c = w.c;

		for (int i = 0; i < 4; i++) {
			int rr = r + dr[i];
			int cc = c + dc[i];
			if ((0 <= rr && rr < R) &&
				(0 <= cc && cc < C) &&
				waterMap[rr][cc] != 'X' &&
				waterMap[rr][cc] != 'D' &&
				Wvisited[rr][cc] == false ) {

				Wvisited[rr][cc] = true;
				waterMap[rr][cc] = '*';
				nextQ.push({ rr,cc });
			}
		}
	}

}

void Gosm_Go() {
	Point g;
	int r, c, d;

	while (!gosmNowQ.empty()) {
		g = gosmNowQ.front();
		gosmNowQ.pop();

		r = g.r;
		c = g.c;
		d = g.d;

		if (map[r][c] == 'D')
			answer = min(answer , d);
		else
			map[r][c] = 'S';

		for (int i = 0; i < 4; i++) {
			int rr = r + dr[i];
			int cc = c + dc[i];
			if ((0 <= rr && rr < R) &&
				(0 <= cc && cc < C) &&
				map[rr][cc] != 'X' &&
				waterMap[rr][cc] != '*' &&
				Gvisited[rr][cc] == false) {

				Gvisited[rr][cc] = true;
				gosmNextQ.push({ rr,cc,d+1 });
			}
		}
	}
	copyQ(gosmNowQ, gosmNextQ);
}

void init() {
	cin >> R >> C;
	for (int i = 0; i < R; i++)
		for (int j = 0; j < C; j++) {
			cin >> map[i][j];
			waterMap[i][j] = map[i][j];
			if (map[i][j] == '*') {
				waterStartPoints.push({ i,j,0 });
			}
			else if (map[i][j] == 'S') {
				gosmNowQ.push({ i,j,0 });
			}
			else if (map[i][j] == 'D') {
				D = { i,j,0 };
			}
		}

	memset(Wvisited, 0, sizeof(Wvisited));
	memset(Gvisited, 0, sizeof(Gvisited));
}

void AllWatersFlood() {
	while (!waterStartPoints.empty()) {
		flood();
	}
	copyQ(waterStartPoints, nextQ);
}

void move() {

	while (
		!nowQ.empty() ||
		!nextQ.empty() ||
		!gosmNowQ.empty() ||
		!gosmNextQ.empty()) {

		AllWatersFlood();
		Gosm_Go();
	}
}

int main() {
	init();
	move();
	if (answer == 987654321)
		cout << "KAKTUS";
	else
		cout << answer;
	return 0;
}