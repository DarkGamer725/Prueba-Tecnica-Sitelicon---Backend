package com.sitelicon.api;

/**
 * The base class for API services, providing common functionalities and
 * resources. It includes the base URL for API requests and an instance of the
 * {@link Connection} class for handling HTTP connections.
 *
 * <p>
 * The {@link #URL} constant represents the base URL for API requests and is set
 * to "http://localhost:8080/api". The {@link #connection} field is an instance
 * of the {@link Connection} class used for making HTTP requests.
 * 
 * @see Connection
 */
public class ApiService {
	/**
	 * The base URL for API requests.
	 */
	final String URL = "http://localhost:8080/api";

	/**
	 * An instance of the {@link Connection} class for handling HTTP connections.
	 */
	final Connection connection = new Connection();

}
