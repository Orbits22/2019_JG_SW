#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<algorithm>
#include<string>
#include<cstring>
#include<vector>
using namespace std;

typedef struct bc {
	int r, c, C, P;
}BC;

BC BClist[8];
int answer1,answer2;
int map[11][11], M, A;
int area[8][11][11];
int dr[5] = { 0,-1,0,1,0 };
int dc[5] = { 0,0,1,0,-1 };
int path1[100], path2[100];
int r1, c1, r2, c2;

void setArea(int BCnum, int r, int c, int C, int P) {
	int pos = 0;
	for (int i = r - C; i <= r + C; i++) {
		if (i < r) {
			for (int j = c - pos; j <= c + pos; j++) {
				if ((1 <= i && i <= 10)
					&& (1 <= j && j <= 10)) {
					area[BCnum][i][j] = P;
				}
			}
			pos++;
		}
		else {
			for (int j = c - pos; j <= c + pos; j++) {
				if ((1 <= i && i <= 10)
					&& (1 <= j && j <= 10)) {
					area[BCnum][i][j] = P;
				}
			}
			pos--;
		}
	}
}

void init() {
	memset(area,0,sizeof(area));
	cin >> M >> A;
	
	for (int i = 0; i < M; i++)
		cin >> path1[i];
	for (int i = 0; i < M; i++)
		cin >> path2[i];
	
	for (int i = 0; i < A; i++)
		cin >> BClist[i].c >> BClist[i].r >> BClist[i].C >> BClist[i].P;

	for (int i = 0; i < A; i++)
		setArea(i, BClist[i].r, BClist[i].c, BClist[i].C, BClist[i].P);

}


void setV(int i, int r, int c, vector<pair<int, int>> &v) {
	int p;
	if (area[i][r][c] != 0) {
		p = BClist[i].P;
		v.push_back(make_pair(i, p));
	}
}

int calc() {
	vector<pair<int, int>> v1, v2;
	int ret = 0;

	for (int i = 0; i < A; i++) {
		setV(i, r1, c1, v1);
		setV(i, r2, c2, v2);
	}
	
	//v1에는 사람1이 접속할 수 있는 BC의 정보 저장
	if (v1.size() != 0 && v2.size() != 0)
	{
		for (int i = 0; i < v1.size(); i++) {
			for (int j = 0; j < v2.size(); j++) {
				int BCnum[2], p[2];
				BCnum[0] = v1[i].first;
				BCnum[1] = v2[j].first;
				p[0] = v1[i].second;
				p[1] = v2[j].second;
				if (BCnum[0] == BCnum[1]) {
					answer1 = p[0]/2;
					answer2 = p[1]/2;
				}
				else {
					answer1 = p[0];
					answer2 = p[1];
				}
				ret = max(ret, answer1 + answer2);
			}
		}
	}
	else if (v1.size() == 0) {
		for (int i = 0; i < v2.size(); i++) {
			ret = max(ret, v2[i].second);
		}
	}
	else if (v2.size() == 0) {
		for (int i = 0; i < v1.size(); i++) {
			ret = max(ret, v1[i].second);
		}
	}

	return ret;
}

int solve() {
	int t = 0, p = 0, sum = 0;

	r1 = c1 = 1;
	r2 = c2 = 10;
	sum += calc();

	for (int i = 0; i < M; i++) {
		r1 += dr[path1[i]];
		c1 += dc[path1[i]];
		r2 += dr[path2[i]];
		c2 += dc[path2[i]];
		sum += calc();
	}

	return sum;
}

int main() {
	std::ios::sync_with_stdio(false);
	cin.tie(false);
	int T, answer;
	cin >> T;
	for (int t = 1; t <= T; t++) 
	{
		init();

		answer = solve();

		printf("#%d %d\n", t, answer);
	}

	return 0;
}