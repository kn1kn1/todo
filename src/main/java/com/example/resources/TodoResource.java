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
	 * @apiDefine TodoNotFoundError
	 *
	 * @apiError (Error 404) TodoNotFound Todoが存在しない.
	 *
	 * @apiErrorExample  Response (example):
	 *     HTTP/1.1 404 Not Found
	 *     {
	 *       "error": "TodoNotFound"
	 *     }
	 */
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
	 * 
	 * @apiUse TodoNotFoundError
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodo() throws JSONException {
		Todos todos = Todos.getInstance();
		Todo todo = todos.getTodo(id);
		if (todo == null) {
			String m = "error: TodoNotFound";
			return Response.status(Status.NOT_FOUND).entity(m).build();
		}

		String message = toJSON(todo).toString();
		return Response.ok(message).build();
	}

	/**
	 * @api {delete} /todos/{todoid} Delete
	 * @apiName DeleteTodo
	 * @apiGroup Todo
	 * 
	 * @apiUse TodoNotFoundError
	 */
	@DELETE
	public Response deleteTodo() throws JSONException {
		Todos todos = Todos.getInstance();
		Todo todo = todos.delteTodo(id);
		if (todo == null) {
			String m = "error: TodoNotFound";
			return Response.status(Status.NOT_FOUND).entity(m).build();
		}
		return Response.ok().build();
	}

	/**
	 * @api {put} /todos/{todoid} Update
	 * @apiName UpdateTodo
	 * @apiGroup Todo
	 * 
	 * @apiParam {String} [title] todoのタイトル.
	 * @apiParam {String} [contents] todoの内容.
	 *
	 * @apiParamExample {json} Request-Example: 
	 * 					{ "title": "example todo",
	 *                  "contents": "This is an example content" }
	 * 
	 * @apiUse TodoNotFoundError
	 * @apiError (Error 400) InvalidJsonSyntax JSONの文法が不正.
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTodo(String message) {
		Todos todos = Todos.getInstance();
		Todo todo = todos.getTodo(id);
		if (todo == null) {
			String m = "error: TodoNotFound";
			return Response.status(Status.NOT_FOUND).entity(m).build();
		}
		System.out.println("message: " + message);
		JSONObject json = null;
		try {
			json = new JSONObject(message);
		} catch (JSONException e) {
			String m = "error: InvalidJsonSyntax";
			return Response.status(Status.BAD_REQUEST).entity(m).build();
		}
		try {
			String title = json.getString("title");
			todo.setTitle(title);
		} catch (JSONException e) {
			// タイトルが設定されていない場合も許容するので無視する
		}
		try {
			String contents = json.getString("contents");
			todo.setContents(contents);
		} catch (JSONException e) {
			// 内容が設定されていない場合も許容するので無視する
		}
		return Response.ok().build();
	}

	public static JSONObject toJSON(Todo todo) throws JSONException {
		return new JSONObject().put("id", todo.getId())
				.put("title", todo.getTitle())
				.put("contents", todo.getContents());
	}
}
