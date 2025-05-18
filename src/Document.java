import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;


import java.io.Serializable;
import java.util.TreeMap;

/**
 * The {@code Document} class represents a textual document with metadata (ID, title, date)
 * and frequency-based representations used for various information retrieval models.
 * It supports raw word occurrences, normalized frequencies, TF-IDF scores, and
 * probabilistic term frequencies.
 *
 * Implements {@code Serializable} to allow serialization of document objects.
 */
public class Document implements Serializable {

    /** Unique identifier for the document. */
    private Integer id;

    /** Raw text content of the document. */
    private String text;

    /** Title of the document. */
    private String title;

    /** Date associated with the document. */
    private String date;

    /** Map of word occurrences in the document. */
    private TreeMap<String, Integer> occurrences;

    /** Normalized term frequencies for the document. */
    private TreeMap<String, Double> frequences;

    /** TF-IDF weighted frequencies for terms in the document. */
    private TreeMap<String, Double> TFIDFfrequences;

    /** Probabilistic frequencies of terms (e.g., for probabilistic models). */
    private TreeMap<String, Double> frequencesProb;

    /**
     * Constructs a new {@code Document} with the given ID.
     * Initializes all frequency maps.
     *
     * @param id the unique identifier of the document
     */
    public Document(Integer id){
        this.id = id;
        occurrences = new TreeMap<>();
        frequences = new TreeMap<>();
        TFIDFfrequences = new TreeMap<>();
        frequencesProb = new TreeMap<>();
    }

    /** @return the raw text of the document */
    public String getText() {
        return text;
    }

    /**
     * Sets the raw text content of the document.
     * @param texte the text to set
     */
    public void setText(String texte) {
        this.text = texte;
    }

    /** @return the title of the document */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the document.
     * @param titre the title to set
     */
    public void setTitle(String titre) {
        this.title = titre;
    }

    /** @return the date of the document */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the document.
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Adds or updates the number of occurrences of a given word in the document.
     * @param mot the word
     * @param occurr the number of occurrences
     */
    public void add1Occur(String mot, Integer occurr){
        occurrences.put(mot, occurr);
    }

    /**
     * @param mot the word
     * @return the number of occurrences of the word, or {@code null} if not present
     */
    public Integer get1Occur(String mot){
        return occurrences.get(mot);
    }

    /**
     * @param mot the word
     * @return {@code true} if the word exists in the occurrence map
     */
    public boolean existsOccur(String mot){
        return occurrences.containsKey(mot);
    }

    /**
     * Adds or updates the normalized frequency of a word.
     * @param mot the word
     * @param frequence the frequency value
     */
    public void add1Freq(String mot, Double frequence){
        frequences.put(mot, frequence);
    }

    /**
     * @param mot the word
     * @return the normalized frequency of the word, or {@code null} if not present
     */
    public Double get1Freq(String mot){
        return frequences.get(mot);
    }

    /**
     * @param mot the word
     * @return {@code true} if the frequency is defined for this word
     */
    public boolean existsFreq(String mot){
        return frequences.containsKey(mot);
    }

    /**
     * Adds or updates the TF-IDF frequency of a word.
     * @param mot the word
     * @param frequence the TF-IDF score
     */
    public void add1TFIDFFreq(String mot, Double frequence){
        TFIDFfrequences.put(mot, frequence);
    }

    /**
     * @param mot the word
     * @return the TF-IDF frequency of the word, or {@code null} if not present
     */
    public Double get1TFIDFFreq(String mot){
        return TFIDFfrequences.get(mot);
    }

    /**
     * @param mot the word
     * @return {@code true} if the word has a TF-IDF frequency value
     */
    public boolean existsTFIDFFreq(String mot){
        return TFIDFfrequences.containsKey(mot);
    }

    /**
     * Adds or updates the probabilistic frequency of a word.
     * @param mot the word
     * @param frequence the probabilistic frequency
     */
    public void add1TFreqProb(String mot, Double frequence){
        frequencesProb.put(mot, frequence);
    }

    /**
     * @param mot the word
     * @return the probabilistic frequency of the word, or {@code null} if not present
     */
    public Double get1FreqProb(String mot){
        return frequencesProb.get(mot);
    }

    /**
     * @param mot the word
     * @return {@code true} if the word has a probabilistic frequency
     */
    public boolean existsFreqProb(String mot){
        return frequencesProb.containsKey(mot);
    }

    /** @return the map of normalized frequencies for all terms in the document */
    public TreeMap<String, Double> getFrequences() {
        return frequences;
    }

    /** @return the map of word occurrences in the document */
    public TreeMap<String, Integer> getOccurrences() {
        return occurrences;
    }

    /** @return the map of TF-IDF scores for all terms in the document */
    public TreeMap<String, Double> getTFIDFFrequences() {
        return TFIDFfrequences;
    }

    /** @return the map of probabilistic frequencies for all terms in the document */
    public TreeMap<String, Double> getFrequencesProb() {
        return frequencesProb;
    }

    /** @return the unique ID of the document */
    public int getId(){
        return this.id;
    }
}
