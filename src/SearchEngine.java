import java.util.Set;
import java.util.TreeSet;

/**
 * Common interface for all search engine implementations.
 */
public interface SearchEngine {

    /**
     * Executes a search query and returns a sorted set of matching documents.
     *
     * @param q the query object containing the search text
     * @return a TreeSet of Documents matching the query
     */
    TreeSet<Document> search(Query q);

    /**
     * Displays the search results to the standard output.
     * Prints a message if no results are found.
     *
     * @param results a set of Documents to display
     */
    default void displayResults(Set<Document> results) {
        if (results.isEmpty()) {
            System.out.println("Aucun résultat trouvé.");
        }
        else {
            for (Document d : results) {
                System.out.println(d.getDate() + " - " + d.getTitle());
                System.out.println(d.getText());
                System.out.println();
            }
            System.out.printf("%d résultat(s) trouvé(s)%n", results.size());
        }
    }
}

