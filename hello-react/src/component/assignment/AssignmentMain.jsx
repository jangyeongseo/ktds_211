import React, { useState } from "react";
import Counter from "./Counter.jsx";
import "./Assignment.css";
import Calc from "./Calc.jsx";

export const AssignmentMain = () => {
  const [number, setNumber] = useState(0);

  // Counter
  const onCountChangeHandler = (type) => {
    if (type === "add") {
      if (number >= 100) {
        alert("100이상 올릴 수 없습니다.");
        setNumber(100);
        return;
      }
      setNumber(number + 1);
    }
    if (type === "minus") {
      if (number <= 0) {
        alert("0 이하로 내릴 수 없습니다.");
        setNumber(0);
        return;
      }
      setNumber(number - 1);
    }
  };

  // Calc
  const [num1, setNum1] = useState(0);
  const [num2, setNum2] = useState(0);
  const [result, setResult] = useState(0);

  const onValueChangeHandler = (type, value) => {
    if (type === "num1") setNum1(Number(value));
    // parseInt(value)도 가능하지만, 소수점 입력도 가능하도록 Number()로 변경
    if (type === "num2") setNum2(Number(value));
  };

  const onCalcClickHandler = (op) => {
    if (op === "+") setResult(num1 + num2);
    if (op === "-") setResult(num1 - num2);
    if (op === "*") setResult(num1 * num2);
    if (op === "/") setResult(num2 !== 0 ? num1 / num2 : 0);
  };

  return (
    <div className="container">
      <Counter number={number} onCountChangeHandler={onCountChangeHandler} />

      <Calc
        num1={num1}
        num2={num2}
        result={result}
        onValueChangeHandler={onValueChangeHandler}
        onCalcClickHandler={onCalcClickHandler}
      />
    </div>
  );
};
