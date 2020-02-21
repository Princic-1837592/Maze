package MazeSolver;

import java.util.Stack;

import MazeBuilder.KruskalMazeBuilder;

import java.util.HashMap;
import java.util.HashSet;

public class MazeSolver
{
	public static Stack<Integer> solve(HashMap<Integer,HashSet<Integer>> maze,int start,int end)
	{
		Stack<Integer> path=new Stack<>();
		HashSet<Integer> visited=new HashSet<>(end);
		boolean moved=false;
		path.push(start);
		visited.add(start);
		while(path.peek()!=end)
		{
			moved=false;
			for(int destination:maze.get(path.peek()))
			{
				if(!visited.contains(destination))
				{
					path.push(destination);
					visited.add(destination);
					moved=true;
					break;
				}
			}
			if(!moved) path.pop();
		}
		return path;
	}
	public static void main(String[] args)
	{
		Object[] maze=new KruskalMazeBuilder(100).build(3,5);
		System.out.println(solve((HashMap<Integer,HashSet<Integer>>)maze[0],(int)maze[1],(int)maze[2]));
	}
}