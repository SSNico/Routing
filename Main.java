package networkRouting;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	private static Routing nRoute;

	public static void main(String[] args) {
		Vector<Node> nodes = new Vector<Node>();
		/*
		 * 
		 * select start node select end node enter package through
		 */

		Node node1 = new Node(1, 100.0, 100.0, 500, 5000, 0);
		Node node2 = new Node(2, 100.0, 900.0, 500.0, 5000, 0);
		Node node3 = new Node(3, 200.0, 500.0, 500.0, 2000, 0);
		Node node4 = new Node(4, 200.0, 1400.0, 500.0, 4000, 0);
		Node node5 = new Node(5, 400.0, 400.0, 500.0, 8000, 0);
		Node node6 = new Node(6, 400.0, 1000.0, 500.0, 8000, 0);
		Node node7 = new Node(7, 400, 1400, 500, 3000, 0);
		Node node8 = new Node(8, 600, 200, 500, 3000, 0);
		Node node9 = new Node(9, 600, 600, 500, 4000, 0);
		Node node10 = new Node(10, 700, 1000, 500, 4000, 0);
		Node node11 = new Node(11, 800.0, 700.0, 500, 5000, 0);
		Node node12 = new Node(12, 900.0, 400.0, 500.0, 5000, 0);
		Node node13 = new Node(13, 900.0, 900.0, 500.0, 2000, 0);
		Node node14 = new Node(14, 900.0, 1200.0, 500.0, 4000, 0);
		Node node15 = new Node(15, 1000.0, 600.0, 500.0, 8000, 0);
		Node node16 = new Node(16, 1100.0, 1000.0, 500.0, 8000, 0);
		Node node17 = new Node(17, 1200, 400, 500, 3000, 0);
		Node node18 = new Node(18, 1400, 200, 500, 3000, 0);
		Node node19 = new Node(19, 1300, 500, 500, 4000, 0);
		Node node20 = new Node(20, 1400, 800, 500, 4000, 0);

		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node3);
		nodes.add(node4);
		nodes.add(node5);
		nodes.add(node6);
		nodes.add(node7);
		nodes.add(node8);
		nodes.add(node9);
		nodes.add(node10);
		nodes.add(node11);
		nodes.add(node12);
		nodes.add(node13);
		nodes.add(node14);
		nodes.add(node15);
		nodes.add(node16);
		nodes.add(node17);
		nodes.add(node18);
		nodes.add(node19);
		nodes.add(node20);

		nRoute = new Routing(nodes);
		nRoute.findNeighbour(node1);
		nRoute.findNeighbour(node2);
		nRoute.findNeighbour(node3);
		nRoute.findNeighbour(node4);
		nRoute.findNeighbour(node5);
		nRoute.findNeighbour(node6);
		nRoute.findNeighbour(node7);
		nRoute.findNeighbour(node8);
		nRoute.findNeighbour(node9);
		nRoute.findNeighbour(node10);
		nRoute.findNeighbour(node11);
		nRoute.findNeighbour(node12);
		nRoute.findNeighbour(node13);
		nRoute.findNeighbour(node14);
		nRoute.findNeighbour(node15);
		nRoute.findNeighbour(node16);
		nRoute.findNeighbour(node17);
		nRoute.findNeighbour(node18);
		nRoute.findNeighbour(node19);
		nRoute.findNeighbour(node20);

		Scanner sc = new Scanner(System.in);
		System.out
				.println("enter the start node index: please enter an integer");
		int startNode = sc.nextInt();

		Node startN = nRoute.selectNodeById(startNode);

		System.out.println("enter the end node index: please enter an integer");
		int endNode = sc.nextInt();
		Node endN = nRoute.selectNodeById(endNode);
		// String endNeighbour = endN.toStringNeighbour(endN);
		// System.out.println("end node"+endN.toString());
		// for(int i =0;i<endN.getNeighbour().size();i++){
		// System.out.println(endN.getNeighbour().get(i).toString());
		// }
		System.out.println("enter the package through output .. integer");
		int buffer = sc.nextInt();
		nRoute.setDeliverBuffer(buffer);
		System.out.println("target deliver" + nRoute.getDeliverBuffer());

		nRoute.routing(buffer, startN, endN);
		nRoute.distanceRouting(buffer, startN, endN);
		while (true) {
			String enter = sc.nextLine();

			for (int i = 0; i < nodes.size(); i++) {
				int curBuf = nodes.get(i).getCurBuffer();
				Random rand = new Random();
				int  n = rand.nextInt(100) + 1;
				//100 is the maximum and the 1 is our minimum 
				curBuf += n;
				nodes.get(i).setCurBuffer(curBuf);
			}			
			
			nRoute.routing(buffer, startN, endN);
			nRoute.distanceRouting(buffer, startN, endN);
		}

	}

}
