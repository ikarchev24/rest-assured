package consts;

public class Endpoints {

    // Board endpoints
    public static final String GET_BOARD_ENDPOINT = "/1/boards/{id}";
    public static final String GET_BOARDS_ENDPOINT = "/1/members/{member}/boards";

    // Card endpoints
    public static final String GET_CARDS_ENDPOINT = " /1/boards/{id}/cards";
    public static final String GET_CARD_ENDPOINT = "/1/cards/{id}";
    public static final String GET_CARDS_LIST_ENDPOINT = "/1/lists/{id}/cards";
    public static final String GET_CARDS_FOR_BOARD_ENDPOINT = "/1/boards/{id}/cards";

}
