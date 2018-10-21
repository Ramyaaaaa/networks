#include<bits/stdc++.h>
using namespace std;
# define INF 0x3f3f3f3f
typedef pair<int, int> iPair;
class Graph
{
    int V;
    list< pair<int, int> > *adj;
    public:
        Graph(int V);
        void addEdge(int u, int v, int w);
        void shortestPath(int s);
};
Graph::Graph(int V)
{
    this->V = V;
    adj = new list<iPair> [V];
}
void Graph::addEdge(int u, int v, int w)
{
    adj[u].push_back(make_pair(v, w));
    adj[v].push_back(make_pair(u, w));
}
void Graph::shortestPath(int src)
{
    priority_queue< iPair, vector <iPair> , greater<iPair> > pq;
    vector<int> dist(V, INF);
    pq.push(make_pair(0, src));
    dist[src] = 0;
    while (!pq.empty())
    {
        int u = pq.top().second;
        pq.pop();
        list< pair<int, int> >::iterator i;
        for (i = adj[u].begin(); i != adj[u].end(); ++i)
        {
            int v = (*i).first;
            int weight = (*i).second;
            if (dist[v] > dist[u] + weight)
            {
                dist[v] = dist[u] + weight;
                pq.push(make_pair(dist[v], v));
            }
        }
    }
    cout<<"Vertex   Distance from Source"<<endl;
    for (int i = 0; i < V; ++i)
    {
        cout<<i<<"\t\t"<<dist[i]<<endl;
    }
}
int main()
{
    int V = 9;
    Graph g(V);
    g.addEdge(0, 1, 4);
    g.addEdge(0, 7, 8);
    g.addEdge(1, 2, 8);
    g.addEdge(1, 7, 11);
    g.addEdge(2, 3, 7);
    g.addEdge(2, 8, 2);
    g.addEdge(2, 5, 4);
    g.addEdge(3, 4, 9);
    g.addEdge(3, 5, 14);
    g.addEdge(4, 5, 10);
    g.addEdge(5, 6, 2);
    g.addEdge(6, 7, 1);
    g.addEdge(6, 8, 6);
    g.addEdge(7, 8, 7);
    for(int i =0;i<V;i++)
    {
    	cout<<"Source is "<<i<<endl;
    	g.shortestPath(i);
    	cout<<endl;
    }
    return 0;
}







/* output


Source is node 0
Vertex   Distance from Source
0		0
1		4
2		12
3		19
4		21
5		11
6		9
7		8
8		14

Source is node 1
Vertex   Distance from Source
0		4
1		0
2		8
3		15
4		22
5		12
6		12
7		11
8		10

Source is node 2
Vertex   Distance from Source
0		12
1		8
2		0
3		7
4		14
5		4
6		6
7		7
8		2

Source is node 3
Vertex   Distance from Source
0		19
1		15
2		7
3		0
4		9
5		11
6		13
7		14
8		9

Source is node 4
Vertex   Distance from Source
0		21
1		22
2		14
3		9
4		0
5		10
6		12
7		13
8		16

Source is node 5
Vertex   Distance from Source
0		11
1		12
2		4
3		11
4		10
5		0
6		2
7		3
8		6

Source is node 6
Vertex   Distance from Source
0		9
1		12
2		6
3		13
4		12
5		2
6		0
7		1
8		6

Source is node 7
Vertex   Distance from Source
0		8
1		11
2		7
3		14
4		13
5		3
6		1
7		0
8		7

Source is node 8
Vertex   Distance from Source
0		14
1		10
2		2
3		9
4		16
5		6
6		6
7		7
8		0

