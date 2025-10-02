import java.util.Comparator;
import java.util.Map;

/**
 * A comparator for Document objects based on their similarity scores.
 * This comparator allows sorting documents in data structures like TreeSet
 * using similarity scores provided in a map. Documents with higher scores
 * should be ranked before those with lower scores.
 *
 * Note: the compare logic falls back to the sum of scores if values are equal,
 * which does not guarantee a strict total order and may lead to issues in sorted collections.
 *
 */
public class ComparatorScalaire implements Comparator<Document> {

	/**
	 * A map associating each {@link Document} with its computed similarity score.
	 */
	private final Map<Document, Double> scalaireDoc;

	/**
	 * Constructs a ComparatorScalaire using a map of similarity scores.
	 *
	 * @param scalaireDoc a map associating each Document to its similarity score
	 */
	public ComparatorScalaire(Map<Document, Double> scalaireDoc) {
		this.scalaireDoc = scalaireDoc;
	}

	/**
	 * Compares two documents based on their scalar score (similarity) with the query.
	 * Documents are sorted in descending order of score. If both documents have the same score,
	 * they are further sorted by ascending document ID to ensure consistent ordering.
	 *
	 * @param a the first document to compare
	 * @param b the second document to compare
	 * @return a negative integer, zero, or a positive integer as document {@code b} has a lower,
	 *         equal, or higher score than document {@code a} (descending score, then ascending ID)
	 */
	@Override
	public int compare(Document a, Document b) {
		int result = Double.compare(scalaireDoc.get(b), scalaireDoc.get(a));

		if (result == 0){
			result = Integer.compare(a.getId(), b.getId());
		}
		return result;
	}

}
