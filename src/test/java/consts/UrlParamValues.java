package consts;

import java.util.Map;

public class UrlParamValues {
    public static final String API_KEY = "61f6e2e1819911ecd24e5ca76465980e";
    public static final String AUTH_TOKEN = "b5be5cbc855892da95f4182f1b5018bc9ba425057b56979a5bba60cff79a4fa7";

    public static final Map<String, String> QUERY_PARAMS_AUTH = Map.of("key", API_KEY, "token", AUTH_TOKEN);
    public static final Map<String, String> QUERY_PARAMS_FIELDS = Map.of("fields", "id,name");
    public static final Map<String, String> PATH_PARAM_MEMBER = Map.of("member", "ivokarchev");
    public static final String PATH_PARAM_ID = "id";

    public static final String EXISTING_BOARD_ID = "617938f1eaa2fd0e661089dc";
    public static final String EXISTING_CARD_ID = "617cd35314124046fc513c02";

    public static final Map<String, String> ANOTHER_USER_AUTH_QUERY_PARAMS = Map.of(
            "key", "8b32218e6887516d17c84253faf967b6",
            "token", "492343b8106e7df3ebb7f01e219cbf32827c852a5f9e2b8f9ca296b1cc604955"
    );
}
