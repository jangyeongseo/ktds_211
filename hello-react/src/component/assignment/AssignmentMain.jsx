import React, { useState } from "react";
import Counter from "./Counter.jsx";
import "./Assignment.css";
import Calc from "./Calc.jsx";

export const AssignmentMain = () => {
  const [number, setNumber] = useState(0);

  // Counter - increment, decrement, includes
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
  const [nums, setNums] = useState({ num1: 0, num2: 0, result: 0 });

  // parseInt, parseFloat, Number => 숫자 변환
  // Number : 소숫점까지 작성 가능, parseInt : 정수만, parseFloat : 소숫점까지 작성 가능
  const onValueChangeHandler = (type, value) => {
    setNums((prev) => ({ ...prev, [type]: Number(value) }));
  };

  // operatoer: +, -, *, / => result
  // const onCalcClickHandler = (op) => {
  //   setNums((prev) => {
  //     const { num1, num2 } = prev;
  //     let result = 0;
  //     if (op === "+") result = num1 + num2;
  //     if (op === "-") result = num1 - num2;
  //     if (op === "*") result = num1 * num2;
  //     if (op === "/") result = num2 !== 0 ? num1 / num2 : 0;

  //     return { ...prev, result };
  //   });
  // };

  const onCalcClickHandler = (op) => {
    let result = 0;
    if (op === "+") result = num1 + num2;
    if (op === "-") result = num1 - num2;
    if (op === "*") result = num1 * num2;
    if (op === "/") result = num2 !== 0 ? num1 / num2 : 0;

    // Extended Object
    setNums((prev) => {
      return { ...prev, result };
    });
  };

  return (
    <div className="container">
      <Counter number={number} onCountChangeHandler={onCountChangeHandler} />

      <Calc
        num1={nums.num1}
        num2={nums.num2}
        result={nums.result}
        onValueChangeHandler={onValueChangeHandler}
        onCalcClickHandler={onCalcClickHandler}
      />
    </div>
  );
};
