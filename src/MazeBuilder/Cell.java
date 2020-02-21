package MazeBuilder;

public class Cell
{
	private Cell father;
	private final int x,y,id;
	public Cell(int i,int j,int id)
	{
		father=this;
		x=i;
		y=j;
		this.id=id;
	}
	public int getId()
	{
		return id;
	}
	public Cell getFather()
	{
		return father!=this?father.getFather():this;
	}
	public void setFather(Cell newFather)
	{
		if(father==this) father=newFather;
		else father.setFather(newFather);
	}
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "("+x+","+y+")";
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj==null||!(obj instanceof Cell)) return false;
		Cell c=(Cell)obj;
		return x==c.x&&y==c.y;
	}
	@Override
	public int hashCode()
	{
		return x+y;
	}
}
