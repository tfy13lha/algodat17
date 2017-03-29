package lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class StableMarriage {

	private int nPairs, totProps;
	private Man[] men;
	private Woman[] women;
	private static boolean debug = true;

	public StableMarriage(InputStream in) {
		parseInput(new Scanner(in));
		findStableSolution();
	}

	private void parseInput(Scanner sc) {
		sc.skip("(#.*\\n)*n=");
		nPairs = sc.nextInt();

		men = new Man[nPairs];
		women = new Woman[nPairs];
		for (int i = 0; i < nPairs; i++) {
			sc.next();
			men[i] = new Man(i, sc.next());
			sc.next();
			women[i] = new Woman(sc.next());
		}

		for (int i = 0; i < nPairs * 2; i++) {
			String s = sc.next();
			// int idx = Integer.parseInt(sc.next("[^:]*"));
			int idx = Integer.parseInt(s.substring(0, s.length() - 1));
			if (idx % 2 == 1) {
				for (int j = 0; j < nPairs; j++) {
					men[idx / 2].preferenceOrder[j] = women[sc.nextInt() / 2 - 1];
				}
			} else {
				for (int j = 0; j < nPairs; j++) {
					women[idx / 2 - 1].preferenceScores[sc.nextInt() / 2] = j;
				}
			}
		}
		sc.close();
		return;
	}

	private void findStableSolution() {
		totProps = 0;
		for (int i = 0; i < nPairs; i++) {
			Man m = men[i];
			if (StableMarriage.debug) {
				System.out.printf("%s: finding me a mate. (%d/%d)%n", men[i].name, i + 1, nPairs);
			}
			while (m != null) {
				totProps++;
				m = m.findProposal();
			}
		}
	}

	public void printSolution(PrintStream out) {
		for (int i = 0; i < nPairs; i++) {
			out.printf("%s -- %s\n", men[i].getName(), men[i].getWifeName());
		}
		if (StableMarriage.debug) {
			System.out.printf("Did %d (n^2=%d) proposals.%n", totProps,nPairs*nPairs);
		}
	}

	public Man[] getMen() {
		return men;
	}

	public static void main(String[] args) {
		try {
			InputStream fis;
			if (args.length > 0) {
				File file = new File(args[0]);
				file = new File("sm-worst-500-in.txt");
				fis = new FileInputStream(file);
			} else {
				fis = System.in;
			}
			StableMarriage sm = new StableMarriage(fis);
			sm.printSolution(System.out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public class Man {
		private String name;
		private Woman[] preferenceOrder;
		private int currentProposalIndex;
		private int index;

		public Man(int index, String name) {
			this.name = name;
			this.index = index;
			this.preferenceOrder = new Woman[nPairs];
			this.currentProposalIndex = -1;
		}

		private Man findProposal() {
			while (true) {
				currentProposalIndex++;
				Woman w = preferenceOrder[currentProposalIndex];
				int newScore = w.preferenceScores[index];
				int oldScore = w.currentPreferenceScore;
				if ((oldScore == -1) || (oldScore > newScore)) {
					w.currentPreferenceScore = newScore;
					Man oldEng = w.currentEngagement;
					w.currentEngagement = this;
					if (StableMarriage.debug) {
						System.out.printf("%s: proposed to %s and she said YES (%d->%d).%n", name, w.name, oldScore,
								newScore);
					}
					if (oldScore != -1) {
						if (StableMarriage.debug) {
							System.out.printf("%s: I was kicked out.%n", oldEng.name);
						}
						return oldEng;
					} else {
						return null;
					}
				} else {
					if (StableMarriage.debug) {
						System.out.printf("%s: proposed to %s but she said NO (%d->%d).%n", name, w.name, oldScore,
								newScore);
					}
				}
			}
		}

		public String getName() {
			return name;
		}

		public String getWifeName() {
			return preferenceOrder[currentProposalIndex].name;
		}
	}

	public class Woman {
		private String name;
		private int[] preferenceScores;
		private int currentPreferenceScore;
		private Man currentEngagement;

		public Woman(String name) {
			this.name = name;
			this.preferenceScores = new int[nPairs];
			this.currentPreferenceScore = -1;
		}

		private int getPreferenceIndex(Man man) {
			int i;
			for (i = 0; i < nPairs; i++) {
				// if (preferenceOrder[i] == man) {
				break;
				// }
			}
			return i;
		}
	}

}
