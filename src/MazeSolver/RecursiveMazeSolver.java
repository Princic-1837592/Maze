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
	public static Stack<Integer> solve(HashMap<Integer,HashSet<Integer>> maze,int start,int end)
	{
		Stack<Integer> reversedPath=new Stack<>(),path=new Stack<>();
		solve(maze,start,end,reversedPath,new HashSet<>());
		while(!reversedPath.empty())
			path.push(reversedPath.pop());
		return path;
	}
	public static void main(String[] args)
	{
		KruskalMazeBuilder builder=new KruskalMazeBuilder(20);
		int n=30,m=50;
		Object[] maze=builder.build(n,m);
		Stack<Integer> solution=solve((HashMap<Integer,HashSet<Integer>>)maze[0],(int)maze[1],(int)maze[2]);
		builder.drawSolution((String)maze[4],solution,n,m,"line");
		System.out.println(maze[3]);
	}
}
