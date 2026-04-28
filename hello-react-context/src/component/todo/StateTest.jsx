import { useState } from "react";

export const StateTest = () => {
  // 변경 가능한 상수를 생성한다. (state)
  const [text, setText] = useState("Initial Value");
  const onTextKeyUpHandler = (e) => {
    setText(e.target.value);
  };

  return <StateTestItem text={text} onKeyUp={onTextKeyUpHandler} />;
};

const StateTestItem = ({ text, onKeyUp }) => {
  return (
    <div>
      {text}
      <div>
        <input type="text" onKeyUp={onKeyUp} />
      </div>
    </div>
  );
};
