import java.util.HashMap;

public class classitem {

	

	public Double getassiavg(String assitype) {
		if(type.containsKey(assitype))
		return type.get(assitype);
		else
			return 0.0;
	}

	public HashMap<String, Double> getType() {
		return type;
	}

	public boolean typeexist(String assitype){
		boolean isexest = false;
				if(type.containsKey(assitype))
				isexest = true;
				return isexest;
	}
	public void setType(HashMap<String, Double> type) {
		this.type = type;
	}

	public void setType(String assitype, Double grade) {
		if(type.containsKey(assitype))
			type.replace(assitype, (type.get(assitype)+ grade)/2);
		else
		type.put(assitype, grade);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	private HashMap<String, Double> type = new HashMap<String, Double>();
	private String className = "";

	public classitem() {
		className = "";
		type.clear();
	}
	
	public classitem(String name, String assitype, Double grade) {
		className = name;
		type.clear();
		type.put(assitype, grade);
	}
}
