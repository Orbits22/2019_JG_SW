#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

typedef struct point {
	int r, c, starNum;
}Point;

int answer, N;
int dr[8] = { -1,-1,-1, 0,0, 1,1,1 };
int dc[8] = { -1,0,1,  -1,1, -1,0,1 };
char map[301][301];
bool check[301][301];

bool click(int, int);
bool comp(const Point &a, const Point &b) {
	return a.starNum < b.starNum;
}
vector<Point> v;
void init() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> map[i][j];
			if (map[i][j] == '*')
				check[i][j] = true;
			else
				check[i][j] = false;
		}
	}
	answer = 0;
}

int getStarNum(int r, int c, int select) {
	int rr, cc, starNum;

	starNum = 0;
	for (int i = 0; i < 8; i++) {
		rr = r + dr[i];
		cc = c + dc[i];

		if (0 <= rr && rr < N &&
			0 <= cc && cc < N) {
			if (select == 0 && map[rr][cc] == '*')
				starNum++;
			else if (select == 1)
				click(rr, cc);
		}
	}
	return starNum;
}

bool click(int r, int c) {
	int starNum;

	if (check[r][c] == true)
		return false;

	check[r][c] = true;
	starNum = getStarNum(r, c, 0);
	map[r][c] = starNum + '0';

	if (starNum == 0)
		getStarNum(r, c, 1);

	return true;
}

void setStarNum() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (check[i][j] == false) {
				map[i][j] = getStarNum(i, j, 0) + '0';
				if (map[i][j] == '0')
					v.push_back({ i,j,map[i][j] - '0' });
			}
		}
	}
}

void clickZero() {
	for (int i = 0; i < v.size(); i++) {
		Point p = v[i];

		if (click(p.r, p.c))
			answer++;
	}
	v.clear();
}

void clickNonZero() {
	for (int i = 0; i < N; i++) 
		for (int j = 0; j < N; j++) 
			if (check[i][j] == false)
				answer++;
}
int main() {
	std::ios::sync_with_stdio(false);
	cin.tie(NULL);

	int T;
	cin >> T;

	for (int t = 1; t <= T; t++) {
		init();
		setStarNum();
		clickZero();
		clickNonZero();
		cout << "#" << t << " " << answer << '\n';
	}
	return 0;
}