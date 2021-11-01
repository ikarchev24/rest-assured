package consts;

public class Endpoints {

    // Board endpoints
    public static final String GET_BOARD = "/1/boards/{id}";
    public static final String GET_BOARDS = "/1/members/{member}/boards";
    public static final String CREATE_BOARD = "/1/boards";

    // Card endpoints
    public static final String GET_CARD = "/1/cards/{id}";
    public static final String GET_CARDS_LIST = "/1/lists/{id}/cards";
    public static final String GET_CARDS_FOR_BOARD = "/1/boards/{id}/cards";

}
