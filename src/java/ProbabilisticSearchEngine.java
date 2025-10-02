import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Implementation of a probabilistic search engine.
 * Uses probabilistic frequencies to rank documents according to a query.
 */
public class ProbabilisticSearchEngine implements SearchEngine {

	private final double pi = 0.1;

	TreeSet<Document> listeDoc;
	TreeMap<String, Keyword> keywords;
	TreeMap<Integer, Document> documents;
	private ArrayList<double[]> vector;
	private double[] VectorRequete;

	/**
	 * Constructs a probabilistic search engine with given keywords and documents.
	 * Initializes probabilistic frequencies for all documents and keywords.
	 *
	 * @param keywords map of keyword terms to Keyword objects
	 * @param documents map of document IDs to Document objects
	 */
	public ProbabilisticSearchEngine(TreeMap<String, Keyword> keywords, TreeMap<Integer, Document> documents){
		this.keywords = keywords;
		this.documents = documents;
		vector = new ArrayList<>();

		remplirFrequenceProb();
	}

	/**
	 * Calculates the probability Q_i of a term occurring in documents.
	 *
	 * @param mot the term (keyword)
	 * @param idDoc document ID
	 * @return probability Q_i for the term
	 */
	public double calculeQI(String mot, int idDoc){
		int nbDoc = keywords.get(mot).getFrequencesProb().keySet().size();
		return (double)(nbDoc) / (double)(documents.size());
	}

	/**
	 * Fills probabilistic frequencies for all keywords in all documents.
	 * Uses formula involving pi and Q_i to compute values.
	 */
	public void remplirFrequenceProb(){
		double qi, val;

		for (Integer idDoc : documents.keySet()) {
			for (String mot : documents.get(idDoc).getOccurrences().keySet()) {

				qi = calculeQI(mot, idDoc);
				val = Math.log(((double)(pi * (1 - qi))) / (double)((1 - pi) * qi));

				documents.get(idDoc).add1TFreqProb(mot, val);
				keywords.get(mot).add1FreqProb(idDoc, val);
			}
		}
	}

	/**
	 * Searches documents matching the query using probabilistic model.
	 * Returns a sorted set of documents based on similarity.
	 *
	 * @param requete query object containing the search terms
	 * @return sorted set of matching documents
	 */
	@Override
	public TreeSet<Document> search(Query requete){
		vector.clear();
		String textReq = (requete.getTextRequete()).toLowerCase();
		String[] motReq = textReq.split(" ");

		this.VectorRequete = new double[motReq.length];

		for (int i = 0; i < motReq.length; i++) {
			this.VectorRequete[i] = 1;
		}

		this.listeDoc = new TreeSet<Document>(new ComparatorProba(VectorRequete, this, requete));

		for (Document doc : documents.values()) {
			for (String mot : motReq) {
				if (doc.existsFreqProb(mot)) {
					this.listeDoc.add(doc);
				}
			}
		}

		return listeDoc;
	}

	/**
	 * Calculates the probabilistic frequency vector for a given document according to a query.
	 *
	 * @param a document to calculate vector for
	 * @param requete query containing search terms
	 * @return array representing the probabilistic frequency vector
	 */
	public double[] caculeVecteurDoc(Document a, Query requete){
		double[] vecteur = new double[this.VectorRequete.length];

		String textReq = (requete.getTextRequete()).toLowerCase();
		String[] motReq = textReq.split(" ");

		int i = 0;

		for (String mot : motReq) {

			if (a.get1FreqProb(mot) != null) {
				vecteur[i] = a.get1FreqProb(mot);
			}
			else {
				vecteur[i] = 0.0;
			}
			i++;
		}

		return vecteur;
	}

	/**
	 * Computes the cosine similarity between two vectors.
	 *
	 * @param vectorA first vector
	 * @param vectorB second vector
	 * @return cosine similarity score, or 0 if norm is zero
	 */
	public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		for (int i = 0; i < vectorA.length; i++) {
			dotProduct += vectorA[i] * vectorB[i];
			normA += vectorA[i] * vectorA[i];
			normB += vectorB[i] * vectorB[i];
		}
		double denominator = Math.sqrt(normA) * Math.sqrt(normB);
        return (denominator == 0.0) ? 0.0 : dotProduct / denominator;
	}
}
