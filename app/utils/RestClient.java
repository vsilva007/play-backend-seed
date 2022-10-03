package utils;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSBodyWritables;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class RestClient implements WSBodyReadables, WSBodyWritables {
	private final WSClient ws;
	private String baseURL = "";

	public RestClient(WSClient ws, String url) {
		this.ws = ws;
		this.baseURL = url;
	}

	public JsonNode getVeosatData(int page) throws InterruptedException, ExecutionException {
		String url = this.baseURL + "veosat?page=" + page;
		CompletionStage<JsonNode> jsonPromise = getWsResponseCompletionStage(url).thenApply(WSResponse::asJson);
		return jsonPromise.toCompletableFuture().get();
	}

	public JsonNode getHeptanData(int page) throws InterruptedException, ExecutionException {
		String url = this.baseURL + "heptan?page=" + page;
		CompletionStage<JsonNode> jsonPromise = getWsResponseCompletionStage(url).thenApply(WSResponse::asJson);
		return jsonPromise.toCompletableFuture().get();
	}

	public void sendOrderToPowerBI(String json) {
		String url = this.baseURL + "pedido";
		this.ws.url(url).addHeader("Content-Type", "application/json").addHeader("X-AUTH-TOKEN", "RWE5XAckCqk=").post(json);
	}

	private CompletionStage<WSResponse> getWsResponseCompletionStage(String url) {
		return this.ws.url(url).addHeader("X-AUTH-TOKEN", "RWE5XAckCqk=").get();
	}
}
