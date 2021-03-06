package consts;

public class Endpoints {

    // Board endpoints /1/boards/{id}
    public static final String GET_BOARD = "/1/boards/{id}";
    public static final String GET_BOARDS = "/1/members/{member}/boards";
    public static final String CREATE_BOARD = "/1/boards";
    public static final String DELETE_BOARD = "/1/boards/{id}";
    public static final String UPDATE_BOARD = "/1/boards/{id}";

    // Card endpoints
    public static final String GET_CARD = "/1/cards/{id}";
    public static final String GET_CARDS_LIST = "/1/lists/{id}/cards";
    public static final String GET_CARDS_FOR_BOARD = "/1/boards/{id}/cards";
    public static final String CREATE_CARD = "/1/cards";
    public static final String DELETE_CARD = "/1/cards/{id}";
    public static final String UPDATE_CARD = "/1/cards/{id}";

}
