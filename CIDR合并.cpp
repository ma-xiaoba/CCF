#include <iostream>
#include <algorithm>
#include <string>
using namespace std;
 
int mask[9]={0,128,192,224,240,248,252,254,255};	//掩码
 
struct IP_add{
	int a[5];
	IP_add(){for(int i=0;i<5;i++) a[i]=0;}
	bool operator<(IP_add t) {for(int i=0;i<5;i++) if(a[i]!=t.a[i]) return a[i]<t.a[i];}
}ip[100005];
 
IP_add input(string s)
{
	IP_add t;
	int n=0,f=0;
	int l=s.length();
	for(int i=0;i<l;i++)
	{
		if(isdigit(s[i])) t.a[n]=t.a[n]*10+s[i]-'0';
		if(s[i]=='.') n++;
		if(s[i]=='/') f=1,n=4;
	}
	if(!f) t.a[4]=(n+1)*8;
	return t;
}
 
void output(IP_add t)
{
	cout<<t.a[0]<<'.'<<t.a[1]<<'.'<<t.a[2]<<'.'<<t.a[3]<<'/'<<t.a[4]<<endl;
}
 
bool Cont(IP_add x,IP_add y)	//判断y是否为x的子集
{
	int l=x.a[4];
	if(l>y.a[4]) return false;
	
	for(int i=0;i<4;i++)
	{
		if(i&&x.a[i-1]!=y.a[i-1]) return false;
		if(l<=(i+1)*8)
		{
			if((x.a[i]&mask[l-i*8])^(y.a[i]&mask[l-i*8])) return false;
			else return true;
		} 
	} 
}
 
bool Union(IP_add&x,IP_add y)	//判断x y能否合并，能合并则将x的前缀长-1
{
	int l=x.a[4];
	if(l!=y.a[4]) return false;
	
	for(int i=0;i<4;i++)
	{
		if(i&&x.a[i-1]!=y.a[i-1]) return false;
		if(l<=(i+1)*8)
		{
			if((x.a[i]&mask[l-i*8])^(y.a[i]&mask[l-i*8])^(1<<((i+1)*8-l))) return false;
			else
			{
				x.a[4]--; return true;
			}
		}
	} 
}
 
int main()
{
	cin.tie(0);
	ios::sync_with_stdio(0);
	int n,i,j,k;
	cin>>n;
	string s;
	
	for(i=0;i<n;i++)
	{
		cin>>s;
		ip[i]=input(s);
	}
	sort(ip,ip+n);
	
	j=0;
	for(i=1;i<n;i++) if(!Cont(ip[j],ip[i])) ip[++j]=ip[i];    //子集合并
	n=j+1;
	
	j=0;
	for(i=1;i<n;i++)    //同级合并
	{
		if(Union(ip[j],ip[i])) while(j>0&&Union(ip[j-1],ip[j])) j--; 
		else ip[++j]=ip[i];
	}
	n=j+1;
	
	for(i=0;i<n;i++) output(ip[i]); 
	return 0;
}