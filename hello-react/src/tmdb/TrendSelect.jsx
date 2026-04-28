const TrendSelect = ({ type, setType }) => {
  const onTypeClickeHandler = () => {
    setType((prev) => (prev === "today" ? "week" : "today"));
  };

  // const ontodayClickeHandler = () => {
  //   setType("today");
  // };

  // const onWeekClickeHandler = () => {
  //   setType("week");
  // };

  return (
    <div>
      <button
        className={type === "today" ? "active" : ""}
        onClick={onTypeClickeHandler}
      >
        <span>오늘</span>
      </button>

      <button
        className={type === "week" ? "active" : ""}
        onClick={onTypeClickeHandler}
      >
        <span>이번 주</span>
      </button>
    </div>
  );
};

export default TrendSelect;
