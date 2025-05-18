import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Implements a boolean search engine that supports basic boolean queries
 * ("and", "or", "not") over a collection of documents using indexed keywords.
 *
 * The engine returns the documents matching the boolean expression and sorts
 * them according to a custom weight comparator.
 */
public class BooleanSearchEngine implements SearchEngine {

	/**
	 * A map of all keywords in the corpus, each associated with frequency data
	 * across documents.
	 */
	private final TreeMap<String, Keyword> keywords;

	/**
	 * A map of all documents indexed by their unique integer ID.
	 */
	private final TreeMap<Integer, Document> documents;

	/**
	 * Constructs a BooleanSearchEngine using provided keyword and document maps.
	 *
	 * @param keywords  a map of keyword strings to {@link Keyword} objects
	 * @param documents a map of document IDs to {@link Document} objects
	 */
	public BooleanSearchEngine(TreeMap<String, Keyword> keywords, TreeMap<Integer, Document> documents) {
		this.keywords = keywords;
		this.documents = documents;
	}

	/**
	 * Performs a boolean search over the indexed documents using a {@link Query}.
	 *
	 * Supports queries of the form "keyword1 and keyword2", "keyword1 or keyword2",
	 * and "keyword1 not keyword2". Returns a sorted set of documents that match the query,
	 * using a custom comparator that evaluates document weight with respect to the query.
	 *
	 * @param requete the query to process
	 * @return a {@link TreeSet} of matching documents sorted by weight
	 */
	@Override
	public TreeSet<Document> search(Query requete) {

		TreeSet<Document> listeDocument = new TreeSet<>(new ComparatorPoid(requete, this));
		Set<Integer> listeId = new TreeSet<>();

		String texteReq = requete.getTextRequete();
		String[] mot = texteReq.split(" ");
		String operator = mot[1];

		Set<Integer> docMot1 = new HashSet<>();
		Set<Integer> docMot2 = new HashSet<>();

		if (keywords.get(mot[0]) != null) {
			docMot1 = keywords.get(mot[0]).getFrequences().keySet();
		}

		if (keywords.get(mot[2]) != null) {
			docMot2 = keywords.get(mot[2]).getFrequences().keySet();
		}

		switch (operator) {
			case "and":
				listeId.addAll(docMot1);
				listeId.retainAll(docMot2);
				break;
			case "or":
				listeId.addAll(docMot1);
				listeId.addAll(docMot2);
				break;
			case "not":
				listeId.addAll(docMot1);
				listeId.removeAll(docMot2);
				break;
		}

		for (int id : listeId) {
			listeDocument.add(documents.get(id));
		}

		return listeDocument;
	}

	/**
	 * Calculates the weight of a document with respect to a given query.
	 * <p>
	 * For a simple query (single word), returns the word frequency in the document.
	 * For a boolean query, recursively evaluates sub-queries using min (for "and")
	 * or max (for "not") of the sub-query weights.
	 *
	 * @param doc the document to evaluate
	 * @param req the query to evaluate against
	 * @return the computed weight (or -1 if query is invalid)
	 */
	public double calculePoidDoc(Document doc, Query req) {

		String[] tab = req.getTextRequete().split(" ");
		double poids = -1;

		if (tab.length == 1) {
			if (doc.get1Freq(tab[0]) == null) {
				poids = 0;
			}
			else {
				poids = doc.get1Freq(tab[0]);
			}
		}

		if (tab[1].equals("and")) {
			Query q0 = new Query(tab[0]);
			Query q2 = new Query(tab[2]);
			poids = Math.min(calculePoidDoc(doc, q0), calculePoidDoc(doc, q2));
		}

		if (tab[1].equals("not")) {
			Query q0 = new Query(tab[0]);
			Query q2 = new Query(tab[2]);
			poids = Math.max(calculePoidDoc(doc, q0), calculePoidDoc(doc, q2));
		}

		return poids;
	}
}
