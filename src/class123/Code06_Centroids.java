package class123;

// 哪些点可以改造成重心
// 给定一棵n个点的树，你可以随便删掉一条边，然后随便加上一条边
// 通过这种方式可以让很多点变成重心
// 打印所有能变成重心的点
// 测试链接 : https://www.luogu.com.cn/problem/CF708C
// 提交以下的code，提交时请把类名改成"Main"，可以通过所有用例

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Code06_Centroids {

	public static int MAXN = 400001;

	public static int n;

	public static int[] head = new int[MAXN];

	public static int[] next = new int[MAXN << 1];

	public static int[] to = new int[MAXN << 1];

	public static int cnt;

	public static int[] size = new int[MAXN];

	public static int[] firstSub = new int[MAXN];

	public static int[] secondSub = new int[MAXN];

	public static int[] innerNear = new int[MAXN];

	public static int[] outerNear = new int[MAXN];

	public static boolean[] ans = new boolean[MAXN];

	public static void build() {
		cnt = 1;
		Arrays.fill(head, 1, n + 1, 0);
		Arrays.fill(firstSub, 1, n + 1, 0);
		Arrays.fill(secondSub, 1, n + 1, 0);
		Arrays.fill(innerNear, 1, n + 1, 0);
		Arrays.fill(outerNear, 1, n + 1, 0);
	}

	public static void addEdge(int u, int v) {
		next[cnt] = head[u];
		to[cnt] = v;
		head[u] = cnt++;
	}

	public static void dfs1(int u, int f) {
		size[u] = 1;
		for (int e = head[u], v; e != 0; e = next[e]) {
			v = to[e];
			if (v != f) {
				dfs1(v, u);
				size[u] += size[v];
				if (size[firstSub[u]] < size[v]) {
					secondSub[u] = firstSub[u];
					firstSub[u] = v;
				} else if (size[secondSub[u]] < size[v]) {
					secondSub[u] = v;
				}
				innerNear[u] = Math.max(innerNear[u], innerNear[v]);
			}
		}
		if (size[u] <= n / 2) {
			innerNear[u] = size[u];
		}
	}

	public static void dfs2(int u, int f) {
		for (int e = head[u], v; e != 0; e = next[e]) {
			v = to[e];
			if (v != f) {
				if (n - size[v] <= n / 2) {
					outerNear[v] = n - size[v];
				} else if (v != firstSub[u]) {
					outerNear[v] = Math.max(outerNear[u], innerNear[firstSub[u]]);
				} else {
					outerNear[v] = Math.max(outerNear[u], innerNear[secondSub[u]]);
				}
				dfs2(v, u);
			}
		}
		if (n - size[u] > size[firstSub[u]]) {
			ans[u] = (n - size[u] - outerNear[u] <= n / 2);
		} else {
			ans[u] = (size[firstSub[u]] - innerNear[firstSub[u]] <= n / 2);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		n = (int) in.nval;
		build();
		for (int i = 1, u, v; i < n; i++) {
			in.nextToken();
			u = (int) in.nval;
			in.nextToken();
			v = (int) in.nval;
			addEdge(u, v);
			addEdge(v, u);
		}
		dfs1(1, 0);
		dfs2(1, 0);
		for (int i = 1; i <= n; i++) {
			out.print(ans[i] ? "1 " : "0 ");
		}
		out.println();
		out.flush();
		out.close();
		br.close();
	}

}
