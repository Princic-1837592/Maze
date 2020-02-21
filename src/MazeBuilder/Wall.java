package MazeBuilder;

public class Wall
{
	protected Cell cell1,cell2;
	protected boolean horizontal;
	public Wall(Cell c1,Cell c2,boolean d)
	{
		cell1=c1;
		cell2=c2;
		horizontal=d;
	}
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return "("+cell1+","+cell2+","+(horizontal?"H":"V")+")";
	}
	@Override
	public int hashCode()
	{
		return horizontal?1:0;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(obj==null||!(obj instanceof Wall));
		Wall w=(Wall)obj;
		return cell1.equals(w.cell1)&&cell2.equals(w.cell2);
	}
}
