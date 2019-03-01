#define _CRT_SECURE_NO_WARNINGS
#include<iostream>
#include<queue>
using namespace std;

typedef struct client {
	int num, arriveTime;
	bool checkA, checkB;
}Client;

typedef struct servicer {
	int processTime, nextTime;
	Client c;
	bool isEmpty;
}Servicer;

int N, M, K, A, B, answer;
//N:접수창구 M:정비창구 K:방문고객수 A,B:지갑두고간고객이용창구번호

queue<Client> Nwaiting, Mwaiting;
Servicer Nline[10], Mline[10];
Client clients[1004];

void input() {
	scanf("%d %d %d %d %d",&N, &M, &K, &A, &B);
	for (int i = 1; i <= N; i++) {
		scanf("%d", &Nline[i].processTime);
		Nline[i].isEmpty = true;
		Nline[i].nextTime = -1;
	}
	for (int i = 1; i <= M; i++) {
		scanf("%d", &Mline[i].processTime);
		Mline[i].isEmpty = true;
		Mline[i].nextTime = -1;
	}
	for (int i = 1; i <= K; i++) {
		int arriveTime;
		scanf("%d", &arriveTime);
		clients[i] = {i,arriveTime,false,false};
	}
	answer = 0;
}

void allocate(Servicer line[], queue<Client>& waiting, int size, int time) {

	for (int i = 1; i <= size; i++) {
		if (!waiting.empty())
		{
			if (line[i].isEmpty && waiting.front().arriveTime <= time) {

				Client c = waiting.front();
				waiting.pop();

				if (&waiting == &Nwaiting && i == A)	c.checkA = true;
				if (&waiting == &Mwaiting && i == B)	c.checkB = true;

				if (c.checkA == true && c.checkB == true)
					answer += c.num;

				line[i].c = c;
				line[i].isEmpty = false;
				line[i].nextTime = time + line[i].processTime;
			}
		}
	}
}


bool endCheck() {
	for (int i = 1; i <= N; i++) {
		if (Nline[i].isEmpty == false)
			return false;
	}
	for (int i = 1; i <= M; i++) {
		if (Mline[i].isEmpty == false)
			return false;
	}
	if (!Nwaiting.empty())	return false;
	if (!Mwaiting.empty())	return false;

	return true;
}

int main() {
	int T,  time;

	scanf("%d", &T);
	for (int t = 1; t <= T; t++) {
		//input & init
		input();

		time = 0;
		for (int i = 1; i<= K; i++) 
			Nwaiting.push(clients[i]);

		while (true) 
		{
			for (int i = 1; i <= N; i++) {
				if (Nline[i].nextTime == time) {
					Mwaiting.push(Nline[i].c);
					Nline[i].isEmpty = true;
				}
			}
			for (int i = 1; i <= M; i++) {
				if (Mline[i].nextTime == time) {
					Mline[i].isEmpty = true;
				}
			}
			
			allocate(Nline, Nwaiting, N, time);
			allocate(Mline, Mwaiting, M, time);

			if (endCheck())	break;

			time++;
		}

		if (answer == 0)	
			answer = -1;

		printf("#%d %d\n",t,answer);
	}
	return 0;
}