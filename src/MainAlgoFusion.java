/**
 * Main class
 */

public class MainAlgoFusion {
	/**
	 * this class is to create a mazgen and a grid with the algo fusion
	 *
	 * @param args take an integer which refere the maze
	 */

	public static void main(String[] args) {
		/*
		 * 
		 * Cell position=new Cell(4,0) {
		 * 
		 * } ;
		 * 
		 * Parchemin parchemin=new Parchemin("name", position);
		 * Hints hint =new Hints(" ",false) {
		 * 
		 * };
		 * Jewels jewels=new Jewels("joyaux", 100);
		 * int gold=100;
		 * Hero hero = new Hero("Hero", position);
		 * Sphynx sphynx = new Sphynx("Sphynx", position);
		 * 
		 */
		FusionAlgorithm fusion = new FusionAlgorithm(7, 4);
		ExplorationAlgorithm explo = new ExplorationAlgorithm(7, 4);

		// labyrinthe fusion
		String res = fusion.apply().consoleDisplay();
		// labyrinth explo
		String resu = explo.apply().consoleDisplay();

		System.out.println("labyrinthe fusion de taille (7 ,4)");
		// afichage fusion
		System.out.println(res);
		System.out.println("\n");
		System.out.println("labyrinthe exploration de taille (7,4)");
		// affichage resu
		System.out.println(resu);

		/*
		 * System.out.println(hero.getName());
		 * System.out.println(hero.getPos());
		 * System.out.println(jewels.DisplayJewel());
		 * System.out.println(parchemin.toString());
		 * 
		 * 
		 * 
		 * for (var line : grid.consoleDisplay()) {
		 * System.out.println(line);
		 */
	}
}
