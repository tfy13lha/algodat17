package Lab4;

public class Main {

	public static void main(String[] args) {

		String fileName = "points.txt";
		
		String[] longlist={
				"att48-tsp.txt",
				"ali535-tsp.txt",
				"a280-tsp.txt",
				"att532-tsp.txt",
				"berlin52-tsp.txt",
				"bier127-tsp.txt",
				"brd14051-tsp.txt",
				"wc-instance-1022.txt",
				"wc-instance-126.txt",
				"wc-instance-14.txt",
				"wc-instance-16382.txt",
				"wc-instance-254.txt",
				"u724-tsp.txt",
				"ulysses16-tsp.txt",
				"burma14-tsp.txt",
				"rl1889-tsp.txt",
				"sw24978-tsp.txt",
				"u159-tsp.txt",
				"pr1002-tsp.txt",
				"pr2392-tsp.txt",
				"rat195-tsp.txt",
				"rd100-tsp.txt",
				"rd400-tsp.txt",
				"p654-tsp.txt",
				"pcb3038-tsp.txt",
				"pla7397-tsp.txt",
				"pla85900-tsp.txt",
				"kroA200-tsp.txt",
				"kroA100-tsp.txt",
				"kroE100-tsp.txt",
				"lin105-tsp.txt",
				"fl1577-tsp.txt",
				"gr137-tsp.txt",
				"gr202-tsp.txt",
				"eil101-tsp.txt",
				"fl417-tsp.txt",
				"dsj1000-tsp.txt",
				"eil51-tsp.txt",
				"close-pairs-1-in.txt",
				"close-pairs-2-in.txt",
				"close-pairs-3-in.txt",
				"close-pairs-4-in.txt",
				"close-pairs-5-in.txt",
				"close-pairs-6-in.txt",
				"wc-instance-30.txt",
				"wc-instance-4094.txt",
				"wc-instance-6.txt",
				"wc-instance-62.txt",
				"wc-instance-65534.txt"
				
		};
		boolean dolonglist=true;

		if (args.length > 0||dolonglist) {
			String[] list=args;
			if (dolonglist) {
				list=longlist;
			}
			for (String fn : list) {
				System.out.print(fn+": ");
				ClosestPair pair = new ClosestPair(fn);
				pair.writeData();
			}
		} else {

			ClosestPair pair = new ClosestPair(fileName);

			pair.writeData();
		}
	}
}
