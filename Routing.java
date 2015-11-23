package networkRouting;

import java.util.Vector;

public class Routing {
	public Node node;
	public Vector<Node> allNodes;
	public int deliverBuffer;

	public Routing(Vector<Node> allNodes) {
		super();
		this.allNodes = allNodes;
	}

	/**
	 * @return the deliverBuffer
	 */
	public int getDeliverBuffer() {
		return deliverBuffer;
	}

	/**
	 * @param deliverBuffer
	 *            the deliverBuffer to set
	 */
	public void setDeliverBuffer(int deliverBuffer) {
		this.deliverBuffer = deliverBuffer;
	}

	// select the node information by id
	public Node selectNodeById(int index) {
		Node node = new Node();
		for (int i = 0; i < allNodes.size(); i++) {
			if (allNodes.get(i).getNodeId() == index) {
				node = allNodes.get(i);
			}
		}
		if (node != null) {
			return node;
		} else {
			System.out.println("no this node");
			return null;
		}
	}

	// calculate distance from original node to destination node
	public double calDistance(Node org, Node des) {
		double distance = Math.sqrt(Math.pow(
				(des.getPositionX() - org.getPositionX()), 2)
				+ Math.pow((des.getPositionY() - org.getPositionY()), 2));
		return distance;
	}

	// calculate available buffer of node
	public int calAvaBuffer(Node node) {
		int avaBuffer = node.getMaxBuffer() - node.getCurBuffer();
		return avaBuffer;
	}

	// find the neighbour of one node
	public void findNeighbour(Node node) {
		Vector<Node> neighbours = new Vector<Node>();
		for (int i = 0; i < allNodes.size(); i++) {
			double distance = calDistance(node, allNodes.get(i));
			if (distance < node.getRange() && distance != 0) {
				neighbours.add(allNodes.get(i));
			}
		}
		node.setNeighbour(neighbours);
		// return neighbours;
	}

	// find the neighbour that has max available buffer ==== sort
	public Vector<Node> sortNeighbourByAvalibility(Node node) {
		// int temp = 0;
		Node tempNode;
		Vector<Node> neighbours = node.getNeighbour();

		if (neighbours.size() != 1) {
			for (int i = 0; i < neighbours.size(); i++) {
				for (int j = i; j < neighbours.size(); j++) {
					int ava1 = calAvaBuffer(neighbours.get(i));
					int ava2 = calAvaBuffer(neighbours.get(j));
					if (ava1 < ava2) {
						// temp = i;
						tempNode = neighbours.get(i);
						// setElementAt（Object obj,int index）
						// i=j
						neighbours.setElementAt(neighbours.get(j), i);
						// j = temp;
						neighbours.setElementAt(tempNode, j);
					}
				}
			}
		}
		// for(int i =0;i<neighbours.size();i++){
		// System.out.println(" **neighbour  "+neighbours.get(i).getNodeId());
		// }
		return neighbours;
	}

	public Vector<Node> sortNeighbourByDistance(Node node) {
		Node temp;
		Vector<Node> neighbours = node.getNeighbour();
		if (neighbours.size() != 1) {
			for (int i = 0; i < neighbours.size(); i++) {
				for (int j = i; j < neighbours.size(); j++) {
					double dis1 = calDistance(node, neighbours.get(i));
					double dis2 = calDistance(node, neighbours.get(j));
					if (dis1 > dis2) {
						temp = neighbours.get(i);
						neighbours.setElementAt(neighbours.get(j), i);
						neighbours.setElementAt(temp, j);
					}
				}
			}
		}
		return neighbours;
	}

