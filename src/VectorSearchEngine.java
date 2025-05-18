import java.util.*;
import java.util.stream.Collectors;

public class VectorSearchEngine implements SearchEngine {

    private final Map<String, Keyword> keywords;
    private final Map<Integer, Document> documents;
    private final Map<Document, Double> cosineCache;

    /**
     * Precomputes the TF–IDF values and stores them in Document and Keyword objects.
     *
     * @param keywords map of terms to Keyword objects
     * @param documents map of document IDs to Document objects
     */
    public VectorSearchEngine(Map<String, Keyword> keywords, Map<Integer, Document> documents) {
        this.keywords    = keywords;
        this.documents   = documents;
        this.cosineCache = new HashMap<>();
        preprocessTfIdf();
    }

    /**
     * Calculates TF–IDF for each term and document, then stores
     * these values inside Document and Keyword TFIDF frequency maps.
     */
    private void preprocessTfIdf() {
        int N = documents.size();
        for (Keyword kw : keywords.values()) {
            String term = kw.getTerm();
            int df = kw.getOccurrences().size();
            double idf = Math.log((double) N / (df + 1)) + 1.0;

            for (Integer docId : kw.getOccurrences().keySet()) {
                Document doc = documents.get(docId);
                double tf = doc.get1Freq(term);
                double tfidf = tf * idf;

                // store in Document and Keyword
                doc.add1TFIDFFreq(term, tfidf);
                kw.add1TFIDFFreq(docId, tfidf);
            }
        }
    }

    /**
     * Performs a vector space model search for the given query.
     * Builds the query vector, identifies relevant documents,
     * computes cosine similarities, and returns documents sorted by similarity.
     *
     * @param requete the search query
     * @return a sorted set of documents relevant to the query
     */
    @Override
    public TreeSet<Document> search(Query requete) {
        String[] terms = requete.getTextRequete()
                .toLowerCase()
                .split("\\s+");

        double[] vecReq = new double[terms.length];
        TreeMap<String, Double> requeteTFIDF = new TreeMap<>();

        Set<Document> docsAComparer = new HashSet<>();

        // 1) Construct the query vector and collect relevant documents
        for (int i = 0; i < terms.length; i++) {
            String t = terms[i];
            Keyword kw = keywords.get(t);

            double poids = 0.0;
            if (kw != null) {
                int df = kw.getTFIDFFrequences().size();
                poids = Math.log((double) documents.size() / (df + 1)) + 1.0;
                requeteTFIDF.put(t, poids);

                // Collect documents containing this term
                docsAComparer.addAll(kw.getTFIDFFrequences().keySet().stream().map(id -> documents.get(id)).collect(Collectors.toSet()));
            }
            else {
                requeteTFIDF.put(t, 0.0);
            }

            vecReq[i] = poids;
        }

        // Save query frequencies
        requete.setFrequences(requeteTFIDF);

        // 2) Compute cosine similarity for relevant documents only
        cosineCache.clear();
        for (Document doc : docsAComparer) {
            double[] vecDoc = new double[terms.length];
            TreeMap<String, Double> tfidfDoc = doc.getTFIDFFrequences();

            for (int j = 0; j < terms.length; j++) {
                vecDoc[j] = tfidfDoc.getOrDefault(terms[j], 0.0);
            }

            double cos = cosineSimilarity(vecReq, vecDoc);
            cosineCache.put(doc, cos);
        }

        // 3) Sort results using ComparatorScalaire based on cosine similarity
        TreeSet<Document> sorted = new TreeSet<>(new ComparatorScalaire(cosineCache));
        sorted.addAll(docsAComparer); // only relevant documents

        return sorted;
    }

    /**
     * Computes the cosine similarity between two vectors of equal length.
     *
     * @param a first vector
     * @param b second vector
     * @return cosine similarity value between 0 and 1
     */
    private static double cosineSimilarity(double[] a, double[] b) {
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < a.length; i++) {
            dot   += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }
        double denom = Math.sqrt(normA) * Math.sqrt(normB);
        return denom == 0.0 ? 0.0 : dot / denom;
    }
}
