const TodoItem = ({ priority, todo, onCheckChange }) => {
  const doneClassName = todo.ischeck ? "done-todo" : "";

  const onDoneChangeHandler = (e) => {
    onCheckChange && onCheckChange(todo.id, e.target.checked);
  };

  return (
    <>
      <li className="task-item" key={todo.id}>
        <input
          id={todo.id}
          type="checkbox"
          checked={todo.ischeck}
          onChange={onDoneChangeHandler}
        />
        <label htmlFor={todo.id} className={doneClassName}>
          {todo.todo}
        </label>
        <span className={`due-date ${doneClassName}`}>{todo.dueDate}</span>
        <span className={`priority ${doneClassName}`}>
          {priority[todo.priority]}
        </span>
      </li>
    </>
  );
};

export default TodoItem;

// default export는 하나의 컴포넌트만 내보낼 수 있음.
export const TodoItemForChildren = ({ children }) => {
  return <li className="tasks-item">{children}</li>;
};
