package duke.logic.message;

public class ProductMessageUtils {
    public static final String MESSAGE_DUPLICATE_PRODUCT = "Product with name \"%s\" already exists in the "
        + "product list";
    public static final String MESSAGE_ADD_PRODUCT_SUCCESS = "New product: %s added";
    public static final String MESSAGE_INVALID_STATUS_VALUE = "-status can only take active/archive";
    public static final String MESSAGE_INVALID_NUMBER_FORMAT = "Invalid number format";
    public static final String MESSAGE_MISSING_PRODUCT_NAME = "use -name to specify a product name";
    public static final String MESSAGE_CONSTRAIN_NAME = "Product's name cannot be blank and must be "
        + "50 characters or less";
    public static final String MESSAGE_LIST_SCOPE = "%s products shown";
    public static final String MESSAGE_INVALID_SCOPE_VALUE = "-scope can only take active/archive/all";
    public static final String MESSAGE_COMMIT_ADD_PRODUCT = "Add product";
    public static final String MESSAGE_COMMIT_DELETE_PRODUCT = "Delete product(s)";
    public static final String MESSAGE_COMMIT_EDIT_PRODUCT = "Edit product";
    public static final String MESSAGE_MISSING_KEYWORD = "A keyword must be specified";
    public static final String MESSAGE_MISSING_SEARCH_PREFIX = "Use -include prefix to specify a "
            + "keyword";
    public static final String MESSAGE_SEARCH_PRODUCT_SHOWN = "Products containing keyword \" %s \" shown";
    public static final String MESSAGE_INVALID_CATEGORY = "-by must be present and can only take "
        + "name/cost/price/profit";
    public static final String MESSAGE_SORT_PRODUCT_SUCCESS = "Product successfully sorted by %s";
    public static final String MESSAGE_SUCCESS_SHOW_PRODUCT = "Products are listed";
    public static final String MESSAGE_SHOW_PRODUCT_SUCCESS = "Showing product %s";
    public static final String MESSAGE_NON_EMPTY_REVERSE_PARAMETER = "-re should not take any value";
    public static final String MESSAGE_INVALID_INGREDIENT_FORMAT = "Invalid input, please use "
        + "[INGREDIENT_NAME, PORTION_NUMBER] format";
    public static final String MESSAGE_INDEX_OUT_OF_BOUND = "No product found at index [%d].";
    public static final String MESSAGE_EMPTY_INDICES = "Indices cannot be empty.";
    public static final String MESSAGE_DELETE_SUCCESS = "Product(s) removed.";


}
