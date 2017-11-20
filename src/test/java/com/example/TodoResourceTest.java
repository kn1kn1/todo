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

public class TodoResourceTest {

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
	public void testTodo() {
		Response response = target.path("todos/0").request()
				.get(Response.class);
		assertEquals(404, response.getStatus());
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testDeleteTodo() {
		String entity = "{\"title\":\"テストタイトル\",\"contents\":\"テストコンテンツ\"}";
		Response response = target.path("todos").request()
				.post(Entity.entity(entity, MediaType.APPLICATION_JSON));
		assertEquals(201, response.getStatus());

		String responseMsg = target.path("todos").request("application/json")
				.get(String.class);
		System.out.println("responseMsg: " + responseMsg);

		response = target.path("todos/0").request().delete();
		assertEquals(200, response.getStatus());
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testDeleteTodo_404() {
		Response response = target.path("todos/0").request().delete();
		assertEquals(404, response.getStatus());
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testUpdateTodo() {
		String entity = "{\"title\":\"テストタイトル\",\"contents\":\"テストコンテンツ\"}";
		Response response = target.path("todos").request()
				.post(Entity.entity(entity, MediaType.APPLICATION_JSON));
		assertEquals(201, response.getStatus());

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

		String entity2 = "{\"title\":\"テストタイトル2\",\"contents\":\"テストコンテンツ2\"}";
		Response response2 = target.path("todos/0").request()
				.put(Entity.entity(entity2, MediaType.APPLICATION_JSON));
		assertEquals(200, response2.getStatus());

		Response response3 = target.path("todos/search").queryParam("q", "テスト")
				.request("application/json").get(Response.class);
		assertEquals(200, response3.getStatus());

		todoList = response3.readEntity(new GenericType<List<Todo>>() {
		});
		assertEquals(1, todoList.size());

		todo = todoList.get(0);
		assertEquals("0", todo.getId());
		assertEquals("テストタイトル2", todo.getTitle());
		assertEquals("テストコンテンツ2", todo.getContents());
	}
}
