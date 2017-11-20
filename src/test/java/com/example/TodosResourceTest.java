package com.example;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.models.Todo;

public class TodosResourceTest {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		// start the server
		server = Main.startServer();
		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());

		target = c.target(Main.BASE_URI);
	}

	@After
	public void tearDown() throws Exception {
		server.shutdownNow();
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testCreateTodo() {
		String entity = "{\"title\":\"テストタイトル\",\"contents\":\"テストコンテンツ\"}";
		Response response = target.path("todos").request()
				.post(Entity.entity(entity, MediaType.APPLICATION_JSON));
		assertEquals(201, response.getStatus());

		Todo todo = target.path("todos/0").request().get(Todo.class);
		assertEquals("0", todo.getId());
		assertEquals("テストタイトル", todo.getTitle());
		assertEquals("テストコンテンツ", todo.getContents());
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testSearchTodo() {
		String entity = "{\"title\":\"テストタイトル\",\"contents\":\"テストコンテンツ\"}";
		Response response = target.path("todos").request()
				.post(Entity.entity(entity, MediaType.APPLICATION_JSON));
		assertEquals(201, response.getStatus());

		String responseMsg = target.path("todos").request("application/json")
				.get(String.class);
		System.out.println("responseMsg: " + responseMsg);

		response = target.path("todos/search").queryParam("q", "テスト")
				.request("application/json").get(Response.class);
		assertEquals(200, response.getStatus());

		List<Todo> todoList = response
				.readEntity(new GenericType<List<Todo>>() {
				});

		assertEquals(1, todoList.size());

		Todo todo = todoList.get(0);
		assertEquals("0", todo.getId());
		assertEquals("テストタイトル", todo.getTitle());
		assertEquals("テストコンテンツ", todo.getContents());
	}
}
