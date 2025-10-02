import java.util.Comparator;

/**
 * ComparatorPoid compares two documents based on their calculated weight
 * with respect to a given query using a {@link BooleanSearchEngine}.
 * <p>
 * The documents are ordered by descending weight (after being multiplied by 1000
 * to preserve decimals in integer comparison). If the weights are equal,
 * their sum is used to ensure a non-zero result (not strictly total).
 */
public class ComparatorPoid implements Comparator<Document> {

	/**
	 * The query used to compute the document weight.
	 */
	private final Query q;

	/**
	 * The BooleanSearchEngine used to compute weights for documents.
	 */
	private final BooleanSearchEngine search;

	/**
	 * Constructs a ComparatorPoid with the specified query and search engine.
	 *
	 * @param q      the query against which document weights are evaluated
	 * @param search the BooleanSearchEngine providing the weight computation
	 */
	public ComparatorPoid(Query q, BooleanSearchEngine search) {
		this.q = q;
		this.search = search;
	}

	/**
	 * Compares two {@link Document} objects by their weight with respect to the query.
	 * If weights are equal, returns the sum (multiplied by 1000) to avoid a tie.
	 *
	 * @param a the first document to compare
	 * @param b the second document to compare
	 * @return a negative integer, zero, or a positive integer as the first document
	 *         is less than, equal to, or greater than the second in terms of weight
	 */
	@Override
	public int compare(Document a, Document b) {
		int weightA = (int) (search.calculePoidDoc(a, q) * 1000);
		int weightB = (int) (search.calculePoidDoc(b, q) * 1000);
		int result = weightA - weightB;

		if (weightA == weightB) {
			result = weightA + weightB;
		}

		return result;
	}
}

