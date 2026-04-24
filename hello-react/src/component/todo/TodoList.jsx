import TodoItem, { TodoItemForChildren } from "./TodoItem";

const TodoList = ({ priority, todoData, onCheckChange }) => {
  return (
    <>
      {todoData.map((todo) => (
        <TodoItem
          key={todo.id}
          priority={priority}
          todo={todo}
          onCheckChange={onCheckChange}
        />
        // <TodoItemForChildren>
        //   <input id={todo.id} type="checkbox" />
        //   <label htmlFor={todo.id}>{todo.todo}</label>
        //   <span className="due-date">{todo.dueDate}</span>
        //   <span className="priority">{priority[todo.priority]}</span>
        // </TodoItemForChildren>
      ))}
    </>
  );
};

export default TodoList;
