import java.util.Comparator;

/**
 * ComparatorProba is used to compare Document objects based on their
 * cosine similarity to a query vector in the context of a probabilistic search engine.
 */
public class ComparatorProba implements Comparator<Document> {

	/** The vector representation of the query. */
	private double[] VectorRequete;

	/** The probabilistic search engine used for computing document vectors and similarities. */
	private ProbabilisticSearchEngine search;

	/** The query used for comparison. */
	private Query req;

	/**
	 * Constructs a ComparatorProba using the query vector, search engine, and query object.
	 *
	 * @param VectorRequete the vector representation of the query
	 * @param search the probabilistic search engine
	 * @param req the query used for document comparison
	 */
	public ComparatorProba(double[] VectorRequete, ProbabilisticSearchEngine search, Query req){
		this.VectorRequete = VectorRequete;
		this.search = search;
		this.req = req;
	}

	/**
	 * Compares two Document objects based on their cosine similarity with the query vector.
	 * If the similarity scores are equal, returns 1. Otherwise, returns the difference of
	 * their scores multiplied by 10,000 and cast to int.
	 *
	 * @param a the first Document to compare
	 * @param b the second Document to compare
	 * @return a negative integer, zero, or a positive integer as the first argument is less than,
	 *         equal to, or greater than the second based on similarity score
	 */
	@Override
	public int compare(Document a, Document b) {
		double[] vecteurDocA = search.caculeVecteurDoc(a, req);
		double[] vecteurDocB = search.caculeVecteurDoc(b, req);
		int result = 1;

		if ((search.cosineSimilarity(vecteurDocA, this.VectorRequete) * 10000) != (search.cosineSimilarity(vecteurDocB, this.VectorRequete)) * 10000) {
			result = (int) ((search.cosineSimilarity(vecteurDocA, this.VectorRequete) * 10000) - (search.cosineSimilarity(vecteurDocB, this.VectorRequete)) * 10000);
		}
		return result;
	}
}
