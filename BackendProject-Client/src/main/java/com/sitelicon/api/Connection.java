package com.sitelicon.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.sitelicon.exceptions.NotFoundException;

/**
 * This class provides methods for making HTTP requests, such as GET, POST, PUT,
 * and DELETE. It uses the {@link HttpClient} class from the Java standard
 * library to handle the HTTP communication.
 *
 * <p>
 * The methods include:
 * <ul>
 * <li>{@link #doGet(String)}: Sends a GET request to the specified URL and
 * retrieves the response body.</li>
 * <li>{@link #doPost(String, String)}: Sends a POST request with the given body
 * to the specified URL.</li>
 * <li>{@link #doUpdate(String, String)}: Sends a PUT request with the given
 * body to the specified URL.</li>
 * <li>{@link #doDelete(String)}: Sends a DELETE request to the specified
 * URL.</li>
 * </ul>
 * Additionally, these methods handle HTTP response status codes, throwing a
 * {@link NotFoundException} for a 404 status code and a general
 * {@link Exception} for other error codes.
 *
 * @see HttpClient
 * @see HttpRequest
 * @see HttpResponse
 */
public class Connection {

	/**
	 * Sends a GET request to the specified URL and retrieves the response body.
	 *
	 * @param url The URL to send the GET request to.
	 * @return The response body as a string.
	 * @throws NotFoundException If the HTTP response status code is 404 (Not
	 *                           Found).
	 * @throws Exception         If an unexpected HTTP response status code is
	 *                           received.
	 */
	public String doGet(String url) throws Exception {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				return response.body();
			} else if (response.statusCode() == 404) {
				throw new NotFoundException();
			} else {
				throw new Exception("Error: " + response.statusCode());
			}

		}

	}

	/**
	 * Sends a POST request with the given body to the specified URL.
	 *
	 * @param body The body of the POST request.
	 * @param url  The URL to send the POST request to.
	 * @throws Exception If an unexpected HTTP response status code is received.
	 */
	public void doPost(String body, String url) throws Exception {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
				.POST(HttpRequest.BodyPublishers.ofString(body)).header("Content-Type", "application/json").build();

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
		}

	}

	/**
	 * Sends a PUT request with the given body to the specified URL.
	 *
	 * @param body The body of the PUT request.
	 * @param url  The URL to send the PUT request to.
	 * @return The response body as a string.
	 * @throws NotFoundException If the HTTP response status code is 404 (Not
	 *                           Found).
	 * @throws Exception         If an unexpected HTTP response status code is
	 *                           received.
	 */
	public String doUpdate(String body, String url) throws Exception {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
				.PUT(HttpRequest.BodyPublishers.ofString(body)).header("Content-Type", "application/json").build();

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());

			if (response.statusCode() == 200) {
				return response.body();
			} else if (response.statusCode() == 404) {
				throw new NotFoundException();
			} else {
				throw new Exception("Error: " + response.statusCode());
			}
		}

	}

	/**
	 * Sends a DELETE request to the specified URL.
	 *
	 * @param url The URL to send the DELETE request to.
	 * @return The response body as a string.
	 * @throws NotFoundException If the HTTP response status code is 404 (Not
	 *                           Found).
	 * @throws Exception         If an unexpected HTTP response status code is
	 *                           received.
	 */
	public String doDelete(String url) throws Exception {
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).DELETE().build();

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());

			if (response.statusCode() == 200) {
				return response.body();
			} else if (response.statusCode() == 404) {
				throw new NotFoundException();
			} else {
				throw new Exception("Error: " + response.statusCode());
			}
		}

	}

}
