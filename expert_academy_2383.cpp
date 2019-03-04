#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <math.h>

using namespace std;

typedef struct person {
	int r = 0;
	int c = 0;
	int time = 0;
	int stair_select = 0;
	int stair_cnt = 0;
}Person;
vector<Person> people;


int N = 0;
int map[10][10] = { 0, };

typedef struct stair {
	int length = 0;
	int r = 0;
	int c = 0;
	queue<Person> q;
}Stair;
vector<Stair> stairs;

int excute() { // 계단선택 모두 이루어진 후 실행하며 시간계산
	int time = 0;
	for (int i = 0; i < people.size(); i++) {
		int dif_r = people[i].r - stairs[people[i].stair_select].r;
		int dif_c = people[i].c - stairs[people[i].stair_select].c;
		people[i].time = abs(dif_r) + abs(dif_c);
		stairs[people[i].stair_select].q.push(people[i]);
	}
	for (int i = 0; i < people.size(); i++) {
		while (!stairs[i].q.empty()) {
			Person tmp = stairs[i].q.front();
			//stairs[i].q.pop();
			if (tmp.stair_cnt == stairs[i].length) {
				time = 
			}
		}
	}
}

int dfs(int stair_select_people_num) {
	if (stair_select_people_num == people.size()) {
		excute();
		return 0;
	}
	for (int i = 0; i < stairs.size(); i++) {
		people[stair_select_people_num].stair_select = i; // 계단 선택
		dfs(stair_select_people_num + 1);
	}
	return 0;
}

int main() {
	int i = 0;
	int j = 0;
	int T = 0;
	int test_case = 0;

	scanf("%d", &T);
	for (test_case = 1; test_case <= T; test_case++) {
		scanf("%d", &N);
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				scanf("%d", &map[i][j]);
				if (map[i][j] != 0) {
					if (map[i][j] == 1) {
						people.push_back({ i,j,0,0,0 });
					}
					else {
						stairs.push_back({map[i][j],i,j });
					}
				}
			}
		}

		dfs(0);
	}
}