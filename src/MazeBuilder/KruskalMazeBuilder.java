package MazeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class KruskalMazeBuilder extends MazeBuilder
{
	public KruskalMazeBuilder(int scale)
	{
		super(scale);
	}
	@Override
	public Object[] build(int n,int m)
	{
		Cell[][] cells=new Cell[n][m];
		ArrayList<Wall> walls=new ArrayList<Wall>((n-1)*m*2);
		HashSet<Cell> paths=new HashSet<>(n*m);
		Random random=new Random();
		Wall randomWall;
		HashMap<Integer,HashSet<Integer>> maze=new HashMap<>();
		int counter=0,randomIndex,start=random.nextInt(m),end=random.nextInt(m)+n*m-m;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++)
			{
				cells[i][j]=new Cell(i,j,counter);
				maze.put(counter++,new HashSet<>());
				paths.add(cells[i][j]);
			}
		for(int i=0;i<n;i++)
			for(int j=0;j<m-1;j++)
				walls.add(new Wall(cells[i][j],cells[i][j+1],true));
		for(int i=0;i<n-1;i++)
			for(int j=0;j<m;j++)
				walls.add(new Wall(cells[i][j],cells[i+1][j],false));
		while(paths.size()>1)
		{
			randomIndex=random.nextInt(walls.size());
			randomWall=walls.get(randomIndex);
			if(randomWall.cell1.getFather()!=randomWall.cell2.getFather())
			{
				paths.remove(randomWall.cell2.getFather());
				randomWall.cell2.setFather(randomWall.cell1);
				maze.get(randomWall.cell1.getId()).add(randomWall.cell2.getId());
				maze.get(randomWall.cell2.getId()).add(randomWall.cell1.getId());
				walls.remove(randomIndex);
			}
		}
		return new Object[]{maze,start,end,toString(walls,n,m,start,end),toImage(walls,n,m,start,end)};
	}
}
