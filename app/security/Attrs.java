package security;

import play.libs.typedmap.TypedKey;

public class Attrs {
	public static final TypedKey<VerifiedJwt> VERIFIED_JWT = TypedKey.create("verifiedJwt");
	public static final Integer ACCESS_TOKEN_EXP_MINUTES = 10;
	public static final Integer REFRESH_TOKEN_EXP_DAYS = 1;
}
