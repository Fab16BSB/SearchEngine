/**
 * Enumeration representing different types of search methods.
 */
enum SearchType {
    /** Boolean search type, represented by code 1. */
    BOOLEAN(1),

    /** Vector search type, represented by code 2. */
    VECTOR(2),

    /** Probabilistic search type, represented by code 3. */
    PROBABILISTIC(3);

    /**
     * Integer code representing the search type.
     */
    private final int code;

    /**
     * Constructs a SearchType enum with the associated integer code.
     *
     * @param code integer code representing the search type
     */
    SearchType(int code) {
        this.code = code;
    }

    /**
     * Returns the SearchType corresponding to a given code.
     * Defaults to BOOLEAN if no matching code is found.
     *
     * @param code integer code to convert to SearchType
     * @return the corresponding SearchType enum
     */
    public static SearchType fromCode(int code) {
        SearchType result = BOOLEAN;

        for (SearchType type : values()) {
            if (type.code == code) {
                result = type;
            }
        }
        return result;
    }
}
