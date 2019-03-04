#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <queue>
#include <list>
#include <vector>
#include <memory.h>
#include <algorithm>

using namespace std;

typedef struct customer_info {
	int c_num = 0;
	int arrive_t = 0;
	int customer_num = 0;
	int t_cnt = 0;
	int p_num = 0;
}Customer_info;

list<Customer_info> customer; // main 에서 입력받은 고객들 
queue<Customer_info> w_c_q;		// waiting reception queue
queue<Customer_info> c_q;		// reception queue
queue<Customer_info> w_p_q;		// waiting repair queue
queue<Customer_info> p_q;		// repair queue
vector<pair<pair<int,int>,pair<int,int>>> w_p_customer;	// waiting repair queue 넣기 전 번호 순으로 sorting 하기위한 vector

int N = 0, M = 0, K = 0, A = 0, B = 0;
int a[10] = { 0, }, b[10] = { 0, };
int result = 0;
int c[10] = { 0, };
int p[10] = { 0, };
bool c_q_flag = 0;
bool p_q_flag = 0;

int arrive(int time) {
	list<Customer_info>::iterator itr;
	Customer_info tmp;
	for (itr = customer.begin(); itr != customer.end(); itr) {
		if (itr->arrive_t == time) {
			tmp.arrive_t = itr->arrive_t;
			tmp.customer_num = itr->customer_num;
			w_c_q.push(tmp);
			itr = customer.erase(itr);
			continue;
		}
		itr++;
	}
	return 0;
}
int reception_waiting() {
	Customer_info tmp;
	int i = 0, j = 0;
	int push_flag = 0;
	int w_c_q_size = w_c_q.size();

	for (i = 1; i <= w_c_q_size; i++)
	{
		push_flag = 0;
		tmp.arrive_t = w_c_q.front().arrive_t;
		tmp.customer_num = w_c_q.front().customer_num;
		tmp.t_cnt = w_c_q.front().t_cnt;
		tmp.c_num = w_c_q.front().c_num;
		w_c_q.pop();
		for ( j = 1; j <= N; j++) {
			if (c[j] == 0) { // 빈 곳이 있을 경우
				tmp.c_num = j;
				c[j] = 1;
				c_q.push(tmp);
				push_flag = 1;
				break;
			}
		}
		if (push_flag == 0) { // 빈 곳이 없는 경우
			w_c_q.push(tmp);
			c_q_flag = 1; // 모든 창구 차있음
		}	
	}
	return 0;
}
int reception() {
	Customer_info tmp;
	int i = 0, j = 0;
	int c_q_size = c_q.size();

	for (i = 1; i <= c_q_size; i++)
	{
		tmp.arrive_t = c_q.front().arrive_t;
		tmp.customer_num = c_q.front().customer_num;
		tmp.t_cnt = c_q.front().t_cnt;
		tmp.c_num = c_q.front().c_num;
		c_q.pop();
		
		if (tmp.t_cnt == a[tmp.c_num]) { // 접수창구 일처리 끝난 경우
			tmp.t_cnt = 0;
			c[tmp.c_num] = 0;
			w_p_customer.push_back({ {tmp.c_num,tmp.customer_num},{tmp.arrive_t,tmp.t_cnt} }); // w_p_q에 바로 넣는 것이 아님.
			c_q_flag = 0; // 빈자리 있음
		}
		else if (tmp.t_cnt < a[tmp.c_num]) { // 일처리 중인 경우
			tmp.t_cnt++;
			c_q.push(tmp);
		}
	}
	return 0;
}
int repair_waiting() {
	Customer_info tmp;
	int i = 0, j = 0;
	int push_flag = 0;

	printf("sort_after========================================\n");
	for (i = 0; i < w_p_customer.size(); i++) {
		printf("%d\n", w_p_customer[i].first.first);
	}
	sort(w_p_customer.begin(), w_p_customer.end());
	printf("sort_before----------------------------------------\n");
	for (i = 0; i < w_p_customer.size(); i++) {
		printf("%d\n", w_p_customer[i].first.first);
	}

	for (i = 0; i < w_p_customer.size(); i++) {
		tmp.c_num = w_p_customer[i].first.first;
		tmp.customer_num = w_p_customer[i].first.second;
		tmp.arrive_t = w_p_customer[i].second.first;
		tmp.p_num = w_p_customer[i].second.second;
		w_p_q.push(tmp);
	}
	w_p_customer.clear();
	int w_p_q_size = w_p_q.size();
	for (i = 1; i <= w_p_q_size; i++)
	{
		push_flag = 0;
		tmp.arrive_t = w_p_q.front().arrive_t;
		tmp.customer_num = w_p_q.front().customer_num;
		tmp.t_cnt = w_p_q.front().t_cnt;
		tmp.c_num = w_p_q.front().c_num;
		w_p_q.pop();
		for (j = 1; j <= M; j++) {
			if (p[j] == 0) { // 빈 곳이 있을 경우
				tmp.p_num = j;
				p[j] = 1;
				p_q.push(tmp);
				push_flag = 1;
				break;
			}
		}
		if (push_flag == 0) { // 빈 곳이 없는 경우
			w_p_q.push(tmp);
			p_q_flag = 1;
		}
	}
	return 0;
}
int repair() {
	Customer_info tmp;
	int i = 0, j = 0;
	int p_q_size = p_q.size();
	for (i = 1; i <= p_q_size; i++)
	{
		tmp.arrive_t = p_q.front().arrive_t;
		tmp.customer_num = p_q.front().customer_num;
		tmp.t_cnt = p_q.front().t_cnt;
		tmp.c_num = p_q.front().c_num;
		tmp.p_num = p_q.front().p_num;
		p_q.pop();

		if (tmp.t_cnt == b[tmp.p_num]) { // 접수창구 일처리 끝난 경우
			tmp.t_cnt = 0;
			p[tmp.p_num] = 0;
			p_q_flag = 0;
			if (tmp.c_num == A && tmp.p_num == B) {
				result = result + tmp.customer_num;
			}
		}
		else if (tmp.t_cnt < b[tmp.p_num]) { // 일처리 중인 경우
			tmp.t_cnt++;
			p_q.push(tmp);
		}
	}
	return 0;
}
int main() {
	int i = 0, j = 0;
	int T = 0, test_case = 0;
	int m_customer_num = 1;
	int time = 0;
	Customer_info tmp;
	scanf("%d", &T);
	for (test_case = 1; test_case <= T; test_case++) {
		scanf("%d %d %d %d %d", &N, &M, &K, &A, &B);
		for (i = 1; i <= N; i++) {
			scanf("%d", &a[i]); // 접수창구 시간 입력
		}
		for (i = 1; i <= M; i++) {
			scanf("%d", &b[i]);
		}
		for (i = 0; i < K; i++) {
			scanf("%d", &tmp.arrive_t);
			tmp.customer_num = m_customer_num++;
			customer.push_back(tmp);
		}

		while (1) {

			arrive(time);
			if(c_q_flag == 0)
				reception_waiting();
			reception();
			if(c_q_flag == 0)
				reception_waiting();
			if (p_q_flag == 0)
				repair_waiting();
			repair();
			if (p_q_flag == 0)
				repair_waiting();

			time++;
			printf("w_c_q=================================================================\n");
			for (int ii = 0; ii < w_c_q.size(); ii++) {
				tmp.c_num = w_c_q.front().c_num;
				tmp.arrive_t = w_c_q.front().arrive_t;
				tmp.customer_num = w_c_q.front().customer_num;
				tmp.t_cnt = w_c_q.front().t_cnt;
				tmp.p_num = w_c_q.front().p_num;
				w_c_q.pop();
				printf("arr_time : %d  //  cus_num : %d  //  c_num : %d  //  p_num : %d \n", tmp.arrive_t, tmp.customer_num, tmp.c_num, tmp.p_num);
				w_c_q.push(tmp);
			}
			printf("c_q=================================================================\n");
			for (int ii = 0; ii < c_q.size(); ii++) {
				tmp.c_num = c_q.front().c_num;
				tmp.arrive_t = c_q.front().arrive_t;
				tmp.customer_num = c_q.front().customer_num;
				tmp.t_cnt = c_q.front().t_cnt;
				tmp.p_num = c_q.front().p_num;
				c_q.pop();
				printf("arr_time : %d  //  cus_num : %d  //  c_num : %d  //  p_num : %d \n", tmp.arrive_t, tmp.customer_num, tmp.c_num, tmp.p_num);
				c_q.push(tmp);
			}
			printf("w_p_q=================================================================\n");
			for (int ii = 0; ii < w_p_q.size(); ii++) {
				tmp.c_num = w_p_q.front().c_num;
				tmp.arrive_t = w_p_q.front().arrive_t;
				tmp.customer_num = w_p_q.front().customer_num;
				tmp.t_cnt = w_p_q.front().t_cnt;
				tmp.p_num = w_p_q.front().p_num;
				w_p_q.pop();
				printf("arr_time : %d  //  cus_num : %d  //  c_num : %d  //  p_num : %d \n", tmp.arrive_t, tmp.customer_num, tmp.c_num, tmp.p_num);
				w_p_q.push(tmp);
			}
			printf("p_q=================================================================\n");
			for (int ii = 0; ii < p_q.size(); ii++) {
				tmp.c_num = p_q.front().c_num;
				tmp.arrive_t = p_q.front().arrive_t;
				tmp.customer_num = p_q.front().customer_num;
				tmp.t_cnt = p_q.front().t_cnt;
				tmp.p_num = p_q.front().p_num;
				p_q.pop();
				printf("arr_time : %d  //  cus_num : %d  //  c_num : %d  //  p_num : %d \n", tmp.arrive_t, tmp.customer_num, tmp.c_num, tmp.p_num);
				p_q.push(tmp);
			}
			if (w_c_q.empty() && c_q.empty() && w_p_q.empty() && p_q.empty() && customer.empty()) {
				break;
			}
			printf("time : %d\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n",time);
		}
	}
	printf("%d\n", result);
	return 0;
}