	public void routing(int buffer, Node org, Node des) {
		double curDistance = 0;
		int receiveBuffer = buffer;
		Node current = null;
		Vector<Node> route = new Vector<Node>(); // result
		curDistance = calDistance(org, des);
		route.add(org);
		Vector<Node> firstNeighbour = sortNeighbourByAvalibility(org);
		// sortNeighbourByAvalibility(current);
		if (firstNeighbour.size() == 1 && firstNeighbour.firstElement() == des) {
			System.out.println("++++ org and des directly linked");
			current = des;
			receiveBuffer = receiveBuffer(receiveBuffer, current);
			route.add(des);

		} else if (firstNeighbour.size() == 1
				&& firstNeighbour.firstElement() != des) {
			if (firstNeighbour.firstElement().getNeighbour().size() != 1) {
				current = firstNeighbour.firstElement();
				receiveBuffer = receiveBuffer(receiveBuffer, current);
				route.add(current);
			} else {
				if (firstNeighbour.firstElement().getNeighbour().firstElement() != des) {
					System.out.println("no route for this situation");
				} else {
					receiveBuffer = receiveBuffer(receiveBuffer,
							firstNeighbour.firstElement());
					route.add(firstNeighbour.firstElement());

					current = des;
					receiveBuffer = receiveBuffer(receiveBuffer, current);
					route.add(current);
				}
			}
		} else {
			for (int i = 0; i < firstNeighbour.size(); i++) {
				double distance = calDistance(firstNeighbour.get(i), des);
				if (distance < curDistance) {
					if (firstNeighbour.get(i).getNeighbour().size() != 1) {
						curDistance = distance;
						current = firstNeighbour.get(i);
						receiveBuffer = receiveBuffer(receiveBuffer, current);
						route.add(current);
						break;
					} else {
						if (firstNeighbour.get(i).getNeighbour().firstElement() == des) {
							curDistance = distance;
							current = firstNeighbour.get(i);
							receiveBuffer = receiveBuffer(receiveBuffer,
									current);
							route.add(current);
							current = des;
							receiveBuffer = receiveBuffer(receiveBuffer,
									current);
							route.add(current);
							break;
						} else {
							i++;
						}
					}
				}
			}
		}
		while (current != des) {
			Vector<Node> neighbour = sortNeighbourByAvalibility(current);
			if (neighbour.size() != 1) {
				for (int i = 0; i < neighbour.size(); i++) {
					double distance = calDistance(neighbour.get(i), des);
					if (distance < curDistance) {
						if (neighbour.get(i).getNeighbour().size() != 1) {
							curDistance = distance;
							current = neighbour.get(i);
							receiveBuffer = receiveBuffer(receiveBuffer,
									current);
							route.add(current);
							break;
						} else {
							if(neighbour.get(i).getNeighbour().firstElement() == des){
								curDistance = distance;
								current = neighbour.get(i);
								receiveBuffer = receiveBuffer(receiveBuffer,
										current);
								route.add(current);
								current = des;
								receiveBuffer = receiveBuffer(receiveBuffer,
										current);
								route.add(current);
								break;
							}else{
								i++;
							}
						}
					}
				}
			} else {
				current = neighbour.firstElement();
				if (current.getNeighbour().size() != 1) {
					receiveBuffer = receiveBuffer(receiveBuffer, current);
					route.add(current);
				} else {
					if (current.getNeighbour().firstElement() == des) {
						receiveBuffer = receiveBuffer(receiveBuffer, current);
						route.add(current);
						current = des;
						receiveBuffer = receiveBuffer(receiveBuffer, current);
						route.add(current);

					}else{
						current = route.get(route.size()-2);
						route.removeElementAt(route.size()-1);
						curDistance= calDistance(current,des);
						
					}
				}
			}
		}
		// route.add(des);
		System.out.println("loss based receive buffer" + receiveBuffer);
		System.out.print("route: [ ");
		for (int i = 0; i < route.size(); i++) {
			System.out.print(route.get(i).getNodeId() + ", ");
		}
		System.out.println("]");
		// return route;
	}

	public void distanceRouting(int buffer, Node org, Node des) {
		double curDistance = 0;
		int receiveBuffer = buffer;
		Node current = null;
		Vector<Node> route = new Vector<Node>(); // result
		curDistance = calDistance(org, des);
		route.add(org);
		Vector<Node> firstNeighbour = sortNeighbourByDistance(org);
		if (firstNeighbour.size() == 1 && firstNeighbour.firstElement() == des) {
			System.out.println("++++ org and des directly linked");
			current = des;
			receiveBuffer = receiveBuffer(receiveBuffer, current);
			route.add(des);

		} else if (firstNeighbour.size() == 1
				&& firstNeighbour.firstElement() != des) {
			current = firstNeighbour.firstElement();
			receiveBuffer = receiveBuffer(receiveBuffer, current);
			route.add(current);
		} else {
			for (int i = 0; i < firstNeighbour.size(); i++) {
				double distance = calDistance(firstNeighbour.get(i), des);
				if (distance < curDistance) {
					curDistance = distance;
					current = firstNeighbour.get(i);
					receiveBuffer = receiveBuffer(receiveBuffer, current);
					route.add(current);
					break;
				}
			}
		}
		while (current != des) {
			Vector<Node> neighbour = sortNeighbourByDistance(current);
			if (neighbour.size() != 1) {
				for (int i = 0; i < neighbour.size(); i++) {
					double distance = calDistance(neighbour.get(i), des);
					if (distance < curDistance) {
						curDistance = distance;
						current = neighbour.get(i);
						receiveBuffer = receiveBuffer(receiveBuffer, current);
						route.add(current);
						break;
					}
				}
			} else {
				current = neighbour.firstElement();
				receiveBuffer = receiveBuffer(receiveBuffer, current);
				route.add(current);
			}
		}
		System.out.println("distance based receive buffer" + receiveBuffer);
		System.out.print("route: [ ");
		for (int i = 0; i < route.size(); i++) {
			System.out.print(route.get(i).getNodeId() + ", ");
		}
		System.out.println("]");
	}

	public int receiveBuffer(int buffer, Node node) {
		int receiveBuffer = 0;
		int avaBuffer = calAvaBuffer(node);
		if (avaBuffer < buffer) {
			receiveBuffer = avaBuffer;
		} else {
			receiveBuffer = buffer;
		}

		return receiveBuffer;
	}

}
