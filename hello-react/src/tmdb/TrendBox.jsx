import { useState } from "react";
import "./TrendBox.css";
import TrendHeader from "./TrendHeader";
import TrendList from "./TrendList";
import trend from "./trend.json";

const TrendBox = () => {
  const [type, setType] = useState("today");

  return (
    <div className="continent">
      <TrendHeader type={type} setType={setType} />
      <TrendList items={trend.items[type]} />
    </div>
  );
};

export default TrendBox;
