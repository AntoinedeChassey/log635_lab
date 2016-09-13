package controller;

public class Main {

	public static void main(String[] args) {

		Build world = new Build();

		for (int i = 0; i < world.getHumans().size(); i++) {
			System.out.println(world.getHumans().get(i).getName());
		}

		System.out.println();

		for (int i = 0; i < world.getZombies().size(); i++) {
			System.out.println(world.getZombies().get(i).getName());
		}

		// voila on a les datas comme on veut, c'est partit mon kiki
	}
}
