import java.io.Serializable;
import java.util.TreeMap;

/**
 * Represents a keyword and stores its statistics across documents.
 * Includes occurrences, frequencies, TF-IDF frequencies, and probabilistic frequencies.
 */
public class Keyword implements Serializable {

    private String terme;
    private TreeMap<Integer,Integer> occurrences;
    private TreeMap<Integer,Double> frequences;
    private TreeMap<Integer,Double> TFIDFfrequences;
    private TreeMap<Integer,Double> frequencesProb;

    /**
     * Constructs a Keyword object with the specified term.
     * Initializes the maps for occurrences and different types of frequencies.
     *
     * @param mot the term (keyword) this object represents
     */
    public Keyword(String mot){
        occurrences = new  TreeMap<Integer,Integer>();
        frequences = new  TreeMap<Integer,Double>();
        TFIDFfrequences = new  TreeMap<Integer,Double>();
        frequencesProb = new  TreeMap<Integer,Double>();
        this.terme = mot;
    }

    /**
     * Returns the number of occurrences of the keyword in the given document.
     *
     * @param idDoc document ID
     * @return number of occurrences or null if not present
     */
    public Integer get1Occur(Integer  idDoc) {
        return occurrences.get(idDoc);
    }

    /**
     * Checks whether occurrences exist for the specified document.
     *
     * @param idDoc document ID
     * @return true if occurrences exist, false otherwise
     */
    public boolean existsOccur(Integer  idDoc) {
        return occurrences.containsKey(idDoc);
    }

    /**
     * Adds or updates the number of occurrences for a document.
     *
     * @param idDoc document ID
     * @param occur number of occurrences
     */
    public void add1Occur(Integer idDoc, Integer occur){
        occurrences.put(idDoc,occur);
    }

    /**
     * Returns the frequency of the keyword in the given document.
     *
     * @param idDoc document ID
     * @return frequency or null if not present
     */
    public Double get1Freq(Integer  idDoc) {
        return frequences.get(idDoc);
    }

    /**
     * Adds or updates the frequency for a document.
     *
     * @param idDoc document ID
     * @param freq frequency value
     */
    public void add1Freq(Integer idDoc, Double freq){
        frequences.put(idDoc,freq);
    }

    /**
     * Checks whether frequency exists for the specified document.
     *
     * @param idDoc document ID
     * @return true if frequency exists, false otherwise
     */
    public boolean existsFreq(Integer  idDoc) {
        return occurrences.containsKey(idDoc);
    }

    /**
     * Returns the TF-IDF frequency of the keyword in the given document.
     *
     * @param idDoc document ID
     * @return TF-IDF frequency or null if not present
     */
    public Double get1TFIDFFreq(Integer  idDoc) {
        return TFIDFfrequences.get(idDoc);
    }

    /**
     * Adds or updates the TF-IDF frequency for a document.
     *
     * @param idDoc document ID
     * @param freq TF-IDF frequency value
     */
    public void add1TFIDFFreq(Integer idDoc, Double freq){
        TFIDFfrequences.put(idDoc,freq);
    }

    /**
     * Checks whether TF-IDF frequency exists for the specified document.
     *
     * @param idDoc document ID
     * @return true if TF-IDF frequency exists, false otherwise
     */
    public boolean existsTFIDFreq(Integer  idDoc) {
        return TFIDFfrequences.containsKey(idDoc);
    }

    /**
     * Returns the probabilistic frequency of the keyword in the given document.
     *
     * @param idDoc document ID
     * @return probabilistic frequency or null if not present
     */
    public Double get1FreqProb(Integer  idDoc) {
        return frequencesProb.get(idDoc);
    }

    /**
     * Adds or updates the probabilistic frequency for a document.
     *
     * @param idDoc document ID
     * @param freq probabilistic frequency value
     */
    public void add1FreqProb(Integer idDoc, Double freq){
        frequencesProb.put(idDoc,freq);
    }

    /**
     * Checks whether probabilistic frequency exists for the specified document.
     *
     * @param idDoc document ID
     * @return true if probabilistic frequency exists, false otherwise
     */
    public boolean existsFreqProb(Integer  idDoc) {
        return frequencesProb.containsKey(idDoc);
    }

    /**
     * Returns the map of frequencies (relative) for all documents.
     *
     * @return map of document ID to frequency
     */
    public TreeMap<Integer, Double> getFrequences() {
        return frequences;
    }

    /**
     * Returns the map of occurrences for all documents.
     *
     * @return map of document ID to occurrences count
     */
    public TreeMap<Integer, Integer> getOccurrences() {
        return occurrences;
    }

    /**
     * Returns the map of TF-IDF frequencies for all documents.
     *
     * @return map of document ID to TF-IDF frequency
     */
    public TreeMap<Integer, Double> getTFIDFFrequences() {
        return TFIDFfrequences;
    }

    /**
     * Returns the map of probabilistic frequencies for all documents.
     *
     * @return map of document ID to probabilistic frequency
     */
    public TreeMap<Integer, Double> getFrequencesProb() {
        return frequencesProb;
    }

    /**
     * Returns the term (keyword) represented by this object.
     *
     * @return the term as a string
     */
    public String getTerm() {
        return terme;
    }
}
