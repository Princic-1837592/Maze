package MazeSolver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import MazeBuilder.KruskalMazeBuilder;

public class RecursiveMazeSolver extends MazeSolver
{
	public static boolean solve(HashMap<Integer,HashSet<Integer>> maze,int start,int end,Stack<Integer> path,
	        HashSet<Integer> visited)
	{
		if(start==end)
		{
			path.push(end);
			return true;
		}
		visited.add(start);
		for(int destination:maze.get(start))
			if(!visited.contains(destination)&&solve(maze,destination,end,path,visited))
			{
				path.push(start);
				return true;
			}
		return false;
	}
	public static Stack<Integer> solve(HashMap<Integer,HashSet<Integer>> maze,HashSet<Integer> visited,int start,
	        int end)
	{
		Stack<Integer> reversedPath=new Stack<>(),path=new Stack<>();
		solve(maze,start,end,reversedPath,visited);
		while(!reversedPath.empty())
			path.push(reversedPath.pop());
		return path;
	}
	public static Stack<Integer> solve(HashMap<Integer,HashSet<Integer>> maze,int start,int end)
	{
		return solve(maze,new HashSet<Integer>(),start,end);
	}
	public static void main(String[] args)
	{
		KruskalMazeBuilder builder=new KruskalMazeBuilder(20);
		int n=100,m=100;
		HashSet<Integer> visited=new HashSet<>();
		Object[] maze=builder.build(n,n);
		Stack<Integer> solution=solve((HashMap<Integer,HashSet<Integer>>)maze[0],visited,(int)maze[1],(int)maze[2]);
		builder.drawSolution((String)maze[4],solution,visited,n,n,"square");
//		System.out.println(maze[3]);
	}
}
