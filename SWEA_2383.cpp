#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<cstring>
#include<cmath>
#include<algorithm>

using namespace std;

typedef struct person {
	int r, c, dist, arriveTime;
	bool check;
}Person;

typedef struct stair {
	int r, c, k;
}Stair;

int N, answer;

Person persons[10];
Person tmp0[10], tmp1[10];
Stair stairs[2];

bool comp(const Person &a , const Person &b) {
	return a.dist < b.dist;
}

void input(int &pcnt) {
	int scnt, num;
	pcnt = scnt = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			scanf("%d", &num);
			if (num == 1) {
				persons[pcnt].r = i;
				persons[pcnt++].c = j;
			}
			else if (num != 0) {
				stairs[scnt].r = i;
				stairs[scnt].c = j;
				stairs[scnt++].k = num;
			}
		}
	}
	answer = 9999999;
}

void getMinTime(int pcnt, bool sw) {
	int cnt0 = 0, cnt1 = 0;
	for (int i = 0; i < pcnt; i++) {
		if (persons[i].check == sw) {
			tmp0[cnt0].dist = abs(persons[i].r - stairs[0].r) + abs(persons[i].c - stairs[0].c);
			tmp0[cnt0].arriveTime = tmp0[cnt0].dist + stairs[0].k + 1;
			cnt0++;
		}
		else {
			tmp1[cnt1].dist = abs(persons[i].r - stairs[1].r) + abs(persons[i].c - stairs[1].c);
			tmp1[cnt1].arriveTime = tmp1[cnt1].dist + stairs[1].k + 1;
			cnt1++;
		}
	}

	//정렬
	sort(tmp0, tmp0 + cnt0, comp);
	sort(tmp1, tmp1 + cnt1, comp);

	//걸리는 시간 구하기
	if (cnt0 > 3) {
		for (int i = 3; i < cnt0; i++) {
			if (tmp0[i - 3].arriveTime > tmp0[i].dist)
				tmp0[i].arriveTime += tmp0[i - 3].arriveTime-1 - tmp0[i].dist;
		}
	}
	if (cnt1 > 3) {
		for (int i = 3; i < cnt1; i++) {
			if (tmp1[i - 3].arriveTime > tmp1[i].dist)
				tmp1[i].arriveTime += tmp1[i - 3].arriveTime-1 - tmp1[i].dist;
		}
	}

	//최소 시간 갱신
	if (answer > max(tmp0[cnt0 - 1].arriveTime, (cnt1 > 0) ? tmp1[cnt1 - 1].arriveTime : 0))
		answer = max(tmp0[cnt0 - 1].arriveTime, (cnt1 > 0) ? tmp1[cnt1 - 1].arriveTime : 0);
}

void calc(int index, int n, int r, int target, int pcnt) {
	if (r == 0) {
		getMinTime(pcnt, true);
		getMinTime(pcnt, false);
	}
	else if (target == N) {
		return;
	}
	else {
		persons[target].check = true;
		calc(index + 1, n, r-1, target + 1, pcnt);

		persons[target].check = false;
		calc(index, n, r, target + 1, pcnt);
	}
}

int main() {
	int T, pcnt;
	T = pcnt = 0;
	cin >> T;

	for (int t = 1; t <= T; t++) {	
		cin >> N;
		input(pcnt);
		for (int i = 1; i <= pcnt; i++) {
			calc(0, pcnt, i, 0, pcnt);
		}

		printf("#%d %d\n", t, answer);
	}
	return 0;
}