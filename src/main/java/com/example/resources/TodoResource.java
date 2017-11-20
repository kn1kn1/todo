package com.example.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.example.models.Todo;
import com.example.models.Todos;

public class TodoResource {
	private final String id;

	public TodoResource(String id) {
		this.id = id;
	}

	/**
	 * @api {get} /todos/{todoid} Get
	 * @apiName GetTodo
	 * @apiGroup Todo
	 *
	 * @apiSuccess {String} id Todoのid.
	 * @apiSuccess {String} title Todoのタイトル.
	 * @apiSuccess {String} contents Todoの内容.
	 * 
	 * @apiSuccessExample {json} Success-Response: 
	 * HTTP/1.1 200 OK 
	 * {
	 *	"id": "0", "title": "todo", "contents": "todoの内容"
	 * }
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodo() throws JSONException {
		Todos todos = Todos.getInstance();
		Todo todo = todos.getTodo(id);
		if (todo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		String message = toJSON(todo).toString();
		return Response.ok(message).build();
	}

	/**
	 * @api {delete} /todos/{todoid} Delete
	 * @apiName DeleteTodo
	 * @apiGroup Todo
	 */
	@DELETE
	public Response deleteTodo() throws JSONException {
		Todos todos = Todos.getInstance();
		Todo todo = todos.delteTodo(id);
		if (todo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().build();
	}

	/**
	 * @api {put} /todos/{todoid} Update
	 * @apiName UpdateTodo
	 * @apiGroup Todo
	 *
	 * @apiParamExample {json} Request-Example: 
	 * 					{ "title": "example todo",
	 *                  "contents": "This is an example content" }
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTodo(String message) {
		Todos todos = Todos.getInstance();
		Todo todo = todos.getTodo(id);
		if (todo == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		System.out.println("message: " + message);
		try {
			JSONObject json = new JSONObject(message);
			String title = json.getString("title");
			String contents = json.getString("contents");
			todo.setTitle(title);
			todo.setContents(contents);
		} catch (JSONException e) {
			String m = "something wrong with JSON body: " + e.getMessage();
			return Response.status(Status.BAD_REQUEST).entity(m).build();
		}
		return Response.ok().build();
	}

	public static JSONObject toJSON(Todo todo) throws JSONException {
		return new JSONObject().put("id", todo.getId())
				.put("title", todo.getTitle())
				.put("contents", todo.getContents());
	}
}
