const Counter = ({ number, onCountChangeHandler }) => {
  return (
    <div className="counter-box">
      <h1>COUNTER</h1>
      <div className="counter">
        <button
          className="btn btn-minus"
          onClick={() => onCountChangeHandler("minus")}
        >
          -
        </button>

        <div className="count">Count: {number}</div>

        <button
          className="btn btn-plus"
          onClick={() => onCountChangeHandler("add")}
        >
          +
        </button>
      </div>
    </div>
  );
};
export default Counter;
