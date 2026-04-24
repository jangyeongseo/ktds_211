const TodoHeader = ({ priority, todoData }) => {
  return (
    <>
      <li className="tasks-header">
        <input id="checkall" type="checkbox" />
        <label>Task</label>
        <span className="due-date">Due date</span>
        <span className="priority">Priority</span>
      </li>
      <li className="task-item">
        <input id="todo_1" type="checkbox" />
        <label htmlFor="todo_1">{todoData[0].todo}</label>
        <span className="due-date">{todoData[0].dueDate}</span>
        <span className="priority">{priority[todoData[0].priority]}</span>
      </li>
    </>
  );
};

export default TodoHeader;
