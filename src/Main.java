import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {

		// 1) Si pas de fichiers sérialisés -> indexer puis sauvegarder
		File docsFile = new File(Index.pathDocs);
		File vocFile = new File(Index.pathVoc);

		if (!docsFile.exists() || !vocFile.exists()) {
			System.out.println("Fichiers sérialisés introuvables, lancement de l'indexation...");
			new Indexation().indexer("hotels/data/chicago");

			Index.saveDocuments();
			Index.saveVocabulary();
			System.out.println("Indexation et sauvegarde terminées.");
		}
		else {
			System.out.println("Fichiers déjà présents, on passe au chargement.");
		}

		// 2) Chargement des données
		try {
			TreeMap<Integer, Document> documents = Index.loadDocuments();
			TreeMap<String, Keyword> vocabulary = Index.loadVocabulary();

			// 3) Choix du moteur
			SearchEngine engine = chooseEngine(vocabulary, documents);

			// 4) Boucle de requêtes
			runInteractiveLoop(engine);

		}
		catch (IOException | ClassNotFoundException e) {
			System.err.println("Impossible de charger les données: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Affiche le menu pour choisir un moteur de recherche et retourne le moteur sélectionné.
	 *
	 * @param vocab le vocabulaire (mots-clés) indexé
	 * @param docs  la collection des documents indexés
	 * @return une instance de SearchEngine correspondant au choix utilisateur
	 */
	private static SearchEngine chooseEngine(TreeMap<String, Keyword> vocab, TreeMap<Integer, Document> docs) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nQuel type de recherche souhaitez-vous utiliser ?");
		System.out.println("1) Boolean   2) Vectorielle   3) Probabiliste");
		int code = readInt(sc, "Votre choix (1-3) : ");

		SearchType type = SearchType.fromCode(code);
		SearchEngine engine = null;

		switch (type) {
			case VECTOR:
				engine = new VectorSearchEngine(vocab, docs);
				break;
			case PROBABILISTIC:
				engine = new ProbabilisticSearchEngine(vocab, docs);
				break;
			case BOOLEAN:
			default:
				System.out.println("Moteur booléen : utilisez les opérateurs AND, OR, NOT pour formuler vos requêtes.");
				engine = new BooleanSearchEngine(vocab, docs);
		}
		return engine;
	}

	/**
	 * Lit un entier depuis l'entrée standard en redemandant la saisie tant que l'entrée n'est pas un entier valide.
	 *
	 * @param sc    Scanner utilisé pour la lecture
	 * @param query message affiché pour inviter à la saisie
	 * @return l'entier saisi par l'utilisateur
	 */
	private static int readInt(Scanner sc, String query) {
		while (true) {
			System.out.print(query);
			if (sc.hasNextInt()) {
				int val = sc.nextInt();
				sc.nextLine();  // consomme le saut de ligne
				return val;
			}
			else {
				System.out.println("Entrée invalide, veuillez saisir un entier.");
				sc.nextLine();
			}
		}
	}

	/**
	 * Boucle interactive principale qui invite l'utilisateur à saisir des requêtes,
	 * exécute la recherche correspondante et affiche les résultats.
	 * La boucle continue jusqu'à ce que l'utilisateur saisisse 'quit'.
	 *
	 * @param engine moteur de recherche utilisé pour traiter les requêtes
	 */
	private static void runInteractiveLoop(SearchEngine engine) {
		Scanner sc = new Scanner(System.in);
		String line;

		do {
			System.out.print("\nTapez 'quit' pour quitter le moteur de recherche ou entrez votre requête: ");
			line = sc.nextLine().trim();

			if (line.isEmpty()) {
				System.out.println("Requête vide, veuillez réessayer.");
				continue;
			}

			if (!"quit".equalsIgnoreCase(line)) {
				Query query = new Query(line);
				Set<Document> results = engine.search(query);
				engine.displayResults(results);
			}

		} while (!"quit".equalsIgnoreCase(line));

		System.out.println("Au revoir !");
	}
}