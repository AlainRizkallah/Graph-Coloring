import java.util.ArrayList;

public class CVertex implements Comparable<CVertex>  {
	public int id;
	public ArrayList<Integer> match;
	public int color;
	
	
	
	public CVertex(ArrayList<Integer> match, int i) {
		this.id= i;
		this.match = match;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Integer> getMatch() {
		return match;
	}

	public void setMatch(ArrayList<Integer> match) {
		this.match = match;
	}



	@Override
	public int compareTo(CVertex o) {
		if (this.id>o.id)
			return 1;
		else if (this.id<o.id)
			return -1;
		else return 0;
	}
	@Override
	public String toString() {
		return "{"+this.id+"}";
	}

}