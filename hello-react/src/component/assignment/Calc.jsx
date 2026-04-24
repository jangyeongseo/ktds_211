const Calc = ({
  num1,
  num2,
  result,
  onValueChangeHandler,
  onCalcClickHandler,
}) => {
  return (
    <div className="counter-box">
      <h1>Calc</h1>
      <div className="counter">
        <input
          type="number"
          value={num1}
          onChange={(e) => onValueChangeHandler("num1", e.target.value)}
        />
        <div className="btn-box">
          <button
            className="btn btn-plus"
            onClick={() => onCalcClickHandler("+")}
          >
            +
          </button>
          <button
            className="btn btn-minus"
            onClick={() => onCalcClickHandler("-")}
          >
            -
          </button>
          <button
            className="btn btn-multiply"
            onClick={() => onCalcClickHandler("*")}
          >
            ×
          </button>
          <button
            className="btn btn-divide"
            onClick={() => onCalcClickHandler("/")}
          >
            ÷
          </button>
        </div>
        <input
          type="number"
          value={num2}
          onChange={(e) => onValueChangeHandler("num2", e.target.value)}
        />
        <div className="count">=</div>
        <div className="count">{result}</div>
      </div>
    </div>
  );
};
export default Calc;
