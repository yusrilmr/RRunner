package client.communication;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responseList")
public class ResponseList {
	private List<String> list;
	
	public List<String> getList(){
		return list;
	}
	
	public void setList(List<String> list){
		this.list = list;
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}
}
