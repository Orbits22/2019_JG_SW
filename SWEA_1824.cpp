#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<random>
#include<cstring>
using namespace std;

int R, C;
char map[20][20];
bool check[20][20][4][16];

int dr[4] = {-1,1,0,0};
int dc[4] = {0,0,-1,1};

bool go(int,int,int,int, int);

void init() {
	//scanf("%d %d", &R, &C);
	cin >> R >> C;
	//getchar();
	for (int r = 0; r < R; r++) {
		for (int c = 0; c < C; c++) {
			//scanf("%c", &map[r][c]);
			cin >> map[r][c];
		}
		//getchar();
	}
	memset(check, 0, sizeof(check));
}

void checkValid(int& r, int& c) {
	if (c == C)		c = 0;
	else if (c == -1)	c = C - 1;
	else if (r == R)	r = 0;
	else if (r == -1)	r = R - 1;
}

void changeP(char cmd, int &P, int& mem) {
	if ('0' <= cmd && cmd <= '9') {
		mem = cmd - '0';
	}
	if (cmd == '_' && mem == 0) P = 3;
	if (cmd == '_' && mem != 0) P = 2;
	if (cmd == '|' && mem == 0) P = 1;
	if (cmd == '|' && mem != 0) P = 0;
	if (cmd == '^') P = 0;
	if (cmd == 'v') P = 1;
	if (cmd == '<') P = 2;
	if (cmd == '>') P = 3;
	
}

void changeMem(char ch, int& mem) {
	if (ch == '-') {
		if (mem == 0)
			mem = 15;
		else
			mem -= 1;
	}
	else if (ch == '+') {
		if (mem == 15)
			mem = 0;
		else
			mem += 1;
	}
}

bool fourSide(int r, int c, int P, int mem, int cnt) {
	int rr, cc;
	for (int i = 0; i < 4; i++) {
		rr = r + dr[i];
		cc = c + dc[i];
		checkValid(rr,cc);

		if (check[rr][cc][i][mem] == false) {
			if (go(rr, cc, i, mem, cnt+1))
				return true;
		}
	}
	return false;
}


//P : 0╩С1го2аб3©Л
bool go(int r, int c, int P, int mem, int cnt) {

	checkValid(r, c);
	char ch = map[r][c];

	if (check[r][c][P][mem] == true
		|| cnt == 100)
		return false;
	else
		check[r][c][P][mem] = true;

	if (('0' <= ch && ch <= '9')
		|| ch == '^'
		|| ch == 'v'
		|| ch == '<'
		|| ch == '>'
		|| ch == '_'
		|| ch == '|') {

		changeP(map[r][c], P, mem);
		return go(r + dr[P], c + dc[P], P, mem, cnt+1);
	}

	if (ch == '?') {
		//printf("%d\n", cnt);
		return fourSide(r,c,P,mem,cnt);
	}

	if (   ch == '-'
		|| ch == '+') {

		changeMem(ch, mem);
		return go(r + dr[P], c + dc[P], P, mem, cnt+1);
	}

	if (ch == '.') return go(r + dr[P], c + dc[P], P, mem, cnt+1);

	if (ch == '@') return true;
}

int main() {
	std::ios::sync_with_stdio(false);
	cin.tie(NULL);

	int T;
	//scanf("%d", &T);
	cin >> T;
	//getchar();
	for (int t = 1; t <= T; t++) {

		init();
		
		//for (int i = 0; i < R; i++) {
		//	for (int j = 0; j < C; j++) {
		//		printf("%c", map[i][j]);
		//	}
		//	printf("\n");
		//}
		

		if(go(0,0,3,0,0))
			printf("#%d %s\n\n", t, "YES");
		else
			printf("#%d %s\n\n", t, "NO");
	}
	return 0;
}