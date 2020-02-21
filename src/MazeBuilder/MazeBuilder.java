package MazeBuilder;

import java.util.ArrayList;
import java.util.Stack;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public abstract class MazeBuilder
{
	private int scale;
	public MazeBuilder(int scale)
	{
		this.scale=scale+1;
	}
	abstract Object[] build(int n,int m);
	public Object[] build(int d)
	{
		return build(d,d);
	}
	public String toString(ArrayList<Wall> walls,int n,int m,int start,int end)
	{
		StringBuilder string=new StringBuilder();
		string.append(" _".repeat(start)).append("  ").append(" _".repeat(m-1-start)).append(" \n");
		for(int i=0;i<n;i++)
		{
			string.append("|");
			for(int j=0;j<m;j++)
			{
				string.append(walls.contains(new Wall(new Cell(i,j,0),new Cell(i+1,j,0),false))?"_":" ");
				string.append(walls.contains(new Wall(new Cell(i,j,0),new Cell(i,j+1,0),true))||j==m-1?"|":" ");
			}
			string.append("\n");
		}
		string.append(" ‾".repeat(end%m)).append("  ").append(" ‾".repeat(m-1-end%m)).append(" \n");
		return string.toString();
	}
	public String toImage(ArrayList<Wall> walls,int n,int m,int start,int end)
	{
		BufferedImage img=new BufferedImage(scale*m+1,scale*n+1,BufferedImage.TYPE_INT_RGB);
		String fileName="MyFile.png";
		Graphics2D g=img.createGraphics();
		g.setColor(Color.white);
		g.drawRect(0,0,scale*m,scale*n);
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				if(walls.contains(new Wall(new Cell(i,j,0),new Cell(i,j+1,0),true)))
				    g.drawLine(scale*(j+1),scale*i+1,scale*(j+1),scale*i+scale-1);
				if(walls.contains(new Wall(new Cell(i,j,0),new Cell(i+1,j,0),false)))
				    g.drawLine(scale*j+1,scale*(i+1),scale*j+scale-1,scale*(i+1));
			}
		}
		for(int i=scale;i<scale*n-1;i+=scale)
			for(int j=scale;j<scale*m-1;j+=scale)
				g.drawLine(j,i,j,i);
		g.setColor(Color.black);
		g.drawLine(start*scale+1,0,start*scale+scale-1,0);
		end%=m;
		g.drawLine(end*scale+1,scale*n,end*scale+scale-1,scale*n);
		g.dispose();
		File f=new File(fileName);
		try
		{
			ImageIO.write(img,"PNG",f);
		}catch(IOException e)
		{
			System.out.println(e);
		}
		return fileName;
	}
	public String drawSolution(String fileName,Stack<Integer> solution,int n,int m,String type)
	{
		BufferedImage img=null;
		try
		{
			img=ImageIO.read(new File(fileName));
		}catch(IOException e)
		{
			System.out.println(e);
		}
		Graphics2D g=img.createGraphics();
		int i,j,now,next;
		g.setColor(Color.red);
		if(type.toUpperCase().equals("LINE")) for(int cell=0;cell<solution.size();cell++)
		{
			now=solution.get(cell);
			i=((int)(now/m))*scale+(int)Math.ceil(scale/2);
			j=(now%m)*scale+(int)Math.ceil(scale/2);
			if(cell<solution.size()-1)
			{
				next=solution.get(cell+1);
				if(Math.abs(next-now)==1) g.drawLine(j,i,j+scale*(next-now),i);
				else g.drawLine(j,i,j,i+(next>now?scale:-scale));
			}
		}
		else if(type.toUpperCase().equals("SQUARE")) for(int cell=0;cell<solution.size();cell++)
		{
			now=solution.get(cell);
			i=((int)(now/m))*scale+1;
			j=(now%m)*scale+1;
			g.fillRect(j+3,i+3,scale-7,scale-7);
		}
		g.dispose();
		File f=new File(fileName);
		try
		{
			ImageIO.write(img,"PNG",f);
		}catch(IOException e)
		{
			System.out.println(e);
		}
		return fileName;
	}
	public String drawSolution(String fileName,Stack<Integer> solution,int d)
	{
		return drawSolution(fileName,solution,d,d,"LINE");
	}
	public String drawSolution(String fileName,Stack<Integer> solution,int n,int m)
	{
		return drawSolution(fileName,solution,n,m,"LINE");
	}
}
