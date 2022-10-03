/*
 * MIT License
 *
 * Copyright (c) 2017 Franz Granlund
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package security;

import com.auth0.jwt.interfaces.DecodedJWT;
import play.libs.Json;

import java.util.Date;
import java.util.List;

public class VerifiedJwtImpl implements VerifiedJwt {
	private String header;
	private String payload;
	private String issuer;
	private String userId;
	private List<Integer> permissions;
	private Date expiresAt;

	public VerifiedJwtImpl(DecodedJWT decodedJWT) {
		this.header = decodedJWT.getHeader();
		this.payload = decodedJWT.getPayload();
		this.issuer = decodedJWT.getIssuer();
		this.expiresAt = decodedJWT.getExpiresAt();
		this.userId = decodedJWT.getClaim("user_id").asString();
		this.permissions = decodedJWT.getClaim("permissions").asList(Integer.class);
	}

	@Override
	public String getHeader() {
		return header;
	}

	@Override
	public String getPayload() {
		return payload;
	}

	@Override
	public String getIssuer() {
		return issuer;
	}

	@Override
	public Date getExpiresAt() {
		return expiresAt;
	}

	@Override
	public String toString() {
		return Json.toJson(this).toString();
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public List<Integer> getPermissions() {
		return this.permissions;
	}
}
