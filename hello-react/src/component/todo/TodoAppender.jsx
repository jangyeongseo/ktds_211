// inputDate : {todo, dueDate, priority}
// -> onChange 이벤트 핸들러 함수 - newTodo 상태값 변경

const TodoAppender = ({
  newTodo,
  onNewTodoChangeHandler,
  onSaveButtonClickHandler,
}) => {
  return (
    <footer>
      <input
        type="text"
        name="todo"
        placeholder="Task"
        value={newTodo.todo}
        onChange={onNewTodoChangeHandler}
      />
      <input
        type="date"
        name="dueDate"
        onChange={onNewTodoChangeHandler}
        value={newTodo.dueDate}
      />
      <select
        name="priority"
        onChange={onNewTodoChangeHandler}
        value={newTodo.priority}
      >
        <option>우선순위</option>
        <option value="1">높음</option>
        <option value="2">보통</option>
        <option value="3">낮음</option>
      </select>
      <button type="button" onClick={onSaveButtonClickHandler}>
        Save
      </button>
    </footer>
  );
};

export default TodoAppender;
