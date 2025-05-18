import java.util.Objects;
import java.util.TreeMap;

/**
 * Represents a search query with its text, term occurrences, frequencies, and operator.
 */
public class Query {

	private String textQuery;
	private TreeMap<String,Integer> occurrences;
	private TreeMap<String,Double> frequences;
	private String operator;

	/**
	 * Constructs a Query object from the input text.
	 * Converts text to lowercase and initializes term occurrences and frequencies.
	 * Ignores the logical operators "or", "and", and "not" when counting terms.
	 *
	 * @param text the raw query text
	 */
	public Query (String text){
		textQuery = text.toLowerCase();

		occurrences = new TreeMap<String,Integer>();
		frequences = new TreeMap<String,Double>();

		String[] ligne= text.split(" ");

		for(String mot: ligne){
			// Filter out logical operators from term occurrences
			if(!mot.equals("or") && !mot.equals("and") && !mot.equals("not")){
				occurrences.put(mot, 1);
				frequences.put(mot, (double) (occurrences.get(mot) / occurrences.size()));
			}
		}
	}

	/**
	 * Returns the full text of the query.
	 * @return query text in lowercase
	 */
	public String getTextRequete(){
		return this.textQuery;
	}

	/**
	 * Returns the operator associated with the query.
	 * (Note: operator field is declared but not set in current code.)
	 * @return operator as a string
	 */
	public String getOperator(){
		return this.operator;
	}

	/**
	 * Sets the occurrences map.
	 * @param occurrences map of terms to their occurrence counts
	 */
	public void setOccurrences(TreeMap<String, Integer> occurrences) {
		this.occurrences = occurrences;
	}

	/**
	 * Returns the map of term occurrences.
	 * @return map of terms to occurrence counts
	 */
	public TreeMap<String, Integer> getOccurrences() {
		return occurrences;
	}

	/**
	 * Returns the map of term frequencies.
	 * @return map of terms to frequencies as Double
	 */
	public TreeMap<String, Double> getFrequences() {
		return frequences;
	}

	/**
	 * Sets the map of term frequencies.
	 * @param frequences map of terms to frequency values
	 */
	public void setFrequences(TreeMap<String, Double> frequences) {
		this.frequences = frequences;
	}
}
