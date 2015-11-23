package networkRouting;

import java.util.Vector;

public class Node {
	public int nodeId;
	public double positionX;
	public double positionY;
	public double range;
	public int maxBuffer;
	public int curBuffer;
	public Vector<Node> neighbour;
	public Node() {
		super();
	}
	public Node(int nodeId, double positionX, double positionY, double range,
			int maxBuffer, int curBuffer, Vector<Node> neighbour) {
		super();
		this.nodeId = nodeId;
		this.positionX = positionX;
		this.positionY = positionY;
		this.range = range;
		this.maxBuffer = maxBuffer;
		this.curBuffer = curBuffer;
		this.neighbour = neighbour;
	}
	public Node(int nodeId, double positionX, double positionY, double range,
			int maxBuffer, int curBuffer) {
		super();
		this.nodeId = nodeId;
		this.positionX = positionX;
		this.positionY = positionY;
		this.range = range;
		this.maxBuffer = maxBuffer;
		this.curBuffer = curBuffer;
	}
	/**
	 * @return the nodeId
	 */
	public int getNodeId() {
		return nodeId;
	}
	/**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	/**
	 * @return the positionX
	 */
	public double getPositionX() {
		return positionX;
	}
	/**
	 * @param positionX the positionX to set
	 */
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	/**
	 * @return the positionY
	 */
	public double getPositionY() {
		return positionY;
	}
	/**
	 * @param positionY the positionY to set
	 */
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	/**
	 * @return the range
	 */
	public double getRange() {
		return range;
	}
	/**
	 * @param range the range to set
	 */
	public void setRange(double range) {
		this.range = range;
	}
	/**
	 * @return the maxBuffer
	 */
	public int getMaxBuffer() {
		return maxBuffer;
	}
	/**
	 * @param maxBuffer the maxBuffer to set
	 */
	public void setMaxBuffer(int maxBuffer) {
		this.maxBuffer = maxBuffer;
	}
	/**
	 * @return the curBuffer
	 */
	public int getCurBuffer() {
		return curBuffer;
	}
	/**
	 * @param curBuffer the curBuffer to set
	 */
	public void setCurBuffer(int curBuffer) {
		this.curBuffer = curBuffer;
	}
	/**
	 * @return the neighbour
	 */
	public Vector<Node> getNeighbour() {
		return neighbour;
	}
	/**
	 * @param neighbour the neighbour to set
	 */
	public void setNeighbour(Vector<Node> neighbour) {
		this.neighbour = neighbour;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", positionX=" + positionX
				+ ", positionY=" + positionY + ", range=" + range
				+ ", maxBuffer=" + maxBuffer + ", curBuffer=" + curBuffer
				+ ", neighbour are";
	}	
	public String toStringNeighbour(Node node){
		String neighbourId = "neighbour";
		for(int i=0; i<node.getNeighbour().size();i++){
			neighbourId+=" , " + node.getNeighbour().get(i);
		}
		return neighbourId;
	}
	
}
