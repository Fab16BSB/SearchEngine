import java.io.*;
import java.util.ArrayList;

/**
 * Class responsible for indexing documents by processing text files,
 * extracting keywords, and managing stop words.
 */
public class Indexation {

	/**
	 * Index containing all keywords and documents.
	 */
	private Index index;

	/**
	 * Unique identifier for each document indexed.
	 */
	private int id;

	/**
	 * List of keywords extracted from documents (not used in current code).
	 */
	private ArrayList<String> motCle;

	/**
	 * List of stop words to be ignored during indexing.
	 */
	private ArrayList<String> motStopWord;

	/**
	 * Constructs a new Indexation instance.
	 * Initializes the index and loads the stop words from the file "stopwords.txt".
	 * If the file cannot be opened, prints an error message.
	 */
	public Indexation() {
		index = new Index();
		this.id = 0;

		File repertoire = new File("../resources/stopwords.txt");
		motStopWord = new ArrayList<String>();

		// Load stop words from file into motStopWord list
		InputStream st;

		try {
			st = new FileInputStream(repertoire);
			InputStreamReader str = new InputStreamReader(st);
			BufferedReader bf = new BufferedReader(str);

			String ligne;
			while ((ligne = bf.readLine()) != null) {
				motStopWord.add(ligne);
			}
		}
		catch (IOException e) {
			System.out.println("Error opening stopwords file");
		}
	}

	/**
	 * Indexes all documents found in the directory specified by the given URL (path).
	 * For each file, reads its contents line by line, extracts document metadata
	 * (date, title, text), splits text into words, filters out stop words,
	 * and updates the index with keywords and frequencies.
	 *
	 * @param url the path to the directory containing files to be indexed
	 */
	public void indexer(String url) {

		File repertoire = new File(url);
		File[] fichiers = repertoire.listFiles();

		String ligne;

		for (File file : fichiers) {
			try {
				InputStream st = new FileInputStream(file);
				InputStreamReader str = new InputStreamReader(st);
				BufferedReader bf = new BufferedReader(str);

				while ((ligne = bf.readLine()) != null) {
					Document doc = new Document(id);
					String[] phrase = ligne.split("\t");
					String[] com;

					if (phrase.length == 2) {
						doc.setDate(phrase[0]);
						doc.setText(phrase[1]);

						com = phrase[1].split(" ");
					}
					else {
						doc.setDate(phrase[0]);
						doc.setTitle(phrase[1]);
						doc.setText(phrase[2]);

						com = phrase[2].split(" ");
					}

					for (String s : com) {
						s.toLowerCase();

						if (!motStopWord.contains(s)) {
							Keyword k;

							if (!doc.getOccurrences().containsKey(s)) {
								k = new Keyword(s);
								doc.add1Occur(s, 1);
								doc.add1Freq(s, 1.0);
								k.add1Occur(id, 1);
								k.add1Freq(id, 1.0);

							}
							else {
								k = index.getKeyword(s);
								doc.add1Occur(s, doc.get1Occur(s) + 1);
								doc.add1Freq(s, (doc.get1Freq(s) + 1.0 / doc.getFrequences().size()));
								k.add1Occur(id, k.get1Occur(id) + 1);
								k.add1Freq(id, k.get1Freq(id) + 1.0 / k.getFrequences().size());
							}
							index.addKeyword(s, k);
						}
					}

					index.addDocument(id, doc);
					this.id += 1;
				}
			}
			catch (IOException e) {
				System.out.println("Error reading file");
			}
		}
	}
}
