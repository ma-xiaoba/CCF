#include <bits/stdc++.h>
using namespace std;
const int N = 1e3 + 7; 
const string base = "0123456789ABCDEF";
string dt[N];
int n, s, l, max_k;
int to_int (char ch) {
    if (ch >= '0' && ch <= '9') return ch - '0';
    else  return ch - 'A' + 10;
}
void _xor (string& ans, string str) {
    for (int i = 0; i < str.size(); i++) {
        int x = to_int(ans[i]);
        int y = to_int(str[i]);
        ans[i] = base[(x ^ y)];
    }
}
string get_xor (int disk, int _start) {
    string ans(8,'0');
    for (int i = 0; i <= n; i++)
        if (i != disk)
            _xor(ans, dt[i].substr(_start, 8));
    return ans;
}
int main ()
{
    ios::sync_with_stdio(false);
    cin >> n >> s >> l;
    n -= 1; // (n块数据,1块冗余)
    for (int i = 1; i <= l; i++) {
        int id; 
        cin >> id;
        cin >> dt[id];
        // cin>>id>>dt[id]; 这样不知道为啥错误
        max_k = dt[id].size() / 8 / s;
    }
    int m; cin >> m;
    while (m--) {
        int x; cin >> x;
        int y = x / s;
        int k = y / n;
        int disk = (n - k % (n + 1) + y % n + 1) % (n + 1);
        if (k >= max_k) cout << "-\n";
        else  if (dt[disk].size() != 0) {
            int _start = 8 * (k * s + x % s);
            cout << dt[disk].substr(_start, 8) << "\n";
        }
        else if (dt[disk].size() == 0 && l == n) {
            int _start = 8 * (k * s + x % s);
            cout << get_xor(disk, _start) << "\n";
        }
        else  cout << "-\n";
    }
    return 0;
}