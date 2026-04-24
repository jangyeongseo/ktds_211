const TodoAppender = ({
  onTaskKeyUpHandler,
  onDateChangeHandler,
  onPrioritySelectChangeHandler,
  onSaveButtonClickHandler,
}) => {
  return (
    <footer>
      <input
        type="text"
        placeholder="Task"
        onKeyUp={onTaskKeyUpHandler} // 보관법 - interpolation : onEventName={eventHandlerFunction}
      />
      <input type="date" onChange={onDateChangeHandler} />
      <select onChange={onPrioritySelectChangeHandler}>
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
