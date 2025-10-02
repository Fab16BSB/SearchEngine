import java.io.*;
import java.util.TreeMap;

/**
 * The {@code Index} class manages an inverted index structure consisting of keywords and documents.
 * It allows adding, retrieving, and serializing/deserializing the index data.
 */
public class Index {

    /** Path to the serialized documents file. */
    static final String pathDocs = "../resources/documents.data";

    /** Path to the serialized vocabulary file. */
    static final String pathVoc = "../resources/vocabulary.data";

    /** Map of keywords, where the key is a word and the value is its associated {@code Keyword} object. */
    static TreeMap<String, Keyword> keywords;

    /** Map of documents, where the key is a document ID and the value is the {@code Document} object. */
    static TreeMap<Integer, Document> documents;

    /**
     * Constructs an empty {@code Index} with initialized maps.
     */
    public Index() {
        keywords = new TreeMap<String, Keyword>();
        documents = new TreeMap<Integer, Document>();
    }

    /**
     * Retrieves the {@code Keyword} object associated with a specific word.
     *
     * @param key the word to retrieve
     * @return the corresponding {@code Keyword} object, or {@code null} if not found
     */
    public Keyword getKeyword(String key) {
        return keywords.get(key);
    }

    /**
     * Adds a {@code Keyword} object to the index.
     *
     * @param key the word to associate with the keyword
     * @param keyword the {@code Keyword} object to add
     */
    public void addKeyword(String key, Keyword keyword) {
        keywords.put(key, keyword);
    }

    /**
     * Retrieves a {@code Document} by its ID.
     *
     * @param id the document ID
     * @return the corresponding {@code Document} object, or {@code null} if not found
     */
    public Document getDocument(Integer id) {
        return documents.get(id);
    }

    /**
     * Adds a {@code Document} to the index.
     *
     * @param id the document ID
     * @param doc the {@code Document} object to add
     */
    public void addDocument(Integer id, Document doc) {
        documents.put(id, doc);
    }

    /**
     * Saves the current vocabulary map to disk.
     */
    public static void saveVocabulary() {
        try {
            File fileTemp = new File(pathVoc);
            if (fileTemp.exists()) {
                fileTemp.delete();
            }

            FileOutputStream f_out = new FileOutputStream(pathVoc);
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(keywords);

        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Saves the current documents map to disk.
     */
    public static void saveDocuments() {
        try {
            File fileTemp = new File(pathDocs);

            if (fileTemp.exists()) {
                fileTemp.delete();
            }

            FileOutputStream f_out = new FileOutputStream(pathDocs);
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(documents);

        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Generic method to load a {@code TreeMap} from a serialized file.
     *
     * @param <K> the key type of the map
     * @param <V> the value type of the map
     * @param path the file path to load from
     * @param description a description used for logging
     * @return the loaded {@code TreeMap}
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    private static <K, V> TreeMap<K, V> loadMap(String path, String description) throws IOException, ClassNotFoundException {

        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(description + " not found at: " + file.getAbsolutePath());
        }

        System.out.println("Loading " + description + " from \"" + path + "\" …");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            TreeMap<K, V> map = (TreeMap<K, V>) ois.readObject();
            System.out.printf("Done: %s (%d entries)%n", description, map.size());
            return map;
        }
    }

    /**
     * Loads the serialized documents map from disk.
     *
     * @return the loaded documents map
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class cannot be found
     */
    public static TreeMap<Integer, Document> loadDocuments() throws IOException, ClassNotFoundException {
        try {
            documents = loadMap(pathDocs, "Documents");
        }
        catch (InvalidClassException e) {
            System.err.println("Outdated files detected, reindexing required.");
            new File(pathDocs).delete();
            new Indexation().indexer("hotels/data/chicago");
            saveDocuments();
            documents = loadMap(pathDocs, "Documents");
        }
        return documents;
    }

    /**
     * Loads the serialized vocabulary map from disk.
     *
     * @return the loaded vocabulary map
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class cannot be found
     */
    public static TreeMap<String, Keyword> loadVocabulary() throws IOException, ClassNotFoundException {
        try {
            keywords = loadMap(pathVoc, "Vocabulary");
        }
        catch (InvalidClassException e) {
            System.err.println("Outdated files detected, reindexing required.");
            new File(pathDocs).delete();
            new Indexation().indexer("hotels/data/chicago");
            saveDocuments();
            keywords = loadMap(pathVoc, "Vocabulary");
        }
        return keywords;
    }

    /**
     * Retrieves the term frequencies for all words in a given document.
     *
     * @param id the document ID
     * @return a map of words to their frequencies in the document
     */
    public TreeMap<String, Double> getMotFrequence(int id) {
        return documents.get(id).getFrequences();
    }

    /**
     * Retrieves the frequencies of a word across all documents.
     *
     * @param mot the word to retrieve
     * @return a map of document IDs to frequency values
     */
    public TreeMap<Integer, Double> getDocFrequence(String mot) {
        return keywords.get(mot).getFrequences();
    }
}
