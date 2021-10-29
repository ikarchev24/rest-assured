package consts;

import java.util.Map;

public class UrlParamValues {
    public static final String API_KEY = "61f6e2e1819911ecd24e5ca76465980e";
    public static final String AUTH_TOKEN = "b5be5cbc855892da95f4182f1b5018bc9ba425057b56979a5bba60cff79a4fa7";

    public static final Map<String, String> QUERY_PARAMS_AUTH = Map.of("key", API_KEY, "token", AUTH_TOKEN);

    public static final Map<String, String> QUERY_PARAMS_FIELDS = Map.of("fields", "id,name");

    public static final Map<String, String> PATH_PARAM_MEMBER = Map.of("member", "ivokarchev");
}
