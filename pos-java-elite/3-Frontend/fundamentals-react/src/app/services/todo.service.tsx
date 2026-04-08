import { Post } from "../model/todo";

export class TodoService {

    async getTodos(): Promise<Post> {
        const resp = await fetch('https://jsonplaceholder.typicode.com/todos');
        return await resp.json();
    }

    async getFirst() {
        return fetch('https://jsonplaceholder.typicode.com/todos/1');
    }
}